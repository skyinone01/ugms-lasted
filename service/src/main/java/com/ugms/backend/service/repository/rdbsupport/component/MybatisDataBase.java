package com.ugms.backend.service.repository.rdbsupport.component;


import com.ugms.backend.bean.base.request.PageRequest;
import com.ugms.backend.bean.exception.WebapiException;
import com.ugms.backend.bean.result.PagedResult;
import com.ugms.backend.service.repository.rdbsupport.handler.DataHandler;
import com.ugms.backend.service.repository.rdbsupport.handler.DataHandlerImpl;
import com.ugms.backend.service.repository.rdbsupport.tools.DataTool;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.HSQLDialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.Oracle10gDialect;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.*;

public class MybatisDataBase implements MybatisData {

    private SqlSessionTemplate dataService;
    private Dialect dialect;
    private SqlSessionFactory sqlSessionFactory;

    public void initialize() {

        if (dialect != null) {
            OffsetLimitInterceptor offsetLimitInterceptor = new OffsetLimitInterceptor();
            offsetLimitInterceptor.setDialect(dialect);
            sqlSessionFactory.getConfiguration().addInterceptor(
                    offsetLimitInterceptor);
        }
        dataService = new SqlSessionTemplate(sqlSessionFactory);

    }


    public <T> List<T> getData(String getData, Object parameter) {
        return dataService.selectList(getData, parameter);
    }

    public <T, V> Map<T, V> getData(String getData, String name,
                                    Object parameter) {
        return dataService.selectMap(getData, parameter, name);
    }

    public <T> T getObject(String getObject, Object parameter) {
        return dataService.<T>selectOne(getObject, parameter);
    }

    public <T> Map<String, T> getMap(String getMap, Object parameter) {
        return dataService.<Map<String, T>>selectOne(getMap, parameter);
    }

    public void insertData(String insertData, Object parameter) {
        dataService.insert(insertData, parameter);
    }

    public void updateData(String updateData, Object parameter) {
        dataService.update(updateData, parameter);
    }

    public void deleteData(String deleteData, Object parameter) {
        dataService.delete(deleteData, parameter);
    }

    public <T> PagedResult<T> getDataPageBatch(String getData,
                                               String getDataCount, Object parameter,
                                               PageRequest pageRequest) {
        return executeDataPageBatch(getData, getDataCount,
                DataTool.convertToMap(parameter), pageRequest);
    }

    private <T> PagedResult<T> executeDataPageBatch(String getData,
                                                    String getDataCount, Object parameter,
                                                    PageRequest pageRequest) {

        int page = pageRequest.getPage();
        int size = pageRequest.getSize();
        if (StringUtils.isBlank(getData) == false && StringUtils.isBlank(getDataCount) == false
                && page > 0 && size > 0) {

            int totalCount = dataService.selectOne(getDataCount, parameter);
            List<T> list = dataService.selectList(getData, parameter, new RowBounds((page - 1) * size, size));
            return new PagedResult<T>(list, totalCount);
        } else {
            throw new WebapiException("check page params and sql");
        }
//		if (reginaPage.getPageRows() > 0) {
//			if (getDataCount != null && "".equals(getDataCount) == false) {
//				reginaPage.setRowSum(((Integer) dataService.selectOne(
//						getDataCount, parameter)).intValue());
//			}
//			int pageCurrent = reginaPage.getPageCurrent();
//			if (pageCurrent > 0) {
//				pageCurrent = pageCurrent - 1;
//			}
//			reginaPage.setRowDatas(dataService.<T> selectList(getData,
//					parameter,
//					new RowBounds(pageCurrent * reginaPage.getPageRows(),
//							reginaPage.getRowsCurrent())));
//		} else {
//
//			reginaPage.setRowDatas(dataService.<T> selectList(getData,
//					parameter));
//			reginaPage.setRowSum(reginaPage.getRowData().size());
//		}
//		return reginaPage;
    }

    @Intercepts({@Signature(type = Executor.class, method = "query", args = {
            MappedStatement.class, Object.class, RowBounds.class,
            ResultHandler.class})})
    private class OffsetLimitInterceptor implements Interceptor {
        private int MAPPED_STATEMENT_INDEX = 0;
        private int PARAMETER_INDEX = 1;
        private int ROWBOUNDS_INDEX = 2;
        private Dialect dialect;

        public Object intercept(Invocation invocation) throws Throwable {
            processIntercept(invocation.getArgs());
            return invocation.proceed();
        }

        private void processIntercept(final Object[] queryArgs) {
            final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
            int offset = rowBounds.getOffset();
            int limit = rowBounds.getLimit();
            if (dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
                MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
                Object parameter = queryArgs[PARAMETER_INDEX];
                boolean symbolable = dialect.supportsLimitOffset();
                queryArgs[MAPPED_STATEMENT_INDEX] = createMappedStatement(ms,
                        symbolable, parameter, offset, limit);
                if (symbolable && offset > 0) {
                    offset = RowBounds.NO_ROW_OFFSET;
                }
                limit = RowBounds.NO_ROW_LIMIT;
                queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);
            }
        }

        private MappedStatement createMappedStatement(MappedStatement ms,
                                                      boolean symbolable, Object parameter, int offset, int limit) {
            BoundSql boundSql = ms.getBoundSql(parameter);
            String sql = boundSql.getSql().trim();
            List<ParameterMapping> list = boundSql.getParameterMappings();
            if (list.size() == 0) {
                list = new ArrayList<ParameterMapping>();
            }
            if (symbolable) {
                sql = dialect.getLimitString(sql, offset, limit);
            } else {
                sql = dialect.getLimitString(sql, 0, limit);
            }
            sql = addParameter(list, ms, parameter, offset, limit, sql,
                    symbolable);
            BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql,
                    list, boundSql.getParameterObject());
            return copyFromMappedStatement(ms, new BoundSqlSqlSource(
                    newBoundSql));
        }

        private MappedStatement copyFromMappedStatement(MappedStatement ms,
                                                        SqlSource newSqlSource) {
            Builder builder = new Builder(
                    ms.getConfiguration(), ms.getId(), newSqlSource,
                    ms.getSqlCommandType());
            builder.resource(ms.getResource());
            builder.fetchSize(ms.getFetchSize());
            builder.statementType(ms.getStatementType());
            builder.keyGenerator(ms.getKeyGenerator());
            // if (ms.getKeyProperties() != null
            // && ms.getKeyProperties().length > 0) {
            // builder.keyProperty(ms.getKeyProperties()[0]);
            // }
            builder.timeout(ms.getTimeout());
            builder.parameterMap(ms.getParameterMap());
            builder.resultMaps(ms.getResultMaps());
            builder.resultSetType(ms.getResultSetType());
            builder.cache(ms.getCache());
            builder.flushCacheRequired(ms.isFlushCacheRequired());
            builder.useCache(ms.isUseCache());
            return builder.build();
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        private String addParameter(List<ParameterMapping> parameterMapping,
                                    MappedStatement ms, Object parameter, int offset, int limit,
                                    String sql, boolean symbolable) {
            if (dialect instanceof Oracle10gDialect) {
                if (parameter instanceof Map) {
                    Map map = (Map) parameter;
                    if (symbolable && offset > 0) {
                        map.put("offset", offset);
                        map.put("limit", offset + limit);
                    } else {
                        map.put("limit", limit);
                    }
                }
                parameterMapping.add(new ParameterMapping.Builder(ms
                        .getConfiguration(), "limit", Integer.class).build());
                if (symbolable && offset > 0) {
                    parameterMapping.add(new ParameterMapping.Builder(ms
                            .getConfiguration(), "offset", Integer.class)
                            .build());
                }
            } else {
                if (dialect instanceof MySQLDialect) {
                    if (parameter instanceof Map) {
                        Map map = (Map) parameter;
                        if (symbolable && offset > 0) {
                            map.put("offset", offset);
                            map.put("limit", limit);
                        } else {
                            map.put("limit", limit);
                        }
                    }

                    Iterator<ParameterMapping> iterator = parameterMapping.iterator();
                    while (iterator.hasNext()) {
                        ParameterMapping temp = iterator.next();
                        if (temp.getProperty().equals("offset")) {
                            iterator.remove();
                        }
                        if (temp.getProperty().equals("limit")) {
                            iterator.remove();
                        }
                    }

                    if (symbolable && offset > 0) {
                        parameterMapping.add(new ParameterMapping.Builder(ms
                                .getConfiguration(), "offset", Integer.class)
                                .build());
                    }
                    parameterMapping.add(new ParameterMapping.Builder(ms
                            .getConfiguration(), "limit", Integer.class)
                            .build());
                } else {
                    if (dialect instanceof HSQLDialect) {
                        String result = sql;
                        if (result.lastIndexOf("offset ?") > 0) {
                            result = result.replace("offset ?", "offset "
                                    + offset);
                        }
                        if (result.lastIndexOf("limit ?") > 0) {
                            result = result
                                    .replace("limit ?", "limit " + limit);
                        }
                        return result;

                    }
                }
            }
            return sql;
        }

        public Object plugin(Object target) {
            return Plugin.wrap(target, this);
        }

        public void setProperties(Properties properties) {
        }

        public void setDialect(Dialect dialect) {
            this.dialect = dialect;
        }

        private class BoundSqlSqlSource implements SqlSource {
            private BoundSql boundSql;

            public BoundSqlSqlSource(BoundSql boundSql) {
                this.boundSql = boundSql;
            }

            public BoundSql getBoundSql(Object parameterObject) {
                return boundSql;
            }
        }
    }

    public void executeBatch(DataHandler dataHandler) {
        executeDataBatch(dataHandler);
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    private void executeDataBatch(DataHandler dataHandler) {
        for (Map<String, Object> handler : dataHandler.getHandler()) {
            if (handler != null) {
                if (handler.containsKey("handlerName")) {
                    String handlerName = (String) handler.get("handlerName");
                    Object parameter = handler.get("parameter");
                    if ("insertData".equals(handlerName)) {
                        if (parameter != null && parameter instanceof List) {
                            for (Object result : (List) parameter) {
                                dataService.insert(
                                        (String) handler.get("handleDataName"),
                                        result);
                            }
                        } else {
                            dataService.insert(
                                    (String) handler.get("handleDataName"),
                                    parameter);
                        }
                    } else {
                        if ("updateData".equals(handlerName)) {
                            if (parameter != null && parameter instanceof List) {
                                for (Object result : (List) parameter) {
                                    dataService.update((String) handler
                                            .get("handleDataName"), result);
                                }
                            } else {
                                dataService.update(
                                        (String) handler.get("handleDataName"),
                                        handler.get("parameter"));
                            }
                        } else {
                            if ("deleteData".equals(handlerName)) {
                                if (parameter != null
                                        && parameter instanceof List) {
                                    for (Object result : (List) parameter) {
                                        dataService.delete((String) handler
                                                .get("handleDataName"), result);
                                    }
                                } else {
                                    dataService.delete((String) handler
                                            .get("handleDataName"), handler
                                            .get("parameter"));
                                }
                            }
                        }
                    }
                } else {
//					try {
//						DataHandler componentDataHandler = createDataHandler(dataHandler
//								.getDataParameter());
//						((ComponentHandler<DataHandler>) handler
//								.get("componentHandler"))
//								.handleComponent(componentDataHandler);
//						executeDataBatch(componentDataHandler);
//					} catch (Exception exception) {
//						exception.printStackTrace();
//						throw new CoreException();
//					}
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public void executeBatch(String batchName, List parameter) {
        executeDataBatch(batchName, createParameter(parameter));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void executeDataBatch(String batchName, List parameter) {
        List list = createParameter(parameter);
        String[] batchNames = null;
        if (DataTool.validateNotEmpty(batchName)) {
            batchNames = batchName.split(",");
        }
        if (batchNames != null) {
            for (int lr = 0, count = list.size(); lr < count; lr = lr + 1) {
                for (int l = 0, batchCount = batchNames.length; l < batchCount; l = l + 1) {
                    String result = batchNames[l];
                    if (result.indexOf(".insert") > 0) {
                        Map map = (Map) list.get(lr);
//						String dataID = DataTool.getRandomKey() + "";
//						map.put(idName, dataID);
//						map.put(idName + l, dataID);
//						if (lr == 0 && map.containsKey("mainID") == false) {
//							map.put("mainID", dataID);
//						}
                        dataService.insert(result, map);
                    } else {
                        if (batchName.indexOf(".update") > 0) {
                            dataService.update(result, list.get(lr));
                        } else {
                            if (result.indexOf(".delete") > 0
                                    || result.indexOf(".batchDelete") > 0) {
                                dataService.delete(result, list.get(lr));
                            } else {
                                dataService.insert(result, list.get(lr));
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private List createParameter(List parameter) {
        if (parameter.size() > 0 && parameter.get(0) instanceof Map) {
            return parameter;
        } else {
            List list = new ArrayList();
            for (Object result : parameter) {
                list.add(DataTool.convertToMap(result));
            }
            return list;
        }
    }


    public DataHandler createDataHandler(Object parameter) {
        if (parameter == null) {
            return new DataHandlerImpl(new HashMap<String, Object>());
        } else {
            return new DataHandlerImpl(DataTool.convertToMap(parameter));
        }
    }

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public SqlSessionTemplate getDataService() {
        return dataService;
    }

    public void setDataService(SqlSessionTemplate dataService) {
        this.dataService = dataService;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}

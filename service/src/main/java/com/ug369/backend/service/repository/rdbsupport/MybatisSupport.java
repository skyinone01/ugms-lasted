package com.ug369.backend.service.repository.rdbsupport;

import com.ug369.backend.service.repository.rdbsupport.handler.DataHandler;
import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.result.PagedResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Roy on 2017/3/30.
 * 功能描述：提供mysql数据的操作，使用mybatis封装的通用接口支持</p>
 * <ul>
 * <li>1. sql语句定义在resources目录下的mybatis目录。</li>
 * <li>2. 查询返回接口可使用entity类也可以自定义bean类。</li>
 * </ul>
 */

public interface MybatisSupport {

    /**
     * 功能描述：通过sql和参数查询实体列表
     *
     * @param getData   定义sql语句文件的命名空间 + sql ID，使用"."连接
     * @param parameter 参数实体
     * @param <T>       期望返回结果集的实体类
     * @return 实体列表
     */
    <T> List<T> getData(String getData, Object parameter);

    /**
     * 功能描述：通过sql和参数查询实体键值对结果集
     *
     * @param getData   定义sql语句文件的命名空间 + sql ID，使用"."连接
     * @param name      期望返回结果集的key
     * @param parameter 参数实体
     * @param <T>       期望返回结果集的key类型
     * @param <V>       期望返回结果集的value类型
     * @return
     */
    <T, V> Map<T, V> getData(String getData, String name, Object parameter);

    /**
     * @param getObject 定义sql语句文件的命名空间 + sql ID，使用"."连接
     * @param parameter 参数实体
     * @param <T>
     * @return
     */
    <T> T getObject(String getObject, Object parameter);

    /**
     * 功能描述：使用sqlmap定义的sql语句查询map结果集
     *
     * @param getMap    定义sql语句文件的命名空间 + sql ID，使用"."连接
     * @param parameter
     */
    <T> Map<String, T> getMap(String getMap, Object parameter);

    /**
     * 功能描述：使用sqlmap定义的sql语句进行插入操作
     *
     * @param insertData 定义sql语句文件的命名空间 + sql ID，使用"."连接
     * @param parameter
     */
    void insertData(String insertData, Object parameter);

    /**
     * 功能描述：使用sqlmap定义的sql语句进行更新操作
     *
     * @param updateData 定义sql语句文件的命名空间 + sql ID，使用"."连接
     * @param parameter
     */
    void updateData(String updateData, Object parameter);

    /**
     * 功能描述：使用sqlmap定义的sql语句进行删除操作
     *
     * @param deleteData 定义sql语句文件的命名空间 + sql ID，使用"."连接
     * @param parameter
     */
    void deleteData(String deleteData, Object parameter);

    /**
     * 功能描述： 查询分页结果集
     *
     * @param <T>
     * @param getData      定义sql语句文件的命名空间 + sql ID，使用"."连接
     * @param getDataCount 定义sql语句文件的命名空间 + sql ID，使用"."连接
     * @param parameter
     * @param pageRequest
     * @return
     */
    <T> PagedResult<T> getDataPageBatch(String getData, String getDataCount,
                                        Object parameter, PageRequest pageRequest);

    /**
     * 功能描述： 执行批量操作，与createDataHandler()配合使用
     *
     * @param handler 是createDataHandler()方法创建返回的句柄
     */
    void executeBatch(DataHandler handler);

    /**
     * 功能描述： 创建批量操作句柄，与executeBatch()方法配合使用。
     *
     * @param parameter 数据库操作参数
     * @return 批量操作句柄
     */
    DataHandler createDataHandler(Object parameter);
}

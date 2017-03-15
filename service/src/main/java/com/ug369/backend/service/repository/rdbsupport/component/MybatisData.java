package com.ug369.backend.service.repository.rdbsupport.component;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.repository.rdbsupport.handler.DataHandler;

import java.util.List;
import java.util.Map;

/**
 * 数据库操作mybatis支持接口类
 * Created by Roy on 2017/3/29.
 */
public interface MybatisData {

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

    <T> Map<String, T> getMap(String getMap, Object parameter);

    void insertData(String insertData, Object parameter);

    void updateData(String updateData, Object parameter);

    void deleteData(String deleteData, Object parameter);

    <T> PagedResult<T> getDataPageBatch(String getData, String getDataCount,
                                        Object parameter, PageRequest pageRequest);

    void executeBatch(DataHandler handler);

    DataHandler createDataHandler(Object parameter);

}
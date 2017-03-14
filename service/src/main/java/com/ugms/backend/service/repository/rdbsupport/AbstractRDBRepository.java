package com.ugms.backend.service.repository.rdbsupport;

import com.ugms.backend.bean.base.request.PageRequest;
import com.ugms.backend.bean.result.PagedResult;
import com.ugms.backend.service.repository.rdbsupport.component.MybatisData;
import com.ugms.backend.service.repository.rdbsupport.handler.DataHandler;
import com.ugms.backend.utils.ContextUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Roy on 2017/3/29.
 */
public class AbstractRDBRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements RDBRepository<T, ID> {


    private final EntityManager entityManager;

    private JpaEntityInformation entityInformation;

    public AbstractRDBRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {

        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public <T> List<T> getData(String getData, Object parameter) {
        return getMybatisData().getData(getData, parameter);
    }

    @Override
    public <T, V> Map<T, V> getData(String getData, String name, Object parameter) {
        return getMybatisData().getData(getData, name, parameter);
    }

    @Override
    public <T> T getObject(String getObject, Object parameter) {
        return getMybatisData().getObject(getObject, parameter);
    }

    @Override
    public <T> Map<String, T> getMap(String getMap, Object parameter) {
        return getMybatisData().getMap(getMap, parameter);
    }

    @Override
    public void insertData(String insertData, Object parameter) {
        getMybatisData().insertData(insertData, parameter);
    }

    @Override
    public void updateData(String updateData, Object parameter) {
        getMybatisData().updateData(updateData, parameter);
    }

    @Override
    public void deleteData(String deleteData, Object parameter) {
        getMybatisData().deleteData(deleteData, parameter);
    }

    @Override
    public <T> PagedResult<T> getDataPageBatch(String getData, String getDataCount, Object parameter, PageRequest
            pageRequest) {
        return getMybatisData().getDataPageBatch(getData, getDataCount, parameter, pageRequest);
    }

    @Override
    public void executeBatch(DataHandler handler) {
        getMybatisData().executeBatch(handler);
    }

    @Override
    public DataHandler createDataHandler(Object parameter) {
        return getMybatisData().createDataHandler(parameter);
    }

    private MybatisData getMybatisData() {
        return (MybatisData) ContextUtils.getApplicationContext().getBean("mybatisData");
    }

}

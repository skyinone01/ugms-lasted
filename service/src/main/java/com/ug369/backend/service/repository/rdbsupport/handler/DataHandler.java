package com.ug369.backend.service.repository.rdbsupport.handler;

import java.util.List;
import java.util.Map;

public interface DataHandler {

    DataHandler addInsert(String name, String insertData, Object parameter);

    DataHandler addUpdate(String name, String updateData, Object parameter);

    DataHandler addDelete(String name, String deleteData, Object parameter);

    DataHandler addInsert(String name, String insertData);

    DataHandler addUpdate(String name, String updateData);

    DataHandler addDelete(String name, String deleteData);

    Object getDataParameter();

    List<Map<String, Object>> getHandler();

    Object getDataView(String name);

    void setDataView(String name, Object dataView);
}

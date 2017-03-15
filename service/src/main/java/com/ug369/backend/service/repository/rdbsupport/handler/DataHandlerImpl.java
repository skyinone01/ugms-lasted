package com.ug369.backend.service.repository.rdbsupport.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */

public class DataHandlerImpl implements DataHandler {
    private Object dataParameter = new HashMap<String, Object>();
    private List<Map<String, Object>> handler = new ArrayList<Map<String, Object>>();
    private Map<String, Object> dataView = new HashMap<String, Object>();

    public DataHandlerImpl(Object parameter) {
        dataParameter = parameter;
    }

    public DataHandler addInsert(String name, String insertData,
                                 Object parameter) {
        addHandler(name, "insertData", insertData, parameter);
        return this;
    }

    public DataHandler addUpdate(String name, String updateData,
                                 Object parameter) {
        addHandler(name, "updateData", updateData, parameter);
        return this;
    }

    public DataHandler addDelete(String name, String deleteData,
                                 Object parameter) {
        addHandler(name, "deleteData", deleteData, parameter);
        return this;
    }

    public DataHandler addInsert(String name, String insertData) {
        addHandler(name, "insertData", insertData);
        return this;
    }

    public DataHandler addUpdate(String name, String updateData) {
        addHandler(name, "updateData", updateData);
        return this;
    }

    public DataHandler addDelete(String name, String deleteData) {
        addHandler(name, "deleteData", deleteData);
        return this;
    }

    private void addHandler(String name, String handlerName,
                            String handleDataName, Object parameter) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("handlerName", handlerName);
        map.put("handleDataName", handleDataName);
        if (parameter == null) {
            map.put("parameter", dataParameter);
        } else {
            map.put("parameter", parameter);
        }
        handler.add(map);
    }

    private void addHandler(String name, String handlerName,
                            String handleDataName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("handlerName", handlerName);
        map.put("handleDataName", handleDataName);
        map.put("parameter", dataParameter);
        handler.add(map);
    }

    public Object getDataParameter() {
        return dataParameter;
    }

    public List<Map<String, Object>> getHandler() {
        return handler;
    }

    public Object getDataView(String name) {
        return dataView.get(name);
    }

    public void setDataView(String name, Object dataView) {
        this.dataView.put(name, dataView);
    }
}

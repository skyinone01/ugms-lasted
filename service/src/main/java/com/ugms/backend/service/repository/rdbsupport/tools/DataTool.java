package com.ugms.backend.service.repository.rdbsupport.tools;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.*;

public final class DataTool {
    private static ResourceBundle resourceBundle = null;

    public static <T> List<T> mergeList(List<List<T>> data) {
        if (data != null) {
            int count = data.size();
            if (count > 1) {
                List<T> list = data.get(0);
                Map<T, Integer> map = countList(list);
                for (int lr = 1; lr < count; lr = lr + 1) {
                    for (T currentData : data.get(lr)) {
                        if (map.containsKey(currentData) == false) {
                            list.add(currentData);
                            map.put(currentData, 0);
                        }
                    }
                }
                return list;
            } else {
                return data.get(0);
            }
        }
        return new ArrayList<T>();
    }

    public static <T> Map<T, Integer> countList(List<T> data) {
        Map<T, Integer> map = new HashMap<T, Integer>();
        for (T currentData : data) {
            Integer count = map.get(currentData);
            if (count == null) {
                map.put(currentData, 0);
            } else {
                map.put(currentData, count + 1);
            }
        }
        return map;
    }

    @SuppressWarnings("rawtypes")
    public static Map convertToMap(Object parameter) {
        if (parameter != null) {
            if (parameter instanceof Map) {
                return (Map) parameter;
            } else {
                try {
                    return PropertyUtils.describe(parameter);
                } catch (Exception exception) {
                    System.out.println("DataTool错误：" + exception.getMessage());
                }
                return new HashMap();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getData(Object data, String propertyName) {
        Object result = null;
        try {
            result = PropertyUtils.getProperty(data, propertyName);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        if (result != null) {
            return (T) result;
        }
        return null;
    }

    public static void setData(Object data, String propertyName,
                               Object propertyData) {
        try {
            PropertyUtils.setProperty(data, propertyName, propertyData);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> T getData(Map data, String name) {
        if (data != null && data.containsKey(name)) {
            return (T) data.get(name);
        }
        return null;
    }

    public static void setData(Object data,
                               Map<String, ? extends Object> parameter) {
        if (data != null && parameter != null) {
            try {
                BeanUtils.populate(data, parameter);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static <T, V> Map<T, List<V>> mergeListByProperty(List<V> data,
                                                             String propertyName) {
        Map<T, List<V>> map = new HashMap<T, List<V>>();
        for (V currentData : data) {
            T name = getData(currentData, propertyName);
            if (map.containsKey(name)) {
                map.get(name).add(currentData);
            } else {
                List<V> list = new ArrayList<V>();
                list.add(currentData);
                map.put(name, list);
            }
        }
        return map;
    }

    public static boolean validateNotEmpty(String... data) {
        for (String currentData : data) {
            if (currentData == null || currentData.equals("")) {
                return false;
            }
        }
        return true;
    }

}
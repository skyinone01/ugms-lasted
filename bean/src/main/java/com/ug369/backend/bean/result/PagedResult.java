package com.ug369.backend.bean.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据库查询分页结果集
 * Created by Roy on 2017/3/8.
 */
public class PagedResult<T> {

    @JsonProperty("total_count")
    private long totalCount;
    private List<T> items;

    public PagedResult(List<T> list, long totalCount) {
        this.items = list;
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

	public PagedResult castTo(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		List list = new LinkedList();
		if (items.size() > 0) {
			Method ofMethod = clazz.getMethod("of", items.get(0).getClass());
			for (T item : items) {
				list.add(ofMethod.invoke(null, item));
			}
		}
		return new PagedResult(list, getTotalCount());
	}
}

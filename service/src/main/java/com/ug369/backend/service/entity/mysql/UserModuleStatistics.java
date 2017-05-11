package com.ug369.backend.service.entity.mysql;

import javax.persistence.Table;

@Table(name = "yg_sys_uservisitlog")
public class UserModuleStatistics {
    private String type;

    private Integer count;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

	@Override
	public String toString() {
		return "UserModuleStatistics [type=" + type + ", count=" + count + "]";
	}
    
}

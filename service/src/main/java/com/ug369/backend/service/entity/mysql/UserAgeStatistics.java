package com.ug369.backend.service.entity.mysql;

import javax.persistence.Table;

@Table(name = "yg_sys_uservisitlog")
public class UserAgeStatistics {

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

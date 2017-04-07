package com.ug369.backend.service.entity.mysql;

import javax.persistence.Table;

@Table(name = "yg_sys_uservisitlog")
public class TotalStatistics {
    private Integer type;

    private Integer count;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

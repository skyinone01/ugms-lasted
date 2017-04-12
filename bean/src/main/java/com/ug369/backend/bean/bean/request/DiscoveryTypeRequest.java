package com.ug369.backend.bean.bean.request;

/**
 * Created by Roy on 2017/4/12.
 */
public class DiscoveryTypeRequest {
    private Integer id;
    private String name;
    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}

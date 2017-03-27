package com.ug369.backend.bean.bean.response;

/**
 * Created by Administrator on 2017/3/27.
 */
public class WelcomeResponse {

    private long id;
    private String picture;
    private String content;
    private String title;
    private int orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }
}

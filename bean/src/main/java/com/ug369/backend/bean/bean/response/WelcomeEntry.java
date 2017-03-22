package com.ug369.backend.bean.bean.response;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/22.
 */
public class WelcomeEntry {

    enum STATUS{NEW,USED,STOPEUSE}
    private long id;
    private String title;
    private Date createTime;
    private STATUS satus;
    private int order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public STATUS getSatus() {
        return satus;
    }

    public void setSatus(STATUS satus) {
        this.satus = satus;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}

package com.ug369.backend.bean.bean.response;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/22.
 */
public class WelcomeEntry {

    private long id;
    private String picture;
    private String content;
    private String title;
    private int orders;
    private Date begin_date;
    private Date create_date;
    private int status;
    private int useable;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

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

    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUseable() {
        return useable;
    }

    public void setUseable(int useable) {
        this.useable = useable;
    }
}

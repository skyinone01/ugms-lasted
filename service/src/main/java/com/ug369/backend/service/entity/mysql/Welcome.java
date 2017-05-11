package com.ug369.backend.service.entity.mysql;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roy on 2017/3/27.
 */
@Entity
@Table(name = "yg_content_guide")
public class Welcome {
    private static final long serialVersionUID = 2406271872055393481L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String picture;
    @JsonProperty("title")
    private String content;
//    private String title;
    private String link;
    @JsonProperty("orders")
    private Integer a_order;
    private Integer group_id;
    private Integer timer;
    private Date begin_date;
    private Date create_date;
    private Integer status;
    private Boolean useable;
    private Boolean isgroup;
    private String applypeople;
    private String applydetail;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getA_order() {
        return a_order;
    }

    public void setA_order(Integer a_order) {
        this.a_order = a_order;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getUseable() {
        return useable;
    }

    public void setUseable(Boolean useable) {
        this.useable = useable;
    }

    public Boolean getIsgroup() {
        return isgroup;
    }

    public void setIsgroup(Boolean isgroup) {
        this.isgroup = isgroup;
    }

    public String getApplypeople() {
        return applypeople;
    }

    public void setApplypeople(String applypeople) {
        this.applypeople = applypeople;
    }

    public String getApplydetail() {
        return applydetail;
    }

    public void setApplydetail(String applydetail) {
        this.applydetail = applydetail;
    }
}

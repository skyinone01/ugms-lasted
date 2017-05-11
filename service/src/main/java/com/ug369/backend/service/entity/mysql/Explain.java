package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;

/**
 * Created by Roy on 2017/3/27.
 */
@Entity
@Table(name = "yg_sys_explain")
public class Explain {
    private static final long serialVersionUID = 2406271872055393481L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "layoutcode")
    private String layoutCode;
    private String type;
    private String title;
    private String content;
    private Boolean useable;
    private Integer status;
    private Boolean isdel;
    private String pictures;
    private String applypeople;
    private String applydetail;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLayoutCode() {
        return layoutCode;
    }

    public void setLayoutCode(String layoutCode) {
        this.layoutCode = layoutCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getUseable() {
        return useable;
    }

    public void setUseable(Boolean useable) {
        this.useable = useable;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }
}

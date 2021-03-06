package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roy on 2017/3/27.
 */
@Entity
@Table(name = "yg_sys_content")
public class Content {
    private static final long serialVersionUID = 2406271872055393481L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "createdate")
    private Date createDate;
    private String title;
    private String icon;
    private String context;
    @Column(name = "orderid")
    private Integer orderId;
    @Column(name = "ismark")
    private Boolean isMark=false;
    @Column(name = "islink")
    private Boolean isLink=false;
    @Column(name = "linkurl")
    private String linkUrl;
    @Column(name = "typeid")
    private Integer typeId;
    private Boolean isdel=false;
    private Integer status;
    private String summary;
    private String category;
    @Column(name = "typestr")
    private String typeStr;
    @Column(name = "senderid")
    private Integer senderId;
    private Boolean readed =false;
    @Column(name = "messagetype")
    private Integer messageType;
    @Column(name = "userid")
    private Integer userId;
    private Boolean issystem = false;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Boolean isMark() {
        return isMark;
    }

    public void setMark(Boolean mark) {
        isMark = mark;
    }

    public Boolean isLink() {
        return isLink;
    }

    public void setLink(Boolean link) {
        isLink = link;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Boolean isdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Boolean isReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean issystem() {
        return issystem;
    }

    public void setIssystem(Boolean issystem) {
        this.issystem = issystem;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }
}

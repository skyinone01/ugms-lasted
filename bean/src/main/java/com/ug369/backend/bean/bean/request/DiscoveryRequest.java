package com.ug369.backend.bean.bean.request;

import java.util.Date;

/**
 * Created by Roy on 2017/4/6.
 */
public class DiscoveryRequest {

    private Long id;
    private Date createDate;
    private String title;
    private String icon;
    private String context;
    private Integer orderId;
    private Boolean isMark;
    private Boolean isLink;
    private String linkUrl;
    private Integer typeId;
    private String typeStr;
    private Boolean isdel;
    private Integer status;
    private String summary;
    private String applyDetail;
    private String applyPeople;

    public String getApplyDetail() {
        return applyDetail;
    }

    public void setApplyDetail(String applyDetail) {
        this.applyDetail = applyDetail;
    }

    public String getApplyPeople() {
        return applyPeople;
    }

    public void setApplyPeople(String applyPeople) {
        this.applyPeople = applyPeople;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getMark() {
        return isMark;
    }

    public void setMark(Boolean mark) {
        isMark = mark;
    }

    public Boolean getLink() {
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

    public Boolean getIsdel() {
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

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}

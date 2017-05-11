package com.ug369.backend.bean.bean.request;

import java.util.Date;

public class LeaveMessageRequest {
	private long id;
    private Integer userId;
    private String userName;
    private String userMobile;
    private String content;
    private String iscomplaint;
    private boolean isreplay;
    private String replayContent;
    private Date createTime;
    private Date updateTime;
    private String wxOpenId;
    private String qqOpendId;
    private String deviceType;
    private String accountType;
    
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getIscomplaint() {
		return iscomplaint;
	}
	public void setIscomplaint(String iscomplaint) {
		this.iscomplaint = iscomplaint;
	}
	
	public boolean isIsreplay() {
		return isreplay;
	}
	public void setIsreplay(boolean isreplay) {
		this.isreplay = isreplay;
	}
	public String getReplayContent() {
		return replayContent;
	}
	public void setReplayContent(String replayContent) {
		this.replayContent = replayContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getWxOpenId() {
		return wxOpenId;
	}
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}
	public String getQqOpendId() {
		return qqOpendId;
	}
	public void setQqOpendId(String qqOpendId) {
		this.qqOpendId = qqOpendId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
    
}

package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by Roy on 2017/3/27.
 */
@Entity
@Table(name = "yg_sys_uservisitlog")
public class Statistics {
	
	private static final long serialVersionUID = 2406271872055393481L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createTime;
    private long userId;
    private String moduleName;
    private long moduleId;
    private long terminalType;
    private String logType;
    private Date loginDate;
    private Date quitDate;
    private String deviceToken;
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public long getModuleId() {
		return moduleId;
	}
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}
	public long getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(long terminalType) {
		this.terminalType = terminalType;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Date getQuitDate() {
		return quitDate;
	}
	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

}

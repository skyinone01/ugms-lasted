package com.ug369.backend.service.entity.mysql;


import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "yg_sys_userleavemessage")
public class LeaveMessage {
    private static final long serialVersionUID = 2406271872055393481L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer userId;
    private String content;
    private String iscomplaint;
    private String isreplay;
    private String replayContent;
    private Date createTime;
    private Date updateTime;
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
	public String getIsreplay() {
		return isreplay;
	}
	public void setIsreplay(String isreplay) {
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

}

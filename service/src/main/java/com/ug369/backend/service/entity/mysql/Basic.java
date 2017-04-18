package com.ug369.backend.service.entity.mysql;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "yg_user_info")
public class Basic {
    private static final long serialVersionUID = 2406271872055393481L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String  wxPublicOpenId;
    private String  qqOpendId;
    private String  wxOpenId;
    private String  userName;
    private String  userPwd;
    private Long    addTime;
    private Date    userCreateTime;
    private String  userMobile;
    private String useable;
    private String ispay;

    private Integer userSex;
    private String  userBirthday;
    private String  imgUrl;
    private Integer labelId;
    private Integer userStatus;
    private String  userQuestion;
    private String  userAnswer;
    private String  userAndroid;
    private String  userIosid;
    private String  userCity;
    private Integer userLevel;
    private String deviceType;
    private String  modelNumber;
    private String  resolution;

    private Integer healthId;
    private Integer userHeight;
    private String userWeight;
    private Integer userSleep;
    private Integer userExperience;
    private Long    createTime;

    private Double longitude;
    private Double latitude;
    private String androidPushId;
    private String iosPushId;
    private String isOffLine;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getWxPublicOpenId() {
		return wxPublicOpenId;
	}
	public void setWxPublicOpenId(String wxPublicOpenId) {
		this.wxPublicOpenId = wxPublicOpenId;
	}
	public String getQqOpendId() {
		return qqOpendId;
	}
	public void setQqOpendId(String qqOpendId) {
		this.qqOpendId = qqOpendId;
	}
	public String getWxOpenId() {
		return wxOpenId;
	}
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public Long getAddTime() {
		return addTime;
	}
	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
	public Date getUserCreateTime() {
		return userCreateTime;
	}
	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUseable() {
		return useable;
	}
	public void setUseable(String useable) {
		this.useable = useable;
	}
	public String getIspay() {
		return ispay;
	}
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getLabelId() {
		return labelId;
	}
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserQuestion() {
		return userQuestion;
	}
	public void setUserQuestion(String userQuestion) {
		this.userQuestion = userQuestion;
	}
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public String getUserAndroid() {
		return userAndroid;
	}
	public void setUserAndroid(String userAndroid) {
		this.userAndroid = userAndroid;
	}
	public String getUserIosid() {
		return userIosid;
	}
	public void setUserIosid(String userIosid) {
		this.userIosid = userIosid;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public Integer getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public Integer getHealthId() {
		return healthId;
	}
	public void setHealthId(Integer healthId) {
		this.healthId = healthId;
	}
	public Integer getUserHeight() {
		return userHeight;
	}
	public void setUserHeight(Integer userHeight) {
		this.userHeight = userHeight;
	}
	public String getUserWeight() {
		return userWeight;
	}
	public void setUserWeight(String userWeight) {
		this.userWeight = userWeight;
	}
	public Integer getUserSleep() {
		return userSleep;
	}
	public void setUserSleep(Integer userSleep) {
		this.userSleep = userSleep;
	}
	public Integer getUserExperience() {
		return userExperience;
	}
	public void setUserExperience(Integer userExperience) {
		this.userExperience = userExperience;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getAndroidPushId() {
		return androidPushId;
	}
	public void setAndroidPushId(String androidPushId) {
		this.androidPushId = androidPushId;
	}
	public String getIosPushId() {
		return iosPushId;
	}
	public void setIosPushId(String iosPushId) {
		this.iosPushId = iosPushId;
	}
	public String getIsOffLine() {
		return isOffLine;
	}
	public void setIsOffLine(String isOffLine) {
		this.isOffLine = isOffLine;
	}
	
}

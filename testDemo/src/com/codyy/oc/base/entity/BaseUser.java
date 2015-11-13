package com.codyy.oc.base.entity;

import java.util.Date;

public class BaseUser {
    private String baseUserId;

    private String username;

    private String password;

    private String realName;

    private String phoneNum;

    private String headPic;

    private String accountId;

    private String email;

    private String sex;

    private String birthDate;

    private String userType;

    private String state;

    private Date createTime;

    private String createBaseUserId;

    private String rAccountid;

    private String changeInitPsd;

    public String getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBaseUserId() {
        return createBaseUserId;
    }

    public void setCreateBaseUserId(String createBaseUserId) {
        this.createBaseUserId = createBaseUserId;
    }

    public String getrAccountid() {
        return rAccountid;
    }

    public void setrAccountid(String rAccountid) {
        this.rAccountid = rAccountid;
    }

    public String getChangeInitPsd() {
        return changeInitPsd;
    }

    public void setChangeInitPsd(String changeInitPsd) {
        this.changeInitPsd = changeInitPsd;
    }
}
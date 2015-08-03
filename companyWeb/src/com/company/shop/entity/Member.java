package com.company.shop.entity;

import java.util.Date;

public class Member {
    private Integer memberId;

    private Integer storeId;

    private String memberNum;

    private String tel;

    private String pwd;

    private String truename;

    private Date birth;

    private Integer sex;

    private Date regTime;

    private String email;

    private Date lastTime;

    private Integer lastStoreId;

    private String lastStoreName;

    private Integer allAmount;

    private Integer allOrder;

    private Integer scoreAmount;

    private Integer scoreBalance;

    private Integer state;

    private String note;

    private Integer activateState;

    private Date activateTime;

    private Integer deleteState;

    private Integer vipRuleId;

    private String addr;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getLastStoreId() {
        return lastStoreId;
    }

    public void setLastStoreId(Integer lastStoreId) {
        this.lastStoreId = lastStoreId;
    }

    public String getLastStoreName() {
        return lastStoreName;
    }

    public void setLastStoreName(String lastStoreName) {
        this.lastStoreName = lastStoreName;
    }

    public Integer getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(Integer allAmount) {
        this.allAmount = allAmount;
    }

    public Integer getAllOrder() {
        return allOrder;
    }

    public void setAllOrder(Integer allOrder) {
        this.allOrder = allOrder;
    }

    public Integer getScoreAmount() {
        return scoreAmount;
    }

    public void setScoreAmount(Integer scoreAmount) {
        this.scoreAmount = scoreAmount;
    }

    public Integer getScoreBalance() {
        return scoreBalance;
    }

    public void setScoreBalance(Integer scoreBalance) {
        this.scoreBalance = scoreBalance;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getActivateState() {
        return activateState;
    }

    public void setActivateState(Integer activateState) {
        this.activateState = activateState;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }

    public Integer getVipRuleId() {
        return vipRuleId;
    }

    public void setVipRuleId(Integer vipRuleId) {
        this.vipRuleId = vipRuleId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
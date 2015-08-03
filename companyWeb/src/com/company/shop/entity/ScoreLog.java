package com.company.shop.entity;

import java.util.Date;

public class ScoreLog {
    private Integer scoreLogId;

    private Integer actionType;

    private Integer score;

    private Integer nowScore;

    private Integer scoreRuleId;

    private Integer memberId;

    private Integer bizType;

    private Integer bizId;

    private String bizName;

    private Integer creatorId;

    private Date createTime;

    private Integer state;

    private String note;

    public Integer getScoreLogId() {
        return scoreLogId;
    }

    public void setScoreLogId(Integer scoreLogId) {
        this.scoreLogId = scoreLogId;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getNowScore() {
        return nowScore;
    }

    public void setNowScore(Integer nowScore) {
        this.nowScore = nowScore;
    }

    public Integer getScoreRuleId() {
        return scoreRuleId;
    }

    public void setScoreRuleId(Integer scoreRuleId) {
        this.scoreRuleId = scoreRuleId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
package com.company.shop.entity;

public class Messagesend {
    private Integer messageSendId;

    private Integer stationMsgId;

    private Integer objectType;

    private Integer objectId;

    private Integer state;

    private String downloadNum;

    private String checkNum;
    
    private Stationmsg stationmsg;
    
    public Stationmsg getStationmsg() {
      return stationmsg;
    }

    public void setStationmsg(Stationmsg stationmsg) {
      this.stationmsg = stationmsg;
    }

    public Messagesend() {
    }

    public Integer getMessageSendId() {
        return messageSendId;
    }

    public void setMessageSendId(Integer messageSendId) {
        this.messageSendId = messageSendId;
    }

    public Integer getStationMsgId() {
        return stationMsgId;
    }

    public void setStationMsgId(Integer stationMsgId) {
        this.stationMsgId = stationMsgId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(String downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }
}
package com.company.shop.entity;

import java.util.Date;

public class ArticleImg {
    private Integer articleImgId;

    private String imgUrl;

    private Integer articleId;

    private Integer clickCnt;

    private Integer rankNum;

    private Integer opId;

    private Date createTime;

    private Integer state;

    private Integer objectType;

    private Integer objectId;

    private String content;
    
    private StoreExtend storeExtend; 

    public StoreExtend getStoreExtend() {
      return storeExtend;
    }

    public void setStoreExtend(StoreExtend storeExtend) {
      this.storeExtend = storeExtend;
    }

    public Integer getArticleImgId() {
        return articleImgId;
    }

    public void setArticleImgId(Integer articleImgId) {
        this.articleImgId = articleImgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getClickCnt() {
        return clickCnt;
    }

    public void setClickCnt(Integer clickCnt) {
        this.clickCnt = clickCnt;
    }

    public Integer getRankNum() {
        return rankNum;
    }

    public void setRankNum(Integer rankNum) {
        this.rankNum = rankNum;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
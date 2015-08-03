package com.company.shop.entity.vo;

import java.util.Date;

public class ArticleVO {
    private Integer articleId;

    private Integer articleSortId;

    private String title;

    private Integer rankNum;
    
    private String rootName;

    private Integer clickCnt;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Integer opId;

    private Integer state;

    private String imgUrl;

    private String note;

    private String content;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleSortId() {
        return articleSortId;
    }

    public void setArticleSortId(Integer articleSortId) {
        this.articleSortId = articleSortId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRankNum() {
        return rankNum;
    }

    public void setRankNum(Integer rankNum) {
        this.rankNum = rankNum;
    }

    public Integer getClickCnt() {
        return clickCnt;
    }

    public void setClickCnt(Integer clickCnt) {
        this.clickCnt = clickCnt;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the rootName
     */
    public String getRootName() {
      return rootName;
    }

    /**
     * @param rootName the rootName to set
     */
    public void setRootName(String rootName) {
      this.rootName = rootName;
    }
}
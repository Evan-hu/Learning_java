/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pager.Page;
import com.company.shop.dao.ArticleMapper;
import com.company.shop.entity.Article;

/**
 * 类描述
 * @author zyq
 * @create_time 2014-7-14 下午7:19:25
 * @project companyWeb
 */

@Service
public class ArticleService {

  @Autowired
  ArticleMapper articleMapper;
  
  /**
   * 查询文章列表
   * @param page
   * @return
   */

   public List<Article> queryArticleList(Page<Article> Page) {
     return articleMapper.selectArticleList(Page);
   }
   /**
   * 
   */
  public ArticleService() {
  }
  
  //根据sortId查询
   public Article queryArticleSortId(String code){
     return this.articleMapper.selectByCode(code);
   }
   
   
   /**
    * 根据文章ID查询文章
    * 
    * @param articleId
    * @return
    */
   public Article quArticle(Integer articleId){
     Article article = articleMapper.selectByPrimaryKey(articleId);
     article.setClickCnt(article.getClickCnt()+1);
     if(this.articleMapper.updateByPrimaryKey(article) > 0){
       return articleMapper.selectByPrimaryKey(articleId);
     }
       return null; 
   }
   //前一篇以及后一篇
   public Map<String, Object> queryArticleId(Integer articleId, Integer type, String code){
     Map<String, Object> params = new HashMap<String, Object>();
     params.put("articleId", articleId);
     params.put("code", code);
     if(type == 1){//大于当前Id的
       Article article = this.articleMapper.selectArticleDetailList(params);
       if(null != article){
         params.put("behindId", article.getArticleId());
         System.out.println("Id = " + article.getArticleId());
         params.put("behindtitle", article.getTitle());
       }
     } else {//小于当前Id的
       Article article = this.articleMapper.selectArticle(params);
       if(null != article){
         params.put("frontId", article.getArticleId());
         System.out.println("Id = " + article.getArticleId());
         params.put("fronttitle", article.getTitle());
       }
     }
     return params;
   }
   
   
   public List<Article> ArticleImgQuery(Page<Article> page){
     return this.articleMapper.selectAImgList(page);
   }
   
   /**
    * 查询门店需要的
    * @param page
    * @return
    */
   
   public List<Article> queryArticleImgList(Page<Article> page) {
     return articleMapper.selectArticleImgList(page);
   }
   
}

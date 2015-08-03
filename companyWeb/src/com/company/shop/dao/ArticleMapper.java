package com.company.shop.dao;

import java.util.List;
import java.util.Map;

import com.company.pager.Page;
import com.company.shop.entity.Article;
import com.company.shop.entity.ArticleSort;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);
    //query by sortId
    Article selectByCode(String code);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
    //大于当前Id
    Article selectArticleDetailList(Map<String, Object> params);
    //小于当前Id
    Article selectArticle(Map<String, Object> params);
    //new list
    List<Article> selectArticleList(Page<Article> page);
    //AImgList
    List<Article> selectAImgList(Page<Article> page);
    
    List<Article> selectArticleImgList(Page<Article> page);
    /**查询分类
     * @return
     */
    List<ArticleSort> selectRoot();
}
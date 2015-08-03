package com.company.shop.dao;

import com.company.shop.entity.ArticleSort;

public interface ArticleSortMapper {
    int deleteByPrimaryKey(Integer articleSortId);

    int insert(ArticleSort record);

    int insertSelective(ArticleSort record);

    ArticleSort selectByPrimaryKey(Integer articleSortId);

    int updateByPrimaryKeySelective(ArticleSort record);

    int updateByPrimaryKey(ArticleSort record);
}
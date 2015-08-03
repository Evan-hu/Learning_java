/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pager.Page;
import com.company.shop.dao.ArticleImgMapper;
import com.company.shop.entity.ArticleImg;


@Service
public class ArticleImgService {

  @Autowired
  ArticleImgMapper articleImgMapper;
  

   public List<ArticleImg> queryArticleImgList(Page<ArticleImg> page) {
     List<ArticleImg> storePic = articleImgMapper.selectArticleImgs(page);
     System.out.println("size = " + storePic.size());
     return storePic;
   }
   
   public List<ArticleImg> queryArticleImgs(Page<ArticleImg> page){
     return this.articleImgMapper.selectAImgDetail(page);
   }
   
  
}

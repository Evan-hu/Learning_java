/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.action.web;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.pager.Page;
import com.company.shop.action.BaseController;
import com.company.shop.entity.Article;
import com.company.shop.entity.ArticleImg;
import com.company.shop.service.ArticleImgService;
import com.company.shop.service.ArticleService;

/**
 * 类描述
 * @author zyq
 * @create_time 2014-7-14 下午7:23:31
 * @project companyWeb
 */
@Controller 
@RequestMapping("/article")
public class ArticleController  extends BaseController {

  @Autowired
  ArticleService articleService;
  
  @Autowired
  ArticleImgService articleImgService;
  
  
  /**
   * 
   * 文字文章列表查询
   * @param modelMap
   * @param sortId
   * @param page
   * @return
   * @throws Exception
   */
  //文章列表分页
  @RequestMapping("/list.do")
  public String queryAllArticle(ModelMap modelMap,String code, Page<Article> Page) throws Exception{
    System.out.println("code = " + code);
    code = Page.optParamStr("code", code);
    String keyword = Page.optParamStr("keyword", null);  
    if(keyword != null){
          keyword = URLDecoder.decode(keyword,"utf-8");
          Page.addParam("keyword", keyword);
    }
    Page.setResults(articleService.queryArticleList(Page));
    modelMap.put("code", code);
    modelMap.put("page", Page);
    return "/news/list";
  }
  
  //文章详情分页
  @RequestMapping("/detailList.do")
  public String queryDetailList(ModelMap modelMap, String code, Integer articleId) throws Exception{
    Article article = this.articleService.quArticle(articleId);
    Map<String, Object> result = new HashMap<String, Object>();
    result = this.articleService.queryArticleId(articleId, 2, code);
    result.putAll(this.articleService.queryArticleId(articleId, 1, code));
    if(result.get("frontId") == null){
      modelMap.put("front", 0);
    } else {
      modelMap.put("front", result.get("frontId"));
    }
    modelMap.put("fronttitle", result.get("fronttitle"));
    System.out.println(result.get("frontId"));
    if(result.get("behindId") == null){
      modelMap.put("behind", 0);
    } else {
      modelMap.put("behind", result.get("behindId"));
    }
    modelMap.put("behindtitle", result.get("behindtitle"));
    modelMap.put("code", code);
    modelMap.put("article", article);
    return "/news/detail";
  }
  
  //非泊风采List
  @RequestMapping("/mienList.do")
  public String querymienList(ModelMap modelMap, Page<Article> Page) throws Exception{
    String code = "mien";
    code = Page.optParamStr("code", code);
    String keyword = Page.optParamStr("keyword", null);  
    if(keyword != null){
          keyword = URLDecoder.decode(keyword,"utf-8");
          Page.addParam("keyword", keyword);
    }
    Page.setResults(articleService.ArticleImgQuery(Page));
    modelMap.put("page", Page);
    return "/news/mienList";
  }
  
  @RequestMapping("/mienDetail.do")
  public String queryMienDetail(ModelMap modelMap, Integer articleId, Page<ArticleImg> page) throws Exception{
    articleId = page.optParamInt("articleId", articleId);
    String keyword = page.optParamStr("keyword", null);  
    if(keyword != null){
          keyword = URLDecoder.decode(keyword,"utf-8");
          page.addParam("keyword", keyword);
    }
    modelMap.put("title", this.articleService.quArticle(articleId).getTitle());
    List<ArticleImg> aList = this.articleImgService.queryArticleImgs(page);
    modelMap.put("size", aList.size());
    page.setResults(aList);
    modelMap.put("page", page);
    return "/news/mienDetail";
  }
  
 @RequestMapping("/picList.do")
 public String queryPicArticle(ModelMap modelMap,@RequestParam(value = "sortId", defaultValue = "2")Integer sortId, 
     Page<Article> page){
   return "/news/mienList";
 }
  
}

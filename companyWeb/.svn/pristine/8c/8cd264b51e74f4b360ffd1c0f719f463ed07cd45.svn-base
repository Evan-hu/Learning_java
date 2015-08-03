/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.action.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.shop.action.BaseController;
import com.company.shop.entity.Application;
import com.company.shop.entity.Article;
import com.company.shop.service.ArticleService;

/**
 * 类描述
 * @author ht
 * @create_time 2014-7-25 下午4:35:51
 * @project companyWeb
 */
@Controller
@RequestMapping("/league")
public class LeagueController extends BaseController{

  @Autowired
  ArticleService articleService;
  
  Article article = null;
  //加盟
  @RequestMapping("/add.do")
  public String addLeague(ModelMap modelMap) throws Exception{
    try {
      article = this.articleService.queryArticleSortId("join");
      if(null != article){
        modelMap.put("article", article);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "/league/addLeague";
  }
  
  //加盟流程
  @RequestMapping("/process.do")
  public String leagueProecss(ModelMap modelMap) throws Exception{
    System.out.println("test====process");
   article = this.articleService.queryArticleSortId("process");
    if(null != article){
      modelMap.put("article", article);
    }
    return "/league/process"; 
  }
  
  //加盟申请
  @RequestMapping("appl.do")
  public String addAppl(ModelMap modelMap,Application application) throws Exception{
    modelMap.put("application", application);
    return "/league/addAppl";
  }
}


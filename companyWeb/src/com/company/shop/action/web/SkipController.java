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
import com.company.shop.entity.Article;
import com.company.shop.service.ArticleService;

/**
 * ������   ������ת��Controller
 * @author ht
 * @create_time 2014-7-25 ����4:53:02
 * @project companyWeb
 */
@Controller
@RequestMapping("/skip")
public class SkipController extends BaseController{
  
  @Autowired
  ArticleService articleService;
  
  //��ϵ
  @RequestMapping("/contact.do")
  public String contact() throws Exception{
    return "/contact";
  }
  
  //��˾������
  @RequestMapping("/companyInfo.do")
  public String skipCompanyInfo(ModelMap modelMap,String code) throws Exception{
    System.out.println(code);
    if(code.equals("organiz")){// ��֯�ܹ�
      modelMap.put("article", this.articleService.queryArticleSortId(code));
      return "/company/fableOrgan";
    } else if(code.equals("about")) {//���ڷǲ�
      modelMap.put("article", this.articleService.queryArticleSortId(code));
      return "/company/aboutFable";
    }// �ܾ����´�
    modelMap.put("article", this.articleService.queryArticleSortId("manager"));
    return "/company/companyInfo";
  }
  
  //ְλ����
  @RequestMapping("/appl.do")
  public String skipAppl(ModelMap modelMap) throws Exception{
    modelMap.addAttribute("msg", "");
    return "/recruiment/applRecriment";
  }
  
  //�����ļ����Ӧ�̣�
  @RequestMapping("/provider.do")
   public String skipProvider() throws Exception{
    return "/provider/providerApp";
  }
  
  
}

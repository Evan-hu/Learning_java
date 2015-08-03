/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.action.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.shop.action.BaseController;
import com.company.shop.entity.Application;
import com.company.shop.service.ApplicationService;

/**
 * ¿‡√Ë ˆ
 * @author ht
 * @create_time 2014-7-18 œ¬ŒÁ3:16:53
 * @project companyWeb
 */
@Controller
@RequestMapping("/appl")
public class ApplicationController extends BaseController{
  
  @Autowired
  ApplicationService applService;
  
  @RequestMapping(value="/Appl.do", method=RequestMethod.POST)
  public String addAppl(HttpServletRequest request,ModelMap modelMap, Application application) throws Exception{
    String msg = "";
    if(this.applService.addAppl(application)){
      msg = "…Í«Î≥…π¶";
    } else {
      msg = "…Í«Î ß∞‹";
    }
    modelMap.addAttribute("msg", msg);
    return "/league/addAppl";
  }
  
  
  
}

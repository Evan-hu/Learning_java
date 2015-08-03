/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.action.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.shop.action.BaseController;
import com.company.shop.entity.Ads;
import com.company.shop.service.AdService;

/**
 * ¿‡√Ë ˆ
 * @author ht
 * @create_time 2014-7-25 œ¬ŒÁ1:39:57
 * @project companyWeb
 */
@Controller
public class IndexController extends BaseController{
  @Autowired
  private AdService adService;
  
  @RequestMapping(value={"/","/index.do"})
  public String index(ModelMap modelMap){
    modelMap.put("adList", adService.loadAd());
    return "/index";
  }

  public AdService getAdService() {
    return adService;
  }

  public void setAdService(AdService adService) {
    this.adService = adService;
  }
}

/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.action.web;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.pager.Page;
import com.company.shop.action.BaseController;
import com.company.shop.entity.ArticleImg;
import com.company.shop.entity.Counselor;
import com.company.shop.entity.StoreExtend;
import com.company.shop.service.ArticleImgService;
import com.company.shop.service.ArticleService;
import com.company.shop.service.StoreService;

/**
 * 类描述
 * @author ht
 * @create_time 2014-7-21 上午11:38:47
 * @project companyWeb
 */
@Controller 
@RequestMapping("/store")
public class StoreController extends BaseController{

  @Autowired
  ArticleService articleService;
  @Autowired
  ArticleImgService articleImgService;
  @Autowired
  StoreService storeService;
  
  
  
  @RequestMapping("/queryStore.do")//所有门店
  public String viewStore(ModelMap modelMap, Page<StoreExtend> page) throws Exception{
    String keyword = page.optParamStr("keyword", null);
    if(null != keyword){
      keyword = URLDecoder.decode(keyword, "utf-8");
      page.addParam("keyword", keyword);
    }
      page.setResults(storeService.queryAllStore(page));
      modelMap.put("page", page);
    return "/store/storeView";
  }
  
   @RequestMapping("/detail.do")//门店详情
   public String storeDetail(ModelMap modelMap, Integer storeId) throws Exception{
     StoreExtend storeExtend = this.storeService.queryStore(storeId);
     if(null != storeExtend){
      modelMap.put("storeExtend", storeExtend);
      return "/store/storeDetail";
    }
    return "/store/storeView";
  }
   
   @RequestMapping("/queryStorePic.do")//门店掠影
   public String viewStorePic(ModelMap modelMap, Page<ArticleImg> page,Integer storeId) throws Exception{
     storeId = page.optParamInt("storeId", storeId);
     String keyword = page.optParamStr("keyword", null);
     if(null != keyword){
       keyword = URLDecoder.decode(keyword, "utf-8");
       page.addParam("keyword", keyword);
     }  
       List<ArticleImg> aList = this.articleImgService.queryArticleImgList(page);
       page.setResults(aList);
       modelMap.put("storeId", storeId);
       modelMap.put("size", aList.size());
       modelMap.put("storeName", this.storeService.queryStore(storeId).getStoreName() + "门店掠影");
       modelMap.put("page", page);
     return "/store/storePic";
   }
   
   
   @RequestMapping("/viewAllCounselor.do")//所有顾问
   public String viewAllCounselor(ModelMap modelMap, Integer storeId, Page<Counselor> page) throws Exception{
     page.setResults(this.storeService.viewAllCounselor(page));
     modelMap.put("page", page);
     return "/store/allCounselor";
   }
   
   @RequestMapping("/viewStoreCounselor.do")//门店对应的顾问
   public String viewCounselorByStoreId(ModelMap modelMap,Integer storeId, Page<Counselor> page) throws Exception{
     String keyword = page.optParamStr("keyword", null);
     storeId = page.optParamInt("storeId", storeId);
     if(null != keyword){
       keyword = URLDecoder.decode(keyword, "utf-8");
       page.addParam("keyword", keyword);
     }
     List<Counselor> cList = this.storeService.viewCounselorByStoreId(page);
     modelMap.put("storeExtend", this.storeService.queryStore(storeId));
     System.out.println("size = " + cList.size());
     if(cList.size() != 0){
     page.setResults(cList);
     modelMap.put("page", page);
     return "/store/allCounselor";
     }
     modelMap.addAttribute("msg", "当前门店没有顾问!");
     return "/store/storeView";
   }
   
   @RequestMapping("/viewCounselor.do")//当前顾问详情
   public String viewCounselor(ModelMap modelMap, Integer counselorId,Page<ArticleImg> page) throws Exception{
     Counselor counselor =this.storeService.viewCounselor(counselorId);
     if(counselor != null){
       modelMap.put("counselor", counselor);
       return "/store/detailCounselor";
     }
     return "/store/allCounselor";
   }
   
}

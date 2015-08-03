/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.action.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.shop.action.BaseController;
import com.company.shop.entity.Stock;
import com.company.shop.exception.BizException;
import com.company.shop.service.StockService;

/**
 * ¿‡√Ë ˆ
 * @author zyq
 * @create_time 2014-7-11 œ¬ŒÁ1:38:07
 * @project companyWeb
 */
@Controller
@RequestMapping("/stock")
public class StockController extends BaseController  {

  
  @Autowired
  StockService stockService;
  
  
  @RequestMapping("/detail.do")
  public String queryStockDetailModelMap (ModelMap modelMap,@RequestParam("stockId") Integer stockId){
    
    Stock stock=stockService.queryStockDetail(stockId);
   if(stock==null){
     
     throw new BizException("°£°£°£");
   }
    modelMap.put("stock", stock);
   
    
    return "stock/stockDetail";
  }
 
  
  public String queryStockList(ModelMap modelMap){
    
    
    
    return "stock/stockList";
  }
}

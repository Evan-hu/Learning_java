/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.shop.dao.StockMapper;
import com.company.shop.entity.Stock;

/**
 * ¿‡√Ë ˆ
 * @author zyq
 * @create_time 2014-7-11 œ¬ŒÁ1:33:37
 * @project companyWeb
 */
@Service
public class StockService {
  
  @Autowired
  StockMapper stockMapper;

 public Stock queryStockDetail(Integer stockId){
   
   
   Stock stock=new Stock();
   stock=stockMapper.selectByPrimaryKey(stockId);
   
   return stock;
   
 }
}

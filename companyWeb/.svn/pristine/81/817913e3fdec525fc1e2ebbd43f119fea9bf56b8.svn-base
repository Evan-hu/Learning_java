/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.shop.dao.ApplicationMapper;
import com.company.shop.entity.Application;

/**
 * ÀàÃèÊö
 * @author ht
 * @create_time 2014-7-18 ÏÂÎç3:50:05
 * @project companyWeb
 */
@Service
public class ApplicationService {
  
  @Autowired
  ApplicationMapper applMapper;
  
  public boolean addAppl(Application appl){
    return this.applMapper.insert(appl) > 0 ? true : false;
  }
  
}

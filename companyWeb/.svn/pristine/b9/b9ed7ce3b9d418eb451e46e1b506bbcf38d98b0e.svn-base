/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pager.Page;
import com.company.shop.dao.CounselorMapper;
import com.company.shop.dao.StoreExtendMapper;
import com.company.shop.entity.Counselor;
import com.company.shop.entity.StoreExtend;

/**
 * 类描述
 * @author ht
 * @create_time 2014-7-21 下午4:49:26
 * @project companyWeb
 */
@Service
public class StoreService {

  @Autowired
  StoreExtendMapper storeExtendMapper;
  @Autowired
  CounselorMapper counselorMapper;
  
  
  public List<StoreExtend> queryAllStore(Page<StoreExtend> page){
    return this.storeExtendMapper.selectAllStore(page);
  }
  
  //查询确定店的详情
  public StoreExtend queryStore(Integer storeId){
    return this.storeExtendMapper.selectByStoreKey(storeId);
  }
  
  //查询当前门店的美容顾问
  public List<Counselor> viewCounselorByStoreId(Page<Counselor> page){
    return this.counselorMapper.selectByStoreId(page);
  }
  
  //查询当前美容顾问
  public Counselor viewCounselor(Integer counselorId){
    return this.counselorMapper.selectByPrimaryKey(counselorId);
  }
  
  //所有的美容顾问
  public List<Counselor> viewAllCounselor(Page<Counselor> page){
    return this.counselorMapper.selectAllCounselor(page);
  }
}

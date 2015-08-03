/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.shop.dao.AdsMapper;
import com.company.shop.entity.Ads;

/**
 * ������
 * 
 * @author jzk
 * @create_time 2014-7-31 ����4:10:42
 * @project companyWeb
 */
@Service
public class AdService {
  @Autowired
  private AdsMapper adsMapper;
  /**
   * ������ҳ�ֲ����
   * 
   * @return
   */
  public List<Ads> loadAd() {
    return this.adsMapper.loadAds();
  }

  public AdsMapper getAdsMapper() {
    return adsMapper;
  }

  public void setAdsMapper(AdsMapper adsMapper) {
    this.adsMapper = adsMapper;
  }
}

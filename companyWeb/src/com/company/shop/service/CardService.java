/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pager.Page;
import com.company.shop.dao.CardMapper;
import com.company.shop.entity.Card;

/**
 * ¿‡√Ë ˆ
 * @author ht
 * @create_time 2014-7-17 …œŒÁ11:56:16
 * @project companyWeb
 */
@Service
public class CardService {

  @Autowired
  CardMapper cardMapper;
  
//  member¿Ò»Ø≤È—Ø
  public List<Card> queryCard(Page<Card> page){
    return cardMapper.selectCardList(page);
  }
  
}

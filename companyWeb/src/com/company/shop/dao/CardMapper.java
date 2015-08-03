package com.company.shop.dao;

import java.util.List;

import com.company.pager.Page;
import com.company.shop.entity.Card;

public interface CardMapper {
    int deleteByPrimaryKey(Integer cardId);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(Integer cardId);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);
    
    //查询会员所有的 礼券
    List<Card> selectCardList(Page<Card> page);
}
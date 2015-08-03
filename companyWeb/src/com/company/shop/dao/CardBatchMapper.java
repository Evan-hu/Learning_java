package com.company.shop.dao;

import com.company.shop.entity.CardBatch;

public interface CardBatchMapper {
    int deleteByPrimaryKey(Integer cardBatchId);

    int insert(CardBatch record);

    int insertSelective(CardBatch record);

    CardBatch selectByPrimaryKey(Integer cardBatchId);

    int updateByPrimaryKeySelective(CardBatch record);

    int updateByPrimaryKey(CardBatch record);
}
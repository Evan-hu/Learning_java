package com.company.shop.dao;

import java.util.List;

import com.company.pager.Page;
import com.company.shop.entity.StoreExtend;

public interface StoreExtendMapper {
    int deleteByPrimaryKey(Integer storeExtendId);

    int insert(StoreExtend record);

    int insertSelective(StoreExtend record);

    StoreExtend selectByPrimaryKey(Integer storeExtendId);

    int updateByPrimaryKeySelective(StoreExtend record);

    int updateByPrimaryKeyWithBLOBs(StoreExtend record);

    int updateByPrimaryKey(StoreExtend record);
    
    StoreExtend selectByStoreKey(Integer storeId);
    
    List<StoreExtend> selectAllStore(Page<StoreExtend> page);
}
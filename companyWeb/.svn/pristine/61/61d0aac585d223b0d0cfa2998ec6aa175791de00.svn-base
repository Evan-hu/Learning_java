package com.company.shop.dao;

import java.util.List;

import com.company.pager.Page;
import com.company.shop.entity.Counselor;

public interface CounselorMapper {
    int deleteByPrimaryKey(Integer counselorId);

    int insert(Counselor record);

    int insertSelective(Counselor record);

    Counselor selectByPrimaryKey(Integer counselorId);
    
    List<Counselor> selectByStoreId(Page<Counselor> page);

    int updateByPrimaryKeySelective(Counselor record);

    int updateByPrimaryKey(Counselor record);
    
    List<Counselor> selectAllCounselor(Page<Counselor> page);
}
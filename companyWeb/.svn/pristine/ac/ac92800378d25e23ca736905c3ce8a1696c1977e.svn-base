package com.company.shop.dao;

import java.util.List;

import com.company.pager.Page;
import com.company.shop.entity.Recruiment;

public interface RecruimentMapper {
    int deleteByPrimaryKey(Integer recruimentId);

    int insert(Recruiment record);

    int insertSelective(Recruiment record);

    Recruiment selectByPrimaryKey(Integer recruimentId);
    
    Recruiment selectByPositionId(Integer positionId);

    int updateByPrimaryKeySelective(Recruiment record);

    int updateByPrimaryKey(Recruiment record);
    
    List<Recruiment> selectAllRecruiments(Page<Recruiment> page);
}
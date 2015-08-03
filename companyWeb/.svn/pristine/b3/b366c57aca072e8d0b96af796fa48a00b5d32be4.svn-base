package com.company.shop.dao;

import java.util.List;

import com.company.pager.Page;
import com.company.shop.entity.ScoreLog;

public interface ScoreLogMapper {
    int deleteByPrimaryKey(Integer scoreLogId);

    int insert(ScoreLog record);

    int insertSelective(ScoreLog record);

    ScoreLog selectByPrimaryKey(Integer scoreLogId);

    int updateByPrimaryKeySelective(ScoreLog record);

    int updateByPrimaryKey(ScoreLog record);
    
    ScoreLog selectByMemberId(Integer memberId);
    
    List<ScoreLog> selectAllScoreLog(Page<ScoreLog> page);
    
}
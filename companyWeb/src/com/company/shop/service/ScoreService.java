/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pager.Page;
import com.company.shop.dao.ScoreLogMapper;
import com.company.shop.entity.ScoreLog;



/**
 * 类描述
 * @author ht
 * @create_time 2014-7-17 上午11:16:22
 * @project companyWeb
 */
@Service 
public class ScoreService {

  @Autowired
  ScoreLogMapper scoreLogMapper;
  
  //积分查询
  public ScoreLog queryScore(int memberid){
    ScoreLog scoreLog = scoreLogMapper.selectByMemberId(memberid);
    if(null != scoreLog){
      return scoreLog;
    }
    return null;
  }
  
  //1 增加积分；  2 消耗积分
  public List<ScoreLog> queryListScorelog(Page<ScoreLog> page, Integer type){
    List<ScoreLog> scoreLogs = this.scoreLogMapper.selectAllScoreLog(page);
    List<ScoreLog> delList = new ArrayList<ScoreLog>();
    if(1 == type){
      for(ScoreLog scoreLog : scoreLogs){
        if(scoreLog.getScore() < 0){
          System.out.println(scoreLog.getScore());
          delList.add(scoreLog);
          }
      }
    } 
    if(2 == type){
      for(ScoreLog scoreLog : scoreLogs){
        if(scoreLog.getScore() > 0){
          System.out.println(scoreLog.getScore());
          delList.add(scoreLog);
        }
      }
    }
    scoreLogs.removeAll(delList);
    return scoreLogs;
  }
}


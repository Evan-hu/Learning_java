/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pager.Page;
import com.company.shop.dao.MessagesendMapper;
import com.company.shop.dao.StationmsgMapper;
import com.company.shop.entity.Messagesend;
import com.company.shop.entity.Stationmsg;

/**
 * 类描述
 * @author ht
 * @create_time 2014-7-28 下午3:20:41
 * @project companyWeb
 */
@Service
public class MsgService {
  
  @Autowired
  MessagesendMapper messagesendMapper;
  @Autowired
  StationmsgMapper stationmsgMapper;
  
  
  //查询所有信息
  public List<Messagesend> queryAllMsg(Page<Messagesend> page) {
    return this.messagesendMapper.selectMessagesends(page);
  }
  
  public Stationmsg queryStationmsg(int sMsgId){
    return this.stationmsgMapper.selectByPrimaryKey(sMsgId);
  }

}

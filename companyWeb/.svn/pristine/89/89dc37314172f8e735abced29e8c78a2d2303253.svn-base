package com.company.shop.dao;

import java.util.List;

import com.company.pager.Page;
import com.company.shop.entity.Messagesend;

public interface MessagesendMapper {
    int deleteByPrimaryKey(Integer messageSendId);

    int insert(Messagesend record);

    int insertSelective(Messagesend record);

    Messagesend selectByPrimaryKey(Integer messageSendId);

    int updateByPrimaryKeySelective(Messagesend record);

    int updateByPrimaryKey(Messagesend record);

    List<Messagesend> selectMessagesends(Page<Messagesend> page);
}
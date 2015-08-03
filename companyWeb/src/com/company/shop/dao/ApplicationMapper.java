package com.company.shop.dao;

import com.company.shop.entity.Application;

public interface ApplicationMapper {
    int insert(Application record);

    int insertSelective(Application record);
}
/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pager.Page;
import com.company.shop.dao.RecruimentMapper;
import com.company.shop.dao.ResumeMapper;
import com.company.shop.entity.Recruiment;
import com.company.shop.entity.Resume;

/**
 * 类描述
 * @author ht
 * @create_time 2014-7-23 下午3:52:38
 * @project companyWeb
 */
@Service
public class RecruimentService {
  @Autowired
  private RecruimentMapper recruimentMapper;
  @Autowired
  private ResumeMapper resumeMapper;
  
  public List<Recruiment> queryAllRecruiment(Page<Recruiment> page){
    return this.recruimentMapper.selectAllRecruiments(page);
  }
  
  public Recruiment queryRecruiment(Integer positionId){
    return this.recruimentMapper.selectByPositionId(positionId);
  }
  
  /**
   * 保存用户职位申请
   * @param resume
   */
  public void save(Resume resume) {
    this.resumeMapper.insert(resume);
  }
}

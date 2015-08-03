/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.action.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.pager.Page;
import com.company.shop.action.BaseController;
import com.company.shop.entity.Recruiment;
import com.company.shop.entity.Resume;
import com.company.shop.service.RecruimentService;

/**
 * 类描述
 * @author ht
 * @create_time 2014-7-23 下午3:45:26
 * @project companyWeb
 */
@Controller
@RequestMapping("/recruiment")
public class RecruimentController extends BaseController{

  @Autowired
  RecruimentService recruimentService;
  
  @RequestMapping("/viewAll.do")
  public String viewAllRecruiment(ModelMap modelMap, Page<Recruiment> page) throws Exception{
    page.setResults(this.recruimentService.queryAllRecruiment(page));
    modelMap.put("page", page);
    return "/recruiment/viewAllRecriment";
  }
  
  @RequestMapping("/viewDetail.do")
  public String viewDetailRecruiment(ModelMap modelMap,Integer positionId) throws Exception{
    Recruiment recruiment = this.recruimentService.queryRecruiment(positionId);
    if(null != recruiment){
      modelMap.put("recruiment", recruiment);
      return "/recruiment/DetailRecriment";
    }
    modelMap.put("msg", "产生了一个错误");
    return "/recruiment/viewAllRecriment";
  }
  
  @RequestMapping("/applRecriment.do")
  public String applRecriment(ModelMap map, HttpServletRequest req, HttpServletResponse resp, Resume resume) throws Exception{
    recruimentService.save(resume);
    map.put("msg", "恭喜您！申请职位成功");
    return "/recruiment/applRecriment";
  }

  
  public RecruimentService getRecruimentService() {
    return recruimentService;
  }

 
  public void setRecruimentService(RecruimentService recruimentService) {
    this.recruimentService = recruimentService;
  }
}

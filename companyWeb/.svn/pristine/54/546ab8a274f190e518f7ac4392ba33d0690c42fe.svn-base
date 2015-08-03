/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.action.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.shop.action.BaseController;
import com.company.shop.entity.Stationmsg;
import com.company.shop.service.MsgService;

/**
 * 类描述
 * @author ht
 * @create_time 2014-7-28 下午3:13:09
 * @project companyWeb
 */
@Controller
@RequestMapping("/msg")
public class MsgController extends BaseController{

  @Autowired
  MsgService mService;
  
  @RequestMapping("/msgDetail.do")
  public String viewAllMsg(ModelMap modelMap, int mesgId, int memberId) throws Exception{
    Stationmsg stationmsg = this.mService.queryStationmsg(mesgId);
    if(stationmsg == null){
      modelMap.put("msg", "产生了未知错误");
      modelMap.put("memberId", memberId);
      return "/user/myMessage";
    }
    modelMap.put("sMsg", stationmsg);
    modelMap.put("memberId", memberId);
    return "/user/msgDetail";
  }
  
}

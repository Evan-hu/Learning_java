package com.company.shop.action;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.util.WebUtils;

import com.company.shop.fiter.AuthImg;
import com.company.shop.util.CommonUtil;


public class BaseController {	

	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String MESSAGE = "message";
	public static final String MESSAGE_PATH = "/message";
	public static final String REDIRECT_INDEX = "redirect:/";
	public static final int AJAX_REQUEST_SUCCESS = 1;
	public static final int AJAX_REQUEST_FAILED = 0;
	
	public Map<String, Object> createAjaxMap(int code, String msg){
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("code", code);
		map.put("message", msg);
		return map;
	}
	
	public String writerMessage(ModelMap modelMap,String msg,String jumpUrl){
	  modelMap.put("msgType", 1);
	  modelMap.put("message", msg);
	  modelMap.put("jumpUrl", jumpUrl);
	  return MESSAGE_PATH;
	}
	
	public String writerMessage(Model model,String msg,String jumpUrl){
		model.addAttribute("msgType", 1);
		model.addAttribute("message", msg);
		model.addAttribute("jumpUrl", jumpUrl);
		return MESSAGE_PATH;
	}
	
	public String writerMessage(ModelMap modelMap,String msg,int jumpIndex){	    
	    modelMap.put("msgType", 1);
	    modelMap.put("message", msg);
	    modelMap.put("jumpIndex", jumpIndex);
	    return MESSAGE_PATH;
	}
	
	public String writerMessage(Model model,String msg,int jumpIndex){    
		model.addAttribute("msgType", 1);
		model.addAttribute("message", msg);
		model.addAttribute("jumpIndex", jumpIndex);
	    return MESSAGE_PATH;
	}
	
	
	public String getRedirectUrl(HttpServletRequest request){
		String redirectUrl = (String) WebUtils.getSessionAttribute(request,CommonUtil.REDIRECT_URL);
		if(StringUtils.isNotBlank(redirectUrl)){
			return "redirect:" + redirectUrl;
		}
		return REDIRECT_INDEX;
	}
	
	public String getPicFullUrl(HttpServletRequest request, String picUrl){
		return request.getScheme()+ "://" + request.getServerName()+":" + request.getServerPort() + picUrl;	
	}
	
	public boolean isAvailableCode(String checkCode, HttpServletRequest request){
		return isAvailableCode(checkCode, request, false);
	}
	
	public boolean isAvailableCode(String checkCode, HttpServletRequest request, boolean isAjax){
		String name = isAjax ? AuthImg.AJAX_AUTH_CODE : AuthImg.AUTH_CODE;
		String authCode = (String) WebUtils.getSessionAttribute(request, name);
		boolean  b = StringUtils.equalsIgnoreCase(checkCode, authCode);
		if(b) request.getSession().removeAttribute(name);
		return b;
	}
	
}

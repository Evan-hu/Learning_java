/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.action.web;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.company.shop.exception.BizException;
import com.company.shop.util.FileUtil;

/**
 * 类描述
 * @author ht
 * @create_time 2014-7-25 上午9:56:06
 * @project companyWeb
 */
@Controller
@RequestMapping("/file")
public class UploadController{
  
  @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
  public String uploadDoc(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException{
    JSONObject  jsonMap = new JSONObject();
    if(file.isEmpty()){
    //  jsonMap.put("status", -1);
     // jsonMap.put("title", "文件为空!");
      return jsonMap.toString();
    }
    if(!file.isEmpty()){
      try {
        FileOutputStream os = new FileOutputStream("D:/" + new Date().getTime() +file.getOriginalFilename());
        InputStream in = file.getInputStream();
        int b = 0;
        while((b = in.read())!= -1){
          os.write(b);
        }
        os.flush();
        os.close();
        in.close();
       // jsonMap.put("title", file.getOriginalFilename());
        System.out.println(file.getOriginalFilename());
        System.out.println("上传成功!");
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
  
    return jsonMap.toString();
 }
  
  /**
   * 上传简历文档
   * @param req
   * @param resp
   * @param modelMap
   * @throws Exception 
   */
  @RequestMapping(value="/uploadResume.do", method=RequestMethod.POST)
  public void uploadResume(HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap) throws Exception{
    String fileName = this.upload(req, resp, "/resume", modelMap);
    PrintWriter out = resp.getWriter();
    out.println("/resume/"+fileName);
    out.close();
  }
  
  /**
   * 保存文件，返回文件名字
   * @param request
   * @param response
   * @param model
   * @throws Exception
   */
  private String upload(HttpServletRequest request, HttpServletResponse response, String direcotory, ModelMap modelMap)throws Exception{
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
    multipartResolver.setDefaultEncoding("UTF-8");
    
    if(!multipartResolver.isMultipart(request)) {
      throw new BizException("表单类型不对!");
    }
    
    MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
    Map<String, MultipartFile> fileMap = req.getFileMap();
    if (fileMap.isEmpty()) {
      throw new BizException("上传文件失败:文件列表为空!");
    }
    
    String fileName = null;
    for(MultipartFile file : fileMap.values()){
      if (file.getSize() >= 2000000) {
        throw new BizException("上传文件失败:文件内容太大");
      }
      
       fileName = new Date().getTime() + file.getOriginalFilename();
       FileUtil.saveFile(file.getInputStream(), ""  , "/" + fileName);
    } 
    
    return fileName;
  }
}

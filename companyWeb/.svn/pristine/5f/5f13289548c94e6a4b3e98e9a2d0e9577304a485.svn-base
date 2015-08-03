package com.company.shop.fiter;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.company.shop.util.CommonUtil;
import com.company.shop.util.SiteConfig;


/**
 * 图片浏览过滤�?
 * @author Administrator
 *
 */
public class Photo extends HttpServlet implements Filter {
  private static Logger logger = Logger.getLogger(Photo.class.getName());

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
  ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    try {
      String filename = req.getServletPath();    
      if(filename.startsWith("/photos/commodityDesc/")) {//商品描述
        dealCommDescPic(req, resp, filename);        
      } 
      else if(filename.startsWith("/photos/spec/")) {
        //普�?图片
        String fileFullPath = filename.replace("/photos/",  SiteConfig.getInstance().getFileRoot()+"/");
        flushPhoto(resp,req,new File(fileFullPath),"3");
      } 
      else if(filename.startsWith("/photos/member/head_")){//"/photos/member/head_1_50_50.jpg"
    	  //这是特例 以后在进行封�?
    	  String str = filename.replace("/photos/member/head_", "");
    	  str = str.substring(0,str.length()-4);
    	  String [] strArr = str.split("_");
    	  if(strArr == null || strArr.length != 3){
    		 flushPhoto(resp,req,null,"3");
    	  }
    	  else{
	        String fileFullPath = SiteConfig.getInstance().getFileRoot()+"/"
	        		+ "member" + CommonUtil.idToPathBase8(strArr[0]) + "/head_" 
	        		+ strArr[1] + "_"+ strArr[2] + ".jpg";
	        File file = new File(fileFullPath);
	        if (file == null || !file.exists()) {
	            file = new File(SiteConfig.getInstance().getFileRoot() + "/member_head.jpg");
	        }
	        flushPhoto(resp,req,file,"3"); 
    	  }
      }
      else {
        //其它图片和商品图�?  
        dealCommPic(req, resp, filename);
      }
    } catch (Exception e) {
      if (!response.isCommitted()) {
        flushPhoto(resp, req, null,"3");
      }
    }
  }
  
  /**
   * 处理商品详细图片 
   */
  private void dealCommDescPic(HttpServletRequest req, HttpServletResponse resp, String filename) throws Exception {
    String path = filename.replace("/photos/",  SiteConfig.getInstance().getFileRoot() +"/");
    //图片尺寸不满足条件则不加水印
    File targetFile = new File(path);
    if (targetFile.exists()) {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      Image targetImg = ImageIO.read(targetFile);
      int width = targetImg.getWidth(null);
      int height = targetImg.getHeight(null);
      if (width/height > 0.6 && width/height < 1.4) {
        //dealImgWithWaterMark(req, resp, SiteConfig.getFileRoot() + "/mark_commodity_detail.png",path,"3");
        flushPhoto(resp,req,new File(path),"3");
      } else  {
        flushPhoto(resp,req,new File(path),"3");
      }
    } else {
      flushPhoto(resp,req,new File(path),"3");
    }
  }

  /**
   * 处理商品图片 
   */
  private void dealCommPic(HttpServletRequest req, HttpServletResponse resp, String filename) throws Exception {
    String lastName = filename.replace("/photos/","");
    lastName = lastName.substring(0,lastName.length()-4);
    String[] imgTypes = lastName.split("_");
    if (imgTypes.length > 1) {
      String commodityId = imgTypes.length > 0 ? imgTypes[0] : "";
      String key = imgTypes[imgTypes.length-1];
      String fileFullPath = SiteConfig.getInstance().getFileRoot() + CommonUtil.idToPathBase8(Long.valueOf(commodityId)) + lastName + ".jpg";
      
      if ("5".equals(key) || "6".equals(key)) {//过滤商品图片以加水印
          //dealImgWithWaterMark(req, resp, SiteConfig.getFileRoot() + "/mark_" + key + ".png", fileFullPath,key);
        flushPhoto(resp,req,new File(fileFullPath),key);
      } else {
        flushPhoto(resp,req,new File(fileFullPath),key);
      }      
    } else {
      //普�?图片
      String fileFullPath = filename.replace("/photos/", SiteConfig.getInstance().getFileRoot()+"/");
      flushPhoto(resp,req,new File(fileFullPath),"3");
    }
  }

  /**
   * 根据文件对象输入图片信息
   * @param resp
   * @param req
   * @param file
   * @throws Exception
   */
  private void flushPhoto(HttpServletResponse resp, HttpServletRequest req, File file,String type) throws IOException{
    if (file == null || !file.exists()) {
      file = new File( SiteConfig.getInstance().getFileRoot() + "/noimg_"+type+".jpg");
    }
   
    try {
      resp.setContentType("image/jpg;charset=utf-8");
      OutputStream output = resp.getOutputStream();//得到输出�?
      InputStream imageIn=new FileInputStream(file);//文件�?
      BufferedInputStream bis=new BufferedInputStream(imageIn);//输入缓冲�?
      BufferedOutputStream bos=new BufferedOutputStream(output);//输出缓冲�?
      byte data[] = new byte[4096];//缓冲字节�?
      int size = 0;
      size = bis.read(data);
      while (size != -1) {
          bos.write(data, 0, size);
          size = bis.read(data);
      }
      bis.close();
      bos.flush();//清空输出缓冲�?
      bos.close();
      output.close();
    } catch (Exception e) {
      //logger.error("图片过滤�?图片输出错误.", e);
    }
  }

@Override
public void init(FilterConfig arg0) throws ServletException {
	// TODO Auto-generated method stub
	
}
}

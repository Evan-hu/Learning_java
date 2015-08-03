package com.ga.erp.filter;

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
import com.ga.click.util.FilePath;
import com.ga.click.util.FileUploadUtil;

/**
 * 图片浏览过滤器
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Photo extends HttpServlet implements Filter {
  @SuppressWarnings("unused")
  private FilterConfig filterConfig;
  private static Logger logger = Logger.getLogger(Photo.class.getName());

  // Handle the passed-in FilterConfig
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
  ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    try {
      String filename = req.getServletPath();
      if(filename.startsWith("/photos/commodityDesc/")) {//商品描述
        dealCommDescPic(req, resp, filename);        
      } else if(filename.startsWith("/photos/")) {
        //普通图片
        String fileFullPath = filename.replace("/photos/", FileUploadUtil.getFileRoot() +"/");
        flushPhoto(resp,req,new File(fileFullPath),"3");
      } else if(filename.startsWith("/photos/brand/") || filename.startsWith("/photos/shop/")){
        String fileFullPath = filename.replace("/photos/", FileUploadUtil.getFileRoot() +"/");
        flushPhoto(resp,req,new File(fileFullPath),"3");
      } else {
        //其它图片和商品图片   
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
    String path = filename.replace("/photos/", FileUploadUtil.getFileRoot() +"/");
    //图片尺寸不满足条件则不加水印
    File targetFile = new File(path);
    if (targetFile.exists()) {
      @SuppressWarnings("unused")
	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      Image targetImg = ImageIO.read(targetFile);
      int width = targetImg.getWidth(null);
      int height = targetImg.getHeight(null);
      if (width/height > 0.6 && width/height < 1.4) {
        //dealImgWithWaterMark(req, resp, FileUploadUtil.getFileRoot() + "/mark_commodity_detail.png", path,"3");
        flushPhoto(resp,req,new File(path),"3");
      } else {
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
      String fileFullPath = FileUploadUtil.getFileRoot() + FilePath.idToPathBase8(Long.valueOf(commodityId)) + lastName + ".jpg";
      flushPhoto(resp,req,new File(fileFullPath),key);
    } else {
      //普通图片
      String fileFullPath = filename.replace("/photos/", FileUploadUtil.getFileRoot() +"/");
      flushPhoto(resp,req,new File(fileFullPath),"3");
    }
  }

//  /**
//   * 根据水印地址和图片地址把图片打上水印
//   * @param req
//   * @param resp
//   * @param waterMarkSrc  水印地址
//   * @param targerSrc  图片地址
//   * @throws IOException
//   * @throws ServletException
//   */
//  private void dealImgWithWaterMark(HttpServletRequest req, HttpServletResponse resp, String waterMarkSrc,
//      String targerSrc,String type) throws IOException, ServletException {
//    File targetFile = new File(targerSrc);
//    File waterMarkFile = new File(waterMarkSrc);
//    if (targetFile.exists() && waterMarkFile.exists()) {//商品图片存在.
//      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//      
//      //加载目标文件
//      Image targetImg = ImageIO.read(targetFile);
//      int width = targetImg.getWidth(null);
//      int height = targetImg.getHeight(null);
//      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//      Graphics g = image.createGraphics();
//      g.drawImage(targetImg, 0, 0, width, height, null);
//      
//      //加载水印文件
//      Image waterMarkImg = ImageIO.read(waterMarkFile);
//      g.drawImage(waterMarkImg, 0, 0, width, height, null);
//      g.dispose();
//      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream);
//      encoder.encode(image);
//    
//      String filename = targetFile.getName();
//      String ext = "jpg";
//      if ((targetFile.getName() != null) && (filename.length() > 0)) {
//        int i = filename.lastIndexOf('.');
//        if ((i >-1) && (i < (filename.length() - 1))) {
//          ext= filename.substring(i + 1);
//        }
//      } 
//      resp.setContentType("image/"+ext+";charset=GBK");
//      resp.setContentLength(byteArrayOutputStream.size());
//      
//      ServletOutputStream outputStream = resp.getOutputStream();
//      byteArrayOutputStream.writeTo(outputStream);
//      outputStream.close();
//    } else {
//      flushPhoto(resp, req, null,type);
//    }
//  }

  /**
   * 根据文件对象输入图片信息
   * @param resp
   * @param req
   * @param file
   * @throws Exception
   */
  private void flushPhoto(HttpServletResponse resp, HttpServletRequest req, File file,String type) throws IOException{
    if (file == null || !file.exists()) {
      file = new File(FileUploadUtil.getFileRoot() + "/noimg_"+type+".jpg");
    }    
    try {      
      String filename = file.getName();
      String ext = "jpg";
      if ((file.getName() != null) && (filename.length() > 0)) {
        int i = filename.lastIndexOf('.');
        if ((i >-1) && (i < (filename.length() - 1))) {
          ext= filename.substring(i + 1);
        }
      } 
      resp.setContentType("image/"+ext+";charset=GBK");
      OutputStream output = resp.getOutputStream();//得到输出流
      InputStream imageIn=new FileInputStream(file);//文件流
      BufferedInputStream bis=new BufferedInputStream(imageIn);//输入缓冲流
      BufferedOutputStream bos=new BufferedOutputStream(output);//输出缓冲流
      byte data[] = new byte[4096];//缓冲字节数
      int size = 0;
      size = bis.read(data);
      while (size != -1) {
          bos.write(data, 0, size);
          size = bis.read(data);
      }
      bis.close();
      bos.flush();//清空输出缓冲流
      bos.close();
      output.close();
    } catch (Exception e) {
      logger.error("图片过滤器,图片输出错误.", e);
    }
  }
  
  public void destroy() {
  }
}



package com.ga.click.services;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.util.BuffImage;
import com.ga.click.util.FilePath;
import com.ga.click.util.FileUploadUtil;
import com.ga.click.util.GaUtil;
import com.ga.click.util.TimeFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class UploadServlet extends HttpServlet {

  public UploadServlet() {
      super();
  }

  public void destroy() {
      super.destroy();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
    try {
      String flag = request.getParameter("flag");
      if (!GaUtil.isNullStr(flag))  {
        if (flag.equals("tmpfile")) {
          saveTmpFile(request,response);
        } else {
          richEditUpload(request,response);
        }
      } else {
        uploadCommodityPhoto(request,response);
      }
    } catch (Exception e) {
        e.printStackTrace();
        throw new BizException(BizException.SYSTEM, e.getMessage());
    }
  }

  public void init() throws ServletException {

  }
  
  private void richEditUpload(HttpServletRequest request, HttpServletResponse response) throws Exception  {
    response.setContentType("text/html; charset=GBK");
    response.setHeader("Cache-Control", "no-cache");
    DiskFileItemFactory fac = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(fac);
    upload.setHeaderEncoding("utf-8");

    List<FileItem> items = null;
    try {
        items = upload.parseRequest(request);
        Iterator<FileItem> itr = items.iterator();
        String flag = request.getParameter("flag");
        if (!GaUtil.isNullStr(flag))  {                       
          String type = request.getParameter("type");
          while (itr.hasNext()) {
              FileItem item = itr.next();
              if (flag.equals("img")) {
                  writeFile(item, type, new Date().getTime() + "", "",response);                    
              } else if (flag.equals("flash")) {
                  // 如果是上传flash
                writeFile(item, type, new Date().getTime() + "", "",response);  
              }
          }
        } else{
          this.uploadCommodityPhoto(request, response);
        }
    } catch (Exception e) {
        e.printStackTrace();
        throw new BizException(BizException.SYSTEM, e.getMessage());
    }
  }
  
  private void saveTmpFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String filePath = getServletContext().getRealPath("/")+"tempUpload";
    File uploadPath = new File(filePath);
    //检查文件夹是否存在 不存在 创建一个
    if(!uploadPath.exists()){
        uploadPath.mkdir();
    }
    //文件最大容量 5M
    int fileMaxSize = 5 * 1024*1024;
    //上传文件
    FileRenamePolicy policy = new TimeFileRenamePolicy();
    MultipartRequest mulit = new MultipartRequest(request,filePath,fileMaxSize,"UTF-8",policy);      
    File upFile = mulit.getFile(mulit.getFileNames().nextElement().toString());
    String rtnPath = upFile.getPath();
    rtnPath = "/"+rtnPath.replace(getServletContext().getRealPath("/"),"");
    rtnPath = rtnPath.replace("\\", "/");
    if (upFile.exists()) {
      response.setContentType("text/plain");
      response.setCharacterEncoding("GBK");
      response.getWriter().write(rtnPath);
    } else {
      response.setContentType("text/plain");
      response.getWriter().write("");
    }
  }
  
  /**
   * 
   * @param item
   * @param filaPath
   * @param fileName
   * @param ext
   *            文件校验方式
   * @return
   */
  private void writeFile(FileItem item, String type, String fileName, String ext, HttpServletResponse response) {

      String extensionName = item.getName().substring(item.getName().lastIndexOf(".") + 1).toLowerCase();
      String dirPath = FileUploadUtil.getEditPicFilePath(type);
      File savePath = new File(dirPath);
      if (!savePath.isDirectory()) {
          savePath.mkdirs();
      }
      String mes = "";
      String dispPath = "";
      PrintWriter out = null;
      try {
          out = response.getWriter();
          String filePath = (GaUtil.isNullStr(fileName) ? new Date().getTime() : fileName) + "." + extensionName;
          item.write(new File(dirPath+"/"+filePath));
          dispPath = FileUploadUtil.getEditVRPath(type) + "/" + filePath;
      } catch (Exception e) {
          e.printStackTrace();
          mes = "对不起，上传失败！";
          throw new BizException(BizException.SYSTEM, e.getMessage());
      } finally {
          if (out != null) {
              out.write("{\"err\":\"" + mes + "\",\"msg\":\"" + dispPath + "\"}");
              out.flush();
              out.close();
          }
      }
  }
  
  private void uploadCommodityPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception  {
    String m = request.getParameter("m");
    if(m!= null){
      String pictureIdStr = request.getParameter("pId");
      String commodityIdStr = request.getParameter("cId");
      if(m.equals("setMain")){
        if( !GaUtil.isNullStr(pictureIdStr) && !GaUtil.isNullStr(commodityIdStr)){
          setMain(Long.parseLong(commodityIdStr), Long.parseLong(pictureIdStr));
        } else {
          throw new BizException(BizException.SYSTEM, "上传异常.");
        }
      } else if (m.equals("delMain")) {
        if( !GaUtil.isNullStr(commodityIdStr)){
          delMainImg(Long.valueOf(commodityIdStr));
        } else {
          throw new BizException(BizException.SYSTEM, "上传异常.");
        }
      } else if (m.equals("del")) {
        if( !GaUtil.isNullStr(pictureIdStr) && !GaUtil.isNullStr(commodityIdStr)){
          delImg(FileUploadUtil.getFileRoot()  + FilePath.idToPathBase8(Long.parseLong(commodityIdStr)), Long.parseLong(commodityIdStr), Long.parseLong(pictureIdStr));
        }
      } else if (m.equals("topImg")) {
        if( !GaUtil.isNullStr(pictureIdStr) && !GaUtil.isNullStr(commodityIdStr)){
          topImg(Long.parseLong(commodityIdStr), Long.parseLong(pictureIdStr));
        }
      }
      response.setContentType("text/plain");
      response.getWriter().write(commodityIdStr);
      return;
    } else {
      String filePath = getServletContext().getRealPath("/")+"tempUpload";
      File uploadPath = new File(filePath);
      //检查文件夹是否存在 不存在 创建一个
      if(!uploadPath.exists()){
          uploadPath.mkdir();
      }
      //文件最大容量 5M
      int fileMaxSize = 5 * 1024*1024;
      //上传文件
      MultipartRequest mulit = new MultipartRequest(request,filePath,fileMaxSize,"GBK");
      String commodityIdStr = mulit.getParameter("commodityId");
      String subID =  mulit.getParameter("subid");
      if( !GaUtil.isNullStr(commodityIdStr)){
        Long commodityId = Long.parseLong(commodityIdStr);
        String relativeImgFilePath = FilePath.idToPathBase8(commodityId);
        String absoluteImgFIlePath = FileUploadUtil.getFileRoot() +  relativeImgFilePath;
        File upFile = mulit.getFile(mulit.getFileNames().nextElement().toString());
        String imgType = upFile.getName().substring(upFile.getName().lastIndexOf(".") + 1).toLowerCase();
        
        File file = new File(absoluteImgFIlePath);
        if (!file.exists()) {file.mkdirs();}
        Long pictureId = null;
        //判断是不是商品第一张图片,如果是则保存为主图
        String imgName =  commodityId + "_6.jpg";//图片保存时的名字
        boolean fileExists = new File(absoluteImgFIlePath + imgName).exists();//主图文件存不存在
        boolean fileExists2 = new File(absoluteImgFIlePath +  commodityId + "_4.jpg").exists();//主图文件存不存在
        if (fileExists && fileExists2) {
          if (GaUtil.isNullStr(subID)) {
            //新建子图片
            pictureId = saveImg(commodityId, imgType);              
          } else {
            pictureId = Long.parseLong(subID);
          }
          if(pictureId != -1){
            imgName = commodityId+ "_" + pictureId + "_6.jpg";
          }
        }
        File imgFile = new File(absoluteImgFIlePath + imgName);
        //upFile.renameTo(imgFile);   
        GaUtil.copyFile(upFile.getPath(),imgFile.getPath());
        GaUtil.delFile(upFile.getPath());
        try {
          if (fileExists) {//普通图片
            BuffImage.saveImageAsJpg(imgFile.getPath(), absoluteImgFIlePath + "/" + commodityId + "_" + pictureId + "_1" + ".jpg", 65, 65);
            BuffImage.saveImageAsJpg(imgFile.getPath(), absoluteImgFIlePath + "/" + commodityId + "_" + pictureId + "_2" + ".jpg", 130, 130);
            BuffImage.saveImageAsJpg(imgFile.getPath(), absoluteImgFIlePath + "/" + commodityId + "_" + pictureId + "_3" + ".jpg", 200, 200);
            BuffImage.saveImageAsJpg(imgFile.getPath(), absoluteImgFIlePath + "/" + commodityId + "_" + pictureId + "_4" + ".jpg", 300, 300);
            BuffImage.saveImageAsJpg(imgFile.getPath(), absoluteImgFIlePath + "/" + commodityId + "_" + pictureId + "_5" + ".jpg", 600, 600);
          } else {
            BuffImage.saveImageAsJpg(imgFile.getPath(), absoluteImgFIlePath + "/" + commodityId + "_1" + ".jpg", 65, 65);
            BuffImage.saveImageAsJpg(imgFile.getPath(), absoluteImgFIlePath + "/" + commodityId + "_2" + ".jpg", 130, 130); 
            BuffImage.saveImageAsJpg(imgFile.getPath(), absoluteImgFIlePath + "/" + commodityId + "_3" + ".jpg", 200, 200);
            BuffImage.saveImageAsJpg(imgFile.getPath(), absoluteImgFIlePath + "/" + commodityId + "_4" + ".jpg", 300, 300);
            BuffImage.saveImageAsJpg(imgFile.getPath(), absoluteImgFIlePath + "/" + commodityId + "_5" + ".jpg", 600, 600);
          }            
          response.setContentType("text/plain");
          response.getWriter().write(commodityIdStr);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
    
  /**
   * 
   * @param commodityId
   * @param imgType
   * @return -1表示加入失败
   */
  Long saveImg(Long commodityId, String imgType){
    Long picId;
    try {
      
      DbUtils.begin();
      picId = DbUtils.queryLong("select SEQ_COMMODITY_PICTURE.NEXTVAL from dual");
      CachedRowSet ocrs = DbUtils.query("select * from COMMODITY_PICTURE cp where cp.commodity_Id=? order by RANK_NUM desc", commodityId);
      if(ocrs.next()){
        int rankNum = ocrs.getInt("RANK_NUM");
        DbUtils.execute("insert into COMMODITY_PICTURE(PICTURE_ID,COMMODITY_ID,STATE,PICTURE_TYPE,RANK_NUM,ISMAIN,IMG_URL) values(?, ?, 1, ?, ?, 1, ?)", picId, commodityId, imgType, rankNum + 1, FilePath.idToPathBase8(commodityId));
      } else {
        DbUtils.execute("insert into COMMODITY_PICTURE(PICTURE_ID,COMMODITY_ID,STATE,PICTURE_TYPE,RANK_NUM,ISMAIN,IMG_URL) values(?, ?,1, ?, ?, 1, ?)", picId, commodityId, imgType, 1, FilePath.idToPathBase8(commodityId));
      }
      DbUtils.commit();
    } catch (Exception e) {
      picId = -1L;
      DbUtils.rollback();
    }
    return picId;
  }
  
  Long delMainImg(Long commodityId){
    try {
      String baseImgFilePath = FileUploadUtil.getFileRoot()  + FilePath.idToPathBase8(commodityId);
      for (int i = 1; i < 7; i++) {
        File file = new File(baseImgFilePath + commodityId + "_" + i + ".jpg");
        if (file.exists()) {
          file.delete();
        }
      }
      CachedRowSet ocrs = DbUtils.query("select * from COMMODITY_PICTURE cp where cp.commodity_Id=? order by RANK_NUM", commodityId);
      if (ocrs.next()) {
        Long pictureId = ocrs.getLong("PICTURE_ID");
        setMain(commodityId, pictureId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return commodityId;
  }
  
  Long delImg(String baseImgFilePath, Long commodityId, Long pictureId){
    String minImgFilePath = baseImgFilePath + commodityId + "_" + pictureId + "_6.jpg";
    try {
      DbUtils.begin();
      DbUtils.execute("delete from COMMODITY_PICTURE where PICTURE_ID=?", pictureId);
      DbUtils.commit();
    } catch(Exception e) {
      DbUtils.rollback();
    }      
    if (new File(minImgFilePath).exists()) {
      try {
        //删除数据库记录和文件          
        baseImgFilePath = FileUploadUtil.getFileRoot() + FilePath.idToPathBase8(commodityId); 
        for (int i = 1; i < 7; i++) {
          File file = new File(baseImgFilePath + commodityId + "_"+ pictureId + "_" + i + ".jpg");
          if(file.exists() && file.isFile()) {
            file.delete();
          }
        }
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return commodityId;
  }
    
  Long setMain(Long commodityId, Long pictureId){
    try {
      DbUtils.begin();
      String mainImgFilePath = FileUploadUtil.getFileRoot() + FilePath.idToPathBase8(commodityId);
      
      String oldImgFilePath = mainImgFilePath + commodityId + "_" + pictureId + "_6.jpg";        
      String oldMainImgFilePath = mainImgFilePath + commodityId + "_6.jpg";        
      File oldMainFile = new File(oldMainImgFilePath);
      //将以前的主图更改为普通图片
      if (oldMainFile.exists()) {
        CachedRowSet ocrs = DbUtils.query("select * from COMMODITY_PICTURE cp where cp.commodity_Id=? order by RANK_NUM desc", commodityId);
        Long picId = DbUtils.queryLong("select SEQ_COMMODITY_PICTURE.NEXTVAL from dual");
        if (ocrs.next()) {
          Long rankNum = ocrs.getLong("RANK_NUM");
          DbUtils.execute("insert into COMMODITY_PICTURE values(?, ?,null, 1, ?, ?, 1, ?)", picId, commodityId, "jpg", rankNum + 1, FilePath.idToPathBase8(commodityId));
          BuffImage.saveImageAsJpg(oldMainImgFilePath, mainImgFilePath + commodityId + "_" + picId + "_1.jpg", 65, 65);
          BuffImage.saveImageAsJpg(oldMainImgFilePath, mainImgFilePath + commodityId + "_" + picId + "_2.jpg", 130, 130);
          BuffImage.saveImageAsJpg(oldMainImgFilePath, mainImgFilePath + commodityId + "_" + picId + "_3.jpg", 200, 200);
          BuffImage.saveImageAsJpg(oldMainImgFilePath, mainImgFilePath + commodityId + "_" + picId + "_4.jpg", 300, 300);
          BuffImage.saveImageAsJpg(oldMainImgFilePath, mainImgFilePath + commodityId + "_" + picId + "_5.jpg", 600, 600);
          File imgFile = new File(mainImgFilePath + commodityId + "_" + picId + "_6.jpg");
          oldMainFile.renameTo(imgFile);                  
        }
      }
      //将此图片设置为主图
      //覆盖以前的主图如果图片存在
      File oldFile = new File(oldImgFilePath);
      if (oldFile.exists()) {
        BuffImage.saveImageAsJpg(oldImgFilePath, mainImgFilePath + commodityId + "_1.jpg", 65, 65);
        BuffImage.saveImageAsJpg(oldImgFilePath, mainImgFilePath + commodityId + "_2.jpg", 130, 130);
        BuffImage.saveImageAsJpg(oldImgFilePath, mainImgFilePath + commodityId + "_3.jpg", 200, 200);
        BuffImage.saveImageAsJpg(oldImgFilePath, mainImgFilePath + commodityId + "_4.jpg", 300, 300);
        BuffImage.saveImageAsJpg(oldImgFilePath, mainImgFilePath + commodityId + "_5.jpg", 600, 600);
        File imgFile = new File(mainImgFilePath + commodityId + "_6.jpg");
        oldFile.renameTo(imgFile);
      }
      //删除数据库记录和文件
      DbUtils.execute("delete from COMMODITY_PICTURE where picture_id = ?", pictureId);
      for (int i = 1; i < 7; i++) {
        File file = new File(mainImgFilePath + commodityId + "_" + pictureId + "_" + i + ".jpg");
        if (file.exists()) {
          file.delete();
        }
      }
      DbUtils.commit();
    } catch (Exception e) {
      DbUtils.rollback();
    }
    return null;
  }
    
  void topImg(Long commodityId, Long pictureId){
    try {
      DbUtils.begin();
      CachedRowSet rs = DbUtils.query("select RANK_NUM from COMMODITY_PICTURE where PICTURE_ID=?", pictureId);
      if(rs.next()){
        Long rankNum = rs.getLong("RANK_NUM");
        DbUtils.execute("update COMMODITY_PICTURE set RANK_NUM=? where RANK_NUM=1 and COMMODITY_ID=?", rankNum, commodityId);
        DbUtils.execute("update COMMODITY_PICTURE set RANK_NUM=1,ISMAIN = 1 where PICTURE_ID=?", pictureId);
      }
      DbUtils.commit();
    } catch (Exception e) {
      DbUtils.rollback();
    }
  }


}

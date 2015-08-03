package com.ga.click.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;
import com.brooksandrus.utils.swf.SWFHeader;
import com.ga.click.exception.BizException;

public class FileUploadUtil {
  private static String siteRoot = "";
  private static String fileRoot = "";
  private static String fileVrRoot = "";
  private static String httpRoot = "";

  static  {
    //读取配置文件初始化
    InputStream fis = null;
    try
    {
       // 定义一个properties对象
       Properties properties = new Properties();
       fis = FileUploadUtil.class.getResourceAsStream("/config.properties");
       properties.load(fis);
       siteRoot = properties.getProperty("SITE_ROOT");
       fileRoot = properties.getProperty("FILE_ROOT");
       fileVrRoot = properties.getProperty("FILEVR_ROOT");
       httpRoot = properties.getProperty("HTTP_ROOT");
    }
    catch (IOException e)
    {
    }
    finally {
      if (fis != null) {
        try {
          fis.close();  
        } catch(Exception ex) {          
        }        
      }
    }
  }

  private static String saveUploadFile(String sourcePath,String resType,Long resID) {
    return saveUploadFile(sourcePath,resType,resID.toString());
  }
  
  public static String getHttpRoot() {
    return httpRoot;
  }

  
  /**
   * 处理文件上传
   * @param sourcePath 上传的临时路径(相对路径)
   * @param saveRoot 保存的根目录()
   * @return 保存后的路径(绝对路径)
   */
  private static String saveUploadFile(String sourcePath,String resType,String fileName) {
    try {
      if(GaUtil.isNullStr(sourcePath)){
        return "";          
      }      
      String suffix =  sourcePath.substring(sourcePath.lastIndexOf(".")+1);
      suffix = suffix.toLowerCase();
      //新的保存路径为:log根路径+ID+后缀
      String newPath =  getPicFilePath(resType) + "/" + fileName + "."+ suffix;      
      String oldPath = siteRoot + sourcePath;
      if (!oldPath.equals(newPath)) {
        GaUtil.copyFile(oldPath,newPath );
        GaUtil.delFile(oldPath);
      }
      return newPath;
    }   
    catch (Exception ex) {
      throw new BizException(BizException.SYSTEM,"保存文件异常");
    }
  }
  
  /**
   * 从文件路径中获取具体的文件名
   * @param filePath 文件路径
   * @return
   */
  private static String getFileName(String filePath) {
   return  filePath.substring(filePath.lastIndexOf("/")+1);
  }
  
  
  /**
   * 获取图片文件系统路径
   * @param resType 资源类型,不指定则表示图片根目录
   * @return 返回路径(不含最后的斜杠)
   */
  public static String getPicFilePath(String resType) {
    if (GaUtil.isNullStr(resType)) {
      return fileRoot;
    } else {
      return fileRoot+"/"+resType;
    }
  }
  
  public static String getEditPicFilePath(String resType) {
    if (GaUtil.isNullStr(resType)) {
      return fileRoot + "/editor";
    } else {
      return fileRoot+"/editor/"+resType;
    }
  }
  
  public static String getEditVRPath(String resType) {
    if (GaUtil.isNullStr(resType)) {
      return "/photos/editor";
    } else {
      return "/photos/editor/"+resType;
    }
  }
  /**
   * 返回文件存储根路径
   * @return
   */
  public static String getFileRoot() {
    return fileRoot;
  }
  
  public static String getSiteRoot() {
    return siteRoot;
  }
  
  /**
   * 获取图片虚拟路径地址(图片路径前后台配置到统一的虚拟目录中)
   * @param resType 资源类型,不指定则表示图片根目录
   * @return
   */
  public static String getPicVrPath(String resType) {
    if (GaUtil.isNullStr(resType)) {
      return fileVrRoot;
    } else {
      return fileVrRoot +"/" +resType;
    }
  }
  
  /**
   * 执行文件上传保存处理（获得图片长宽或者是FLASH的长宽，其他文件格式无法获取,FLASH只能上传SWF格式）
   * (执行业务数据保存前调用)
   * @param valueMap 业务数据对象
   * @param fileField 文件字段名
   * @param resType 文件资源类型
   */
  public static void uploadSave(Map<String,Object> valueMap,String fileField,String resType,Long resID,boolean isGetFileInfo) {
    try {
      //转换值内容
      String uploadPath = (String)valueMap.get(fileField);
      if (!GaUtil.isNullStr(uploadPath)) {
        if (!uploadPath.startsWith(getPicVrPath(""))) {
          //如上传的临时目录同虚拟路径不一致，表示已修改
          String name = saveUploadFile(uploadPath,resType,resID);
          File file = new File(name);
          long height = 0L;
          long width = 0L;
          if(name.indexOf(".swf") > 0){
            SWFHeader header = new SWFHeader(file);
            height = header.getHeight();
            width = header.getWidth();
          }else{
            BufferedImage bi = null;
            try {
              bi = ImageIO.read(file);
              height = (long) bi.getHeight();
              width = (long) bi.getWidth();

            } catch (IOException e) {
              throw new BizException(BizException.SYSTEM, "创建图片异常");
            } finally {
              if (bi != null) {
                bi.flush();
              }
            }
          }
          valueMap.put("WIDTH", width);
          valueMap.put("HEIGHT", height);
          valueMap.put(fileField, getFileName(name));
        } else {
          valueMap.put(fileField, getFileName(uploadPath));  
        }        
      }      
    } catch(Exception ex) {
      throw new BizException("处理上传文件异常");
    }    
  }
  /**
   * 执行文件上传保存处理
   * (执行业务数据保存前调用)
   * @param valueMap 业务数据对象
   * @param fileField 文件字段名
   * @param resType 文件资源类型
   */
  public static void uploadSave(Map<String,Object> valueMap,String fileField,String resType,Long resID) {
    try {
      //转换值内容
      String uploadPath = (String)valueMap.get(fileField);
      if (!GaUtil.isNullStr(uploadPath)) {
        if (!uploadPath.startsWith(getPicVrPath(""))) {
          //如上传的临时目录同虚拟路径不一致，表示已修改
          String name = saveUploadFile(uploadPath,resType,resID);
          valueMap.put(fileField, getFileName(name));
        } else {
          valueMap.put(fileField, getFileName(uploadPath));  
        }        
      }      
    } catch(Exception ex) {
      throw new BizException("处理上传文件异常");
    }
  }
  
  public static void uploadSave(String tmpFileValue,String resType,String newName) {
    try {
      //转换值内容
      if (!GaUtil.isNullStr(tmpFileValue)) {
        if (!tmpFileValue.startsWith(getPicVrPath(""))) {
          //如上传的临时目录同虚拟路径不一致，表示已修改
          String name = saveUploadFile(tmpFileValue,resType,newName);
        }       
      }      
    } catch(Exception ex) {
      throw new BizException("处理上传文件异常");
    }
  }
  
  /**
   * 将文件字段转为可下载的连接或可预览的图片地址
   * @param valueMap 查询出的值对象
   * @param fileField 文件字段名
   * @param resType 文件资源类型
   */
  public static void downloadSet(Map<String,Object> valueMap,String fileField,String resType) {
    try {
      String fileName = (String)valueMap.get(fileField);
      if (!GaUtil.isNullStr(fileName)) {
        valueMap.put(fileField, getPicVrPath(resType) + "/" + fileName);
      }  
    } catch(Exception ex) {
      throw new BizException("处理文件路径转换异常");
    }
  }
}

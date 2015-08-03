/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.company.shop.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.company.shop.exception.BizException;

/**
 * 类描述
 * @author administrator
 * @create_time 2014-7-30 下午4:32:38
 * @project companyWeb
 */
public class FileUtil {
  
  /**
   *保存文件到指目录 
   * @param in
   * @param directoryPath
   * @param fileName
   * @throws IOException 
   */
  public static void saveFile(InputStream in, String directoryPath, String fileName) throws IOException{
    String sitePath = SiteConfig.getInstance().getDocRoot();
    File directory = createOrLoadDirectory(sitePath + directoryPath);
    File file = createOrLoadFile(directory.getPath() + fileName);
    FileOutputStream out = null;
    byte[] buffer = new byte[1024];
    try {
      out = new FileOutputStream(file);
      while(in.read(buffer) != -1) {
        out.write(buffer);
      }
      
       out.flush();
    }
    catch (Exception e) {
      throw new BizException("保存文件失败!");
    }
    finally{
      if (out != null) {
        out.close();
      }
      
      if (in != null) {
        in.close();
      }
    }
  }
  
  /**
   * 创建目录是否存在，不存在创建目录
   * @param directory
   * @return
   */
  public static File createOrLoadDirectory(String directoryPath) {
    File directory = new File(directoryPath);
    if (!directory.exists()) {
        if(!directory.mkdir()){
          throw new BizException("创建目录失败!");
        }
    }
    
    return directory;
  }
  
  /**
   * 判断指定的文件是否存在，如不存在则创建文件
   * @param filePath
   * @return
   * @throws IOException 
   */
  public static File createOrLoadFile(String filePath) throws IOException{
    File file = new File(filePath);
    if (!file.exists()) {
      if(!file.createNewFile()) {
          throw new BizException("创建文件失败!");
      }
    }
    
    return file;
  }
}

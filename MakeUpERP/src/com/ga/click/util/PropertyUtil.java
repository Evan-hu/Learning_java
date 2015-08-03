package com.ga.click.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 类描述
 * @author spe
 * @create_time 2014-3-11 上午10:45:45
 */
public class PropertyUtil {
  private static boolean isSimpleAd = false;
  
  static  {
    //读取配置文件初始化
    InputStream fis = null;
    try
    {
       // 定义一个properties对象
       Properties properties = new Properties();
       fis = FileUploadUtil.class.getResourceAsStream("/config.properties");
       properties.load(fis);
       try {
         isSimpleAd = Boolean.parseBoolean(properties.getProperty("IS_SIMPLE_AD"));
       } catch (Exception e) {
         isSimpleAd = false;
       }
       System.out.println("issimplead="+isSimpleAd);
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
  
  
  public static boolean isSimpleAd() {
    return isSimpleAd;
  }
}

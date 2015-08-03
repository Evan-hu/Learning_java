package com.ga.click.util;

/**
 * 保存文件或者图片存储路径
 * @create_time 2011-2-21 上午10:26:53
 */
public class FilePath {
  
  public static String PHOTO_PATH = "D:/iy3/Shop";//图片绝对路径  
  public static String COMMDOITY = "/photos/commodity";//商品图片
  public static String TMPFILE_PATH = "/tmpfile";//商品图片
  
  
  /**
   * 根据商品ID转化生成相应的图片存储路径
   * e.g. 8 -> /00/00/00/01
   */
  public static String idToPathBase8(Long commodityId) {
    String raw = "0000000";
    String idStr = commodityId.toString();
    String combinePath = raw.substring(0, 8 - idStr.length()) + idStr;
    StringBuilder builder = new StringBuilder();
    builder.append("/").append(combinePath.substring(0, 2))
           .append("/").append(combinePath.substring(2, 4)).append("/").append(combinePath.substring(4, 6))
           .append("/").append(combinePath.substring(6, 8)).append("/");
    return builder.toString();
  }
  
}

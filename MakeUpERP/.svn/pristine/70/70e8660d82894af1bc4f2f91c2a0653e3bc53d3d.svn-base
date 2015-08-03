package com.ga.erp.biz.statistic.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//import com.noah.ebiz.norder.activity.entiy.SysCode;

public  class ActivityUtil {
  
  
  /**
   * 赠送多倍积分
   */
  public static final long DISCOUNT_TYPE_2 = 2L;
  /**
   * 赠送固定积分
   */
  public static final long DISCOUNT_TYPE_3 = 3L;
  /**
   * 赠送赠品
   */
  public static final long DISCOUNT_TYPE_4 = 4L;
  /**
   * 赠送优惠券
   */
  public static final long DISCOUNT_TYPE_5 = 5L;
  /**
   * 减免固定金额
   */
  public static final long DISCOUNT_TYPE_6 = 6L;
  /**
   * 打折
   */
  public static final long DISCOUNT_TYPE_8 = 8L;
  
  /**
   * 赠送礼品
   */
  public static final long DISCOUNT_TYPE_12 = 12L;
  
  public static int MONEY_POINT_NUM = 1;// 金钱小数保留位数

  public static int SCORE_POINT_NUM = 1;// 积分小数保留位数

  public static int MONEY_DIVDE_TYPE = BigDecimal.ROUND_HALF_UP;// 金钱小数点截截取方式

  public static int SCORE_DIVDE_TYPE = BigDecimal.ROUND_HALF_UP;// 积分小数点截截取方式
  
  public static String cutStr(String src, int maxLen) {
    try {
      if (src == null || src.equals("") || maxLen < 1) {
        return "";
      }
      char[] chars = src.toCharArray();
      int srcLen = 0;
      int charLen = 0;
      for (int i = 0; i < chars.length; i++) {
        charLen = (chars[i] / (int) 0x80 == 0) ? 1 : 2;
        if (srcLen + charLen >= maxLen) {
          if (i < chars.length) {
            byte[] strByte = new byte[srcLen];
            System.arraycopy(src.getBytes("GBK"), 0, strByte, 0, srcLen);
            return new String(strByte,"GBK") + "…";
          } else {
            return src;
          }
        } else {
          srcLen += charLen;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return src;
  }
  /**
   * 检查字符串不为null且不为empty也不为""
   * 
   * @return true:是, false:否.
   */
  public static boolean isNotNullStr(String str) {
    return str != null && !str.isEmpty() && !"".equals(str);
  }
  
  /**
   * 处理价格，只保留角，返回的值也是乘了100的（如：传入1056，返回1060）
   * 
   * @param num
   * @param divisor
   * @return
   */
  public static Long cutPrice(Object num) {
    try {
      if (num == null) {
        return 0L;
      }
      return mulLong(cutPriceStr(num, 100), 100);
    } catch (Exception e) {
      e.printStackTrace();
      return 0L;
    }
  }
  
  public static long mulLong(Object v1, Object v2) {
    return new BigDecimal(v1.toString()).multiply(new BigDecimal(v2.toString())).longValue();
  }
  
  public static Double cutPriceStr(Object num,Object divisor) {
    try {
      if (num == null) {
        return 0d;
      }
      return new BigDecimal(num.toString()).divide(new BigDecimal(divisor.toString()), MONEY_POINT_NUM,MONEY_DIVDE_TYPE).doubleValue();
    } catch (Exception e) {
      e.printStackTrace();
      return 0d;
    }
  }

  /**
   * 处理价格，只保留1位，返回的值也是乘了100的（如：传入1056，返回1060）
   * 
   * @param num
   * @param divisor
   * @return
   */
  public static Long cutScore(Object num) {
    try {
      if (num == null) {
        return 0L;
      }
      return mulLong(cutPriceStr(num, 100), 100);
    } catch (Exception e) {
      e.printStackTrace();
      return 0L;
    }
  }
  
  /**
   * 获得优惠值（多买多得的方式，如活动满200送优惠10元，用户购买450块 应该优惠20元，赠品活动除外）
   * @param maxCount 最大允许优惠次数 （0为不限制）
   * @param activityPrice 购买的金额（活动后）
   * @param setPrice 规则设置的金额
   * @return
   */
  
//  public static Long getPreferentialValue(long activityPrice,com.noah.ebiz.norder.activity.entiy.ActivityRule rule){
//    if(rule == null || activityPrice == 0L){
//      return 1L;
//    }
//    long count = 1L;
//    long maxCount = rule.getCount();
//    if(rule.getMinMoney() > 0){
//      count = activityPrice / rule.getMinMoney();
//    }else if(rule.getMinCount() > 0){
//      count = activityPrice / rule.getMinCount();
//    }
//    if(maxCount > 0 && count > maxCount){
//      count = maxCount;
//    }
//    return count;
//  }

  
  /**
   * 两数相除并保留指定位数小数
   * 
   * @param size
   *          被除数
   * @param divisor
   *          除数
   * @param pointNum
   *          保留小数位
   * @param divideType
   *          小数点截截取方式: BigDecimal.ROUND_DOWN ROUND_FLOOR ROUND_HALF_DOWN
   *          ROUND_HALF_EVEN ROUND_HALF_UP
   * @return
   */
  public static double dividePoint(Object size, Object divisor, int pointNum, int divideType) {
    try {
      if (size == null || pointNum == 0) {
        return 0d;
      }
      return new BigDecimal(size.toString()).divide(new BigDecimal(divisor.toString()), pointNum, divideType)
          .doubleValue();
    } catch (Exception e) {
      e.printStackTrace();
      return 0d;
    }
  }
  
  public static Double cutScoreStr(Object num, Object divisor) {
    try {
      if (num == null) {
        return 0d;
      }
      return new BigDecimal(num.toString()).divide(new BigDecimal(divisor.toString()), SCORE_POINT_NUM,
          SCORE_DIVDE_TYPE).doubleValue();
    } catch (Exception e) {
      e.printStackTrace();
      return 0d;
    }
  }
  
  public static double subtDouble(Object v1, Object v2) {
    return new BigDecimal(v1.toString()).subtract(new BigDecimal(v2.toString())).doubleValue();
  }
  
  /**
   * 判断传入的集合是否全部为数字.
   * @param str 传入的字符串. 
   * @return true:数字. false:不是数字.
   */
  public static boolean isNumber(String ...strs) {
    for(String str : strs){
      if (str == null || "".equals(str)) {
        return false;
      }
      if(!java.util.regex.Pattern.matches("^-?\\d+$", str.trim())){
        return false;
      }
    }
    return true;
  }
  
//  public static boolean haveHouse(long houseId,long placeId){
//    if(houseId == placeId){
//      return true;
//    }
//    ActivityCache ca = ActivityCache.getInstance();
//    SysCode pSort = ca.getSysCodeMap().get(houseId);
//    if(pSort == null){
//      return false;
//    }
//    if(pSort.getChildSysCodes() != null){
//      for(SysCode childSysCode : pSort.getChildSysCodes()){
//        if(childSysCode.getSysCodeId() == placeId){
//          return true;
//        }else{
//          if(haveHouse(childSysCode.getSysCodeId(),placeId)){
//            return true;
//          }
//        }
//      }
//    }
//    return false;
//  }

  public  static String getOrderNum(){
    Date date=new Date();
    DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
    return df.format(date);
  }
  //获取日期，格式：yyyy-MM-dd HH:mm:ss
  public  static String getDateFormatter(){
    Date date=new Date();
    DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return df.format(date);
  }
  
  
  public static String getDate(){
    Date date=new Date();
    DateFormat df=new SimpleDateFormat("yyyyMMdd");
    return df.format(date);
  }
  
  //产生随机的三位数
  public static String getThree(){
    Random rad=new Random();
    return rad.nextInt(1000)+"";
  }

}

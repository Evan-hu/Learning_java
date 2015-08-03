package com.company.shop.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 工具�?- 运算
 */

public class ArithUtil {

  // 默认除法运算精度
  private static final int DEF_DIV_SCALE = 10;

  private ArithUtil() {
    
  }

  /**
   * 提供精确的加法运算�?
   * 
   * @param v1
   *            被加�?
   * @param v2
   *            加数
   * @return 两个参数的和
   */
  public static double add(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.add(b2).doubleValue();
  }

  /**
   * 提供精确的减法运算�?
   * 
   * @param v1
   *            被减�?
   * @param v2
   *            减数
   * @return 两个参数的差
   */
  public static double sub(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.subtract(b2).doubleValue();
  }

  /**
   * 提供精确的乘法运算�?
   * 
   * @param v1
   *            被乘�?
   * @param v2
   *            乘数
   * @return 两个参数的积
   */
  public static Double mul(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.multiply(b2).doubleValue();
  }

  /**
   * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以�?0位，以后的数字四舍五入�?
   * 
   * @param v1
   *            被除�?
   * @param v2
   *            除数
   * @return 两个参数的商
   */
  public static double div(double v1, double v2) {
    return div(v1, v2, DEF_DIV_SCALE);
  }

  /**
   * 提供（相对）精确的除法运算�?当发生除不尽的情况时，由scale参数�?定精度，以后的数字四舍五入�?
   * 
   * @param v1
   *            被除�?
   * @param v2
   *            除数
   * @param scale
   *            表示表示�?��精确到小数点以后几位�?
   * @return 两个参数的商
   */
  public static Double div(double v1, double v2, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException("参数scale必须为整数为�?");
    }
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  /**
   * 提供精确的小数位四舍五入处理�?
   * 
   * @param v
   *            �?��四舍五入的数�?
   * @param scale
   *            小数点后保留几位
   * @return 四舍五入后的结果
   */
  public static double round(double v, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException("参数scale必须为整数为�?");
    }
    BigDecimal b = new BigDecimal(Double.toString(v));
    BigDecimal one = new BigDecimal("1");
    return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  /**
   * 提供精确的类型转�?Float)
   * 
   * @param v
   *            �?��被转换的数字
   * @return 返回转换结果
   */
  public static float convertsToFloat(double v) {
    BigDecimal b = new BigDecimal(v);
    return b.floatValue();
  }

  /**
   * 提供精确的类型转�?Int)不进行四舍五�?
   * 
   * @param v
   *            �?��被转换的数字
   * @return 返回转换结果
   */
  public static int convertsToInt(double v) {
    BigDecimal b = new BigDecimal(v);
    return b.intValue();
  }

  /**
   * 提供精确的类型转�?Long)
   * 
   * @param v
   *            �?��被转换的数字
   * @return 返回转换结果
   */
  public static long convertsToLong(double v) {
    BigDecimal b = new BigDecimal(v);
    return b.longValue();
  }

  /**
   * 返回两个数中大的�?���?
   * 
   * @param v1
   *            �?��被对比的第一个数
   * @param v2
   *            �?��被对比的第二个数
   * @return 返回两个数中大的�?���?
   */
  public static double returnMax(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.max(b2).doubleValue();
  }

  /**
   * 返回两个数中小的�?���?
   * 
   * @param v1
   *            �?��被对比的第一个数
   * @param v2
   *            �?��被对比的第二个数
   * @return 返回两个数中小的�?���?
   */
  public static double returnMin(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.min(b2).doubleValue();
  }

  /**
   * 精确比较两个数字
   * 
   * @param v1
   *            �?��被对比的第一个数
   * @param v2
   *            �?��被对比的第二个数
   * @return 如果两个数一样则返回0，如果第�?��数比第二个数大则返回1，反之返�?1
   */
  public static int compareTo(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.compareTo(b2);
  }
  
  /**
   * 获取数字小数位数
   * 
   * @param number
   *            数字.
   * 
   * @return 小数位数
   */
  public static int getDecimals(double number) {
    DecimalFormat decimalFormat = new DecimalFormat("#.####");
    String numberString = decimalFormat.format(number);
    if (numberString.indexOf(".") > 0) {
      return numberString.length() - String.valueOf(number).indexOf(".") - 1;
    } else {
      return 0;
    }
  }
  
  /**
   * 获取数字小数位数
   * 
   * @param number
   *            数字.
   * 
   * @return 小数位数
   */
  public static int getDecimals(float number) {
    DecimalFormat decimalFormat = new DecimalFormat("#.####");
    String numberString = decimalFormat.format(number);
    if (numberString.indexOf(".") > 0) {
      return numberString.length() - String.valueOf(number).indexOf(".") - 1;
    } else {
      return 0;
    }
  }
}

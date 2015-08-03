package com.ga.click.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.sql.rowset.CachedRowSet;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;

public class GaUtil {
  
	private static String formateDate = "yyyy-MM-dd HH:mm:ss";
	
	public static SimpleDateFormat sdf = new SimpleDateFormat(formateDate);
	
	public static Set<String> haveDelTables = new HashSet<String>();//拥有删除字段的表格
	
	public static Set<String> haveCheckTables = new HashSet<String>();//拥有审核字段的表格
	
	private static String[][] FilterChars = { { "<", "&lt;" }, { ">", "&gt;" },
			{ " ", "&nbsp;" }, { "\"", "&quot;" }, { "&", "&amp;" },
			{ "/", "&#47;" }, { "\\", "&#92;" }, { "\n", "<br>" } };
	
	/**
	 *  过滤通过javascript脚本处理并提交的字符
	 */
	private static String[][] FilterScriptChars = { { "\n", "\'+\'\\n\'+\'" },
			{ "\r", " " }, { "\\", "\'+\'\\\\\'+\'" },
			{ "\'", "\'+\'\\\'\'+\'" } };

	/**
	 * 用特殊的字符连接字符串
	 * @param strings  要连接的字符串数组
	 * @param spilit_sign   连接字符
	 * @return 连接字符串
	 */
	public static String stringConnect(String[] strings, String spilit_sign) {
		String str = "";
		for (int i = 0; i < strings.length; i++) {
			str += strings[i] + spilit_sign;
		}
		return str;
	}

	/**
	 * 过滤字符串里的的特殊字符
	 * @param str   要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String stringFilter(String str) {
		if(str == null || "".equals(str)){
			return "";
		}
		String[] str_arr = stringSpilit(str, "");
		for (int i = 0; i < str_arr.length; i++) {
			for (int j = 0; j < FilterChars.length; j++) {
				if (FilterChars[j][0].equals(str_arr[i]))
					str_arr[i] = FilterChars[j][1];
			}
		}
		return (stringConnect(str_arr, "")).trim();
	}

	public static boolean isNullStr(String str){
	  return str == null || "".equals(str.trim()) || "null".equals(str);
	}
	/**
	 * 过滤脚本中的特殊字符（包括回车符(\n)和换行符(\r)）
	 * @param str   要进行过滤的字符串
	 * @return 过滤后的字符串 
	 */
	public static String stringFilterScriptChar(String str) {
		String[] str_arr = stringSpilit(str, "");
		for (int i = 0; i < str_arr.length; i++) {
			for (int j = 0; j < FilterScriptChars.length; j++) {
				if (FilterScriptChars[j][0].equals(str_arr[i]))
					str_arr[i] = FilterScriptChars[j][1];
			}
		}
		return (stringConnect(str_arr, "")).trim();
	}

	/**
	 * 分割字符串
	 * @param str 要分割的字符串
	 * @param spilit_sign 字符串的分割标志
	 * @return 分割后得到的字符串数组
	 */
	public static String[] stringSpilit(String str, String spilit_sign) {
		String[] spilit_string = str.split(spilit_sign);
		if (spilit_string[0].equals("")) {
			String[] new_string = new String[spilit_string.length - 1];
			for (int i = 1; i < spilit_string.length; i++)
				new_string[i - 1] = spilit_string[i];
			return new_string;
		} else
			return spilit_string;
	}

	/**
	 * 将Date类型格式化为字符串(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return String
	 */
	public static String getDateStr(Date date) {
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * 将字符串格式化为Date类型
	 * @param strDate
	 * @return Date
	 */
	public static Date getStrDate(String strDate) {
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串格式化为另一种样式
	 * @param strDate
	 *            需要格式化的字符串
	 * @param format
	 *            格式化后的样式
	 * @return Date
	 */
	public static Date getStrDate(String strDate, String format) {
		try {
			if(strDate == null || "".equals(strDate)){
				return null;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将Date类型格式化为指定的字符串格式
	 * @param format
	 *            指定字符串格式
	 * @param date
	 *            需要格式化的日期
	 * @return String
	 */
	public static String formatDate(String format, Date date) {
		if(date == null)
			return null;
		SimpleDateFormat sdfs = new SimpleDateFormat(format);
		return sdfs.format(date);
	}


	/**
	 * 生成32位MD5，编码为GBK
	 * @param str  待生成MD5的字符串
	 * @return 生成的32位MD5字符串
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		Logger logger = Logger.getLogger(GaUtil.class);
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("GBK"));
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}

	/**
	 * 根据长度截取字符串,截取部分以...代替
	 * @param src 原字符串
	 * @param maxLen 截取长度
	 * @return
	 */
	public static String getShowStr(String src, int maxLen) {
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
	 * 将文件压缩
	 * @param oldFilePath 被压缩文件路径
	 * @param newFilePath 生成的压缩文件路径
	 * @throws IOException
	 */
	public static void rarFile(String oldFilePath, String newFilePath) throws IOException {
		File f = new File(oldFilePath);
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] buf = new byte[1024];
		int len;
		FileOutputStream fos = new FileOutputStream(newFilePath);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ZipOutputStream zos = new ZipOutputStream(bos);// 压缩包
		ZipEntry ze = new ZipEntry(f.getName());// 这是压缩包名里的文件名
		zos.putNextEntry(ze);// 写入新的 ZIP 文件条目并将流定位到条目数据的开始处
		while ((len = bis.read(buf)) != -1) {
			zos.write(buf, 0, len);
			zos.flush();
		}
		bis.close();
		zos.close();
	}

	/**
	 * 根据商品ID转化生成相应的图片存储路径 e.g. 8 -> /00/00/00/01
	 */
	public static String idToPathBase8(Long id) {
		String raw = "0000000";
		String idStr = id.toString();
		String combinePath = raw.substring(0, 8 - idStr.length()) + idStr;
		StringBuilder builder = new StringBuilder();
		builder.append("/").append(combinePath.substring(0, 2)).append("/")
				.append(combinePath.substring(2, 4)).append("/").append(
						combinePath.substring(4, 6)).append("/").append(
						combinePath.substring(6, 8));
		return builder.toString();
	}

  public static String PadRight(String targetStr,int strLength){
    int curLength = targetStr.getBytes().length;
    if(targetStr!=null && curLength>strLength){
      targetStr = targetStr.substring(0,strLength);
    }   
    String newString = "";
    int cutLength = strLength-targetStr.getBytes().length;
    for(int i=0;i<cutLength;i++)
     newString +=" ";
    return newString+targetStr;  
 }
  /**
   * 根据配置文件的路径得到某个属性的值
   * @param name 属性名
   * @param filePath 配置文件路径
   * @return
   */
  public static String getConfigPro(String name, String filePath) {
    Properties prop = new Properties(); 
    try { 
      InputStream in = GaUtil.class.getResourceAsStream(filePath);
      prop.load(in); 
      return prop.getProperty(name); 
    } catch (IOException e) {
      System.err.println("加载配置文件出错!");
      return "";
    } 
  }
  
  /**
   * 判断字符串是否为数字.
   * @param str 传入的字符串. 
   * @return true:数字. false:不是数字.
   */
  public static boolean isNumber(String str) {
    if (str == null || "".equals(str)) {
      return false;
    }
    return java.util.regex.Pattern.matches("^-?\\d+$", str.trim());
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
  
  /**
   * 产生随机数(可根据num的数字调整产生数的大小写字母,数字)
   * @param length 要产生的位数
   * @return
   */
  public static String getRandomString(int length) {  
    Random random=new Random();
    StringBuffer sb=new StringBuffer();
    for(int i=0;i<length;i++){
      int number=random.nextInt(3);
      long result=0;
      switch(number){
        case 0:
          result = Math.round(Math.random()*25+65);
          sb.append(String.valueOf((char)result));
          break;
        case 1:
          result = Math.round(Math.random()*25+97);
          sb.append(String.valueOf((char)result));
          break;
        case 2:
          sb.append(String.valueOf(new Random().nextInt(10)));
          break;
      }
    }
    return sb.toString();
  }
  
  /**
   * 判断是否为有效字符串.有效字符串是指不为null不为empty.
   */
  public static boolean isValidStr(String str) {
    return str != null && !str.isEmpty() &&  !"null".equals(str); 
  }
  
  /**
   * 获得一天结束的时间
   */
  public static Date getDayEnd(Date date) {
    if (date == null) {
      return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    return calendar.getTime();
  }
  
  /**
   * 获得一天的开始时间
   */
  public static Date getDayBegin(Date date) {
    if (date == null) {
      return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 000);
    return calendar.getTime();
  }
  
  /**
   * 得到浮点类型的价格
   * @param f
   * @return
   */
  public static float getPriceForRound(double f){
    BigDecimal   bd   =   new   BigDecimal(f);   
    bd   =   bd.setScale(2,4);   
    return bd.floatValue();
  }
  
  /**
   * 将字符串编码为MD5.
   * @param str 待编码字符串.
   * @return MD5编码后的字符串.
   */
  public static String encodeToMD5(String str) {
    MessageDigest messageDigest = null;
    try {
      messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.reset();
      messageDigest.update(str.getBytes("GBK"));
    } catch (NoSuchAlgorithmException e) {
      System.exit( -1);
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    byte[] byteArray = messageDigest.digest();
    StringBuffer md5StrBuff = new StringBuffer();
    for (int i = 0; i < byteArray.length; i++) {
      if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
        md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
      } else {
        md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
      }
    }
    return md5StrBuff.toString();
  }
  
  /**
   * 将输入的元转为分单位
   * @param money
   * @return
   */
  public static BigDecimal getInputMoney(String money) {
    if (GaUtil.isNullStr(money)) return null;
    BigDecimal moneyObj = new BigDecimal(money);
    moneyObj = moneyObj.multiply(new BigDecimal("100"));
    return moneyObj;
  }
  
  public static String getMoneyDisplay(String money) {
    if (GaUtil.isNullStr(money)) return "";  
    BigDecimal moneyObj = new BigDecimal(money);
    return getMoneyDisplay(moneyObj);
  }
  
  public static String getMoneyDisplay(Long money) {
    if (money == null) return "";    
    BigDecimal moneyObj = new BigDecimal(money.toString());
    return getMoneyDisplay(moneyObj);
  }
  
  public static String getMoneyDisplay(BigDecimal money) {
    if (money == null) return "";    
    money.setScale(2,BigDecimal.ROUND_HALF_UP);
    return String.valueOf(money.divide(new BigDecimal("100")).doubleValue());
  }
  

  
  public static Object convertData(String value,int dtType,String formatStr) {
    if (value == null) return null;
    Object valueObject = null;
    try {
      switch (dtType) {
      case GaConstant.DT_PASSWORD:
        //md5加密      
        valueObject = encodeToMD5(value);
        break;
      case GaConstant.DT_STRING:
        valueObject = value;
        break;
      case GaConstant.DT_DATETIME:
        valueObject = value;  //value;
        break;
      case GaConstant.DT_DOUBLE:
        valueObject = Double.parseDouble(value);
        break;
      case GaConstant.DT_INT:
        valueObject = Integer.parseInt(value);
        break;  
      case GaConstant.DT_MONEY:
        BigDecimal moneyObj = new BigDecimal(value);
        moneyObj = moneyObj.multiply(new BigDecimal("100"));
        valueObject = moneyObj;
        break;
      case GaConstant.DT_LONG:
        valueObject = Long.parseLong(value);
        break;  
      }
    } catch (Exception ex) {      
    }
    return valueObject;
  }
  
  public static void copyFile(String oldPath, String newPath) {
    try{
      @SuppressWarnings("unused")
      int bytesum = 0;
      int byteread = 0;
      if (GaUtil.isNullStr(newPath)) return;
      //判断文件路径是否存在，有不存在则自动创建
      File newFile = new File(newPath);
      if (!newFile.exists()) {
        File dir = new File(newFile.getParent());
        if (!dir.exists()) {
          dir.mkdirs();
        }
        dir = null;
      };
      newFile = null;
      File oldfile = new File(oldPath);
      if (oldfile.exists()) {
        InputStream inStream = new FileInputStream(oldPath);
        FileOutputStream fs = new FileOutputStream(newPath);
        byte[] buffer = new byte[1444];
        while ((byteread = inStream.read(buffer)) != -1) {
            bytesum += byteread; 
            fs.write(buffer, 0, byteread);
        }
        fs.flush();
        fs.close();
        inStream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"复制文件错误");
    }
  }
  public static void delFile(String filePathAndName) {
    try {
      String filePath = filePathAndName;
      filePath = filePath.toString();
      File myDelFile = new File(filePath);
      myDelFile.delete();
    }
    catch (Exception e) {
      throw new BizException(BizException.SYSTEM,"删除文件错误");
    }
  }
  
  public static long getLastId() {
    return DbUtils.queryLong("SELECT LAST_INSERT_ID()");
  }
  
  public static Long getLastId(String tableName){
    return DbUtils.queryLong("SELECT auto_increment FROM information_schema.`TABLES` " +
    		"WHERE TABLE_SCHEMA='lllm' AND TABLE_NAME='" + tableName +"'");
 }
  
  public static double division(long i,long j,int len){
	if(j == 0){
		return 0d;
	}
    
    java.math.BigDecimal big = new java.math.BigDecimal((double)i/j);  
    return big.setScale(len,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
  }
  public static double formatDouble(double d,int len){
    if(d == 0d){
      return 0d;
    }
    java.math.BigDecimal big = new java.math.BigDecimal(d);  
    return big.setScale(len,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
    
  }
  
  /*
   * java运算没有精度问题
   */
  public static double mulDouble(Object v1,Object v2){
    return new BigDecimal(v1.toString()).multiply(new BigDecimal(v2.toString())).doubleValue();
  }
  
  public static int mulInt(Object v1,Object v2){
    return new BigDecimal(v1.toString()).multiply(new BigDecimal(v2.toString())).intValue();
  }
  
  public static double divideDouble(Object v1,Object v2){
    return new BigDecimal(v1.toString()).divide(new BigDecimal(v2.toString())).doubleValue();
  }
  
  public static int divideInt(Object v1,Object v2){
    return new BigDecimal(v1.toString()).divide(new BigDecimal(v2.toString())).intValue();
  }
  
  public static double sumDouble(Object v1,Object v2){ 
    return new BigDecimal(v1.toString()).add(new BigDecimal(v2.toString())).doubleValue();
  } 
  
  public static double subtDouble(Object v1,Object v2){ 
    return new BigDecimal(v1.toString()).subtract(new BigDecimal(v2.toString())).doubleValue();
  }
  
  /**
   * 两数相除并保留指定位数小数
   * @param size 被除数
   * @param divisor 除数
   * @param decimalNum 保留小数位
   * @param decimalNum 小数点截截取方式: BigDecimal.ROUND_DOWN  ROUND_FLOOR ROUND_HALF_DOWN ROUND_HALF_EVEN ROUND_HALF_UP
   * @return
   */
  public static Double dividePoint(Object size, Object divisor, int pointNum, int divideType) {
    try {
      if (size == null || pointNum == 0) {
        return 0d;
      }
      return new BigDecimal(size.toString()).divide(new BigDecimal(divisor.toString()), pointNum, divideType).doubleValue();
    } catch (Exception e) {
      e.printStackTrace();
      return 0d;
    }
  }
  /**
   * 判断TIME是否在TIME2之前
   * @param time 时间
   * @param time2 要比较的时间
   * @param temp 时间格式
   * @return
   */
 
  public static boolean compareTimeStr(String time,String time2,String format){
	  if(GaUtil.isNullStr(time) || GaUtil.isNullStr(time2)){
		  throw new BizException(BizException.SYSTEM, "比较时间不能为空!");
	  }
	  Date begTime = GaUtil.getStrDate(time, format);
	  Date endTime = GaUtil.getStrDate(time2, format);
	  
	  return begTime.before(endTime);
	  
  }
  
  /**
   * 获取指定JSON结构的值,如值不存在或是解析异常则返回null
   * @param json 
   * @param paramName
   * @return
   */
  public static String getJsonValue(JSONObject json,String paramName) {
    try {
      return json.getString(paramName);
    } catch(Exception e) {
      return null;
    }
  }
  
  public static String[] getJsonValues(JSONObject json,String paramName) {
    return null;
  }
  
  /**
   * 从CLOB字段中读取内容
   * @param clob
   * @return
   * @throws SQLException
   * @throws IOException
   */
  public static String clobToString(Clob clob) {
    try {
      String reString = "";
      Reader is = clob.getCharacterStream();// 得到流
      BufferedReader br = new BufferedReader(is);
      String s = br.readLine();
      StringBuffer sb = new StringBuffer();
      while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
        sb.append(s);
        s = br.readLine();
      }
      reString = sb.toString();
      return reString;
    } catch(Exception e) {
      return "";
    }
  }
  
  public static String addUrlParam(String url,String paramName,String paramValue) {
    String linkChar = "?";
    if (url.indexOf("?") > 0) {
      linkChar = "&";
    }
    url = url + linkChar + paramName + "="+paramValue;
    return url;
  }
 
  public static JSONArray convertToJSON(List<Map<String,Object>> result) {
    return convertToJSON(result,9999);
  }
  
  public static JSONArray convertToJSON(List<Map<String,Object>> result,int maxCount) {   
    JSONArray rtnList = new JSONArray();
    int i=0;
    for(Map<String,Object> row : result) {
      JSONObject rowObj = new JSONObject();
      if (i>maxCount) {
        break;
      }
      for(String key : row.keySet()) {
        try {
          rowObj.put(key,row.get(key)); 
        } catch(Exception e) {
          
        }
      }
      i ++;
      rtnList.put(rowObj);
    }
    return rtnList;
  }
  
  public static JSONArray convertToJSONArray(List<Map<String,Object>> result) {
    JSONArray rtnList = new JSONArray();
    for(Map<String,Object> row : result) {
      JSONArray oneData = new JSONArray();
     // JSONObject rowObj = new JSONObject();
      for(String key : row.keySet()) {
        try {
          oneData.put(key);
          oneData.put(row.get(key));
        } catch(Exception e) {
          
        }
      }
      rtnList.put(oneData);
    }
    return rtnList;
  }
  
  public static String formatLongMoney(BigDecimal money) {
	  money.setScale(2);
	  DecimalFormat   df   =   new   DecimalFormat("###,###.##");
	  return df.format(money);
  }
  
  public static String HtmlText(String inputString) {
    String htmlStr = inputString; // 含html标签的字符串
    String textStr = "";
    java.util.regex.Pattern p_script;
    java.util.regex.Matcher m_script;
    java.util.regex.Pattern p_style;
    java.util.regex.Matcher m_style;
    java.util.regex.Pattern p_html;
    java.util.regex.Matcher m_html;
    try {
      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
                                                                                               //}
      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
                                                                                            // }
      String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

      p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
      m_script = p_script.matcher(htmlStr);
      htmlStr = m_script.replaceAll(""); // 过滤script标签

      p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
      m_style = p_style.matcher(htmlStr);
      htmlStr = m_style.replaceAll(""); // 过滤style标签

      p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
      m_html = p_html.matcher(htmlStr);
      htmlStr = m_html.replaceAll(""); // 过滤html标签
      /* 空格 —— */
      // p_html = Pattern.compile("\\ ", Pattern.CASE_INSENSITIVE);
      m_html = p_html.matcher(htmlStr);
      htmlStr = htmlStr.replaceAll(" ", " ");
      textStr = htmlStr;
    } catch (Exception e) {
    }
    return textStr;
  }
  

  /**
   * 
   * @param conn 数据库连接
   * @param tableName 表名
   * @param sortId 分类ID
   * @param pSortId 上级分类ID
   * @param newState 修改后的状态
   * @return
   */
  
  public static boolean updateTreeLevel(Connection conn, String tableName, Object sortId, Object pSortId, Object newState) {
    String coloumName = tableName+"_ID";
    try {
      String sortSql = "select * from "+tableName+" where " + coloumName +"=?";
      
      Map<String, Object> oldSortMap = DbUtils.queryMap(conn, sortSql, sortId);
      Map<String, Object> oldPSortMap = DbUtils.queryMap(conn, sortSql, pSortId);
      
      String oldnLevelMark = (String)oldSortMap.get("ID_PATH");//旧节点标识
      String newnLevelMar = (String)oldPSortMap.get("ID_PATH") + sortId+"/";//新节点标识
      String[] levelMarks = newnLevelMar.split("/");
      int newnLevel = levelMarks.length-2;//新节点号
      String newnLevelState = (String)oldPSortMap.get("STATE_PATH")+ newState +"/";//新节点状态
      
      //更新分类节点号  节点标识 节点状态(根据标识来确认状态)
      DbUtils.update(conn, "update "+tableName+" set NLEVEL=?,ID_PATH=?,STATE_PATH=? where " + coloumName +"=?", newnLevel,newnLevelMar,newnLevelState, sortId);
      
      List<Map<String, Object>> sortList = DbUtils.queryMapList(conn, "select * from "+tableName+" where ID_PATH like '%"+oldnLevelMark+"%' and " + coloumName +"<>" + sortId);
      String[] oldLenvelMarks = null;
      if (sortList.size() > 0) {
        oldLenvelMarks = oldnLevelMark.split("/");
      }
     
      for (Map<String, Object> map : sortList) {
        //更新所有下级分类节点号
        int nextSortId = (Integer) map.get(coloumName);
        String mark = (String) map.get("ID_PATH");
        String state = (String) map.get("STATE_PATH");
        String nextLevelMark = mark.replaceAll(oldnLevelMark, newnLevelMar);
        String[] newNextMarks = nextLevelMark.split("/");
        int levelLength = newNextMarks.length - 2;
        
        String newNowState = state.replaceFirst(state.substring(0,((oldLenvelMarks.length)*2)-1), newnLevelState);
        DbUtils.update(conn, "update "+tableName+" set NLEVEL=?,ID_PATH=?,STATE_PATH=? where " + coloumName +"=?", levelLength,nextLevelMark,newNowState, nextSortId);
      }
      
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  

  /**
   * 返回记录集对应的LIST
   * @param dbField
   * @param rs
   * @return
   */
  public static List<Map<String,Object>> getDataMapList(List<DbField> dbField,ResultSet rs) {
    try {
      if (rs == null) {
        return null;
      }
      List<Map<String,Object>> rtnList =new ArrayList<Map<String,Object>>();
      while (rs.next()) {
        rtnList.add(getRowDataMap(dbField,rs));
      }
      return rtnList;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"读取数据时异常");
    }
  }
  
  /**
   * 直接返回第一行记录MAP
   * @param dbField 
   * @param rs
   * @return
   */
  public static Map<String,Object> getDataMap(List<DbField> dbField,ResultSet rs) {
    try {
      if (rs == null || !rs.next()) {
        return null;
      }      
      return getRowDataMap(dbField,rs);
    } catch(Exception ex) {
    	ex.printStackTrace();
      throw new BizException(BizException.SYSTEM,"读取数据时异常");
    }

  }
  
  /**
   * 获取当前行的数据(调用前必须先移动记录集)
   * @param dbField
   * @param rs
   */
  public static Map<String,Object> getRowDataMap(List<DbField> dbField,ResultSet rs) {
    Map<String,Object> dataMap = new HashMap<String,Object>();

    for(DbField field : dbField) {
      String fieldCode = field.getFieldCode(false);
      try {
        if (rs.getObject(fieldCode) == null) {
          dataMap.put(fieldCode, null);
          continue;
        }
        switch (field.getDataType()) 
        {
        case GaConstant.DT_DATETIME:
          dataMap.put(fieldCode,rs.getTimestamp(fieldCode));
          break;
        case GaConstant.DT_DOUBLE:
          dataMap.put(fieldCode,rs.getDouble(fieldCode));
          break;
        case GaConstant.DT_INT:
          dataMap.put(fieldCode,rs.getInt(fieldCode));
          break;
        case GaConstant.DT_LONG:
          dataMap.put(fieldCode,rs.getLong(fieldCode));           
          break;
        case GaConstant.DT_MONEY:
          dataMap.put(fieldCode,rs.getBigDecimal(fieldCode));           
          break;
        case GaConstant.DT_PASSWORD:
        case GaConstant.DT_STRING:
        default:
          dataMap.put(fieldCode,rs.getString(fieldCode));
          break;
        }
      } catch(SQLException e) {
        //表示缺少字段定义或类型转换失败
      }
    }
    return dataMap;
  }
  
  /**
   * 处理百分比
   */
  public static String getPercentage(Double para){
    String str = "";
    if(para != null){
      para = para * 100;
      str = String.valueOf(para);
      int i = str.indexOf(".")+3 > str.length()-1 ? str.length() : str.indexOf(".")+3;
      str = str.substring(0,i)+"%";
    }
    return str;
  }
  
  /**
   * 获取系统编码值
   * @param codeId 编码ID
   * @return
   */
  public String getSyscode(Long codeId){
    CachedRowSet rs = DbUtils.query("SELECT SYS_CODE_VALUE FROM SYS_CODE WHERE SYS_CODE_ID = ?".toUpperCase(), codeId);
      try {
        if (rs.next()) {
          return rs.getString(1);
        }
      }catch (SQLException e) {
        throw new BizException(BizException.DB, "查询失败");
      }
      return null;
  }
  
  /***
   * 获取订单号码
   * @param code 单据代码
   * @param id  单据ID
   * @return
   */
  private String getOrderNum(String code, Long id){
    CachedRowSet rs = DbUtils.query("SELECT CONCAT(UPPER(?),LPAD(?,13,'0'))".toUpperCase(), code, id);
    try {
      if (rs.next()) {
        return rs.getString(1);
      }
    }catch (SQLException e) {
      throw new BizException(BizException.DB, "查询失败");
    }
    return null;
  }
  
  /***
   * 修改单号
   * @param tableName 表名
   * @param updateField 单号字段
   * @param codeId 对应系统编码 (编码表中需有对应数据)
   */
  public void updateOrderNum(String tableName, String updateField, Long codeId){
    String codeNum = getOrderNum(getSyscode(codeId), GaUtil.getLastId());
    DbUtils.update("update " + tableName + " set " + updateField + " = ?", codeNum);
  }

}

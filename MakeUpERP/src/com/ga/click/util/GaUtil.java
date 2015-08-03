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
	
	public static Set<String> haveDelTables = new HashSet<String>();//ӵ��ɾ���ֶεı��
	
	public static Set<String> haveCheckTables = new HashSet<String>();//ӵ������ֶεı��
	
	private static String[][] FilterChars = { { "<", "&lt;" }, { ">", "&gt;" },
			{ " ", "&nbsp;" }, { "\"", "&quot;" }, { "&", "&amp;" },
			{ "/", "&#47;" }, { "\\", "&#92;" }, { "\n", "<br>" } };
	
	/**
	 *  ����ͨ��javascript�ű������ύ���ַ�
	 */
	private static String[][] FilterScriptChars = { { "\n", "\'+\'\\n\'+\'" },
			{ "\r", " " }, { "\\", "\'+\'\\\\\'+\'" },
			{ "\'", "\'+\'\\\'\'+\'" } };

	/**
	 * ��������ַ������ַ���
	 * @param strings  Ҫ���ӵ��ַ�������
	 * @param spilit_sign   �����ַ�
	 * @return �����ַ���
	 */
	public static String stringConnect(String[] strings, String spilit_sign) {
		String str = "";
		for (int i = 0; i < strings.length; i++) {
			str += strings[i] + spilit_sign;
		}
		return str;
	}

	/**
	 * �����ַ�����ĵ������ַ�
	 * @param str   Ҫ���˵��ַ���
	 * @return ���˺���ַ���
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
	 * ���˽ű��е������ַ��������س���(\n)�ͻ��з�(\r)��
	 * @param str   Ҫ���й��˵��ַ���
	 * @return ���˺���ַ��� 
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
	 * �ָ��ַ���
	 * @param str Ҫ�ָ���ַ���
	 * @param spilit_sign �ַ����ķָ��־
	 * @return �ָ��õ����ַ�������
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
	 * ��Date���͸�ʽ��Ϊ�ַ���(yyyy-MM-dd HH:mm:ss)
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
	 * ���ַ�����ʽ��ΪDate����
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
	 * ���ַ�����ʽ��Ϊ��һ����ʽ
	 * @param strDate
	 *            ��Ҫ��ʽ�����ַ���
	 * @param format
	 *            ��ʽ�������ʽ
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
	 * ��Date���͸�ʽ��Ϊָ�����ַ�����ʽ
	 * @param format
	 *            ָ���ַ�����ʽ
	 * @param date
	 *            ��Ҫ��ʽ��������
	 * @return String
	 */
	public static String formatDate(String format, Date date) {
		if(date == null)
			return null;
		SimpleDateFormat sdfs = new SimpleDateFormat(format);
		return sdfs.format(date);
	}


	/**
	 * ����32λMD5������ΪGBK
	 * @param str  ������MD5���ַ���
	 * @return ���ɵ�32λMD5�ַ���
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
	 * ���ݳ��Ƚ�ȡ�ַ���,��ȡ������...����
	 * @param src ԭ�ַ���
	 * @param maxLen ��ȡ����
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
            return new String(strByte,"GBK") + "��";
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
	 * ���ļ�ѹ��
	 * @param oldFilePath ��ѹ���ļ�·��
	 * @param newFilePath ���ɵ�ѹ���ļ�·��
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
		ZipOutputStream zos = new ZipOutputStream(bos);// ѹ����
		ZipEntry ze = new ZipEntry(f.getName());// ����ѹ����������ļ���
		zos.putNextEntry(ze);// д���µ� ZIP �ļ���Ŀ��������λ����Ŀ���ݵĿ�ʼ��
		while ((len = bis.read(buf)) != -1) {
			zos.write(buf, 0, len);
			zos.flush();
		}
		bis.close();
		zos.close();
	}

	/**
	 * ������ƷIDת��������Ӧ��ͼƬ�洢·�� e.g. 8 -> /00/00/00/01
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
   * ���������ļ���·���õ�ĳ�����Ե�ֵ
   * @param name ������
   * @param filePath �����ļ�·��
   * @return
   */
  public static String getConfigPro(String name, String filePath) {
    Properties prop = new Properties(); 
    try { 
      InputStream in = GaUtil.class.getResourceAsStream(filePath);
      prop.load(in); 
      return prop.getProperty(name); 
    } catch (IOException e) {
      System.err.println("���������ļ�����!");
      return "";
    } 
  }
  
  /**
   * �ж��ַ����Ƿ�Ϊ����.
   * @param str ������ַ���. 
   * @return true:����. false:��������.
   */
  public static boolean isNumber(String str) {
    if (str == null || "".equals(str)) {
      return false;
    }
    return java.util.regex.Pattern.matches("^-?\\d+$", str.trim());
  }
  /**
   * �жϴ���ļ����Ƿ�ȫ��Ϊ����.
   * @param str ������ַ���. 
   * @return true:����. false:��������.
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
   * ���������(�ɸ���num�����ֵ����������Ĵ�Сд��ĸ,����)
   * @param length Ҫ������λ��
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
   * �ж��Ƿ�Ϊ��Ч�ַ���.��Ч�ַ�����ָ��Ϊnull��Ϊempty.
   */
  public static boolean isValidStr(String str) {
    return str != null && !str.isEmpty() &&  !"null".equals(str); 
  }
  
  /**
   * ���һ�������ʱ��
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
   * ���һ��Ŀ�ʼʱ��
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
   * �õ��������͵ļ۸�
   * @param f
   * @return
   */
  public static float getPriceForRound(double f){
    BigDecimal   bd   =   new   BigDecimal(f);   
    bd   =   bd.setScale(2,4);   
    return bd.floatValue();
  }
  
  /**
   * ���ַ�������ΪMD5.
   * @param str �������ַ���.
   * @return MD5�������ַ���.
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
   * �������ԪתΪ�ֵ�λ
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
        //md5����      
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
      //�ж��ļ�·���Ƿ���ڣ��в��������Զ�����
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
      throw new BizException(BizException.SYSTEM,"�����ļ�����");
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
      throw new BizException(BizException.SYSTEM,"ɾ���ļ�����");
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
   * java����û�о�������
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
   * �������������ָ��λ��С��
   * @param size ������
   * @param divisor ����
   * @param decimalNum ����С��λ
   * @param decimalNum С����ؽ�ȡ��ʽ: BigDecimal.ROUND_DOWN  ROUND_FLOOR ROUND_HALF_DOWN ROUND_HALF_EVEN ROUND_HALF_UP
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
   * �ж�TIME�Ƿ���TIME2֮ǰ
   * @param time ʱ��
   * @param time2 Ҫ�Ƚϵ�ʱ��
   * @param temp ʱ���ʽ
   * @return
   */
 
  public static boolean compareTimeStr(String time,String time2,String format){
	  if(GaUtil.isNullStr(time) || GaUtil.isNullStr(time2)){
		  throw new BizException(BizException.SYSTEM, "�Ƚ�ʱ�䲻��Ϊ��!");
	  }
	  Date begTime = GaUtil.getStrDate(time, format);
	  Date endTime = GaUtil.getStrDate(time2, format);
	  
	  return begTime.before(endTime);
	  
  }
  
  /**
   * ��ȡָ��JSON�ṹ��ֵ,��ֵ�����ڻ��ǽ����쳣�򷵻�null
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
   * ��CLOB�ֶ��ж�ȡ����
   * @param clob
   * @return
   * @throws SQLException
   * @throws IOException
   */
  public static String clobToString(Clob clob) {
    try {
      String reString = "";
      Reader is = clob.getCharacterStream();// �õ���
      BufferedReader br = new BufferedReader(is);
      String s = br.readLine();
      StringBuffer sb = new StringBuffer();
      while (s != null) {// ִ��ѭ�����ַ���ȫ��ȡ����ֵ��StringBuffer��StringBufferת��STRING
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
    String htmlStr = inputString; // ��html��ǩ���ַ���
    String textStr = "";
    java.util.regex.Pattern p_script;
    java.util.regex.Matcher m_script;
    java.util.regex.Pattern p_style;
    java.util.regex.Matcher m_style;
    java.util.regex.Pattern p_html;
    java.util.regex.Matcher m_html;
    try {
      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script>
                                                                                               //}
      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style>
                                                                                            // }
      String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ

      p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
      m_script = p_script.matcher(htmlStr);
      htmlStr = m_script.replaceAll(""); // ����script��ǩ

      p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
      m_style = p_style.matcher(htmlStr);
      htmlStr = m_style.replaceAll(""); // ����style��ǩ

      p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
      m_html = p_html.matcher(htmlStr);
      htmlStr = m_html.replaceAll(""); // ����html��ǩ
      /* �ո� ���� */
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
   * @param conn ���ݿ�����
   * @param tableName ����
   * @param sortId ����ID
   * @param pSortId �ϼ�����ID
   * @param newState �޸ĺ��״̬
   * @return
   */
  
  public static boolean updateTreeLevel(Connection conn, String tableName, Object sortId, Object pSortId, Object newState) {
    String coloumName = tableName+"_ID";
    try {
      String sortSql = "select * from "+tableName+" where " + coloumName +"=?";
      
      Map<String, Object> oldSortMap = DbUtils.queryMap(conn, sortSql, sortId);
      Map<String, Object> oldPSortMap = DbUtils.queryMap(conn, sortSql, pSortId);
      
      String oldnLevelMark = (String)oldSortMap.get("ID_PATH");//�ɽڵ��ʶ
      String newnLevelMar = (String)oldPSortMap.get("ID_PATH") + sortId+"/";//�½ڵ��ʶ
      String[] levelMarks = newnLevelMar.split("/");
      int newnLevel = levelMarks.length-2;//�½ڵ��
      String newnLevelState = (String)oldPSortMap.get("STATE_PATH")+ newState +"/";//�½ڵ�״̬
      
      //���·���ڵ��  �ڵ��ʶ �ڵ�״̬(���ݱ�ʶ��ȷ��״̬)
      DbUtils.update(conn, "update "+tableName+" set NLEVEL=?,ID_PATH=?,STATE_PATH=? where " + coloumName +"=?", newnLevel,newnLevelMar,newnLevelState, sortId);
      
      List<Map<String, Object>> sortList = DbUtils.queryMapList(conn, "select * from "+tableName+" where ID_PATH like '%"+oldnLevelMark+"%' and " + coloumName +"<>" + sortId);
      String[] oldLenvelMarks = null;
      if (sortList.size() > 0) {
        oldLenvelMarks = oldnLevelMark.split("/");
      }
     
      for (Map<String, Object> map : sortList) {
        //���������¼�����ڵ��
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
   * ���ؼ�¼����Ӧ��LIST
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
      throw new BizException(BizException.SYSTEM,"��ȡ����ʱ�쳣");
    }
  }
  
  /**
   * ֱ�ӷ��ص�һ�м�¼MAP
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
      throw new BizException(BizException.SYSTEM,"��ȡ����ʱ�쳣");
    }

  }
  
  /**
   * ��ȡ��ǰ�е�����(����ǰ�������ƶ���¼��)
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
        //��ʾȱ���ֶζ��������ת��ʧ��
      }
    }
    return dataMap;
  }
  
  /**
   * ����ٷֱ�
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
   * ��ȡϵͳ����ֵ
   * @param codeId ����ID
   * @return
   */
  public String getSyscode(Long codeId){
    CachedRowSet rs = DbUtils.query("SELECT SYS_CODE_VALUE FROM SYS_CODE WHERE SYS_CODE_ID = ?".toUpperCase(), codeId);
      try {
        if (rs.next()) {
          return rs.getString(1);
        }
      }catch (SQLException e) {
        throw new BizException(BizException.DB, "��ѯʧ��");
      }
      return null;
  }
  
  /***
   * ��ȡ��������
   * @param code ���ݴ���
   * @param id  ����ID
   * @return
   */
  private String getOrderNum(String code, Long id){
    CachedRowSet rs = DbUtils.query("SELECT CONCAT(UPPER(?),LPAD(?,13,'0'))".toUpperCase(), code, id);
    try {
      if (rs.next()) {
        return rs.getString(1);
      }
    }catch (SQLException e) {
      throw new BizException(BizException.DB, "��ѯʧ��");
    }
    return null;
  }
  
  /***
   * �޸ĵ���
   * @param tableName ����
   * @param updateField �����ֶ�
   * @param codeId ��Ӧϵͳ���� (����������ж�Ӧ����)
   */
  public void updateOrderNum(String tableName, String updateField, Long codeId){
    String codeNum = getOrderNum(getSyscode(codeId), GaUtil.getLastId());
    DbUtils.update("update " + tableName + " set " + updateField + " = ?", codeNum);
  }

}

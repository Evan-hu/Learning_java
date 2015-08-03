package com.company.shop.util;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 工具�?- 公用
 */

public class CommonUtil {
  
  private final static Log logger = LogFactory.getLog(CommonUtil.class);

  /** 格式化模�? => yyyy-MM-dd HH:mm:ss */
  public static String DATE_PATTERN_1 = "yyyy-MM-dd HH:mm:ss";
	public static final String REDIRECT_URL = "redirectUrl";
	public static final int MSG_CORRCT = 1;
	public static final int MSG_ERROR = 2;
	
	public static String getNotNullStr(String str) {
		if(StringUtils.isBlank(str)){
			return "";
		}
		return str;
	}
	/**
	 * 随机获取UUID字符�?无中划线)
	 * 
	 * @return UUID字符�?
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}

	public static Map<String, Object> returnInfo(int flag, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 随机获取字符�?
	 * 
	 * @param length
	 *            随机字符串长�?
	 * 
	 * @return 随机字符�?
	 */
	public static String getRandomString(int length) {
		if (length <= 0) {
			return "";
		}
		char[] randomChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's',
				'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b',
				'n', 'm' };
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			stringBuffer.append(randomChar[Math.abs(random.nextInt())
					% randomChar.length]);
		}
		return stringBuffer.toString();
	}
	
	public static String getRandomNumberString(int length) {
		if (length <= 0) {
			return "";
		}
		char[] randomChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			stringBuffer.append(randomChar[Math.abs(random.nextInt())
					% randomChar.length]);
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 根据指定长度 分隔字符�?
	 * 
	 * @param str
	 *            �?��处理的字符串
	 * @param length
	 *            分隔长度
	 * 
	 * @return 字符串集�?
	 */
	public static List<String> splitString(String str, int length) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length(); i += length) {
			int endIndex = i + length;
			if (endIndex <= str.length()) {
				list.add(str.substring(i, i + length));
			} else {
				list.add(str.substring(i, str.length() - 1));
			}
		}
		return list;
	}

	public static String encoder(String encoderStr) {
		try {
			return URLEncoder.encode(encoderStr, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encoderStr;
	}

	public static String decode(String decodeStr) {
		try {
			URLDecoder.decode(decodeStr, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decodeStr;
	}

	/**
	 * 将字符串List转化为字符串，以分隔符间�?
	 * 
	 * @param list
	 *            �?��处理的List.
	 * 
	 * @param separator
	 *            分隔�?
	 * 
	 * @return 转化后的字符�?
	 */
	public static String toString(List<String> list, String separator) {
		StringBuffer stringBuffer = new StringBuffer();
		for (String str : list) {
			stringBuffer.append(separator + str);
		}
		stringBuffer.deleteCharAt(0);
		return stringBuffer.toString();
	}

	/**
	 * 根据商品ID转化生成相应的图片存储路�?e.g. 8 -> /00/00/00/01
	 */
	public static String idToPathBase8(Long id) {
		return idToPathBase8(id.toString());
	}
	
	public static String idToPathBase8(String idStr) {
		String raw = "0000000";
		String combinePath = raw.substring(0, 8 - idStr.length()) + idStr;
		StringBuilder builder = new StringBuilder();
		builder.append(File.separatorChar).append(combinePath.substring(0, 2)).append(File.separatorChar)
				.append(combinePath.substring(2, 4)).append(File.separatorChar)
				.append(combinePath.substring(4, 6)).append(File.separatorChar)
				.append(combinePath.substring(6, 8)).append(File.separatorChar);
		return builder.toString();
	}
	
	public static boolean isNotNullStr(String str) {
		return str != null && !str.isEmpty() && !"".equals(str);
	}

	/**
	 * 获取访问IP.
	 * 
	 * @param req
	 *            HttpServletRequest.
	 * @return 访问IP字符�?
	 */
	public static String getRemoteIp(HttpServletRequest req) {
		String ip = req.getRemoteAddr();// 获得Ip
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取并设置用户匿名ID
	 * 
	 * @param req
	 * @return
	 */
	public static String getAnonymouseUserID(HttpServletRequest req,
			HttpServletResponse resp) {
		Cookie cookie = CookieUtil.getCookie(req, "anonymouseid");
		if (cookie != null) {
			return cookie.getValue();
		} else {
			String idstr = UUID.randomUUID().toString();
			CookieUtil.setCookie(req, resp, "anonymouseid", idstr,
					365 * 24 * 60 * 60);
			return idstr;
		}
	}
	
	/**
	 * 转换指定日期与当前日期的间隔
	 * @param date
	 * @return
	 */
	public static String getNearTime(Date date){
		  String showDataString=null;//显示结果
		  if(date !=null ){
				long timeLag = (System.currentTimeMillis() - date.getTime())/1000;// �?
				if(timeLag > 4*24*3600){
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					showDataString=simpleDateFormat.format(date);
				}else if(timeLag >= 24*3600){
					showDataString = (int)Math.floor(timeLag/(24*3600))+"天前";
				}else if(timeLag >= 12*3600){
					showDataString = "半天�?";
				}else if(timeLag >= 3600){
					showDataString = (int)Math.floor(timeLag/3600)+"小时�?";
				}else if(timeLag >= 30*60){
					showDataString = "半小时前";
				}else if(timeLag >= 60){
					showDataString = (int)Math.floor(timeLag/60)+"分钟�?";
				}else{
					showDataString = (int)Math.floor(timeLag)+"秒前";
				}
			}
		  return showDataString;
	  }
	
	
	/**
	 * 查询订单物流情况
	 * 
	 * @param deliveryNo
	 * @param deliveryCode
	 * @return
	 */
	public static JSONObject getDeliveryJson(String deliveryNo,
			String deliveryCode) {

		deliveryNo = deliveryNo == null ? "" : deliveryNo;
		deliveryCode = deliveryCode == null ? "" : deliveryCode;
		JSONObject returnObject = new JSONObject();
		StringBuffer sb = new StringBuffer();
		sb.append("http://www.kuaidi100.com/query?type=")
				.append(deliveryCode.trim()).append("&postid=")
				.append(deliveryNo.trim()).append("&order=asc");
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = null;
		int statusCode;
		try {
			getMethod = new GetMethod(sb.toString());
			statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			byte[] responseBody = getMethod.getResponseBody();
			JSONObject jsonObject = new JSONObject(new String(responseBody,
					"UTF-8"));
			if (!jsonObject.isNull("data")) {
				returnObject.put("message", jsonObject.getString("message"));
				returnObject.put("status", 1);
				List<JSONObject> jsons = new ArrayList<JSONObject>();
				for (int i = 0; i < jsonObject.getJSONArray("data").length(); i++) {
					if (!(jsonObject.getJSONArray("data").getJSONObject(i)
							.getString("time").length() <= 0)) {
						jsons.add(jsonObject.getJSONArray("data")
								.getJSONObject(i));
					}
				}
				Collections.reverse(jsons);
				returnObject.put("data", jsons);
			} else {
				returnObject.put("message", jsonObject.getString("message"));
				returnObject.put("status", 0);
			}
			return returnObject;
		} catch (HttpException e) {
			Logger.getLogger(CommonUtil.class.getName()).info("发生错误,请检查网络协�?");
		} catch (IOException e) {
			Logger.getLogger(CommonUtil.class.getName()).info("发生错误,请检查网�?");
		} catch (JSONException e) {
			Logger.getLogger(CommonUtil.class.getName()).info("JSON转换错误,请检�?");
		} catch (Exception e) {
			Logger.getLogger(CommonUtil.class.getName()).info(e.getMessage());
		} finally {
			getMethod.releaseConnection();
		}
		return null;
	}
	/**
   * 将date格式化为时间字符�?
   * 
   * @param date
   *          时间.
   * @param pattern
   *          格式化模�?NoahUtil类中提供�?种模�? DATE_PATTERN_1 => yyyy-MM-dd HH:mm:ss
   *          DATE_PATTERN_2 => yyyy-MM-dd DATE_PATTERN_3 => yyyy年MM月dd�?
   *          DATE_PATTERN_4 => MM月dd�?DATE_PATTERN_5 => HH小时mm分钟.
   * @return 格式化之后的时间字符�?
   */
  public static String formatDateToStr(Date date, String pattern) {
    if (date == null) {
      return "";
    }
    return new SimpleDateFormat(pattern).format(date);
  }
  /**
   * 判断字符串是否为数字.
   * 
   * @param str
   *          传入的字符串.
   * @return true:数字. false:不是数字.
   */
  public static boolean isNumber(String str) {
    if (str == null || "".equals(str)) {
      return false;
    }
    return java.util.regex.Pattern.matches("^(-?\\d+)(\\.\\d+)?$", str.trim());
  }
  
  public static String getBaiduStaticImageUrl(int width, int height, double lng, double lat, int zoom){
	  StringBuffer sb = new StringBuffer("http://api.map.baidu.com/staticimage?markerStyles=l,,0xff0000");
	  sb.append("&width=").append(width);
	  sb.append("&height=").append(height);
	  sb.append("&zoom=").append(zoom);
	  sb.append("&center=").append(lng).append(',').append(lat);
	  sb.append("&markers=").append(lng).append(',').append(lat);
	  return sb.toString();
  }
  
  /**
   * 获取会员论坛等级描述
   * @param memberBbsLvl
   * @return
   */
  public static String getMemberBbsLvlStr(Integer memberBbsLvl){
	  String[] bbsLvls = {"初级学员",	"中级学员",	"高级学员",	"正式学员",
						  "核心学员",	"铁杆学员",	"知名学员",	"人气学员",
						  "C1级牌照?",	"C2级牌照",	"B级牌照",	"A级牌照",
						  "特种牌照",	"初级教练",	"中级教练",	"高级教练"};
	  if(memberBbsLvl!=null && memberBbsLvl>=1 && memberBbsLvl<=16){
		  return bbsLvls[memberBbsLvl.intValue()-1];
	  }
	  return bbsLvls[0];
  }
  
  /**
   * 通过IP返回百度坐标 数组[0] lng [1]lat
   * @param ip
   * @return
   */
  public static double [] getLocation(String ip) {
	  double [] point = new double[2];
	  HttpClient client = new HttpClient(); 
	  HttpMethod method = new GetMethod("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&coor=bd09ll&ip=" + ip);
	  try {
		client.executeMethod(method);
		String data = method.getResponseBodyAsString();
		JSONObject pointJsonObject = new JSONObject(data).getJSONObject("content").getJSONObject("point");
		point[0] = pointJsonObject.getDouble("x");
		point[1] = pointJsonObject.getDouble("y");
		return point;
	  } catch (Exception e) {

	  } finally{
	    method.releaseConnection(); 
	  }
	  point[0] = 116.39564504d;
	  point[1] = 39.92998578d;
	  return point;
  }
  
  public static double [] getLocation(HttpServletRequest req) {
	  return getLocation(getRemoteIp(req));
  }
  
  /**
   * 获取网站根地�?
   * @param request
   * @return
   */
  public static String getWebServiceUri(HttpServletRequest request){
	  return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
  }
  
  public static boolean deleteDir(File dir, int beforeSecond) {
	if(dir == null || !dir.exists()){
		return false;
	}
	else if (dir.isDirectory()) {
        File [] children = dir.listFiles();
        for (int i=0; i< children.length; i++) {
            if (!deleteDir(children[i], beforeSecond)) 
            	return false;
        }
        return true;
    }
	else {			
		Date d = new Date(dir.lastModified());
		Calendar resultDate=Calendar.getInstance();
		logger.debug("t1�? + resultDate.getTimeInMillis()");
		resultDate.add(Calendar.SECOND, -beforeSecond);
		logger.debug("t2�? + resultDate.getTimeInMillis()");
		logger.debug("d�? + d.getTime()");
		if(resultDate.getTimeInMillis() >= d.getTime()){
			logger.debug("删除文件�? + dir.getName()");
			return dir.delete();
		}
		return true;
	}
    }
}
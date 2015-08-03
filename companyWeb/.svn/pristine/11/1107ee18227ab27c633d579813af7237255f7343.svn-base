package com.company.shop.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SmsSender {
	private static Logger logger = Logger.getLogger(SmsSender.class.getName());
	
	private final static String URL = "http://121.199.16.178/webservice/sms.php?method=Submit";
	private final static String ACCOUNT = "cf_lhxx";
	private final static String PASSWORD = "47H76c";
	private final static String AUTH_CODE_TEMP = "您的验证码是�?{authCode}。请不要把验证码泄露给其他人�?";
	
	//发�?验证�?
	public static boolean sendAuthCode(String mobile, String authCode) {
		String content = AUTH_CODE_TEMP.replace("${authCode}", authCode);
		logger.debug(content);
		return send(mobile, content);
	}
	
	public static boolean send(String mobile, String content) {	
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(URL); 
			
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
	    
		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", ACCOUNT), 
			    new NameValuePair("password", PASSWORD), 			    
			    new NameValuePair("mobile", mobile), 
			    new NameValuePair("content", content),
		};
		method.setRequestBody(data);
		try {
			client.executeMethod(method);				
			String SubmitResult =method.getResponseBodyAsString();
					
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();

			String code = root.elementText("code");//返回值为2时，表示提交成功	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");//仅当提交成功后，此字段�?才有意义
			if("2".equals(code)){
				logger.info(mobile + "短信发�?成功,smsid="+smsid);
				return true;
			}
			else{
				logger.warn(msg+",发�?手机:"+mobile);
			}
		} catch (HttpException e) {
			logger.error("请求网络", e);
		} catch (DocumentException e) {
			logger.error("解析XML", e);
		} catch (IOException e) {
			logger.error("发�?短信", e);
		} 	
		return false;
	}
}

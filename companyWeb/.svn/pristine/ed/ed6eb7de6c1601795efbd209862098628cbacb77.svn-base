package com.company.shop.util.mai;

/**  
 * 发�?邮件�?��使用的基本信�? 
 */
import java.io.UnsupportedEncodingException;
import java.util.Properties;
public class MailSenderInfo {
	private String mailServerHost = "smtp.163.com";
	private String mailServerPort = "25";
	private String fromAddress = "luhelm@163.com";
	private String userName = "luhelm@163.com";
	private String password = "ph829923";

	// 邮件接收者的地址
	private String toAddress;
	// 是否�?��身份验证
	private boolean validate = true;
	// 邮件主题
	private String subject;
	// 邮件的文本内�?
	private String content;
	// 邮件附件的文件名
	private String[] attachFileNames;
	/**
	 * 获得邮件会话属�?
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}
	public String getMailServerHost() {
		return mailServerHost;
	}
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}
	public String getMailServerPort() {
		return mailServerPort;
	}
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public String[] getAttachFileNames() {
		return attachFileNames;
	}
	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}
	public String getFromAddress() {
		try {
			return new String(new String("陆路联盟").getBytes("GB2312"),"ISO-8859-1") + "<" + this.fromAddress + ">";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String textContent) {
		this.content = textContent;
	}
}

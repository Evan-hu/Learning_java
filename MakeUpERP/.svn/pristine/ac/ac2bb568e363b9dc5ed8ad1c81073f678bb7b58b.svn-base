package com.ga.erp.util.mail;

public class MailUtil {
//	private static String UrlAddr = "";
//	private static String EmailAddr = "";
	public final static String EMAIL_HEADER = "";
	public final static String EMAIL_FOOTER = "";

	/**
	 * 邮箱发送邮件
	 * 
	 * @param email
	 *            邮箱
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件中间内容 //html代码
	 */
	public static void sendMail(String email, String subject, String content) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(email);
		mailInfo.setSubject(subject);
		mailInfo.setContent(EMAIL_HEADER + content + EMAIL_FOOTER);
		SimpleMailSender.sendHtmlMail(mailInfo);// 发送html格式
	}
	/**
	 * 发送邮箱验证码
	 * @param email
	 * @param code
	 */
	public static boolean sendAuthCode(String email, String code){
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(email);
		mailInfo.setSubject("陆路商城-密码找回");
		String content = "您的密码找回验证码：" + code +",五分钟后过期,不要泄露给他人！";
		mailInfo.setContent(EMAIL_HEADER + content + EMAIL_FOOTER);
		return SimpleMailSender.sendHtmlMail(mailInfo);
	}
}

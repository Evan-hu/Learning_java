package com.company.shop.util.mai;

public class MailUtil {
	private static String reHeUrlAddr = "http://www.lululm.com";
	private static String reHeEmailAddr = "luhelm@163.com";
	public final static String EMAIL_HEADER = "";
	public final static String EMAIL_FOOTER = "";

	/**
	 * 邮箱发�?邮件
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
		SimpleMailSender.sendHtmlMail(mailInfo);// 发�?html格式
	}
	/**
	 * 发�?邮箱验证�?
	 * @param email
	 * @param code
	 */
	public static boolean sendAuthCode(String email, String code){
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(email);
		mailInfo.setSubject("陆路联盟-密码找回");
		String content = "您的密码找回验证码：" + code +",五分钟后过期,不要泄露给他人！";
		mailInfo.setContent(EMAIL_HEADER + content + EMAIL_FOOTER);
		return SimpleMailSender.sendHtmlMail(mailInfo);
	}
}

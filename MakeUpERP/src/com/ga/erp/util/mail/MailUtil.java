package com.ga.erp.util.mail;

public class MailUtil {
//	private static String UrlAddr = "";
//	private static String EmailAddr = "";
	public final static String EMAIL_HEADER = "";
	public final static String EMAIL_FOOTER = "";

	/**
	 * ���䷢���ʼ�
	 * 
	 * @param email
	 *            ����
	 * @param subject
	 *            �ʼ�����
	 * @param content
	 *            �ʼ��м����� //html����
	 */
	public static void sendMail(String email, String subject, String content) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(email);
		mailInfo.setSubject(subject);
		mailInfo.setContent(EMAIL_HEADER + content + EMAIL_FOOTER);
		SimpleMailSender.sendHtmlMail(mailInfo);// ����html��ʽ
	}
	/**
	 * ����������֤��
	 * @param email
	 * @param code
	 */
	public static boolean sendAuthCode(String email, String code){
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(email);
		mailInfo.setSubject("½·�̳�-�����һ�");
		String content = "���������һ���֤�룺" + code +",����Ӻ����,��Ҫй¶�����ˣ�";
		mailInfo.setContent(EMAIL_HEADER + content + EMAIL_FOOTER);
		return SimpleMailSender.sendHtmlMail(mailInfo);
	}
}

package com.ga.erp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.ga.click.exception.BizException;

/**
 * ��ȡվ��������Ϣ
 * 
 * @author Administrator
 * 
 */
public class SiteConfig {
	@SuppressWarnings("unused")
	private static SiteConfig siteConfig = new SiteConfig();

	public static String SITE_ROOT = "";
	public static String FILE_ROOT = "";
	public static String LUCENE_INDEX_ROOT = "";
	static {
		// ��ȡ�����ļ���ʼ��
		InputStream fis = null;
		try {
			// ����һ��properties����
			Properties properties = new Properties();
			fis = SiteConfig.class.getResourceAsStream("/config.properties");
			properties.load(fis);
			SITE_ROOT = properties.getProperty("SITE_ROOT");
			FILE_ROOT = properties.getProperty("FILE_ROOT");
			LUCENE_INDEX_ROOT = properties.getProperty("LUCENEINDEX_ROOT");
		} catch (IOException e) {
		} catch (NumberFormatException e) {
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ex) {

				}
			}
		}
	}

	/**
	 * ����ʱ��ȡվ��ģ�����ò����л���
	 */
	private SiteConfig() {
		try {
			this.loadConfig();
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException("����ģ�������쳣");
		}
	}

	/**
	 * ����վ�����е�վ��������Ϣ
	 */
	private void loadConfig() {

	}

	/**
	 * �Ƿ���ʾ��ҳ����
	 * 
	 * @return
	 */
	public boolean isDisplaySort() {
		return false;
	}


}

package com.ga.erp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.ga.click.exception.BizException;

/**
 * 获取站点配置信息
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
		// 读取配置文件初始化
		InputStream fis = null;
		try {
			// 定义一个properties对象
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
	 * 构建时读取站点模板设置并进行缓存
	 */
	private SiteConfig() {
		try {
			this.loadConfig();
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException("加载模板配置异常");
		}
	}

	/**
	 * 加载站点所有的站点配置信息
	 */
	private void loadConfig() {

	}

	/**
	 * 是否显示首页分类
	 * 
	 * @return
	 */
	public boolean isDisplaySort() {
		return false;
	}


}

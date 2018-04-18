package com.xuanfeiyang.erp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局配置和定义类
 * 
 * @author Adam
 *
 */
public class App {
	
	private App() {}
	
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
	/**
	 * 系统用户默认密码
	 */
	public final static String DEFAULT_USER_PASSWORD = "123456";
	
	public final static String SESSION_USER_KEY = "sessionUser";
	
	public final static String TOKEN= "token";

	public final static String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public final static String PATTERN_DATE = "yyyy-MM-dd";
	public final static String TIMEZONE = "GMT+8";
	
	/**
	 * 默认卖家ID
	 */
	public final static int DEFAULT_SELLER_ID = 1;
	
	/**
	 * 预定义角色ID：卖家
	 */
	public final static int ROLE_ID_SELLER = 1;

	/**
	 * 预定义角色ID：自营卖家
	 */
	public final static int ROLE_ID_SELF_SELLER = 2;
	
	/**
	 * 预定义角色ID：VIP卖家
	 */
	public final static int ROLE_ID_VIP_SELLER = 3;
	
	/**
	 * 预定义角色ID：代理商
	 */
	public final static int ROLE_ID_AGENT = 4;

	// 配置文件的位置 ， 类路径
	private final static String configLocation = "config/config.properties";
	
	private final static Properties p = new Properties(); 
	
	public static void loadProperties() {		
		InputStream is = App.class.getClassLoader().getResourceAsStream(configLocation);
		
		if (is != null) {
			try {
				p.load(is);
			} catch (IOException e) {
				logger.error("加载配置文件出错", e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	
	static {
		loadProperties();
	}	
	
	/**
	 * 查找配置
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		if (key == null)
			return "";
		
		return p.getProperty(key);
	}
	
	/**
	 * 查找配置
	 * @param key
	 * @param defaultValue 指定如果找不到配置时返回的默认值
	 * @return
	 */
	public static String getConfig(String key, String defaultValue) {
		if (key == null)
			return defaultValue;
		
		return p.getProperty(key, defaultValue);
	}
	
	/**
	 * 查找配置，并转为 int 
	 * @param key
	 * @return 配置的int值，default 0。
	 */
	public static int getConfigInt(String key) {
		String value = App.getConfig(key);
		return NumberUtils.toInt(value);
	}
	
	/**
	 * 查找配置，并转为 long 
	 * @param key
	 * @return 配置的int值，default 0。
	 */
	public static long getConfigLong(String key) {
		String value = App.getConfig(key);
		return NumberUtils.toLong(value);
	}
}
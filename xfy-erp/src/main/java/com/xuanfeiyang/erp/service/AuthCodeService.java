package com.xuanfeiyang.erp.service;

/**
 * 验证码相关逻辑类
 * 
 * @author Adam
 *
 */
public interface AuthCodeService {
	
	/**
	 * 创建验证码
	 * @param key
	 * @return
	 */
	public String createCode(String key);
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	public String getCode(String key);
	
	/**
	 * 删除以使用的
	 * @param key
	 */
	public void invalideCode(String key);
	
	/**
	 * 验证
	 * @param key
	 * @param authCode
	 * @return
	 */
	public boolean check(String key, String authCode);
	
}

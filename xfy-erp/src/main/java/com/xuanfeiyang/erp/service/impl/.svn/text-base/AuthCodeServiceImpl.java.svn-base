package com.xuanfeiyang.erp.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xuanfeiyang.erp.service.AuthCodeService;

@Service
public class AuthCodeServiceImpl implements AuthCodeService {
	
	private static Logger logger = LoggerFactory.getLogger(AuthCodeServiceImpl.class);
	
	/**
	 * 缓存验证码信息，10分钟
	 */
	private final static Cache<String, String> codeCache = CacheBuilder.newBuilder()
			.expireAfterAccess(10, TimeUnit.MINUTES)
			.build();

	@Override
	public String createCode(String key) {
		if (key == null) {
			return null;
		}
		
		String code = this.createRandomCode();
		codeCache.invalidate(key);
		codeCache.put(key, code);
		
		if (logger.isDebugEnabled()) {
			logger.debug("验证码缓存增加, key: {}, code: {}", key, code);
		}
		
		return code;
	}
	
	// 生成4位随机数
	private String createRandomCode() {
		Double tmpDouble = (Math.random() * 10000) + 10000;
		return (String.valueOf(tmpDouble.intValue())).substring(1);
	}

	@Override
	public String getCode(String key) {
		return codeCache.getIfPresent(key);
	}

	@Override
	public void invalideCode(String key) {
		codeCache.invalidate(key);
		if (logger.isDebugEnabled()) {
			logger.debug("验证码缓存清除： {}", key);
		}
	}

	@Override
	public boolean check(String key, String authCode) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(authCode)) {
			return false;
		}
		
		return authCode.equals(this.getCode(key));
	}
	
	
}

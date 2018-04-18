package com.xuanfeiyang.erp.service.express.sf;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordEncryption {
	private static final Logger logger = LoggerFactory
			.getLogger(PasswordEncryption.class);
	/**
	 * 加密
	 * 
	 * @param src
	 *            源数据
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(src);
			byte b[] = md.digest();
			return b;
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 密码加密
	 * 
	 * @param Password
	 * @return EncryptPassword
	 * @throws Exception
	 */
	public final static String encrypt(String password) {

		try {
			return byte2hex(encrypt(password.getBytes()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {

		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

}

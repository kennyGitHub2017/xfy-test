package com.xuanfeiyang.erp.service.express.winit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	/**
	 * 取得字符串的摘要信息
	 * @param input
	 * @return
	 */
	public static String getMd5(String input){
		byte[] out = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(input.getBytes());
			out = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return toHexString(out);
	}
	
	/**
	 * 转换成16进制
	 * @param out
	 * @return
	 */
	private static String toHexString(byte[] out) {
		StringBuffer buf = new StringBuffer();
		byte[] arrayOfByte = out; 
		int j = out.length; 
		for (int i = 0; i < j; i++) { 
			byte b = arrayOfByte[i];
			buf.append(String.format("%02X", new Object[] { Byte.valueOf(b) }));
	    }
		return buf.toString();
	}
}

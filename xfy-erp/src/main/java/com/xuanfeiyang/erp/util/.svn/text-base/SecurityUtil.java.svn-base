package com.xuanfeiyang.erp.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 安全工具类，包含授权签名所需的hmac_sha1算法，主要用于计算签名
 */
public final class SecurityUtil {

	public static final String HMAC_SHA1 = "HmacSHA1";
	
	public static final String HMAC_SHA256 = "HmacSHA256";
	
	
	public static String hmacDigest(String msg, byte []key) {
	    String digest = null;
	    try {
	      SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA256);
	      Mac mac = Mac.getInstance(HMAC_SHA256);
	      mac.init(signingKey);
	      final byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));
	      StringBuffer hash = new StringBuffer();
	      for (int i = 0; i < bytes.length; i++) {
	        String hex = Integer.toHexString(0xFF & bytes[i]);
	        if (hex.length() == 1) {
	          hash.append('0');
	        }
	        hash.append(hex);
	      }
	      digest = hash.toString();
	    } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    } catch (InvalidKeyException e) {
	      e.printStackTrace();
	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
	    return digest;
	  }
	
	
	public static byte[] hmacSha1(byte[] data, byte[] key, int offset, int len) {
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = null;
		try {
			mac = Mac.getInstance(HMAC_SHA1);
			mac.init(signingKey);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		mac.update(data, offset, len);
		return mac.doFinal();
	}

	public static byte[] hmacSha1(byte[][] datas, byte[] key) {
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = null;
		try {
			mac = Mac.getInstance(HMAC_SHA1);
			mac.init(signingKey);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		for (byte[] data : datas) {
			mac.update(data);
		}
		return mac.doFinal();
	}

	public static byte[] hmacSha1(String[] datas, byte[] key) {
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = null;
		try {
			mac = Mac.getInstance(HMAC_SHA1);
			mac.init(signingKey);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		try {
			for (String data : datas) {
				mac.update(data.getBytes(StringUtil.CHARSET_NAME_UTF8));
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return mac.doFinal();
	}

	public static String hmacSha1ToHexStr(byte[] data, byte[] key, int offset,
			int len) {
		byte[] rawHmac = hmacSha1(data, key, offset, len);
		return StringUtil.encodeHexStr(rawHmac);
	}

	public static String hmacSha1ToHexStr(byte[] data, String key, int offset,
			int len) {
		try {
			return hmacSha1ToHexStr(data,
					key.getBytes(StringUtil.CHARSET_NAME_UTF8), offset, len);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String hmacSha1ToHexStr(String str, String key) {
		try {
			byte[] data = str.getBytes(StringUtil.CHARSET_NAME_UTF8);
			return hmacSha1ToHexStr(data,
					key.getBytes(StringUtil.CHARSET_NAME_UTF8), 0, data.length);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private SecurityUtil() {
	}
}

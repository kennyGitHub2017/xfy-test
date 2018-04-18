package com.xuanfeiyang.erp.service.express.winit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;


public class SignUtil {
	
	/**
	 * 按字段名字母顺序排列
	 * 对参数进行MD5加密取得签名
	 * @param requestMsg
	 * @param token
	 * @return
	 */
	private static Logger logger = LoggerFactory.getLogger(SignUtil.class);
	public static String getSign(RequestMsg requestMsg,String token) {
		StringBuffer buf = new StringBuffer();
		buf.append(token);
		buf.append("action").append(requestMsg.getAction());
		buf.append("app_key").append(requestMsg.getApp_key());
		buf.append("data").append(requestMsg.getData() == null?"":new Gson().toJson(requestMsg.getData()).toString());
		buf.append("format").append(requestMsg.getFormat() == null?"":requestMsg.getFormat());
		buf.append("platform").append(requestMsg.getPlatform() == null?"":requestMsg.getPlatform());
		buf.append("sign_method").append(requestMsg.getSign_method() == null?"":requestMsg.getSign_method());
		buf.append("timestamp").append(requestMsg.getTimestamp() == null?"":requestMsg.getTimestamp());
		buf.append("version").append(requestMsg.getVersion() == null?"":requestMsg.getVersion());
		buf.append(token);
		logger.info("SignUtil buf:{}",buf.toString());
		return MD5Util.getMd5(decode(buf.toString()));
	}
	
	 private static String decode(String unicodeStr) {
		    if (unicodeStr == null) {
		      return null;
		    }
		    StringBuffer retBuf = new StringBuffer();
		    int maxLoop = unicodeStr.length();
		    for (int i = 0; i < maxLoop; i++) {
		      if (unicodeStr.charAt(i) == '\\') {
		        if ((i < maxLoop - 5) && (
		          (unicodeStr.charAt(i + 1) == 'u') || 
		          (unicodeStr.charAt(i + 1) == 'U')))
		          try {
		            retBuf.append((char)Integer.parseInt(
		              unicodeStr.substring(i + 2, i + 6), 16));
		            i += 5;
		          } catch (NumberFormatException localNumberFormatException) {
		            retBuf.append(unicodeStr.charAt(i));
		          }
		        else
		          retBuf.append(unicodeStr.charAt(i));
		      }
		      else retBuf.append(unicodeStr.charAt(i));
		    }

		    return retBuf.toString();
		  }
}

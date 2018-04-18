package com.xuanfeiyang.erp.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Smt授权服务类，主要提供了所有授权服务都会用到的获取授权令牌的功能
 */
public class SmtAuthService {
	
	private static HttpsRequest req = new HttpsRequest();
	
	private static final Logger logger = LoggerFactory
			.getLogger(SmtAuthService.class);
	/**
	 * 通过临时令牌换取授权令牌
	 * 
	 * @param host
	 *            请求的主机名，包括域名和端口
	 * @param params
	 *            请求参数，必填client_id、client_secret、redirect_uri和code，scope和view可选
	 * @param needRefreshToken
	 *            是否需要返回refreshToken
	 * @return getToken请求的json串
	 */
	public static String getToken(String url, Map<String, String> params,Boolean needRefreshToken) throws Exception{
		if (params != null) {
			if (params.get("client_id") == null
					|| params.get("client_secret") == null
					|| params.get("redirect_uri") == null
					|| params.get("code") == null) {
				logger.debug("params is invalid, lack neccessary key!");
				return null;
			}
			params.put("grant_type", "authorization_code");
			params.put("need_refresh_token", Boolean.toString(needRefreshToken));
			
			return req.postReturnString(url, params,null);
		}
		return null;
	}
	
	/**
     * 通过长时令牌换取授权令牌
     * @param host 请求的主机名，包括域名和端口
     * @param params 请求参数，必填client_id、client_secret、redirect_uri和refresh_token，scope和view可选
     * @return
     */
    public static String refreshToken(String url, Map<String, String> params) throws Exception{
    	if (params != null) {
			if (params.get("client_id") == null
					|| params.get("client_secret") == null
					|| params.get("refresh_token") == null) {
				logger.debug("params is invalid, lack neccessary key!");
				return null;
			}
			params.put("grant_type", "refresh_token");
			return req.postReturnString(url, params,null);
		}
		return null;
    }
    
    
    public static String getClientAuthUrl(String host, Map<String, String> params, String appSecretKey){
        if(params == null){
            return null;
        }
        if(params.get("client_id") == null || params.get("site") == null
                || params.get("redirect_uri") == null){
        	logger.debug("params is invalid, lack neccessary key!");
            return null;
        }
        String signature = CommonUtil.signatureWithParamsOnly(params, appSecretKey);
        params.put("_aop_signature", signature);
        return CommonUtil.getWholeUrl(host, params);
    }

}

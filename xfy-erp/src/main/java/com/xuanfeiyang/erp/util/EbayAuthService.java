package com.xuanfeiyang.erp.util;

import java.util.Map;

public class EbayAuthService {
	private static HttpsRequest req = new HttpsRequest();
	
	/**
	 * 获得ebay的SessionId
	 * @param host
	 * @param params
	 * @return
	 */
	public static String getSessionId(String host,String content,Map<String,String> headers) throws Exception{
		//缺少必要的头信息
		if (null==headers || headers.get(Constants.EBAY.GETSESSION_APIHEADER_DEVNAME)==null || headers.get(Constants.EBAY.GETSESSION_APIHEADER__APPNAME)==null
				|| headers.get(Constants.EBAY.GETSESSION_APIHEADER_APPCERT)==null || headers.get(Constants.EBAY.GETSESSION_APIHEADER__APPNAME)==null
				|| headers.get(Constants.EBAY.GETSESSION_APIHEADER_CLEVEL)==null || headers.get(Constants.EBAY.GETSESSION_APIHEADER_SITEID)==null
				|| headers.get(Constants.EBAY.GETSESSION_APIHEADER_CALLNAME)==null){
			return null;
		}
		return req.postReturnString(host,content, headers);
	}
	public static String getClientAuthUrl(String host, Map<String, String> params){
        if(params == null){
            return null;
        }
        if(params.get("RuName") == null || params.get("SessID") == null){
            return null;
        }
        return CommonUtil.getWholeUrl(host, params);
    }
	
	/**
	 * 获取ebay的token
	 * @param host
	 * @param content
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String getToken(String host,String content,Map<String,String> headers) throws Exception{
		//缺少必要的头信息
				if (null==headers || headers.get(Constants.EBAY.GETSESSION_APIHEADER_DEVNAME)==null || headers.get(Constants.EBAY.GETSESSION_APIHEADER__APPNAME)==null
						|| headers.get(Constants.EBAY.GETSESSION_APIHEADER_APPCERT)==null || headers.get(Constants.EBAY.GETSESSION_APIHEADER__APPNAME)==null
						|| headers.get(Constants.EBAY.GETSESSION_APIHEADER_CLEVEL)==null || headers.get(Constants.EBAY.GETSESSION_APIHEADER_SITEID)==null
						|| headers.get(Constants.EBAY.GETSESSION_APIHEADER_CALLNAME)==null){
					return null;
				}
				return req.postReturnString(host,content, headers);
	}
}

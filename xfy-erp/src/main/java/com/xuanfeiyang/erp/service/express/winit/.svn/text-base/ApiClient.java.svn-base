package com.xuanfeiyang.erp.service.express.winit;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class ApiClient {
	
	public static final String CHARSET = "UTF-8";
	private static Logger logger = LoggerFactory.getLogger(ApiClient.class);
	/**
	 * 
	 * @param requestMsg
	 * @param url
	 * @param app_secret
	 * @return
	 */
	public String post(RequestMsg requestMsg,String url,String token){
		
		String sign = SignUtil.getSign(requestMsg,token);
		requestMsg.setSign(sign);
		logger.info("sign:{}",sign);
		return post(requestMsg,url);
	}
	
	/**
	 * 
	 * @param requestMsg
	 * @param url
	 * @return
	 */
	public String post(RequestMsg requestMsg,String url){
		String result = null;
		HttpPost post = null;
		try {
			String postData = new Gson().toJson(requestMsg);
			HttpEntity entity = new StringEntity(postData, CHARSET);
			
			post = new HttpPost(url);
			post.setEntity(entity);
			post.setHeader("Content-Type","application/xml;charset=UTF-8");
			
			CloseableHttpClient client = HttpClients.createDefault();
			HttpResponse httpResponse = client.execute(post);
			
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(post != null){
				post.abort();
			}
		}
		return result;
	}
}

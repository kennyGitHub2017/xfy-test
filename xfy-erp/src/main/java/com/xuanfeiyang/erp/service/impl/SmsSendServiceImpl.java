package com.xuanfeiyang.erp.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.service.SmsSendService;

@Service
public class SmsSendServiceImpl implements SmsSendService {
	
	private static Logger logger = LoggerFactory.getLogger(SmsSendServiceImpl.class);

	@Override
	public boolean send(String mobile, String content) {
		
		if (mobile == null || content == null) {
			return false;
		}
		
		logger.info("发送短信手机：{}, 内容：{}", mobile, content);
		
//		String corpId = App.getConfig("sms.corp.id"); // 账户名
//		String pwd = App.getConfig("sms.corp.pwd"); // 密码
		
		String encodedContent = content;
		try {
			encodedContent = URLEncoder.encode(
					content.replaceAll("<br/>", " "), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("短信内容转码出错", e);
			return false;
		}

//		URL url = null;
//		// 发送内容
//		try {
//			url = new URL(String.format(App.getConfig("sms.url.pattern"), corpId, pwd, mobile, encodedContent));
//		} catch (MalformedURLException e) {
//			logger.error("短信URL处理出错", e);
//			return false;
//		}
//		
//		BufferedReader in = null;
//		long inputLine = 0;
//		try {
//			in = new BufferedReader(new InputStreamReader(url.openStream()));
//			inputLine = new Long(in.readLine()).longValue();
//		} catch (Exception e) {
//			logger.error("网络异常,发送短信失败！", e);
//			inputLine = -2;
//		}
//		
//		logger.info("发送短信结果：{}, 手机：{}, 内容：{}", inputLine, mobile, content);
//		return inputLine > 0;
		
		String custCode = App.getConfig("sms.corp.id");
		String password = App.getConfig("sms.corp.pwd");
		String sign = DigestUtils.md5Hex(encodedContent + password);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(4);
		params.add(new BasicNameValuePair("cust_code", custCode));
		params.add(new BasicNameValuePair("content", content));
		params.add(new BasicNameValuePair("destMobiles", mobile));
		params.add(new BasicNameValuePair("sign", sign));

		HttpEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("编码出错", e);
			return false;
		}

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(App.getConfig("sms.url.pattern"));
		post.setEntity( entity );
		HttpResponse httpResponse = null;
		String response = null;
		try {
			httpResponse = httpClient.execute(post);
			HttpEntity re = httpResponse.getEntity();
			response = re != null ? EntityUtils.toString(re) : null;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		
		logger.info("发送短信结果 ===> {}", response);
		
		try {
			response = URLDecoder.decode(response, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("短信结果转码异常", e);
			return false;
		}

		logger.info("发送短信结果 ===> {}", response);
		
		return response.startsWith("SUCCESS");
	}

	public static void main(String[] args) {
//		String msg = "您好，当前验证码%s，请及时注册，如非本人操作，请忽略【CSS系统】";
//		String msg = "您好，您的登录密码已经重置为%s,请用新密码登录。http://chinasalestore.com【CSS系统】";
//		String msg = "尊敬的用户，您的资料已经审核通过，现在就开始体验吧。网址：http://chinasalestore.com【CSS系统】";
//		String msg = "尊敬的用户，您的资料未被审核通过，请重新提交资料。网址：http://chinasalestore.com【CSS系统】";
//		String msg = "尊敬的用户，请及时提交审核资料。http://chinasalestore.com【CSS系统】";
		String msg = "尊敬的用户,您的动态密码为%s。http://chinasalestore.com【CSS系统】";
		boolean result = new SmsSendServiceImpl().send("13424387624", msg);
		System.out.println(result);
	}
}

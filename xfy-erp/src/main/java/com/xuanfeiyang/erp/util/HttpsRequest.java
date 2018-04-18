package com.xuanfeiyang.erp.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsRequest {

	public HttpsRequest() {
	}

	public HttpsRequest(String charset) {
		this.charset = charset;
	}

	private String charset = "utf-8";

	static class TrustAnyHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	}

	static class TrustAnyTrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	/**
	 * 发送post请求
	 * @param url
	 * @param xml 要发送的内容 , 与para 不能共存
	 * @param para 要发送的内容集合,不xml 不能共存
	 * @param headers 要写入的https头信息
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	private byte[] postReturnStream(String url,String xml,Map<String,String> para,Map<String,String> headers)
			throws NoSuchAlgorithmException, KeyManagementException,
			IOException {
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
				new java.security.SecureRandom());
		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);  
		//追加https头部信息
		if (null!=headers && headers.size()>0){
			Set<Entry<String,String>> set = headers.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()){
				Entry<String,String> entry = it.next();
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		if (null!=xml && xml.trim().length()>0){
			  out.write(xml.getBytes(charset));
		}
		else if(para!=null){
			StringBuffer sbf= new StringBuffer();
			Set<Map.Entry<String, String>> set = para.entrySet();
			Iterator<Map.Entry<String, String>> it = set.iterator();
	        while(it.hasNext()){
	            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
	            String paramStr = entry.getKey().concat("=" ).concat(entry.getValue());
	            sbf.append(paramStr+"&");
	        }
	        String paraStr = sbf.toString();
	        out.write(paraStr.getBytes(charset));
		}
		//刷新、关闭
		out.flush();
		out.close();
		InputStream is = conn.getInputStream();
		if (is != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			is.close();
			return outStream.toByteArray();
		}
		return null;
	}
	
	

	public String postReturnString(String url, Map<String,String> para,Map<String,String> header) throws Exception {
		return new String(postReturnStream(url, null,para,header), charset);
	}
	
	public String postReturnString(String url,String str,Map<String,String> header) throws Exception{
		return new String(postReturnStream(url,str,null,header),charset);
	}
}

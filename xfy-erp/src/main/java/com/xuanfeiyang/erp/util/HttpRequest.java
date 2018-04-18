package com.xuanfeiyang.erp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * bernard
 */
public class HttpRequest {
	private String charset = "utf-8";
	private Integer connectTimeout = 10;
	private Integer socketTimeout = 10;
	private String proxyHost = null;
	private Integer proxyPort = null;
	
	private static final Logger logger = LoggerFactory
			.getLogger(HttpRequest.class);

	public HttpRequest(){}
	
	public HttpRequest(String proxyHost, Integer proxyPort,String charset,Integer socketTimeout,Integer connectTimeout) {
		super();
		this.charset = charset;
		this.connectTimeout = connectTimeout;
		this.socketTimeout = socketTimeout;
		this.proxyHost = proxyHost;
		this.proxyPort = proxyPort;
	}



	public String doGet(String url) throws Exception {
		URL localURL = new URL(url);
		URLConnection connection = localURL.openConnection();
		renderRequest(connection);
		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
		httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		httpURLConnection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;

		if (httpURLConnection.getResponseCode() >= 300) {
			throw new Exception(
					"HTTP Request is not success, Response code is "
							+ httpURLConnection.getResponseCode());
		}

		try {
			inputStream = httpURLConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputStreamReader);

			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}

		} finally {

			if (reader != null) {
				reader.close();
			}

			if (inputStreamReader != null) {
				inputStreamReader.close();
			}

			if (inputStream != null) {
				inputStream.close();
			}

		}

		return resultBuffer.toString();
	}
	
	private String getParameterStr(Map<String,String> parameterMap){
		/* Translate parameter map to parameter date string */
		StringBuffer parameterBuffer = new StringBuffer();
		if (parameterMap != null) {
			Iterator<String> iterator = parameterMap.keySet().iterator();
			String key = null;
			Object value = null;
			while (iterator.hasNext()) {
				key = iterator.next();
				if (parameterMap.get(key) != null) {
					value = parameterMap.get(key);
				}
				if (value == null) {
					continue;
				}
				parameterBuffer.append(key).append("=").append(value);
				if (iterator.hasNext()) {
					parameterBuffer.append("&");
				}
			}
		}
		return parameterBuffer.toString();
	}
	
	public InputStream doPostWIthStream(String url,String paraStr,Map<String,String> headerMap) throws Exception{
		logger.debug("POST parameter : " + paraStr);
		URL localURL = new URL(url);
		URLConnection connection = openConnection(localURL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("POST");
		if (null!=headerMap && headerMap.size()>0){
			for(Map.Entry<String, String> entry:headerMap.entrySet()){
				httpURLConnection.setRequestProperty(entry.getKey(),entry.getValue());
			}
		}else{
			httpURLConnection.setRequestProperty("Accept-Charset", charset);
			httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			httpURLConnection.setRequestProperty("Content-Length",String.valueOf(paraStr.length()));
		}
		OutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		try {
			outputStream = httpURLConnection.getOutputStream();
			outputStreamWriter = new OutputStreamWriter(outputStream);

			outputStreamWriter.write(paraStr);
			outputStreamWriter.flush();
			logger.debug(String.valueOf(httpURLConnection.getResponseCode()));
//			if (httpURLConnection.getResponseCode() >= 300) {
//				throw new Exception(
//						"HTTP Request is not success, Response code is "
//								+ httpURLConnection.getResponseCode());
//			}
			try {
				return httpURLConnection.getInputStream();
			} catch (IOException e) {
				InputStream is = httpURLConnection.getErrorStream();
				if (is == null) {
					throw e;
				}
				
				return is;
			}
			
		} finally {
			if (outputStreamWriter != null) {
				outputStreamWriter.close();
			}

			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public InputStream doPostWithStream(String url, Map<String, String> parameterMap,Map<String,String> headerMap)
			throws Exception {
		String paraStr = getParameterStr(parameterMap);
		return doPostWIthStream(url,paraStr,headerMap);
	}
	
	public String doPostWithString(String url, Map<String, String> parameterMap,Map<String,String> headerMap) throws Exception{
		InputStream is = null;
		BufferedReader reader = null;
		String tempLine;
		StringBuffer resultBuffer = new StringBuffer();
		try{
			is = doPostWithStream(url,parameterMap,headerMap);
			reader = new BufferedReader(new InputStreamReader(is));
			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
		}finally{
			if (reader!=null){
				reader.close();
			}
			if (is!=null){
				is.close();
			}
		}
		return resultBuffer.toString();
	}

	private URLConnection openConnection(URL localURL) throws IOException {
		URLConnection connection;
		if (proxyHost != null && proxyPort != null) {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					proxyHost, proxyPort));
			connection = localURL.openConnection(proxy);
		} else {
			connection = localURL.openConnection();
		}
		return connection;
	}

	private void renderRequest(URLConnection connection) {

		if (connectTimeout != null) {
			connection.setConnectTimeout(connectTimeout);
		}

		if (socketTimeout != null) {
			connection.setReadTimeout(socketTimeout);
		}
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public Integer getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}
	
	/*
	public static void main(String []args) throws Exception{
		HttpRequest util =new HttpRequest();
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "text/xml;charset=utf-8");
		headerMap.put("Authorization","basic MzAxNjEwOjM0NzUwOTg4");
		String content = "<string>RG084267884CN</string>";
		headerMap.put("Content-Length",String.valueOf(content.length()));
		String url="http://online.yw56.com.cn/service/Users/301610/Expresses/A4LCLabel";
		InputStream is = util.doPostWIthStream(url, content, headerMap);
		
		Reader inputStreamReader = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String tempLine;
		StringBuffer resultBuffer = new StringBuffer();
		while ((tempLine = reader.readLine()) != null) {
			resultBuffer.append(tempLine+"\n");
		}
		System.out.println(resultBuffer.toString());
		
		is.close();
	}
   */
}

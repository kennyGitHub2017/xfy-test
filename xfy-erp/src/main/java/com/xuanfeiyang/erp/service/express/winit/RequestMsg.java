package com.xuanfeiyang.erp.service.express.winit;

public class RequestMsg {
	
	/** 请求方法 */
	private String action;
	/** 用户名 */
	private String app_key;
	/** 时间  */
	private String timestamp;
	/** 版本号  */
	private String version = "1.0";
	/** 签名  */
	private String sign;
	/** 签名方式  */
	private String sign_method = "md5";
	/** 返回格式  */
	private String format = "json";
	/** 平台  */
	private String platform;
	/** 语言  */
	private String language = "zh_CN";
	/** 业务参数 json string */
	private Object data;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_method() {
		return sign_method;
	}
	public void setSign_method(String sign_method) {
		this.sign_method = sign_method;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}

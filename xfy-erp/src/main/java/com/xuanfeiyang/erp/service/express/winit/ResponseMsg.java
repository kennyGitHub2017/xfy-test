package com.xuanfeiyang.erp.service.express.winit;

/**
 * 返回报文对象
 * @author Administrator
 *
 */
public class ResponseMsg {
	
	/** 返回码  */
	private int code = 0;
	/** 返回消息  */
	private String msg;
	/** 返回结果集  */
	private Object data = "";
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}

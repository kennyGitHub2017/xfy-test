package com.xuanfeiyang.erp.service;

/**
 * 数据重复异常
 * 
 * @author Adam
 *
 */
@SuppressWarnings("serial")
public class DuplicateDataExcepption extends Exception {

	// 重复的数据信息
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 
	 * @param data 重复的数据
	 */
	public DuplicateDataExcepption(Object data) {
		super();
		this.data = data;
	}

	public DuplicateDataExcepption(String message) {
		super(message);
	}
	
	public DuplicateDataExcepption(String message, Object data) {
		super(message);
		this.data = data;
	}
}

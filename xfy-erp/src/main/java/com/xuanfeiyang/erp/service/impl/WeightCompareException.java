package com.xuanfeiyang.erp.service.impl;

public class WeightCompareException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public WeightCompareException(){
		
	}
	
	public WeightCompareException(String trackNumber, String message) {
		super();
		this.trackNumber = trackNumber;
		this.message = message;
	}
	
	public WeightCompareException(String message) {
		super();
		this.message = message;
	}

	public String getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	private String trackNumber;
	private String message;
	
}

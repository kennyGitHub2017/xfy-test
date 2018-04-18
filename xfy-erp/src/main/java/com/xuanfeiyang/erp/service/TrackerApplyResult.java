package com.xuanfeiyang.erp.service;

public class TrackerApplyResult {
	
	private Integer orderId;
	
	private String packageId;
	
	private boolean success;
	
	// 跟踪号
	private String trackNumber;
	
	private String message;
	
	// 跟踪号参考字段
	private String trackNumberRef;

	public String getTrackNumberRef() {
		return trackNumberRef;
	}

	public void setTrackNumberRef(String trackNumberRef) {
		this.trackNumberRef = trackNumberRef;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TrackerApplyResult [orderId=");
		builder.append(orderId);
		builder.append(", packageId=");
		builder.append(packageId);
		builder.append(", success=");
		builder.append(success);
		builder.append(", trackNumber=");
		builder.append(trackNumber);
		builder.append(", message=");
		builder.append(message);
		builder.append(", trackNumberRef=");
		builder.append(trackNumberRef);
		builder.append("]");
		return builder.toString();
	}
}

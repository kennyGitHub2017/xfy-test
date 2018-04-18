package com.xuanfeiyang.erp.service.express.yanwen;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 燕文XML映射对象
 * 
 * @author Administrator
 *
 */
@XmlRootElement(name = "Response")
public class Response implements Serializable {
	private static final long serialVersionUID = 2735624837986211617L;

	private String userid;
	private String operation;
	private boolean success;
	private String reason;
	private String reasonMessage;

	@XmlElement(name = "Userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@XmlElement(name = "Operation")
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@XmlElement(name = "Success")
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@XmlElement(name = "Reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@XmlElement(name = "ReasonMessage")
	public String getReasonMessage() {
		return reasonMessage;
	}

	public void setReasonMessage(String reasonMessage) {
		this.reasonMessage = reasonMessage;
	}

	@Override
	public String toString() {
		return "Response [userid=" + userid + ", operation=" + operation + ", success=" + success + ", reason=" + reason
				+ ", reasonMessage=" + reasonMessage + "]";
	}

}

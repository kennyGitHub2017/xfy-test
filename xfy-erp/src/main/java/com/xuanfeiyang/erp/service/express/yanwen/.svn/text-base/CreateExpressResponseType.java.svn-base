package com.xuanfeiyang.erp.service.express.yanwen;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 燕文CreateExpress(创建订单)的响应对象
 * 
 * @author Administrator
 *
 */
@XmlRootElement(name = "CreateExpressResponseType")
public class CreateExpressResponseType implements Serializable {
	private static final long serialVersionUID = 1538797626540181074L;

	private boolean callSuccess;
	private CreatedExpress createdExpress;
	private Response response;

	@XmlElement(name = "CallSuccess")
	public boolean isCallSuccess() {
		return callSuccess;
	}

	public void setCallSuccess(boolean callSuccess) {
		this.callSuccess = callSuccess;
	}

	@XmlElement(name = "CreatedExpress")
	public CreatedExpress getCreatedExpress() {
		return createdExpress;
	}

	public void setCreatedExpress(CreatedExpress createdExpress) {
		this.createdExpress = createdExpress;
	}

	@XmlElement(name = "Response")
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "CreateExpressResponseType [callSuccess=" + callSuccess + ", createdExpress=" + createdExpress + ", response=" + response
				+ "]";
	}

}

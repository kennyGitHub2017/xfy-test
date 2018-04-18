package com.xuanfeiyang.erp.service.express.sf;

import java.util.List;
import java.util.Map;

public class SenderModel {

	private String serviceName;

	private String headText;

	@SuppressWarnings("rawtypes")
	private Map<Object, List> datas;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getHeadText() {
		return headText;
	}

	public void setHeadText(String headText) {
		this.headText = headText;
	}

	@SuppressWarnings("rawtypes")
	public Map<Object, List> getDatas() {
		return datas;
	}

	@SuppressWarnings("rawtypes")
	public void setDatas(Map<Object, List> datas) {
		this.datas = datas;
	}

}

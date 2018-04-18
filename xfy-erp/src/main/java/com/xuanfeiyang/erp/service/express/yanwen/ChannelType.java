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
@XmlRootElement(name = "ChannelType")
public class ChannelType implements Serializable {
	private static final long serialVersionUID = 9122633932789118806L;
	private String id;
	private String name;
	private boolean status;

	@XmlElement(name = "Id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "Status")
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ChannelType [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

}

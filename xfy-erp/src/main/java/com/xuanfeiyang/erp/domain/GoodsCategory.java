package com.xuanfeiyang.erp.domain;

import java.util.Date;
import java.util.List;

public class GoodsCategory {
	private Integer id;

	private String name;

	private String code;
	
	private Integer parentId;

	private GoodsCategory parent;

	private List<GoodsCategory> childs;

	private String note;

	private Date createdTime;

	private Date lastUpdatedTime;

	public GoodsCategory() {
	}

	public GoodsCategory(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GoodsCategory getParent() {
		return parent;
	}

	public void setParent(GoodsCategory parent) {
		this.parent = parent;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public List<GoodsCategory> getChilds() {
		return childs;
	}

	public void setChilds(List<GoodsCategory> childs) {
		this.childs = childs;
	}

	// 返回类目级别
	public Integer getLevel() {
		int level = 1;
		GoodsCategory temp = parent;
		// 最顶级类目
		if (this.parent == null) {
			return 0;
		}
		while (temp != null && null != temp.getId() && temp.getId() != 0) {
			level += 1;
			temp = temp.parent;
		}
		return level;
	}

	public String toString() {
		return "{\"id\":".concat(String.valueOf(id)).concat(",\"name\":\"")
				.concat(name + "\"}");
	}
}

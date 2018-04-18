package com.xuanfeiyang.erp.domain;

import java.util.Date;

/**
 * 商品仓库货位
 * @author kenny
 *
 */
public class StoreShelf {
	
	private Integer id; //ID
	private String code; //仓位编码
	private String note; //备注
	private Integer storeId;//所属仓库ID
	private Date createdTime; //添加时间
	private Date lastUpdatedTime;//修改时间
	private String storeName;//所属仓库name
	private String keywords;//
	
	public StoreShelf(){}
	
	public StoreShelf(Integer id, String code) {
		super();
		this.id = id;
		this.code = code;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	

}

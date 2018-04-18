package com.xuanfeiyang.erp.domain;

import java.util.Date;
import java.util.List;

public class GoodsCombination {
	private Integer id;

	private String combSku;

	private String name;

	private String note;

	private Date createdTime;

	private Date lastUpdatedTime;
	
	private List<GoodsCombinationItem> items;
	
	public String getItemsStr() {
		if (items != null) {
			StringBuilder sb = new StringBuilder();
			for (GoodsCombinationItem item : items) {
				sb.append(item.getOldSku());
				sb.append("*");
				sb.append(item.getQuantity());
				sb.append(",");
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length()-1);
			}
			return sb.toString();
		}
		
		return null;
	}

	public List<GoodsCombinationItem> getItems() {
		return items;
	}

	public void setItems(List<GoodsCombinationItem> items) {
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCombSku() {
		return combSku;
	}

	public void setCombSku(String combSku) {
		this.combSku = combSku == null ? null : combSku.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
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
}
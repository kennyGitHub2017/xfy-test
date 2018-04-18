package com.xuanfeiyang.erp.domain;

import java.util.Date;

public class PurchaseOrderLog {
    private Integer id;

    private String orderSn;

    private Date createdTime;

    private Short oldStatus;

    private Short newStatus;

    private String content;

    private String operUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Short getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Short oldStatus) {
        this.oldStatus = oldStatus;
    }

    public Short getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Short newStatus) {
        this.newStatus = newStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getOperUser() {
		return operUser;
	}

	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}
}
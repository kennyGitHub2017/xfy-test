package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Seller {
	private Integer id;

	private String contacts;

	private String email;

	private String phone;

	private String mobile;

	private String address;

	private String comName;

	private Integer status;

	private Date createdTime;

	private Date lastUpdatedTime;

	private Integer type;

	private Date statusTime;

	private String idCardNo;

	private String idCardUrl1;

	private String idCardUrl2;

	private String photoUrl;

	private String comCode;

	private String comLegalPerson;

	private String comBizLicenseUrl;

	private Date applyCertTime;

	private String statusNote;

	// 保证金
	private BigDecimal deposit;
	
	// 是否自营卖家
	private String selfFlag;
	
	private String platforms;
	
	private String shop;
	
	private String qqNo;
	
	private String referrerMobile;
	
	private Integer locked;
	
	private Date lockedTime;
	
	//平台信息获取渠道
	private String infoAcquisitionChannel;
	
	private String vipFlag;
	
	private Integer agentUserId;
	
	public Integer getAgentUserId() {
		return agentUserId;
	}

	public void setAgentUserId(Integer agentUserId) {
		this.agentUserId = agentUserId;
	}

	public String getVipFlag() {
		return vipFlag;
	}

	public void setVipFlag(String vipFlag) {
		this.vipFlag = vipFlag;
	}

	public String getInfoAcquisitionChannel() {
		return infoAcquisitionChannel;
	}

	public void setInfoAcquisitionChannel(String infoAcquisitionChannel) {
		this.infoAcquisitionChannel = infoAcquisitionChannel;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Date getLockedTime() {
		return lockedTime;
	}

	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}

	public String getReferrerMobile() {
		return referrerMobile;
	}

	public void setReferrerMobile(String referrerMobile) {
		this.referrerMobile = referrerMobile;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getSelfFlag() {
		return selfFlag;
	}

	public void setSelfFlag(String selfFlag) {
		this.selfFlag = selfFlag;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts == null ? null : contacts.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName == null ? null : comName.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo == null ? null : idCardNo.trim();
	}

	public String getIdCardUrl1() {
		return idCardUrl1;
	}

	public void setIdCardUrl1(String idCardUrl1) {
		this.idCardUrl1 = idCardUrl1 == null ? null : idCardUrl1.trim();
	}

	public String getIdCardUrl2() {
		return idCardUrl2;
	}

	public void setIdCardUrl2(String idCardUrl2) {
		this.idCardUrl2 = idCardUrl2 == null ? null : idCardUrl2.trim();
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl == null ? null : photoUrl.trim();
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode == null ? null : comCode.trim();
	}

	public String getComLegalPerson() {
		return comLegalPerson;
	}

	public void setComLegalPerson(String comLegalPerson) {
		this.comLegalPerson = comLegalPerson == null ? null : comLegalPerson.trim();
	}

	public String getComBizLicenseUrl() {
		return comBizLicenseUrl;
	}

	public void setComBizLicenseUrl(String comBizLicenseUrl) {
		this.comBizLicenseUrl = comBizLicenseUrl == null ? null : comBizLicenseUrl.trim();
	}

	public Date getApplyCertTime() {
		return applyCertTime;
	}

	public void setApplyCertTime(Date applyCertTime) {
		this.applyCertTime = applyCertTime;
	}

	public String getQqNo() {
		return qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}
	
	
}
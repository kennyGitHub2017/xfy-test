package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xuanfeiyang.erp.App;

public class AgentConfigure {
	private Integer version;
	private Integer userId; // 代商商用户id
	private Integer bond; // 保证金
	private BigDecimal deposit; // 总余额
	private BigDecimal costRebateRate;	//成本返点比率
	private BigDecimal serviceRebateRate;	//服务费返点比率
	@JsonFormat(pattern=App.PATTERN_DATETIME, timezone=App.TIMEZONE)
	private Date createdTime;       //创建时间
	@JsonFormat(pattern=App.PATTERN_DATETIME, timezone=App.TIMEZONE)
	private Date lastUpdate; //最后修改时间
	private String userName; //用户名
	private String email; //邮箱
	private String mobile; //手机
	private String updateTimeFrom;
	private String updateTimeTo;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBond() {
		return bond;
	}

	public void setBond(Integer bond) {
		this.bond = bond;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getCostRebateRate() {
		return costRebateRate;
	}

	public void setCostRebateRate(BigDecimal costRebateRate) {
		this.costRebateRate = costRebateRate;
	}

	public BigDecimal getServiceRebateRate() {
		return serviceRebateRate;
	}

	public void setServiceRebateRate(BigDecimal serviceRebateRate) {
		this.serviceRebateRate = serviceRebateRate;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getUpdateTimeFrom() {
		return updateTimeFrom;
	}

	public void setUpdateTimeFrom(String updateTimeFrom) {
		this.updateTimeFrom = updateTimeFrom;
	}

	public String getUpdateTimeTo() {
		return updateTimeTo;
	}

	public void setUpdateTimeTo(String updateTimeTo) {
		this.updateTimeTo = updateTimeTo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}

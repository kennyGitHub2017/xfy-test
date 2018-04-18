package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderRefund {
    private Long id;

    private String accountid;

    private String acctid;

    private String orderid;

    private String itemid;

    private String transactionid;

    private String buyerid;

    private String caseid;

    private String srn;

    private String currency;

    private BigDecimal total;

    private String sku;

    private String country;

    private Date paiddate;

    private Date shippingdate;

    private String refundreson;

    private String refundtype;
    
    private String refundresonSelf;
    
    private String refundtypeSelf;

    private Short refundstatus;

    private Date createtime;

    private String createuser;

    private Short voidflag;

    private String mome;

    private String platid;

    private Short refundfrom;

    private String buyerrevAccount;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date maxTime;
    
    
    
    ////////////////////////////
    private Integer orderId;
    private BigDecimal  orderAmount;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getAcctid() {
		return acctid;
	}

	public void setAcctid(String acctid) {
		this.acctid = acctid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}

	public String getCaseid() {
		return caseid;
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	public String getSrn() {
		return srn;
	}

	public void setSrn(String srn) {
		this.srn = srn;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getPaiddate() {
		return paiddate;
	}

	public void setPaiddate(Date paiddate) {
		this.paiddate = paiddate;
	}

	public Date getShippingdate() {
		return shippingdate;
	}

	public void setShippingdate(Date shippingdate) {
		this.shippingdate = shippingdate;
	}

	public String getRefundreson() {
		return refundreson;
	}

	public void setRefundreson(String refundreson) {
		this.refundreson = refundreson;
	}

	public String getRefundtype() {
		return refundtype;
	}

	public void setRefundtype(String refundtype) {
		this.refundtype = refundtype;
	}

	public Short getRefundstatus() {
		return refundstatus;
	}

	public void setRefundstatus(Short refundstatus) {
		this.refundstatus = refundstatus;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Short getVoidflag() {
		return voidflag;
	}

	public void setVoidflag(Short voidflag) {
		this.voidflag = voidflag;
	}

	public String getMome() {
		return mome;
	}

	public void setMome(String mome) {
		this.mome = mome;
	}

	public String getPlatid() {
		return platid;
	}

	public void setPlatid(String platid) {
		this.platid = platid;
	}

	public Short getRefundfrom() {
		return refundfrom;
	}

	public void setRefundfrom(Short refundfrom) {
		this.refundfrom = refundfrom;
	}

	public String getBuyerrevAccount() {
		return buyerrevAccount;
	}

	public void setBuyerrevAccount(String buyerrevAccount) {
		this.buyerrevAccount = buyerrevAccount;
	}
	
	public Date getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Date maxTime) {
        this.maxTime = maxTime;
    }

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getRefundresonSelf() {
		return refundresonSelf;
	}

	public void setRefundresonSelf(String refundresonSelf) {
		this.refundresonSelf = refundresonSelf;
	}

	public String getRefundtypeSelf() {
		return refundtypeSelf;
	}

	public void setRefundtypeSelf(String refundtypeSelf) {
		this.refundtypeSelf = refundtypeSelf;
	}
}
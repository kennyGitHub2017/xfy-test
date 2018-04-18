package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawApply {
    private Integer id;

    private String payeeName;

    private String payeeBank;

    private String payeeCardno;

    private BigDecimal amount;

    private String note;

    private Date createdTime;

    private Date lastUpdate;

    private Integer applyUser;

    private Short status;

    private Date auditTime;
    
    private Integer auditUser;

    private Date payedTime;

    private Integer payedUser;
    
    private Short valid;
    
    private String payedBank;
    
    private String payedCardno;
    
    private BigDecimal bond;				//代理商保证金
    private BigDecimal deposit;				//余额
    
    
    //--------以下非表字段-----------------------------------------
    private String applyUserName;
    private String payedUserName;
    private String auditUserName;
    private BigDecimal aviableWithdraw;	 //可提现金额
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public String getPayeeBank() {
        return payeeBank;
    }

    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank == null ? null : payeeBank.trim();
    }

    public String getPayeeCardno() {
        return payeeCardno;
    }

    public void setPayeeCardno(String payeeCardno) {
        this.payeeCardno = payeeCardno == null ? null : payeeCardno.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(Integer applyUser) {
        this.applyUser = applyUser;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getPayedTime() {
        return payedTime;
    }

    public void setPayedTime(Date payedTime) {
        this.payedTime = payedTime;
    }

    public Integer getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(Integer auditUser) {
		this.auditUser = auditUser;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public Integer getPayedUser() {
		return payedUser;
	}

	public void setPayedUser(Integer payedUser) {
		this.payedUser = payedUser;
	}

	public String getPayedUserName() {
		return payedUserName;
	}

	public void setPayedUserName(String payedUserName) {
		this.payedUserName = payedUserName;
	}

	public Short getValid() {
        return valid;
    }

    public void setValid(Short valid) {
        this.valid = valid;
    }

	public String getPayedBank() {
		return payedBank;
	}

	public void setPayedBank(String payedBank) {
		this.payedBank = payedBank;
	}

	public String getPayedCardno() {
		return payedCardno;
	}

	public void setPayedCardno(String payedCardno) {
		this.payedCardno = payedCardno;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public BigDecimal getBond() {
		return bond;
	}

	public void setBond(BigDecimal bond) {
		this.bond = bond;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getAviableWithdraw() {
		return aviableWithdraw;
	}

	public void setAviableWithdraw(BigDecimal aviableWithdraw) {
		this.aviableWithdraw = aviableWithdraw;
	}
}
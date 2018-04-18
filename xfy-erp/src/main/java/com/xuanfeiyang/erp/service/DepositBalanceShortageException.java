package com.xuanfeiyang.erp.service;

import java.math.BigDecimal;

/**
 * 账户余额不足异常
 * 
 * @author Adam
 *
 */
public class DepositBalanceShortageException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8493980192149534317L;

	// 余额
	private BigDecimal balance;
	
	// 扣减额度
	private BigDecimal decreaseAmount;
	
	public DepositBalanceShortageException(BigDecimal balance, BigDecimal decreaseAmount) {
		super();
		this.balance = balance;
		this.decreaseAmount = decreaseAmount;
	}
	
	public DepositBalanceShortageException(BigDecimal balance, BigDecimal decreaseAmount, String message) {
		super(message);
		this.balance = balance;
		this.decreaseAmount = decreaseAmount;
	}

	@Override
	public String getMessage() {
		String msg = super.getMessage();
		if (msg == null) {
			msg = String.format("余额不足，当前余额：%s，需扣除：%s", balance, decreaseAmount);
		}
		return msg;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getDecreaseAmount() {
		return decreaseAmount;
	}

	public void setDecreaseAmount(BigDecimal decreaseAmount) {
		this.decreaseAmount = decreaseAmount;
	}
	
	
}

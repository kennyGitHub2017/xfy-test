package com.xuanfeiyang.erp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.SellerDeposit;
import com.xuanfeiyang.erp.domain.SellerDepositLog;
import com.xuanfeiyang.erp.param.SellerDepositParams;

public interface SellerDepositService {
	
	public SellerDeposit load(Integer id);

	/**
	 * 增加保证金
	 * @param id	卖家ID
	 * @param amount 增加金额
	 * @param note 原因
	 * @param operId 操作者ID， 按场景传入，如果为人工为卖家充值，则带入当前用户的ID 
	 */
	public void increaseDeposit(Integer sellerId, BigDecimal amount, 
			String note, Integer operId);
	
	/**
	 * 扣减保证金
	 * @param id	卖家ID
	 * @param amount 增加金额
	 * @param note 原因
	 * @param operId 操作者ID， 按场景传入，如果为人工为卖家修改余额，则带入当前用户的ID 
	 * 
	 * @throws DepositBalanceShortageException 余额不足时发生
	 */
	public void decreaseDeposit(Integer sellerId, BigDecimal amount, 
			String note, Integer operId) throws DepositBalanceShortageException;
	
	
	/**
	 * 增加保证金
	 * @param id	卖家ID
	 * @param amount 增加金额
	 * @param note 原因
	 */
	public void increaseDeposit(Integer sellerId, BigDecimal amount, 
			String note);
	
	/**
	 * 扣减保证金
	 * @param id	卖家ID
	 * @param amount 增加金额
	 * @param note 原因
	 * 
	 * @throws DepositBalanceShortageException 余额不足时发生
	 */
	public void decreaseDeposit(Integer sellerId, BigDecimal amount, 
			String note) throws DepositBalanceShortageException;
	
	
	public Page<SellerDepositLog> findLogPage(PageRequest pageRequest, SellerDepositParams params);
	
	/**
	 * 
	 * 处理订单运费的函数
	 * 临时使用方法
	 * 
	 */
	public void importDepositLog(MultipartFile file);
	
	/***
	 * 资金流水
	 * @param params
	 * @return List
	 */
	public List<SellerDepositLog> findDepositLog(SellerDepositParams params);
	
	
	public Integer getSumAmountLog(SellerDeposit params);
	
	public Integer findDepositLogCount(SellerDepositParams params);
	
	public Map<String,BigDecimal> getTotalInfo(SellerDepositParams params); 
	
	/**
	 * 导入物流信息
	 * @param file
	 */
	public List<Map<String,String>> importLogistics(MultipartFile file) throws Exception;
	
	/**
	 * 卖家ID
	 * @param sellerId
	 * @param days
	 * @return
	 */
	public BigDecimal latestDaysAverageExpenses(Integer sellerId, Integer days);
	
}

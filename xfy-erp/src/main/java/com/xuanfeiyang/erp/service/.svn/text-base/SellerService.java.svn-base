package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.param.SellerParams;

public interface SellerService {
	Page<Seller> findPage(PageRequest pageRequest, SellerParams params);
	
	Page<Seller> findPageWithDeposit(PageRequest pageRequest, SellerParams params);
	
	void delete(Integer id);

	void save(Seller record);

	Seller load(Integer id);

	void update(Seller record);
	
	/**
	 * 审核通过
	 * @param sellerId
	 */
	void approve(Integer sellerId);
	
	void unapprove(Integer sellerId, String note);
	
	/**
	 * 更新自营卖家标识
	 * 
	 * @param id
	 *            卖家ID
	 * @param selfFlag
	 *            自营标识
	 */
	void updateSelfFlag(Integer id, String selfFlag);

	/**
	 * 计算待审核的卖家
	 */
	int countPendingSeller(); 
	
	/**
	 * 自动给卖家发送信息，提醒提交审核资料
	 */
	public void autoSendSmsToSellerforApprove();
	
	/**
	 * 锁定卖家
	 */
	public void lock(Integer sellerId);
	
	/**
	 * 解锁卖家
	 */
	public void unlock(Integer sellerId);

	/**
	 * 更新 VIP 卖家标识
	 * 
	 * @param id
	 *            卖家ID
	 * @param vipFlag
	 *            VIP 标识
	 */
	void updateVipFlag(Integer id, String vipFlag);
	
	/**
	 * 升级卖家为代理商
	 * @param id
	 */
	void upgradeAgent(Integer id);
	
	/**
	 * 根据代理商查询卖家
	 * @param agentUserId
	 * @return
	 */
	public List<Seller> findByAgent(Integer agentUserId);
	
	public Integer getCountByUserId(Integer agentUserId);
	
}

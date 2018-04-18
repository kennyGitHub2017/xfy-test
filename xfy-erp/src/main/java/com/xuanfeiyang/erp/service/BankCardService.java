package com.xuanfeiyang.erp.service;

import java.util.List;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.BankCard;
import com.xuanfeiyang.erp.param.BankCardParam;


public interface BankCardService {
	
	/**
	 * 查找银行卡
	 * @return
	 */
	public List<BankCard> findAll();
	
	/**
	 * 通过银行卡号查找银行卡信息
	 * @param cardNumber
	 * @return
	 */
	public BankCard load(String cardNumber);
	
	/**
	 * 通过id查找银行卡信息
	 * @param id
	 * @return
	 */
	public BankCard findById(Integer id);
	/**
	 * 修改银行卡
	 * @param bankCard
	 * @return
	 */
	public boolean update(BankCard bankCard);
	
	/**
	 * 添加银行卡
	 * @param bankCard
	 * @return
	 */
	public boolean insert(BankCard bankCard);
	
	/**
	 * 删除银行卡
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	/**
	 * 分页查找
	 * @param pageRequest
	 * @param bankCardParam
	 * @return
	 */
	public Page<BankCard> getPage(PageRequest pageRequest,BankCardParam bankCardParam);
}

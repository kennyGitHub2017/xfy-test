package com.xuanfeiyang.erp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.BankCard;
import com.xuanfeiyang.erp.param.BankCardParam;

/**
 * 银行卡管理
 * @author Bryant
 *
 */
public interface BankCardDao {
	
	/**
	 * 查找银行卡信息
	 * @return
	 */
	public List<BankCard> findAll();
	
	/**
	 * 添加银行卡
	 * @return
	 */
	public int insert(@Param("param")BankCard bankCard);
	
	/**
	 * 修改银行卡
	 * @return
	 */
	public int update(@Param("param")BankCard bankCard);
	
	/**
	 * 通过银行卡号查询
	 */
	public BankCard load(String cardNumber);
	
	/**
	 * 通过id查找银行卡信息
	 * @param id
	 * @return
	 */
	public BankCard findById(Integer id);
	
	/**
	 * 根据id删除银行卡
	 * @param id
	 */
	public int deleteById(Integer id);
	
	/**
	 * 分页查找
	 * @param pageRequest
	 * @param bankCardParam
	 * @return
	 */
	public Page<BankCard> findPage(PageRequest pageRequest,@Param("params") BankCardParam bankCardParam);
	
}

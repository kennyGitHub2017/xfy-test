package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.PlatFormAccountSimple;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.param.PlatformAccountParams;

public interface PlatformAccountService {
	
	public Page<PlatFormAccountSimple> findPage(PageRequest pageRequest,PlatformAccountParams param);	
	
	/**
	 * 只有 id 和 accountName 有值
	 * 
	 * @param params
	 * @return
	 */
	public List<PlatformAccount> findNameIdPairs(PlatformAccountParams params);

	/**
	 * 查询账号信息
	 * 
	 * @param params
	 * @return
	 */
	public List<PlatformAccount> find(PlatformAccountParams params);
	
	/**
	 * 通过ID查询账号信息
	 * @param id
	 * @return
	 */
	public PlatformAccount loadById(Integer id); 
	
	/**
	 * 通过卖家ID查询
	 * @param sellerId
	 * @return
	 */
	public List<PlatformAccount> loadBySellerId(Integer sellerId);
}

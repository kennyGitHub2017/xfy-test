package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.PlatFormAccountSimple;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.param.PlatformAccountParams;

public interface PlatformAccountDao {
	
	/**
	 * 平台账号分页数据
	 * @param pageRequest
	 * @param param
	 * @return
	 */
	public Page<PlatFormAccountSimple> findPage(PageRequest pageRequest,
			@Param("param")PlatformAccountParams param);

	public List<PlatformAccount> find(PlatformAccountParams params);

	/**
	 * 只查询 ID 和 Account_Name 两个字段
	 * 
	 * @param params
	 * @return
	 */
	public List<PlatformAccount> findNameIdPairs(PlatformAccountParams params);

	/**
	 * 通过平台和账号查询账号信息
	 * 
	 * @param platform
	 *            平台
	 * @param accountId
	 *            账号
	 * @return
	 */
	public PlatformAccount loadByPlatformAndAccountId(
			@Param("platform") String platform,
			@Param("platform") String accountId);
	
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	public PlatformAccount loadById(Integer id);
	
	/**
	 * 更新 <code>smt</code> 的 <code>access_token</code>
	 * @param id
	 */
	public void updateSmtAccessToken(
			@Param("platAccountId") Integer platAccountId, 
			@Param("accessToken") String accessToken);
	
	/**
	 * 通过卖家ID查询账户信息
	 * @param sellerId
	 * @return
	 */
	public List<PlatformAccount> loadBySellerId(Integer sellerId);
}

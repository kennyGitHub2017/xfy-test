package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.UserParams;

public interface UserInfoDao {
	int delete(Integer userId);

	int save(UserInfo userInfo);

	UserInfo load(Integer userId);

	int update(UserInfo userInfo);

	Page<UserInfo> findPage(PageRequest pageRequest, 
			@Param("params") UserParams params);

	UserInfo loadByCode(String code);

	List<UserInfo> findAll();
	
	UserInfo loadByMobile(String modile);
	
	UserInfo loadByIdCardNo(String icCardNo);
	
	UserInfo loadByEmail(String email);
	
	int deleteBySellerId(Integer sellerId);

	int countBySellerId(Integer sellerId);
	
	/**
	 * 根据部门ID查询
	 * @param departmentId 部门ID
	 * @return
	 */
	List<UserInfo> findByDepartment(@Param("departmentId")Integer departmentId);
	
	List<UserInfo> findBySellerId(Integer sellerId);
	
	/***
	 * 查询销售人员
	 * @return
	 */
	List<UserInfo> findSaleUser();
	
	/***
	 * 查询代理商
	 * @return
	 */
	List<UserInfo> findAgentList();
}
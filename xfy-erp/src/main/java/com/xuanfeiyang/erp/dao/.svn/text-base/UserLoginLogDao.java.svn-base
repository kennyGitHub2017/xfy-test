package com.xuanfeiyang.erp.dao;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.UserLoginLog;
import com.xuanfeiyang.erp.param.UserLoginLogParams;

public interface UserLoginLogDao {
	
	public int insert(UserLoginLog r);

	public Page<UserLoginLog> findPage(PageRequest pageRequest, @Param("params") UserLoginLogParams param);
}
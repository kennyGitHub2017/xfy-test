package com.xuanfeiyang.erp.service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.UserLoginLog;
import com.xuanfeiyang.erp.param.UserLoginLogParams;

public interface UserLoginLogService {

	public void save(UserLoginLog ull);
	
	public Page<UserLoginLog> findPage(PageRequest pageRequest, UserLoginLogParams param);
	
}

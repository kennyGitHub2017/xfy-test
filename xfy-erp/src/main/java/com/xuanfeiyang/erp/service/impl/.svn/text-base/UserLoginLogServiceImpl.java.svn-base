package com.xuanfeiyang.erp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.UserLoginLogDao;
import com.xuanfeiyang.erp.domain.UserLoginLog;
import com.xuanfeiyang.erp.param.UserLoginLogParams;
import com.xuanfeiyang.erp.service.UserLoginLogService;

@Service
public class UserLoginLogServiceImpl implements UserLoginLogService {

	@Resource
	private UserLoginLogDao userLoginLogDao;
	
	@Override
	public void save(UserLoginLog ull) {
		if (ull == null) {
			return;
		}
		
		this.userLoginLogDao.insert(ull);
	}

	@Override
	public Page<UserLoginLog> findPage(PageRequest pageRequest,
			UserLoginLogParams param) {
		return this.userLoginLogDao.findPage(pageRequest, param);
	}

}

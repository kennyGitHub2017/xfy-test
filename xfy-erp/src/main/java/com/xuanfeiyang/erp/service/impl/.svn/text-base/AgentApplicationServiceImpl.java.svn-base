package com.xuanfeiyang.erp.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.AgentApplicationDao;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.UserParams;
import com.xuanfeiyang.erp.service.AgentApplicationService;

@Service
public class AgentApplicationServiceImpl implements AgentApplicationService {
	
	@Resource
	private AgentApplicationDao dao;
	
	@Override
	public UserInfo load(Integer userId) {
		if(userId == null) {
			return null;
		}
		return this.dao.load(userId);
	}

	@Override
	public Page<UserInfo> getPage(PageRequest pageRequest, UserParams params) {
		
		return this.dao.findPage(pageRequest, params);
	}

	@Override
	public boolean approve(Integer userId) {
		int result = this.dao.approve(userId);
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean unapprove(Integer userId,String note) {
		
		note = StringUtils.trimToNull(note);
		Preconditions.checkNotNull(note, "审核不过时，必须填写审核不通过原因");
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userId);
		userInfo.setNote(note);
		int result = this.dao.unapprove(userInfo);
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Integer userId) {
		
		int result = this.dao.delete(userId);
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void lock(Integer userId) {
		this.lockUser(userId, 1);
	}

	@Override
	public void unlock(Integer userId) {
		this.lockUser(userId, 0);
	}
	
	private void lockUser(Integer userId, int locked) {
		UserInfo u = new UserInfo();
		u.setUserId(userId);
		u.setLocked(locked);
		u.setLockedTime(new Date());
		this.dao.update(u);
	}

}

package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.SysPageDao;
import com.xuanfeiyang.erp.domain.SysPage;
import com.xuanfeiyang.erp.service.SysPageService;

@Service
public class SysPageServiceImpl implements SysPageService {
	
	@Resource
	private SysPageDao sysPageDao;
	
	@Override
	public void insert(SysPage sysPage) {
		if(sysPage == null || sysPage.getName() == null){
			return;
		}
		this.sysPageDao.insert(sysPage);
	}

	@Override
	public void delete(String code) {
		if(code == null){
			return;
		}
		this.sysPageDao.delete(code);
	}

	@Override
	public void update(SysPage sysPage) {
		if(sysPage == null || sysPage.getName() == null){
			return;
		}
		this.sysPageDao.update(sysPage);
	}

	@Override
	public SysPage load(String code) {
		if(code == null){
			return null;
		}
		return this.sysPageDao.load(code);
	}

	@Override
	public List<SysPage> findByModuleCode(String moduleCode) {
		if(moduleCode == null){
			return null;
		}
		return this.sysPageDao.findByModuleCode(moduleCode);
	}

}

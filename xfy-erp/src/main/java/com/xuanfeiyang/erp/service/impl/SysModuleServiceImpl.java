package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.SysModuleDao;
import com.xuanfeiyang.erp.domain.SysModule;
import com.xuanfeiyang.erp.service.SysModuleService;

@Service
public class SysModuleServiceImpl implements SysModuleService {

	@Resource
	private SysModuleDao sysModuleDao;
	
	@Override
	public List<SysModule> findAll() {
		return this.sysModuleDao.find();
	}

	@Override
	public void insert(SysModule sysModule) {
		if(sysModule == null || sysModule.getName() == null){
			return;
		}
		this.sysModuleDao.insert(sysModule);
	}

	@Override
	public void delete(String code) {
		if(code == null){
			return;
		}
		this.sysModuleDao.delete(code);
	}

	@Override
	public void update(SysModule sysModule) {
		if(sysModule == null || sysModule.getName() == null){
			return;
		}
		this.sysModuleDao.update(sysModule);
	}

	@Override
	public SysModule load(String code) {
		if(code == null){
			return null;
		}
		return this.sysModuleDao.load(code);
	}

}

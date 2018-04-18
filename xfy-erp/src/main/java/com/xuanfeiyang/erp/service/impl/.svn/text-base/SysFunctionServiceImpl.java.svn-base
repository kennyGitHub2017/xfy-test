package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.SysFunctionDao;
import com.xuanfeiyang.erp.domain.SysFunction;
import com.xuanfeiyang.erp.service.SysFunctionService;

@Service
public class SysFunctionServiceImpl implements SysFunctionService {
	
	@Resource
	private SysFunctionDao sysFunctionDao;
	
	@Override
	public void insert(SysFunction sysFunction) {
		if(sysFunction == null || sysFunction.getName() == null){
			return;
		}
		this.sysFunctionDao.insert(sysFunction);
	}

	@Override
	public void delete(String code) {
		if(code == null){
			return;
		}
		this.sysFunctionDao.delete(code);
	}

	@Override
	public void update(SysFunction sysFunction) {
		if(sysFunction == null || sysFunction.getName() == null){
			return;
		}
		this.sysFunctionDao.update(sysFunction);
	}

	@Override
	public SysFunction load(String code) {
		if(code == null){
			return null;
		}
		return this.sysFunctionDao.load(code);
	}

	@Override
	public List<SysFunction> findByPageCode(String pageCode) {
		if(pageCode == null){
			return null;
		}
		return this.sysFunctionDao.findByPageCode(pageCode);
	}

}

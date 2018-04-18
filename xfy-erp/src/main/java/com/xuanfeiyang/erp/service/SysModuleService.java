package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.domain.SysModule;

public interface SysModuleService {
	
	public List<SysModule> findAll();
	
	public void insert(SysModule sysModule);
	
	public void delete(String code);
	
	public void update(SysModule sysModule);
	
	public SysModule load(String code);
	
}

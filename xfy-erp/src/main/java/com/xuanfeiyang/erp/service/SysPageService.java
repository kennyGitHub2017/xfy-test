package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.domain.SysPage;

public interface SysPageService {
	
	public void insert(SysPage sysPage);
	
	public void delete(String code);
	
	public void update(SysPage sysPage);
	
	public SysPage load(String code);
	
	public List<SysPage> findByModuleCode(String moduleCode);
}

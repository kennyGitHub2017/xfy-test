package com.xuanfeiyang.erp.service;


import java.util.List;

import com.xuanfeiyang.erp.domain.SysFunction;

public interface SysFunctionService {
	
	public void insert(SysFunction sysFunction);
	
	public void delete(String code);
	
	public void update(SysFunction sysFunction);
	
	public SysFunction load(String code);
	
	public List<SysFunction> findByPageCode(String pageCode);
}

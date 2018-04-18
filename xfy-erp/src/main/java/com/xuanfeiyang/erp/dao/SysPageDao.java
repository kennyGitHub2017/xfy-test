package com.xuanfeiyang.erp.dao;

import java.util.List;

import com.xuanfeiyang.erp.domain.SysPage;

public interface SysPageDao {
	int delete(String code);

	int insert(SysPage record);

	SysPage load(String code);

	int update(SysPage record);
	
	List<SysPage> findByModuleCode(String moduleCode);

}
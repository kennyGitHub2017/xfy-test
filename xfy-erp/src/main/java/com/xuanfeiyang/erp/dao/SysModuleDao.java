package com.xuanfeiyang.erp.dao;

import java.util.List;

import com.xuanfeiyang.erp.domain.SysModule;

public interface SysModuleDao {
	int delete(String code);

	int insert(SysModule record);

	SysModule load(String code);

	int update(SysModule record);

	List<SysModule> find();
}
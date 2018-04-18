package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.SysInformation;
import com.xuanfeiyang.erp.param.SysInformationParams;

public interface SysInformationDao {
	
	public Page<SysInformation> findPage(PageRequest pageRequest,
			@Param("params") SysInformationParams params);
	
	SysInformation load(Integer id);

	public List<SysInformation>  findNew(
			@Param("number") Integer number,
			@Param("type") Integer type,
			@Param("keywords") String keywords);
	
	int insert(SysInformation record);

	void update(SysInformation record);

	void delete(Integer id);
}
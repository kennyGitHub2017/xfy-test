package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.SysInformation;
import com.xuanfeiyang.erp.param.SysInformationParams;

public interface SysInformationService {
	public Page<SysInformation> findPage(PageRequest pageRequest,
			SysInformationParams params);
	
	SysInformation load(Integer id);

	/**
	 * 查询前几条咨询 
	 * @param number
	 * @param type 
	 * @param keywords
	 * @return
	 */
	public List<SysInformation>  findNew(Integer number, Integer type, String keywords);
	
	int insert(SysInformation record);

	void update(SysInformation record);

	void delete(Integer id);
}

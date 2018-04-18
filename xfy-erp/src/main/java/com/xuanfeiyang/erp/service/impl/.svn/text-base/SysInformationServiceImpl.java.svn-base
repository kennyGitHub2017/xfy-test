package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.SysInformationDao;
import com.xuanfeiyang.erp.domain.SysInformation;
import com.xuanfeiyang.erp.param.SysInformationParams;
import com.xuanfeiyang.erp.service.SysInformationService;
@Service
public class SysInformationServiceImpl implements SysInformationService{
	@Resource
	private SysInformationDao sysInformationDao;
	@Override
	public Page<SysInformation> findPage(PageRequest pageRequest,
			SysInformationParams params) {
		return sysInformationDao.findPage(pageRequest, params);
	}

	@Override
	public SysInformation load(Integer id) {
		return sysInformationDao.load(id);
	}

	@Override
	public List<SysInformation> findNew(Integer number, Integer type, String keywords) {
		return sysInformationDao.findNew(number, type, keywords);
	}

	@Override
	public int insert(SysInformation record) {
		return sysInformationDao.insert(record);
	}

	@Override
	public void update(SysInformation record) {
		 sysInformationDao.update(record);
	}

	@Override
	public void delete(Integer id) {
		sysInformationDao.delete(id);
	}
}

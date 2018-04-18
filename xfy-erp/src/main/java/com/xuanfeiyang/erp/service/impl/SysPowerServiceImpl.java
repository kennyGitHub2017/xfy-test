package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.SysModuleDao;
import com.xuanfeiyang.erp.domain.SysModule;
import com.xuanfeiyang.erp.service.SysPowerService;

@Service
public class SysPowerServiceImpl implements SysPowerService {

	@Resource 
	private SysModuleDao sysModuleDao;
	
	@Override
	public List<SysModule> findModules() {
		return this.sysModuleDao.find();
	}

}

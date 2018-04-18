package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.PlatformAccountDao;
import com.xuanfeiyang.erp.domain.PlatFormAccountSimple;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.param.PlatformAccountParams;
import com.xuanfeiyang.erp.service.PlatformAccountService;

@Service
public class PlatformAccountServiceImpl implements PlatformAccountService {

	@Resource
	private PlatformAccountDao platformAccountDao;

	@Override
	public Page<PlatFormAccountSimple> findPage(PageRequest pageRequest,
			PlatformAccountParams param) {
		return platformAccountDao.findPage(pageRequest, param);
	}

	@Override
	public List<PlatformAccount> findNameIdPairs(PlatformAccountParams params) {
		return this.platformAccountDao.findNameIdPairs(params);
	}

	@Override
	public List<PlatformAccount> find(PlatformAccountParams params) {
		return this.platformAccountDao.find(params);
	}

	@Override
	public PlatformAccount loadById(Integer id) {
		return id == null ? null : this.platformAccountDao.loadById(id);
	}

	@Override
	public List<PlatformAccount> loadBySellerId(Integer sellerId) {
		return sellerId == null ? null : this.platformAccountDao.loadBySellerId(sellerId);
	}

}

package com.xuanfeiyang.erp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.SkuMappingDao;
import com.xuanfeiyang.erp.domain.SkuMapping;
import com.xuanfeiyang.erp.service.SkuMappingService;

@Service
public class SkuMappingServiceImpl implements SkuMappingService {
	
	@Resource
	private SkuMappingDao skuMappingDao;
	
	@Override
	public void insert(SkuMapping skuMapping) {
		if(skuMapping == null){
			return;
		}
		this.skuMappingDao.insert(skuMapping);
	}

	@Override
	public void update(SkuMapping skuMapping) {
		if(skuMapping == null || skuMapping.getPlatformSku() == null){
			return;
		}
		if(skuMapping.getCreateDate() == null){
			skuMapping.setCreateDate(new Date());
		}
		this.skuMappingDao.update(skuMapping);
	}

	@Override
	public void delete(String platformSku) {
		if(platformSku == null){
			return;
		}
		this.skuMappingDao.delete(platformSku);
	}

	@Override
	public SkuMapping load(String platformSku) {
		if(platformSku == null){
			return null;
		}
		return this.skuMappingDao.load(platformSku);
	}

	@Override
	public List<SkuMapping> find() {
		return this.skuMappingDao.find();
	}
	
	@Override
	public Page<SkuMapping> findPage(PageRequest pageRequest,
			SkuMapping skuMapping) {
		return this.skuMappingDao.findPage(pageRequest, skuMapping);
	}

	@Override
	public void saveOrUpdate(SkuMapping skuMapping) {
		try {
			this.insert(skuMapping);
		} catch (DuplicateKeyException e) {
			this.update(skuMapping);
		}
	}


}

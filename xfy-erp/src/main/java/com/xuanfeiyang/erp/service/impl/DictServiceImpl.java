package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.DictDao;
import com.xuanfeiyang.erp.domain.Dict;
import com.xuanfeiyang.erp.service.DuplicateDataExcepption;
import com.xuanfeiyang.erp.service.DictService;

@Service
public class DictServiceImpl implements DictService {
	
	private static Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);
	
	@Resource
	private DictDao dictDao;

	@Override
	public List<Dict> findByType(Integer type) {
		return this.dictDao.findByType(type);
	}
	
	@Override
	public void delete(Integer type, Integer code) {
		this.dictDao.delete(type, code);
	}

	@Override
	public void save(Dict dict) throws DuplicateDataExcepption {
		if (dict == null || dict.getType() == null 
				|| dict.getCode() == null || dict.getType() == null) {
			return;
		}
		
		if (dict.getType() == 0 && dict.getCode() == 0) {
			logger.warn("新增字典类型时，代码值不能为0， 0为保留值");
			throw new DuplicateDataExcepption("新增字典类型时，代码值不能为0， 0为保留值");
		}
		
		try {
			this.dictDao.save(dict);
		} catch (DuplicateKeyException e) {
			throw new DuplicateDataExcepption(dict);
		}
	}

	@Override
	public void update(Dict dict) {
		if (dict == null) {
			return;
		}
		
		this.dictDao.update(dict);
	}

	@Override
	public Dict load(Integer type, Integer code) {
		return this.dictDao.load(type, code);
	}

	@Override
	public List<Dict> findByTable(String tableName) {
		return this.dictDao.findByTable(tableName);
	}
}

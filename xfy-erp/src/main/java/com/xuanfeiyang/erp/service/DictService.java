package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.domain.Dict;

/**
 * 代码字典操作业务
 * 
 * @author Adam
 *
 */
public interface DictService {

	List<Dict> findByType(Integer type);
	
	void delete(Integer type, Integer code);
	
	void save(Dict dict) throws DuplicateDataExcepption;
	
	void update(Dict dict);
	
	Dict load(Integer type, Integer code);
	
	List<Dict> findByTable(String tableName);
}

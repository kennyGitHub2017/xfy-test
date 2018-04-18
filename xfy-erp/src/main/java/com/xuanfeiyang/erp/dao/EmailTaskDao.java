package com.xuanfeiyang.erp.dao;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.EmailTask;
import com.xuanfeiyang.erp.param.EmailTaskParams;

public interface EmailTaskDao {
	int delete(Integer id);

	int insert(EmailTask record);

	EmailTask findById(Integer id);

	int update(EmailTask record);

	Page<EmailTask> findPage(PageRequest pageRequest, 
			@Param("params") EmailTaskParams params);

}
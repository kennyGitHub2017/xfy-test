package com.xuanfeiyang.erp.dao;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Notice;

public interface NoticeDao {
	int delete(Integer id);

	int insert(Notice record);

	Notice load(Integer id);

	int update(Notice record);
	
	Page<Notice> findPage(PageRequest pageRequest);
	
	/**
	 * 获取有效的一个
	 * @return
	 */
	Notice getLatestOne();
}
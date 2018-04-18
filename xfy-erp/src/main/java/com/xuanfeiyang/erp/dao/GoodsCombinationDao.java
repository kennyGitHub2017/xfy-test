package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsCombination;

public interface GoodsCombinationDao {

	int delete(Integer id);

	int insert(GoodsCombination record);

	GoodsCombination load(Integer id);

	int update(GoodsCombination record);

	List<String> findAllSkus();

	GoodsCombination loadBySku(String combSku);

	Page<GoodsCombination> findPage(PageRequest pageRequest,
			@Param("keywords") String keywords);

}
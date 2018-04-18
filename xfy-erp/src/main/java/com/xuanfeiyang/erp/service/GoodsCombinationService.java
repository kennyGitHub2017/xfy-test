package com.xuanfeiyang.erp.service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsCombination;

public interface GoodsCombinationService {
	
	public void save(GoodsCombination gc);

	public Page<GoodsCombination> findPageWithItems(PageRequest pageRequest,
			String keywords);

	public void delete(Integer id);

	public GoodsCombination loadWithItems(Integer id);

	public void save(Integer id, String combSku, String name, String note, String itemsStr);
	
}

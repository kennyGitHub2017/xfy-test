package com.xuanfeiyang.erp.service;

import java.util.List;
import java.util.Map;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.PurchaseRequestOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseWarn;
import com.xuanfeiyang.erp.param.PurchaseWarnParam;

public interface PurchaseWarnService {
	
	public Page<PurchaseWarn> findPage(PageRequest pageRequest,PurchaseWarnParam param);
	
	public PurchaseWarn detail(String goodsSku, Integer priorityRule);

	public List<String> find(PurchaseWarnParam param);
	/**
	 * 根据供应商生成请购单
	 * @param suplyItemsMap key:供应商id value:供应商下的所有请购单行
	 */
	public void generatePurchaseRequestOrder(Map<Integer,List<PurchaseRequestOrderItem>> suplyItemsMap,Integer userId);
}

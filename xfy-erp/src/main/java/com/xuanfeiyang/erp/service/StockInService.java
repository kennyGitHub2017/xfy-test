package com.xuanfeiyang.erp.service;

import java.math.BigDecimal;
import java.util.List;

import com.xuanfeiyang.erp.domain.IoOrder;
import com.xuanfeiyang.erp.domain.IoOrderItem;
public interface StockInService {
	//增加入库单
	public void addStock(IoOrder order,List<IoOrderItem> orderItems,String operator);
	//更新入库单(一个采购单多次入库的情况)
	public void editStock(IoOrder order,List<IoOrderItem> orderItems,String operator);
	//根据sku获取最近一次采购入库的本产品单个重量
	public BigDecimal getLatestGoodsWeightBySku(String sku);
}

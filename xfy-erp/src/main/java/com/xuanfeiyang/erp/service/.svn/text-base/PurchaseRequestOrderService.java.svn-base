package com.xuanfeiyang.erp.service;


import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.PurchaseRequestOrder;
import com.xuanfeiyang.erp.domain.PurchaseRequestOrderItem;
import com.xuanfeiyang.erp.param.PurchaseRequestOrderParam;

public interface PurchaseRequestOrderService {
		Integer add(PurchaseRequestOrder instance,List<PurchaseRequestOrderItem> items);
		
		void update(PurchaseRequestOrder instance,List<PurchaseRequestOrderItem> items);
		
		 void remove(Integer id);
		
		PurchaseRequestOrder get(Integer id);
	    
	    List<PurchaseRequestOrder> detail(Integer orderId);
	    
	    List<PurchaseRequestOrderItem> findItem(Integer id);
	    
	    Page<PurchaseRequestOrder> findPage(PageRequest pageRequest,PurchaseRequestOrderParam param);
	    
	    /**
	     * 
	     * @param reqOrder  多个同供应商的请购单后合并后生成的请购单对象
	     * @param reqitems  多个同供应商的请购单后合并后请购单子项集合
	     * @param reqorders 多个同供应商的请购单集合(未合并)
	     */
	    void generateOrder(PurchaseRequestOrder reqOrder,List<PurchaseRequestOrderItem> reqitems,List<PurchaseRequestOrder> reqorders,String orderNo,String operator);
}

package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.PurchaseRequestOrder;
import com.xuanfeiyang.erp.domain.PurchaseRequestOrderItem;
import com.xuanfeiyang.erp.param.PurchaseRequestOrderParam;

public interface PurchaseRequestOrderDao {
	 	
	    int insert(PurchaseRequestOrder record);
	    
	    void update(PurchaseRequestOrder record);

	    PurchaseRequestOrder get(Integer id);
	    
	    /**
	     * 根据供应商Id查询
	     * 通过库存预警程序生成的未转采购的请购单
	     * @param supplierId
	     * @return
	     */
	    PurchaseRequestOrder findBySupplierId(@Param("supplierId")Integer supplierId);
	    
	    List<PurchaseRequestOrder> detail(Integer id);
	    
	    Page<PurchaseRequestOrder> findPage(PageRequest pageRequest,@Param("param") PurchaseRequestOrderParam param);
	    
	    void delete(Integer id);
	    
	    void deleteItem(String orderNo);

	    int insertItem(PurchaseRequestOrderItem record);

	    PurchaseRequestOrderItem getItem(Integer itemId);
	    
	    List<PurchaseRequestOrderItem> findItem(Integer id);
}

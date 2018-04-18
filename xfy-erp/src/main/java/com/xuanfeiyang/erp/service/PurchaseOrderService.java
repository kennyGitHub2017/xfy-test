package com.xuanfeiyang.erp.service;

import java.math.BigDecimal;
import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.PurchaseOrder;
import com.xuanfeiyang.erp.domain.PurchaseOrderExport;
import com.xuanfeiyang.erp.domain.PurchaseOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrderStatistic;
import com.xuanfeiyang.erp.domain.PurchaseOrderWayBillNo;
import com.xuanfeiyang.erp.param.PurchaseOrderParam;
import com.xuanfeiyang.erp.param.PurchaseOrderReportParam;

public interface PurchaseOrderService {
    
    Page<PurchaseOrder> findPage(PageRequest pageRequest,PurchaseOrderParam param);

    PurchaseOrderItem getItem(Integer itemId);    
    
    Integer add(PurchaseOrder instance,List<PurchaseOrderItem> items);
	
	void edit(PurchaseOrder instance,List<PurchaseOrderItem> items);
	
	void editItem(List<PurchaseOrderItem> item);
	
	 void remove(Integer id);
	
	 PurchaseOrder get(Integer id);
	 
	 PurchaseOrder findByOrderNo(String orderNo);
    
    List<PurchaseOrder> detail(Integer id);
    
    Page<PurchaseOrder> report(PageRequest pageRequest,PurchaseOrderReportParam param);
    
    List<PurchaseOrder> findReport(PurchaseOrderReportParam param);
    
    List<PurchaseOrderItem> findItem(Integer id);
    boolean modifyReceive(Short status,String orderNo);
    
    /**
     * 根据sku获取最新采购价格
     * @param sku
     * @return
     */
    BigDecimal getLatestGoodsCostBySku(String sku);
    
    /**
     * 设置采购单运单号
     */
    void fillOrderYdh(String orderNo,String willbiNo,String logisticsCompany);
    
    List<PurchaseOrderWayBillNo>  findWayBillNos(String orderNo);
    
    PurchaseOrderWayBillNo  findWayBillNo(String orderNo,String willbiNo,String logisticsCompany);
    
    void editWayBillNo(PurchaseOrderWayBillNo param);
    
    List<PurchaseOrderExport> findList(PurchaseOrderParam param,List<Integer> ids);
    
    Integer findCount(PurchaseOrderParam param,List<Integer> ids);
    
    PurchaseOrderStatistic purchaseOrderTotal(PurchaseOrderReportParam param);
}

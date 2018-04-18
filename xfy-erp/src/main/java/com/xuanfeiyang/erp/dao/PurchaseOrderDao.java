package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.PurchaseOrder;
import com.xuanfeiyang.erp.domain.PurchaseOrderExport;
import com.xuanfeiyang.erp.domain.PurchaseOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrderStatistic;
import com.xuanfeiyang.erp.domain.PurchaseOrderWayBillNo;
import com.xuanfeiyang.erp.param.PurchaseOrderParam;
import com.xuanfeiyang.erp.param.PurchaseOrderReportParam;

public interface PurchaseOrderDao {
	void  delete(Integer id);
	
	void deleteItem(Integer itemId);

    int insert(PurchaseOrder record);
    
    void update(PurchaseOrder record);

    PurchaseOrder get(Integer id);
    
    PurchaseOrder findByOrderNo(String orderNo);
    
    List<PurchaseOrder> detail(Integer id);
    
    Page<PurchaseOrder> report(PageRequest pageRequest,@Param("param")PurchaseOrderReportParam param);
    
    List<PurchaseOrder> findReport(@Param("param")PurchaseOrderReportParam param);
    
    Page<PurchaseOrder> findPage(PageRequest pageRequest,@Param("param") PurchaseOrderParam param);
    
    void deleteItemByOrderId(Integer id);
    
    int insertItem(PurchaseOrderItem record);
    
    void updateItem(PurchaseOrderItem record);

    PurchaseOrderItem getItem(Integer itemId);
    
    List<PurchaseOrderItem> findItem(Integer id);
    
    List<PurchaseOrderItem> findItemBySku(String sku);
    int updateReceive(@Param("param1")Short status, @Param("param2")String orderNo);
    
    /**
     * 查询指定SKU的最新采购信息
     * 
     * @param goodsSku
     * @return
     */
    PurchaseOrderItem findLatestPurchaseItemBySku(String goodsSku);
    
    void fillOrderYdh(@Param("orderNo")String orderNo,@Param("willbiNo")String willbiNo,@Param("logisticsCompany")String logisticsCompany);
    
    List<PurchaseOrderWayBillNo>  findWayBillNos(@Param("orderNo")String orderNo);
    
    PurchaseOrderWayBillNo findWayBillNo(@Param("orderNo")String orderNo,@Param("willbiNo")String willbiNo,@Param("logisticsCompany") String logisticsCompany);
    
    void editWayBillNo(@Param("param")PurchaseOrderWayBillNo param);
    
    
    public List<PurchaseOrderExport> findExportList(@Param("param")PurchaseOrderParam param,@Param("ids")List<Integer> ids);

	public Integer findCount(@Param("param")PurchaseOrderParam param, @Param("ids")List<Integer> ids);
	
	public PurchaseOrderStatistic purchaseOrderTotal(@Param("param")PurchaseOrderReportParam param);
}
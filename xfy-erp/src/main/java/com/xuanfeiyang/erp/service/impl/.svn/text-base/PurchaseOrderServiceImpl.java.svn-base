package com.xuanfeiyang.erp.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.PurchaseOrderDao;
import com.xuanfeiyang.erp.dao.PurchaseOrderLogDao;
import com.xuanfeiyang.erp.domain.PurchaseOrder;
import com.xuanfeiyang.erp.domain.PurchaseOrderExport;
import com.xuanfeiyang.erp.domain.PurchaseOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrderLog;
import com.xuanfeiyang.erp.domain.PurchaseOrderStatistic;
import com.xuanfeiyang.erp.domain.PurchaseOrderWayBillNo;
import com.xuanfeiyang.erp.param.PurchaseOrderParam;
import com.xuanfeiyang.erp.param.PurchaseOrderReportParam;
import com.xuanfeiyang.erp.service.PurchaseOrderService;

@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	
	@Resource
	private PurchaseOrderDao purchaseOrderDao;
	
	@Resource
	private PurchaseOrderLogDao purchaseOrderLogDao;
	
	public void setPurchaseOrderDao(PurchaseOrderDao purchaseOrderDao) {
		this.purchaseOrderDao = purchaseOrderDao;
	}

	@Override
	public Page<PurchaseOrder> findPage(PageRequest pageRequest,
			PurchaseOrderParam param) {
		return purchaseOrderDao.findPage(pageRequest, param);
	}



	@Override
	public PurchaseOrderItem getItem(Integer itemId) {
		return purchaseOrderDao.getItem(itemId);
	}



	@Override
	@Transactional
	public Integer add(PurchaseOrder instance, List<PurchaseOrderItem> items) {
		purchaseOrderDao.insert(instance);
		if(null!=items && items.size()>0){
			for(PurchaseOrderItem item:items){
				item.setOrderNo(instance.getOrderNo());
				item.setReceivedCount(0);
				item.setQualifiedCount(0);
				item.setUnQualifiedCount(0);
				purchaseOrderDao.insertItem(item);
			}
		}
		//记录采购单生成日志
		PurchaseOrderLog log = new PurchaseOrderLog();
		log.setOrderSn(instance.getOrderNo());
		log.setCreatedTime(new Date());
		log.setNewStatus((short)1);
		log.setContent("新建采购单:单号" +instance.getOrderNo());
		log.setOperUser(instance.getCreatedUserName());
		purchaseOrderLogDao.insert(log);
		return instance.getId();
	}


	@Override
	@Transactional
	public void edit(PurchaseOrder instance, List<PurchaseOrderItem> items) {
		purchaseOrderDao.update(instance);
		if (null==items || items.size()==0){
			return;
		}
		purchaseOrderDao.deleteItemByOrderId(instance.getId());
		if(null!=items && items.size()>0){
			for(PurchaseOrderItem item:items){
				item.setOrderNo(instance.getOrderNo());
				purchaseOrderDao.insertItem(item);
			}
		}
	}

	@Override
	@Transactional
	public void editItem(List<PurchaseOrderItem> items) {
		if (null==items || items.size()==0){
			return;
		}
		for(PurchaseOrderItem item:items){
			purchaseOrderDao.updateItem(item);
		}
	}

	@Override
	@Transactional
	public void remove(Integer id) {
		purchaseOrderDao.deleteItemByOrderId(id);
		purchaseOrderDao.delete(id);
	}

	@Override
	public PurchaseOrder get(Integer id) {
		return purchaseOrderDao.get(id);
	}

	@Override
	public PurchaseOrder findByOrderNo(String orderNo) {
		return purchaseOrderDao.findByOrderNo(orderNo);
	}

	@Override
	public List<PurchaseOrder> detail(Integer id) {
		return purchaseOrderDao.detail(id);
	}

	@Override
	public Page<PurchaseOrder> report(PageRequest pageRequest,PurchaseOrderReportParam param) {
		return purchaseOrderDao.report(pageRequest,param);
	}

	@Override
	public List<PurchaseOrder> findReport(PurchaseOrderReportParam param) {
		return purchaseOrderDao.findReport(param);
	}

	@Override
	public List<PurchaseOrderItem> findItem(Integer id) {
		return purchaseOrderDao.findItem(id);
	}

	@Override
	public BigDecimal getLatestGoodsCostBySku(String sku) {		
		PurchaseOrderItem item = this.purchaseOrderDao.findLatestPurchaseItemBySku(sku);
		return item == null ? null : item.getGoodsCost();
	}

	@Override
	public boolean modifyReceive(Short status, String orderNo) {
		int res = this.purchaseOrderDao.updateReceive(status, orderNo);
		if (res > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void fillOrderYdh(String orderNo, String willbiNo,String logisticsCompany) {
		this.purchaseOrderDao.fillOrderYdh(orderNo, willbiNo,logisticsCompany);
	}

	@Override
	public List<PurchaseOrderWayBillNo> findWayBillNos(String orderNo) {
		return this.purchaseOrderDao.findWayBillNos(orderNo);
	}
	

	@Override
	public PurchaseOrderWayBillNo findWayBillNo(String orderNo,
			String willbiNo, String logisticsCompany) {
		return this.purchaseOrderDao.findWayBillNo(orderNo, willbiNo, logisticsCompany);
	}

	@Override
	public void editWayBillNo(PurchaseOrderWayBillNo param) {
		//修改运单号表的数据
		if (null==param.getFirstWaybillNo()){
			this.purchaseOrderDao.editWayBillNo(param);
		}
		//修改采购单表中的运单号数据
		else{
			PurchaseOrder  po = new PurchaseOrder();
			po.setOrderNo(param.getOrderNo());
			po.setWaybillNo(param.getWaybillNoNew());
			po.setLogisticsCompany(param.getLogisticsCompanyNew());
			this.purchaseOrderDao.update(po);
		}
	}

	@Override
	public List<PurchaseOrderExport> findList(PurchaseOrderParam param,
			List<Integer> ids) {
		return this.purchaseOrderDao.findExportList(param, ids);
	}

	@Override
	public Integer findCount(PurchaseOrderParam param, List<Integer> ids) {
		return this.purchaseOrderDao.findCount(param, ids);
	}

	@Override
	public PurchaseOrderStatistic purchaseOrderTotal(
			PurchaseOrderReportParam param) {
		return this.purchaseOrderDao.purchaseOrderTotal(param);
	}
}

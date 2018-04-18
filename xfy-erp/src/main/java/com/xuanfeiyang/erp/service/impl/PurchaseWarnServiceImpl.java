package com.xuanfeiyang.erp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.PurchaseRequestOrderDao;
import com.xuanfeiyang.erp.dao.PurchaseWarnDao;
import com.xuanfeiyang.erp.domain.PurchaseRequestOrder;
import com.xuanfeiyang.erp.domain.PurchaseRequestOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseWarn;
import com.xuanfeiyang.erp.param.PurchaseWarnParam;
import com.xuanfeiyang.erp.service.PurchaseWarnService;
import com.xuanfeiyang.erp.service.XxNoGenerateService;
import com.xuanfeiyang.erp.service.XxNoType;
@Service("purchaseWarnService")
public class PurchaseWarnServiceImpl implements PurchaseWarnService{

	@Resource
	private PurchaseWarnDao purchaseWarnDao;
	@Resource
	private PurchaseRequestOrderDao purchaseRequestOrderDao; 
	@Resource
	private XxNoGenerateService xxNoGenerateService;
		
	@Override
	public Page<PurchaseWarn> findPage(PageRequest pageRequest,
			PurchaseWarnParam param) {
		return purchaseWarnDao.findPage(pageRequest, param);
	}

	@Override
	public PurchaseWarn detail(String goodsSku, Integer priorityRule) {
		return purchaseWarnDao.detail(goodsSku, priorityRule);
	}

	@Override
	public List<String> find(PurchaseWarnParam param) {
		return purchaseWarnDao.find(param);
	}

	@Override
	public void generatePurchaseRequestOrder(Map<Integer,List<PurchaseRequestOrderItem>> suplyItemsMap,Integer userId) {
		for(Map.Entry<Integer, List<PurchaseRequestOrderItem>> entry:suplyItemsMap.entrySet()){
			Integer supplyId = entry.getKey();
			//如果供应商id为空则不处理
			if (null==supplyId){
				continue;
			}
			PurchaseRequestOrder prr = purchaseRequestOrderDao.findBySupplierId(supplyId);
			//新增请购单
			if (prr==null){
				 PurchaseRequestOrder order = new PurchaseRequestOrder();
				 order.setSupplierId(supplyId);
				 order.setOrderNo(xxNoGenerateService.generate(XxNoType.CS));
				 order.setType((short)2);			  // 设置请购单类型为库存预警
				 order.setStatus((short)1);			 // 设置请购单状态为未转采购单
				 order.setCreatedUserId(userId);
				 savePurchaseOrderItems(order,entry.getValue());
			 }else{
				 //将新的请购单合并到已有请购单(未转采购)上
				 List<PurchaseRequestOrderItem> items = entry.getValue();
				 if (null!=items && items.size()>0){
						for(PurchaseRequestOrderItem item:items){
							if (StringUtils.isBlank(item.getOrderNo())){
								item.setOrderNo(prr.getOrderNo());
							}
							purchaseRequestOrderDao.insertItem(item);
						}
					}
			 }
		 }
	}
	
	@Transactional
	public void savePurchaseOrderItems(PurchaseRequestOrder order,List<PurchaseRequestOrderItem> items){
		 purchaseRequestOrderDao.insert(order);
		if (null!=items && items.size()>0){
			for(PurchaseRequestOrderItem item:items){
				if (StringUtils.isBlank(item.getOrderNo())){
					item.setOrderNo(order.getOrderNo());
				}
				purchaseRequestOrderDao.insertItem(item);
			}
		}
	}
	
}

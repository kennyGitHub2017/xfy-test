package com.xuanfeiyang.erp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.GoodsSupplierDao;
import com.xuanfeiyang.erp.dao.GoodsSupplierPriceDao;
import com.xuanfeiyang.erp.domain.GoodsSupplier;
import com.xuanfeiyang.erp.service.GoodsSupplierService;

@Service
public class GoodsSupplierServiceImpl implements GoodsSupplierService {
	
	private final static Logger logger = LoggerFactory.getLogger(GoodsSupplierServiceImpl.class);	

	@Resource
	private GoodsSupplierDao goodsSupplierDao;
	@Resource
	private GoodsSupplierPriceDao goodsSupplierPriceDao;
	
	@Override
	public void save(GoodsSupplier supplier) {
		if (supplier == null || supplier.getCompanyName() == null) {
			return;
		}
		
		Date now = new Date();
		supplier.setCreatedTime(now );
		supplier.setLastUpdatedTime(now);
		supplier.setStatus(0);
		
		this.goodsSupplierDao.save(supplier);
	}

	@Override
	public void update(GoodsSupplier supplier) {
		if (supplier == null || supplier.getId() == null) {
			return;
		}
		
		// 一旦修改，状态变为未审核，需重新审核
		supplier.setStatus(0);
		
		supplier.setLastUpdatedTime(new Date());
		this.goodsSupplierDao.update(supplier);
		/**
		 * TODO 更新报价单周期
		 * @author kenny
		 */
		this.goodsSupplierPriceDao.modifyBySupplier(supplier.getBuyPeriod(), supplier.getId());
		
	}

	@Override
	public Page<GoodsSupplier> findPage(PageRequest pageRequest, String keywords) {
		
		return this.findPage(pageRequest, null, keywords);
	}

	@Override
	public Page<GoodsSupplier> findPage(PageRequest pageRequest,
			Integer status, String keywords) {
		return this.goodsSupplierDao.findPage(pageRequest, status, keywords);
	}

	@Override
	public void delete(Integer id) {
		if (id != null) {
			this.goodsSupplierDao.delete(id);
		}
	}

	@Override
	public GoodsSupplier load(Integer id) {
		return this.goodsSupplierDao.load(id);
	}

	@Override
	public void approve(Integer id, Integer status, Integer auditUserId) {
		
		GoodsSupplier s = new GoodsSupplier();
		s.setId(id);
		s.setStatus(status);
		
		Date now = new Date();
		s.setAuditTime(now);
		s.setAuditUserId(auditUserId);
		s.setLastUpdatedTime(now);
		
		this.goodsSupplierDao.update(s);
	}

	@Override
	public List<GoodsSupplier> find() {
		return this.goodsSupplierDao.find();
	}

	@Override
	public GoodsSupplier findByCode(String code) {
		
		return this.goodsSupplierDao.findByCode(code);
	}

	@Override
	public String importGoodsSupplier(List<GoodsSupplier> datas)  {
		if (datas==null || datas.size()==0){
			return "本次导入操作未影响纪录条数";
		}
		int insertCount=0,updateCount = 0,errorCount=0;
		for(GoodsSupplier data:datas){
			try{
				GoodsSupplier supplier = goodsSupplierDao.findByCode(data.getCode());
				//数据库存在则更新供应商，否则新增供应商
				if (supplier!=null){
					 data = new GoodsSupplier();
					 data.setId(supplier.getId());
					 data.setStatus(0);
					 data.setLastUpdatedTime(new Date());
					 this.goodsSupplierDao.update(data);
					 updateCount++;
				}else{
					data.setCompanyName(StringUtils.isEmpty(data.getCompanyName())?data.getShortName():data.getCompanyName());
					data.setStatus(1);   //已审核状态
					data.setPriority("D");
					data.setCreatedTime(new Date());
					data.setLastUpdatedTime(new Date());
					this.goodsSupplierDao.save(data);
					insertCount++;
				}
			}catch(Exception e){
				errorCount++;
				logger.error("供应商导入出错", e);
			}
		}
		return String.format("本次导入操作更新%d条供应商,新增%d条供应商,出错%d条供应商",updateCount,insertCount,errorCount); 
	}

}

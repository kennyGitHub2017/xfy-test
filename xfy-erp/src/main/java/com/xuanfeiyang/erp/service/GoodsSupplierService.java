package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsSupplier;

public interface GoodsSupplierService {
	
	public void save(GoodsSupplier supplier);
	
	public void update(GoodsSupplier supplier);
	
	public List<GoodsSupplier> find();
	
	public Page<GoodsSupplier> findPage(PageRequest pageRequest, String keywords);
	
	public Page<GoodsSupplier> findPage(PageRequest pageRequest, Integer status, String keywords);
	
	public void delete(Integer id);
	
	public GoodsSupplier load(Integer id);
	
	/**
	 * 审核
	 * @param id 供应商ID
	 * @param status 状态
	 * @param auditUserId 审核用户ID
	 */
	public void approve(Integer id, Integer status, Integer auditUserId);
	
	public GoodsSupplier findByCode(String code);
	
	public String importGoodsSupplier(List<GoodsSupplier> datas);
	
}

package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Store;
import com.xuanfeiyang.erp.domain.StoreShelf;

public interface StoreService {
	

	public List<Store> findStroe();
	
	public List<Store> findStore(Integer type);
	
	public List<StoreShelf> findStoreShelf(Integer storeId);
	
	public boolean saveStore(Store store);
	
	public boolean saveShelf(StoreShelf storeShelf);
	
	/**
	 * 删除仓库
	 * @param id
	 * @return
	 */
	public boolean deleteStore(Integer id);
	
	/**
	 * 删除仓位
	 * @param id
	 * @return
	 */
	public boolean deleteShelf(Integer id);
	
	public boolean updateStore(Store store);
	
	public Store findStoreById(Integer id);
	
	public boolean updateShelf(StoreShelf storeShelf);
	
	public StoreShelf findShelfById(Integer id);
	
	public List<StoreShelf> findShelfByStore(Integer storeId);
	
	public Store findByStoreCode(String code,Integer id);
	
	public StoreShelf findByShelfCode(String code,Integer id);
	
	public Page<StoreShelf> findPage(PageRequest pageRequest, StoreShelf storeShelf);
}

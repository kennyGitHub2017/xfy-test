package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.StoreDao;
import com.xuanfeiyang.erp.dao.StoreShelfDao;
import com.xuanfeiyang.erp.domain.Store;
import com.xuanfeiyang.erp.domain.StoreShelf;
import com.xuanfeiyang.erp.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	@Resource
	private StoreDao storeDao;
	@Resource
	private StoreShelfDao StoreShelfDao;
	
	@Override
	public List<Store> findStroe() {
		return storeDao.find();
		 
	}

	@Override
	public List<Store> findStore(Integer type) {
		return storeDao.findByType(type);
	}


	@Override
	public List<StoreShelf> findStoreShelf(Integer storeId) {
		return StoreShelfDao.find(storeId);
	}

	@Override
	public boolean saveStore(Store store) {
		int result = storeDao.save(store);
		return result > 0;
		
	}

	@Override
	public boolean saveShelf(StoreShelf storeShelf) {
		int result = StoreShelfDao.save(storeShelf);
		return result > 0;
	}

	/**
	 * 删除仓库
	 * 删除仓位
	 */
	@Override
	@Transactional
	public boolean deleteStore(Integer id) {
		int result = storeDao.delete(id);
		StoreShelfDao.deleteByStore(id);
		return result > 0;
	}

	@Override
	public boolean deleteShelf(Integer id) {
		int result =  StoreShelfDao.delete(id);
		return result > 0;
	}

	@Override
	public boolean updateStore(Store store) {
		int result = storeDao.update(store);
		return result > 0;
	}

	@Override
	public Store findStoreById(Integer id) {
		return storeDao.findById(id);
	}

	@Override
	public boolean updateShelf(StoreShelf storeShelf) {
		int result = StoreShelfDao.update(storeShelf);
		return result > 0;
	
	}

	@Override
	public StoreShelf findShelfById(Integer id) {
		return StoreShelfDao.findById(id);
		 
	}

	@Override
	public List<StoreShelf> findShelfByStore(Integer storeId) {
		return StoreShelfDao.findByStore(storeId);
	}

	@Override
	public Store findByStoreCode(String code,Integer id) {
		return this.storeDao.findByCode(code,id);
	}

	@Override
	public StoreShelf findByShelfCode(String code,Integer id) {
		return this.StoreShelfDao.findByCode(code,id);
	}

	@Override
	public Page<StoreShelf> findPage(PageRequest pageRequest,StoreShelf storeShelf) {
		return this.StoreShelfDao.findPage(pageRequest,storeShelf);
	}

}

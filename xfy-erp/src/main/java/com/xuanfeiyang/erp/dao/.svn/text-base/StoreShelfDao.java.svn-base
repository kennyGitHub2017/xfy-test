package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.StoreShelf;

/**
 * 商品仓库货位
 * @author kenny
 *
 */
public interface StoreShelfDao {
	
	public List<StoreShelf> find(@Param("storeId")Integer storeId);
	
	public int save(@Param("param")StoreShelf storeShelf);
	
	public int delete(Integer id);
	
	public int update(@Param("param")StoreShelf storeShelf);
	
	public StoreShelf findById(Integer id);
	
	List<StoreShelf> findByStore(Integer storeId);
	
	public int deleteByStore(Integer id);
	
	public StoreShelf findByCode(@Param("code")String code,@Param("id")Integer id);
	
	public Page<StoreShelf> findPage(PageRequest pageRequest,@Param("param")StoreShelf storeShelf);
}

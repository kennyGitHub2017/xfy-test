package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.Store;

/**
 * 商品仓库
 * @author kenny
 *
 */
public interface StoreDao {
	
	public List<Store> find();
	
	public List<Store> findByType(@Param("type")Integer type);
	
	public int save(@Param("param")Store store);
	
	public int delete(Integer id);
	
	public int update(@Param("param")Store store);
	
	public Store findById(Integer id);
	
	public Store findByCode(@Param("code")String code, @Param("id")Integer id);

}

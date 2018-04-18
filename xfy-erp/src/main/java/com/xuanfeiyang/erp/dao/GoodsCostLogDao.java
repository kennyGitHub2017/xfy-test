package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.GoodsCostLog;

public interface GoodsCostLogDao {
	
	public void add(@Param("param")GoodsCostLog param);
	public List<GoodsCostLog> findBySku(@Param("sku")String sku);

}

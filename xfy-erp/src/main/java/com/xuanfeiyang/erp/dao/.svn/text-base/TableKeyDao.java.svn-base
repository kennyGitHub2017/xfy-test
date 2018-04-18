package com.xuanfeiyang.erp.dao;

import org.apache.ibatis.annotations.Param;

public interface TableKeyDao {
	public void  updateSerialNumber(@Param("tableName")String tableName);
	public Integer nextSerialNumber(@Param("tableName")String tableName);
	public void insert(@Param("tableName")String tableName,@Param("serialNumber")Integer serialNumber);
	public void deleteAll();
}

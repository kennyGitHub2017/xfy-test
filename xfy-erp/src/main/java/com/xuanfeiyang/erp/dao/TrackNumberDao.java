package com.xuanfeiyang.erp.dao;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.TrackNumber;

public interface TrackNumberDao {
	
	public void insert(@Param("param")TrackNumber trackNumber);
	
	public TrackNumber getByType(Integer type);
	
	public void delete(@Param("type")Integer type,@Param("trackNo")String trackNo);

}

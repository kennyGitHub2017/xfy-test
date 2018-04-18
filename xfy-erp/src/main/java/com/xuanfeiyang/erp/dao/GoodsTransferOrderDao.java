package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsTransferOrder;
import com.xuanfeiyang.erp.param.GoodsTransferOrderParam;

public interface GoodsTransferOrderDao {
	Long insert(GoodsTransferOrder account);
	GoodsTransferOrder get(Long id);
	List<GoodsTransferOrder> find(GoodsTransferOrderParam param);
	Page<GoodsTransferOrder> findPage(PageRequest pageRequest,@Param("param") GoodsTransferOrderParam param);
}

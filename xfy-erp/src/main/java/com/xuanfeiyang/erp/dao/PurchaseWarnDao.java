package com.xuanfeiyang.erp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.PurchaseWarn;
import com.xuanfeiyang.erp.param.PurchaseWarnParam;

public interface PurchaseWarnDao {
	/**
	 * 需要生成请购单的sku详情
	 * @param goodsSku
	 * @param priorityRule 单价、供应商获取规则   0:优先级  1:采购周期优先 2:价格优先  默认值:0取优先级
	 * @return
	 */
	public PurchaseWarn detail(@Param("sku")String goodsSku,@Param("rule")Integer priorityRule);
	
	public Page<PurchaseWarn> findPage(PageRequest pageRequest,@Param("param") PurchaseWarnParam param);
	
	public List<String> find(@Param("param")PurchaseWarnParam param);
}

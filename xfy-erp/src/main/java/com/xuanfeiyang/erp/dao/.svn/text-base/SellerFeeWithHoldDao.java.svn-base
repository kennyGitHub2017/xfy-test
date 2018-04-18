package com.xuanfeiyang.erp.dao;

import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import com.xuanfeiyang.erp.domain.SellerFeeWithHold;
/**
 * 卖家费用预扣
 * @author Administrator
 *
 */
public interface SellerFeeWithHoldDao {
	SellerFeeWithHold getByOrderId(@Param("orderId")Integer orderId);
	BigDecimal getWitholdFeeTotalBySeller(@Param("sellerId")Integer sellerId);
	void insert (@Param("param")SellerFeeWithHold sfhd);
	void deletedByOrderId(@Param("orderId")Integer orderId);
}

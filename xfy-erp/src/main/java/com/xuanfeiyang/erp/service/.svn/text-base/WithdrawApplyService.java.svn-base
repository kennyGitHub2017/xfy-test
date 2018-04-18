package com.xuanfeiyang.erp.service;

import java.math.BigDecimal;
import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.WithdrawApply;
import com.xuanfeiyang.erp.param.WithdrawApplyParam;

public interface WithdrawApplyService {
	
	 Page<WithdrawApply> findPage(PageRequest pageRequest,WithdrawApplyParam param);

	 int insert(WithdrawApply record);
	 
	 Integer recordCount(WithdrawApplyParam param,Integer []ids);
	 
	 
	 List<WithdrawApply> find(WithdrawApplyParam param,Integer []ids);

	 WithdrawApply get(Integer id);

	 /**
	  * 审核
	  * @param id
	  */
	 void audit(Integer id,Integer operatorUserid);
	 
	 /**
	  * 反审核
	  * @param id
	  */
	 void reverseAudit(Integer id,Integer operatorUserid);
	 
	 /**
	  * 获取代理商用户所有[状态:  0:未审核  1:已审核 ](未成功提现)的申请金额汇总
	  * @param userId 代理商用户id
	  * @return
	  */
	 BigDecimal applyWithdrawAmount(Integer userId);
	 
	 /**
	  * 确认付款
	  * @param record
	  */
	 void payForApply(WithdrawApply record) throws RuntimeException;
}

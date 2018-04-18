package com.xuanfeiyang.erp.dao;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.WithdrawApply;
import com.xuanfeiyang.erp.param.WithdrawApplyParam;

public interface WithdrawApplyDao {
	
	Page<WithdrawApply> findPage(PageRequest pageRequest,@Param("param") WithdrawApplyParam param);
	
	List<WithdrawApply> find(@Param("param")WithdrawApplyParam param,@Param("ids")Integer []ids);
	
	Integer recordCount(@Param("param")WithdrawApplyParam param,@Param("ids")Integer []ids);
	
    int delete(Integer id);

    int insert(WithdrawApply record);

    WithdrawApply get(Integer id);
    
    void update(WithdrawApply record);
    
    BigDecimal applyWithdrawAmount(Integer userId);
}
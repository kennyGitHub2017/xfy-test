package com.xuanfeiyang.erp.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.AgentConfigureDao;
import com.xuanfeiyang.erp.dao.AgentRebateDao;
import com.xuanfeiyang.erp.dao.WithdrawApplyDao;
import com.xuanfeiyang.erp.domain.AgentConfigure;
import com.xuanfeiyang.erp.domain.AgentRebate;
import com.xuanfeiyang.erp.domain.WithdrawApply;
import com.xuanfeiyang.erp.param.WithdrawApplyParam;
import com.xuanfeiyang.erp.service.WithdrawApplyService;
@Service
public class WithdrawApplyServiceImpl implements WithdrawApplyService{

	@Resource
	private WithdrawApplyDao withdrawApplyDao;
	
	@Resource
	private AgentRebateDao agentRebateDao;
	
	@Resource
	private AgentConfigureDao agentConfigureDao;
	
	@Override
	public Page<WithdrawApply> findPage(PageRequest pageRequest,
			WithdrawApplyParam param) {
		return withdrawApplyDao.findPage(pageRequest, param);
	}
	

	@Override
	public Integer recordCount(WithdrawApplyParam param, Integer[] ids) {
		return withdrawApplyDao.recordCount(param, ids);
	}

	@Override
	public List<WithdrawApply> find(WithdrawApplyParam param, Integer[] ids) {
		return withdrawApplyDao.find(param, ids);
	}


	@Override
	public int insert(WithdrawApply record) {
		if (record.getAmount().compareTo(record.getAviableWithdraw())>0){
			throw new RuntimeException("账号余额不足以此次提现申请金额");
		}
		record.setCreatedTime(new Date());
		return withdrawApplyDao.insert(record);
	}

	@Override
	public WithdrawApply get(Integer id) {
		return withdrawApplyDao.get(id);
	}

	@Override
	public void audit(Integer id,Integer operatorUserid) {
		WithdrawApply wday = get(id);
		if (wday==null){
			return;
		}
		WithdrawApply t = new WithdrawApply();
		Date now = new Date();
		t.setId(wday.getId());
		t.setStatus((short)1);
		t.setAuditTime(now);
		t.setAuditUser(operatorUserid);
		t.setLastUpdate(now);
		withdrawApplyDao.update(t);
	}

	@Override
	public void reverseAudit(Integer id,Integer operatorUserid) {
		WithdrawApply wday = get(id);
		if (wday==null){
			return;
		}
		WithdrawApply t = new WithdrawApply();
		t.setId(wday.getId());
		t.setStatus((short)0);
		t.setAuditTime(null);
		t.setAuditUser(null);
		t.setLastUpdate(new Date());
		withdrawApplyDao.update(t);
	}
	
	@Override
	public BigDecimal applyWithdrawAmount(Integer userId) {
		return withdrawApplyDao.applyWithdrawAmount(userId);
	}

	@Override
	@Transactional
	public void payForApply(WithdrawApply record) throws RuntimeException {
		this.withdrawApplyDao.update(record);
		AgentConfigure agent = this.agentConfigureDao.getDeposit(record.getApplyUser());
		if (agent==null || agent.getDeposit().compareTo(record.getAmount())<0){
			throw new RuntimeException("操作失败,账户可提取资金不足以本次提现操作");
		}
		if (null!=agent){
			//记录代现商资金流水
			AgentRebate rebate = new AgentRebate();
			rebate.setUserId(record.getApplyUser());
			rebate.setType(2);		//支出-提现
			rebate.setCurrentDeposit(agent.getDeposit().subtract(record.getAmount()));
			rebate.setAmount(record.getAmount());
			rebate.setCreatedTime(new Date());
			this.agentRebateDao.insert(rebate);
			// 扣减本次申请的提现金额
			agent.setDeposit(agent.getDeposit().subtract(record.getAmount()));
			this.agentConfigureDao.updateDeposit(agent);
		}
	}
}

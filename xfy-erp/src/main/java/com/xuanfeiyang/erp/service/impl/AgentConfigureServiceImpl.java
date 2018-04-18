package com.xuanfeiyang.erp.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.AgentConfigureDao;
import com.xuanfeiyang.erp.dao.AgentRebateDao;
import com.xuanfeiyang.erp.dao.SellerDao;
import com.xuanfeiyang.erp.dao.SellerDepositLogDao;
import com.xuanfeiyang.erp.domain.AgentConfigure;
import com.xuanfeiyang.erp.domain.AgentConfigureLog;
import com.xuanfeiyang.erp.domain.AgentRebate;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.SellerDepositLog;
import com.xuanfeiyang.erp.param.SellerDepositParams;
import com.xuanfeiyang.erp.service.AgentConfigureService;

@Service
public class AgentConfigureServiceImpl  implements AgentConfigureService {
	
	private static Logger logger = LoggerFactory.getLogger(AgentConfigureServiceImpl.class);

	@Resource
	private AgentConfigureDao agentConfigureDao;
	
	@Resource
	private SellerDepositLogDao sellerDepositLogDao;
	
	@Resource
	private SellerDao sellerDao;
	
	@Resource
	private AgentRebateDao agentRebateDao;
	
	@Override
	public Page<AgentConfigure> findPage(PageRequest pageRequest,
			AgentConfigure param) {
		
		return this.agentConfigureDao.findPage(pageRequest, param);
	}

	@Override
	public void updateRebateRate(String costRate, String serviceRate,
			String bond,Integer userId,Integer operId) {
		AgentConfigure ac = new AgentConfigure();

		if (StringUtils.isNotBlank(bond)) {
			ac.setBond(Integer.parseInt(bond));

		} else {
			ac.setCostRebateRate(new BigDecimal(costRate));
			ac.setServiceRebateRate(new BigDecimal(serviceRate));
			
			String note = "成本返点修改"+costRate +"%"+ "服务返点修改"+ serviceRate+"%";
			this.addRebateRateLog(userId, operId, note);//添加修改日志
		}
			ac.setUserId(userId);
		this.agentConfigureDao.updateDeposit(ac);
		
	}

	/***
	 * 修改保证金额度
	 */
	@Override
	public void updateAgentBond(Integer bond,Integer userId,Integer operId) {
		AgentConfigure ac = new AgentConfigure();
		ac.setBond(bond);
		ac.setUserId(userId);
		this.agentConfigureDao.updateDeposit(ac);
		
		String note = "保证金修改为"+bond;
		this.addRebateRateLog(userId, operId, note);//添加修改日志
		
	}
	
	
	
	/***
	 * 根据sellerd获得代理商ID
	 * sellerId 卖家Id
	 * @return
	 */
	private Integer getAgentIdBySellerId(Integer sellerId){
		Integer agentId =  null;
		Seller seller = this.sellerDao.load(sellerId);
		if(seller != null){
			if(seller.getAgentUserId() != null){
				agentId = seller.getAgentUserId();
			}
		}
		return agentId;
	}

	/**
	 * 
	 * 根据userId 查询 代理商 返点参数设置
	 * userId 用户id
	 */
	@Override
	public AgentConfigure getByUserId(Integer userId) {
		AgentConfigure configure = this.agentConfigureDao.getByUserId(userId);
		return configure;
	}
	
	
	
	
	/***
	 * 发货订单返点
	 */
	//TODO
	@Override
	public void shippedOrderRebate() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		SellerDepositParams sdl = new SellerDepositParams();
		sdl.setStartDate(cal.getTime());//当天0点整
		sdl.setEndDate(new Date());//当前时间
		
		// 查询所有当天发货扫描的订单 --根据时间
		List<SellerDepositLog> depositLogList = this.sellerDepositLogDao.findShippedOrdersDepositLog(sdl);
		
		// 循环遍历发货订单
		for(SellerDepositLog thisLog : depositLogList){
			
			// 很据发货日志的 sellerId 查询  代理商 id
			Integer agentId = this.getAgentIdBySellerId(thisLog.getSellerId());
			
			//判断agentId 是否 为空
			if(agentId == null){
				logger.info(thisLog.getSellerId() + "此卖家无代理商");
				continue;
			} else {

				// 根据代理商ID 查询 是否 设置返点参数
				AgentConfigure  configure = this.getByUserId(agentId);
				if(configure == null ){
					logger.info(agentId+"代理商没有返点信息");
					continue;
				} else{
					this.addOneRebateLog(thisLog, configure);
				}
				
			}
			
		}
		
	}
	
	/**
	 * 处理一个已发货的订单的返点金额
	 * @param thisLog
	 * @param configure
	 */
	public void addOneRebateLog(SellerDepositLog thisLog, AgentConfigure configure){
		//添加代理商资金流水日志
		AgentRebate agentRebate = new AgentRebate();
		agentRebate.setUserId(configure.getUserId());
		agentRebate.setOrderId(thisLog.getOrderId());
		agentRebate.setCreatedTime(new Date());
		agentRebate.setType(0);//0:收入-订单交易成功 
		agentRebate.setCurrentDeposit(configure.getDeposit());
		BigDecimal cost = thisLog.getOrderCost(); //成本
		BigDecimal orderFee = thisLog.getOrderFee();//服务费

		BigDecimal rebateCost = new BigDecimal(0);
		
		if(cost != null){
			rebateCost = cost.multiply(configure.getCostRebateRate())
					.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		BigDecimal rebateOrderFee = new BigDecimal(0);
		
		if(orderFee != null){
			 rebateOrderFee = orderFee.multiply(configure.getServiceRebateRate())
						.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		
		BigDecimal thisRebateFee = rebateCost.add(rebateOrderFee).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		agentRebate.setAmount(thisRebateFee);
		agentRebateDao.insert(agentRebate);
		
		//修改代理商资金
		AgentConfigure ac = new AgentConfigure();
		ac.setUserId(configure.getUserId());
		ac.setVersion(configure.getVersion());
		ac.setDeposit(configure.getDeposit().add(thisRebateFee).setScale(2, BigDecimal.ROUND_HALF_UP));
		this.agentConfigureDao.updateDeposit(ac);
	}
	
	private void addRebateRateLog(Integer userId,Integer operId,String note){
		AgentConfigureLog al = new AgentConfigureLog();
		al.setCreatedTime(new Date());
		al.setUserId(userId);
		al.setOperId(operId);
		al.setNote(note);
		this.agentConfigureDao.addConfigureLog(al);
	}

	@Override
	public List<AgentConfigureLog> getLogByUserId(Integer userId) {
		return this.agentConfigureDao.getLogByUserId(userId);
	}
	
	@Override
	public boolean add(AgentConfigure agentConfigure) {
		int result = this.agentConfigureDao.insert(agentConfigure);
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}

package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.PaypalAccountDao;
import com.xuanfeiyang.erp.domain.PaypalAccount;
import com.xuanfeiyang.erp.service.PaypalAccountService;
@Service
public class PaypalAccountServiceImpl implements PaypalAccountService{
@Resource
private PaypalAccountDao paypalAccountDao;
	
	@Override
	public List<PaypalAccount> find() {
		return paypalAccountDao.find();
	}

	@Override
	public boolean delete(Long id) {
		int result = paypalAccountDao.delete(id);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean add(PaypalAccount paypalAccount) {
		int result = paypalAccountDao.save(paypalAccount);
		if(result > 0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean update(PaypalAccount paypalAccount) {
		int result = paypalAccountDao.update(paypalAccount);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public PaypalAccount findById(Long id) {
		return paypalAccountDao.findById(id);
	}

}

package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.BankCardDao;
import com.xuanfeiyang.erp.domain.BankCard;
import com.xuanfeiyang.erp.param.BankCardParam;
import com.xuanfeiyang.erp.service.BankCardService;

@Service
public class BankCardServiceImpl implements BankCardService {
	
	@Resource 
	private BankCardDao bankCardDao;
	
	@Override
	public List<BankCard> findAll() {
		return this.bankCardDao.findAll();
	}

	@Override
	public boolean update(BankCard bankCard) {
		int result = this.bankCardDao.update(bankCard);
		if(result > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean insert(BankCard bankCard) {
		int result = this.bankCardDao.insert(bankCard);
		if(result > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) {
		int result = this.bankCardDao.deleteById(id);
		if(result > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Page<BankCard> getPage(PageRequest pageRequest,
			BankCardParam bankCardParam) {
		return this.bankCardDao.findPage(pageRequest, bankCardParam);
	}

	@Override
	public BankCard load(String cardNumber) {
		if(cardNumber == null) {
			return null;
		}
		return this.bankCardDao.load(cardNumber);
	}

	@Override
	public BankCard findById(Integer id) {
		if(id == null) {
			return null;
		}
		return this.bankCardDao.findById(id);
	}
	
	
}

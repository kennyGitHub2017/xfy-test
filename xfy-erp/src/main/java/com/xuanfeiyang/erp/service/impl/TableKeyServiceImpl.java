package com.xuanfeiyang.erp.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xuanfeiyang.erp.dao.TableKeyDao;
import com.xuanfeiyang.erp.service.TableKeyService;
@Service
public class TableKeyServiceImpl  implements TableKeyService{
	@Resource
	private TableKeyDao tableKeyDao;
	
	@Override
	public void deleteAll() {
		tableKeyDao.deleteAll();
	}

	@Override
	public void reSettingSerialNumber(String tableName,Integer initValue) {
		tableKeyDao.insert(tableName,initValue);
	}

	@Override
	public  String nextSerialNumber(String tableName,String fmtStr) {
		Integer serNumber = null;
		synchronized(this){
			tableKeyDao.updateSerialNumber(tableName);
			serNumber = tableKeyDao.nextSerialNumber(tableName);
		}
		return String.format(fmtStr,serNumber+1);
	}

	@Override
	@Transactional
	public void dailyDataInit() {
		deleteAll();
		reSettingSerialNumber("i_orders",10000);
		reSettingSerialNumber("o_orders",10000);
		reSettingSerialNumber("purchase_orders",10000);
		reSettingSerialNumber("goods_transfer_order",10000);
		reSettingSerialNumber("seller_deposit_log",00000);
	}
}

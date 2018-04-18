package com.xuanfeiyang.erp.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.erp.service.TableKeyService;

/**
 * 测试使用
 * 
 * @author Adam
 *
 */
@Service
public class TempTestService {
	
	private Logger logger = LoggerFactory.getLogger(TempTestService.class);
	
	private DateFormat dft = new SimpleDateFormat("yyyyMMdd");
	
	@Resource
	private TableKeyService tableKeyService;
	
	@Transactional
	public void transaction() throws Exception {
		String dateStr = dft.format(new Date());
		String fmt1 = String.format("LS-%s-",dateStr)+"%d";
		String fmt2 = String.format("ex-%s-",dateStr)+"%05d";
		
		logger.info("-------------------------------- 1");
		
		String sn1 = tableKeyService.nextSerialNumber("o_orders",fmt1);
		logger.info("sn ==> {}", sn1);
		
		logger.info("-------------------------------- 2");
		
		String sn2 = tableKeyService.nextSerialNumber("seller_deposit_log",fmt2);
		logger.info("sn ==> {}", sn2);

		logger.info("-------------------------------- 3");
		
		Thread.sleep(40000);
	}
	
}

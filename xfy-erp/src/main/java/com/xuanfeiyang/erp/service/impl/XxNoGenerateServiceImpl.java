package com.xuanfeiyang.erp.service.impl;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.TableKeyDao;
import com.xuanfeiyang.erp.service.XxNoGenerateService;
import com.xuanfeiyang.erp.service.XxNoType;

/**
 * 简单处理，统一格式为
 * 类型 -yyyyMMdd-HHmmss + 4位流水
 * 
 * XX-20140101-0101010001
 * 
 * @author Adam
 *
 */
@Service
public class XxNoGenerateServiceImpl implements XxNoGenerateService {

	private static AtomicInteger counter = new AtomicInteger(10000);
	
	@Resource
	private TableKeyDao  tableKeyDao; 
	
	@Override
	public String generate(XxNoType type) {
		String flowno = String.valueOf(counter.incrementAndGet());
		// 只取4位
		flowno = flowno.substring(flowno.length() - 4);

		StringBuilder sb = new StringBuilder();
		sb.append(type.value());
		sb.append("-");
		sb.append(DateFormatUtils.format(new Date(), "yyyyMMdd"));
		sb.append("-");
		sb.append(DateFormatUtils.format(new Date(), "HHmmss"));
		sb.append(flowno);
		
		return sb.toString();
	}


	/*
	public static void main(String[] args) {
		XxNoGenerateServiceImpl s = new XxNoGenerateServiceImpl();
		
		for (int i = 0; i < 10000; i++) {
			System.out.println(s.generate(XxNoType.CS));
		}
	}
	*/
}

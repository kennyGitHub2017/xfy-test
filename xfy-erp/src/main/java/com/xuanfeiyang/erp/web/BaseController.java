package com.xuanfeiyang.erp.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@ExceptionHandler
	public String handleException(Exception e) {
		
		logger.error("system.error", e);
		
		return "500";
	}
}

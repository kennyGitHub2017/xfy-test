package com.xuanfeiyang.erp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestController {

	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) {
		logger.info("Main page !");
		return "test/main";
	}

	@RequestMapping(value = "/cols2-fixed-left", method = RequestMethod.GET)
	public String cols2FixedLeft(Model model) {
		logger.info("Main page !");
		return "test/cols2-fixed-left";
	}
	
	@RequestMapping(value = "/left-sidebar", method = RequestMethod.GET)
	public String leftSidebar(Model model) {
		logger.info("Main page !");
		return "test/left-sidebar";
	}
}

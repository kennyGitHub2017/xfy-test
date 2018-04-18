package com.xuanfeiyang.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.service.SmsSendService;
import com.xuanfeiyang.test.BaseTestCase;

public class SmsSendServiceImplTest extends BaseTestCase {
	
	@Resource
	private SmsSendService smsSendService;
	
	@Test
	public void testSend() {
		String mobile = "13424387624";
		String content = String.format(App.getConfig("sms.content.auth.code"), "123");
		this.smsSendService.send(mobile, content);
	}
	
}

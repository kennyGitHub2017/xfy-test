package com.xuanfeiyang.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.xuanfeiyang.erp.service.impl.EmailTaskService;
import com.xuanfeiyang.test.BaseTestCase;

public class EmailSenderServiceTest extends BaseTestCase {
	
	@Resource 
	private EmailTaskService emailSenderService;
	
	@Test
	public void testSendEmail() throws Exception {
		List<String> toEmails = new ArrayList<>();
		toEmails.add("adam@xuanfeiyang.com");
		
		String subject = "邮件发送测试";
		String content = "<span style=\"color:red\">Closing org.springframework.context.support.GenericApplicationContext@b7f23d9: startup date [Thu Aug 27 14:22:37 CST 2015]; root of context hierarchy</span>";
		
		emailSenderService.sendEmail(toEmails, subject, content, 1);
	}
	
}

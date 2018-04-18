package com.xuanfeiyang.erp.service.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.EmailSenderConfigDao;
import com.xuanfeiyang.erp.dao.EmailTaskDao;
import com.xuanfeiyang.erp.domain.EmailSenderConfig;
import com.xuanfeiyang.erp.domain.EmailTask;
import com.xuanfeiyang.erp.param.EmailTaskParams;

/**
 * 邮件发送
 * 
 * @author Adam
 *
 */
@Service
public class EmailTaskService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailTaskService.class);
	
	@Resource
	private EmailSenderConfigDao emailSenderConfigDao;

	@Resource
	private EmailTaskDao emailTaskDao;

	public void sendEmail(List<String> toEmails, String subject, 
			String content, Integer creatorId) throws Exception {
		checkArgument(StringUtils.isNotBlank(subject), "缺少邮件标题");
		checkArgument(StringUtils.isNotBlank(content), "缺少邮件内容");
		
		toEmails = this.validatedEmails(toEmails);
		checkArgument(CollectionUtils.isNotEmpty(toEmails), "缺少收件人信息");
		
		List<EmailSenderConfig> emailSenderConfigs = this.emailSenderConfigDao.findAll();
		checkArgument(CollectionUtils.isNotEmpty(emailSenderConfigs), "缺少发件人配置信息");
		
		EmailSenderConfig emailConf = emailSenderConfigs.get(0);
		
		EmailTask et = this.saveEmailTask(emailConf, toEmails, subject, content, creatorId);
		
		try {
			this.sendingMail(emailConf, toEmails, subject, content);
		} catch (MessagingException e) {
			logger.error("发送邮件出错", e);
			this.updateEmailTaskStatus(et.getId(), 2);
			throw e;
		}
		
		this.updateEmailTaskStatus(et.getId(), 1);
	}

	private void updateEmailTaskStatus(Integer id, int status) {
		EmailTask et = new EmailTask();
		et.setId(id);
		et.setStatus(status);
		et.setStatusTime(new Date());
		this.emailTaskDao.update(et);
	}

	private EmailTask saveEmailTask(EmailSenderConfig emailConf, 
			List<String> toEmails, String subject, String content, Integer creatorId) {

		EmailTask et = new EmailTask();
		et.setSubject(subject);
		et.setContent(content);
		et.setSenderEmail(emailConf.getEmail());
		et.setReceiverEmail(StringUtils.join(toEmails, ","));
		et.setStatus(0);
		et.setCreator(creatorId);
		Date now = new Date();
		et.setCreatedTime(now);
		et.setStatusTime(now);
		
		this.emailTaskDao.insert(et);
		
		return et;
	}

	private void sendingMail(EmailSenderConfig emailConf, List<String> toEmails,
			String subject, String content) throws MessagingException {
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(emailConf.getSmtpServerIp());
		sender.setPort(emailConf.getSmtpServerPort());
		sender.setUsername(emailConf.getSmtpUsername());
		sender.setPassword(emailConf.getSmtpPassword());
		sender.setDefaultEncoding("UTF-8");

		// 配置文件对象
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true"); // 是否进行验证
		Session session = Session.getInstance(props);
		sender.setSession(session); // 为发送器指定会话

		MimeMessage mail = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail);
		helper.setTo(toEmails.toArray(new String[0]));
		helper.setSubject(subject); // 标题
		helper.setFrom(emailConf.getEmail()); // 来自
		// 邮件内容，第二个参数指定发送的是HTML格式
		helper.setText(content, true);

		sender.send(mail);
	}

	// 返回验证过的 email 数组
	private List<String> validatedEmails(List<String> toEmails) {
		if (toEmails == null) {
			return null;
		}
		
		List<String> tmpEmailsList = new ArrayList<>(toEmails.size());
		
		for (String toEmail : toEmails) {
			if (this.validateEmail(toEmail)) {
				tmpEmailsList.add(toEmail);
			}
		}
		
		return tmpEmailsList;
	}

	// 简单校验 email
	private boolean validateEmail(String toEmail) {
		toEmail = StringUtils.trimToEmpty(toEmail);
		return StringUtils.isNotBlank(toEmail) && toEmail.contains("@");
	}
	
	public Page<EmailTask> findPage(PageRequest pageRequest, EmailTaskParams params) {
		return this.emailTaskDao.findPage(pageRequest, params);
	}
	
	public EmailTask findById(Integer id) {
		return id == null ? null : this.emailTaskDao.findById(id);
	}
}

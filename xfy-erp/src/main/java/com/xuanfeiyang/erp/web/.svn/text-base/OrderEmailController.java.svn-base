package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.OrderDao;
import com.xuanfeiyang.erp.domain.EmailTask;
import com.xuanfeiyang.erp.param.EmailTaskParams;
import com.xuanfeiyang.erp.service.impl.EmailTaskService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.Result;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/order-email")
@SessionAttributes(App.SESSION_USER_KEY)
public class OrderEmailController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderEmailController.class);
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private EmailTaskService emailTaskService;
	
	/**
	 * 发送邮件页面
	 * @param orderIds
	 * @return
	 */
	@RequestMapping(value={"/", ""}, method={RequestMethod.GET, RequestMethod.POST})
	public String emailPage(Model model, 
			@RequestParam(value="id") List<Integer> ids) {
		List<String> emails = this.orderDao.findBuyerEmailsByOrderIds(ids);
		model.addAttribute("toEmail", StringUtils.join(emails, ","));
		return "orders/order-email";
	}
	
	@RequestMapping(value="/send.json", method=RequestMethod.POST)
	@ResponseBody
	public Result send(RedirectAttributes redirect,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser,
			@RequestParam(value="toEmail", required=false) List<String> toEmails,
			@RequestParam(value="subject", required=false) String subject,
			@RequestParam(value="content", required=false) String content) {
		Result r = new Result();
		
		try {
			this.emailTaskService.sendEmail(toEmails, subject, content,
					sessionUser.getUserId());
			r.setSuccess(true);
		} catch (Exception e) {
			logger.error("发送邮件出错", e);
			r.setSuccess(false);
			r.setMessage(e.getMessage());
		}

		return r;
	}
	
	@RequestMapping(value="/email-logs", method=RequestMethod.GET)
	public String emailLogs() {
		return "orders/order-email-logs";
	}
	
	@RequestMapping(value="/email-logs.json")
	@ResponseBody
	public DataTableResponse<EmailTask> depositLogJson(Model model, 
			@RequestBody DataTableRequest<EmailTaskParams> dtr) {
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		
		Page<EmailTask> logs = this.emailTaskService.findPage(pageRequest, dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, logs);
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(Model model, 
			@RequestParam("id") Integer id) {
		
		model.addAttribute("emailTask", this.emailTaskService.findById(id));
		
		return "orders/order-email-view";
	}
	
}

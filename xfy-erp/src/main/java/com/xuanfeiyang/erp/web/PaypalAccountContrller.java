package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.domain.Account;
import com.xuanfeiyang.erp.domain.PaypalAccount;
import com.xuanfeiyang.erp.enums.AccountEnum;
import com.xuanfeiyang.erp.param.AccountParam;
import com.xuanfeiyang.erp.service.AccountService;
import com.xuanfeiyang.erp.service.PaypalAccountService;

@Controller
@RequestMapping("paypalAccount")
public class PaypalAccountContrller extends BaseController{
	
	@Resource
	private PaypalAccountService paypalAccountService;
	@Resource
	private AccountService accountService;
	@Resource
	private MessageSource messageSource;
	
	/**
	 * paypal账号列表
	 * @return
	 */
	@RequestMapping(value = {"", "/"})
	private String getPaypalAccount(Model model){
		List<PaypalAccount> list = paypalAccountService.find();
		model.addAttribute("paypalList", list);
		return "account/paypal/account";
	}
	
	
	/**
	 * 添加汇率信息
	 * @param redirectAttr
	 * @param paypalAccount
	 * @return
	 */
	@RequestMapping("/add")
	public String addRate(RedirectAttributes redirectAttr,PaypalAccount paypalAccount){
		
		boolean bl = paypalAccountService.add(paypalAccount);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "添加成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "添加失败");
		}
		return "redirect:/paypalAccount";
		
	}
	
	/**
	 * 修改汇率信息
	 * @param paypalAccount
	 * @param redirectAttr
	 * @return
	 */
	@RequestMapping("update")
	public String updateRate(PaypalAccount paypalAccount, RedirectAttributes redirectAttr){
		boolean bl = paypalAccountService.update(paypalAccount);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "修改成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "修改失败");
		}
		return "redirect:/paypalAccount";
	}
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping("remove")
	public String remove(Long id, RedirectAttributes redirectAttr){
		
		boolean bl = paypalAccountService.delete(id);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "删除成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "删除失败");
		}
		return "redirect:/paypalAccount";
	}
	
	
	/***
	 * From 表单
	 * 
	 * @param id
	 * @param flag 标记 添加 / 修改
	 * @return
	 */
	@RequestMapping("form")
	public String form(Model model,
			@RequestParam(value="id", required=true, defaultValue="0") Long id,
			@RequestParam(value="flag", required=true, defaultValue="0") Integer flag){
		AccountParam param = new AccountParam();
		param.setAccountType(AccountEnum.EBAY.getValue());
//		List<Account> list = accountService.find(param);
		List<Account> list = null;
		
		if(flag != 0){ //修改
			model.addAttribute("subFlag" ,1);
			PaypalAccount paypalAcc = paypalAccountService.findById(id);
			model.addAttribute("paypalAccAttr", paypalAcc);
			model.addAttribute("accountAttr", list);
			
		}else{//添加
			model.addAttribute("subFlag" ,0);
			model.addAttribute("accountAttr", list);
		}
		

		return "account/paypal/account-form";
		
	}

}

package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

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
import com.xuanfeiyang.erp.domain.BankCard;
import com.xuanfeiyang.erp.param.BankCardParam;
import com.xuanfeiyang.erp.service.BankCardService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@SessionAttributes(App.SESSION_USER_KEY)
@RequestMapping("/bankcard")
public class BankCardController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(BankCardController.class);
	
	@Resource
	private BankCardService bankCardService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = {"","/"})
	public String getBankCard(Model model){
		List<BankCard> bankcards = this.bankCardService.findAll();
		model.addAttribute("bankcards", bankcards);
		return "system/bankcard";
	}
	
	/**
	 * 银行卡管理查询分页
	 * @param curUser
	 * @param dtr
	 * @return
	 */
	@RequestMapping(value="/bankcardPageJson")
	@ResponseBody
	public DataTableResponse<BankCard> bankcardPageJson(
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,
			@RequestBody DataTableRequest<BankCardParam> dtr) {
		Integer userId = curUser.getUserId();
		BankCardParam params = dtr.getParams();
		params.setUserId(userId);
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		
		Page<BankCard> logs = this.bankCardService.getPage(pageRequest, params);
		return WebHelper.assembleDataTableResponse(dtr, logs);
	}
	
	/**
	 * 加载修改银行卡内容的form页面
	 */
	@RequestMapping(value="/form")
	public String editBankcardForm(Model model,
			@RequestParam(value="action") String action,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,
			@RequestParam(value="id",required=false) Integer id){
		Integer userId = curUser.getUserId();
		BankCard bankCard = null;
		logger.info("BankCardController:id:{}",id);
		if(id != null) {
			bankCard = this.bankCardService.findById(id);
		} else {
			bankCard = new BankCard();
		}
		model.addAttribute("bankCard", bankCard);
		model.addAttribute("action", action);
		model.addAttribute("userId", userId);
		return "system/bankcard-form";
	}
	
	/**
	 * 修改菜单模块
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(RedirectAttributes attr,BankCard bankcard,
			@RequestParam(value="action") String action,
			@RequestParam(value="userId") Integer userId){
		
		String view = "redirect:/bankcard";
		if(bankcard == null){
			return view;
		}
		if("update".equals(action)){
			this.bankCardService.update(bankcard);
		} else if("add".equals(action)){
			bankcard.setUserId(userId);;
			this.bankCardService.insert(bankcard);
		}
		
		attr.addFlashAttribute("successMessage", "g.tips.success");
		return view;
	}
	
	/**
	 * 检查银行卡号是否存在
	 * @param cardNumber
	 * @return
	 */
	@RequestMapping("check_cardNumber.json")
	@ResponseBody
	public boolean check(@RequestParam(value="cardNumber") String cardNumber){
		
		if(cardNumber == null){
			return false;
		}
		BankCard bandCard = this.bankCardService.load(cardNumber);
		if(bandCard != null){
			return false;
		}
		return true;
	}
	
	/**
	 * 删除银行卡信息
	 * @param model
	 * @param redirectAttr
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="id") Integer id) {
		
		boolean result = this.bankCardService.delete(id);
		if(result){
			redirectAttr.addFlashAttribute("successMessage", "删除成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "删除失败");
		}
		return "redirect:/bankcard";
	}
	
}

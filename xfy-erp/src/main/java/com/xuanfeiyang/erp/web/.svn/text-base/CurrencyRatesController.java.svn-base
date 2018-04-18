package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.domain.CurrencyRates;
import com.xuanfeiyang.erp.service.CurrencyRatesService;

@Controller
@RequestMapping("currencyRates")
public class CurrencyRatesController extends BaseController{
	@Resource
	private CurrencyRatesService currencyRatesService;
	
	
	@Resource
	private MessageSource messageSource;
	
	/**
	 * 获得汇率列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = {"", "/"})
	public String getCurrencyRates(Model model) {
		List<CurrencyRates> list = currencyRatesService.find();
		model.addAttribute("currencyRates", list);
		return "system/currencyRates";
	}
	
	/**
	 * 添加汇率信息
	 * @param currencyRates
	 */
	@RequestMapping("/add")
	public String  addRate(RedirectAttributes redirectAttr,CurrencyRates currencyRates){
		boolean bl = currencyRatesService.add(currencyRates);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "保存成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "保存失败");
		}
		return "redirect:/currencyRates";
		
	}
	
	
	
	/**
	 * 根据id删除记录
	 * @param id
	 */
	@RequestMapping("remove")
	public String  remove(Long id,RedirectAttributes redirectAttr){
		boolean bl = currencyRatesService.delete(id);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "删除成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "删除失败");
		}
		return "redirect:/currencyRates";
	}
	

	@RequestMapping("update")
	public String update(CurrencyRates currencyRates,RedirectAttributes redirectAttr){
		boolean bl = currencyRatesService.modify(currencyRates);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "修改成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "修改失败");
		}
		return "redirect:/currencyRates";
	}
	
	
	/**
	 * form表单
	 * @return
	 */
	@RequestMapping("form")
	public String form(Model model,
			@RequestParam(value="id", required=true, defaultValue="0") Long id,
			@RequestParam(value="flag", required=true, defaultValue="0") Integer flag){
		if(flag != 0){//修改
			CurrencyRates currencyRates = currencyRatesService.findById(id);
			model.addAttribute("rateAttr", currencyRates);
			model.addAttribute("subFlag", "1");
		}else{
			model.addAttribute("subFlag", "0"); //添加标示
		}
		
		return "system/currencyRates-form";
	}
	

}

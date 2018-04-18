package com.xuanfeiyang.erp.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.SellerConfig;
import com.xuanfeiyang.erp.domain.User;
import com.xuanfeiyang.erp.service.SellerConfigService;

/**
 * 
 * 卖家相关设置
 * @author kenny
 *
 */
@RequestMapping("/sellerConfig")
@Controller
public class SellerConfigController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(SellerConfigController.class);
	@Resource
	private SellerConfigService sellerConfigService;
	@Resource
	private MessageSource messageSource;
	
	/**
	 * 卖家发货设置（添加）
	 * @param sellerConfig
	 * @param redirectAttr
	 * @return
	 */
	@RequestMapping("editShipping")
	public String editShipping(SellerConfig sellerConfig,RedirectAttributes redirectAttr,HttpSession session){
		User user = (User)session.getAttribute(App.SESSION_USER_KEY);
		if(user.getSellerId() != null){
			
			sellerConfig.setSellerId(user.getSellerId().longValue());
			boolean bl = sellerConfigService.saveShip(sellerConfig);
			if(bl){
				redirectAttr.addFlashAttribute("successMessage", "设置成功");
			}else{
				redirectAttr.addFlashAttribute("successMessage", "设置失败");
			}
			
		}else{
				redirectAttr.addFlashAttribute("successMessage", "暂没卖家信息，设置无效");
		}
		
		return "redirect:/sellerConfig/setShipping";
	}
	
	/**
	 * 修改发货设置（修改）
	 * @param sellerConfig
	 * @param redirectAttr
	 * @return
	 */
	@RequestMapping("update")
	public String update(SellerConfig sellerConfig,RedirectAttributes redirectAttr){
		boolean bl = sellerConfigService.updateShip(sellerConfig);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "设置成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "设置失败");
		}
		return "redirect:/sellerConfig/setShipping";
	}
	
	/**
	 * 卖家发货设置页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/setShipping")
	public String setShipping(Model model,SellerConfig sellerConfig,HttpSession session){
		User user = (User)session.getAttribute(App.SESSION_USER_KEY);
		try {
			
			 sellerConfigService.getById(user.getSellerId().longValue());
			
		} catch (Exception e) {
			logger.error("SellerConfig==>"+e);
		}
		
		if(sellerConfig.getShipType() != null ){ //表示发货已经设置
			model.addAttribute("sellerConfigAttr",sellerConfig);
			model.addAttribute("subFlag", 1);
		}else{
			model.addAttribute("subFlag", 0);
		}
		return "system/seller-shipping";
	}
	

}

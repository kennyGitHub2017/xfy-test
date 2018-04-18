package com.xuanfeiyang.erp.web;

import java.util.Date;

import javax.annotation.Resource;

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
import com.xuanfeiyang.erp.domain.GoodsSupplierPrice;
import com.xuanfeiyang.erp.service.GoodsSupplierPriceService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/supplier-price")
@SessionAttributes(App.SESSION_USER_KEY)
public class GoodsSupplierPriceController {
	
	@Resource
	private GoodsSupplierPriceService goodsSupplierPriceService;
	
	@RequestMapping({ "", "/" })
	public String index() {
		return "goods/supplier-price";
	}
	
	@RequestMapping(value="page-json")
	@ResponseBody
	public DataTableResponse<GoodsSupplierPrice> pageJson(
			@RequestBody DataTableRequest<GoodsSupplierPrice> dtr) {
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<GoodsSupplierPrice> page = this.goodsSupplierPriceService.findPage(pageRequest, dtr.getParams());

		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	@RequestMapping("/form")
	public String form(Model model, 
			@RequestParam(value="id", required=false) Integer id) {
		GoodsSupplierPrice gsp = null;
		if (id != null) {
			gsp = this.goodsSupplierPriceService.load(id);
		} else {
			gsp = new GoodsSupplierPrice();
			gsp.setStartDate(new Date()); // 默认当天
		}
		model.addAttribute("model", gsp);
		
		return "goods/supplier-price-form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttr,
			GoodsSupplierPrice gsp,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser) {
		if (gsp.getId() == null) {
			gsp.setOperUserId(sessionUser.getUserId());
			this.goodsSupplierPriceService.save(gsp);
		} else {
			this.goodsSupplierPriceService.update(gsp);
		}
		
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		// 带回SKU参数以便列表页面只显示此SKU的报价
		redirectAttr.addAttribute("sku", gsp.getGoodsSku());
		
		return "redirect:/supplier-price";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="id", required=true) Integer id) {
		
		this.goodsSupplierPriceService.delete(id);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:/supplier-price";
	}
}

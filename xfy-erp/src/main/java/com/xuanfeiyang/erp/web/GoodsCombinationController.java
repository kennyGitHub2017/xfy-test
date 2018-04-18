package com.xuanfeiyang.erp.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsCombination;
import com.xuanfeiyang.erp.service.GoodsCombinationService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("goods-combination")
public class GoodsCombinationController extends BaseController {
	
	@Resource
	private GoodsCombinationService goodsCombinationService;
	
	@RequestMapping(value={"", "/"})
	public String index() {
		return "goods/goods-combination";
	}
	
	@RequestMapping(value="page.json")
	@ResponseBody
	public DataTableResponse<GoodsCombination> pageJson(
			@RequestBody DataTableRequest<?> dtr) {

		String keywords = dtr.getSearch() == null ? null : dtr.getSearch().getValue();
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<GoodsCombination> page = this.goodsCombinationService.findPageWithItems(pageRequest, keywords);

		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	@RequestMapping(value = "delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer", defaultValue="/goods-combination") String referer,
			@RequestParam(value="id", required=true) Integer id) {
		
		this.goodsCombinationService.delete(id);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:" + referer;
	}

	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String form(Model model, 
			@RequestParam(value="id", required=false) Integer id) {
		
		GoodsCombination gc = new GoodsCombination();
		if (id != null) {
			gc = this.goodsCombinationService.loadWithItems(id);
		} else {
			gc = new GoodsCombination();
		}
		
		model.addAttribute("gc", gc);
		
		return "goods/goods-combination-form";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Model model, RedirectAttributes redirect,
			@RequestParam(value="id", required=false) Integer id,
			@RequestParam(value="combSku", required=true) String combSku,
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="itemsStr", required=true) String itemsStr,
			@RequestParam(value="note", required=false) String note) {
		
		try {
			this.goodsCombinationService.save(id, combSku, name, note, itemsStr);
			redirect.addFlashAttribute("successMessage", "g.tips.success");
		} catch (RuntimeException e) {
			redirect.addFlashAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			redirect.addFlashAttribute("errorMessage", e.getMessage());
		}
		
		return "redirect:/goods-combination";
	}
	
}

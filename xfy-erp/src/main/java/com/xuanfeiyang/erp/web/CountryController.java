package com.xuanfeiyang.erp.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Country;
import com.xuanfeiyang.erp.service.CountryService;

@Controller
@RequestMapping("/country")
public class CountryController extends BaseController{
	@Resource
	private CountryService countryService;
	
	@Resource
	private MessageSource messageSource;
	
	/**
	 * 获得地区信息
	 * @return
	 */
	@RequestMapping(value = {"", "/"})
	public String getCountry(Model model){
		List<Country> List = countryService.find();
		model.addAttribute("countryList", List);
		return "system/country";
	}
	
	
	/**
	 * 添加地区
	 * @param country
	 * @return
	 */
	@RequestMapping("/add")
	public String add(RedirectAttributes redirectAttr, Country country){
		boolean bl = countryService.save(country);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "添加成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "添加失败");
		}
		
		
		return "redirect:/country";
	}
	
	
	/**
	 * 修改地区信息
	 * @param redirectAttr
	 * @param country
	 * @return
	 */
	@RequestMapping("/update")
	public String update(RedirectAttributes redirectAttr, Country country){
		boolean bl = countryService.modify(country);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "修改成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "修改失败");
		}
		return "redirect:/country";
	}
	
	
	/***
	 * 根据id删除地区信息
	 * @return
	 */
	@RequestMapping("remove")
	public String  remove(Integer id,RedirectAttributes redirectAttr){
		boolean bl = countryService.deleteById(id);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "删除成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "删除失败");
		}
		return "redirect:/country";
	}
	
	/**
	 * FORM 表单
	 * @return
	 */
	@RequestMapping("form")
	public String form(Model model,
			@RequestParam(value="id", required=true, defaultValue="0") Integer id,
			@RequestParam(value="flag", required=true, defaultValue="0") Integer flag){

		if(flag != 0){ //修改[1]
			Country country = countryService.findCountryById(id);
			model.addAttribute("countAttr",country);
			model.addAttribute("subFlag", 1);//修改
		}else{
			model.addAttribute("subFlag", 0);//添加
		}
		
		return "system/country-form";
	}
	
	
	/**
	 * 
	 * @param draw
	 * @param start
	 * @param pageSize
	 * @param keywords
	 * @return
	 */
	@RequestMapping("/pageJson")
	@ResponseBody
	public Map<String, Object> pageJson(@RequestParam(value="draw", required=false) Integer draw,
			@RequestParam(value="start", required=false) Integer start,
			@RequestParam(value="length", required=false) Integer pageSize,
			@RequestParam(value="search[value]", required=false) String keywords) {
		int pageNo = start / pageSize;
		PageRequest pageRequest = new PageRequest(pageNo, pageSize);
		Page<Country> userPage = this.countryService.findPageCountry(pageRequest, keywords);
		Map<String, Object> page = new LinkedHashMap<>();
		page.put("draw", draw != null ? draw + 1 : 1);
		page.put("recordsTotal", userPage.getTotalElements());
		page.put("recordsFiltered", userPage.getTotalElements());
		page.put("data", userPage.getContent());
		
		return page;
	}
	


}

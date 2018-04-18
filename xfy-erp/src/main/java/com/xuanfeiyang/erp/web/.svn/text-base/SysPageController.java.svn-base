package com.xuanfeiyang.erp.web;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.domain.SysPage;
import com.xuanfeiyang.erp.service.SysPageService;

@Controller
@RequestMapping(value="/sys-page")
public class SysPageController extends BaseController{
	
	@Resource
	private SysPageService sysPageService;
	
	/**
	 * 加载修改菜单的页面内容的form页面
	 */
	@RequestMapping(value="/form")
	public String editPageForm(Model model,
			@RequestParam(value="action") String action,
			@RequestParam(value="code",required=false) String code){
		SysPage page = null;
		if(code != null){
			page = this.sysPageService.load(code);
		} else {
			page = new SysPage();
		}
		model.addAttribute("page", page);
		model.addAttribute("action", action);
		return "system/sys-page-form";
	}
	
	/**
	 * 修改菜单页面内容
	 */
	@RequestMapping(value="/update")
	public String update(RedirectAttributes attr,SysPage page,
			@RequestParam(value="action") String action){
		
			String view = "redirect:/sys-module";
			if(page == null){
				return view;
			}
			if("update".equals(action)){
				this.sysPageService.update(page);
			} else if("add".equals(action)){
				this.sysPageService.insert(page);
			}
			
			attr.addFlashAttribute("successMessage", "g.tips.success");
			return view;
		}
	
	
	/**
	 * 检查页面编码code是否存在
	 * @param code
	 * @return
	 */
	@RequestMapping("check_page_code.json")
	@ResponseBody
	public boolean check(@RequestParam(value="code") String code){
		
		if(code == null){
			return false;
		}
		SysPage page = this.sysPageService.load(code);
		if(page != null){
			return false;
		}
		return true;
		
	}
	
	/**
	 * 删除菜单页面
	 * @param model
	 * @param redirectAttr
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="code") String code) {
		
		this.sysPageService.delete(code);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:/sys-module";
	}
}

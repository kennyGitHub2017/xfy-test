package com.xuanfeiyang.erp.web;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.domain.SysFunction;
import com.xuanfeiyang.erp.service.SysFunctionService;

@Controller
@RequestMapping(value="/sys-function")
public class SysFunctionController extends BaseController {
	
	@Resource
	private SysFunctionService sysFunctionService;
	
	/**
	 * 加载修改菜单的功能内容的form页面
	 */
	@RequestMapping(value="/form")
	public String editFunctionForm(Model model,
			@RequestParam(value="action") String action,
			@RequestParam(value="code",required=false) String code){
		SysFunction function = null;
		if(code != null){
			function = this.sysFunctionService.load(code);
		} else {
			function = new SysFunction();
		}
		model.addAttribute("function", function);
		model.addAttribute("action", action);
		return "system/sys-function-form";
	}
	
	/**
	 * 修改菜单功能的页面内容
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(RedirectAttributes attr,SysFunction function,
			@RequestParam(value="action") String action){
			
			String view = "redirect:/sys-module";
			if(function == null){
				return view;
			}
			if("update".equals(action)){
				this.sysFunctionService.update(function);
			} else if("add".equals(action)){
				this.sysFunctionService.insert(function);
			}
			
			attr.addFlashAttribute("successMessage", "g.tips.success");
			return view;
		}
	
	/**
	 * 检查功能编码code是否存在
	 * @param code
	 * @return
	 */
	@RequestMapping("check_function_code.json")
	@ResponseBody
	public boolean check(@RequestParam(value="code") String code){
		
		if(code == null){
			return false;
		}
		SysFunction function = this.sysFunctionService.load(code);
		if(function != null){
			return false;
		}
		return true;
	}
	
	
	/**
	 * 删除菜单功能
	 * @param model
	 * @param redirectAttr
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="code") String code) {
		
		this.sysFunctionService.delete(code);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:/sys-module";
	}
	
}

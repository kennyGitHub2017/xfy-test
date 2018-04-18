package com.xuanfeiyang.erp.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuanfeiyang.erp.service.SysPowerService;

/**
 * 
 * @author Adam
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Resource
	private SysPowerService sysPowerService;
	
	@RequestMapping("/powers")
	public String powers(Model model) {
		
		model.addAttribute("modules", this.sysPowerService.findModules());
		return "system/sys-powers";
	}
		
	@RequestMapping("/modules")
	public String modules() {
		
		return "system/sys-modules";
	}
	
	@RequestMapping("/pages")
	public String pages() {
		return "system/sys-pages";
	}

	@RequestMapping("/functions")
	public String functions() {
		return "system/sys-functions";
	}
	
}

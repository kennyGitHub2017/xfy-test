package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.domain.SysModule;
import com.xuanfeiyang.erp.service.SysModuleService;

@Controller
@RequestMapping(value="/sys-module")
public class SysModuleController extends BaseController{
	
	@Resource
	private SysModuleService sysModuleService;
	
	/**
	 * 加载编辑菜单页面
	 * @author Administrator
	 *
	 */
	@RequestMapping({"", "/"})
	public String load(Model model){
		
		List<SysModule> modules = this.sysModuleService.findAll();
		model.addAttribute("modules", modules);
		return "/system/sys-module";
	}
	
	/**
	 * 加载修改菜单模块内容的form页面
	 */
	@RequestMapping(value="/form")
	public String editModuleForm(Model model,
			@RequestParam(value="action") String action,
			@RequestParam(value="code",required=false) String code){
		
		SysModule module = null;
		if(code != null){
			module = this.sysModuleService.load(code);
		}else{
			module = new SysModule();
		}
		
		model.addAttribute("module", module);
		model.addAttribute("action", action);
		return "system/sys-module-form";
	}
	
	
	/**
	 * 修改菜单模块
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(RedirectAttributes attr,SysModule module,
			@RequestParam(value="action") String action){
		
		String view = "redirect:/sys-module";
		if(module == null){
			return view;
		}
		if("update".equals(action)){
			this.sysModuleService.update(module);
		} else if("add".equals(action)){
			this.sysModuleService.insert(module);
		}
		
		attr.addFlashAttribute("successMessage", "g.tips.success");
		return view;
	}
	
	
	
	/**
	 * 检查菜单编码code是否存在
	 * @param code
	 * @return
	 */
	@RequestMapping("check_module_code.json")
	@ResponseBody
	public boolean check(@RequestParam(value="code") String code){
		
		if(code == null){
			return false;
		}
		SysModule module = this.sysModuleService.load(code);
		if(module != null){
			return false;
		}
		return true;
		
		
	}
	
	/**
	 * 删除菜单模块
	 * @param model
	 * @param redirectAttr
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="code") String code) {
		
		this.sysModuleService.delete(code);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:/sys-module";
	}
	
}

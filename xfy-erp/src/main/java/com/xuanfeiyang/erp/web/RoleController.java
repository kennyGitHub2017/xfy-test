package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.domain.Role;
import com.xuanfeiyang.erp.domain.SysModule;
import com.xuanfeiyang.erp.service.RoleService;
import com.xuanfeiyang.erp.service.SysModuleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private SysModuleService sysModuleService;

	@RequestMapping({"", "/"})
	public String index(Model model) {
		
		List<Role> roles = this.roleService.find();
		model.addAttribute("items", roles);
		
		return "system/role";
	}
	
	/**
	 * 加载单个数据的form页面
	 */
	@RequestMapping("/form")
	public String form(Model model, 
			@RequestParam(value="id", required=false) Integer id) {
		
		Role role = null;
		
		if (id != null) {
			role = this.roleService.load(id);
		} else {
			role = new Role();
		}
		
		model.addAttribute("role", role);
		
		return "system/role-form";
	}
	
	/**
	 * 保存
	 * @param redirectAttr
	 * @param dict
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttr, Role role) {
		
		String view = "redirect:/role";
		
		if (role == null) {
			return view;
		}
		
		if (role.getId() == null) {
			this.roleService.save(role);
		} else {
			this.roleService.update(role);
		}
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return view;
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="id") Integer id) {
		
		this.roleService.delete(id);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:/role";
	}
	
	@RequestMapping(value = "/power", method = RequestMethod.GET)
	public String powerPage(Model model,
			@RequestParam(value="id") Integer roleId) {
		
		List<SysModule> modules = this.sysModuleService.findAll();
		model.addAttribute("modules", modules);
		
		model.addAttribute("powersMap", this.roleService.findRolePowers(roleId));
		
		return "system/role-power";
	}

	@RequestMapping(value = "/power", method = RequestMethod.POST)
	public String powerPage(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="id", required=true) Integer roleId,
			@RequestParam(value="modulePower", required=false) List<String> modulePowers,
			@RequestParam(value="pagePower", required=false) List<String> pagePowers,
			@RequestParam(value="functionPower", required=false) List<String> functionPowers) {
		
		this.roleService.saveRolePowers(roleId, modulePowers, pagePowers, functionPowers);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		redirectAttr.addAttribute("id", roleId);
		return "redirect:/role/power";
	}
	
}

package com.xuanfeiyang.erp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.xuanfeiyang.erp.domain.Department;
import com.xuanfeiyang.erp.service.DepartmentService;
import com.xuanfeiyang.erp.service.UserService;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(DepartmentController.class); 
	
	@Resource
	private MessageSource messageSource;
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping({"", "/"})
	public String index(Model model) {
		
		List<Department> departments = this.departmentService.find();		
		model.addAttribute("departments", departments);
		
		return "system/department";
	}
	
	/**
	 * 加载单个数据的form页面
	 */
	@RequestMapping("/form")
	public String form(Model model, 
			@RequestParam(value="id", required=false) Integer id) {
		
		Department department = null;
		
		if (id != null) {
			department = this.departmentService.load(id);
			// 修改标识
			model.addAttribute("updateFlag", "1");
		} else {
			department = new Department();
		}
		
		model.addAttribute("department", department);
		
		model.addAttribute("users", this.userService.findAll());
		
		return "system/department-form";
	}
	
	/**
	 * 添加
	 * @param redirectAttr
	 * @param dict
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttr, Department department) {
		
		logger.info("==> {}", department);
		
		this.departmentService.save(department);
		redirectAttr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		
		return "redirect:/department";
	}
	
	// 更新
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttr, Department department) {
		
		this.departmentService.update(department);
		redirectAttr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		
		
		return "redirect:/department";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="id") Integer id) {
		
		this.departmentService.delete(id);

		redirectAttr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		
		return "redirect:/department";
	}
	
	@RequestMapping(value="check")
	@ResponseBody
	public Map<String,Object> checkDepartment(@RequestParam(value="depName") String depName,@RequestParam(value="id",required=false) Integer id){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		boolean exists = this.departmentService.exists(depName);
		Department dep = null;
		if (id!=null){
			dep = departmentService.load(id);
		}
		if (null!=dep && dep.getName().equals(depName)){
			exists = false; 
		}
		jsonMap.put("result",exists);
		return jsonMap;
	}
}

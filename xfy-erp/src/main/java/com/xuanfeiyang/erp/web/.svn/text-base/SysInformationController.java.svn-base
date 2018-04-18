package com.xuanfeiyang.erp.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.SysInformation;
import com.xuanfeiyang.erp.param.SysInformationParams;
import com.xuanfeiyang.erp.service.SysInformationService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.WebHelper;
@Controller
@RequestMapping("/sysinfo")
public class SysInformationController extends BaseController{
	private static final Logger logger = LoggerFactory
			.getLogger(SysInformationController.class);	
	@Resource
	private SysInformationService sysInformationService;
	@Resource
	private MessageSource messageSource;
	
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
    public String index(Model model) {
		return "system/sysinfo";
	}
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
    public String index(Model model,@RequestParam(required=false,value="type")Integer type) {
		model.addAttribute("type", type);
		return "system/sysinfoview";
	}
	
	@RequestMapping("pageJson")
	@ResponseBody
	public DataTableResponse<SysInformation> pageJson(@RequestBody DataTableRequest<SysInformationParams> dtr) {
		String keywords = dtr.getSearch() == null ? null : dtr.getSearch().getValue();
		SysInformationParams params = (dtr.getParams() != null ? dtr.getParams() : new SysInformationParams());
		params.setKeywords(keywords);
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<SysInformation> page = this.sysInformationService.findPage(pageRequest, dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	
	@RequestMapping(value ="form", method = RequestMethod.GET)
    public String form(Model model,@RequestParam(required=false,value="id")Integer id) {
		SysInformation sifn = null;
		if (id!=null){
			sifn = sysInformationService.load(id);
		}
		sifn = sifn==null?new SysInformation():sifn;
		model.addAttribute("sysinfo", sifn);
		return "system/sysinfo-form";
	}
	
	
	@RequestMapping(value ="edit")
    public String edit(Model model,SysInformation sysinfo,RedirectAttributes attr) {
		try{
			if (sysinfo.getId()==null){
				sysInformationService.insert(sysinfo);
			}else{
				sysInformationService.update(sysinfo);
			}
			attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		}catch(Exception e){
			logger.error(e.getMessage());
			attr.addFlashAttribute("errorMessage",this.messageSource.getMessage("g.tips.error", null, null));
		}
		return "redirect:/sysinfo/list";
	}
	
	
	@RequestMapping(value ="remove", method = RequestMethod.GET)
    public String remove(Model model,Integer id,RedirectAttributes attr) {
		try{
			sysInformationService.delete(id);
			attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		}catch(Exception e){
			logger.error(e.getMessage());;
			attr.addFlashAttribute("errorMessage",this.messageSource.getMessage("g.tips.error", null, null));
		}
		return "redirect:/sysinfo/list";
	}
	
}

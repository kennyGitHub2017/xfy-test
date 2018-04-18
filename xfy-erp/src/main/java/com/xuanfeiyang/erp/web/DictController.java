package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.domain.Dict;
import com.xuanfeiyang.erp.service.DictService;
import com.xuanfeiyang.erp.service.DuplicateDataExcepption;


/**
 * 代码字典表总控制器
 * 
 * @author Adam
 *
 */

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {
	
//	private static Logger logger = LoggerFactory.getLogger(DictController.class);
	
	@Resource
	private DictService dictService;
	
	@Resource
	private MessageSource messageSource;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value="type", required=false) Integer type) {
		/*
		 * type=0 为 预留，用来表示 字典类型。
		 */
		
		List<Dict> typeList = this.dictService.findByType(0);		
		model.addAttribute("typeList", typeList);
		
		// 当前类型
		Dict curType = null;
		if (type != null) {
			for (Dict dict : typeList) {
				if (dict.getCode().equals(type)) {
					curType = dict;
					break;
				}
			}
			
			List<Dict> dictList = this.dictService.findByType(type);			
			model.addAttribute("dictList", dictList);
		} else {
			curType = new Dict();
			curType.setType(0);
			curType.setCode(0);
			curType.setName("字典类型");

			model.addAttribute("dictList", typeList);
		}
		
		model.addAttribute("curType", curType);
		
		return "system/dict";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="type", required=false) Integer type,
			@RequestParam(value="code", required=false) Integer code) {
		
		if (type != null && code != null) {
			this.dictService.delete(type, code);

			redirectAttr.addAttribute("type", type);
			redirectAttr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		}
		
		return "redirect:/dict";
	}
	
	/**
	 * 保存
	 * @param redirectAttr
	 * @param dict
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttr, Dict dict) {
		try {
			this.dictService.save(dict);
			redirectAttr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		} catch (DuplicateDataExcepption e) {
			String msg = e.getMessage();
			if (msg == null) {
				Dict tmp = (Dict) e.getData();
				msg = this.messageSource.getMessage("dict.code.exists", new Object[]{tmp.getCode()}, null);
			}
			redirectAttr.addFlashAttribute("errorMessage", msg);
		}
		
		redirectAttr.addAttribute("type", dict.getType());
		
		return "redirect:/dict";
	}
	
	// 更新
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttr, Dict dict) {
		
		this.dictService.update(dict);
		redirectAttr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		
		redirectAttr.addAttribute("type", dict.getType());
		
		return "redirect:/dict";
	}
	
	/**
	 * 加载单个数据的form页面
	 */
	@RequestMapping("/form")
	public String form(Model model, 
			@RequestParam("type") Integer type,
			@RequestParam(value="code", required=false) Integer code) {
		
		model.addAttribute("type", type);
		if (code != null) {
			model.addAttribute("dict", this.dictService.load(type, code));
			// 修改标识
			model.addAttribute("updateFlag", "1");
		}
		
		return "system/dict-form";
	}
	
}

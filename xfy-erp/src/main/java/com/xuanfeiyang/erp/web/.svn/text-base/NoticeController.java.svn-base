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
import com.xuanfeiyang.erp.domain.Notice;
import com.xuanfeiyang.erp.service.NoticeService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {
	
	@Resource
	private NoticeService noticeService;
	
	@RequestMapping({ "", "/" })
	public String index() {
		return "system/notice";
	}
	
	@RequestMapping(value="page.json")
	@ResponseBody
	public DataTableResponse<Notice> pageJson(
			@RequestParam(value="status", required=false) Integer status,
			@RequestBody DataTableRequest<?> dtr) {
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<Notice> page = this.noticeService.findPage(pageRequest);

		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	@RequestMapping("/form")
	public String form(Model model, 
			@RequestParam(value="id", required=false) Integer id) {
		
		Notice notice = this.noticeService.load(id);
		notice = notice != null ? notice : new Notice();
		model.addAttribute("notice", notice);
		
		return "system/notice-form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer", defaultValue="/notice") String referer,
			Notice notice) {
		String v = "redirect:" + referer;
		if (notice == null) {
			return v;
		}
		
		if (notice.getId() == null) {
			this.noticeService.save(notice);
		} else {
			this.noticeService.update(notice);
		}
		
		return v;
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer", defaultValue="/notice") String referer,
			@RequestParam(value="id", required=true) Integer id) {
		
		this.noticeService.delete(id);
		return "redirect:" + referer;
	}
	
}

package com.xuanfeiyang.erp.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.SellerDao;
import com.xuanfeiyang.erp.domain.PlatFormAccountSimple;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.param.PlatformAccountParams;
import com.xuanfeiyang.erp.service.PlatformAccountService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.WebHelper;


@Controller
@RequestMapping("platform-account")
public class PlatformAccountController {
	@Resource
	private SellerDao sellerDao;
	
	@Resource
	private PlatformAccountService  platformAccountService;
	
	
	@RequestMapping("account-info")
	public String accountInfoLog(Model model,PlatformAccountParams params,Integer sellerId){

		Map<String, List<PlatformAccount>> accounts = new HashMap<>();
		List<PlatformAccount> pas = platformAccountService.find(params);
		for (PlatformAccount acct : pas) {
			
			String platform = acct.getPlatform();
			List<PlatformAccount> accts = accounts.get(platform);
			if (accts == null) {
				accts = new ArrayList<PlatformAccount>();
				accounts.put(platform, accts);
			}
			accts.add(acct);
		}
		
		model.addAttribute("accountPlatform", accounts);
		
		return "system/seller-account-log";
	}
	
	
	@RequestMapping(value="/")
	public String index(Model model){
		model.addAttribute("sellerList",sellerDao.findAssginColumn(2, Arrays.asList("id","contacts")));
		return "system/platformAccount-list";
	}
	
	
	
	@RequestMapping(value="pageJson")
	@ResponseBody
	public DataTableResponse<PlatFormAccountSimple> pageJson(@RequestBody DataTableRequest<PlatformAccountParams> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<PlatFormAccountSimple> page = platformAccountService.findPage(pageRequest, dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	

}

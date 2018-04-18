package com.xuanfeiyang.erp.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.dao.ListingSkuDao;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.ListingSku;
import com.xuanfeiyang.erp.domain.SkuMapping;
import com.xuanfeiyang.erp.service.OrderItemService;
import com.xuanfeiyang.erp.service.SkuMappingService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/sku-mapping")
public class SkuMappingController extends BaseController {
	
	@Resource
	private SkuMappingService skuMappingService;
	
	@Resource
	private ListingSkuDao listingSkuDao; 
	
	@Resource
	private OrderItemService orderItemService;
	
	@Resource
	private GoodsDao goodsDao;
	
	/**
	 * 加载SKU映射维护页面
	 * @return
	 */
	@RequestMapping({ "", "/" })
	public String index() {
		return "system/sku-mapping";
	} 
	
	
	/**
	 * 加载listing SKU映射维护页面
	 * @return
	 */
	@RequestMapping("listing-sku")
	public String listingSku(Model model,@RequestParam(required=false)String platForm,@RequestParam(required=false)String sku) {
		if (StringUtils.isNotBlank(platForm)){
			List<ListingSku> skuList = listingSkuDao.getListByplatFormType(platForm,sku);
			matchGoodsSku(skuList);
			model.addAttribute("listSku", skuList);
		}
		model.addAttribute("platForm", platForm);
		model.addAttribute("sku", sku);
		return "system/listing-sku";
	}
	

	private void matchGoodsSku(List<ListingSku> skuList){
		if (skuList==null || skuList.size()<1){
			return;
		}
		for(ListingSku sku:skuList){
			String oldSku =  orderItemService.getOldSkuFromPlatformSku(sku.getSku());
			if (StringUtils.isEmpty(oldSku)){
				continue;
			}
			sku.setGoodsSku(goodsDao.findGoodsSkuByOldSku(oldSku));
		}
	}
	
	//内部类
	static class ListingSkuList{
		private List<ListingSku>  listSku;

		public List<ListingSku> getListSku() {
			return listSku;
		}

		public void setListSku(List<ListingSku> listSku) {
			this.listSku = listSku;
		}
	}
	
	//保存listing-sku映射
	@RequestMapping(value="save-listing-sku",method = RequestMethod.POST)
	public String saveListingSku(Model model,ListingSkuList skuList,RedirectAttributes redirectAttr,String platForm) {
		try{
			for(ListingSku lsu:skuList.getListSku()){
				if (StringUtils.isEmpty(lsu.getGoodsSku()) || !lsu.getGoodsSku().matches("^[0-9]{9,10}$")){
					continue;
				}
				listingSkuDao.update(lsu);
				insertIntoSkuMapping(lsu);
			}
			redirectAttr.addAttribute("platForm", platForm);
			redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		}catch(Exception e){
			e.printStackTrace();
			redirectAttr.addFlashAttribute("successMessage", "g.tips.error");
		}
		return "redirect:/sku-mapping/listing-sku";
	}
	
	private void insertIntoSkuMapping(ListingSku listingSku){
		SkuMapping  skuMapping = skuMappingService.load(listingSku.getSku());
		if (skuMapping!=null){
			return;
		}
		Goods g = goodsDao.findBySku(listingSku.getGoodsSku());
		skuMapping = new SkuMapping();
		skuMapping.setOldSku(g.getOldSku());
		skuMapping.setCreateDate(new Date());
		skuMapping.setNewSku(listingSku.getGoodsSku());
		skuMapping.setPlatformSku(listingSku.getSku());
		skuMappingService.insert(skuMapping);
	}
	
	
	/**
	 * 分页查询
	 * @param dtr
	 * @return
	 */
	@RequestMapping(value="page.json")
	@ResponseBody
	public DataTableResponse<SkuMapping> pageJson(
			@RequestBody DataTableRequest<SkuMapping> dtr) {
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<SkuMapping> page = this.skuMappingService.findPage(pageRequest, dtr.getParams());

		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	
	/**
	 * 加载修改SKU映射内容的form页面
	 */
	@RequestMapping("/form")
	public String form(Model model, 
			@RequestParam(value="action") String action,
			@RequestParam(value="platformSku",required=false) String platformSku) {
		SkuMapping skuMapping = null;
		if (platformSku != null) {
			skuMapping = this.skuMappingService.load(platformSku);
		} else {
			skuMapping = new SkuMapping();
		}
		model.addAttribute("skuMapping", skuMapping);
		model.addAttribute("action", action);
		return "system/sku-mapping-form";
	}
	
	
	/**
	 * 修改SKU映射内容
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(RedirectAttributes attr,SkuMapping skuMapping,
			@RequestParam(value="action") String action){
		
		String view = "redirect:/sku-mapping";
		if(skuMapping == null){
			return view;
		}
		if("update".equals(action)){
			this.skuMappingService.update(skuMapping);
		} else if("add".equals(action)){
			this.skuMappingService.insert(skuMapping);
		}
		attr.addFlashAttribute("successMessage", "g.tips.success");
		return view;
	}
	
	/**
	 * 检查平台SKU是否存在
	 * @param code
	 * @return
	 */
	@RequestMapping("check_platform_sku.json")
	@ResponseBody
	public boolean check(@RequestParam(value="platformSku") String platformSku){
		
		if(platformSku == null){
			return false;
		}
		SkuMapping sku = this.skuMappingService.load(platformSku);
		if(sku != null){
			return false;
		}
		return true;
	}
	
	/**
	 * 删除SKU映射
	 * @param model
	 * @param redirectAttr
	 * @param platformSku
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="platformSku") String platformSku) {
		
		this.skuMappingService.delete(platformSku);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:/sku-mapping";
	}
	
}

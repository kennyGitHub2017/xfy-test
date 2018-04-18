package com.xuanfeiyang.erp.web;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.GoodsCategory;
import com.xuanfeiyang.erp.domain.GoodsTransferOrder;
import com.xuanfeiyang.erp.domain.Store;
import com.xuanfeiyang.erp.domain.StoreShelf;
import com.xuanfeiyang.erp.param.GoodsTransferOrderParam;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
import com.xuanfeiyang.erp.service.GoodsTransferOrderService;
import com.xuanfeiyang.erp.service.InventoryShortageException;
import com.xuanfeiyang.erp.service.StoreService;
import com.xuanfeiyang.erp.service.TableKeyService;
import com.xuanfeiyang.erp.service.XxNoGenerateService;
import com.xuanfeiyang.erp.service.XxNoType;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/allocateOrder")
@SessionAttributes(App.SESSION_USER_KEY)
public class GoodsTransferOrderController extends BaseController{
	@Resource
	private GoodsTransferOrderService goodsTransferOrderService;
	
	@Resource
	private GoodsCategoryService goodsCategoryService; 
	
	@Resource
	private StoreService storeService; 
	
	@Resource
	private MessageSource messageSource;
	
	@Resource
	private XxNoGenerateService xxNoGenerateService; 
	
	@Resource
	private TableKeyService tableKeyService; 
	
	private DateFormat dft = new SimpleDateFormat("yyyyMMdd");
	
	private static final Logger logger = LoggerFactory
			.getLogger(GoodsTransferOrderController.class);

	
	public void setGoodsTransferOrderService(
			GoodsTransferOrderService goodsTransferOrderService) {
		this.goodsTransferOrderService = goodsTransferOrderService;
	}

	public void setXxNoGenerateService(XxNoGenerateService xxNoGenerateService) {
		this.xxNoGenerateService = xxNoGenerateService;
	}


	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}


	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public void setGoodsCategoryService(GoodsCategoryService goodsCategoryService) {
		this.goodsCategoryService = goodsCategoryService;
	}

	@RequestMapping("list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView();
		List<GoodsCategory> firstCategory = this.goodsCategoryService.findByParentId(0);
		GoodsTransferOrderParam param = new GoodsTransferOrderParam();
		mv.addObject("categoryList", firstCategory);
		mv.addObject("search",param);
		List<Store> allstore = storeService.findStroe();
		mv.addObject("storeList", allstore);
		mv.setViewName("goods/allocateorder-list");
		return mv;
	}
	
	@RequestMapping("form")
	public ModelAndView form(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("allocateorder", new GoodsTransferOrder());
		List<Store> allstore = storeService.findStroe();
		mv.addObject("storeList", allstore);
		mv.setViewName("goods/allocateorder-form");
		return mv;
	}
	
	@RequestMapping("pageJson")
	@ResponseBody
	public DataTableResponse<GoodsTransferOrder> GoodsTransferOrderPageJson(@RequestBody DataTableRequest<GoodsTransferOrderParam> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<GoodsTransferOrder> page = this.goodsTransferOrderService.findPage(pageRequest,dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	
	@RequestMapping("add")
	public ModelAndView add(GoodsTransferOrder gaor,RedirectAttributes attr,HttpServletRequest request,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		try{
			gaor.setOrderNo(xxNoGenerateService.generate(XxNoType.DB));
			gaor.setOperatorId(curUser.getUserId());
			String dateStr = dft.format(Calendar.getInstance().getTime());
			String fmt =String.format("LS-%s-",dateStr)+"%d";
			gaor.setSerialNumber(tableKeyService.nextSerialNumber("goods_transfer_order",fmt));
			this.goodsTransferOrderService.add(gaor);
			logger.info("add  GoodsTransferOrder id:" + gaor.getId());
			attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		}catch(InventoryShortageException e){
			attr.addFlashAttribute("errorMessage", e.getGoodsInventory().getGoodsSku() + "库存不足");
		}
		 return new ModelAndView("redirect:/allocateOrder/list");
	}
	
	@RequestMapping(value="getStoreShelf")
	@ResponseBody
	public Map<String,Object> getChildCategory(@RequestParam(value="storeId") Integer storeId){
		Map<String,Object> retMap = new HashMap<String,Object>();
		try{
			StoreShelf ssfobj = null;
			List<StoreShelf> data  = new ArrayList<StoreShelf>();
			List<StoreShelf> ssfList =  storeService.findShelfByStore(storeId);
			for(StoreShelf ssf:ssfList){
				ssfobj = new StoreShelf(ssf.getId(),ssf.getCode());
				data.add(ssfobj);
			}
			retMap.put("result",true);
			retMap.put("datas",data);
		}catch(Exception e){
			retMap.put("result",false);
		}
		return retMap;
	}
	
}
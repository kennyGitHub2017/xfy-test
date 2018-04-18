package com.xuanfeiyang.erp.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.PurchaseRequestOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseWarn;
import com.xuanfeiyang.erp.param.PurchaseWarnParam;
import com.xuanfeiyang.erp.service.DictService;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
import com.xuanfeiyang.erp.service.GoodsSupplierPriceService;
import com.xuanfeiyang.erp.service.PurchaseWarnService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;
@Controller
@RequestMapping("/purchase-warn")
@SessionAttributes(App.SESSION_USER_KEY)
public class PurchaseWarnControl extends BaseController{
	@Resource
	private GoodsCategoryService goodsCategoryService; 
	@Resource
	private PurchaseWarnService purchaseWarnService;
	@Resource
	private DictService dictService;
	@Resource
	private  GoodsSupplierPriceService goodsSupplierPriceService;  
	
	@RequestMapping("/")
	public ModelAndView page(ModelAndView model){
		model.addObject("categoryList", goodsCategoryService.findByParentId(0));
		model.addObject("search",new PurchaseWarnParam());
		model.addObject("goodsStatus", this.dictService.findByType(102));
		model.setViewName("goods/purchase-warn");
		return model;
	}
	
	@RequestMapping("list")
	public ModelAndView list(ModelAndView model,
			@RequestParam(value="goodsSku",required=false)String goodsSku,
			@RequestParam(value="goodsStatus",required=false)Integer goodsStatus,
			@RequestParam(value="firstCategory",required=false)String firstCategory,
			@RequestParam(value="secondCategory",required=false)String secondCategory,
			@RequestParam(value="thirdCategory",required=false)String thirdCategory,
			@RequestParam(value="priorityRule",required=false)String priorityRule){
		
		PurchaseWarnParam param = new PurchaseWarnParam(firstCategory,secondCategory,
				thirdCategory,priorityRule,goodsStatus,goodsSku);
		List<String> skuList = purchaseWarnService.find(param);		//获取所有需要采购的sku
		List<PurchaseWarn> allSkuDetail = new ArrayList<>();
		if (null!=skuList && skuList.size()>0){
			for(String sku:skuList){
				Integer prorityRule = StringUtils.isEmpty(param.getPriorityRule())?0:Integer.valueOf(param.getPriorityRule());
				PurchaseWarn item = purchaseWarnService.detail(sku,prorityRule);
				item.setSuggestPurchase(item.getUnAudit()+item.getNeedPurchase()+item.getSalesVolume()-item.getAvailableCount());	//建议采购量＝销售订单量（订单状态为未审核+订单审核后产生的待采购）－可用库存+ 销量
				item.setActualPurchase(item.getSuggestPurchase());
				if (item.getSuggestPurchase()>0){
					allSkuDetail.add(item);
				}
			}
			model.addObject("dataList",allSkuDetail);
		}
		param.setPriorityRule(StringUtils.isEmpty(param.getPriorityRule())?"0":param.getPriorityRule());
		model.addObject("search",param);
		model.addObject("categoryList", goodsCategoryService.findByParentId(0));
		model.addObject("goodsStatus", this.dictService.findByType(102));
		if (StringUtils.isNotBlank(secondCategory)){
			model.addObject("secondCategoryList", goodsCategoryService.findByParentId(Integer.valueOf(firstCategory)));
		}
		if (StringUtils.isNotBlank(thirdCategory)){
			model.addObject("thirdCategoryList", goodsCategoryService.findByParentId(Integer.valueOf(secondCategory)));
		}
		model.setViewName("goods/purchase-warn");
		return model;
	}
	
	
	@RequestMapping("pageJson")
	@ResponseBody
	public DataTableResponse<PurchaseWarn> purchaseWarnOrderPageJson(@RequestBody DataTableRequest<PurchaseWarnParam> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<PurchaseWarn> page = this.purchaseWarnService.findPage(pageRequest,dtr.getParams());
		for(PurchaseWarn item:page.getContent()){
			int temp = item.getSales7()+item.getUnOutstoreSale()-item.getAllCount()-item.getPurchaseCount();    // 采购周期<7 天       建议采购量＝7天销量+销售占用－实际库存－在途
			//采购周期>=7天
			if (item.getBuyPeriod()!=null && item.getBuyPeriod()>=7){
				temp =	2* item.getSales7()+item.getUnOutstoreSale()-item.getAllCount()-item.getPurchaseCount();  //建议采购量＝（2*7天销量+销售占用－实际库存－在途） 
			}
			if (temp>10){
				int modV = temp%10;
				temp = modV<5?(temp-modV):(temp+10-modV);  //四舍五入
			}
			item.setSuggestPurchase(temp);
			item.setActualPurchase(item.getSuggestPurchase());
			//根据获取规则设置相应的建议供应商 、建议价、周期
			/*
			PurchaseWarnParam param = dtr.getParams();
			GoodsSupplierPrice gsp = goodsSupplierPriceService.findByPriorityRuleSku(item.getGoodsSku(),Integer.valueOf(param.getPriorityRule()));
			if (gsp!=null){
				item.setBuyPeriod(Integer.valueOf(gsp.getBuyPeriod()));
				item.setSuggestBuyprice(gsp.getPrice().floatValue());
				item.setSuggestSupplier(gsp.getSupplierName());
				item.setSupplierId(gsp.getSupplierId());
			}
			*/
		}
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	
	/**
	 * 选中sku行,生成请购单
	 * @return
	 */
	@RequestMapping("generate-reqorder.json")
	@ResponseBody
	public Map<String,Object> generatePurchaseRequestOrder(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,
			@RequestParam(value="suplierId",required=true)Integer suplierId[],
			@RequestParam(value="sku",required=true)String sku[],@RequestParam(value="name",required=true)String name[],@RequestParam(value="unit",required=true)String unit[],
			@RequestParam(value="category",required=true)Integer category[],@RequestParam(value="price",required=true)Float price[],@RequestParam(value="actualPurchase",required=true)Integer actualPurchase[]){
		Map<Integer,List<PurchaseRequestOrderItem>> suplierItemMap = new HashMap<>();
		Map<String,Object> retMap = new HashMap<String,Object>();
		 int idx=0;
		try{
			 for(Integer sid:suplierId){
				 PurchaseRequestOrderItem item = new PurchaseRequestOrderItem();
				 item.setGoodsSku(sku[idx]);
				 item.setGoodsName(name[idx]);
				 item.setGoodsUnit(unit[idx]);
				 item.setGoodsCategory(category[idx]);
				 item.setGoodsCost(BigDecimal.valueOf(price[idx]));
				 item.setGoodsCount(actualPurchase[idx]);
				 if (!suplierItemMap.containsKey(sid)){
					 List<PurchaseRequestOrderItem> list = new ArrayList<PurchaseRequestOrderItem>();
					 list.add(item);
					 suplierItemMap.put(sid,list);
				 }else{
					 suplierItemMap.get(sid).add(item);
				 }
				 idx++;
			 }
			 purchaseWarnService.generatePurchaseRequestOrder(suplierItemMap,curUser.getUserId());
			 retMap.put("result", true);
		}catch(Exception e){
			retMap.put("result", false);
			retMap.put("error", e.getMessage());
		}
		 return retMap;
	}
	
}

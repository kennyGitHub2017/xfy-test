package com.xuanfeiyang.erp.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsCategory;
import com.xuanfeiyang.erp.domain.GoodsSupplierPrice;
import com.xuanfeiyang.erp.domain.PurchaseRequestOrder;
import com.xuanfeiyang.erp.domain.PurchaseRequestOrderItem;
import com.xuanfeiyang.erp.param.PurchaseRequestOrderParam;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
import com.xuanfeiyang.erp.service.GoodsService;
import com.xuanfeiyang.erp.service.GoodsSupplierPriceService;
import com.xuanfeiyang.erp.service.GoodsSupplierService;
import com.xuanfeiyang.erp.service.PurchaseOrderService;
import com.xuanfeiyang.erp.service.PurchaseRequestOrderService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.service.XxNoGenerateService;
import com.xuanfeiyang.erp.service.XxNoType;
import com.xuanfeiyang.erp.util.StringUtil;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/purchaserequest-order")
@SessionAttributes(App.SESSION_USER_KEY)
public class PurchaseRequestOrderController extends BaseController{
	@Resource
	private PurchaseRequestOrderService purchaseRequestOrderService;
	@Resource
	private PurchaseOrderService purchaseOrderService; 
	@Resource
	private GoodsCategoryService goodsCategoryService; 
	@Resource
	private GoodsService goodsService;
	@Resource
	private GoodsSupplierService  goodsSupplierService;
	@Resource
	private XxNoGenerateService xxNoGenerateService;
	@Resource
	private UserService userService;
	@Resource
	private MessageSource messageSource;
	@Resource
	private GoodsSupplierPriceService goodsSupplierPriceService;
	private static final Logger logger = LoggerFactory
			.getLogger(PurchaseRequestOrderController.class);
	public void setPurchaseRequestOrderService(
			PurchaseRequestOrderService purchaseRequestOrderService) {
		this.purchaseRequestOrderService = purchaseRequestOrderService;
	}
	public void setGoodsCategoryService(GoodsCategoryService goodsCategoryService) {
		this.goodsCategoryService = goodsCategoryService;
	}
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	public void setGoodsSupplierService(GoodsSupplierService goodsSupplierService) {
		this.goodsSupplierService = goodsSupplierService;
	}
	
	public void setPurchaseOrderService(PurchaseOrderService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	public void setGoodsSupplierPriceService(
			GoodsSupplierPriceService goodsSupplierPriceService) {
		this.goodsSupplierPriceService = goodsSupplierPriceService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping("list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView();
		List<GoodsCategory> firstCategory = this.goodsCategoryService.findByParentId(0);
		PurchaseRequestOrderParam param = new PurchaseRequestOrderParam();
		mv.addObject("categoryList", firstCategory);
		mv.addObject("search",param);
		mv.setViewName("goods/purchaserequest-order-list");
		return mv;
	}
	
	@RequestMapping("pageJson")
	@ResponseBody
	public DataTableResponse<PurchaseRequestOrder> purchaseRequestOrderPageJson(@RequestBody DataTableRequest<PurchaseRequestOrderParam> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<PurchaseRequestOrder> page = this.purchaseRequestOrderService.findPage(pageRequest,dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	@RequestMapping("form")
	public ModelAndView form(@RequestParam(value="id",required=false) Integer id,ModelAndView mv,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		PurchaseRequestOrder pror = null;
		Integer buyPeriod = 1;
		if (null!=id){
			mv.addObject("updateFlag", "1");
			pror = purchaseRequestOrderService.get(id);
			if (pror!=null && pror.getBuyUserId()==null){
				pror.setBuyUserId(curUser.getUserId());
				pror.setBuyuserName(curUser.getUsername());
			}
			List<PurchaseRequestOrderItem> itemList = purchaseRequestOrderService.findItem(id);
			String categoryName = null;
			for(PurchaseRequestOrderItem item:itemList){
				if (item.getGoodsCategory()!=null && null==categoryName){
					GoodsCategory category = goodsCategoryService.get(item.getGoodsCategory()); 
					categoryName = category!=null?category.getName():"";
				}
				item.setGoodsCategoryName(categoryName);
				GoodsSupplierPrice  gsp =  goodsSupplierPriceService.findBySupplySku(pror.getSupplierId(), item.getGoodsSku());
				buyPeriod = Math.max(buyPeriod, gsp==null?0:gsp.getBuyPeriod());
			}
			//设置要求交货日期
			if (pror.getDeliveryDate()==null){
				Calendar calendar = Calendar.getInstance() ;
				calendar.setTime(pror.getCreatedTime());
				calendar.add(Calendar.DATE,buyPeriod);
				pror.setDeliveryDate(calendar.getTime());
			}
			mv.addObject("itemList", itemList);
		}else{
			pror = new PurchaseRequestOrder();
			pror.setCreatedUserName(curUser.getName());
			pror.setCreatedTime(new Date());
			pror.setBuyUserId(curUser.getUserId());
			pror.setBuyuserName(curUser.getUsername());
			pror.setOrderNo(xxNoGenerateService.generate(XxNoType.CS));
		}
		mv.addObject("users", this.userService.findAll());
		mv.addObject("purchasereq", pror);
		mv.setViewName("goods/purchaserequest-order-form");
		return mv;
	}
	
	@RequestMapping("add")
	public ModelAndView add(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,PurchaseRequestOrder order, 
			@RequestParam("itemSku") List<String> itemSku,
			@RequestParam("itemName") List<String> itemName,
			@RequestParam("itemUnit") List<String> itemUnit,
			@RequestParam("itemCost") List<BigDecimal> itemCost,
			@RequestParam("itemCount") List<Integer> itemCount,
			@RequestParam("itemDeliveryDate") List<String> itemDeliveryDate,
			@RequestParam(value="itemCategory",required=false) List<Integer> itemCategory,
			RedirectAttributes attr) throws Exception{
		List<PurchaseRequestOrderItem> items = new ArrayList<PurchaseRequestOrderItem>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (itemSku!=null && itemSku.size()>0){
			for(int i=0;i<itemSku.size();i++){
				PurchaseRequestOrderItem item = new PurchaseRequestOrderItem(itemSku.get(i),itemName.get(i),itemCost.get(i),
						itemCount.get(i),itemUnit.get(i),itemCategory==null?null:itemCategory.get(i),sdf.parse(itemDeliveryDate.get(i))); 
				items.add(item);
			}
		}
		order.setCreatedUserId(curUser.getUserId());
		this.purchaseRequestOrderService.add(order,items);
		logger.debug("add new PurchaseRequestOrder: {}",order.getId());
		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		return new ModelAndView("redirect:/purchaserequest-order/list");
	}
	
	@RequestMapping("edit")
	public ModelAndView edit(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,PurchaseRequestOrder order,
			@RequestParam("itemSku") List<String> itemSku,
			@RequestParam("itemName") List<String> itemName,
			@RequestParam("itemUnit") List<String> itemUnit,
			@RequestParam("itemCost") List<BigDecimal> itemCost,
			@RequestParam("itemCount") List<Integer> itemCount,
			@RequestParam("itemDeliveryDate") List<String> itemDeliveryDate,
			@RequestParam(value="itemCategory",required=false) List<Integer> itemCategory,
			RedirectAttributes attr) throws Exception{
		order.setCreatedUserId(curUser.getUserId());
		List<PurchaseRequestOrderItem> items = new ArrayList<PurchaseRequestOrderItem>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (itemSku!=null && itemSku.size()>0){
			for(int i=0;i<itemSku.size();i++){
				PurchaseRequestOrderItem item = new PurchaseRequestOrderItem(itemSku.get(i),itemName.get(i),itemCost.get(i),itemCount.get(i),itemUnit.get(i),itemCategory.get(i),sdf.parse(itemDeliveryDate.get(i))); 
				items.add(item);
			}
		}
		this.purchaseRequestOrderService.update(order, items);
		logger.debug("update PurchaseRequestOrder: {}",order.getId());
		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		return new ModelAndView("redirect:/purchaserequest-order/list");
	}
	
	@RequestMapping("detail/{id}")
	public ModelAndView detail(@PathVariable("id")Integer id,Model model){
		List<PurchaseRequestOrder> detailList = purchaseRequestOrderService.detail(id);
		model.addAttribute("detailList", detailList);
		return new ModelAndView("goods/purchaserequest-order-detail");
	}
	@RequestMapping("remove/{id}")
	public ModelAndView remove(@PathVariable("id")  Integer id,RedirectAttributes attr){
		this.purchaseRequestOrderService.remove(id);
		logger.debug("delete PurchaseRequestOrder: {}",id);
		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		return new ModelAndView("redirect:/purchaserequest-order/list");
	}
	
	@ResponseBody
	@RequestMapping("goodsInfo")
	public Map<String,Object> goodsInfoBySku(@RequestParam("sku")String sku,@RequestParam(value="suppliyId",required=false)Integer suppliyId){
		Map<String,Object> retMap = new HashMap<String,Object>();
		Map<String,String> propMap = new HashMap<String,String>();
		if (sku==null || sku.length()==0){
			retMap.put("result", false);
			return retMap;
		}
		Goods goods = goodsService.findBySku(sku);
		if (null==goods){
			retMap.put("result", false);
			return retMap;
		}
		propMap.put("color", goods.getColor());
		propMap.put("size", goods.getGoodsSize());
		propMap.put("rules", goods.getRules());
		propMap.put("model", goods.getModel());
		propMap.put("name",goods.getName());
		propMap.put("unit",goods.getUnit());
		propMap.put("weight",goods.getWeight() == null ? "0" : goods.getWeight().toString());
		propMap.put("cost",goods.getCost().toString());
		if (null!=suppliyId){
			GoodsSupplierPrice  gspe =  goodsSupplierPriceService.findBySupplySku(suppliyId, sku);
			if (gspe!=null && gspe.getPrice()!=null){
				propMap.put("price", gspe.getPrice().toString());
			}
		}
		BigDecimal newCost = purchaseOrderService.getLatestGoodsCostBySku(goods.getGoodsSku());
		propMap.put("newcost", null==newCost?"":String.valueOf(newCost));
		if (goods.getCategoryId()!=null){
			GoodsCategory category = goodsCategoryService.get(goods.getCategoryId());
			if (null!=category){
				propMap.put("categoryId",String.valueOf(category.getId()));
				propMap.put("categoryName",category.getName());
			}
		}
		retMap.put("result", true);
		retMap.put("datas", propMap);
		return retMap;
	}
	
	/**
	 * ajax 请购转采购
	 * @return
	 */
	@ResponseBody
	@RequestMapping("convert")
	public Map<String,Object> purchaseReqToOrder(@RequestParam("ids")String ids,RedirectAttributes attr,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<String,Object>();
		//key:合并后的请购单,value:通过供商商+sku合并后的子项
		Map<PurchaseRequestOrder,List<PurchaseRequestOrderItem>> mergeMap = new HashMap<PurchaseRequestOrder,List<PurchaseRequestOrderItem>>();
		List<PurchaseRequestOrderItem> allItems = new ArrayList<PurchaseRequestOrderItem>();
		List<PurchaseRequestOrder> allReqOrder = new ArrayList<PurchaseRequestOrder>();
		retMap.put("result", true);
		try{
			if (StringUtil.isNotBlank(ids)){
				String idArray [] = ids.split("\\|");
				for(String id:idArray){
					PurchaseRequestOrder p = this.purchaseRequestOrderService.get(Integer.valueOf(id));
					allReqOrder.add(p);
					List<PurchaseRequestOrderItem> items = this.purchaseRequestOrderService.findItem(p.getId());
					for(PurchaseRequestOrderItem item:items){
						item.setSupplierId(p.getSupplierId());
					}
					allItems.addAll(items);
				}
				//通过供应商id合并请购单
				//key:供应商id value:请购单对象
				Map<Integer,PurchaseRequestOrder> mapReqOrder = new HashMap<Integer,PurchaseRequestOrder>();
				Map<Integer,List<PurchaseRequestOrder>> mapReqOrderList = new HashMap<Integer,List<PurchaseRequestOrder>>();
				for(PurchaseRequestOrder reqOrder:allReqOrder){
					if (!mapReqOrder.containsKey(reqOrder.getSupplierId())){
						mapReqOrder.put(reqOrder.getSupplierId(), reqOrder);
					}
					if (!mapReqOrderList.containsKey(reqOrder.getSupplierId())){
						List<PurchaseRequestOrder> list = new ArrayList<PurchaseRequestOrder>();
						list.add(reqOrder);
						mapReqOrderList.put(reqOrder.getSupplierId(), list);
					}else{
						mapReqOrderList.get(reqOrder.getSupplierId()).add(reqOrder);	
					}
				}
				
				//通过供应商id和sku合并子项
				//key:供应商id value:合并后的子项
				Map<Integer,List<PurchaseRequestOrderItem>> mapItem = new HashMap<Integer,List<PurchaseRequestOrderItem>>();
				Iterator<PurchaseRequestOrderItem> pit = allItems.iterator();
				for(;pit.hasNext();){
					PurchaseRequestOrderItem item = pit.next();
					if (!mapItem.containsKey(item.getSupplierId())){
						List<PurchaseRequestOrderItem> tempList =  new ArrayList<PurchaseRequestOrderItem>();
						tempList.add(item);
						mapItem.put(item.getSupplierId(),tempList);
						pit.remove();
						continue;
					}
					List<PurchaseRequestOrderItem> tempList = mapItem.get(item.getSupplierId());
					List<PurchaseRequestOrderItem> tempList2 = tempList;
					boolean flag = false;
					for(PurchaseRequestOrderItem temppror:tempList){
						//如果sku相等，合并数量
						if (item.getGoodsSku().equals(temppror.getGoodsSku())){
							double lowprice = Math.min(item.getGoodsCost().doubleValue(),temppror.getGoodsCost().doubleValue());
							temppror.setGoodsCount(item.getGoodsCount()+temppror.getGoodsCount());
							temppror.setGoodsCost(BigDecimal.valueOf(lowprice));
							flag = true;
							break;
						}
					}
					//如果子项不存在加入集合
					if (!flag){
						tempList2.add(item);
					}
					pit.remove();
					mapItem.put(item.getSupplierId(),tempList2);
				}
				
				//组装合并好的数据并进行处理
				Set<Integer> suplierSet = mapReqOrder.keySet();
				for(Iterator<Integer> it =suplierSet.iterator();it.hasNext();){
					Integer suplierId = it.next(); 
					mergeMap.put(mapReqOrder.get(suplierId),mapItem.get(suplierId));
				}
				Set<Map.Entry<PurchaseRequestOrder, List<PurchaseRequestOrderItem>>> entrySet = mergeMap.entrySet();
				StringBuffer sbf = new StringBuffer();
				for(Map.Entry<PurchaseRequestOrder, List<PurchaseRequestOrderItem>> entry:entrySet){
					String orderNo = xxNoGenerateService.generate(XxNoType.PO);
					sbf.append(orderNo+",");
					PurchaseRequestOrder pror = entry.getKey();
					List<PurchaseRequestOrderItem> items = entry.getValue();
					//如果采购人为空,则设置当前登陆用户为采购人
					if (pror.getBuyUserId()==null){
						pror.setBuyUserId(curUser.getUserId());
					}
					//如果要求交货时间为空,则设置要求交货时间
					if (pror.getDeliveryDate()==null){
						Integer buyPeriod = 1;
						for(PurchaseRequestOrderItem item:items){
							GoodsSupplierPrice  gsp =  goodsSupplierPriceService.findBySupplySku(pror.getSupplierId(), item.getGoodsSku());
							buyPeriod = Math.max(buyPeriod, gsp==null?0:gsp.getBuyPeriod());
						}
						Calendar calendar = Calendar.getInstance() ;
						calendar.setTime(pror.getCreatedTime());
						calendar.add(Calendar.DATE,buyPeriod);
						pror.setDeliveryDate(calendar.getTime());
					}
					this.purchaseRequestOrderService.generateOrder(entry.getKey(),entry.getValue(),mapReqOrderList.get(pror.getSupplierId()),orderNo,curUser.getName());
				}
				retMap.put("ids", "生成采购单号:"+sbf.deleteCharAt(sbf.length()-1));
			}
		}catch(Exception e){
			logger.error("purchaseRequestOrder convert to purchaseOrder error:",e);
			retMap.put("result",false);
			retMap.put("errorMsg",this.messageSource.getMessage("purchaserequest.convert.error", null, null));
		}
		return retMap;
	}
	public void setXxNoGenerateService(XxNoGenerateService xxNoGenerateService) {
		this.xxNoGenerateService = xxNoGenerateService;
	}
}

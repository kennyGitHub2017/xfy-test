package com.xuanfeiyang.erp.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.annotations.Token;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.GoodsCostLogDao;
import com.xuanfeiyang.erp.domain.Dict;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsCategory;
import com.xuanfeiyang.erp.domain.GoodsCostLog;
import com.xuanfeiyang.erp.domain.GoodsPackingMaterial;
import com.xuanfeiyang.erp.domain.IoOrder;
import com.xuanfeiyang.erp.domain.IoOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrder;
import com.xuanfeiyang.erp.domain.PurchaseOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrderStatistic;
import com.xuanfeiyang.erp.domain.Store;
import com.xuanfeiyang.erp.domain.StoreShelf;
import com.xuanfeiyang.erp.param.IoOrderParam;
import com.xuanfeiyang.erp.service.DictService;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
import com.xuanfeiyang.erp.service.GoodsPackingMaterialService;
import com.xuanfeiyang.erp.service.GoodsService;
import com.xuanfeiyang.erp.service.GoodsSupplierService;
import com.xuanfeiyang.erp.service.IoOrderService;
import com.xuanfeiyang.erp.service.PurchaseOrderService;
import com.xuanfeiyang.erp.service.StockInService;
import com.xuanfeiyang.erp.service.StoreService;
import com.xuanfeiyang.erp.service.XxNoGenerateService;
import com.xuanfeiyang.erp.service.XxNoType;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

/**
 * 采购入库
 * @author bernard
 *
 */
@Controller
@RequestMapping("stockin")
@SessionAttributes(App.SESSION_USER_KEY)
public class StockInController  extends  BaseController{
	@Resource
	private IoOrderService ioOrderService;
	@Resource
	private GoodsCategoryService goodsCategoryService;
	@Resource
	private GoodsSupplierService goodsSupplierService;
	@Resource
	private XxNoGenerateService xxNoGenerateService;
	@Resource
	private MessageSource messageSource;
	@Resource
	private PurchaseOrderService purchaseOrderService; 
	@Resource
	private  StoreService storeService;
	@Resource
	private GoodsService goodsService; 
	@Resource
	private DictService dictService; 
	@Resource
	private StockInService stockInService; 
	@Resource
	private GoodsPackingMaterialService goodsPackingMaterialService; 
	@Resource
	private GoodsCostLogDao goodsCostLogDao;
	
	private final Integer UNQUALIFIED_STORE_ID = App.getConfigInt("unqualified.store.id");		//不合格仓库
	
	private static final Logger logger = LoggerFactory.getLogger(StockInController.class);

	@RequestMapping("list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView();
		List<GoodsCategory> firstCategory = this.goodsCategoryService.findByParentId(0);
		IoOrderParam param = new IoOrderParam();
		mv.addObject("categoryList", firstCategory);
		mv.addObject("search",param);
		List<Store> storeList = storeService.findStroe();
		mv.addObject("storeList", storeList);
		mv.addObject("goodsStatus", this.dictService.findByType(102));
		mv.setViewName("goods/stockin-list");
		return mv;
	}
	
	@RequestMapping("detail/{orderNo}")
	public ModelAndView detail(@PathVariable("orderNo")String orderNo){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods/stockin-detail");
		if (orderNo!=null && !"".equals(orderNo)){
			mv.addObject("detailList", ioOrderService.detail(orderNo));
		}
		return mv;
	}
	
	
	@RequestMapping("pageJson")
	@ResponseBody
	public DataTableResponse<IoOrder> purchaseOrderPageJson(@RequestBody DataTableRequest<IoOrderParam> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		dtr.getParams().setOrderStr("a.last_updated_time");
		Page<IoOrder> page = this.ioOrderService.findPage(pageRequest,dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	
	@RequestMapping("purchaseTotal.json")
	@ResponseBody
	public PurchaseOrderStatistic purchaseOrderTotal(IoOrderParam param) {
		return ioOrderService.purchaseOrderStatistic(param);
	}
	
	@RequestMapping("form")
	@Token(save=true)
	public ModelAndView form(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,
			@RequestParam(value="purchaseOrderNo",required=false) String purchaseOrderNo){
		IoOrder order = new IoOrder();
		order.setCreatedUserName(curUser.getName());
		order.setCreatedTime(new Date());
		order.setBuyOrderNo(purchaseOrderNo);
		List<PurchaseOrderItem> notstockin = new ArrayList<PurchaseOrderItem>();
		PurchaseOrder purchaseOrder = null;
		if (null!=purchaseOrderNo && purchaseOrderNo.length()>0){
			purchaseOrder =  purchaseOrderService.findByOrderNo(purchaseOrderNo);
			List<PurchaseOrderItem> pitems = purchaseOrderService.findItem(purchaseOrder.getId());
			//判断是否已入库过
			List<IoOrder> stockinorder = this.ioOrderService.getOrderByPurchaseOrderNo(purchaseOrderNo);
			if (null!=stockinorder){
				//如果某个sku已经完成入库(采购数量=已交数量),则移除此sku
				for(PurchaseOrderItem pitem:pitems){
					if (pitem.getReceivedCount()!=pitem.getGoodsCount()){
						pitem.setRemainCount(pitem.getGoodsCount()-pitem.getReceivedCount());
						notstockin.add(pitem);
					}
				}
			}else{
				notstockin = pitems;
				for(PurchaseOrderItem item:notstockin){
					item.setRemainCount(item.getGoodsCount());
				}
			}
		}
		//设置子项sku的仓库及货位
		for(PurchaseOrderItem poim:notstockin){
			poim.setSupplierName(purchaseOrder.getSupplierName());
			poim.setBuyUserName(purchaseOrder.getBuyUserName());
			Goods goods =  goodsService.findBySku(poim.getGoodsSku());
			if (null!=goods){
				poim.setStoreId(goods.getStoreId());
				poim.setStoreShelfId(goods.getStoreShelfId());
				poim.setOldSku(goods.getOldSku());
			}
		}
		List<Store> storeList = storeService.findStroe();
		List<StoreShelf> unQualifiedStoreList = storeService.findShelfByStore(UNQUALIFIED_STORE_ID);  //获取所有不合格仓库
		List<Dict> dicts = dictService.findByType(220);					//获取所有质量不合格
		ModelAndView mv = new ModelAndView();
		order.setOrderNo(xxNoGenerateService.generate(XxNoType.QR));
		mv.addObject("ioorder", order);
		mv.addObject("purchaseOrder", purchaseOrder);
		mv.addObject("nostockList",notstockin);
		mv.addObject("storeList", storeList);
		mv.addObject("dictList", dicts);
		mv.addObject("unQualifiedStoreList", unQualifiedStoreList);
		mv.setViewName("goods/stockin-form");
		return mv;
	}
	
	@RequestMapping("add")
	@Token(remove=true)
	public ModelAndView add(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,IoOrder order, 
			@RequestParam("itemId") List<Integer> itemId,
			@RequestParam("itemInspect") List<Integer> itemInspect,
			@RequestParam("itemSku") List<String> itemSku,
			@RequestParam("itemName") List<String> itemName,
			@RequestParam("buyCount") List<Integer> buyCount,
			@RequestParam("itemCost") List<Float> itemCost,
			@RequestParam("remainCount") List<Integer> remainCount,
			@RequestParam("testCount") List<Integer> testCount,
			@RequestParam("qualifiedCount") List<Integer> qualifiedCount,
			@RequestParam("unQualifiedCount") List<Integer> unQualifiedCount,
			@RequestParam("reason") List<String> reason,
			@RequestParam("unQualifiedStoreShelf") List<String> unQualifiedStoreShelf,
			@RequestParam("storeId") List<String> storeId,
			@RequestParam("storeShelf") List<String> storeShelf,
			@RequestParam("skuWeight")List<BigDecimal> skuWeight,
			RedirectAttributes attr) throws Exception{
		List<IoOrderItem> items = new ArrayList<IoOrderItem>();
		//   temp = 采购单运费/采购单下的sku数量
		PurchaseOrder po = purchaseOrderService.findByOrderNo(order.getBuyOrderNo());
		//采购单已经完结
		if (po.getStatus().intValue()==3 || po.getStatus().intValue()==4){
			return new ModelAndView("redirect:/stockin/detail/"+order.getOrderNo());
		}
		List<PurchaseOrderItem> poItems = purchaseOrderService.findItem(po.getId());
		BigDecimal temp = po.getFreight()==null?BigDecimal.valueOf(0):new BigDecimal(Float.toString(po.getFreight()));
		Integer itemCount = 0;			//采购单下所有sku数量汇总
		BigDecimal cost =null,customCost=null;
		for(PurchaseOrderItem item:poItems){
			itemCount += item.getGoodsCount();
		}
		temp = temp.divide(BigDecimal.valueOf(itemCount),1,BigDecimal.ROUND_HALF_UP);
		
		if (itemSku!=null && itemSku.size()>0){
			for(int i=0;i<itemSku.size();i++){
				IoOrderItem item = new IoOrderItem();
				item.setGoodsSku(itemSku.get(i));
				item.setItemId(itemId.get(i));
				item.setGoodsName(itemName.get(i));
				item.setBuyCount(buyCount.get(i));
				item.setGoodsCost(BigDecimal.valueOf(itemCost.get(i)).add(temp));
				item.setSkuTotalWeight(skuWeight.get(i));
				Goods goods =  goodsService.findBySku(item.getGoodsSku());
				//如果SKU有包装材料，则设置sku包装费
				if (null!=goods.getPackingMaterialId()){
					GoodsPackingMaterial gpl = goodsPackingMaterialService.findById(goods.getPackingMaterialId());
					item.setPackingMaterialFee(gpl!=null?gpl.getPrice():null);			
				}
				item.setRemainCount(remainCount.get(i));
				item.setTestCount(testCount.get(i));
				item.setQualifiedCount(qualifiedCount.get(i));
				item.setUnqualifiedCount(unQualifiedCount.get(i));
				item.setTestType(itemInspect.get(i));
				
				//更新产品表的cost采购成本字段 [仅当产品的价格维护类型为自动时有效]
				if (null!=goods && null!=goods.getCostUpdateType() && 0==goods.getCostUpdateType()){
					cost = setSkuCost(goods.getId(),item);
				}
				
				//更新产品表的卖家成本采购成本字段 [仅当产品的卖家价格更新类型为自动时有效]
				if (null!=goods && null!=goods.getCustomUpdateType() && 0==goods.getCustomUpdateType()){
					customCost = setSkuCustomCost(goods.getId(),item);
				}
				
				//记录产品成本或卖家成本修改记录
				if (null!=goods && null!=goods.getCostUpdateType() && 0==goods.getCostUpdateType()
						|| null!=goods && null!=goods.getCustomUpdateType() && 0==goods.getCustomUpdateType()){
					addCostLog(cost,goods.getCostUpdateType(),curUser.getUsername(),goods.getGoodsSku(),customCost,goods.getCustomUpdateType());
				}
				
				//设置产品重量
				setSkuWeight(goods.getId(),item);
				
				if(reason.size()>0 && null!=reason.get(i) && !"".equals(reason.get(i))){
					item.setReason(reason.get(i));
				}
				if(unQualifiedStoreShelf.size()>0 && unQualifiedStoreShelf.get(i)!=null && !"".equals(unQualifiedStoreShelf.get(i))){
					item.setUnqualifiedStoreId(UNQUALIFIED_STORE_ID);
					item.setUnqualifiedShelfId(Integer.valueOf(unQualifiedStoreShelf.get(i)));
				}
				if(storeId.get(i)!=null && !"".equals(storeId.get(i))){
					item.setStoreId(Integer.valueOf(storeId.get(i)));
				}
				if(storeShelf.get(i)!=null && !"".equals(storeShelf.get(i))){
					item.setStoreShelfId(Integer.valueOf(storeShelf.get(i)));
				}
				items.add(item);
			}
		}
		order.setCreatedUserId(curUser.getUserId());
		stockInService.addStock(order, items,curUser.getName());
		/*
		 * 同一采购单多次入库，只生成一条入库单
		IoOrder stockinorder = this.ioOrderService.getOrderByPurchaseOrderNo(order.getBuyOrderNo());
		order.setCreatedUserId(curUser.getUserId());
		if (null==stockinorder){
			stockInService.addStock(order, items);
		}else{
			stockInService.editStock(order, items);
		}
		*/
		logger.debug("PurchaseOrder stockin success: {}",order.getOrderNo());
		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		return new ModelAndView("redirect:/stockin/detail/"+order.getOrderNo());
	}

	
	/**
	 * 设置产品表sku重量
	 */
	private void setSkuWeight(Integer gId,IoOrderItem item){
		Goods tempGoods = new Goods();
		tempGoods.setId(gId);
		BigDecimal oneskuWeight = item.getSkuTotalWeight().divide(BigDecimal.valueOf(item.getTestCount()),4,2);		//单个sku重量  =sku总重量/sku数量
		BigDecimal  latestGoodsWeight = this.stockInService.getLatestGoodsWeightBySku(item.getGoodsSku());		//最近一次采购入库的本产品单个重量
		if (latestGoodsWeight!=null){
			oneskuWeight = oneskuWeight.add(latestGoodsWeight).divide(BigDecimal.valueOf(2),4,2);					//本次sku重量  和  最近一次采购入库的相同sku重量相加除以2算出新的平均值
		}
		tempGoods.setWeight(oneskuWeight);
		item.setGoodsWeight(oneskuWeight);
		tempGoods.setLastUpdatedTime(new Date());
		goodsService.update(tempGoods);
	}
	
	/**
	 * 设置sku成本
	 * @param gId
	 * @param item
	 */
	private BigDecimal setSkuCost(Integer gId,IoOrderItem item){
		Goods tempGoods = new Goods();
		tempGoods.setId(gId);
		tempGoods.setCost(item.getGoodsCost());
		tempGoods.setLastUpdatedTime(new Date());
		goodsService.update(tempGoods);
		return tempGoods.getCost();
	}
	
	/**
	 * 设置卖家sku成本价
	 * @param gId
	 * @param item
	 */
	private BigDecimal setSkuCustomCost(Integer gId,IoOrderItem item){
		Goods tempGoods = new Goods();
		tempGoods.setId(gId);
		BigDecimal customCost = item.getGoodsCost().multiply(BigDecimal.valueOf(1.1));	//采购价*1.1	
		if (item.getPackingMaterialFee()!=null){										//加上产品包装费
			customCost = customCost.add(item.getPackingMaterialFee());
		}
		tempGoods.setCustomCost(customCost.setScale(2,BigDecimal.ROUND_HALF_UP));
		tempGoods.setLastUpdatedTime(new Date());
		goodsService.update(tempGoods);
		return tempGoods.getCustomCost();
	}
	
	
	private void addCostLog(BigDecimal costNew, Integer updateType,
			String operUser,String goodsSku,BigDecimal customCost,Integer customUpType){
		
		GoodsCostLog gcl = new GoodsCostLog();
		gcl.setCost(costNew);
		gcl.setCreatedTime(new Date());
		gcl.setNote("自动");
		if (updateType!=null){
			gcl.setNote(gcl.getNote()+"，sku改为" +costNew +"，sku维护"+updateType );
		}
		if (customUpType!=null){
			gcl.setNote(gcl.getNote()+"，卖家价格改" + customCost + "，卖家价格维护"+customUpType);
		}
		gcl.setOperUser(operUser);
		gcl.setGoodsSku(goodsSku);
		gcl.setCostUpType(updateType);
		
		gcl.setCustomCost(null==customCost?"":customCost.toString());
		gcl.setCustomUpType(customUpType);
		
		this.goodsCostLogDao.add(gcl);
	}
}

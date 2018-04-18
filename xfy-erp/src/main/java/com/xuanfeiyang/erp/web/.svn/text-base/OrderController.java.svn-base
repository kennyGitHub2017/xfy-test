package com.xuanfeiyang.erp.web;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.buzheng.excel.Column;
import org.buzheng.excel.ExcelBuilder;
import org.buzheng.excel.FieldFormatter;
import org.buzheng.excel.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryCostDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryLockDao;
import com.xuanfeiyang.erp.dao.OrderDao;
import com.xuanfeiyang.erp.dao.OrderPackageDao;
import com.xuanfeiyang.erp.dao.SellerDao;
import com.xuanfeiyang.erp.dao.SellerFeeWithHoldDao;
import com.xuanfeiyang.erp.domain.ExportOrder;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderLog;
import com.xuanfeiyang.erp.domain.OrderMarkShippingResult;
import com.xuanfeiyang.erp.domain.OrderNote;
import com.xuanfeiyang.erp.domain.OrderRefund;
import com.xuanfeiyang.erp.domain.OrderShippingFee;
import com.xuanfeiyang.erp.domain.OrderStatistic;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.StockOutSku;
import com.xuanfeiyang.erp.domain.TrackNumber;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.enums.OrderStatusEnum;
import com.xuanfeiyang.erp.param.OrderParam;
import com.xuanfeiyang.erp.param.PlatformAccountParams;
import com.xuanfeiyang.erp.service.CurrencyRatesService;
import com.xuanfeiyang.erp.service.DictService;
import com.xuanfeiyang.erp.service.FileImportException;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
import com.xuanfeiyang.erp.service.MarkShippingService;
import com.xuanfeiyang.erp.service.OrderItemService;
import com.xuanfeiyang.erp.service.OrderLogService;
import com.xuanfeiyang.erp.service.OrderNoteService;
import com.xuanfeiyang.erp.service.OrderPackageService;
import com.xuanfeiyang.erp.service.OrderService;
import com.xuanfeiyang.erp.service.PlatformAccountService;
import com.xuanfeiyang.erp.service.PurchaseOrderService;
import com.xuanfeiyang.erp.service.ShippingService;
import com.xuanfeiyang.erp.service.TrackerApplyResult;
import com.xuanfeiyang.erp.service.TrackerNumberService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.util.ExcelParser;
import com.xuanfeiyang.erp.util.ExportUtil;
import com.xuanfeiyang.erp.util.ReflectUtil;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;
@Controller
@RequestMapping("/order")
@SessionAttributes(App.SESSION_USER_KEY)
public class OrderController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(OrderController.class);
	@Resource
	private OrderService orderService;
	@Resource
	private MessageSource messageSource;
	@Resource
	private GoodsCategoryService goodsCategoryService;
	@Resource
	private  CurrencyRatesService currencyRatesService;
	@Resource
	private DictService dictService; 
	@Resource
	private ShippingService shippingService;
	@Resource
	private OrderLogService orderLogService; 
	@Resource
	private OrderNoteService orderNoteService;
	@Resource
	private GoodsInventoryDao goodsInventoryDao;
	@Resource
	private GoodsInventoryCostDao goodsInventoryCostDao;
	@Resource
	private GoodsInventoryLockDao goodsInventoryLockDao; 
	@Resource
	private OrderDao orderDao;
	@Resource
	private GoodsDao goodsDao;
	@Resource
	private TrackerNumberService trackerNumberService;
	@Resource
	private OrderPackageService orderPackageService;
	@Resource
	private OrderPackageDao orderPackageDao; 
	@Resource 
	private MarkShippingService markShippingService;
	
	@Resource
	private PlatformAccountService platformAccountService;
	
	@Resource
	private OrderItemService orderItemService;
	@Resource
	private PurchaseOrderService purchaseOrderService;
	@Resource
	private UserService UserService;
	@Resource
	private SellerDao sellerDao;
	@Resource
	private SellerFeeWithHoldDao sellerFeeWithHoldDao;
	
	private static final Map<String,String> map = new HashMap<String,String>();
	
	private final OrderParam para = new OrderParam();
	
	static{
		map.put("s1", "无对应sku");
		map.put("s4", "无跟踪号");
		map.put("s9", "已申请跟踪号");
		map.put("s5", "无发货方式");
		map.put("s2", "缺货订单");
		map.put("s3", "物流退回");
		map.put("s6", "已退款");
		map.put("s7", "停止交易");
		map.put("s8", "已暂停");
		map.put("s10", "线下订单");
	}
	
	
	/*
	private static final Map<Integer,String> columnIdxName = new HashMap<Integer,String>();
	
	static {
		columnIdxName.put(9, "a.track_number");				//订单跟踪号
		columnIdxName.put(11, "a.shipping_fee");					//运费
		columnIdxName.put(12, "a.amount");					//订单金额
		columnIdxName.put(13, "a.amount");					//订单金额
		columnIdxName.put(14, "a.refund_fee");					//退款金额
		columnIdxName.put(15, "a.calc_weight");				//订单重量
		columnIdxName.put(16, "a.package_weight");					//称重重量
		columnIdxName.put(17, "a.shipping_fee");					//运费
		columnIdxName.put(18, "a.profit");					//订单利润
		columnIdxName.put(19, "a.package_weight");					//称重重量
		columnIdxName.put(20, "a.package_shipfee");					//称重运费
	}
	*/
	
	/**
	 * 申请订单的快递跟踪号
	 * 
	 * @param model
	 * @param orderIds 订单ID列表
	 * @param type	平台类型  
	 * @param action 操作类型： apply: 申请(默认操作，可空)， cancel: 取消, confirm: 交运
	 * @return
	 */
	@RequestMapping(value = "tracker-number", method = {RequestMethod.GET,RequestMethod.POST})
	public String applyExpressTrackerNumber(
			Model model,
			@RequestParam("id") List<Integer> orderIds,
			@RequestParam("type") String type,
			@RequestParam("action") String action,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser) {
		
		try {
			List<TrackerApplyResult> results = null;
			String operator ="";
			// 取消删除跟踪号, 目前只有 ebay apac 有此操作
			if ("cancel".equals(action)) {
				results = this.trackerNumberService.cancelApac(orderIds);
				operator = "删除跟踪号";
			} 
			// 交运
			else if ("confirm".equals(action)) {
				results = this.trackerNumberService.confirmApac(orderIds);
				operator = "订单标发";
			}
			// 重新发货
			else if ("recreate".equals(action)) {
				results = this.trackerNumberService.recreateApac(orderIds);
				operator = "订单标发";
			}
			// 根据类型申请跟踪号
			else {
				results = this.trackerNumberService.applyMultiOrders(type, orderIds);
				operator = "申请跟踪号";
			}
			model.addAttribute("results", results);
			//保存订单操作日志
			for(TrackerApplyResult result:results){
				OrderLog log = new OrderLog();
				log.setOrderId(result.getOrderId());
				log.setOperTime(new Date());
				log.setOperUserId(curUser.getUsername());
				log.setLog(operator+":<br>"+result);
				orderLogService.insert(log);
			}
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}

		return "orders/tracker-number";
	}
	
	
	@RequestMapping(value="sellers")
	@ResponseBody
	public DataTableResponse<PlatformAccount> pageJson(@RequestParam(value="sellerId", required=false) Integer sellerId,@RequestBody DataTableRequest<?> dtr) {
		String keywords = dtr.getSearch() == null ? null : dtr.getSearch().getValue();
		PlatformAccountParams params = new PlatformAccountParams();
		params.setAccountName(keywords);
		if (params!=null){
			params.setSellerId(sellerId);
		}
		List<PlatformAccount> list = this.platformAccountService.findNameIdPairs(params);
		Page<PlatformAccount> page = new Page<>(list);
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	@RequestMapping(value="list")
	public ModelAndView orderList(ModelAndView model){
		model.addObject("rateList",currencyRatesService.find());
		model.addObject("categoryList", goodsCategoryService.findByParentId(0));
		model.addObject("orderstatusList",dictService.findByType(101));
		model.addObject("orderTypeList",dictService.findByType(100));
		model.addObject("refundReasonList",dictService.findByTable("t_refund_reson"));
		model.addObject("refundTypeList",dictService.findByTable("t_refund_type"));
		model.addObject("shippingList", shippingService.find());
		model.addObject("goodsStatus", this.dictService.findByType(102));
	//	List<Seller> sellers = this.sellerDao.findAll(2);
		model.addObject("loginUserList",this.sellerDao.findAssginColumn(2, Arrays.asList("id","contacts")));
		model.addObject("search",para);
		/*
		map.put("s1", "无对应sku("+orderDao.statExceptionOrderCount("s1",null,null)+")");
		map.put("s4", "无跟踪号("+orderDao.statExceptionOrderCount("s4",null,null)+")");
		map.put("s9", "已申请跟踪号("+orderDao.statExceptionOrderCount("s9",null,null)+")");
		map.put("s5", "无发货方式("+orderDao.statExceptionOrderCount("s5",null,null)+")");
		map.put("s2", "缺货订单("+orderDao.statExceptionOrderCount("s2",null,null)+")");
		map.put("s3", "物流退回("+orderDao.statExceptionOrderCount("s3",null,null)+")");
		map.put("s6", "已退款("+orderDao.statExceptionOrderCount("s6",null,null)+")");
		map.put("s8", "已暂停("+orderDao.statExceptionOrderCount("s8",null,null)+")");
		map.put("s10", "线下订单("+orderDao.statExceptionOrderCount("s10",null,null)+")");
		*/
		
		model.addObject("exceptionOrder",map);
		model.setViewName("orders/order-list");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="exceptionOrderCount")
	public Map<String,Object> exceptionOrderCount(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,
			@RequestParam(value="sellerId",required=false) Integer sellerId,@RequestParam(value="userId",required=false) Integer userId){
		Map<String,Object> retMap = new HashMap<>();
		try{
			retMap.put("result", true);
			Boolean isAgent = curUser.getType()==3;
			retMap.put("s1", "无对应sku("+orderDao.statExceptionOrderCount("s1",sellerId,userId,isAgent)+")");
			retMap.put("s4", "无跟踪号("+orderDao.statExceptionOrderCount("s4",sellerId,userId,isAgent)+")");
			retMap.put("s9", "已申请跟踪号("+orderDao.statExceptionOrderCount("s9",sellerId,userId,isAgent)+")");
			retMap.put("s5", "无发货方式("+orderDao.statExceptionOrderCount("s5",sellerId,userId,isAgent)+")");
			retMap.put("s2", "缺货订单("+orderDao.statExceptionOrderCount("s2",sellerId,userId,isAgent)+")");
			retMap.put("s3", "物流退回("+orderDao.statExceptionOrderCount("s3",sellerId,userId,isAgent)+")");
			retMap.put("s6", "已退款("+orderDao.statExceptionOrderCount("s6",sellerId,userId,isAgent)+")");
			retMap.put("s7", "停止交易("+orderDao.statExceptionOrderCount("s7",sellerId,userId,isAgent)+")");
			retMap.put("s8", "已暂停("+orderDao.statExceptionOrderCount("s8",sellerId,userId,isAgent)+")");
			retMap.put("s10", "线下订单("+orderDao.statExceptionOrderCount("s10",sellerId,userId,isAgent)+")");
		}catch(Exception e){
			logger.error(e.getMessage());
			retMap.put("result", false);
		}
		return retMap;
	}
	
	@RequestMapping(value="seller-order")
	public ModelAndView sellerOrderList(ModelAndView model,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,@RequestParam(required=false,value="id")Integer orderId){
		model.addObject("rateList",currencyRatesService.find());
		model.addObject("categoryList", goodsCategoryService.findByParentId(0));
		model.addObject("orderstatusList",dictService.findByType(101));
		model.addObject("orderTypeList",dictService.findByType(100));
		model.addObject("refundReasonList",dictService.findByType(500));
		model.addObject("shippingList", shippingService.find());
		model.addObject("goodsStatus", this.dictService.findByType(102));
		PlatformAccountParams params = new PlatformAccountParams();
		PlatformAccountParams params2 = new PlatformAccountParams();
		Integer sellerId  = null;
		Integer userId = null;
		
		UserInfo userInfo = this.UserService.loadUserInfo(curUser.getUserId());
		//params2.setSellerId(curUser.getSellerId());
		
		if(userInfo.getSellerId() == App.DEFAULT_SELLER_ID){
			
			userId = curUser.getUserId();
			params.setUserId(userId);
			model.addObject("userIdFlag", userId);
			
		}else{
			if(userInfo.getIsMaster() == 1){//主账户
				sellerId = curUser.getSellerId();
				params.setSellerId(sellerId);
				params2.setSellerId(sellerId);//主账号可以查询所有的账号
			}else{
				userId = curUser.getUserId();
				params.setUserId(userId);
				model.addObject("userIdFlag", userId);
				params2.setUserId(userId);//子账号可以查询自己所分配的账号
			}
		}
		
		model.addObject("accountIdList", this.platformAccountService.findNameIdPairs(params2));
		model.addObject("search", para);
		model.addObject("exceptionOrder",map);
		if (orderId!=null){
			model.addObject("orderId",orderId);
		}
		model.setViewName("orders/seller-order-list");
		return model;
	}
	
	
	
	@RequestMapping(value="agent-order")
	public ModelAndView agentSellerOrderList(ModelAndView model,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		
		if (curUser.getType()!=3){
			model.addObject("errorMessage", this.messageSource.getMessage("agent.noregisted", null, null));
			model.addObject("invalidrequest", true);
			model.setViewName("agent/agent-order-list");
			return model;
		}
		
		model.addObject("rateList",currencyRatesService.find());
		model.addObject("categoryList", goodsCategoryService.findByParentId(0));
		model.addObject("orderstatusList",dictService.findByType(101));
		model.addObject("orderTypeList",dictService.findByType(100));
		model.addObject("refundReasonList",dictService.findByType(500));
		model.addObject("shippingList", shippingService.find());
		model.addObject("goodsStatus", this.dictService.findByType(102));
		
		PlatformAccountParams params2 = new PlatformAccountParams();
		params2.setAgentUserId(curUser.getUserId());
		model.addObject("userId", curUser.getUserId());
			
		model.addObject("accountIdList", this.platformAccountService.findNameIdPairs(params2));
		model.addObject("sellerList",this.sellerDao.findByAgent(curUser.getUserId()));
		model.addObject("search", para);
		model.addObject("exceptionOrder",map);
		
		model.setViewName("agent/agent-order-list");
		return model;
	}
	
	@RequestMapping("pageJson")
	@ResponseBody
	public DataTableResponse<Order> orderPageJson(@RequestBody DataTableRequest<OrderParam> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		dtr.getParams().setOrderStr(dtr.getOrderStr());
		String thisSellerId = dtr.getParams().getSellerId();
		if(thisSellerId != null){
			if(thisSellerId.equals("-1") || thisSellerId.equals("-2")){
				dtr.getParams().setSellerId("");
				if(thisSellerId.equals("-1")){
					dtr.getParams().setSellerType("1");//自营卖家
				}else if(thisSellerId.equals("-2")){
					dtr.getParams().setSellerType("0");//非自营卖家
				}
			}
		}
		Page<Order> page = this.orderService.findPage(pageRequest,dtr.getParams());
		for(Order order:page.getContent()){
			if (null==order.getItems() || order.getItems().size()==0){
				continue;
			}
			for(OrderItem item:order.getItems()){
				if (StringUtils.isEmpty(item.getSku())){
					continue;
				}
				item.setCountInstore(goodsInventoryDao.getAvailableCountBySku(item.getSku()));
				/*
				Integer skuCount = goodsInventoryDao.getCountBySku(item.getSku());
				Integer skuLock = goodsInventoryLockDao.getLockCountBySku(item.getSku());
				skuLock = skuLock ==null?0:skuLock;
				//logger.info("sku:" +item.getSku()+"\tcount:" + skuCount);
				if (skuCount!=null){
					item.setCountInstore(skuCount-skuLock<0?0:skuCount-skuLock);
				}else{
					item.setCountInstore(0);	//不存在此sku库存
				}
				*/
			}
		}
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	
	//需二次标发的ebay订单分页数据
	@RequestMapping("ebayOrderJson")
	@ResponseBody
	public DataTableResponse<Order> ebayOrderJson(@RequestBody DataTableRequest<OrderParam> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		dtr.getParams().setOrderStr(dtr.getOrderStr());
		List<String> idList = dtr.getParams().getIdList(); 
		List<String> newIdList = new ArrayList<>();
		Integer idx = idList.size()%1000==0?idList.size()/1000:idList.size()/1000+1;
		Integer idListSize = idList.size();
		for(int i=0;i<idx;i++){
			int from = i*1000;
			int to = (i+1)*1000;
			newIdList.add(StringUtils.join(idList.subList(from,to>idListSize?idListSize:to), ","));
		}
		dtr.getParams().setIdList(newIdList);
		Page<Order> page = this.orderService.findEbabyTwoSendPage(pageRequest,dtr.getParams());
		for(Order order:page.getContent()){
			if (null==order.getItems() || order.getItems().size()==0){
				continue;
			}
			for(OrderItem item:order.getItems()){
				if (StringUtils.isEmpty(item.getSku())){
					continue;
				}
				item.setCountInstore(goodsInventoryDao.getAvailableCountBySku(item.getSku()));
				/*
				Integer skuCount = goodsInventoryDao.getCountBySku(item.getSku());
				Integer skuLock = goodsInventoryLockDao.getLockCountBySku(item.getSku());
				skuLock = skuLock ==null?0:skuLock;
				//logger.info("sku:" +item.getSku()+"\tcount:" + skuCount);
				if (skuCount!=null){
					item.setCountInstore(skuCount-skuLock<0?0:skuCount-skuLock);
				}else{
					item.setCountInstore(0);	//不存在此sku库存
				}
				*/
			}
		}
		newIdList = null;
		idList = null;
		return WebHelper.assembleDataTableResponse(dtr, page);
	}

	
	@ResponseBody
	@RequestMapping(value="copy")
	public Map<String,Object> copyOrder(@RequestParam("id") Integer id,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<>();
		Integer neworderId = null;
		try{
			neworderId = orderService.copy(id, curUser.getUsername());
			retMap.put("result", true);
			retMap.put("orderId", neworderId);
		}catch(Exception e){
			retMap.put("result", false);
			logger.error("copy order[" +id+"] error", e);
		}
		return retMap;
	}
	
	/**
	 * 读取smt订单买家留言
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="smtMessage.json")
	public Map<String,Object> readSmtMessage(@RequestParam("orderSn") String orderSn){
		Map<String,Object> retMap = new HashMap<>();
		try{
			List<String> msgList = this.orderService.readSmtOrderMessage(orderSn);
			retMap.put("result", true);
			retMap.put("messages", msgList);
		}catch(Exception e){
			retMap.put("result", false);
		}
		return retMap;
	}
	
	@RequestMapping(value={"editpage"})
	public ModelAndView orderInfoPage(@RequestParam(value="id",required=true)Integer id,ModelAndView model,@RequestParam(value="type",required=true)Integer type
			,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Order order = orderService.load(id);
		order = order==null?new Order():order;
		if (null!=order.getBuyinfo()){
			OrderBuyerInfo info = order.getBuyinfo();
			if (StringUtils.isBlank(info.getShippingCountryName())){
				info.setShippingCountryName(info.getShippingCountry());
			}
		}
		PlatformAccountParams params2 = new PlatformAccountParams();
		params2.setSellerId(curUser.getSellerId());
		model.addObject("orderLogList", orderLogService.queryByOrder(id));
		model.addObject("orderNoteList", orderNoteService.queryByOrder(id));
		model.addObject("orderTypeList",dictService.findByType(100));
		model.addObject("orderstatusList",dictService.findByType(101));
		model.addObject("rateList",currencyRatesService.find());
		model.addObject("accountIdList", this.platformAccountService.findNameIdPairs(params2));
		model.addObject("shippingList", shippingService.find());
		model.addObject("order", order);
		//type:1 内嵌该页面  2:新窗口打开该页面
		if (type==1){
			model.setViewName("/orders/orderInfo");
		}else{
			model.setViewName("/orders/orderInfoNew");
		}
		return model;
	}
	
	@RequestMapping("batchedit-page")
	public ModelAndView batchOrderEditPage(ModelAndView model){
		model.addObject("shippingList", shippingService.find());
		model.addObject("orderTypeList",dictService.findByType(100));
		model.setViewName("orders/batchedit-order");
		return model;
	}
	
	@ResponseBody
	@RequestMapping("gqhqy")
	public Map<String,Object> orderSuspendAndEnable(@RequestParam("orderId")List<Integer>orderId,@RequestParam("suspendEnbale")Integer suspendEnbale,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<>();
		try{
			orderService.orderSuspendAndEnable(orderId, suspendEnbale,curUser.getUsername());
			retMap.put("result", true);
		}catch(Exception e){
			retMap.put("result", false);
		}
		return retMap;
	}
	
	
	@ResponseBody
	@RequestMapping("edit")
	public Map<String,Object> orderEdit(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,Order order,@RequestParam("itemId" )String []itemId,
			@RequestParam("systemSku")String []systemSku,@RequestParam("itemCount")Integer []itemCount,
			@RequestParam("itemOrderSn")String []itemOrderSn,@RequestParam("platsku")String []platsku,
			@RequestParam("itemPic")String []itemPic,@RequestParam("itemUrl")String []itemUrl,
			@RequestParam("itemTitle")String []itemTitle,@RequestParam("itemCost")Double []itemCost,
			@RequestParam(value="platFee",required=false)Double []platFee,@RequestParam(value="orderLimitId",required=false)String []orderLimitId
			){
		Map<String,Object> retMap = new HashMap<>();
		List<OrderItem> items = new ArrayList<>();
		Order tempOrder;
		try{
			tempOrder = orderService.load(order.getId());
			if (tempOrder!=null && tempOrder.getSuspend()==0 && tempOrder.getOrderStatus().intValue()>OrderStatusEnum.NOAUDIT.code()){
				retMap.put("result", false);
				retMap.put("error", "修改已审核订单,必须先暂停该订单");
				return retMap;
			}
			if (null!=itemId && itemId.length>0){
				for(int i=0;i<itemId.length;i++){
					//sku,item名称,item价格,item数量 必填项
					if (StringUtils.isBlank(systemSku[i]) || StringUtils.isBlank(itemTitle[i])  || StringUtils.isBlank(itemId[i]) 
							|| null==itemCost[i] || null==itemCount[i]){
						continue;
					}
					items.add(new OrderItem(itemId[i],itemTitle[i],itemPic.length==0?"":itemPic[i],itemUrl.length==0?"":itemUrl[i],systemSku[i],
							platsku.length==0?"":platsku[i],BigDecimal.valueOf(itemCost[i]),(platFee!=null && platFee.length>0 && platFee[i]!=null)?BigDecimal.valueOf(platFee[i]):null,
									itemCount[i],itemOrderSn.length==0?"":itemOrderSn[i],(orderLimitId!=null && orderLimitId.length>0 && orderLimitId[i]!=null)?orderLimitId[i]:null));
				}
			}
			//订单不存在，新增
			if (order.getId()==null){
				order.setCreatedTime(new Date());
				order.setLastUpdatedTime(new Date());
				order.setCombine((short)0);
				order.setOrderType((short)-1);   //设置为线下订单
				order.setOrderStatus((short)1);
				order.setExchangeRate(currencyRatesService.currencyTranslateRate(order.getCurrency(),"RMB"));
				order.setItems(items);
				int orderId = orderService.insert(order);
				orderDao.mixedOrder(orderId);				//更新是否混合订单标志
				retMap.put("result", true);
				return retMap;
			}
			//编辑订单时,复制原来订单行的某些字段已有数据
			for(OrderItem item:items){
				for(OrderItem tempItem:tempOrder.getItems()){
					if (StringUtils.isEmpty(tempItem.getSku())){
						continue;
					}
					//修复更改组合sku时,导致平台费用丢失bug
					if (item.getSku().equals(tempItem.getSku())){
						item.setPackageAmount(tempItem.getPackageAmount());			//包裹数
						item.setLockAmount(tempItem.getLockAmount());				//锁定数
						item.setCancelAmount(tempItem.getCancelAmount());			//取消数
						item.setShipmentAmount(tempItem.getShipmentAmount());		//出货数量
						item.setOrderSrn(tempItem.getOrderSrn());                   
						item.setPurchasePrice(tempItem.getPurchasePrice());			//卖家sku成本
						item.setPlatShipfee(tempItem.getPlatShipfee());             //平台运费
					}
				}
			}
			
			//如果订单未发货,则允许修改sku
			if (tempOrder.getOrderStatus().intValue()<6){
				order.setItems(items);
			}else{
				order.setItems(tempOrder.getItems());
			}
			orderService.update(order);
			//保存订单表格行备注
			if (!StringUtils.isEmpty(order.getOrderNote())){
				OrderNote note = new OrderNote();
				note.setOperUserId(curUser.getUsername());
				note.setContent(order.getOrderNote());
				note.setOrderId(order.getId());
				note.setCreatedTime(new Date());
				orderNoteService.insert(note);
			}
			//修改非待审核订单sku数量或删除sku行,清空包裹数据并还原订单状态
			boolean itemChanged = itemisChange(tempOrder.getItems(),order.getItems());		//订单行下sku是否已修改
			if (order.getOrderStatus().intValue()!=OrderStatusEnum.NOAUDIT.code() && itemChanged){
				orderService.reStoreOrder(order);
				goodsInventoryLockDao.deleteBySaleOrderId(order.getId());		//删除订单所对应的锁定库存
				orderDao.calculateOrderCost(order.getId());		//重装计算订单成本
				orderDao.setOrderPackingMaterialFee(order.getId()); 		// 设置订单包装费
				orderDao.mixedOrder(order.getId());				//更新是否混合订单标志
				sellerFeeWithHoldDao.deletedByOrderId(order.getId());
			}
			//保存修改记录
			orderEditLog(tempOrder,order,curUser.getUsername());
			
			// 如果修改了跟踪号或者运输方式，则将跟踪号同步到包裹上 Added by Adam @20150819
			if ( (! StringUtils.equals(order.getShippingName(), tempOrder.getShippingName()))
					|| (! StringUtils.equals(order.getTrackNumber(), tempOrder.getTrackNumber()))) {
				this.orderPackageDao.updateTrackNumberAndShippingNameByOrderId(tempOrder.getId());
			}
			
			retMap.put("result", true);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			retMap.put("result", false);
			retMap.put("error", e.getMessage());
		}finally{
			items = null;
			tempOrder = null;
		}
		 return retMap;
	}
	
	
	private boolean itemisChange(List<OrderItem> item1List,List<OrderItem> item2List){
		if (item1List.size()!=item2List.size()){
			return true;
		}else{
			for(int i=0;i<item1List.size();i++){
				OrderItem item1 = item1List.get(i);
				OrderItem item2 = item2List.get(i);
				//sku已改变或sku数量已改变
				if (null==item1.getSku() || !item1.getSku().equals(item2.getSku()) || item1.getItemQuantity()!=item2.getItemQuantity()){
					return true;
				}
			}
		}
		return false;
	}
	
	//保存订单修改日志
	private void orderEditLog(Order beforeEdit,Order order,String curUser){
		StringBuffer editMsg = new StringBuffer();
		try {
			Field fields[] = Order.class.getDeclaredFields();
			for (Field field : fields) {
				// 过滤 static、 final、private static final字段
				if (field.getModifiers() == 16 || field.getModifiers() == 8
						|| field.getModifiers() == 26) {
					continue;
				}
				com.xuanfeiyang.annotations.Column annotationColumn = field.getAnnotation(com.xuanfeiyang.annotations.Column.class);
				if (annotationColumn==null){
					continue;
				}
				String fieldName = field.getName();
				//买家信息修改记录
				if (fieldName.equals("buyinfo")){
					//begin
					Field  buyerFields []= OrderBuyerInfo.class.getDeclaredFields();
					String [] addressArray = new String[]{"shippingName","shippingStreet1","shippingStreet2","shippingCity","shippingState","shippingCountry","shippingCountryName","shippingPostcode"};
					List<String> addressList = Arrays.asList(addressArray);
					boolean updatedFlag = false;
					for (Field f : buyerFields) {
						// 过滤 static、 final、private static final字段
						if (f.getModifiers() == 16 || f.getModifiers() == 8
								|| f.getModifiers() == 26) {
							continue;
						}
						annotationColumn = f.getAnnotation(com.xuanfeiyang.annotations.Column.class);
						if (annotationColumn==null){
							continue;
						}
						Object oldV = ReflectUtil.getValueMethod(beforeEdit.getBuyinfo(),f.getName(),f.getType());
						Object newV = ReflectUtil.getValueMethod(order.getBuyinfo(),f.getName(),f.getType());
						if (newV != null && !newV.equals(oldV) && StringUtils.isNotBlank(newV.toString())) {
							editMsg.append(annotationColumn.Desc() + " 从 " + oldV + "修改成"
									+ newV + "<br>");
							//如果买家收货地址修改,标识更新字段
							if (!updatedFlag && addressList.contains(f.getName())){
								orderDao.updateflagOrderBuyinfo(order.getBuyinfo().getId(),1);
								updatedFlag = true;
							}
						}
					}
					//end
					
				}else{
					
					//订单信息修改记录
					Object oldV = ReflectUtil.getValueMethod(beforeEdit,fieldName,field.getType());
					Object newV = ReflectUtil.getValueMethod(order,fieldName,field.getType());
					if (newV != null && !newV.equals(oldV) && StringUtils.isNotBlank(newV.toString())) {
						editMsg.append(annotationColumn.Desc() + " 从 " + oldV + "修改成"
								+ newV + "<br>");
					}
					
				}
			}
			logger.info(editMsg.toString());
			//保存订单修改日志
			if (StringUtils.isNotBlank(editMsg.toString())){
				OrderLog log = new OrderLog();
				log.setLog(editMsg.toString());
				log.setOperTime(new Date());
				log.setOrderId(order.getId());
				log.setOperUserId(curUser);
				orderLogService.insert(log);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value="batchedit",method=RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public Map<String,Object> batchOrderEdit(@RequestBody Map<Object,Object> param,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<String,Object>();
		try{
			String  column[] =null;
			String columnValue[] =null;
			Integer [] id =null;
			
			Object temp = param.get("column");
			String dataType = temp.getClass().getName();
			if (dataType.equals("java.util.ArrayList")){
				
				List<Object> t = (List<Object>)temp;
				column = t.toArray(new String[t.size()]);
			}else if (dataType.equals("java.lang.String")){
				column = new String[]{(String)temp};
			}
			
			temp = param.get("columnValue");
			dataType = temp.getClass().getName();
			if (dataType.equals("java.util.ArrayList")){
				List<Object> t = (List<Object>)temp;
				columnValue = t.toArray(new String[t.size()]);
			}else if (dataType.equals("java.lang.String")){
				columnValue = new String[]{(String)temp};
			}
			
			temp = param.get("id");
			dataType = temp.getClass().getName();
			if (dataType.equals("java.util.ArrayList")){
				List<Object> t = (List<Object>)temp;
				String [] sary = t.toArray(new String[t.size()]);
				id = new Integer[sary.length];
				for(int i=0;i<sary.length;i++){
					id[i] = Integer.valueOf(sary[i]);
				}
			}else if (dataType.equals("java.lang.String")){
				id = new Integer[]{Integer.valueOf(temp.toString())};
			}
			for(Integer orderId:id){
				Order order = orderService.load(orderId);
				//非公司自营卖家订单审核后,不能进行任何修改
				Seller seller = sellerDao.load(curUser.getSellerId());
				if (null!=order.getId() && order.getOrderStatus()!=1 && curUser.getSellerId()!=App.DEFAULT_SELLER_ID  && "0".equals(seller.getSelfFlag())){
					continue;
				}
				orderService.batchEditOrder(column, columnValue, id);
				

				if(column.length == 1 && column[0].equals("shipping_Name")){
					BigDecimal thisOrderFee = this.orderService.countShiFee(order, columnValue[0]);
					Order order1 = new Order();
					order1.setId(order.getId());
					order1.setShippingFee(thisOrderFee);
					order1.setItems(order.getItems());
					this.orderService.update(order1);
				}
				Order afterEdit = orderService.load(orderId);
				//保存订单修改日志
				orderEditLog(order,afterEdit,curUser.getUsername());
			}
			retMap.put("result", true);
		}catch(Exception e){
			logger.error(e.getMessage());
			retMap.put("result", false);
		}
		return retMap;
	}
	
	@ResponseBody
	@RequestMapping(value="audit")
	public Map<String,Object> auditOrder(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,@RequestParam(value="id",required=true)Integer[] ids){
		Map<String,Object> retMap = new HashMap<>();
		try{
			if (null!=ids && ids.length>0){
				boolean error = false;
				StringBuffer errorMsg = new StringBuffer();
				ids = orderService.getOrderIdAfterSortByQuantity(Arrays.asList(ids));
				for(Integer id:ids){
					try{
						orderService.audit(id,curUser.getUsername());
					}catch(RuntimeException e){
						error = true;
						errorMsg.append("\n 订单["+id+"]审核失败:" + e.getMessage());
					}
				}
				if (!error){
					retMap.put("result", true);
				}else{
					retMap.put("result", false);
					retMap.put("error", errorMsg.toString());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			retMap.put("result", false);
			retMap.put("error", "系统异常");
		}
		 return retMap;
	}
	
	@ResponseBody
	@RequestMapping("editSku-json")
	public Map<String,Object> editOrderItemSku(
			@RequestParam(value="id", required=true) Integer id,
			@RequestParam(value="sku", required=true) String sku,
			@RequestParam(value="oid", required=true)Integer orderId){
		Map<String,Object> retMap = new HashMap<>();
		try{
			Order o = orderDao.load(orderId);
			if (o.getOrderStatus().intValue()!=OrderStatusEnum.NOAUDIT.code()){
				throw new Exception("订单已审核,不能修改该sku");
			}
			this.orderItemService.updateSku(id, sku);
			retMap.put("result", true);
		}catch(Exception e){
			retMap.put("result", false);
			retMap.put("error",e.getMessage());
		}
		return retMap;
	}
	
	@ResponseBody
	@RequestMapping("auto-merge")
	public  Map<String,Object> autoMergeOrde(@RequestBody DataTableRequest<OrderParam> dtr,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<>();
		try{
		//	OrderParam param = dtr.getParams();
			List<Order> orderList= orderService.findMergeOrder(curUser.getUserId());
			orderService.merge(orderList,curUser.getUsername());
			retMap.put("result", true);
		}catch(Exception e){
			logger.error(e.getMessage());
			retMap.put("result", false);
		}
		return retMap;
	}
	
	@ResponseBody
	@RequestMapping(value="merge")
	public  Map<String,Object> selectMergeOrde(@RequestParam(value="id",required=true)Integer ids[],@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<>();
		try{
			List<Order> orderList = new ArrayList<Order>();
			for(Integer id:ids){
				Order o = orderService.load(id);
				//只能合并订单状态为未审核的订单
				if (o.getOrderStatus().intValue()!=OrderStatusEnum.NOAUDIT.code()){
					continue;
				}
				orderList.add(o);
			}
			String msg = orderService.merge(orderList,curUser.getUsername());
			retMap.put("result", true);
			retMap.put("msg", msg);
		}catch(Exception e){
			logger.error("订单手动合并错误:",e);
			retMap.put("result", false);
			retMap.put("msg", "订单手动合并错误");
		}
		return retMap;
	}
	
	/**
	 * 订单折分
	 * "1.订单列表和卖家订单中增加订单拆分功能按钮。
		2.能执行订单拆分功能的订单为未审核的订单。
		3.订单拆分为除开第一个SKU行，其余每个SKU行成为一个新的订单。
		4。新订单行中除了订单号变化其他的订单基本信息不变。新的订单中只会有一个SKU行"
	 * @return
	 */
	@RequestMapping(value="split")
	@ResponseBody
	public Map<String,Object> split(@RequestParam(value="id",required=true)Integer ids[],@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<>();
		try{
			List<Order> orderList = new ArrayList<>();
			for(Integer oid:ids){
				Order order = orderService.load(oid);
				//只能拆分未审核订单
				if (order.getOrderStatus().intValue()!=OrderStatusEnum.NOAUDIT.code()){
					continue;
				}
				//只折分混合sku订单
				if (order.getItems().size()<2){
					continue;
				}
				orderList.add(order);
			}
			if (orderList.size()==0){
				throw new Exception("请选择未审核的混合sku订单进行拆分");
			}
			String msg = orderService.split(orderList, curUser.getUsername());
			retMap.put("result", true);
			retMap.put("msg",msg);
		}catch(Exception e){
			logger.error("订单折分异常",e);
			retMap.put("result", false);
			retMap.put("msg", e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * 订单退款
	 * @param id
	 * @param refundAmount   退款额
	 * @param refundReason	 退款原因
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="refund")
	public String refund(OrderRefund refund,RedirectAttributes attr,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,@RequestHeader(value="Referer", defaultValue="") String referer){
		try{
			Order order = orderService.load(refund.getOrderId());
			if (order!=null){
				BigDecimal origiRefund = (null==order.getRefundFee())?new BigDecimal(0):order.getRefundFee();
				String origiRefundReason = (null==order.getRefundReason())?"":order.getRefundReason();
				origiRefund = origiRefund.add(refund.getTotal());
				origiRefund = origiRefund.compareTo(order.getAmount())>0?order.getAmount():origiRefund;
				order.setRefundFee(origiRefund);
				if (origiRefundReason.length()>0){
					order.setRefundReason(origiRefundReason+","+refund.getRefundreson());
				}else{
					order.setRefundReason(refund.getRefundreson());
				}
				
				orderService.update(order);
				OrderLog log = new OrderLog();
				log.setOrderId(refund.getOrderId());
				log.setOperTime(new Date());
				log.setOperUserId(curUser.getUsername());
				log.setLog("申请订单退款:"+(refund.getTotal().compareTo(order.getAmount())>0?order.getAmount():refund.getTotal())+",退款原因:"+refund.getRefundreson());
				orderLogService.insert(log);
				
				refund.setCreateuser(String.valueOf(curUser.getUserId()));
				orderService.applyRefund(refund,order);
				attr.addFlashAttribute("successMessage",this.messageSource.getMessage("g.tips.success", null, null));
			}
		}catch(Exception e){
			e.printStackTrace();
			attr.addFlashAttribute("errorMessage", this.messageSource.getMessage("g.tips.error", null, null));
		}
		return "redirect:"+referer;
	}
	
	/**
	 * 跟踪号批量导入
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="importTraceNumer")
	public String traceNumberImport(MultipartHttpServletRequest request,@RequestParam("showEbay") Integer showEbay,RedirectAttributes attr){
		InputStream is = null;
		MultipartFile file = null;
		String fileName = null;
		List<Order> orderList = new ArrayList<>();
		List<String[]> rowsList;
		List<Integer> orderIds = new ArrayList<>();
		try{
			file = request.getFile("traceNumberFile");
			fileName = file.getOriginalFilename();
			int idx = fileName.lastIndexOf(".");
			String extensionName = fileName.substring(idx + 1);
			if (null==file || idx==-1 || file.isEmpty() || !extensionName.startsWith("xls")){
				attr.addFlashAttribute("errorMessage","导入文件格式不对或导入文件为空");
			}else{
				is = file.getInputStream();
				rowsList = ExcelParser.parseToString(is, extensionName);
				for(int i=1;i<rowsList.size();i++){
					String [] row = rowsList.get(i);
					if (row==null || StringUtils.isEmpty(row[0])){
						continue;
					}
					String  orderId = row[0].trim();	//订单号
					String packageId = row[1];	//包裹号
					String tranceNumber = row[2];	//跟踪号
					String shippingName = row[3];	//运输方式
					if ((StringUtils.isEmpty(orderId) && StringUtils.isEmpty(packageId))  || StringUtils.isEmpty(tranceNumber) || StringUtils.isEmpty(shippingName)){
						continue;
					}
					Order order = null;
					if (StringUtils.isNotBlank(orderId)){
						order = orderService.load(Integer.valueOf(orderId));
						orderIds.add(order.getId());
					}
					//跟踪号已经存在,则跳过处理
					BigDecimal count = orderService.countByTrackNumber(tranceNumber.trim()); 
					if (count!=null && count.intValue()>0){
						continue;
					}
					//导入订单的跟踪号
					if (order!=null){
						order.setTrackNumber(tranceNumber.trim());
						order.setShippingName(shippingName.trim());
						orderList.add(order);
					}
				}
				
				
				//执行跟踪号，物流方式导入
				if (showEbay!=2){
					int deal = 100;		//每次处理订单跟踪号导入数
					int circlecount = orderList.size()%deal==0?orderList.size()/deal:orderList.size()/deal+1;
					for(int i=0;i<circlecount;i++){
						int from = i*deal;
						int to = (i+1)*deal;
						to = to<orderList.size()?to:orderList.size();
						List<Order> dealIdList = orderList.subList(from,to);
						orderService.traceNumberImport(dealIdList);
						for(Order order:dealIdList){
							orderPackageDao.updateTrackNumberAndShippingNameByOrderId(order.getId());
						}
					}
				}
				
				attr.addFlashAttribute("successMessage",this.messageSource.getMessage("g.tips.success", null, null));
				//显示需要二次标发的ebay订单
				if (showEbay==1 || showEbay==2){
					attr.addFlashAttribute("idList", orderIds);
				}
			}
		}catch(Exception e){
			attr.addFlashAttribute("errorMessage", this.messageSource.getMessage("g.tips.error", null, null));
		}finally{
			rowsList = null;
			orderList = null;
			try {
				if (null!=is){
						is.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return "redirect:/order/list";
	}
	
	/**
	 * 启用取消订单
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("orderEnable")
	public  Map<String,Object> orderEnable(@RequestParam(value="orderId") Integer orderId){
		Map<String,Object> retMap = new HashMap<>(); 
		try{
			Order order = orderDao.load(orderId);
			orderService.reStoreOrder(order);
			goodsInventoryLockDao.deleteBySaleOrderId(order.getId());		//删除订单所对应的锁定库存
			retMap.put("result", true);
		}catch(Exception e){
			retMap.put("result", false);
			retMap.put("error", e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * 订单标发
	 * @param ordersIds
	 * @return
	 */
	@RequestMapping("mark-shipping")
	public String markShipping(Model model, 
			@RequestParam(value="id") List<Integer> orderIds,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser) {
		
		List<OrderMarkShippingResult> results = this.markShippingService.mark(orderIds);
		orderPackageService.updateShipInfo(orderIds);//更新包裹
		model.addAttribute("results", results);
		for(OrderMarkShippingResult result:results){
			OrderLog log = new OrderLog();
			log.setOrderId(result.getOrderId());
			log.setOperTime(new Date());
			log.setOperUserId(curUser.getUsername());
			log.setLog("订单标发:" +result.toString());
			orderLogService.insert(log);
		}
		return "orders/mark-shipping";
	}
	/**
	 * 订单行取消
	 * @param sku
	 * @param orderId
	 * @param cancelAmount
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="cancelOrderItem.json")
	@ResponseBody
	public Map<String,Object> cancelOrderItem(
			@RequestParam("itemId")Integer itemId,@RequestParam("cancelAmount")Integer cancelAmount,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<>();
		try{
			orderService.cancelOrderItem(itemId, cancelAmount,curUser.getUsername());
			retMap.put("result", true);
		}catch(Exception e){
			retMap.put("result", false);
			retMap.put("error", e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * 取消订单
	 * @param orderId
	 * @param curUser
	 * @return
	 */
	@RequestMapping(value="cancelOrder.json")
	@ResponseBody
	public Map<String,Object> cancelOrder(@RequestParam("orderId")List<Integer>orderId,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<>();
		try{
			for(Integer id:orderId){
				orderService.cancelOrder(id,curUser.getUsername());
			}
		}catch(Exception e){
			retMap.put("result", false);
			retMap.put("error", e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * 停止交易
	 * * @param orderId
	 * @param curUser
	 * @return
	 */
	@RequestMapping(value="stop-order.json")
	@ResponseBody
	public Map<String,Object> stopOrder(@RequestParam("orderId")List<Integer>orderId,@RequestParam("msg")String msg,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<>();
		try{
			for(Integer id:orderId){
				orderService.stopOrder(id,curUser.getUsername(),msg);
			}
		}catch(Exception e){
			retMap.put("result", false);
			retMap.put("error", e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * 生成包裹
	 * @param id
	 * @param itemId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("gepack")
	public Map<String,Object> generateOrderPack(@RequestParam(value="orderId",required=true)Integer []orderId,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<>();
		try{
			for(Integer id:orderId){
				Order order = orderService.load(id);
				//处理待锁定,已锁定的正常订单
				if (null!=order && order.getSuspend()==0 
						&& (order.getOrderStatus().intValue()==OrderStatusEnum.AUDITED.code() || order.getOrderStatus().intValue()==OrderStatusEnum.LOCKED.code() )
				   ){
					orderPackageService.generatePackage(id,curUser.getUsername());
				}
			}
			retMap.put("result", true);
		}catch(Exception e){
			logger.error("订单生成包裹异常", e);
			retMap.put("result", false);
			retMap.put("error", "订单生成包裹异常");
		}
		return retMap;
	}
	
	/**
	 * 根据订单ID获得发货方式与运费
	 * @param orderId
	 * @return
	 */
	@RequestMapping("shipFee-json")
	@ResponseBody
	public List<OrderShippingFee> shipFeeJson(@RequestParam("orderId")Integer orderId){
		return this.orderService.getShipFee(orderId);
	}
	
	/**
	 * 设置发货方式1
	 * @param param
	 * @return
	 * @param id 【order_shipping_fee表ID】
	 */
	@RequestMapping("setShipping")
	@ResponseBody
	public boolean setShipping(@RequestParam("id")Integer id){
		boolean bl = this.orderService.setShipping(id);
		if(bl){
			return true;
		}else{
			return false;
		}
	}
	
	/***
	 * 发货清单
	 * @param param
	 * @param response
	 * @throws Exception
	 * 没有勾选功能
	 */
	@RequestMapping("exportShipping")
	public void exportShipping(OrderParam param,HttpServletResponse response)throws Exception{
		String thisSellerId = param.getSellerId();
		
		if("-1".equals(thisSellerId) || "-2".equals(thisSellerId)){
			param.setSellerId("");
			if("-1".equals(thisSellerId)){
				param.setSellerType("1");//自营卖家
			}else if("-2".equals(thisSellerId)){
				param.setSellerType("0");//非自营卖家
			}
		}
		
		if ("c.sku".equals(param.getSearchColumn()) && StringUtils.isNotBlank(param.getSearchValue().trim())){
			param.setSkuList(Arrays.asList(param.getSearchValue().split(",")));
		}
		
		List<ExportOrder> rows= orderService.exportInvoice(param);
		
		if(rows.size() > App.getConfigInt("export.excel.maxnumber")){
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("error");
			response.getWriter().close();
		}else{
			
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("大类","baseCategorie"));
		cs.add(new Column("SKU","goodsSku"));
		cs.add(new Column("总数量","totalCount"));
		cs.add(new Column("总金额","amount","#,##0.0000"));
		cs.add(new Column("总重量","totalWeight","#,##0.0000"));
		
		String filename = "发货清单.xlsx";
		ExcelBuilder<ExportOrder> eb = new ExcelBuilder<ExportOrder>();
		eb.setFileType(FileType.XLSX);
		eb.setData(rows);
		eb.setColumns(cs);
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
		+new String(filename.getBytes("GBK"),"ISO-8859-1"));
		
		ServletOutputStream os = response.getOutputStream();
		eb.toOutputStream(os);
		os.flush();
		os.close();
		}
	}
	
	/***
	 * 导出订单-
	 * @param param
	 * @param ids
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportOrder")
	public void exportOrder(OrderParam param,@RequestParam(value="ids",required=false) List<Integer> ids,
			HttpServletResponse response)throws Exception{


		
		if (ids == null) {
			
			String thisSellerId = param.getSellerId();
			
			if("-1".equals(thisSellerId) || "-2".equals(thisSellerId)){
				param.setSellerId("");
				if("-1".equals(thisSellerId)){
					param.setSellerType("1");//自营卖家
				}else if("-2".equals(thisSellerId)){
					param.setSellerType("0");//非自营卖家
				}
			}
			
			if ("c.sku".equals(param.getSearchColumn()) && StringUtils.isNotBlank(param.getSearchValue().trim())){
				param.setSkuList(Arrays.asList(param.getSearchValue().split(",")));
			}
			
			Integer count = orderService.expirtOrderCount(param, ids);
			if (count > App.getConfigInt("export.excel.maxnumber")) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print("error");
				response.getWriter().close();
			} else {
				List<ExportOrder> rows = orderService.expirtOrder(param, ids);
				this.exportOrderColumn(response, rows);
			}

		} else {
			List<ExportOrder> rows = orderService.expirtOrder(param, ids);
			this.exportOrderColumn(response, rows);
		}
	
	}
	
	
	
	private void exportOrderColumn(HttpServletResponse response,List<ExportOrder> rows)throws Exception{
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("订单ID","orderId"));
		cs.add(new Column("买家邮箱","buyEmail"));
		cs.add(new Column("SKU","sku"));
		cs.add(new Column("数量","itemQuantity"));
		cs.add(new Column("发货方式","shippingName"));
		cs.add(new Column("跟踪号","trackNumber"));
		cs.add(new Column("销售时间","orderSaleTime"));
		cs.add(new Column("付款时间","orderPaidTime"));
		cs.add(new Column("平台账号","accountName"));
		
		cs.add(new Column("扫描时间","scannedTime"));
		cs.add(new Column("收件人","buyName"));
		cs.add(new Column("收货地址1","shippingStreet1"));
		cs.add(new Column("收货地址2","shippingStreet2"));
		cs.add(new Column("收货城市","shippingCity"));
		cs.add(new Column("收货省份","shippingState"));
		cs.add(new Column("邮编","shippingPostcode"));
		cs.add(new Column("收货国家缩写","shippingCountry"));
		cs.add(new Column("收货国家全称","shippingCountryName"));
		cs.add(new Column("手机号","shippingPhone"));
		
		cs.add(new Column("单个成本价", "purchasePrice", new FieldFormatter<BigDecimal,ExportOrder>(){
			@Override
			public Object format(BigDecimal purchasePrice,ExportOrder exportOrder) {
				if(purchasePrice != null){
					return	purchasePrice.setScale(1, BigDecimal.ROUND_HALF_UP);
				}else{
					return new BigDecimal(0);
				}
				
			}
		}));
		
		cs.add(new Column("单个重量","weight"));
		cs.add(new Column("订单称重运费","packageShipfee"));
		cs.add(new Column("订单称重重量","packageWeight"));
		cs.add(new Column("订单计算重量","calcWeight"));
		cs.add(new Column("订单计算运费","calcShipfee"));
		
		cs.add(new Column("锁定数量","lockAmount"));
		cs.add(new Column("需要采购数","needPurchase"));
		cs.add(new Column("出货数量总和","shipmentAmount"));
		cs.add(new Column("退款金额","refundFee"));
	
		String filename = "导出订单.xlsx";
		ExcelBuilder<ExportOrder> eb = new ExcelBuilder<ExportOrder>();
		eb.setFileType(FileType.XLSX);
		eb.setData(rows);
		eb.setColumns(cs);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
		+new String(filename.getBytes("GBK"),"ISO-8859-1"));
		
		ServletOutputStream os = response.getOutputStream();
		eb.toOutputStream(os);
		os.flush();
		os.close();
		
	}

	/**
	 * 销售报表
	 * @param param
	 * @param ids
	 * @param response
	 * @throws Exception
	 * 没有勾选功能
	 */
	@RequestMapping("exportSale")
	public void exportSale(OrderParam param,
			@RequestParam(value="ids",required=false)List<Integer> ids,
			HttpServletResponse response)throws Exception{
		
		if(ids == null){
			
			String thisSellerId = param.getSellerId();
			if("-1".equals(thisSellerId) || "-2".equals(thisSellerId)){
				param.setSellerId("");
				if("-1".equals(thisSellerId)){
					param.setSellerType("1");//自营卖家
				}else if("-2".equals(thisSellerId)){
					param.setSellerType("0");//非自营卖家
				}
			}
		}
		
		if ("c.sku".equals(param.getSearchColumn()) && StringUtils.isNotBlank(param.getSearchValue().trim())){
				param.setSkuList(Arrays.asList(param.getSearchValue().split(",")));
			}

		List<ExportOrder> rows= orderService.exportSale(param,ids);
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("平台","orderPlatform"));
		cs.add(new Column("账号","accountName"));
		cs.add(new Column("订单数量","countOrder"));
		cs.add(new Column("item数量","countItem"));
		cs.add(new Column("销售额$","totalAmount"));
		cs.add(new Column("EBAY费用$","ebayFee"));
		cs.add(new Column("paypal费用$","paypalFee"));
		cs.add(new Column("总成本","totalCost"));
		cs.add(new Column("退款金额","refundFee"));
		cs.add(new Column("称重总重量","totalWeight"));
		cs.add(new Column("称重总运费","totalShippingFee"));
		cs.add(new Column("计算总重量","calcWeight"));
		cs.add(new Column("计算总运费","calcShipfee"));
		
		String filename = "销售报表.xlsx";
		ExcelBuilder<ExportOrder> eb = new ExcelBuilder<ExportOrder>();
		eb.setFileType(FileType.XLSX);
		eb.setData(rows);
		eb.setColumns(cs);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
		+new String(filename.getBytes("GBK"),"ISO-8859-1"));
		
		ServletOutputStream os = response.getOutputStream();
		eb.toOutputStream(os);
		os.flush();
		os.close();
	}
	
	/**
	 * 导出缺货订单
	 * @return
	 */
	@RequestMapping("exportStockOut")
	public void exportStockOutOrder(HttpServletResponse response) throws Exception{
		List<StockOutSku> rows= orderService.exportStockOut();
		String filename = "缺货订单.xlsx";
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("平台","platForm"));
		cs.add(new Column("订单ID","orderId"));
		cs.add(new Column("平台订单号","orderSn"));
		cs.add(new Column("同步时间","createdTime"));
		cs.add(new Column("产品Sku","sku"));
		cs.add(new Column("平台Sku","itemSku"));
		cs.add(new Column("平台Sku名称","itemName"));
		cs.add(new Column("购买数量","itemQuantity"));
		cs.add(new Column("缺货数量","stockOut"));
		cs.add(new Column("采购员","buyerName"));
		cs.add(new Column("sku缺货统计","stockOutCount"));
		
		/*
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("平台","platForm"));
		cs.add(new Column("订单ID","orderId"));
		cs.add(new Column("平台订单号","orderSn"));
		cs.add(new Column("同步时间","createdTime"));
		cs.add(new Column("产品Sku","sku"));
		cs.add(new Column("平台Sku","itemSku"));
		cs.add(new Column("平台Sku名称","itemName"));
		cs.add(new Column("购买数量","itemQuantity"));
		cs.add(new Column("缺货数量","stockOut"));
		cs.add(new Column("采购员","buyerName"));
		cs.add(new Column("sku缺货统计","stockOutCount"));
		
		ServletOutputStream os = null;
		try{
			ExcelBuilder<StockOutSku> eb = new ExcelBuilder<StockOutSku>();
			eb.setFileType(FileType.XLSX);
			eb.setData(rows);
			eb.setColumns(cs);
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
			+new String(filename.getBytes("GBK"),"ISO-8859-1"));
			
			os = response.getOutputStream();
			eb.toOutputStream(os);
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			rows = null;
			if (null!=os){
				os.flush();
				os.close();
			}
		}
		*/
		ExportUtil.export(filename, response, rows,cs);
	}
	
	
	@RequestMapping("batchSetShip")
	public String batchSetShip(OrderParam param){
		List<Order> list = orderService.findOrder(param);
		
		for (int i = 0; i < list.size(); i++) {
			orderService.batchSetShip(list.get(i).getId());
		}
		return "redirect:/order/list";
	}
	
	@RequestMapping("importTrackPage")
	public String importTrack(){
		return "orders/import-tracknumber";
	}
	
	
	/***
	 * 提交跟踪号信息
	 * @param file 文件
	 * @param tNumber 类
	 * @param type 类型
	 * @return
	 */
	@RequestMapping("importTrackInfo")
	public String importTrackInfo(@RequestParam("fileName")MultipartFile file,
			TrackNumber tNumber,RedirectAttributes redirectAttr,
			@RequestParam("type")Integer type){
		try {
			orderService.importTrackFile(file, tNumber, type);
			redirectAttr.addFlashAttribute("successMessage", "导入成功");
		} catch (FileImportException e) {
			logger.error(e.getMessage());
			redirectAttr.addFlashAttribute("successMessage", "导入失败");
		}
		return "redirect:/order/importTrackPage";
	}
	
	
	/****
	 * 顺邮宝申请跟踪号
	 * @param model
	 * @param orderIds
	 * @param type
	 * @return
	 */
	@RequestMapping("syb-trackernumber")
	public String applySybTrackerNumber(Model model,
			@RequestParam("id") List<Integer> orderIds,
			@RequestParam("type")Integer type,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		List<TrackerApplyResult> result = orderService.sybTrackerNumber(orderIds, type);
		model.addAttribute("results", result);
		//保存订单操作日志
		for(TrackerApplyResult rs:result){
			OrderLog log = new OrderLog();
			log.setOrderId(rs.getOrderId());
			log.setOperTime(new Date());
			log.setOperUserId(curUser.getUsername());
			log.setLog("申请跟踪号-导入:"+rs);
			orderLogService.insert(log);
		}
		return "orders/tracker-number";
	}
	
	@ResponseBody
	@RequestMapping("check-ordersn.json")
	public Boolean checkOrderSn(@RequestParam("orderSn") String orderSn){
		Order o = orderDao.findByOrderSn(orderSn);
		return o==null?true:false;
	}
	
	/***
	 * 国际物流导出格式
	 * @param param
	 * @param ids
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportEubOrder")
	public void exportEubOrder(OrderParam param,@RequestParam(value="ids",required=false) List<Integer> ids,
			HttpServletResponse response)throws Exception{
		
		if (ids == null) {
			
			String thisSellerId = param.getSellerId();
			
			if("-1".equals(thisSellerId) || "-2".equals(thisSellerId)){
				param.setSellerId("");
				if("-1".equals(thisSellerId)){
					param.setSellerType("1");//自营卖家
				}else if("-2".equals(thisSellerId)){
					param.setSellerType("0");//非自营卖家
				}
			}
			
			if ("c.sku".equals(param.getSearchColumn()) && StringUtils.isNotBlank(param.getSearchValue().trim())){
				param.setSkuList(Arrays.asList(param.getSearchValue().split(",")));
			}
			
			Integer count = orderService.expirtOrderCount(param, ids);
			if (count > App.getConfigInt("export.excel.maxnumber")) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print("error");
				response.getWriter().close();
			} else {
				List<ExportOrder> rows = orderService.expirtOrder(param, ids);
				this.exportEubOrderColumn(response, rows);
			}

		} else {
			List<ExportOrder> rows = orderService.expirtOrder(param, ids);
			this.exportEubOrderColumn(response, rows);
		}
	}
	
	
	private void exportEubOrderColumn(HttpServletResponse response,List<ExportOrder> rows)throws Exception{
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("订单ID","orderId"));
		cs.add(new Column("跟踪号","trackNumber"));
		cs.add(new Column("收件人","buyName"));
		cs.add(new Column("收货地址1","shippingStreet1"));
		cs.add(new Column("收货地址2","shippingStreet2"));
		cs.add(new Column("收货城市","shippingCity"));
		cs.add(new Column("收货省份","shippingState"));
		cs.add(new Column("邮编","shippingPostcode"));
		cs.add(new Column("收货国家缩写","shippingCountry"));
		cs.add(new Column("收货国家全称","shippingCountryName"));
		cs.add(new Column("SKU","sku"));
		cs.add(new Column("购买数量","itemQuantity"));
		cs.add(new Column("内件名称","declarationNameCn"));
		cs.add(new Column("内件英文名称","declarationNameEn"));
		cs.add(new Column("手机号","mobile"));
		cs.add(new Column("电话号","shippingPhone"));
		cs.add(new Column("单个重量","weight"));
		
		String filename = "国际物流导出.xlsx";
		ExcelBuilder<ExportOrder> eb = new ExcelBuilder<ExportOrder>();
		eb.setFileType(FileType.XLSX);
		eb.setData(rows);
		eb.setColumns(cs);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
		+new String(filename.getBytes("GBK"),"ISO-8859-1"));
		
		ServletOutputStream os = response.getOutputStream();
		eb.toOutputStream(os);
		os.flush();
		os.close();
		
	}
	
	/**
	 * 导入退回订单页面
	 * @return
	 */
	@RequestMapping("importOrderPage")
	public String orderReturnImport(){
		return "orders/order-return-import";
	}
	
	/***
	 * 导入退回订单
	 * @param file
	 * @return
	 */
	@RequestMapping("importOrderReturn")
	public String importOrderReturn(Model model,@RequestParam("fileName")MultipartFile file,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser, Integer returnType){
		
		try {
			List<Map<String, String>> list = this.orderService.importOrderReturn(file, returnType, curUser.getUsername());
				if(list.size() == 0){
					model.addAttribute("successMessage", "导入成功");
				}else{
					model.addAttribute("successMessage", list);
				}
		} catch (FileImportException e) {
			logger.error(e.getMessage());
			model.addAttribute("errorMessage", String.format("%s行 %s列, %s", e.getLineNumber(), e.getColumnNumber(), e.getMessage()));
		}catch (Exception e) {
			logger.error("导入出错", e);
			model.addAttribute("errorMessage", e.getMessage());
		}
		return "orders/order-return-import";
	}
	
	/***
	 * 处理退货订单页面
	 * @return
	 */
	@RequestMapping("addReturnNotePage")
	public String addReturnNotePage(Model model){
		return "orders/order-return-note";
	}
	
	/***
	 * 已退货处理
	 * @param orderIds
	 * @param returnNote
	 * @return
	 */
	@RequestMapping("saveReturnNote")
	@ResponseBody
	public String saveReturnNote(Model model, @ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,
			@RequestParam("ids")List<Integer> ids,String returnNote){
		Order order = new Order();
		order.setId(ids.get(0));
		order.setReturnNote(returnNote.trim());
		order.setOrderStatus((short)9);
		String back = "succ";
		try {
			
			this.orderDao.update(order);//orderDao函数
			OrderLog log = new OrderLog();
			log.setLog("订单变成 已处理退货订单");
			log.setOperTime(new Date());
			log.setOperUserId(curUser.getUsername());
			log.setOrderId(ids.get(0));
			this.orderLogService.insert(log);
			
			model.addAttribute(back, "succ");
		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute(back, "error");
		}
		return back;
	}
	
	@RequestMapping("getOrderStatus")
	@ResponseBody
	public boolean getOrderStatus(@RequestParam("ids")List<Integer> ids){
		
		Order order = this.orderService.load(ids.get(0));
		Short os = order.getOrderStatus();
		if(os == 8 || os == 9){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 统计所查询订单的sku数量及订单金额
	 * @param param
	 * @return
	 */
	@RequestMapping("orderStatistic.json")
	@ResponseBody
	public OrderStatistic  orderStatistic(OrderParam param){
		return this.orderService.orderStatistic(param);
	}
	
	/**
	 * 手动同步订单
	 * @return
	 */
	@ResponseBody
	@RequestMapping("syncOrder.json")
	public Map<String,Object> syncOrder(){
		Map<String,Object> retMap = new HashMap<>();
		try{
			Integer syncOrderCount = orderService.batchInsertOrders();
			if (syncOrderCount==-1){
				retMap.put("msg", "订单正在同步中...请稍侯再试");
			}else{
				retMap.put("msg", String.format("本次成功同步%d个订单",syncOrderCount));
			}
		}catch(Exception e){
			retMap.put("msg", "同步订单发生异常");
		}
		return retMap;
	}
	
	/***
	 * 订单费用导出
	 * @param param
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportOrderFeeInfo")
	public void exportOrderFeeInfo(OrderParam param, HttpServletResponse response)throws Exception{
		String thisSellerId = param.getSellerId();
		if("-1".equals(thisSellerId) || "-2".equals(thisSellerId)){
			param.setSellerId("");
			if("-1".equals(thisSellerId)){
				param.setSellerType("1");//自营卖家
			}else if("-2".equals(thisSellerId)){
				param.setSellerType("0");//非自营卖家
			}
		}
		
		if ("c.sku".equals(param.getSearchColumn()) && StringUtils.isNotBlank(param.getSearchValue().trim())){
			param.setSkuList(Arrays.asList(param.getSearchValue().split(",")));
		}
		
		Integer count = this.orderService.findOrderFeeCount(param);
		if(count > App.getConfigInt("export.excel.maxnumber")){
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("error");
			response.getWriter().close();
		}else{
			
		List<ExportOrder> rows = this.orderService.findOrderFee(param);
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("发货扫描时间","scannedTime"));
		cs.add(new Column("订单编号","orderId"));
		cs.add(new Column("用户姓名","contacts"));
		cs.add(new Column("平台账号","accountName"));
		cs.add(new Column("订单总成本","totalCost"));
		cs.add(new Column("称重运费","packageShipfee"));
		
		cs.add(new Column("服务费","sku",new FieldFormatter<Object, Object>(){
			@Override
			public Object format(Object fieldValue, Object rowValue) {
				return "2";
			}
			
		}));
		cs.add(new Column("订单总金额","totalAmount"));
		
		String filename = "订单费用信息.xlsx";
		ExcelBuilder<ExportOrder> eb = new ExcelBuilder<ExportOrder>();
		eb.setFileType(FileType.XLSX);
		eb.setData(rows);
		eb.setColumns(cs);
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
		+new String(filename.getBytes("GBK"),"ISO-8859-1"));
		
		ServletOutputStream os = response.getOutputStream();
		eb.toOutputStream(os);
		os.flush();
		os.close();
		
		}
		
	}
	
	@ResponseBody
	@RequestMapping("lockInventory.json")
	public Map<String,Object> lockInventory(@RequestParam("ids")Integer [] ids){
		Map<String,Object> retMap = new HashMap<>();
		try{
			List<Integer> idList = new ArrayList<>();
			// 手工锁定只处理待锁定未暂停订单
			for(Integer id:ids){
				Order o = this.orderService.load(id);
				if (o.getOrderStatus().intValue()==OrderStatusEnum.AUDITED.code() && o.getSuspend()==0){
					idList.add(id);
				}
			}
			orderService.lockOrderInventory(idList.toArray(new Integer[idList.size()]));
			retMap.put("result", true);
		}catch(Exception e){
			retMap.put("result", false);
			retMap.put("msg", "手动锁定库存失败");
		}
		return retMap;
	}
	
	
	/**
	 * 根据订单号导出订单PAGE
	 * @return
	 */
	@RequestMapping("exportOrderPage")
	public String exportOrderPage(){
		return "orders/order-export";
	}
	
	
	/***
	 * 根据订单号码导出报表
	 * @param file
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportByOrderId")
	public void uploadOrderFile(@RequestParam("fileName")MultipartFile file,HttpServletResponse response)throws Exception{
			List<Integer> list = this.orderService.uploadOrderFile(file);
			List<ExportOrder> rows = this.orderDao.findOrderInfoById(list);
			List<Column> cs = new ArrayList<Column>();
			cs.add(new Column("订单ID","orderId"));
			cs.add(new Column("平台","orderPlatform"));
			cs.add(new Column("账号","accountName"));
			cs.add(new Column("跟踪号","trackNumber"));
			cs.add(new Column("收件人","buyName"));
			cs.add(new Column("收货地址1","shippingStreet1"));
			cs.add(new Column("收货地址2","shippingStreet2"));
			cs.add(new Column("收货城市","shippingCity"));
			cs.add(new Column("收货省份","shippingState"));
			
			cs.add(new Column("邮编","shippingPostcode"));
			cs.add(new Column("收货国家缩写","shippingCountry"));
			cs.add(new Column("收货国家全称","shippingCountryName"));
			cs.add(new Column("SKU","sku"));
			cs.add(new Column("老sku","oldSku"));
			cs.add(new Column("购买数量","itemQuantity"));
			cs.add(new Column("下单时间","orderSaleTime"));
			String filename = "国际物流导出.xlsx";
			ExcelBuilder<ExportOrder> eb = new ExcelBuilder<ExportOrder>();
			eb.setFileType(FileType.XLSX);
			eb.setData(rows);
			eb.setColumns(cs);
			
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
			+new String(filename.getBytes("GBK"),"ISO-8859-1"));
			
			ServletOutputStream os = response.getOutputStream();
			eb.toOutputStream(os);
			os.flush();
			os.close();
	}
	
	
	@RequestMapping("exportOrder2")
	public void exportOrderInfo2(OrderParam param,@RequestParam(value="ids",required=false) List<Integer> ids,
			HttpServletResponse response)throws Exception{
		if (ids == null) {
			String thisSellerId = param.getSellerId();
			if("-1".equals(thisSellerId) || "-2".equals(thisSellerId)){
				param.setSellerId("");
				if("-1".equals(thisSellerId)){
					param.setSellerType("1");//自营卖家
				}else if("-2".equals(thisSellerId)){
					param.setSellerType("0");//非自营卖家
				}
			}
			
			if ("c.sku".equals(param.getSearchColumn()) && StringUtils.isNotBlank(param.getSearchValue().trim())){
				param.setSkuList(Arrays.asList(param.getSearchValue().split(",")));
			}
			
			Integer count = orderService.expirtOrderCount(param, ids);
			if (count > App.getConfigInt("export.excel.maxnumber")) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print("error");
				response.getWriter().close();
			} else {
				List<ExportOrder> rows = orderService.expirtOrder(param, ids);
				this.exportOrderColumn2(response, rows);
			}

		} else {
			List<ExportOrder> rows = orderService.expirtOrder(param, ids);
			this.exportOrderColumn2(response, rows);
		}
	}
	
	private void exportOrderColumn2(HttpServletResponse response,List<ExportOrder> rows)throws Exception{
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("订单ID","orderId"));
		cs.add(new Column("产品名","goodsName"));
		cs.add(new Column("英文名","enName")); 
		cs.add(new Column("平台","orderPlatform"));
		cs.add(new Column("账号","accountName"));
		cs.add(new Column("跟踪号","trackNumber"));
		cs.add(new Column("收件人","buyName"));
		cs.add(new Column("收货地址1","shippingStreet1"));
		cs.add(new Column("收货地址2","shippingStreet2"));
		cs.add(new Column("收货城市","shippingCity"));
		cs.add(new Column("收货省份","shippingState"));
		cs.add(new Column("邮编","shippingPostcode"));
		cs.add(new Column("收货国家缩写","shippingCountry"));
		cs.add(new Column("收货国家全称","shippingCountryName"));
		cs.add(new Column("SKU","sku"));
		cs.add(new Column("老sku","oldSku"));
		cs.add(new Column("购买数量","itemQuantity"));
		cs.add(new Column("下单时间","orderSaleTime"));
		cs.add(new Column("扫描时间","scannedTime"));
		
		String filename = "订单信息.xlsx";
		ExcelBuilder<ExportOrder> eb = new ExcelBuilder<ExportOrder>();
		eb.setFileType(FileType.XLSX);
		eb.setData(rows);
		eb.setColumns(cs);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
		+new String(filename.getBytes("GBK"),"ISO-8859-1"));
		
		ServletOutputStream os = response.getOutputStream();
		eb.toOutputStream(os);
		os.flush();
		os.close();
	}
	
}
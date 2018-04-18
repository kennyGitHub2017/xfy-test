package com.xuanfeiyang.erp.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.buzheng.excel.Column;
import org.buzheng.excel.ExcelBuilder;
import org.buzheng.excel.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.xuanfeiyang.erp.dao.OrderItemDao;
import com.xuanfeiyang.erp.dao.OrderPackageItemDao;
import com.xuanfeiyang.erp.dao.StoreShelfDao;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderMarkShippingResult;
import com.xuanfeiyang.erp.domain.OrderPackage;
import com.xuanfeiyang.erp.domain.OrderPackageItem;
import com.xuanfeiyang.erp.domain.OrderPackagePrintExport;
import com.xuanfeiyang.erp.domain.Shipping;
import com.xuanfeiyang.erp.enums.OrderPackageStatus;
import com.xuanfeiyang.erp.enums.OrderStatusEnum;
import com.xuanfeiyang.erp.param.OrderPackageParam;
import com.xuanfeiyang.erp.service.AccountService;
import com.xuanfeiyang.erp.service.GoodsService;
import com.xuanfeiyang.erp.service.MarkShippingService;
import com.xuanfeiyang.erp.service.OrderPackageService;
import com.xuanfeiyang.erp.service.OrderService;
import com.xuanfeiyang.erp.service.ShippingService;
import com.xuanfeiyang.erp.service.TrackerApplyResult;
import com.xuanfeiyang.erp.service.TrackerNumberService;
import com.xuanfeiyang.erp.service.impl.WeightCompareException;
import com.xuanfeiyang.erp.util.ExcelParser;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.Result;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/order-package")
@SessionAttributes(App.SESSION_USER_KEY)
public class OrderPackageController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderPackageController.class);
	
	@Resource
	private OrderPackageService orderPackageService;
	@Resource
	private OrderPackageItemDao orderPackageItemDao;
	@Resource
	private OrderItemDao orderItemDao; 
	@Resource
	private StoreShelfDao storeShelfDao;
	@Resource
	private OrderService orderService;
	@Resource
	private GoodsService goodsService;
	@Resource
	private MarkShippingService markShippingService;
	@Resource
	private AccountService accountService;
	@Resource
	private MessageSource messageSource;
	@Resource
	private TrackerNumberService trackerNumberService;
	@Resource
	private ShippingService shippingService;
	
	
	/**
	 * 批量修改包裹运输方式
	 * @return
	 */
	@RequestMapping("batchedit-page")
	public ModelAndView batcheditPackagePage(@RequestParam(value="id",required=false)String[] id,ModelAndView model){
		if (null==id){
			model.addObject("all", 1);
		}else{
			model.addObject("idCount",id.length);
			model.addObject("ids",id);
		}
		model.addObject("shippingList", shippingService.find());
		model.setViewName("orders/batchedit-package");
		return model;
	}
	
	@RequestMapping(value="batchedit")
	public ModelAndView batcheditPackage(@RequestParam(value="id",required=false)String[] ids,String shippingName,
			RedirectAttributes attr){
		if (ids==null || ids.length==0){
			return new ModelAndView("redirect:/order-package");
		}
		List<OrderPackage> packList = new ArrayList<>();
		for(String id:ids){
			OrderPackage pack= orderPackageService.getById(id);
			pack.setShippingName(shippingName);
			packList.add(pack);
		}
		orderPackageService.batcheditPackage(packList);
		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		return new ModelAndView("redirect:/order-package");
	}
	
	@RequestMapping({"", "/"})
	public String index(Model model,@RequestParam(value="orderId",required=false)Integer orderId){
		List<Shipping> list = shippingService.find();
		model.addAttribute("shippingAttr",list);
		if (null!=orderId){
			model.addAttribute("orderId",orderId);
		}
		return "orders/order-package";
	}
	
	/**
	 * 包裹列表
	 * @param dtr
	 * @return
	 */
	@RequestMapping("pageJson")
	@ResponseBody
	public DataTableResponse<OrderPackage> pageJson(@RequestBody DataTableRequest<OrderPackageParam> dtr){
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<OrderPackage>  page = this.orderPackageService.findPage(pageRequest, dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	/***
	 * 根据包裹id查询包裹明细
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("packageItem")
	@ResponseBody
	public List<OrderPackageItem> findByPackage(@RequestParam(value="id")String id){
		return orderPackageService.findByPackage(id);
	}
	
	/**
	 * 回退物流
	 * @param idList
	 * @return
	 */
	@RequestMapping("back")
	public String updateState(@RequestParam(value="idList")List<String> idList){
		int state = 9;//退回物流
		for (int i = 0; i < idList.size(); i++) {
			orderPackageService.updateState(state, idList.get(i));
		}
		return "redirect:/order-package";
	}
	
	/**
	 * 包裹扫描工作台页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "scanning", method = RequestMethod.GET)
	public String scanningPage(Model model){
		List<Shipping> list = shippingService.find();
		model.addAttribute("shippingAttr",list);
		return "orders/package-scanning";
	}
	
	/**
	 * 根据 跟踪号查询包裹信息
	 * 
	 * @return
	 */
	@RequestMapping("get-by-track-number.json")
	@ResponseBody
	public OrderPackage getByTrackNumber(
			@RequestParam(value="trackNumber", required=false) String trackNumber){
		
		return this.orderPackageService.getByTrackNumber(trackNumber);
	}
	
	/**
	 * 执行扫描后动作
	 * @param trackingNumber 跟踪号
	 * @return
	 */
	@RequestMapping(value = "confirm-scan.json")
	@ResponseBody
	public Result confirmScan(HttpServletRequest request,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser,
			@RequestParam(value="trackNumber", required=false) String trackNumber,
			@RequestParam(value="deviceNo", required=false) String deviceNo,
			@RequestParam(value="flag",required=false) String flag) {
		
		Result result = new Result();
		
		try {
			
			if(StringUtils.isBlank(trackNumber.trim())){
				throw new NullPointerException("未获取到跟踪号");
			}
			trackNumber = trackNumber.trim();
			
			OrderPackage res = this.orderPackageService.getByTrackNumber(trackNumber);
			
			if(OrderPackageStatus.WAITSEND.code() != res.getStatus()){
				
				OrderPackageStatus nowStatus = OrderPackageStatus.valueOfCode(res.getStatus());
				throw new Exception("包裹" + trackNumber+ "的状态为-" + nowStatus.desc());
			}
			
			if("0".equals(flag)){
				this.orderPackageService.readPackageWeight(trackNumber, deviceNo);
			}
		
			OrderPackage p = this.orderPackageService.confirmScan(trackNumber, sessionUser.getUsername());
			result.setData(p);
			result.setSuccess(true);
			
		} catch (WeightCompareException e) {
			
			logger.error("扫描包裹出错", e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			result.setConfirmFlag(true);

		} catch (Exception e) {
			
			logger.error("扫描包裹出错", e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			result.setData(this.orderPackageService.getByTrackNumber(trackNumber));
		}

		
		return result;
	}
	
	/**
	 * 物流公司交接页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "shipping", method = RequestMethod.GET)
	public String shippingPage(){
		return "orders/package-shipping";
	}
	
	/**
	 * 打印工作台
	 * @return
	 */
	@RequestMapping(value = "printList")
	public ModelAndView orderPrintList(ModelAndView model){
		List<Shipping> list = shippingService.find();
		model.addObject("shippingAttr",list);
		model.addObject("seach",new OrderPackageParam());
		model.setViewName("orders/order-package-print");
		return model;
	}
	
	
	/**
	 * 待发货工作台
	 * @return
	 */
	@RequestMapping(value = "waitSendList")
	public ModelAndView orderWaitSendList(ModelAndView model){
		List<Shipping> list = shippingService.find();
		model.addObject("shippingAttr",list);
		model.addObject("seach",new OrderPackageParam());
		model.setViewName("orders/order-waitsend");
		return model;
	}
	
	
	@RequestMapping(value = "sendFlag.json")
	@ResponseBody
	public Boolean ordersendFlag(@RequestParam("orderId")List<Integer> orderId){
		for(Integer id:orderId){
			this.orderService.updateStateById(OrderStatusEnum.WAITSEND.code(), id);
			Order order = this.orderService.load(id);
			OrderPackage pge = this.orderPackageService.getByTrackNumber(order.getTrackNumber());
			
			this.orderPackageService.updateState(OrderPackageStatus.WAITSEND.code(), pge.getId());
		}
		return Boolean.valueOf(true);
	}
	
	
	
	/**
	 * 包裹标发。
	 * 实际仍然是订单标发，根据包裹ID，找出对应的订单ID，然后标发订单。
	 * 
	 * @param ordersIds
	 * @return
	 */
	@RequestMapping("mark-shipping")
	public String markShipping(Model model, 
			@RequestParam(value="id") List<String> packageIds) {
		
		List<OrderMarkShippingResult> results = this.markShippingService.markPackages(packageIds);
		orderPackageService.updateShipInfo2(packageIds);//更新订单
		model.addAttribute("results", results);
		
		return "orders/mark-shipping";
	}
	
	/**
	 * 修改发货方式
	 * @param packageId 包裹Id
	 * @param id 订单运费选择表 id
	 * @return
	 */
	@RequestMapping("update-shipping.json")
	@ResponseBody
	public boolean updateShipping(@RequestParam(value="packageId")String packageId,
			@RequestParam(value="id")Integer id){
		try {
			orderPackageService.updateShipping(packageId, id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 申请包裹的快递跟踪号
	 * 
	 * @param model
	 * @param packageIds 订单ID列表
	 * @param type	平台类型  
	 * @param action 操作类型： apply: 申请(默认操作，可空)， cancel: 取消
	 * @return
	 */
	@RequestMapping(value = "tracker-number", method = {RequestMethod.GET,RequestMethod.POST})
	public String applyExpressTrackerNumber(
			Model model,
			@RequestParam("id") List<String> packageIds,
			@RequestParam("type") String type,
			@RequestParam("action") String action) {
		
		try {
			List<TrackerApplyResult> results = null;
			// 取消删除跟踪号, 目前只有 ebay apac 有此操作
			if ("cancel".equals(action)) {
				results = this.trackerNumberService.cancelApacForPackage(packageIds);
			} 
			// 交运, 目前只有 ebay apac 有此操作
			else if ("confirm".equals(action)) {
				results = this.trackerNumberService.confirmApacForPackage(packageIds);
			}
			// 根据类型申请跟踪号
			else {
				results = this.trackerNumberService.applyMultiPackages(type, packageIds);
			}
			model.addAttribute("results", results);
			
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}

		return "orders/tracker-number";
	}
	
	/**
	 * 匹配运输方式
	 * @return
	 */
	@RequestMapping("matchingShipping")
	public String matchingShipping(){
		orderPackageService.matchingShipping2();
		return "orders/order-package";
	}
	
	/**
	 * 物流退回导入
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="importRejectPack")
	public String traceNumberImport(MultipartHttpServletRequest request,RedirectAttributes attr){
		InputStream is = null;
		MultipartFile file = null;
		String fileName = null;
		try{
			file = request.getFile("rejectPackFile");
			fileName = file.getOriginalFilename();
			int idx = fileName.lastIndexOf(".");
			String extensionName = fileName.substring(idx + 1);
			if (null==file || idx==-1 || file.isEmpty() || !extensionName.startsWith("xls")){
				attr.addFlashAttribute("errorMessage","导入文件格式不对或导入文件为空");
			}else{
				is = file.getInputStream();
				List<String[]> rowsList = ExcelParser.parseToString(is, extensionName);
				List<String> tranceNumberList = new ArrayList<>();
				for(int i=1;i<rowsList.size();i++){
					String [] row = rowsList.get(i);
					String  traceNumber = row[0];	//跟踪号
					if (StringUtils.isNotBlank(traceNumber)){
						tranceNumberList.add(traceNumber);
					}
				}
				orderPackageService.rejectPackBytraceNumber(tranceNumberList);
				attr.addFlashAttribute("successMessage",this.messageSource.getMessage("g.tips.success", null, null));
			}
		}catch(Exception e){
			attr.addFlashAttribute("errorMessage", this.messageSource.getMessage("g.tips.error", null, null));
		}finally{
			try {
				if (null!=is){
						is.close();
				}
				//移除并删除临时文件
				if (null!=file){
					File f = null;
					String path = OrderController.class.getResource("").getPath();
					f = new File(path+File.separator+fileName);
					file.transferTo(f);
					f.delete();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());;
			}
		}
		return "redirect:/order-package";
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
			@RequestParam("id") List<String> packageIds,
			@RequestParam("type")Integer type){
			
		List<Integer> orderIds = new ArrayList<Integer>();
		for (String packageId : packageIds) {
			OrderPackage p = this.orderPackageService.getById(packageId);
			orderIds.add(p.getOrderId());
		}
		List<TrackerApplyResult> result = orderService.sybTrackerNumber(orderIds, type);
		model.addAttribute("results", result);
		return "orders/tracker-number";
	}
	
	/***
	 * 标记打印
	 * @param ids
	 * @return
	 */
	@RequestMapping("mark-print")
	@ResponseBody
	public Result markPrint(
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser,
			@RequestParam("ids") List<String> ids){
		Result result = new Result();
		try {
			for (String id : ids) {
				this.orderPackageService.markPrintFlag(id, sessionUser.getUsername());
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("标打印失败.", e);
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 打印工作台列表
	 * @param dtr
	 * @return
	 */
	@RequestMapping("printPageJson")
	@ResponseBody
	public DataTableResponse<OrderPackage> printPageJson(@RequestBody DataTableRequest<OrderPackageParam> dtr){
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<OrderPackage>  page = this.orderPackageService.printFindPage(pageRequest, dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}

	/**
	 * 接口调用  传入重量值
	 * @param weight
	 * @throws Exception
	 */
	@RequestMapping("packageWeight")
	@ResponseBody
	public Integer writerPackageWeight(String weight,HttpServletRequest request,String deviceNo) throws Exception {
		try {
			Double.parseDouble(weight);
		} catch (Exception e) {
			weight="0";
		}
		
		File file = new File(App.getConfig("electron.weight.file")+deviceNo+".txt");

		FileUtils.write(file, weight, "utf-8");
		
		return 0;
	}
	
	@RequestMapping("exportPrint")
	public void exportStockOutOrder(HttpServletResponse response,OrderPackageParam param,@RequestParam(value="ids",required=false)List<String> ids) throws Exception{
		List<OrderPackagePrintExport> rows = new ArrayList<OrderPackagePrintExport>();
		Integer count = orderPackageService.findCount(param, ids);
		if (count > App.getConfigInt("export.excel.maxnumber")) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("error");
			response.getWriter().close();
			return;
		}
		List<OrderPackage> packages = orderPackageService.find(param,ids);
		for(OrderPackage ope:packages){
			ope.setOrderPackageItem(orderPackageItemDao.findByPackage(ope.getId()));
			rows.addAll(fillExportData(ope));
		}
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("包裹编号/ID","id"));
		cs.add(new Column("打印时间","printTime","yyyy-MM-dd HH:mm:ss"));
		cs.add(new Column("订单ID","orderId"));
		cs.add(new Column("自营卖家","selfSeller"));
		cs.add(new Column("运输方式","shippingName"));
		cs.add(new Column("跟踪号","trackNumber"));
		cs.add(new Column("标发","isSend"));
		cs.add(new Column("打印","printedFlag"));
		cs.add(new Column("状态","statusName"));
		cs.add(new Column("重量","packageWeight"));
		cs.add(new Column("运费","shippingFee"));
		cs.add(new Column("SKU","sku"));
		cs.add(new Column("名称","goodsName"));
		cs.add(new Column("单价","price"));
		cs.add(new Column("总价","totalPrice"));
		cs.add(new Column("仓库","storeName"));
		cs.add(new Column("订单数量","orderAmount"));
		cs.add(new Column("包裹数量","packageAmount"));
		
		String filename = "打印工作台.xlsx";
		ServletOutputStream os = null;
		try{
			ExcelBuilder<OrderPackagePrintExport> eb = new ExcelBuilder<OrderPackagePrintExport>();
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
			logger.error(e.getMessage());;
		}finally{
			rows = null;
			packages = null;
			if (null!=os){
				os.flush();
				os.close();
			}
		}
	}
	
	
	private List<OrderPackagePrintExport> fillExportData(OrderPackage pack){
		OrderPackagePrintExport export = null;
		List<OrderPackagePrintExport> list = new ArrayList<OrderPackagePrintExport>();
		for(OrderPackageItem item:pack.getOrderPackageItem()){
			export = new OrderPackagePrintExport();
			export.setId(pack.getId());
			export.setPrintTime(pack.getPrintedTime());
			export.setOrderId(String.valueOf(pack.getOrderId()));
			export.setSelfSeller(null!=pack.getSelfSeller() && pack.getSelfSeller()==0?"否":"是");
			export.setShippingName(pack.getShippingName());
			export.setShippingFee(String.valueOf(pack.getShippingFee()));
			export.setTrackNumber(pack.getTrackNumber());
			export.setIsSend(null!=pack.getIsSend() &&  pack.getIsSend()==1?"是":"否");
			export.setPrintedFlag(null!=pack.getPrintedFlag() && pack.getPrintedFlag()==1?"是":"否");
			export.setStatusName(pack.getStatusName());
			export.setPackageWeight(String.valueOf(pack.getPackageWeight()));
			export.setSku(item.getSku());
			export.setGoodsName(item.getGoodsName());
			export.setPrice(String.valueOf(item.getPrice()));
			export.setTotalPrice(String.valueOf(item.getTotalPrice()));
			export.setStoreName(item.getStoreName());
			export.setOrderAmount(item.getOrderAmount());
			export.setPackageAmount(item.getPackageAmount());
			list.add(export);
		}
		return list;
	}
	
	/***
	 * 称重差距大取消后动作
	 * @param trackNumber
	 */
	@RequestMapping("cancelWeigh")
	public void cancelWeigh(String trackNumber){
		if(StringUtils.isNotBlank(trackNumber)){
			this.orderPackageService.cancelweighService(trackNumber);
		}
	}
	
}

package com.xuanfeiyang.erp.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.buzheng.excel.Column;
import org.buzheng.excel.ExcelBuilder;
import org.buzheng.excel.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Dict;
import com.xuanfeiyang.erp.domain.IoOrder;
import com.xuanfeiyang.erp.domain.IoOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrderStatistic;
import com.xuanfeiyang.erp.domain.Store;
import com.xuanfeiyang.erp.domain.StoreShelf;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.IoOrderParam;
import com.xuanfeiyang.erp.service.DictService;
import com.xuanfeiyang.erp.service.FileImportException;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
import com.xuanfeiyang.erp.service.GoodsInventoryService;
import com.xuanfeiyang.erp.service.InventoryShortageException;
import com.xuanfeiyang.erp.service.IoOrderService;
import com.xuanfeiyang.erp.service.PurchaseOrderService;
import com.xuanfeiyang.erp.service.StoreService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.service.XxNoGenerateService;
import com.xuanfeiyang.erp.service.XxNoType;
import com.xuanfeiyang.erp.web.util.DataTableOrder;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/io-order")
@SessionAttributes(App.SESSION_USER_KEY)
public class IoOrderController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	@Resource
	private IoOrderService ioOrderService;
	
	@Resource
	private StoreService storeService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private DictService dictService;
	
	@Resource
	private XxNoGenerateService xxNoGenerateService;
	
	@Resource
	private GoodsCategoryService goodsCategoryService;
	
	@Resource
	private PurchaseOrderService purchaseOrderService;
	
	@Resource
	private GoodsInventoryService goodsInventoryService;

	/**
	 * 出入库单list页面
	 * @param model
	 * @param type
	 * @return
	 */
	@RequestMapping({"", "/"})
	public String index(Model model,
			@RequestParam(value="type", required=true) Integer type){
		List<Store> list =  storeService.findStroe();
		model.addAttribute("store", list);
		
		List<UserInfo> usreList = userService.findAll();
		model.addAttribute("user", usreList);
		
		List<Dict> typeList = dictService.findByType(201);
		model.addAttribute("typeAttr", typeList);
		model.addAttribute("baseCategories", this.goodsCategoryService.findByParentId(0));
		
		return "goods/io-order";
	}
	

	/**
	 * 出入库单头列表
	 * @param dtr
	 * @return
	 */
	@RequestMapping("page-json")
	@ResponseBody
	public DataTableResponse<IoOrder> orderList(@RequestBody DataTableRequest<IoOrderParam> dtr){
		dtr.getParams().setOrderStr(orderByStr(dtr.getOrder()));
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<IoOrder> page = this.ioOrderService.findPage(pageRequest, dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	
	private String orderByStr(List<DataTableOrder> columnList){
		if (null==columnList || columnList.size()==0){
			return null;
		}
		Map<Integer,String> columnIdxName = new HashMap<Integer,String>();
		columnIdxName.put(1, "a.serial_number");				//流水号
		columnIdxName.put(0, "a.last_updated_time");				//更新时间
		StringBuffer sbf = new StringBuffer();
		for(DataTableOrder column:columnList){
			if (!columnIdxName.containsKey(column.getColumn())){
				continue;
			}
			sbf.append(columnIdxName.get(column.getColumn())+" "+column.getDir()+",");
		}
		return sbf.length()>1?sbf.substring(0, sbf.length()-1):null;
	}
	
	
	/**
	 * 添加出入库单
	 * @param attr
	 * @param sessionUser
	 * @param goodsSku
	 * @param goodsName
	 * @param goodsCount
	 * @param goodsCost
	 * @param storeId
	 * @param storeShelfId
	 * @param order
	 * @return
	 */
	@RequestMapping("save")
	public String save(RedirectAttributes attr,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser,
			@RequestParam("goodsSku") List<String> goodsSku,
			@RequestParam("goodsName") List<String> goodsName,
			@RequestParam("goodsCount") List<Integer> goodsCount,
			@RequestParam("goodsCost") List<BigDecimal> goodsCost,
			@RequestParam("storeId") List<Integer> storeId,
			@RequestParam("storeShelfId") List<Integer> storeShelfId,
			IoOrder order){
		
		List<IoOrderItem> items = new ArrayList<>(3);
		order.setItems(items);
		
		for (int i = 0; i < goodsSku.size(); i++) {
			IoOrderItem item = new IoOrderItem();
			item.setGoodsSku(goodsSku.get(i));
			item.setGoodsName(goodsName.get(i));
			item.setStoreId(storeId.get(i));
			item.setStoreShelfId(storeShelfId.get(i));
			item.setQualifiedCount(goodsCount.get(i));
			item.setGoodsCost(goodsCost.get(i));
			
			items.add(item);
		}
		
		order.setAuditStatus(0);
		// 制单人
		order.setCreatedUserId(sessionUser.getUserId());
		
		if (order.getId() == null) {
			this.ioOrderService.save(order);
		} else {
			this.ioOrderService.update(order);
		}

		attr.addFlashAttribute("successMessage", "g.tips.success");
		attr.addAttribute("type", order.getType());
		return "redirect:/io-order";
	}
	
	/**
	 * 编辑页面
	 * @param model
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping("edit")
	public String editPage(Model model, 
			@RequestParam(value="id", required=false) Integer id,
			@RequestParam(value="type", required=false) Integer type){
		
		IoOrder order = null;
		if (id != null) {
			order = this.ioOrderService.load(id);
		
		} else {
			order = new IoOrder();
			order.setType(type);
			order.setIoDate(new Date()); // 默认为当天
			order.setOrderNo(this.xxNoGenerateService.generate(type.equals(0) ? XxNoType.QR : XxNoType.QC));
		}
		
		model.addAttribute("order", order);
		
		List<Dict> typeList = dictService.findByType(201);
		model.addAttribute("typeAttr", typeList);
		
		List<Store> listStore =  storeService.findStroe();
		model.addAttribute("store", listStore);
		
		return "goods/io-order-edit";
	}
	
	/**
	 * 审核
	 * @param attr
	 * @param sessionUser
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping("approve")
	public String approve(RedirectAttributes attr,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser, 
			@RequestParam(value="id") Integer id, 
			@RequestParam(value="type") Integer type) {
		
		try {
			this.ioOrderService.approve(id, sessionUser.getUserId());
			attr.addFlashAttribute("successMessage", "g.tips.success");
		} catch (InventoryShortageException e) {
			attr.addFlashAttribute("errorMessage", e.getGoodsInventory().getGoodsSku() + "库存不足");
		}
		
		attr.addAttribute("type", type);
		return "redirect:/io-order";
	}
	
	/**
	 * 删除出入库单
	 * @param attr
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(RedirectAttributes attr,
			@RequestParam(value="id") Integer id, 
			@RequestParam(value="type") Integer type) {
		
		this.ioOrderService.delete(id);

		attr.addFlashAttribute("successMessage", "g.tips.success");
		attr.addAttribute("type", type);
		return "redirect:/io-order";
	}
	
	/**
	 * 商品收发明细表
	 * @param dtr
	 * @return
	 */
	@RequestMapping("json-page")
	@ResponseBody
	public DataTableResponse<IoOrderItem> goodsIoItem(
			@RequestBody DataTableRequest<IoOrderItem> dtr){
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<IoOrderItem> page = this.ioOrderService.goodsIoItem(pageRequest, dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	/**
	 * 产品收发明细页面
	 * @param model
	 * @return
	 */
	@RequestMapping("io-listing")
	public String ioItem(Model model){
		List<Store> list =  storeService.findStroe();
		model.addAttribute("store", list);
		List<StoreShelf> listShelf = storeService.findStoreShelf(null);
		model.addAttribute("shelf", listShelf);
		return "goods/io-listing";
	}
	
	/**
	 * 产品收发明细页面
	 * @param model
	 * @return
	 */
	@RequestMapping("io-journal")
	public String ioJournal(Model model){
		List<Store> list =  storeService.findStroe();
		model.addAttribute("store", list);
		List<StoreShelf> listShelf = storeService.findStoreShelf(null);
		model.addAttribute("shelf", listShelf);
		return "goods/io-journal";
	}	
	
	@RequestMapping("inOutTotal.json")
	@ResponseBody
	public PurchaseOrderStatistic IoOrderTotal(IoOrderItem param) {
		return ioOrderService.statisticIoItem(param);
	}
	
	/**
	 * 建立出入库单时查询商品价格
	 * 如果为入库单，获取商品的最新采购价
	 * 如果为出库单，获取商品的历史加权平均价
	 * 
	 * @param sku sku
	 * @param type 出入库类型
	 */
	@RequestMapping("get-goods-cost")
	@ResponseBody
	public BigDecimal getGoodsCost(@RequestParam("sku") String sku, 
			@RequestParam("type") Integer type) {
		
		BigDecimal latestCost = null;
		if (type == 0) {
			latestCost = this.purchaseOrderService.getLatestGoodsCostBySku(sku);
		} else if (type == 1) {
			latestCost = this.goodsInventoryService.getLatestGoodsCostBySku(sku);
		}
		
		return latestCost;
	}
	
	/***
	 * 根据sku查询 库存 和 锁定 数量
	 * @param sku
	 * @return
	 */
	@RequestMapping("goods-inventory-json")
	@ResponseBody
	public Map<String, Object> getGoodsInventory(String sku) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer InventoryLockGoods = this.goodsInventoryService.getLockCountBySku(sku);
		if (InventoryLockGoods == null) {
			InventoryLockGoods = 0;
		}
		

		Integer  InventoryGoods = this.goodsInventoryService.getCountBySku(sku);
		if (InventoryGoods == null) {
			InventoryGoods = 0;
		}		
	
		map.put("Inventory", InventoryGoods);
		map.put("InventoryLock", InventoryLockGoods);
		return map;
	}
	
	

	/***
	 * 
	 * 批量导入 出入库单
	 * 页面按钮 隐藏
	 * @param model
	 * @param redirectAttr
	 * @param file
	 * @param ioOrder
	 * @return
	 */
	@RequestMapping("importIoOrder")
	public String importIoOrder(Model model,@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser, 
			RedirectAttributes redirectAttr,@RequestParam("fileName")MultipartFile file,IoOrder ioOrder){
			
		
		try {
			ioOrder.setCreatedUserId(sessionUser.getUserId());
			this.ioOrderService.importIoOrder(file, ioOrder);
			model.addAttribute("successMessage", "导入成功");
			
		} catch (FileImportException e) {
			logger.error("导入出错", e);
			model.addAttribute("errorMessage", String.format("%s行 %s列, %s", e.getLineNumber(), e.getColumnNumber(), e.getMessage()));
		} catch (Exception e) {
			logger.error("导入出错", e);
			model.addAttribute("errorMessage", e.getMessage());
		}
		model.addAttribute("orderType", ioOrder.getType());
		List<Dict> typeList = dictService.findByType(201);
		model.addAttribute("typeAttr", typeList);
		model.addAttribute("orderType", ioOrder.getType());
		//return "redirect:/io-order";
		return "goods/io-order-import";
	}

	/**
	 * 
	 * 打印出/入库单
	 * @param orderNo
	 * @return
	 */
	@RequestMapping("ioOrderPrint")
	public String ioOrderPrint(String orderNo,Model model){
		IoOrder order = this.ioOrderService.loadByOrderNo(orderNo);
		List<IoOrderItem> orderItemList = this.ioOrderService.detail(orderNo);
		model.addAttribute("order", order);
		model.addAttribute("orderItem", orderItemList);
		return "goods/io-order-print";
	}
	
	/**
	 * 导入出入库单的页面
	 * @return
	 */
	@RequestMapping("import-page")
	public String ioOrderImport(@RequestParam("type") Integer type,Model model){
		List<Dict> typeList = dictService.findByType(201);
		model.addAttribute("typeAttr", typeList);
		model.addAttribute("orderType", type);
		return "goods/io-order-import";
	}
	
	/***
	 * 出入库明细
	 * @param dtr
	 * @return
	 */
	@RequestMapping("ioItem-json")
	@ResponseBody
	public DataTableResponse<IoOrderItem> orderItem(@RequestBody DataTableRequest<IoOrderItem> dtr){
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<IoOrderItem> page = this.ioOrderService.findIoOrderItem(pageRequest, dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	@RequestMapping("/io-order-log")
	public String ioItemPage(Model model){
		model.addAttribute("goodsStatus", this.dictService.findByType(102));
		List<UserInfo> userList1 = this.userService.findByDepartment(142679);//采购
		List<UserInfo> userList3 = this.userService.findByDepartment(142678);//开发员
		List<Store> storeList = this.storeService.findStroe();
		List<Dict> typeList = dictService.findByType(201);
		model.addAttribute("typeAttr", typeList);
		
		model.addAttribute("buyUser", userList1);//采购
		model.addAttribute("devUser", userList3);//开发员
		model.addAttribute("storeList", storeList);//仓库
		
		return "goods/io-order-log";
	}
	
	/***
	 * 导出出入库明细
	 * @param response
	 * @param ioOrderItem
	 * @throws Exception
	 */
	@RequestMapping("/exportIoOrderItem")
	public void exportIoOrderItem(HttpServletResponse response,IoOrderItem ioOrderItem)throws Exception{
		Integer count = this.ioOrderService.findIoOrderItemCount(ioOrderItem);
		if(count > App.getConfigInt("export.excel.maxnumber")){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("error");
			response.getWriter().close();
		}else{
			List<IoOrderItem> rows = this.ioOrderService.ioOrderItemList(ioOrderItem);
			List<Column> cs = new ArrayList<Column>();
			cs.add(new Column("SKU","goodsSku"));
			cs.add(new Column("产品名","goodsName"));
			cs.add(new Column("开发人","developUser"));
			cs.add(new Column("开发时间","developTime"));
			cs.add(new Column("采购人","buyUser"));
			cs.add(new Column("日期","endDate"));
			cs.add(new Column("单据类型","typeName"));
			cs.add(new Column("单据名称","typeDetailName"));
			cs.add(new Column("单据号","orderNo"));
			cs.add(new Column("仓库","storeName"));
			cs.add(new Column("仓位","shelfName"));
			cs.add(new Column("收发数量","qualifiedCount"));
			cs.add(new Column("收发成本","goodsCost"));
			String filenName = "商品收发明细.xlsx";
			ExcelBuilder<IoOrderItem> eb = new ExcelBuilder<IoOrderItem>();
			eb.setFileType(FileType.XLSX);
			eb.setData(rows);
			eb.setColumns(cs);
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
			+new String(filenName.getBytes("GBK"),"ISO-8859-1"));
			ServletOutputStream os = response.getOutputStream();
			eb.toOutputStream(os);
			os.flush();
			os.close();
		
		}

	}
	
	
	
}

package com.xuanfeiyang.erp.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.PathVariable;
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
import com.xuanfeiyang.erp.dao.PurchaseOrderLogDao;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsCategory;
import com.xuanfeiyang.erp.domain.GoodsSupplier;
import com.xuanfeiyang.erp.domain.PurchaseOrder;
import com.xuanfeiyang.erp.domain.PurchaseOrderExport;
import com.xuanfeiyang.erp.domain.PurchaseOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrderLog;
import com.xuanfeiyang.erp.domain.PurchaseOrderStatistic;
import com.xuanfeiyang.erp.domain.PurchaseOrderWayBillNo;
import com.xuanfeiyang.erp.param.PurchaseOrderParam;
import com.xuanfeiyang.erp.param.PurchaseOrderReportParam;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
import com.xuanfeiyang.erp.service.GoodsService;
import com.xuanfeiyang.erp.service.GoodsSupplierService;
import com.xuanfeiyang.erp.service.IoOrderService;
import com.xuanfeiyang.erp.service.PurchaseOrderService;
import com.xuanfeiyang.erp.service.TableKeyService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.service.XxNoGenerateService;
import com.xuanfeiyang.erp.service.XxNoType;
import com.xuanfeiyang.erp.util.ExcelParser;
import com.xuanfeiyang.erp.web.util.DataTableOrder;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/purchaseorder")
@SessionAttributes(App.SESSION_USER_KEY)
public class PurchaseOrderController extends BaseController{
	@Resource
	private PurchaseOrderService purchaseOrderService;
	@Resource
	private GoodsCategoryService goodsCategoryService; 
	@Resource
	private GoodsService goodsService;
	@Resource
	private MessageSource messageSource;
	@Resource
	private XxNoGenerateService xxNoGenerateService;
	@Resource
	private  TableKeyService tableKeyService;
	@Resource
	private GoodsSupplierService  goodsSupplierService;
	@Resource
	private IoOrderService ioOrderService;
	@Resource
	private UserService userService;
	@Resource
	private PurchaseOrderLogDao purchaseOrderLogDao;
	
	private DateFormat dft = new SimpleDateFormat("yyyyMMdd");
	
	private static final Logger logger = LoggerFactory
			.getLogger(PurchaseOrderController.class);

	@RequestMapping("list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView();
		List<GoodsCategory> firstCategory = this.goodsCategoryService.findByParentId(0);
		PurchaseOrderParam param = new PurchaseOrderParam();
		mv.addObject("categoryList", firstCategory);
		mv.addObject("search",param);
		mv.addObject("users", this.userService.findAll());
		mv.setViewName("goods/purchase-order-list");
		return mv;
	}
	
	@RequestMapping("to-stockin")
	public ModelAndView toStockInList(){
		ModelAndView mv = new ModelAndView();
		List<GoodsCategory> firstCategory = this.goodsCategoryService.findByParentId(0);
		PurchaseOrderParam param = new PurchaseOrderParam();
		mv.addObject("categoryList", firstCategory);
		List<GoodsSupplier> allstore = goodsSupplierService.find();
		Iterator<GoodsSupplier> itr = allstore.iterator();
		while (itr.hasNext()){
			if(itr.next().getStatus()!=1){
				itr.remove();
			}
		}
		mv.addObject("supplyList", allstore);
		mv.addObject("search",param);
		mv.setViewName("goods/purchase2stockin");
		return mv;
	}
	
	
	@RequestMapping("pageJson")
	@ResponseBody
	public DataTableResponse<PurchaseOrder> purchaseOrderPageJson(@RequestBody DataTableRequest<PurchaseOrderParam> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		dtr.getParams().setOrderStr(orderByStr(dtr.getOrder()));
		Page<PurchaseOrder> page = this.purchaseOrderService.findPage(pageRequest,dtr.getParams());
		for(PurchaseOrder po :page.getContent()){
			StringBuffer wayBillNoStr = new StringBuffer(StringUtils.isEmpty(po.getWaybillNo())?"":po.getWaybillNo().concat(" "));
			 StringBuffer logisticsCompanyStr = new StringBuffer(StringUtils.isEmpty(po.getLogisticsCompany())?"":po.getLogisticsCompany().concat(" "));
			 List<PurchaseOrderWayBillNo> wayBillNoList = this.purchaseOrderService.findWayBillNos(po.getOrderNo());
			 if (null!=wayBillNoList && wayBillNoList.size()>0){
				 for(PurchaseOrderWayBillNo wayBillNo:wayBillNoList){
					 wayBillNoStr.append(StringUtils.isEmpty(wayBillNo.getWaybillNo())?"":wayBillNo.getWaybillNo().concat(" "));
					 logisticsCompanyStr.append(StringUtils.isEmpty(wayBillNo.getLogisticsCompany())?"":wayBillNo.getLogisticsCompany().concat(" "));
				 }
			 }
			 po.setWaybillNos(wayBillNoStr.toString());
			 po.setLogisticsCompanys(logisticsCompanyStr.toString());
		}
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	private String orderByStr(List<DataTableOrder> columnList){
		if (null==columnList || columnList.size()==0){
			return "a.CREATED_TIME desc";
		}
		Map<Integer,String> columnIdxName = new HashMap<Integer,String>();
		columnIdxName.put(3, "a.serial_number");				//编号
		columnIdxName.put(0, "a.CREATED_TIME");
		StringBuffer sbf = new StringBuffer();
		for(DataTableOrder column:columnList){
			if (column.getColumn()==0){
				sbf.append("a.CREATED_TIME desc,");
				continue;
			}
			if (!columnIdxName.containsKey(column.getColumn())){
				continue;
			}
			sbf.append(columnIdxName.get(column.getColumn())+" "+column.getDir()+",");
		}
		return sbf.length()>1?sbf.substring(0, sbf.length()-1):null;
	}
	
	@RequestMapping("form")
	public ModelAndView form(@RequestParam(value="id",required=false) Integer id,ModelAndView mv,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		PurchaseOrder pror = null;
		if (null!=id){
			mv.addObject("updateFlag", "1");
			pror = purchaseOrderService.get(id);
			pror.setFreight((null==pror.getFreight())?0.0f:pror.getFreight());
			 List<PurchaseOrderItem> items = purchaseOrderService.findItem(id);
			 String categoryName = null;
			 for(PurchaseOrderItem item:items){
				 Goods goods = goodsService.findBySku(item.getGoodsSku());
				 if (null!=goods && null!=goods.getWeight()){
					 item.setGoodsWeight(goods.getWeight());
				 }
				 if (item.getGoodsCategory()!=null && null==categoryName){
					 GoodsCategory category = goodsCategoryService.get(item.getGoodsCategory());
					categoryName = category!=null?category.getName():"";
				 }
				 item.setGoodsCategoryName(categoryName);
				 item.setPurchaseRequestId(pror.getPurchaseRequestId());
				 item.setNewCost(purchaseOrderService.getLatestGoodsCostBySku(item.getGoodsSku()));
			 }
			mv.addObject("itemList", items);
		}else{
			pror = new PurchaseOrder();
			pror.setCreatedTime(new Date());
			pror.setPurchaseRequestCreater(curUser.getName());
			pror.setOrderNo(xxNoGenerateService.generate(XxNoType.PO));
			pror.setIsSample((short)0);
			pror.setFreight(0f);
		}
		mv.addObject("purchaseorder", pror);
		mv.setViewName("goods/purchase-order-form");
		return mv;
	}
	
	@RequestMapping("add")
	public ModelAndView add(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,PurchaseOrder order, 
			@RequestParam("itemSku") List<String> itemSku,
			@RequestParam("itemName") List<String> itemName,
			@RequestParam("itemUnit") List<String> itemUnit,
			@RequestParam("itemCost") List<BigDecimal> itemCost,
			@RequestParam("itemCount") List<Integer> itemCount,
			@RequestParam("itemWeight") List<BigDecimal> itemWeight,
			@RequestParam("itemCategory") List<String> itemCategory,
			RedirectAttributes attr) throws Exception{
		List<PurchaseOrderItem> items = new ArrayList<PurchaseOrderItem>();
		if (itemSku!=null && itemSku.size()>0){
			for(int i=0;i<itemSku.size();i++){
				PurchaseOrderItem item = new PurchaseOrderItem(itemSku.get(i).trim(),itemName.get(i),itemCost.get(i),
						itemCount.get(i),itemUnit.get(i),null,
						itemWeight==null?null:itemWeight.get(i),order.getDeliveryDate(),itemCategory==null?null:itemCategory.get(i)); 
				items.add(item);
			}
		}
		order.setCreatedUserId(curUser.getUserId());
		order.setCreatedUserName(curUser.getName());
		order.setBuyUserId(curUser.getUserId());
		purchaseOrderService.add(order,items);
		logger.debug("add new PurchaseOrder: {}",order.getId());
		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		return new ModelAndView("redirect:/purchaseorder/list");
	}
	
	
	@RequestMapping("edit")
	public ModelAndView edit(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,PurchaseOrder order, 
			@RequestParam("itemSku") List<String> itemSku,
			@RequestParam("itemName") List<String> itemName,
			@RequestParam("itemUnit") List<String> itemUnit,
			@RequestParam("itemCost") List<BigDecimal> itemCost,
			@RequestParam("itemCount") List<Integer> itemCount,
			@RequestParam("itemWeight") List<BigDecimal> itemWeight,
			@RequestParam("itemCategory") List<String> itemCategory,
			RedirectAttributes attr) 
			throws Exception{
		List<PurchaseOrderItem> items = new ArrayList<PurchaseOrderItem>();
		if (itemSku!=null && itemSku.size()>0){
			for(int i=0;i<itemSku.size();i++){
				PurchaseOrderItem item = new PurchaseOrderItem(itemSku.get(i),itemName.get(i),itemCost.get(i),
						itemCount.get(i),itemUnit.get(i),null,
						itemWeight.get(i),order.getDeliveryDate(),itemCategory.get(i)); 
						item.setReceivedCount(0);
						item.setQualifiedCount(0);
						item.setUnQualifiedCount(0);
				items.add(item);
			}
		}
		order.setCreatedUserId(curUser.getUserId());
		purchaseOrderService.edit(order, items);
		logger.debug("update new PurchaseOrder: {}",order.getId());
		//attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		attr.addAttribute("id",order.getId());
		return new ModelAndView("redirect:/purchaseorder/form");
	}
	
	@RequestMapping("remove/{id}")
	public ModelAndView remove(@PathVariable("id")  Integer id,RedirectAttributes attr,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		PurchaseOrder po = purchaseOrderService.get(id);
		if (!curUser.getUserId().equals(po.getCreatedUserId())){
			attr.addFlashAttribute("errorMessage", this.messageSource.getMessage("purchase.delete.error", null, null));
			return new ModelAndView("redirect:/purchaseorder/list");
		}
		purchaseOrderService.remove(id);
		logger.debug("delete  PurchaseRequestOrder: {}",id);
		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		return new ModelAndView("redirect:/purchaseorder/list");
	}
	
	
	@ResponseBody
	@RequestMapping("batchremove.json")
	public Map<String,Object> batchRemove(@RequestParam("ids")  Integer []ids,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> map = new HashMap<>();
	    List<String> invalidOrderNo = new ArrayList<>();
		try{
			for(int id:ids){
				PurchaseOrder po = purchaseOrderService.get(id);
				if (!curUser.getUserId().equals(po.getCreatedUserId()) && po.getStatus().intValue()==1){
					invalidOrderNo.add(po.getOrderNo());
				}
				purchaseOrderService.remove(id);
				logger.debug("delete  PurchaseRequestOrder: {}",id);
			}
			map.put("result", true);
			if (invalidOrderNo.size()>0){
				map.put("msg", String.format("采购单[%s]不能被删除,非本人创建或非未审核状态",invalidOrderNo));
			}
		}catch(Exception e){
			map.put("result", false);
			map.put("msg",e.getMessage());
		}
		return map;
	}
	
	
	
	@RequestMapping("detail/{id}")
	public ModelAndView detail(@PathVariable("id")Integer id,Model model){
		List<PurchaseOrder> detailList = purchaseOrderService.detail(id);
		List<PurchaseOrderLog> logList = new ArrayList<>();
		if (detailList!=null && detailList.size()>0){
			String orderNo = detailList.get(0).getOrderNo();
			logList = purchaseOrderLogDao.findByOrderNo(orderNo);
		}
		model.addAttribute("detailList", detailList);
		model.addAttribute("logList", logList);
		return new ModelAndView("goods/purchase-order-detail");
	}
	
	@RequestMapping("report-page")
	public ModelAndView reportPage(ModelAndView model){
		model.addObject("search", new PurchaseOrderReportParam());
		/*
		List<GoodsSupplier> allstore = goodsSupplierService.find();
		Iterator<GoodsSupplier> itr = allstore.iterator();
		while (itr.hasNext()){
			if(itr.next().getStatus()!=1){
				itr.remove();
			}
		}
		model.addObject("supplyList", allstore);
		*/
		model.addObject("categoryList", goodsCategoryService.findByParentId(0));
		model.setViewName("goods/purchase-order-report");
		return model;
	}
	
	/**
	 * 采购运单号sku价格导入
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="importSkuPrice")
	public String SkuPriceImport(MultipartHttpServletRequest request,RedirectAttributes attr,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		InputStream is = null;
		MultipartFile file = null;
		String fileName = null;
		try{
			file = request.getFile("importSkuPriceFile");
			fileName = file.getOriginalFilename();
			int idx = fileName.lastIndexOf(".");
			String extensionName = fileName.substring(idx + 1);
			if (null==file || idx==-1 || file.isEmpty() || !extensionName.startsWith("xls")){
				attr.addFlashAttribute("errorMessage","导入文件格式不对或导入文件为空");
			}else{
				is = file.getInputStream();
				List<String[]> rowsList = ExcelParser.parseToString(is, extensionName);
				for(int i=1;i<rowsList.size();i++){
					String [] row = rowsList.get(i);
					if (null==row || StringUtils.isEmpty(row[0])){
						continue;
					}
					//只针对自己所创未审核的订单有效
					PurchaseOrder po = this.purchaseOrderService.findByOrderNo(row[0]);
					if (po==null || po.getStatus().intValue()!=1 || !curUser.getUserId().equals(po.getCreatedUserId())){
						continue;
					}
					po.setPayMethod(row[1].trim());
					po.setFreight(StringUtils.isEmpty(row[2])?null:Float.valueOf(row[2].trim()));
					List<PurchaseOrderItem> items = this.purchaseOrderService.findItem(po.getId());
					for(PurchaseOrderItem item:items){
						if (item.getGoodsSku().equals(row[3].trim())){
							item.setGoodsCount(Integer.valueOf(row[4].trim()));
							item.setGoodsCost(BigDecimal.valueOf(Double.valueOf(row[5].trim())));
						}
					}
					this.purchaseOrderService.edit(po, items);
				}
				attr.addFlashAttribute("successMessage",this.messageSource.getMessage("g.tips.success", null, null));
			}
		}catch(Exception e){
			e.printStackTrace();
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
		return "redirect:/purchaseorder/report-page";
	}
	
	/*
	@RequestMapping("report")
	@ResponseBody
	public Map<String,Object> report(@RequestBody DataTableRequest<PurchaseOrderReportParam> dtr){
		Map<String,Object> retMap = new HashMap<String,Object>();
		try{
			List<PurchaseOrder> dataList = purchaseOrderService.report(dtr.getParams());
			
			for(PurchaseOrder porder:dataList){
				List<IoOrder> ioorderList = ioOrderService.getOrderByPurchaseOrderNo(porder.getOrderNo());
				//设置入库日期
				if (ioorderList!=null && ioorderList.size()>0){
					porder.setIoDate(ioorderList.get(0).getIoDate());
				}
			}
			retMap.put("result", true);
			retMap.put("datas", dataList);
		}catch(Exception e){
			logger.error(e.getMessage());;
			retMap.put("result", false);
		}
		return retMap;
	}
	*/
	
	@RequestMapping("report")
	@ResponseBody
	public DataTableResponse<PurchaseOrder> orderPageJson(@RequestBody DataTableRequest<PurchaseOrderReportParam> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<PurchaseOrder> page = this.purchaseOrderService.report(pageRequest,dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	
	@RequestMapping(value="overDue",method=RequestMethod.POST)
	@ResponseBody
	public Boolean overDue(@RequestParam("poNo")String poNo){
		Boolean purchaseOrderDue = false;
		PurchaseOrder po = this.purchaseOrderService.findByOrderNo(poNo);
		//过了交期未入库的采购单或异常入库的采购单
		if (po.getDeliveryDate().compareTo(Calendar.getInstance().getTime())<0 && po.getStatus().intValue()!=3 && po.getStatus().intValue()!=4){
			purchaseOrderDue = true;
		}
		return purchaseOrderDue;
	}
	
	@RequestMapping(value="fillHandle",method=RequestMethod.POST)
	public ModelAndView  fillHandle(String purchaseOrderNo,String handleDesc,ModelAndView model){
		PurchaseOrder po = new PurchaseOrder();
		po.setOrderNo(purchaseOrderNo);
		po.setNote(handleDesc);
		this.purchaseOrderService.edit(po, null);
		model.setViewName("redirect:/purchaseorder/list");
		return model;
	}
	
	
	@RequestMapping("export")
	public void exportReport(HttpServletResponse response,PurchaseOrderReportParam param) throws Exception{
		List<PurchaseOrder> rows = this.purchaseOrderService.findReport(param);
		
		List<Column> cs = new ArrayList<Column>();
		
		cs.add(new Column("采购单号","orderNo"));
		cs.add(new Column("采购日期","purchaseDate","yyyy-MM-dd"));
	//	cs.add(new Column("付款时间","paidTime","yyyy-MM-dd HH:mm:ss"));
		cs.add(new Column("采购员","buyUserName"));
		cs.add(new Column("请购人","purchaseRequestCreater"));
		cs.add(new Column("SKU","goodsSku"));
		cs.add(new Column("产品类型","goodsCategory"));
		cs.add(new Column("名称 ","goodsName"));
		cs.add(new Column("单位 ","goodsUnit"));
		cs.add(new Column("数量","goodsCount"));
		cs.add(new Column("采购单价","goodsCost"));
		cs.add(new Column("金额 ","goodsCount",new FieldFormatter<Integer,PurchaseOrder>(){
			public BigDecimal format(Integer goodsCount, PurchaseOrder rowValue){
				return BigDecimal.valueOf(goodsCount).multiply(BigDecimal.valueOf(rowValue.getGoodsCost()));
			 }
			}));
		cs.add(new Column("已交数量","receivedCount"));
		cs.add(new Column("未交数量","receivedCount",new FieldFormatter<Integer,PurchaseOrder>(){
			public Integer format(Integer receivedCount, PurchaseOrder rowValue){
				if (null!=rowValue.getGoodsCount() && null!=rowValue.getReceivedCount()){
					return rowValue.getGoodsCount()- rowValue.getReceivedCount();
				}
				return rowValue.getGoodsCount(); 
			 }
			}));
		cs.add(new Column("合格数量","qualifiedCount"));
		cs.add(new Column("不合格数量","unQualifiedCount"));
		cs.add(new Column("供应商","supplierName"));
		cs.add(new Column("要求交货日期","deliveryDate","yyyy-MM-dd"));
	
		cs.add(new Column("状态 ","status",new FieldFormatter<Short,PurchaseOrder>(){
			public String format(Short status, PurchaseOrder rowValue){
				if (status==-1){
					return "异常入库";
				}
				else if (status==1){
					return "待审核";
				}
				else if (status==2){
					return "已审核";
				}
				else if (status==3){
					return "正常关闭";
				}
				return "";
			 }
			}
		));
		cs.add(new Column("源单类型 ","type",new FieldFormatter<Short,PurchaseOrder>(){
			public String format(Short type, PurchaseOrder rowValue){
				if (type==1){
					return "请购转入";
				}
				else if (type==2){
					return "手工新增";
				}
				return "";
			 }
			}));
		cs.add(new Column("运费 ","freight"));
		cs.add(new Column("支付方式 ","payMethod"));
		cs.add(new Column("制单人","createdUserName"));
		cs.add(new Column("制单日期 ","createdTime","yyyy-MM-dd"));
		cs.add(new Column("关闭","status",new FieldFormatter<Short,PurchaseOrder>(){
			public String format(Short status, PurchaseOrder rowValue){
				if (status==3){
					return "是";
				}
				else {
					return "否";
				}
			 }
			}));
		String filename = "采购报表"+".xls";
		ServletOutputStream os = null;
		try{
			ExcelBuilder<PurchaseOrder> eb = new ExcelBuilder<PurchaseOrder>();
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
	}
	
	@ResponseBody
	@RequestMapping("audit")
	public Map<String,Object> orderAudit(@RequestParam("id") Integer id,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("result", true);
		try{
			PurchaseOrder pror = purchaseOrderService.get(id);
			//采购日期、要求交货日期、运费、供应商、支付方式 为空,则审核不通过
			if (pror.getPurchaseDate()==null || pror.getDeliveryDate()==null  || pror.getFreight()==null 
					|| pror.getSupplierId()==null || StringUtils.isEmpty(pror.getPayMethod()) /*|| StringUtils.isEmpty(pror.getWaybillNo()) || StringUtils.isEmpty(pror.getLogisticsCompany())*/){
				retMap.put("result", false);
				//retMap.put("errorMsg","采购日期、要求交货日期、运费、供应商、支付方式 、运单号、物流公司  为空,审核不通过");
				retMap.put("errorMsg","采购日期、要求交货日期、运费、供应商、支付方式 为空,审核不通过");
				return retMap;
			}
			pror.setStatus((short)2);
			String dateStr = dft.format(Calendar.getInstance().getTime());
			String fmt =String.format("LS-%s-",dateStr)+"%d";
			pror.setSerialNumber(this.tableKeyService.nextSerialNumber("purchase_orders",fmt));
			pror.setAuditTime(new Date());
			pror.setAuditUserId(curUser.getUserId());
			pror.setLastUpdatedTime(new Date());
			purchaseOrderService.edit(pror,null);
			
			//记录采购单生成日志
			PurchaseOrderLog log = new PurchaseOrderLog();
			log.setOrderSn(pror.getOrderNo());
			log.setCreatedTime(new Date());
			log.setOldStatus((short)1);
			log.setNewStatus((short)2);
			log.setContent("采购单变更状态:待审核到已审核");
			log.setOperUser(curUser.getName());
			purchaseOrderLogDao.insert(log);
			
		}catch(Exception e){
			retMap.put("result", false);
			retMap.put("errorMsg",e.getMessage());
			logger.error("audit purchaseorder error:",e);
		}
		return retMap;
	}
	
	
	@RequestMapping("reverse_audit")
	public String reverseAudit(@RequestParam("id") Integer id,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		try{
			PurchaseOrder pror = purchaseOrderService.get(id);
			pror.setStatus((short)1);
			pror.setAuditTime(null);
			pror.setAuditUserId(null);
			pror.setLastUpdatedTime(new Date());
			purchaseOrderService.edit(pror,null);
			
			//记录采购单生成日志
			PurchaseOrderLog log = new PurchaseOrderLog();
			log.setOrderSn(pror.getOrderNo());
			log.setCreatedTime(new Date());
			log.setOldStatus((short)2);
			log.setNewStatus((short)1);
			log.setContent("采购单变更状态:已审核到待审核");
			log.setOperUser(curUser.getName());
			purchaseOrderLogDao.insert(log);
			
		}catch(Exception e){
			logger.error("reverse audit purchaseorder error:",e);
		}
		return "redirect:/purchaseorder/list";
	}
	
	@RequestMapping("manualClosing")
	public String manualClosing(@RequestParam(value="id",required=true)Integer id,RedirectAttributes attr,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		PurchaseOrder pror = purchaseOrderService.get(id);
		List<PurchaseOrderItem> items = purchaseOrderService.findItem(id);
		int oldStatus = pror.getStatus();
		String oldStatusName = "";
		switch(oldStatus){
			case -1: oldStatusName="异常入库";break;
			case 1: oldStatusName="待审核";break;
			case 2: oldStatusName="审核未接收";break;
			case 5: oldStatusName="审核已接收";break;
		}
		//手工结案：就是订单有至少一行　入库数量<采购数量　时，可以手工结案，手工结案后，行状态为关闭，采购订单状态为手工关闭
		boolean canClose = false;
		if (items!=null && items.size()>0){
			for(PurchaseOrderItem item:items){
				if (!item.getQualifiedCount().equals(item.getGoodsCount())){
					canClose = true;
				}
			}
		}
		if (canClose){
			pror.setStatus((short)4);			//修改采购采状态为:手工结案
			purchaseOrderService.edit(pror,null);
			//记录采购单生成日志
			PurchaseOrderLog log = new PurchaseOrderLog();
			log.setOrderSn(pror.getOrderNo());
			log.setCreatedTime(new Date());
			log.setOldStatus((short)oldStatus);
			log.setNewStatus((short)4);
			log.setContent("采购单变更状态:"+oldStatusName+"到手工结案");
			log.setOperUser(curUser.getName());
			purchaseOrderLogDao.insert(log);
		}
		attr.addFlashAttribute(!canClose?"infoMessage":"successMessage", !canClose?"手工结案操作失败[该采购单sku均已完成入库]":"手工结案成功");
		return "redirect:/purchaseorder/list";
	}
	
	/**
	 * 更新收货时间及状态
	 * @param order
	 * @param state
	 * @return
	 */
	@RequestMapping("receive")
	public String receive(PurchaseOrder order,Integer state,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		short status = 5;
		if(state == 5){
			status = 5;
			order.setStatus(status);
		}else if(state == 2){
			status = 2;
			order.setStatus(status);
		}
		this.purchaseOrderService.modifyReceive(status, order.getOrderNo());
		//记录采购单生成日志
		PurchaseOrderLog log = new PurchaseOrderLog();
		log.setOrderSn(order.getOrderNo());
		log.setCreatedTime(new Date());
		log.setNewStatus(order.getStatus());
		log.setContent("采购单变更状态:已审核到" +((order.getStatus()==2)?"审核未接收":"审核已接收"));
		log.setOperUser(curUser.getName());
		purchaseOrderLogDao.insert(log);		
		return "redirect:/purchaseorder/list";
	}
	
	/**
	 * 采购合同打印
	 * @return
	 */
	@RequestMapping("print")
	public ModelAndView orderPrint(ModelAndView model,@RequestParam(value="orderId",required=true)Integer orderId[]){
		List<PurchaseOrder> poList = new ArrayList<>();
		for(Integer id:orderId){
			PurchaseOrder porder = purchaseOrderService.get(id);
			porder.setItems(purchaseOrderService.findItem(id));
			porder.setSupplier(goodsSupplierService.load(porder.getSupplierId()));
			poList.add(porder);
		}
		model.addObject("poList",poList);
		model.setViewName("goods/purchaseorder-print");
		return model;
	}
	
	/**
	 * 采购订单批量打印
	 * @param printOrderId  待打印的采购单
	 * @param group 0:按供应商分组  1:按采购员分组
	 * @return
	 */
	@RequestMapping("batch-print")
	public  ModelAndView batchPrint(ModelAndView model,@RequestParam(value="orderId",required=true)Integer orderId[],Integer group){
		Map<String,List<PurchaseOrderItem>> supplierItemMap = new LinkedHashMap<>();
		Map<String,List<PurchaseOrderItem>> buyerItemMap = new LinkedHashMap<>();
		List<PurchaseOrderItem> itemList = new ArrayList<>();
		if (group==null){
			for(Integer id:orderId){
				PurchaseOrder porder = purchaseOrderService.get(id);
				List<PurchaseOrderItem> items = purchaseOrderService.findItem(id);
				for(PurchaseOrderItem item:items){
					item.setSupplierName(porder.getSupplierName());
				}
				itemList.addAll(items);
			}
			model.addObject("data",itemList);
		}else if (group==0){
			for(Integer id:orderId){
				PurchaseOrder porder = purchaseOrderService.get(id);
				List<PurchaseOrderItem> items = purchaseOrderService.findItem(id);
				for(PurchaseOrderItem item:items){
					item.setSupplierName(porder.getSupplierName());
				}
				String supplierName = porder.getSupplierName();
				if (!supplierItemMap.containsKey(supplierName)){
					supplierItemMap.put(supplierName, items);
				}else{
					supplierItemMap.get(supplierName).addAll(items);
				}
			}
			model.addObject("data",supplierItemMap);
		}else if (group==1){
			for(Integer id:orderId){
				PurchaseOrder porder = purchaseOrderService.get(id);
				List<PurchaseOrderItem> items = purchaseOrderService.findItem(id);
				for(PurchaseOrderItem item:items){
					item.setSupplierName(porder.getSupplierName());
				}
				String buyerName = porder.getBuyUserName();
				if (!buyerItemMap.containsKey(buyerName)){
					buyerItemMap.put(buyerName, items);
				}else{
					buyerItemMap.get(buyerName).addAll(items);
				}
			}
			model.addObject("data",buyerItemMap);
		}
		model.addObject("printOrderId",orderId);
		model.addObject("group",group);
		model.setViewName("goods/purchaseorder-batchprint");
		return model;
	}
	
	/**
	 * 批量审核
	 * @param id
	 * @param curUser
	 * @return
	 */
	@RequestMapping("batch-audit")
	public ModelAndView orderBatchAudit(ModelAndView model,@RequestParam("orderId") Integer []orderId,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		try{
			Map<String,String> errorOrderNo = new HashMap<>();
			for(Integer id:orderId){
				PurchaseOrder pror = purchaseOrderService.get(id);
				if (pror.getStatus()!=1){
					errorOrderNo.put(pror.getOrderNo(),"采订单已审核");
					continue;
				}
				//采购日期、要求交货日期、运费、供应商、支付方式 为空,则审核不通过
				if (pror.getPurchaseDate()==null || pror.getDeliveryDate()==null  || pror.getFreight()==null 
						|| pror.getSupplierId()==null || StringUtils.isEmpty(pror.getPayMethod()) /* || StringUtils.isEmpty(pror.getWaybillNo()) || StringUtils.isEmpty(pror.getLogisticsCompany())*/){
					//errorOrderNo.put(pror.getOrderNo(),"请检查采购日期、要求交货日期、运费、供应商、支付方式、运单号、物流公司  是否为空");
					errorOrderNo.put(pror.getOrderNo(),"请检查采购日期、要求交货日期、运费、供应商、支付方式  是否为空");
					continue;
				}
				pror.setStatus((short)2);
				String dateStr = dft.format(Calendar.getInstance().getTime());
				String fmt =String.format("LS-%s-",dateStr)+"%d";
				pror.setSerialNumber(this.tableKeyService.nextSerialNumber("purchase_orders",fmt));
				pror.setAuditTime(new Date());
				pror.setAuditUserId(curUser.getUserId());
				pror.setLastUpdatedTime(new Date());
				purchaseOrderService.edit(pror,null);
				
				//记录采购单生成日志
				PurchaseOrderLog log = new PurchaseOrderLog();
				log.setOrderSn(pror.getOrderNo());
				log.setCreatedTime(new Date());
				log.setOldStatus((short)1);
				log.setNewStatus((short)2);
				log.setContent("采购单变更状态:待审核到已审核");
				log.setOperUser(curUser.getName());
				purchaseOrderLogDao.insert(log);
			}
			model.addObject("errorMap",errorOrderNo);
		}catch(Exception e){
			logger.error("batch audit purchaseorder error:",e);
		}
		model.setViewName("goods/purchaseorder-batchaudit");
		return model;
	}
	
	/**
	 * 采购运单号导入
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="importYdh")
	public String traceNumberImport(MultipartHttpServletRequest request,RedirectAttributes attr){
		InputStream is = null;
		MultipartFile file = null;
		String fileName = null;
		try{
			file = request.getFile("importYdhFile");
			fileName = file.getOriginalFilename();
			int idx = fileName.lastIndexOf(".");
			String extensionName = fileName.substring(idx + 1);
			if (null==file || idx==-1 || file.isEmpty() || !extensionName.startsWith("xls")){
				attr.addFlashAttribute("errorMessage","导入文件格式不对或导入文件为空");
			}else{
				is = file.getInputStream();
				List<String[]> rowsList = ExcelParser.parseToString(is, extensionName);
				for(int i=1;i<rowsList.size();i++){
					String [] row = rowsList.get(i);
					//采购单正常关闭或手工结案则不允许再导入运单号
					PurchaseOrder po = this.purchaseOrderService.findByOrderNo(row[0]);
					if (po==null || po.getStatus().intValue()==3 || po.getStatus().intValue()==4){
						continue;
					}
					PurchaseOrderWayBillNo pbo = this.purchaseOrderService.findWayBillNo(row[0].trim(), row[1].trim(),row[2].trim());
					//如果运单号已存在,跳过处理
					if (pbo!=null){
						continue;
					}
					////row[0] 采购单号   //row[1] 运单号   //row[2] 物流公司  任一项为空则不处理
					if (StringUtils.isEmpty(row[0].trim()) || StringUtils.isEmpty(row[1].trim()) || StringUtils.isEmpty(row[2].trim())){
						continue;
					}
					//row[0] 采购单号   //row[1] 运单号   //row[2] 物流公司
					purchaseOrderService.fillOrderYdh(row[0].trim(), row[1].trim(),row[2].trim());
				}
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
		return "redirect:/purchaseorder/list";
	}
	
	
	@RequestMapping("purchaseReportTotal.json")
	@ResponseBody
	public PurchaseOrderStatistic purchaseOrderTotal(PurchaseOrderReportParam param) {
		return this.purchaseOrderService.purchaseOrderTotal(param);
	}
	
	
	@RequestMapping("orderpaid.json")
	@ResponseBody
	/**
	 * 
	 * @param orderId
	 * @param payStatus  支付类型  1:部分付款  2:全部付款
	 * @return
	 */
	public Map<String,Object> orderPaid(@RequestParam("orderId") Integer []orderId,@RequestParam("payStatus") Integer payStatus){
		Map<String,Object> retMap = new HashMap<>();
		Date date = new Date();
		try{
			for(Integer id:orderId){
				PurchaseOrder por = this.purchaseOrderService.get(id);
				//未审核采购单不能付款
				if (por.getStatus().intValue()==1){
					continue;
				}
				//采购已全额付款或则跳过处理
				if (null!=por.getPayStatus() && por.getPayStatus()==2){
					continue;
				}
				PurchaseOrder por2 = new PurchaseOrder();
				por2.setOrderNo(por.getOrderNo());
				por2.setPayStatus(payStatus);
				por2.setPaidTime(date);
				this.purchaseOrderService.edit(por2, null);
			}
			retMap.put("result", true);
		}catch(Exception e){
			retMap.put("result", false);
		}
		return retMap;
	}
	
	@RequestMapping("waybill")
	public ModelAndView  waybillNos(ModelAndView model,@RequestParam("orderNo") String orderNo){
		PurchaseOrder po = this.purchaseOrderService.findByOrderNo(orderNo);
		List<PurchaseOrderWayBillNo> wayBillNoList = this.purchaseOrderService.findWayBillNos(orderNo);
		if (!StringUtils.isEmpty(po.getWaybillNo())){
			PurchaseOrderWayBillNo powb = new PurchaseOrderWayBillNo();
			powb.setOrderNo(po.getOrderNo());
			powb.setWaybillNo(po.getWaybillNo());
			powb.setLogisticsCompany(po.getLogisticsCompany());
			powb.setCreatedTime(po.getAuditTime());
			powb.setFirstWaybillNo(true);
			wayBillNoList.add(powb);
		}
		model.addObject("wayBillNos",wayBillNoList);
		//如果采购单已完成，则只能查看其运单号信息
		model.addObject("finished",(po.getStatus()==3 || po.getStatus()==4)?true:false);
		model.setViewName("goods/purchaseorder-waybillno");
		return model;
	}
	
	@RequestMapping("editWaybillNo")
	public   String editWaybillNo(PurchaseOrderWayBillNo param,RedirectAttributes redirt,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		try{
			redirt.addAttribute("orderNo",param.getOrderNo());	
			//运单号和物流公司未更改
			if (param.getWaybillNo().equals(param.getWaybillNoNew()) && param.getLogisticsCompany().equals(param.getLogisticsCompanyNew())){
				return "redirect:/purchaseorder/waybill";
			}
			this.purchaseOrderService.editWayBillNo(param);
			PurchaseOrderLog log = new PurchaseOrderLog();
			log.setOrderSn(param.getOrderNo());
			log.setCreatedTime(new Date());
			log.setContent(String.format("运单号[%s]修改成[%s],物流公司[%s]修改成[%s]",param.getWaybillNo(),param.getWaybillNoNew(),param.getLogisticsCompany(),param.getLogisticsCompanyNew()));
			log.setOperUser(curUser.getName());
			this.purchaseOrderLogDao.insert(log);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return "redirect:/purchaseorder/waybill";
	}
	
	
	//导出采购订单
	@RequestMapping("exportPurchaseOrder")
	public void exportPurchaseOrder(PurchaseOrderParam param,@RequestParam(value="ids",required=false) List<Integer> ids,
			HttpServletResponse response) throws Exception{
		 Integer maxRowCount = App.getConfigInt("export.excel.maxnumber");
		 if (ids!=null && ids.size()>0){
			 param = null;
		 }
		 Integer recordCount = this.purchaseOrderService.findCount(param, ids);
		if (recordCount!=null && recordCount>maxRowCount){
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("error");
			response.getWriter().close();
		}else{
			List<PurchaseOrderExport> exportList = this.purchaseOrderService.findList(param, ids);
			//拼接采购单的多个运单号及物流公司字符串 begin
			Map<String,String> orderNoWayBllNoMap= new HashMap<>();
			Map<String,String> orderNoLogisticsCompanyMap= new HashMap<>();
			Map<String,String> orderFreightMap = new HashMap<>();
			for(PurchaseOrderExport po :exportList){
				if (!orderFreightMap.containsKey(po.getOrderNo())){
					orderFreightMap.put(po.getOrderNo(), po.getFreight());
				}
				if (orderNoWayBllNoMap.containsKey(po.getOrderNo()) || orderNoLogisticsCompanyMap.containsKey(po.getOrderNo())){
					continue;
				}
				orderNoWayBllNoMap.put(po.getOrderNo(),StringUtils.isEmpty(po.getWaybillNo())?" ":po.getWaybillNo());
				orderNoLogisticsCompanyMap.put(po.getOrderNo(), StringUtils.isEmpty(po.getLogisticsCompany())?" ":po.getLogisticsCompany());
				 List<PurchaseOrderWayBillNo> wayBillNoList = this.purchaseOrderService.findWayBillNos(po.getOrderNo());
				 if (null!=wayBillNoList && wayBillNoList.size()>0){
					 for(PurchaseOrderWayBillNo wayBillNo:wayBillNoList){
						 orderNoWayBllNoMap.put(wayBillNo.getOrderNo(),orderNoWayBllNoMap.get(po.getOrderNo()).concat(wayBillNo.getWaybillNo()+" "));
						 orderNoLogisticsCompanyMap.put(wayBillNo.getOrderNo(),orderNoLogisticsCompanyMap.get(po.getOrderNo()).concat( wayBillNo.getLogisticsCompany()+" "));
					 }
				 }
			}
			
			
			for(PurchaseOrderExport po :exportList){
				po.setWaybillNo(orderNoWayBllNoMap.get(po.getOrderNo()));
				po.setLogisticsCompany(orderNoLogisticsCompanyMap.get(po.getOrderNo()));
				//采购单多个sku运费只显示在一个sku上
				if (orderFreightMap.containsKey(po.getOrderNo())){
					orderFreightMap.remove(po.getOrderNo());
				}else{
					po.setFreight(null);
				}
			}
			
			//组装多个运单号及物流公司字符串 end
			
			List<Column> cs = new ArrayList<Column>();
			
			cs.add(new Column("SKU","goodsSku"));
			cs.add(new Column("名称","goodsName"));
			cs.add(new Column("数量","goodsCount"));
			cs.add(new Column("采购单价","goodsCost"));
			cs.add(new Column("金额","amount"));
			cs.add(new Column("采购单号","orderNo"));
			cs.add(new Column("流水号","serialNumber"));
			cs.add(new Column("采购类型","orderType"));
			cs.add(new Column("运单号","waybillNo"));
			cs.add(new Column("物流公司","logisticsCompany"));
			cs.add(new Column("采购日期","purchaseDate"));
			cs.add(new Column("付款状态","payStatus"));
			cs.add(new Column("付款日期","paidTime"));
			cs.add(new Column("供应商名称","companyName"));
			cs.add(new Column("采购员","buyer"));
			cs.add(new Column("运费","freight"));
			cs.add(new Column("支付方式","payMethod"));
			cs.add(new Column("审核人","auditer"));
			cs.add(new Column("审核日期","auditTime"));
			cs.add(new Column("状态","status"));
			
			String filename = "采购单.xlsx";
			ServletOutputStream os = null;
			try{
				ExcelBuilder<PurchaseOrderExport> eb = new ExcelBuilder<PurchaseOrderExport>();
				eb.setFileType(FileType.XLSX);
				eb.setData(exportList);
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
				exportList = null;
				orderNoWayBllNoMap = null;
				orderNoLogisticsCompanyMap = null;
				orderFreightMap = null;
				if (null!=os){
					os.flush();
					os.close();
				}
			}
		}
	}
	
}
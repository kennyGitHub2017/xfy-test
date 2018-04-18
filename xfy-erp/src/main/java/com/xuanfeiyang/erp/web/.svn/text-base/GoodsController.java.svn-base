package com.xuanfeiyang.erp.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.buzheng.excel.Column;
import org.buzheng.excel.ExcelBuilder;
import org.buzheng.excel.FieldFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsCostLog;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.GoodsParam;
import com.xuanfeiyang.erp.service.DictService;
import com.xuanfeiyang.erp.service.FileImportException;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
import com.xuanfeiyang.erp.service.GoodsPackingMaterialService;
import com.xuanfeiyang.erp.service.GoodsService;
import com.xuanfeiyang.erp.service.StoreService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.web.util.DataTableOrder;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/goods")
@SessionAttributes(App.SESSION_USER_KEY)
public class GoodsController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private DictService dictService;
	
	@Resource
	private GoodsCategoryService goodsCategoryService;
	
	@Resource
	private GoodsPackingMaterialService goodsPackingMaterialService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private StoreService storeService;
	
	@RequestMapping({"", "/"})
	public String index(Model model){
		model.addAttribute("baseCategories", this.goodsCategoryService.findByParentId(0));
		//model.addAttribute("users", this.userService.findAll());
		model.addAttribute("stores", this.storeService.findStroe());
		model.addAttribute("goodsStatus", this.dictService.findByType(102));	
		model.addAttribute("goods", new Goods());
		List<UserInfo> userList1 = this.userService.findByDepartment(142679);//采购
		List<UserInfo> userList2 = this.userService.findByDepartment(142677);//包装$拣货
		List<UserInfo> userList3 = this.userService.findByDepartment(142678);//开发员
		model.addAttribute("buyUser", userList1);//采购
		model.addAttribute("packUser", userList2);//包装$拣货
		model.addAttribute("devUser", userList3);//开发员
		return "goods/goods-list";
	}
	
	/**
	 * 添加
	 * @param goods
	 * @return
	 */
	@RequestMapping("add")
	public String add(Goods goods,RedirectAttributes redirectAttr, MultipartHttpServletRequest request)throws Exception{
		this.uploadFun(request, goods);

		boolean bl = this.goodsService.insert(goods);
		 
		 if(bl){
			 redirectAttr.addFlashAttribute("successMessage", "添加成功");
			}else{
				redirectAttr.addFlashAttribute("successMessage", "添加失败");
			}
		 return "goods/goods-edit";
	}
	
	/**
	 * 列表
	 * @param dtr
	 * @param goods
	 * @return
	 */
	@RequestMapping("/page-json")
	@ResponseBody
	public DataTableResponse<Goods> pageJson(@RequestBody DataTableRequest<GoodsParam> dtr){
		String keywords = dtr.getSearch() == null ? null : dtr.getSearch().getValue();
		dtr.getParams().setOrderByStr(getOrderByStr(dtr.getOrder()));
		GoodsParam params = dtr.getParams();
		if (params == null) {
			params = new GoodsParam();
		}
		params.setKeywords(keywords);
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		
		Page<Goods> page = this.goodsService.findPage(pageRequest,params);
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	/**
	 * 删除
	 * @param redirectAttr
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(RedirectAttributes redirectAttr,
			@RequestParam(value="id", required=false) Integer id){
		boolean bl = this.goodsService.delete(id);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "删除成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "删除失败");
		}
		
		return "redirect:/goods/goodsList";
	}
	
	/**
	 * 修改
	 * @param goods
	 * @return
	 */
	@RequestMapping("update")
	public String update(Model model, RedirectAttributes attr,
			Goods goods,MultipartHttpServletRequest request){
		
		boolean bl = this.goodsService.mofify(goods);
		if(bl){
			 goods = this.goodsService.findById(goods.getId());
			 model.addAttribute("goodsAttr",goods);
		}
		
		attr.addAttribute("id", goods.getId());
		attr.addFlashAttribute("successMessage", "g.tips.success");
		return "redirect:/goods/edit";
	}
	
	/**
	 * 页面
	 * GOODS 添加/修改  页面
	 * @param id
	 * @param model
	 * @param editType
	 * @return
	 */
	@RequestMapping("edit")
	public String editPage(Model model,
			@RequestParam(value = "actionType",required = false)Integer actionType,
			@RequestParam(value = "id", required = false) Integer id) {
		
		if (id != null) {// 表示修改
			Goods goods = this.goodsService.findByIdWithSupplierName(id);
			model.addAttribute("goods", goods);
			model.addAttribute("descriptions", this.goodsService.findDescriptionsBySku(goods.getGoodsSku()));
		} else {
			model.addAttribute("goods", new Goods());
		}

		model.addAttribute("goodsStatus", this.dictService.findByType(102));
		model.addAttribute("baseCategories", this.goodsCategoryService.findByParentId(0));
		model.addAttribute("packingMateriales", this.goodsPackingMaterialService.find());
		
		List<UserInfo> userList1 = this.userService.findByDepartment(142679);//采购
		List<UserInfo> userList2 = this.userService.findByDepartment(142677);//包装$拣货
		List<UserInfo> userList3 = this.userService.findByDepartment(142678);//开发员
		List<UserInfo> userList4 = this.userService.findSaleUser();//销售人员
		model.addAttribute("buyUser", userList1);//采购
		model.addAttribute("packUser", userList2);//包装$拣货
		model.addAttribute("devUser", userList3);//开发员
		model.addAttribute("saleUser", userList4);//销售人员
		
		model.addAttribute("stores", this.storeService.findStroe());
		model.addAttribute("actionType", actionType);//操作类型 查看=0 修改=1
		return "goods/goods-edit";
	}
	
	
	/**
	 * 上传商品
	 * @param request
	 * @param goods
	 * @throws Exception
	 */
	
	public void uploadFun(MultipartHttpServletRequest request, Goods goods) throws Exception{
		
		
		MultipartFile file = request.getFile("file");
		String filename = file.getOriginalFilename();
		String extensionName = FilenameUtils.getExtension(filename);
		
		filename = goods.getGoodsSku() + "." + extensionName;
		
		String relativePath = this.getRelativePath(filename);
		String realPath = App.getConfig("upload.base.dir") + relativePath;
		File imgFile = new File(realPath);
		
		File dir = imgFile.getParentFile();
		if (! dir.exists()) {
			dir.mkdirs();
		}
		
		FileCopyUtils.copy(file.getBytes(), imgFile);
		
		goods.setImgUrl(File.separatorChar != '/' ? relativePath.replace(File.separatorChar, '/') : relativePath);
	}
	
	private String getRelativePath(String filename) {
		return "goods" + File.separator + filename;
	}

	@RequestMapping("/load-by-sku.json")
	@ResponseBody
	public Goods loadBySku(@RequestParam("sku") String sku) {
		Goods goods = this.goodsService.findBySku(sku);
		return goods;
	}
	
	/**
	 * 判断SKU是否重复
	 * @param goodsSku
	 * @param id
	 * @return
	 */
	@RequestMapping("checkSku")
	@ResponseBody
	public boolean checkSku(@RequestParam("goodsSku")String goodsSku,@RequestParam("id")Integer id){
		Goods goods = null;
		if (id == null) {// 添加
			goods = this.goodsService.findBySku(goodsSku);
			if (goods != null) {
				return false;
			} else {
				return true;
			}

		} else {// 修改
			goods = this.goodsService.findBySku(goodsSku);
			if (goods.getId().equals(id)) {
				return true;
			} else {
				goods = this.goodsService.findBySku(goodsSku);

				if (goods != null) {
					return false;
				} else {
					return true;
				}
			}
		}
	}
	
	/**
	 * 导入商品资料importGoods
	 * @param request
	 * @return
	 */
	@RequestMapping("import")
	public String importGoods(Model model,RedirectAttributes redirectAttr,
			@RequestParam("fileName")MultipartFile file,Goods goods){
		
		try {
			goodsService.importGoods(file, goods);
			redirectAttr.addFlashAttribute("successMessage", "导入成功");
		} catch (FileImportException e) {
			logger.error("导入出错", e);
			model.addAttribute("errorMessage", String.format("%s行 %s列, %s", e.getLineNumber(), e.getColumnNumber(), e.getMessage()));
		} catch (Exception e) {
			logger.error("导入出错", e);
			model.addAttribute("errorMessage", e.getMessage());
		}
		 
		return "goods/goods-list";
	}
	
	/**
	 * 导入商品描述页面
	 * 
	 * @return
	 */
	@RequestMapping(value="import-goods-desc", method=RequestMethod.GET)
	public String importGoodsDescPage() {
		return "goods/goods-desc-upload";
	}
	
	/**
	 * 处理商品详情导入
	 * 
	 * @param file
	 * @param redirectAttr
	 * @return
	 */
	@RequestMapping(value="import-goods-desc", method=RequestMethod.POST)
	public String importGoodsDesc(@RequestParam("goodsDescFile") MultipartFile file,
			RedirectAttributes redirectAttr) {
		
		try {
			this.goodsService.importGoodsDesc(file);
			redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		} catch (FileImportException e) { 
			String msg = e.getMessage();
			if (e.getLineNumber() > 0) {
				msg += ", " + e.getLineNumber() + " 行";
			}
			redirectAttr.addFlashAttribute("errorMessage", msg);
		} catch (Exception e) {
			redirectAttr.addFlashAttribute("errorMessage", "g.tips.error");
		}
		
		return "redirect:/goods/import-goods-desc";
	}
	
	/**
	 * 更新商品 openFlag 是否开放给卖家
	 * @param ids
	 * @param openFlag
	 * @return
	 */
	@RequestMapping("/open-flag.json")
	@ResponseBody
	public String updateOpenFlag(@RequestParam("id") List<Integer> ids, 
			@RequestParam("open") String openFlag) {
		
		if (ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				try {
					this.goodsService.updateOpenFlag(id, openFlag);
				} catch (Exception e) {
					logger.error("更新商品 openFlag 出错", e);
				}
			}
		}
		
		return "1";
	}
	
	
	/***
	 * 导出产品资料
	 * @param param
	 */
	@RequestMapping("exportInfo")
	public void exportInfo(GoodsParam param,HttpServletResponse response,Integer type,
			@RequestParam(value="ids",required=false)List<Integer> ids)throws Exception{
		List<Goods> rows = null;
		if(ids != null){
			rows = this.goodsService.findListById(ids);
		}else{
			rows = this.goodsService.findByParam(param);
		}
		
		
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("SKU","goodsSku"));
		cs.add(new Column("老sku","oldSku"));
		cs.add(new Column("货品名称","name"));
		cs.add(new Column("英文","enName"));
		cs.add(new Column("产品单位","unit"));
		cs.add(new Column("采购价","cost"));
		
		cs.add(new Column("产品重量(kg)","weight"));
		cs.add(new Column("建议售价","price"));
		cs.add(new Column("英文申报名称","declarationNameEn"));
		cs.add(new Column("海关编码","customsCode"));
		cs.add(new Column("中文申报名称","declarationNameCn"));
		cs.add(new Column("申报价值USD","declarationCost"));
		
		cs.add(new Column("产品长(cm)","length"));
		cs.add(new Column("产品宽(cm)","width"));
		cs.add(new Column("产品高(cm)","height"));
		
		cs.add(new Column("颜色","color"));
		cs.add(new Column("码数大小","goodsSize"));
		
		cs.add(new Column("材质","materil"));
		cs.add(new Column("品牌","brand"));
		
		cs.add(new Column("是否带磁性","isMagnetic",new FieldFormatter<Integer,Goods>(){
			@Override
			public String format(Integer isMagnetic, Goods goods) {
				if(isMagnetic == 0){
					return "否";
				}else if(isMagnetic == 1){
					return  "是";
				}else{
					return "";
				}
			}

		}));
		cs.add(new Column("是否含电池","isBattery",new FieldFormatter<Short,Goods>(){
			@Override
			public String format(Short isBattery, Goods goods) {
				if(isBattery == 0){
					return "否";
				}else if(isBattery == 1){
					return  "是";
				}else{
					return "";
				}
			}
			
		}));
		
		cs.add(new Column("是否侵权","isCopyright",new FieldFormatter<Short,Goods>(){
			@Override
			public Object format(Short isCopyright, Goods goods) {
				if(isCopyright == 0){
					return "否";
				}else if(isCopyright == 1){
					return  "是";
				}else{
					return "";
				}
			}
		}));
		
		
		cs.add(new Column("是否指定运输方式","isShipping",new FieldFormatter<Short,Goods>(){
			@Override
			public Object format(Short isShipping, Goods goods) {
				if(isShipping == 0){
					return "否";
				}else if(isShipping == 1){
					return  "是";
				}else{
					return "";
				}
			}
			
		}));
		
		
		cs.add(new Column("是否带包装","ispacking",new FieldFormatter<Short,Goods>(){

			@Override
			public Object format(Short ispacking, Goods goods) {
				if(ispacking == 0){
					return "否";
				}else if(ispacking == 1){
					return  "是";
				}else{
					return "";
				}
			}
			
		}));
		
		cs.add(new Column("是否是液体","isLiquid", new FieldFormatter<Short,Goods>(){

			@Override
			public Object format(Short isLiquid, Goods goods) {
				if(isLiquid == 0){
					return "否";
				}else if(isLiquid == 1){
					return  "是";
				}else{
					return "";
				}
			}
			
			
		}));
		
		cs.add(new Column("是否是管制品","isRegulated",new FieldFormatter<Integer,Goods>(){

			@Override
			public Object format(Integer isRegulated, Goods goods) {
				if(isRegulated == 0){
					return "否";
				}else if(isRegulated == 1){
					return  "是";
				}else{
					return "";
				}
			}
			
		}));
		
		cs.add(new Column("销售人员","saleUser"));
		cs.add(new Column("采购人员","buyUserName"));
		cs.add(new Column("拣货人员","pickUserName"));
		cs.add(new Column("配货人员","assembleUserName"));
		
		cs.add(new Column("包装材料","packingCapacity"));
		cs.add(new Column("货位号","storeShelfCode"));
		
		cs.add(new Column("包装规格","materialModel"));
		cs.add(new Column("供应商1","supplierName"));
		cs.add(new Column("供应商2","supplier2Name"));
		cs.add(new Column("供应商3","supplier3Name"));
		
		cs.add(new Column("产品状态","status"));
		cs.add(new Column("开发时间","developTime"));
		cs.add(new Column("开发员","developUserName"));
		
		cs.add(new Column("产品描述1","note1"));
		cs.add(new Column("产品描述2","note2"));
		cs.add(new Column("产品描述3","note3"));
		cs.add(new Column("产品描述4","note4"));
		cs.add(new Column("产品描述5","note5"));
		
		cs.add(new Column("检验方式","testType",new FieldFormatter<Short,Goods>(){
			@Override
			public Object format(Short testType, Goods goods) {
				if(testType != null){
					
				
				if(testType == 0){
					return  "全检";
				}else if(testType == 1){
					return  "抽检";
				}else{
					return "";
				}
				
				}else{
					return "";
				}
				
			}
			
		}));
		
		cs.add(new Column("包装费用","packingFee"));
		cs.add(new Column("产品小类","categoryName"));
		cs.add(new Column("规格","rules"));
		cs.add(new Column("型号","model"));
		cs.add(new Column("7天销量","sales7"));
		cs.add(new Column("15天销量","sales15"));
		cs.add(new Column("30天销量","sales30"));
		cs.add(new Column("库存量","count"));
		cs.add(new Column("锁定量","lockCount"));
		cs.add(new Column("在途量","purchaseCount"));
		
		cs.add(new Column("参考链接","referenceUrl"));
		
		String filename = "产品资料"+".xlsx";
		ExcelBuilder<Goods> eb = new ExcelBuilder<Goods>();
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
	 * 获取字段排序字段名
	 * 根据排序字段列数获取字段名称
	 * @param columnList
	 * @return
	 */
	private String getOrderByStr(List<DataTableOrder> columnList){
		if(columnList == null || columnList.size() == 0){
			return null;
		}
		
		Map<Integer,String> columnMap = new HashMap<Integer,String>();
		columnMap.put(2, "g.goods_sku");
		columnMap.put(13, "sales7");
		columnMap.put(14, "sales15");
		columnMap.put(15, "sales30");
		StringBuffer sbf = new StringBuffer();
		for (DataTableOrder column:columnList) {
			if(!columnMap.containsKey(column.getColumn())){
				continue;
			}
			sbf.append(columnMap.get(column.getColumn()) + " " + column.getDir()+",");
		}
		return sbf.length() > 1?sbf.substring(0, sbf.length()-1):null;
	}
	
	
	@RequestMapping("skuListingPage")
	private String skuListingPage(Model model, String goodsSku, Integer count,
			@RequestParam(value = "goodsSku", required = false) String goods){
		model.addAttribute("goodsSku", goodsSku);
		model.addAttribute("count",count);
		return "goods/goods-listing-info";
	}
	
	@RequestMapping("listing-info")
	@ResponseBody
	private DataTableResponse<Goods> listingInfo(@RequestBody DataTableRequest<GoodsParam> dtr,
			@RequestParam(value = "goodsSku", required = false)String goodsSku, Integer count){
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr, true);
		Page<Goods> page = this.goodsService.getListingInfo(pageRequest, goodsSku);
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	
	@RequestMapping("getListingCount")
	@ResponseBody
	public List<Goods> getListingCount(@RequestParam("sku")List<String> sku){
		return this.goodsService.getListingCount(sku);
	}
	
	@RequestMapping("costPage")
	public String goodsCostPage(Model model,@RequestParam("id")Integer id){
		Goods goods = this.goodsService.findById(id);
		model.addAttribute("goodsAttr", goods);
		List<GoodsCostLog> costList = this.goodsService.findLogBySku(goods.getGoodsSku());
		model.addAttribute("costListAttr", costList);
		return "goods/goods-cost";
	}

	/**
	 * 修改价格
	 * @param user
	 * @param id
	 * @return
	 */
	@RequestMapping("modifyCost")
	@ResponseBody
	public String modifyCost(
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser user,
			@RequestParam("id") Integer id,@RequestParam("sku") String sku,
			@RequestParam("cost") String cost,
			@RequestParam(value="costUptype",required = false) Integer costUptype,
			@RequestParam(value="customUpType",required = false) Integer customUpType,
			@RequestParam(value="customCost",required = false) String customCost) {
		
		String userName = user.getUsername();
		String flag = "";
		try {
			
			this.goodsService.modifyGoodsCost(id, cost, costUptype, userName, 
					sku, customCost, customUpType);
			flag = "succ";
		} catch (Exception e) {
			flag = "errer";
			logger.error("修改价格失败",e);
		}
		return flag;
	}

}

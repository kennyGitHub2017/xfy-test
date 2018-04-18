package com.xuanfeiyang.erp.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.buzheng.excel.Column;
import org.buzheng.excel.ExcelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsInventory;
import com.xuanfeiyang.erp.domain.GoodsInventoryStat;
import com.xuanfeiyang.erp.param.GoodsInventoryParam;
import com.xuanfeiyang.erp.service.DictService;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
import com.xuanfeiyang.erp.service.GoodsInventoryService;
import com.xuanfeiyang.erp.service.StoreService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.WebHelper;

/**
 * 商品库存相关
 * 
 * @author Adam
 *
 */
@Controller
@RequestMapping("/inventory")
public class GoodsInventoryController {
	
	private static Logger logger = LoggerFactory.getLogger(GoodsInventoryController.class);
	
	@Resource
	private GoodsInventoryService goodsInventoryService;
	
	@Resource
	private StoreService storeService;
	
	@Resource
	private GoodsCategoryService goodsCategoryService;
	
	@Resource
	private DictService dictService;
	
	/**
	 * 即时库存首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping({ "", "/" })
	public String index(Model model) {
		// 仓库
		model.addAttribute("stores", this.storeService.findStroe());
		// 大类
		model.addAttribute("baseCategories", this.goodsCategoryService.findByParentId(0));
		
		
		return "goods/inventory";
	}
	
	/**
	 * 即时库存分页查询
	 * 
	 * @param dtr
	 * @return
	 */
	@RequestMapping(value="page-json")
	@ResponseBody
	public DataTableResponse<GoodsInventory> pageJson(
			@RequestBody DataTableRequest<GoodsInventoryParam> dtr) {
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<GoodsInventory> page = this.goodsInventoryService.findPage(pageRequest, dtr.getParams());

		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	/**
	 * 库存统计页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/stat" })
	public String status(Model model) {
		// 仓库
		model.addAttribute("stores", this.storeService.findStroe());
		// 大类
		model.addAttribute("baseCategories", this.goodsCategoryService.findByParentId(0));
		
		model.addAttribute("goodsStatus", this.dictService.findByType(102));

		return "goods/inventory-stat";
	}
	
	/**
	 * 库存统计查询
	 * 
	 * @param dtr
	 * @return
	 */
	@RequestMapping(value="stat-json")
	@ResponseBody
	public DataTableResponse<GoodsInventoryStat> statJson(
			@RequestBody DataTableRequest<GoodsInventoryParam> dtr) {
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<GoodsInventoryStat> page = this.goodsInventoryService
				.findStatPage(pageRequest, dtr.getParams());

		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	/**
	 * 库存预警
	 * @return
	 */
	@RequestMapping("/alarm")
	public String alarm() {
		return "goods/inventory-alarm";
	}
	
	/**
	 * 导出即时库存
	 * @param response
	 * @param dtr
	 * @throws Exception
	 */
	@RequestMapping("exportInventory")
	public void exportGoodsInventory(HttpServletResponse response,GoodsInventoryParam dtr) throws Exception{
		List<GoodsInventory> rows = this.goodsInventoryService.find(dtr);
		
		List<Column> cs = new ArrayList<Column>();
		
		cs.add(new Column("SKU","goodsSku"));
		cs.add(new Column("商品名","goodsName"));
		cs.add(new Column("商品单位","goodsUnit"));
		cs.add(new Column("库存量","count"));
		cs.add(new Column("均价","hisAvgPrice"));
		cs.add(new Column("仓库","storeName"));
		cs.add(new Column("货位","storeShelfCode"));
		cs.add(new Column("商品大类","goodsBaseCategoryName"));
		cs.add(new Column("商品中类","goodsMidCategoryName"));
		cs.add(new Column("商品小类","goodsCategoryName"));
		
		String filename = "即时库存"+".xls";
		ServletOutputStream os = null;
		try{
			ExcelBuilder<GoodsInventory> eb = new ExcelBuilder<GoodsInventory>();
			eb.setData(rows);
			eb.setColumns(cs);
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
			+new String(filename.getBytes("GBK"),"ISO-8859-1"));
			
			os = response.getOutputStream();
			eb.toOutputStream(os);
		}catch(Exception e){
			logger.error("导出出错", e);
		}finally{
			rows = null;
			if (null!=os){
				os.flush();
				os.close();
			}
		}
	}
	
	/**
	 * 导出库存状态
	 * @param response
	 * @param dtr
	 * @throws Exception
	 */
	@RequestMapping("exportStat")
	public void exportStat(HttpServletResponse response,GoodsInventoryParam dtr) throws Exception{
		List<GoodsInventoryStat> rows = this.goodsInventoryService.findStat(dtr);
		
		List<Column> cs = new ArrayList<Column>();
		
		cs.add(new Column("SKU","goodsSku"));
		cs.add(new Column("商品名","goodsName"));
		cs.add(new Column("商品分类","goodsCategoryName"));
		cs.add(new Column("商品单位","goodsUnit"));
		cs.add(new Column("产品状态","goodsStatus"));
		cs.add(new Column("库存量","allCount"));
		cs.add(new Column("均价","hisAvgPrice"));
		cs.add(new Column("在途量","purchaseCount"));
		cs.add(new Column("锁定库存","lockCount"));
		cs.add(new Column("可用库存","availableCount"));
		cs.add(new Column("7天销量","sales7"));
		cs.add(new Column("15天销量","sales15"));
		cs.add(new Column("30天销量","sales30"));
		cs.add(new Column("60天销量","sales60"));
		cs.add(new Column("180天销量","sales180"));
		
		String filename = "库存状态"+".xls";
		ServletOutputStream os = null;
		try{
			ExcelBuilder<GoodsInventoryStat> eb = new ExcelBuilder<GoodsInventoryStat>();
			eb.setData(rows);
			eb.setColumns(cs);
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
			+new String(filename.getBytes("GBK"),"ISO-8859-1"));
			
			os = response.getOutputStream();
			eb.toOutputStream(os);
		}catch(Exception e){
			logger.error("导出出错", e);
		}finally{
			rows = null;
			if (null!=os){
				os.flush();
				os.close();
			}
		}
	}
	
	
}

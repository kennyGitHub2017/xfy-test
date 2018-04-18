package com.xuanfeiyang.erp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.domain.GoodsCategory;
import com.xuanfeiyang.erp.service.GoodsCategoryService;

@Controller
@RequestMapping("/goodscategory")
public class GoodsCategoryController extends BaseController{
	@Resource
	private GoodsCategoryService goodsCategoryService;
	
	@Resource
	private MessageSource messageSource;
	
	private Logger logger = LoggerFactory.getLogger(GoodsCategoryController.class);
	
	public void setGoodsCategoryService(GoodsCategoryService goodsCategoryService) {
		this.goodsCategoryService = goodsCategoryService;
	}
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	private void add(GoodsCategory category,RedirectAttributes attr){
		this.goodsCategoryService.add(category);
		logger.info("add  category id:" + category.getId());
		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
	}
	
	private void update(GoodsCategory category,RedirectAttributes attr){
		this.goodsCategoryService.modify(category);
		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
	}
	
	private void remove( Integer id,RedirectAttributes attr){
		if (id!=null){
			List<GoodsCategory> tmp = this.goodsCategoryService.findByParentId(id);
			if (tmp != null && tmp.size() > 0){
				attr.addFlashAttribute("errorMessage",this.messageSource.getMessage("goods.category.remove.childcategory", null, null));
				return;
			}
			if (goodsCategoryService.categoryInuse(id)){
				attr.addFlashAttribute("errorMessage",this.messageSource.getMessage("goods.category.remove.inuse", null, null));
				return;
			}
			this.goodsCategoryService.remove(id);
			logger.info("dele category id:" + id);
			attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
		}
	}
	
	////////////////////////////产品大类///////////////////////////////////////////////////

	@RequestMapping(value = "firstcgy-list")
	public ModelAndView firstCategoryList(ModelAndView model){
		//获取所有一级分类
		List<GoodsCategory> firstCategory =  this.goodsCategoryService.findByParentId(0);
		model.addObject("firstCategory", firstCategory);
		model.setViewName("goods/firstCategory-list");
		return model;
	}
	
	@RequestMapping(value ="firstcgy-add")
	public ModelAndView addFirstCategory(GoodsCategory category,RedirectAttributes attr){
		add(category,attr);
		return new ModelAndView("redirect:/goodscategory/firstcgy-list");
	}
	
	@RequestMapping(value ="firstcgy-edit")
	public ModelAndView editFirstCategory(GoodsCategory category,RedirectAttributes attr){
		update(category,attr);
		return new ModelAndView("redirect:/goodscategory/firstcgy-list");
	}
	
	@RequestMapping(value ="firstcgy-remove/{id}")
	public ModelAndView  removeFirstCategory(@PathVariable(value="id") Integer id,RedirectAttributes attr){
		remove(id,attr);
		return new ModelAndView("redirect:/goodscategory/firstcgy-list");
	}
	
	/**
	 * 加载单个数据的form页面
	 */
	@RequestMapping(value="firstcgy-form")
	public String form(Model model, @RequestParam(value="id",required=false) Integer id) {
		if (id != null) {
			model.addAttribute("category", this.goodsCategoryService.get(id));
			// 修改标识
			model.addAttribute("updateFlag", "1");
		}else{
			model.addAttribute("category", new GoodsCategory());
		}
		return "goods/firstCategory-form";
	}
	
	/**
	 * 检查类目的名称，编码是否存在
	 * @param categoryName
	 * @param id
	 * @return
	 */
	@RequestMapping(value="checkCategory")
	@ResponseBody
	public boolean checkCategory(
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="id",required=false) Integer id){
		
		if (name == null && code == null) {
			return true;
		}
		
		GoodsCategory cat = null;
		if (name != null) {
			cat = this.goodsCategoryService.loadByName(name);
		} 

		if (code != null){
			cat = this.goodsCategoryService.loadByCode(code);
		}
		
		if (cat == null) {
			return true;
		}
		
		if (id != null) {
			return cat.getId().equals(id);
		}
		
		return false;
	}
	
	
	///////////////////////////////产品类别////////////////////////////////////////////////////
	
	@RequestMapping(value="categorytype-list")
	public ModelAndView categoryType(ModelAndView model){
		List<GoodsCategory> allCategory = this.goodsCategoryService.findWithChildren(0);
		model.addObject("allCategory", allCategory);
		model.setViewName("goods/categorytype-list");
		return model;
	}
	
	/**
	 * 加载单个数据的form页面
	 */
	@RequestMapping(value="categorytype-form")
	public String categoryform(Model model, @RequestParam(value="id",required=false) Integer id) {
		List<GoodsCategory> firstCategory = this.goodsCategoryService.findByParentId(0);
		if (id != null) {
			GoodsCategory cgy = this.goodsCategoryService.loadWithParents(id);
			model.addAttribute("category", cgy);
			// 修改标识
			model.addAttribute("updateFlag", "1");
		}else{
			model.addAttribute("category", new GoodsCategory());
		}
		model.addAttribute("firstCategory", firstCategory);
		return "goods/categorytype-form";
	}
	
	
	@RequestMapping(value="secondCategory")
	@ResponseBody
	public Map<String,Object> getChildCategory(@RequestParam(value="id") Integer cgyId){
		Map<String,Object> retMap = new HashMap<String,Object>();
		try{
			List<GoodsCategory> data = this.goodsCategoryService.findByParentId(cgyId);
			retMap.put("result",true);
			retMap.put("datas",data);
		}catch(Exception e){
			retMap.put("result",false);
		}
		return retMap;
	}
	
	
	@RequestMapping(value ="categorytype-add")
	public ModelAndView addCategory(GoodsCategory category,RedirectAttributes attr){
			add(category,attr);
		return new ModelAndView("redirect:/goodscategory/categorytype-list");
	}
	
	@RequestMapping(value ="categorytype-edit")
	public ModelAndView editCategory(GoodsCategory category,RedirectAttributes attr){
			update(category,attr);
			return new ModelAndView("redirect:/goodscategory/categorytype-list");
	}
	
	@RequestMapping(value ="categorytype-remove/{id}")
	public ModelAndView  removeCategory(@PathVariable(value="id") Integer id,RedirectAttributes attr){
		remove(id,attr);
		return new ModelAndView("redirect:/goodscategory/categorytype-list");
	}
	
	/**
	 * 根据父类查询直接子类列表
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping("list-json")
	@ResponseBody
	public List<GoodsCategory> findByParentId(@RequestParam(value="id") Integer parentId){
		return this.goodsCategoryService.findByParentId(parentId);
	}
}

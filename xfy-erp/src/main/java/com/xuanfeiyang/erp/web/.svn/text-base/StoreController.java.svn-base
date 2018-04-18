package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Dict;
import com.xuanfeiyang.erp.domain.Store;
import com.xuanfeiyang.erp.domain.StoreShelf;
import com.xuanfeiyang.erp.service.DictService;
import com.xuanfeiyang.erp.service.StoreService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/store")
public class StoreController {
	@Resource
	private StoreService storeService;
	@Resource
	private MessageSource messageSource;
	@Resource
	private DictService dictService;
	
	
	
	@RequestMapping(value = {"", "/"})
	public String storeMain(Model model){
		model.addAttribute("flag", 0);
		List<Store> list =  storeService.findStroe();
		model.addAttribute("store", list);
		
		return "goods/store";
	}
	
	/***
	 * 仓位列表
	 * @param model
	 * @return
	 */
	@RequestMapping("shelf")
	public String shelfList(Model model, 
			@RequestParam(value="id", required=false) Integer storeId){
		model.addAttribute("storeId",storeId);
		model.addAttribute("flag", 1);
		return "goods/store-shelf";
	}
	
	@RequestMapping("shelf-page.json")
	@ResponseBody
	public DataTableResponse<StoreShelf> shelfPageJson(Model model,
			@RequestParam(value="storeId", required=false) Integer storeId,
			@RequestBody DataTableRequest<StoreShelf> dtr){
		String keywords = dtr.getSearch() == null ? null : dtr.getSearch().getValue();
		StoreShelf params = dtr.getParams();
		if (params == null) {
			params = new StoreShelf();
		}
		params.setKeywords(keywords);
		params.setStoreId(storeId);
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<StoreShelf> page = this.storeService.findPage(pageRequest, params);
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	
	
	
	
	/**
	 * 添加仓库
	 * @param redirectAttr
	 * @param store
	 * @return
	 */
	@RequestMapping("addStore")
	public String addStore(RedirectAttributes redirectAttr,Store store){
		boolean bl = storeService.saveStore(store);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "添加成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "添加失败");
		}
		return "redirect:/store";
	}
	
	/***
	 * 添加仓位
	 * @param redirectAttr
	 * @param storeShelf
	 * @return
	 */
	@RequestMapping("addShelf")
	public String addShelf(RedirectAttributes redirectAttr,StoreShelf storeShelf,Model model){
		boolean bl = storeService.saveShelf(storeShelf);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "添加成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "添加失败");
		}
		
		redirectAttr.addAttribute("id", storeShelf.getStoreId());
		return "redirect:/store/shelf";
	}
	
	/**
	 * 修改仓库
	 * @param redirectAttr
	 * @param store
	 * @return
	 */
	@RequestMapping("updateStore")
	public String updateStore(RedirectAttributes redirectAttr,Store store){
		boolean bl = storeService.updateStore(store);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "修改成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "修改失败");
		}
		return "redirect:/store";
	}
	
	@RequestMapping("updateShelf")
	public String updateShelf(RedirectAttributes redirectAttr,StoreShelf storeShelf){
		boolean bl = storeService.updateShelf(storeShelf);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "修改成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "修改失败");
		}

		redirectAttr.addAttribute("id", storeShelf.getStoreId());
		return "redirect:/store/shelf";
	}
	

	/**
	 * 删除仓库
	 * @param redirectAttr
	 * @param id
	 * @return
	 */
	@RequestMapping("removeStore")
	public String removeStore(RedirectAttributes redirectAttr,Integer id){
		boolean bl = storeService.deleteStore(id);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "删除成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "删除失败");
		}
		return "redirect:/store";
	}
	
	/**
	 * 删除仓位
	 * @param redirectAttr
	 * @param id
	 * @return
	 */
	@RequestMapping("removeShelf")
	public String removeShelf(RedirectAttributes redirectAttr,Integer id){
		boolean bl = storeService.deleteShelf(id);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "删除成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "删除失败");
		}
		
		return "redirect:/store/shelf";
	}
	
	
	
	
	
	/**
	 * 仓库编辑form
	 * @return
	 */
	@RequestMapping("storeForm")
	public String storeForm(Model model, Integer id,
			@RequestParam(value="editType", required=false, defaultValue="0") Integer editType){
		List<Dict> list = dictService.findByType(202);
		
		if(editType == 0){ //表示添加
			model.addAttribute("subFlag",0);
			model.addAttribute("typeList", list);
			
		}else if(editType == 1){ //表示修改
			Store store = storeService.findStoreById(id);
			model.addAttribute("subFlag",1);
			model.addAttribute("storeAttr", store);
			model.addAttribute("typeList", list);
		}
		
		return "goods/store-form";
	}
	
	/**
	 * 仓位编辑form
	 * @return
	 */
	@RequestMapping("shelfForm")
	public String shelfForm(Model model,Integer id,
			@RequestParam(value="editType", required=false, defaultValue="0") Integer editType){
		List<Store> list =  storeService.findStroe();
		model.addAttribute("store", list);
		
		if(editType == 0){ //表示添加
			model.addAttribute("subFlag",0);
		}else if(editType == 1){ //表示修改
			model.addAttribute("subFlag",1);
			StoreShelf shelf = storeService.findShelfById(id);
			model.addAttribute("shelfAttr", shelf);
			
		}
		
		return "goods/store-shelf-form";
	}

	/**
	 * 返回仓位列表json
	 * @param storeId 仓库Id
	 * @return
	 */
	@RequestMapping("shelf-json")
	@ResponseBody
	public List<StoreShelf> shelfJson(@RequestParam("id") Integer storeId) {
		return this.storeService.findShelfByStore(storeId);
	}
	
	/**
	 * 根据编号检查仓库
	 * @param code
	 * @return
	 */
	@RequestMapping("checkStore")
	@ResponseBody
	public boolean chechStoreCode(String code,Integer id){
		Store store = null; 
		if(id == null){//添加
			store = this.storeService.findByStoreCode(code,id);
			if(store == null){
				return true; 
			}else{
				return false;
			}
			
		}else{//修改
			store = this.storeService.findByStoreCode(code,id);
			if(store != null){
				return true;
			}else{
				store = this.storeService.findByStoreCode(code,null);
				if(store == null){
					return true; 
				}else{
					return false;
				}
			}
			
			
		}
	}
	
	/***
	 * 根据编号检查仓位
	 * @param code
	 * @return
	 */
	@RequestMapping("checkShelf")
	@ResponseBody
	public boolean checkShelfCode(String code,Integer id){
		StoreShelf shelf = null;
		
		if(id == null){//添加
			shelf = this.storeService.findByShelfCode(code,id);
			if(shelf == null){
				return true;
			}else{
				return  false;
			}
			
		}else{//修改
			shelf = this.storeService.findByShelfCode(code,id);
			if(shelf != null){
				return true;
			}else{
				shelf = this.storeService.findByShelfCode(code,null);
				if(shelf == null){
					return true;
				}else{
					return  false;
				}
			}
			
		}
		
	}
	
	@RequestMapping("get-store-shelf-by-code.json")
	@ResponseBody
	public StoreShelf getStoreShelfByCode(@RequestParam("code") String code) {
		return this.storeService.findByShelfCode(code, null);
	}
}

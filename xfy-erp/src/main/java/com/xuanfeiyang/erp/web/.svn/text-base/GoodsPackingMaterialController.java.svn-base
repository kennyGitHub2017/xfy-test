package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.domain.GoodsPackingMaterial;
import com.xuanfeiyang.erp.service.GoodsPackingMaterialService;

@Controller
@RequestMapping("packingMaterial")
public class GoodsPackingMaterialController {
	@Resource
	private GoodsPackingMaterialService goodsPackingMaterialService;
	@Resource
	private MessageSource messageSource;
	
	
	/**
	 * 包装材料列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"", "/"})
	public String getList(Model model){
		
	List<GoodsPackingMaterial> list = goodsPackingMaterialService.find();
	model.addAttribute("packingMaterial",list);
	return "goods/packing-material";
	
	}
	
	/**
	 * 删除产品材料
	 * @param id
	 * @param redirectAttr
	 * @return
	 */
	@RequestMapping("remove")
	public String remove(Integer id,RedirectAttributes redirectAttr){
		boolean bl = goodsPackingMaterialService.delete(id);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "删除成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "删除失败");
		}
		return "redirect:/packingMaterial";
		
	}
	
	/**
	 * 添加产品材料
	 * @param packingMaterial
	 * @param redirectAttr
	 * @return
	 */
	@RequestMapping("/add")
	public String add(GoodsPackingMaterial packingMaterial,RedirectAttributes redirectAttr){
		boolean bl = goodsPackingMaterialService.add(packingMaterial);
		
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "添加成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "添加失败");
		}
		return "redirect:/packingMaterial";
	}
	
	/**
	 * 修改
	 * @param packingMaterial
	 * @param redirectAttr
	 * @return
	 */
	@RequestMapping("update")
	public String update(GoodsPackingMaterial packingMaterial,RedirectAttributes redirectAttr){
		boolean bl = goodsPackingMaterialService.modify(packingMaterial);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "修改成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "修改失败");
		}
		return "redirect:/packingMaterial";
	}
	
	/**
	 * FORM
	 * @param model
	 * @param id
	 * @param flag
	 * @return
	 */
	@RequestMapping("/form")
	public String form(Model model,
			@RequestParam(value="id", required=true, defaultValue="0") Integer id,
			@RequestParam(value="flag", required=true, defaultValue="0") Integer flag){
		if(flag != 0){//修改
			GoodsPackingMaterial packingMaterial = goodsPackingMaterialService.findById(id);
			model.addAttribute("material", packingMaterial);
			model.addAttribute("subFlag",1);
		}else{
			model.addAttribute("subFlag",0);
		}
		return "goods/packing-material-form";
	}
	
	/**
	 * 检查model
	 * @param model
	 * @return
	 */
	@RequestMapping("checkModel")
	@ResponseBody
	public boolean checkModel(@RequestParam(value="model") String model,Integer id){
		GoodsPackingMaterial result = null;
		if(id == null ){//添加
			 result = goodsPackingMaterialService.findByModel(model, id);
			 if(result != null){
				 return false;
			 }else{
				 return true;
			 }
		}else{ //修改
			result = goodsPackingMaterialService.findByModel(model,id);
			if (result != null) {
				return true;
			} else {
				result = goodsPackingMaterialService.findByModel(model, null);
				if (result == null) {
					return true;
				} else {
					return false;
				}
			}
		}

	}
	
	

}

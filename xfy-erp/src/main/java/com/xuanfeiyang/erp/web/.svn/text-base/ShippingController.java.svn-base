package com.xuanfeiyang.erp.web;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.ShippingFee2Dao;
import com.xuanfeiyang.erp.domain.Shipping;
import com.xuanfeiyang.erp.domain.ShippingAddressConfig;
import com.xuanfeiyang.erp.domain.ShippingFee;
import com.xuanfeiyang.erp.domain.ShippingFee2;
import com.xuanfeiyang.erp.service.ShippingFeeService;
import com.xuanfeiyang.erp.service.ShippingService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.WebHelper;


@Controller
@RequestMapping("/shipping")
public class ShippingController extends BaseController{
	
	@Resource
	private ShippingService shippingService;
	
	@Resource
	private MessageSource messageSource;
	
	@Resource
	private ShippingFeeService shippingFeeService;
	
	@Resource
	private ShippingFee2Dao shippingFee2Dao;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ShippingController.class);
	
	/**
	 * 获取发货方式
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"", "/"})
	public String getShipping(Model model){
		List<Shipping> list = shippingService.find();
		model.addAttribute("shippingAttr",list);
		return "system/shipping";
	}
	
	/**
	 * 添加
	 * @param redirectAttr
	 * @param shipping
	 * @return
	 */
	@RequestMapping("add")
	public String add(RedirectAttributes redirectAttr, Shipping shipping){

		boolean bl = shippingService.add(shipping);
		if(bl){
			return "redirect:/shipping";
		}else{
			redirectAttr.addFlashAttribute("successMessage", "添加失败");
		}
		return "system/shipping-edit";
	}
	
	/**
	 * 修改
	 * @param redirectAttr
	 * @param shipping
	 * @return
	 */
	@RequestMapping("update")
	public String update(RedirectAttributes redirectAttr,Shipping shipping){
		boolean bl = shippingService.modifyById(shipping);
		if(bl){
			return "redirect:/shipping";
		}else{
			redirectAttr.addFlashAttribute("successMessage", "修改失败");
		}
		return "system/shipping-edit";
	}
	
	/**
	 * 删除
	 * @param redirectAttr
	 * @param id
	 * @return
	 */
	@RequestMapping("remove")
	public String remove(RedirectAttributes redirectAttr,Integer id){
		boolean bl = shippingService.delete(id);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "删除成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "删除失败");
		}
		return "redirect:/shipping";
		
	}
	
	/**
	 * 打开编辑页面
	 * @return
	 */
	@RequestMapping("openEdit")
	public String openEdit(Model model,
			@RequestParam(value="flag", required=true, defaultValue="0") Integer flag,
			@RequestParam(value="id", required=true, defaultValue="0") Integer id){
			
		if(flag == 1){ //修改
			Shipping  shipping = shippingService.findById(id);
			model.addAttribute("shippingKey", shipping);
			model.addAttribute("subFlag",flag);
		}else{
			model.addAttribute("subFlag",flag);
		}
		
		return "system/shipping-edit";
	}
	
	
	
	////////////////////////////////////////////////////运费计算类型1
	
	@RequestMapping("shippingFee")
	public String shippingFee(Integer id,Model model){
		model.addAttribute("shipId", id);
		List<ShippingFee> shipFee = shippingFeeService.getByShipId(id);
		model.addAttribute("shipFee", shipFee);
		return "system/shipping-fee";	
	}
	
	@RequestMapping("editFee")
	public String editFee(Integer shipId,Model model,Integer id,@RequestParam(value="type",defaultValue="1")Integer type){
		if (type == 0) {
			model.addAttribute("shipId", shipId);
			model.addAttribute("subFlag", 0);//表示 添加
		} else {
			ShippingFee shippingFee = shippingFeeService.getById(id);
			model.addAttribute("shipId", shipId);
			model.addAttribute("fee", shippingFee);
			model.addAttribute("subFlag",1);
		}

		return "system/shipping-fee-form";
	}
	
	@RequestMapping("addShipFee")
	public String addShipFee(RedirectAttributes redirectAttr,ShippingFee shippingFee){
		if(shippingFee.getType() == 1){
			shippingFee.setRegion(shippingFee.getRegion2());
			shippingFee.setCountry(shippingFee.getCountry2());
			shippingFee.setDiscountRate(shippingFee.getDiscountRate2());
			shippingFee.setServiceFee(shippingFee.getServiceFee2());
		}
		
		boolean bl = shippingFeeService.insert(shippingFee);
		if(bl){
			redirectAttr.addFlashAttribute("successMessage", "添加成功");
		}else{
			redirectAttr.addFlashAttribute("successMessage", "添加失败");
		}
		redirectAttr.addAttribute("id", shippingFee.getShippingId());
		
		return "redirect:/shipping/shippingFee";
	}
	
	@RequestMapping("removeShipFee")
	public String removeShipFee(@RequestParam("id")Integer id,@RequestParam("shipId")Integer shipId,
			RedirectAttributes redirectAttr){
		shippingFeeService.delete(id);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		redirectAttr.addAttribute("id",shipId);
		return "redirect:/shipping/shippingFee";
		
	}
	
	
	/**
	 * 修改运费
	 * @param ShippingFee
	 * @return
	 */
	@RequestMapping("updateShipFee")
	public String updateShipFee(ShippingFee shippingFee,RedirectAttributes redirectAttr){
		logger.debug(shippingFee.getShippingId().toString());
		if(shippingFee.getType() == 1){
			
			shippingFee.setRegion(shippingFee.getRegion2());
			shippingFee.setCountry(shippingFee.getCountry2());
			shippingFee.setDiscountRate(shippingFee.getDiscountRate2());
			shippingFee.setServiceFee(shippingFee.getServiceFee2());
		}
		shippingFeeService.update(shippingFee);
		redirectAttr.addAttribute("id",shippingFee.getShippingId());
		
		return "redirect:/shipping/shippingFee";
	}
	
	@RequestMapping(value="/address", method=RequestMethod.GET)
	public String shippingAddressPage(Model model,
			@RequestParam("id") Integer id) {
		
		Shipping shipping = this.shippingService.findById(id);
		model.addAttribute("shippingName", shipping.getShippingName());
		
		ShippingAddressConfig address = this.shippingService.loadAddressConfig(id);
		if (address == null) {
			address = new ShippingAddressConfig();
			address.setShippingId(id);
		}
		model.addAttribute("address", address);
		return "system/shipping-address";
	}
	
	@RequestMapping(value="/address", method=RequestMethod.POST)
	public String saveShippingAddress(Model model, 
			RedirectAttributes redirect,
			ShippingAddressConfig address) {
		
		this.shippingService.saveAddressConfig(address);
		redirect.addFlashAttribute("successMessage", "g.tips.success");
		redirect.addAttribute("id", address.getShippingId());
		return "redirect:/shipping/address";
	}
	
	/***
	 * 运费国家查看面板
	 * @return
	 */
	@RequestMapping("shipFeeCountry-log")
	public String shipFeeCountry(@RequestParam("id") Integer id,Model model){
		ShippingFee shipfee = this.shippingFeeService.getById(id);
		model.addAttribute("countries", shipfee.getCountry());
		return "system/shipFee-log";
	}

	
	///////////////////////////////////////////////////////////////////////运费计算类型2
	
	
	/**
	 * dhl 运费页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("shippingFee2")
	public String shippingFee2(Integer id,Model model){
		model.addAttribute("shipId", id);
		return "system/shipping-fee2";	
	}
	
	/***
	 * 
	 * DHL运费参数列表
	 * @param dtr
	 * 
	 */
	@RequestMapping("page-json")
	@ResponseBody
	public DataTableResponse<ShippingFee2> pageJson(@RequestBody DataTableRequest<ShippingFee2> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<ShippingFee2> page = this.shippingFee2Dao.getPage(pageRequest, dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	/***
	 * 添加运费参数面板2
	 * @return
	 */
	@RequestMapping("editFee2")
	public String editFee2(Integer shipId,Model model){
		model.addAttribute("shipId", shipId);
		return "system/shipping-fee-form2";
	}
	
	/**
	 * 添加运费参数DHL
	 * @return
	 */
	@RequestMapping("addShipFee2")
	public String addShipFee2(Model model,
			Integer shippingId,String country,
			@RequestParam("weightFrom")List<BigDecimal> weightFrom,
			@RequestParam("weightTo")List<BigDecimal> weightTo,
			@RequestParam("shippFee")List<BigDecimal> shippFee){
			this.shippingFeeService.addShippFee2(weightFrom, weightTo, shippFee, shippingId, country);
			model.addAttribute("shipId", shippingId);
			return "system/shipping-fee2";
	}
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	@RequestMapping("removeShipFee2")
	public String removeShipFee2(Integer id){
		this.shippingFee2Dao.deleteById(id);
		return "system/shipping-fee2";
	}
	
	/***
	 * 修改运费参数面板
	 */
	@RequestMapping("updateShippFee2")
	public String updateShippFee2(Model model, Integer id){
		ShippingFee2 res = this.shippingFee2Dao.getById(id);
		model.addAttribute("shippFee", res);
		model.addAttribute("flag", 1);
		return "system/shipping-fee-form2";
	}
	
	/****
	 * 
	 * 修改DHL运费
	 * @return
	 */
	@RequestMapping("modifyShippFee2")
	public String modifyShippFee2(ShippingFee2 param,Model model){
		this.shippingFee2Dao.updateById(param);
		model.addAttribute("shipId", param.getShippingId());
		return "system/shipping-fee2";
	}
	
}






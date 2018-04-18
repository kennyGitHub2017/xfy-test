package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.param.GoodsParam;
import com.xuanfeiyang.erp.service.DictService;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
import com.xuanfeiyang.erp.service.SellerGoodsService;
import com.xuanfeiyang.erp.service.SellerService;
import com.xuanfeiyang.erp.service.StoreService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

/**
 * 卖家商品
 * 
 * @author Adam
 *
 */
@Controller
@RequestMapping("/seller-goods")
@SessionAttributes(App.SESSION_USER_KEY)
public class SellerGoodsController extends BaseController {
	
	@Resource
	private SellerGoodsService sellerGoodsService;

	@Resource
	private DictService dictService;
	
	@Resource
	private GoodsCategoryService goodsCategoryService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private StoreService storeService;
	
	@Resource
	private SellerService sellerService;
	
	/**
	 * 查看已收藏的商品列表
	 */
	@RequestMapping({"", "/"})
	public String index(Model model) {
		model.addAttribute("baseCategories", this.goodsCategoryService.findByParentId(0));
		model.addAttribute("users", this.userService.findAll());
		model.addAttribute("stores", this.storeService.findStroe());
		model.addAttribute("goodsStatus", this.dictService.findByType(102));	
		model.addAttribute("goodsParam", new GoodsParam());
		
		return "goods/seller-goods";
	}
	
	@RequestMapping("/page.json")
	@ResponseBody
	public DataTableResponse<Goods> page(@RequestBody DataTableRequest<GoodsParam> dtr,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser) {
		
		Integer sellerId = sessionUser.getUserId();
		
		GoodsParam params = dtr.getParams();
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<Goods> page = this.sellerGoodsService.findSellerGoods(pageRequest, params, sellerId);
		
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	/**
	 * 添加收藏
	 */
	@RequestMapping("/add")
	public String add(@RequestParam("goodsId") List<Integer> goodsIds, 
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser,
			RedirectAttributes redirect) {
		
		Integer sellerId = sessionUser.getUserId();
		this.sellerGoodsService.save(sellerId, goodsIds);
		
		redirect.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:/seller-goods";
	}

	/**
	 * 添加收藏
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("goodsId") List<Integer> goodsIds, 
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser,
			RedirectAttributes redirect) {
		
		Integer sellerId = sessionUser.getUserId();
		this.sellerGoodsService.delete(sellerId, goodsIds);
		
		redirect.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:/seller-goods";
	}
	
	/**
	 * 选择商品页面
	 */
	@RequestMapping("/select")
	public String list(Model model,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser) {
		
		model.addAttribute("baseCategories", this.goodsCategoryService.findByParentId(0));
		model.addAttribute("users", this.userService.findAll());
		model.addAttribute("stores", this.storeService.findStroe());
		model.addAttribute("goodsStatus", this.dictService.findByType(102));	
		model.addAttribute("goodsParam", new GoodsParam());
		
		Seller seller = this.sellerService.load(sessionUser.getSellerId());
		model.addAttribute("seller", seller);
		
		return "goods/seller-goods-select";
	}
}

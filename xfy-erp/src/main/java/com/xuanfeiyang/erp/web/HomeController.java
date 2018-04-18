package com.xuanfeiyang.erp.web;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.OrderStatistic;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.SellerDeposit;
import com.xuanfeiyang.erp.domain.SysInformation;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.GoodsParam;
import com.xuanfeiyang.erp.param.PlatformAccountParams;
import com.xuanfeiyang.erp.service.GoodsService;
import com.xuanfeiyang.erp.service.NoticeService;
import com.xuanfeiyang.erp.service.PlatformAccountService;
import com.xuanfeiyang.erp.service.SellerDepositService;
import com.xuanfeiyang.erp.service.SellerService;
import com.xuanfeiyang.erp.service.StatisticService;
import com.xuanfeiyang.erp.service.SysInformationService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.SessionUserPowersCache;

@Controller
@SessionAttributes(App.SESSION_USER_KEY)
public class HomeController extends BaseController {
	
	@Resource
	private SellerService sellerService;
	
	@Resource
	private StatisticService statisticService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private PlatformAccountService  platformAccountService;
	
	@Resource
	private SysInformationService sysInformationService;
	
	@Resource
	private SellerDepositService sellerDepositService;
	
	@Resource 
	private NoticeService noticeService;
	
	@Resource 
	private UserService userService;

	@RequestMapping("/")
	public String home(Model model, 
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser) {
		Integer userId = sessionUser.getUserId();
		UserInfo userInfo = this.userService.loadUserInfo(userId);
		model.addAttribute("userInfo", userInfo);
		
		Integer sellerId = sessionUser.getSellerId();
		Seller seller = this.sellerService.load(sellerId);
		model.addAttribute("seller", seller);
		
		OrderStatistic orderStat = this.statisticService.statOrderStatusCount(sellerId);
		model.addAttribute("orderStat", orderStat);
		
		PlatformAccountParams pa =  new PlatformAccountParams();
		pa.setSellerId(sellerId);
		List<PlatformAccount> pas = this.platformAccountService.find(pa);
		model.addAttribute("platformAccountSize", pas.size());
		
		Integer count = this.goodsService.getCountByParam(new GoodsParam() );
		model.addAttribute("goodsCount", count);
		
		GoodsParam param1 = new GoodsParam();
		param1.setStatus(3);
		Integer state3 = this.goodsService.getCountByParam(param1);
		model.addAttribute("promotionGoods", state3);
		
		GoodsParam param2 = new GoodsParam();
		param2.setHotGoods(50);
		Integer hotCount = this.goodsService.getCountByParam(param2);
		model.addAttribute("hotGoods", hotCount);
		
		GoodsParam param3 = new GoodsParam();
		param3.setNewGoodsKey(5);
		param3.setCreateTime(new Date());
		Integer newGoods = this.goodsService.getCountByParam(param3);
		model.addAttribute("newGoodsCount", newGoods);
		
		SellerDeposit deposit = this.sellerDepositService.load(sellerId);
		BigDecimal balance= new BigDecimal(0);
		if(deposit != null){
			balance = deposit.getDeposit();
		}
		model.addAttribute("balance", balance);
		
		// 已审核的非自营卖家 才查询7天平均消费和公告
		if (seller != null && seller.getStatus() == 2 && "0".equals(seller.getSelfFlag())) {
			BigDecimal avg7 = this.sellerDepositService.latestDaysAverageExpenses(sellerId, 7);
			model.addAttribute("avg7", avg7);
			
			model.addAttribute("notice", this.noticeService.getLatestOne());
		}
		
		SellerDeposit sd1 = new SellerDeposit();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		sd1.setCreatedTimeFrom(cal.getTime());
		sd1.setCreatedTimeTo(new Date());
		sd1.setSellerId(sellerId);
		
		Integer countLog = this.sellerDepositService.getSumAmountLog(sd1);
		if(countLog == null){
			countLog= 0;
		}
		model.addAttribute("sumAmountDay", countLog);
		
		SellerDeposit sd2 = new SellerDeposit();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(cal.get(Calendar.YEAR),cal2.get(Calendar.MONDAY), cal2.get(Calendar.DAY_OF_MONTH), 0,0,0);
		cal2.set(Calendar.DAY_OF_MONTH,cal2.getActualMinimum(Calendar.DAY_OF_MONTH));
		sd2.setCreatedTimeFrom(cal2.getTime());
		sd2.setCreatedTimeTo(new Date());
		sd2.setSellerId(sellerId);
		Integer countLog2 = this.sellerDepositService.getSumAmountLog(sd2);
		if(countLog2 == null){
			countLog2 = 0;
		}
		model.addAttribute("sumAmountMonth", countLog2);
		
		// 资讯类型
		int type = 0;
		
		// 检查是否有卖家审核权限，若有待审核的卖家，页面增加提示
		if (sellerId == App.DEFAULT_SELLER_ID) {
			if (this.checkSellerApprove(sessionUser.getUserId())) {
				int pendingSellers = this.sellerService.countPendingSeller();
				if (pendingSellers > 0) {
					model.addAttribute("pendingSellers", pendingSellers);
				}
			}
			
			type = 1;
		}
		
		// 查询最新资讯
		List<SysInformation> sysInfos = this.sysInformationService.findNew(12, type, null);
		model.addAttribute("sysInfos", sysInfos);
		
		return "index";
	}
	
	private boolean checkSellerApprove(Integer userId) {
		Map<String, Set<String>> powers = SessionUserPowersCache.getPowerMap(userId);
		if (powers == null || powers.isEmpty()) {
			return false;
		}
		
		Set<String> typePowers= powers.get("function");
		if (typePowers == null || typePowers.isEmpty()) {
			return false;
		}
		
		return typePowers.contains("seller_approve");
	}
}

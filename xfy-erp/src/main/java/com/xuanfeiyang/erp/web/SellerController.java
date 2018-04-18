package com.xuanfeiyang.erp.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.PayOrderDao;
import com.xuanfeiyang.erp.domain.PayOrder;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.SellerDeposit;
import com.xuanfeiyang.erp.domain.SellerDepositLog;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.domain.UserPower;
import com.xuanfeiyang.erp.param.PayOrderParams;
import com.xuanfeiyang.erp.param.PlatformAccountParams;
import com.xuanfeiyang.erp.param.SellerDepositParams;
import com.xuanfeiyang.erp.param.SellerParams;
import com.xuanfeiyang.erp.service.AlipayService;
import com.xuanfeiyang.erp.service.DepositBalanceShortageException;
import com.xuanfeiyang.erp.service.PayOrderService;
import com.xuanfeiyang.erp.service.PlatformAccountService;
import com.xuanfeiyang.erp.service.SellerDepositService;
import com.xuanfeiyang.erp.service.SellerService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.service.impl.AlipayServiceImpl;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/seller")
@SessionAttributes(App.SESSION_USER_KEY)
public class SellerController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(SellerController.class);
	
	@Resource
	private SellerService sellerService;
	
	@Resource 
	private SellerDepositService sellerDepositService;
	
	@Resource
	private MessageSource messageSource;
	
	@Resource
	private PlatformAccountService platformAccountService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private PayOrderService payOrderService;
	
	@Resource
	private AlipayService alipayService;
	
	@Resource
	private AlipayServiceImpl alipayServiceImpl;
	
	@Resource
	private PayOrderDao payOrderDao;
	
	@RequestMapping({"", "/"})
	public String index(Model model, 
			@RequestParam(value="status", defaultValue="1") Integer status) {
		model.addAttribute("status", status);
		return "system/seller";
	}
	
	@RequestMapping("/page-json")
	@ResponseBody
	public DataTableResponse<Seller> pageJson(
			@RequestParam(value="for", required=false) String forWhat, // 查询类型：deposit:查询保证金信息
			@RequestBody DataTableRequest<SellerParams> dtr) {
		
		String keywords = dtr.getSearch() == null ? null : dtr.getSearch().getValue();
		
		SellerParams params = dtr.getParams();
		params = params == null ? new SellerParams() : params;
		params.setKeywords(keywords);
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<Seller> page = null;
		
		// 查询保证金信息
		if ("deposit".equals(forWhat)) {
			page = this.sellerService.findPageWithDeposit(pageRequest, params);
		} else {
			page = this.sellerService.findPage(pageRequest, params);
		}

		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	/**
	 * 加载单个数据的form页面
	 */
	@RequestMapping("/form")
	public String form(Model model,
			@RequestParam(value = "id", required = false) Integer id) {
		Seller seller = null;
		if (id != null) {
			seller = this.sellerService.load(id);
			// 修改标识
			model.addAttribute("updateFlag", "1");
		} else {
			seller = new Seller();
		}

		model.addAttribute("seller", seller);

		return "system/seller-form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttr, Seller seller) {

		this.sellerService.save(seller);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");

		return "redirect:/seller";
	}
	
	@RequestMapping("/update")
	public String update(RedirectAttributes redirectAttr, Seller seller) {

		this.sellerService.update(seller);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");

		return "redirect:/seller";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer", defaultValue="/seller") String referer,
			@RequestParam(value="id", required=true) Integer id) {
		
		this.sellerService.delete(id);

		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = "/approve-page", method = RequestMethod.GET)
	public String approvePage(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="id", required=true) Integer sellerId) {
		Seller seller = this.sellerService.load(sellerId);
		model.addAttribute("seller", seller);
		
		return "system/seller-approve-form";
	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public String approve(Model model, RedirectAttributes redirectAttr,
			@RequestHeader("Referer") String referer,
			@RequestParam(value="id", required=true) Integer sellerId) {
		
		try {
			this.sellerService.approve(sellerId);
			redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		} catch (IllegalArgumentException e) {
			redirectAttr.addFlashAttribute("errorMessage", e.getMessage());
		}
		
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = "/unapprove", method = RequestMethod.POST)
	public String approve(Model model, RedirectAttributes redirectAttr,
			@RequestHeader("Referer") String referer,
			@RequestParam(value="id", required=true) Integer sellerId,
			@RequestParam(value="note", required=false) String note) {
		
		try {
			this.sellerService.unapprove(sellerId, note);
			redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		} catch (IllegalArgumentException e) {
			redirectAttr.addFlashAttribute("errorMessage", e.getMessage());
		}
		
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="id", required=true) Integer sellerId) {
		Seller seller = this.sellerService.load(sellerId);
		model.addAttribute("seller", seller);
		model.addAttribute("view", "1");
		return "system/seller-approve-form";
	}
	

	/**
	 * 买家保证金管理页面
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/deposit", method=RequestMethod.GET)
	public String depositPage(Model model, 
			@RequestParam(value="status", defaultValue="1") Integer status) {
		model.addAttribute("status", status);
		return "system/seller-deposit";
	}
	
	/**
	 * 查询单卖家保证金
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/deposit-form", method=RequestMethod.GET)
	public String depositForm(Model model, 
			@RequestParam(value="id", defaultValue="1") Integer id) {
		
		SellerDeposit sd = this.sellerDepositService.load(id);
		if (sd == null) {
			sd = new SellerDeposit();
			sd.setSellerId(id);
			sd.setDeposit(new BigDecimal(0));
		}
		model.addAttribute("sellerDeposit", sd);
		return "system/seller-deposit-form";
	}
	
	@RequestMapping(value="/deposit", method=RequestMethod.POST)
	public String saveDeposit(Model model, RedirectAttributes redirect,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser,
			@RequestParam(value = "action", defaultValue = "") String action,
			@RequestParam("id") Integer id,
			@RequestParam("amount") BigDecimal amount,
			@RequestParam("note") String note,
			@RequestParam("orderId") Integer orderId) {
		if(orderId != null){
			if(note != null){
				note =  "- " + note + ",订单号 :" + orderId;
			} else {
				note =  "订单号:" + orderId;
			}
		} else {
			note = (note == null ? "" : ("- " + note));
		}
		
		try {
			if ("1".equals(action)) {
				this.sellerDepositService.increaseDeposit(id, amount, ("客服转入" + note), sessionUser.getUserId());
			} else if ("0".equals(action)) {
				this.sellerDepositService.decreaseDeposit(id, amount, ("客服扣减" + note), sessionUser.getUserId());
			}
			
			redirect.addFlashAttribute("successMessage", "g.tips.success");
		} catch (DepositBalanceShortageException e) {
			redirect.addFlashAttribute("errorMessage", "余额不足");
		} catch (Exception e) {
			redirect.addFlashAttribute("errorMessage", "g.tips.error");
		}
		
		return "redirect:/seller/deposit";
	}
	
	@RequestMapping(value="/deposit-log", method=RequestMethod.GET)
	public String depositLogPage(Model model, 
			@RequestParam(value="id", defaultValue="1") Integer sellerId) {
		
		model.addAttribute("sellerId", sellerId);
		return "system/seller-deposit-log";
	}
	
	@RequestMapping(value="/deposit-log.json")
	@ResponseBody
	public DataTableResponse<SellerDepositLog> depositLogJson(Model model, 
			@RequestBody DataTableRequest<SellerDepositParams> dtr) {
		
		SellerDepositParams params = dtr.getParams();
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		
		Page<SellerDepositLog> logs = this.sellerDepositService.findLogPage(pageRequest, params);
		return WebHelper.assembleDataTableResponse(dtr, logs);
	}
	
	/**
	 * 更新 selfFlag 是否自营卖家
	 * @param ids
	 * @param openFlag
	 * @return
	 */
	@RequestMapping("/self-flag")
	public String updateSelfFlag(RedirectAttributes redirect,
			@RequestHeader("Referer") String referer,
			@RequestParam("id") Integer id, 
			@RequestParam("self") String openFlag) {
		
		try {
			this.sellerService.updateSelfFlag(id, openFlag);
			redirect.addFlashAttribute("successMessage", "g.tips.success");
		} catch (Exception e) {
			redirect.addFlashAttribute("errorMessage", "g.tips.error");
		}
		
		return "redirect:" + referer;
	}
	
	/**
	 * 更新 vipFlag 是否 VIP 卖家
	 * @param ids
	 * @param openFlag
	 * @return
	 */
	@RequestMapping("/vip-flag")
	public String updateVipFlag(RedirectAttributes redirect,
			@RequestHeader("Referer") String referer,
			@RequestParam("id") Integer id, 
			@RequestParam("vip") String vipFlag) {
		
		try {
			this.sellerService.updateVipFlag(id, vipFlag);
			redirect.addFlashAttribute("successMessage", "g.tips.success");
		} catch (Exception e) {
			redirect.addFlashAttribute("errorMessage", "g.tips.error");
		}
		
		return "redirect:" + referer;
	}
	
	/***
	 * 卖家-用户 权限页面
	 * @return
	 */
	@RequestMapping("sellerPower-page")
	public String SellerPower(PlatformAccountParams params,
			Integer userId1,Model model, 
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser){
		
		params.setSellerId(sessionUser.getSellerId());
		List<PlatformAccount> pas = platformAccountService.find(params);
		
		List<PlatformAccount> smtlist = new ArrayList<PlatformAccount>();
		List<PlatformAccount> ebayList = new ArrayList<PlatformAccount>();
		List<PlatformAccount> amzList = new ArrayList<PlatformAccount>();
		List<PlatformAccount> wishList = new ArrayList<PlatformAccount>();
		List<PlatformAccount> lazadaList = new ArrayList<PlatformAccount>();
		
		for (int i = 0; i < pas.size(); i++) {
			PlatformAccount thisPas = pas.get(i);
			
			if(thisPas.getPlatform().equals("smt")){
				PlatformAccount pa1 = new PlatformAccount();
				pa1.setId(thisPas.getId());
				pa1.setAccountName(thisPas.getAccountName());
				smtlist.add(pa1);
			}
			
			if(thisPas.getPlatform().equals("ebay")){
				PlatformAccount pa2 = new PlatformAccount();
				pa2.setId(thisPas.getId());
				pa2.setAccountName(thisPas.getAccountName());
				ebayList.add(pa2);
			}
			
			if(thisPas.getPlatform().equals("amazon")){
				PlatformAccount pa3 = new PlatformAccount();
				pa3.setId(thisPas.getId());
				pa3.setAccountName(thisPas.getAccountName());
				amzList.add(pa3);
			}
			
			if(thisPas.getPlatform().equals("wish")){
				PlatformAccount pa = new PlatformAccount();
				pa.setId(thisPas.getId());
				pa.setAccountName(thisPas.getAccountName());
				wishList.add(pa);
			}
			
			if(thisPas.getPlatform().equals("lazada")){
				PlatformAccount pa = new PlatformAccount();
				pa.setId(thisPas.getId());
				pa.setAccountName(thisPas.getAccountName());
				lazadaList.add(pa);
			}
		}

		List<UserPower> up = this.userService.getPlatformAccountPower(userId1);
		
		model.addAttribute("smtAttr", smtlist);
		model.addAttribute("ebayAttr", ebayList);
		model.addAttribute("amzAttr", amzList);
		model.addAttribute("wishAttr", wishList);
		model.addAttribute("lazadaAttr", lazadaList);
		model.addAttribute("userId",userId1);
		model.addAttribute("accountList",up);
		return "system/seller-power";
	
	}
	
	
	/**
	 * 设置账号权限
	 * 先删除已经设置的权限 - 重新添加
	 * @param attr
	 * @param userId
	 * @param accountId
	 * @return
	 */
	@RequestMapping("saveSellerPower")
	public String saveSellerPower(RedirectAttributes attr,
			@RequestParam("userId")Integer userId,
			@RequestParam("accountId")List<Integer> accountId){
		
		UserPower up = new UserPower();
		this.userService.deletePlatformAccountPower(userId);//删除权限
		
		if(accountId.size() > 0){
			
			for (int i = 0; i < accountId.size(); i++) {
				up.setAccountId(accountId.get(i));
				up.setUserId(userId);
				try {
					this.userService.savePlatformAccountPower(up);
					attr.addFlashAttribute("successMessage", "g.tips.success");
				} catch (Exception e) {
					attr.addFlashAttribute("errorMessage", "g.tips.error");
				}
			}
			
		}
		attr.addAttribute("userId1", userId);
		return "redirect:/seller/sellerPower-page";
	}
	
	// 卖家登录日志
	@RequestMapping(value="/login-log")
	public String loginLogPage() {
		return "system/seller-login-log";
	}
	
	/***
	 * 
	 * 导入卖家流水日志
	 * 
	 */
	@RequestMapping("import-log")
	@ResponseBody
	public void importDepositLog(@RequestParam("fileName")MultipartFile file){
		this.sellerDepositService.importDepositLog(file);
	}
	
	@RequestMapping("lock")
	public String lock(RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer", defaultValue="/seller") String referer,
			@RequestParam("id") Integer id) {
		this.sellerService.lock(id);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		return "redirect:" + referer;
	}

	@RequestMapping("unlock")
	public String unlock(RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer", defaultValue="/seller") String referer,
			@RequestParam("id") Integer id) {
		this.sellerService.unlock(id);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		return "redirect:" + referer;
	}
	
	
	/***
	 * 导出资金流水
	 * 
	 */
	@RequestMapping("exportDeposit")
	public void exportDeposit(SellerDepositParams params, HttpServletResponse response)throws Exception{
		
		Integer count = this.sellerDepositService.findDepositLogCount(params);
		
		if(count > App.getConfigInt("export.excel.maxnumber")){
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("error");
			response.getWriter().close();
		}else{
			
		List<SellerDepositLog> rows = this.sellerDepositService.findDepositLog(params);
		List<Column> cs = new ArrayList<Column>();
		
		cs.add(new Column("姓名","contacts"));
		cs.add(new Column("卖家账号","accountName"));
		cs.add(new Column("时间","createdTimeStr"));
		
		cs.add(new Column("类型","type",new FieldFormatter<Integer,SellerDepositLog>(){
			@Override
			public String format(Integer type, SellerDepositLog rowValue) {
				String typeStr = "";
				if(type == 1){
					typeStr = "收入";
				}else if(type == 0){
					typeStr = "支出";
				}
				return typeStr;
			}
		}));
		
		cs.add(new Column("订单号","orderId"));
		cs.add(new Column("产品成本","orderCost"));
		cs.add(new Column("运费(元)","orderShippingFee",new FieldFormatter<BigDecimal,SellerDepositLog>(){
			@Override
			public BigDecimal format(BigDecimal orderShippingFee, SellerDepositLog rowValue) {
				BigDecimal val = new BigDecimal(0);
				if(orderShippingFee != null){
					val = orderShippingFee.setScale(1, BigDecimal.ROUND_HALF_UP);
				}
				return val;
			}
		}));
		
		cs.add(new Column("订单处理费(元)","orderFee",new FieldFormatter<BigDecimal,SellerDepositLog>(){
			@Override
			public BigDecimal format(BigDecimal orderFee, SellerDepositLog rowValue) {
				BigDecimal val = new BigDecimal(0);
				if(orderFee != null){
					val = orderFee.setScale(1, BigDecimal.ROUND_HALF_UP);
				}
				return val;
			}
		}));
		
		cs.add(new Column("流水金额(元)","amount",new FieldFormatter<BigDecimal,SellerDepositLog>(){
			@Override
			public BigDecimal format(BigDecimal amount, SellerDepositLog rowValue) {
				BigDecimal val = amount.setScale(1, BigDecimal.ROUND_HALF_UP);
				return val;
			}
		}));
		
		cs.add(new Column("余额(元)","balanceAfter",new FieldFormatter<BigDecimal,SellerDepositLog>(){
			@Override
			public BigDecimal format(BigDecimal balanceAfter, SellerDepositLog rowValue) {
				BigDecimal val = balanceAfter.setScale(1, BigDecimal.ROUND_HALF_UP);
				return val;
			}
		}));
		
		cs.add(new Column("物流重量","logisticsWeight"));
		cs.add(new Column("物流运费","logisticsShipFee"));
		cs.add(new Column("备注","note"));
		
		String filename = "资金流水.xlsx";
		ExcelBuilder<SellerDepositLog> eb = new ExcelBuilder<SellerDepositLog>();
		eb.setFileType(FileType.XLSX);
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
	}
	
	@RequestMapping("depositLogExport-page")
	public String exportLogPage(){
		return "system/seller-depositLog-export";
	}
	
	/**
	 * 微信充值页面
	 * @return
	 */
	@RequestMapping("weixinPayPage")
	public String weixinPayPage(){
		return "system/weixin-pay";
	}
	
	/**
	 * 提交充值数据
	 * @param amount 充值金额
	 * @param body 商品描述
	 */
	@RequestMapping("weixinPay")
	@ResponseBody
	public String weixinPay(@RequestParam("body")String body,@RequestParam("amount")String amount,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser, HttpServletRequest request){
		
		Integer sellerId = sessionUser.getSellerId();
		try {
			body = new String(body.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		String ip = "172.16.0.188";
		String code = this.payOrderService.weixinPay(amount, body, ip,sellerId);
		return code;
	}
	
	
	/***
	 *微信支付后通知 
	 */
	@RequestMapping("weixinPayNotify")
	public void weixinPayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
		}
		
		outSteam.close();
		inStream.close();
		
		String info  = new String(outSteam.toByteArray(),"utf-8");
		String result = this.payOrderService.getNotifyInfo(info);
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();

	}
	
	
	/**
	 * 加载账户充值页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/account-recharge")
	public String recharge(Model model,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Integer userId = curUser.getUserId();
		UserInfo user = this.userService.loadUserInfo(userId);
		Integer sellerId = user.getSellerId();
		model.addAttribute("sellerId", sellerId);
		String out_trade_no = alipayServiceImpl.tradeNo();
		model.addAttribute("out_trade_no", out_trade_no);
		
		SellerDeposit sellerDeposit = this.sellerDepositService.load(sellerId);
		if(sellerDeposit != null) {
			model.addAttribute("sellerDeposit", sellerDeposit);
		} else {
			sellerDeposit =null;
		}
		return "system/account-recharge";
	}
	
	/**
	 * 提交充值数据,跳转到alipayapi.jsp页面
	 */
	@RequestMapping(value = "alipayapi", method = RequestMethod.POST)
	public String alipayapi(
			@RequestParam("total_fee") String total_fee,
			@RequestParam("subject") String subject,
			@RequestParam(value="sellerId",required=false) String sellerId,
			@RequestParam("out_trade_no") String out_trade_no) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("partner", App.getConfig("alipay.partner"));
		map.put("seller_email", App.getConfig("alipay.seller_email"));
		map.put("out_trade_no", out_trade_no);
		map.put("subject", subject);
		map.put("total_fee", total_fee);
		if(sellerId != null && !sellerId.equals("") && !sellerId.equals("1")) {
			alipayServiceImpl.addPayOrder(map, sellerId);
		}
		return "system/alipayapi";
	}
	
	/**
	 * 支付宝异步通知
	 * @throws IOException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "alipayNotify")
	public void notify_url(@RequestParam("out_trade_no") String tradeNo,
			@RequestParam("trade_status") String tradeStatus,
			@RequestParam("notify_time") String notifyTime,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		//获取支付宝POST过来反馈信息
		Map<String, String> notifyParams = new HashMap<String, String>();
		Map req = request.getParameterMap();
		for (Iterator iter = req.keySet().iterator(); iter.hasNext();) {
			String name = (String)iter.next();
			String[] values = (String[])req.get(name);
			String strVal = "";
			for (int i = 0; i< values.length; i++) {
				strVal = (i == values.length - 1) ? strVal + values[i]
						: strVal + values[i] +",";
			}
			notifyParams.put(name, strVal);
		}
		PayOrder po = this.payOrderDao.getByTradeNo(tradeNo);
		boolean verify_result = alipayServiceImpl.verify(notifyParams);
		logger.info("alipayNotify 验证返回 verify_result:{} ",verify_result);
		if (verify_result) {
			logger.info("alipayNotify tradeStatus:{}",tradeStatus);
			if(tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")){
				if(po != null && po.getStatus() == 0) {    //充值成功
					logger.info("alipayNotify 充值成功！");
					this.payOrderDao.updateByOutTradeNo(1, notifyTime, tradeNo);  // 更新支付状态
					this.alipayService.addDeposit(tradeNo); //资金流水增加记录
				}
				tradeStatus = "成功";
			}
			response.getWriter().print("success");
		} else {
			tradeStatus = "验证失败！";
			response.getWriter().print("fail");
		}
	}
	
	/**
	 * 支付宝返回页面
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "alipayReturn",method = RequestMethod.GET)
	public String return_url(@RequestParam("out_trade_no") String tradeNo,
			@RequestParam("trade_status") String tradeStatus,
			@RequestParam("total_fee") String totalFee,
			@RequestParam("notify_time") String notifyTime,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser session,
			HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		Map req = request.getParameterMap();
		for (Iterator iter = req.keySet().iterator(); iter.hasNext();) {
			String name = (String)iter.next();
			String[] values = (String[])req.get(name);
			String strVal = "";
			for (int i = 0; i< values.length; i++) {
				strVal = (i == values.length - 1) ? strVal + values[i]
						: strVal + values[i] +",";
			}
			strVal = new String(strVal.getBytes("ISO-8859-1"),"UTF-8");
			params.put(name, strVal);
		}
		Integer userId = session.getUserId();
		UserInfo user = this.userService.loadUserInfo(userId);
		Integer sellerId = user.getSellerId();
		PayOrder po = this.payOrderDao.getByTradeNo(tradeNo);
		boolean verify_result = alipayServiceImpl.verify(params);
		logger.info("alipayReturn 验证返回 verify_result:{} ",verify_result);
		if (verify_result) {
			logger.info("alipayReturn tradeStatus:{}",tradeStatus);
			if(tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")){
				if(po != null && po.getStatus() == 0) {    //充值成功
					logger.info("alipayReturn 充值成功！");
					this.payOrderDao.updateByOutTradeNo(1, notifyTime, tradeNo);  // 更新支付状态
					this.alipayService.addDeposit(tradeNo); //资金流水增加记录
				}
				SellerDeposit sellerDeposit = this.sellerDepositService.load(sellerId);
				model.addAttribute("sellerDeposit", sellerDeposit);
				tradeStatus = "成功";
			}
		} else {
			tradeStatus = "验证失败！";
		}
		
		model.addAttribute("tradeNo", tradeNo);
		model.addAttribute("totalFee", totalFee);
		model.addAttribute("notifyTime", notifyTime);
		model.addAttribute("tradeStatus", tradeStatus);
		return "system/alipay-return";
	}
	
	/**
	 * 账户充值查询页面
	 * @return
	 */
	@RequestMapping(value="/account-recharge-list")
	public String rechargeList() {
		return "system/accountRecharge-list";
	}
	
	/**
	 * 账户充值查询分页
	 * @param curUser
	 * @param dtr
	 * @return
	 */
	@RequestMapping(value="/payOrderPageJson")
	@ResponseBody
	public DataTableResponse<PayOrder> payOrderPageJson(
			@RequestBody DataTableRequest<PayOrderParams> dtr) {
		
		PayOrderParams params = dtr.getParams();
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		
		Page<PayOrder> logs = this.payOrderService.findPage(pageRequest, params);
		return WebHelper.assembleDataTableResponse(dtr, logs);
	}
	
	
	@RequestMapping("/exportData")
	public void exportData(PayOrderParams params,HttpServletResponse response) throws IOException {
		
		List<PayOrder> rows = this.payOrderService.export(params);
		if(rows.size() > App.getConfigInt("export.excel.maxnumber")) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("error");
			response.getWriter().close();
		} else {
			List<Column> cs = new ArrayList<Column>();
			cs.add(new Column("姓名","contacts"));
			cs.add(new Column("手机号","mobile"));
			cs.add(new Column("订单号","outTradeNo"));
			cs.add(new Column("充值金额","totalFee"));
			cs.add(new Column("充值描述","body"));
			cs.add(new Column("支付时间","createdTime","yyyy-mm-dd hh:mm:ss"));
			cs.add(new Column("支付状态","status",new FieldFormatter<Integer,PayOrder>(){
				public String format(Integer status, PayOrder rowValue){
					if (status == 1){
						return "支付成功";
					}
					else {
						return "支付失败";
					}
				 }
				}));
			
			cs.add(new Column("充值类型","type",new FieldFormatter<Integer, PayOrder>() {
				public String format(Integer type, PayOrder rowValue){
					if(type != null) {
						if (type == 1) {
							return "支付宝充值";
						} else {
							return "微信充值";
						}
					} else {
						return "微信充值";
					}
				}
			}));
			
			String filename = "充值记录.xlsx";
			ExcelBuilder<PayOrder> eb = new ExcelBuilder<PayOrder>();
			eb.setFileType(FileType.XLSX);
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
	}
	
	@RequestMapping("logistics-page")
	public String logisticsImportPage(){
		return "my/logistics-info-import";
	}
	

	@RequestMapping(value = "/importLogistics")
	public String importLogistics(HttpServletResponse response, Model model,RedirectAttributes redirectAttr,
			HttpServletRequest request, @RequestParam(value = "fileName") MultipartFile file) {

			try {
				List<Map<String, String>> list = this.sellerDepositService
						.importLogistics(file);
				if (list.size() == 0) {
					redirectAttr.addFlashAttribute("successMessage", "操作成功");
				} else {
					redirectAttr.addFlashAttribute("errorMessage", list);
				}

			} catch (Exception e) {
				redirectAttr.addFlashAttribute("errorMessage", e.getMessage());
			}

		return "redirect:/seller/logistics-page";
	}
	
	/**
	 * 代理商旗下卖家管理页面
	 * kenny
	 */
	@RequestMapping("agent-sellers")
	public String agentSellers(Model model, @ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,
			@RequestParam(value="status", defaultValue="1") Integer status) {
		model.addAttribute("status", status);
		model.addAttribute("agentUserId", curUser.getUserId());
		return "agent/agent-sellers";
	}
	
	/***
	 * 代理商旗下卖家资金流水页面
	 * kenny
	 */
	@RequestMapping("agentDeposit-log")
	public String depositLogPage(Model model,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		List<Seller> sellers = this.sellerService.findByAgent(curUser.getUserId());
		model.addAttribute("agentSellers", sellers);
		model.addAttribute("agentUserId", curUser.getUserId());
		return "agent/agent-deposit-log";
	}
	
}

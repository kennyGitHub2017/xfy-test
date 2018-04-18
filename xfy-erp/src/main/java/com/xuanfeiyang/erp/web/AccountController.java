
package com.xuanfeiyang.erp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xuanfeiyang.erp.App;

/**
 * 平台账号管理
 * @author bernard
 *
 */
@Controller
@RequestMapping("/account")
@SessionAttributes(App.SESSION_USER_KEY)
public class AccountController extends BaseController{
//	private static final Logger logger = LoggerFactory
//			.getLogger(AccountController.class);
//	@Resource
//	private AccountService accountService;
//	
//	@Resource
//	private MessageSource messageSource;
//	
//	/**
//	 * @param accountService
//	 *            要设置的 accountService
//	 */
//	public void setAccountService(AccountService accountService) {
//		this.accountService = accountService;
//	}
//	
//	/**
//	 * @param messageSource
//	 *            要设置的 messageSource
//	 */
//	public void setMessageSource(MessageSource messageSource) {
//		this.messageSource = messageSource;
//	}
//	
//	//拼截order by 字符串
//	private String orderString(DataTableRequest<?> dtrt){
//		StringBuffer orderBuff = new StringBuffer();
//		List<DataTableOrder> orderList = dtrt.getOrder();
//		List<DataTableColumn> columns = dtrt.getColumns();
//		for (int i=0;i<orderList.size();i++){
//			DataTableOrder order = orderList.get(i);
//			orderBuff.append(columns.get(order.getColumn()).getName()+" " + order.getDir()+",");
//		}
//		return orderBuff.substring(0,orderBuff.length()-1);
//	}
//
//
//	/******************** 亚马账号管理 ***************************************/
//	@RequestMapping(value = "am/list", method = RequestMethod.GET)
//	public ModelAndView amAccountList() {
//		ModelAndView mv = new ModelAndView();
////		AccountParam  param= new AccountParam();
////		param.setAccountType(AccountEnum.AMAZON.getValue());
////		List<Account> datas = accountService.find(param);
////		mv.addObject("accountList", datas);
//		mv.setViewName("account/am/list");
//		return mv;
//	}
//	
//	@RequestMapping("am/pageJson")
//	@ResponseBody
//	public Map<String, Object> amPageJson(@RequestBody DataTableRequest<?> dtrt,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser) {
//		logger.debug("=======> {}", dtrt); 
//		String keywords = dtrt.getSearch() == null ? null : dtrt.getSearch().getValue();
//		PageRequest pageRequest = WebHelper.assemblePageRequest(dtrt);
////		ParamMap<Object> paraMap = new ParamMap<Object>();
////		paraMap.put("pageRequest", pageRequest);
////		paraMap.put("accountType",AccountEnum.AMAZON.getValue());
////		paraMap.put("keywords", keywords);
////		String orderStr = orderString(dtrt);
////		paraMap.put("orderColumns",orderStr);
//		AccountParam para = new AccountParam();
//		para.setAccountType(AccountEnum.AMAZON.getValue());
//		para.setSellerId(curUser.getSellerId());
//		para.setKeywords(keywords);
//		para.setOrderColumns(orderString(dtrt));
//		Page<Account> page = this.accountService.findPage(pageRequest,para);
//		return WebHelper.assemblePageData(page, dtrt);
//	}
//
//	@RequestMapping(value = "am/addPage")
//	public ModelAndView amAccountAddPage() {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("account", new Account());
//		mv.setViewName("account/am/page");
//		return mv;
//	}
//
//	@RequestMapping(value = "am/editPage/{id}", method = RequestMethod.GET)
//	public ModelAndView amAccountEditPage(@PathVariable("id") Long id) {
//		ModelAndView mv = new ModelAndView();
//		Account account = accountService.get(id);
//		mv.addObject("account", account);
//		mv.setViewName("account/am/page");
//		return mv;
//	}
//	
//	
//
//	@RequestMapping(value = "am/add", method = RequestMethod.POST)
//	public ModelAndView amAccountAdd(Account account,RedirectAttributes attr,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser) {
//		account.setAccountType(AccountEnum.AMAZON.getValue());
//		account.setSellerId(curUser.getSellerId());
//		Long id = this.accountService.add(account);
//		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
//		logger.info("save new am account id:" + id);
//		return new ModelAndView("redirect:/account/am/list");
//	}
//
//	@RequestMapping(value = "am/edit", method = RequestMethod.POST)
//	public ModelAndView amAccountEdit(Account account,RedirectAttributes attr) {
//		account.setAccountType(AccountEnum.AMAZON.getValue());
//		this.accountService.modify(account);
//		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
//		return new ModelAndView("redirect:/account/am/list");
//	}
//
//	
//	@RequestMapping(value = "am/remove/{id}", method = RequestMethod.GET)
//	public ModelAndView amAccountRemove(@PathVariable("id") Long id,RedirectAttributes attr) {
//		if (id!=null){
//			this.accountService.remove(id);
//			logger.info("dele am account id:" + id);
//			attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
//		}
//		return new ModelAndView("redirect:/account/am/list");
//	}
//
//	/**************** 讯卖通账号管理 *******************************************/	
//	
//	/**
//	 * 获取smt授权地址
//	 * @id 为平台账户Id
//	 * @return
//	 * @throws Exception
//	 */
//	private String getSmtAuthPage(Long id) throws Exception{
//		String host = App.getConfig(Constants.SMT.AUTH_URL);		//获得授权链接地址
//		String appSecretKey = App.getConfig(Constants.SMT.APP_SECRET);
//		Map<String,String> params = new HashMap<String,String>();
//		params.put("site", "aliexpress");
//		params.put("client_id", App.getConfig(Constants.SMT.APP_KEY));
//		params.put("redirect_uri", App.getConfig(Constants.SMT.REDIRECT_URL));
//		if (null!=id){
//			params.put("id",String.valueOf(id));
//		}
//		return SmtAuthService.getClientAuthUrl(host, params, appSecretKey);
//	}
//	
//	/**
//	 * return the response  send the request of get the accesstoken by refreshToken  
//	 * 
//	 */
//	private String getUpdateSmtTokenResponse(String refreshToken) throws Exception{
//		String host =  App.getConfig(Constants.SMT.AUTH_REFRESHTOKEN);
//		Map<String,String> params = new HashMap<String,String>();
//		params.put("refresh_token", refreshToken);
//		params.put("client_id", App.getConfig(Constants.SMT.APP_KEY));
//		params.put("client_secret", App.getConfig(Constants.SMT.APP_SECRET));
//		String smtResponse = SmtAuthService.refreshToken(host, params);
//		return smtResponse;
//	}
//	
//	
//	
//	@RequestMapping(value = "smt/list", method = RequestMethod.GET)
//	public ModelAndView smtAccountList() {
//		ModelAndView mv = new ModelAndView();
//	//	Map<String, Object> para = new HashMap<String, Object>();
//	//	para.put("accountType", AccountEnum.SMT.getValue());
//	//	List<Account> datas = accountService.getAccountList(para);
//	//	mv.addObject("accountList", datas);
//		mv.setViewName("account/smt/list");
//		return mv;
//	}
//	
//	
//	@RequestMapping("smt/pageJson")
//	@ResponseBody
//	public Map<String, Object> smtPageJson(@RequestBody DataTableRequest<?> dtrt,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser) {
//		logger.debug("=======> {}", dtrt); 
//		String keywords = dtrt.getSearch() == null ? null : dtrt.getSearch().getValue();
//		PageRequest pageRequest = WebHelper.assemblePageRequest(dtrt);
////		ParamMap<Object> paraMap = new ParamMap<Object>();
////		paraMap.put("pageRequest", pageRequest);
////		paraMap.put("accountType",AccountEnum.SMT.getValue());
////		paraMap.put("keywords", keywords);
////		String orderStr = orderString(dtrt);
////		paraMap.put("orderColumns",orderStr);
//		AccountParam para = new AccountParam();
//		para.setAccountType(AccountEnum.SMT.getValue());
//		para.setSellerId(curUser.getSellerId());
//		para.setKeywords(keywords);
//		para.setOrderColumns(orderString(dtrt));
//		Page<Account> page = this.accountService.findPage(pageRequest,para);
//		return WebHelper.assemblePageData(page, dtrt);
//	}
//	
//
//	@RequestMapping(value = "smt/addPage")
//	public String smtAccountAddPage() {
//		return "account/smt/add";
//	}
//
//	@RequestMapping(value = "smt/editPage/{id}", method = RequestMethod.GET)
//	public ModelAndView smtAccountEditPage(@PathVariable("id") Long id) {
//		ModelAndView mv = new ModelAndView();
//		Account account = accountService.get(id);
//		mv.addObject("account", account);
//		mv.setViewName("account/smt/edit");
//		return mv;
//	}
//
//	
//	@RequestMapping(value = "smt/login", method = RequestMethod.POST)
//	public ModelAndView smtAccountLogin(@RequestParam String accountName,@RequestParam Long id, ModelAndView model) throws Exception{
//		String authUrl = getSmtAuthPage(id);
//		model.addObject("authUrl", authUrl);
//		model.setViewName("account/smt/add");
//		return model;
//	}
//	
//
//	@RequestMapping(value = "smt/edit", method = RequestMethod.POST)
//	public ModelAndView smtAccountEdit(Account account,RedirectAttributes attr) {
//		this.accountService.modify(account);
//		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
//		return new ModelAndView("redirect:/account/smt/list");
//	}
//	
//	/**
//	 * 
//	 * 如果该账号refreshToken值存在且未过期，直接换取accessToken
//	 * refreshToken过期/错误/不存在,则跳转至smt用户授权页面
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "smt/updateToken/{id}")
//	public ModelAndView smtAccountUpdateToken(@PathVariable("id") Long id,ModelAndView model,RedirectAttributes attr) throws Exception{
//			Account account = this.accountService.get(id);
//			//如果此时该账号被意外删除或refreshToken不存在,则跳转至授权页面
//			if (account==null || !StringUtil.isNotBlank(account.getSmtRefreshToken())){
//				String authUrl = getSmtAuthPage(id);
//				model.addObject("authUrl", authUrl);
//				model.addObject("id", account!=null?String.valueOf(id):"");
//				model.setViewName("account/smt/add");
//				return model;
//			}
//			else if(StringUtil.isNotBlank(account.getSmtRefreshToken())){
//				String smtResponse = getUpdateSmtTokenResponse(account.getSmtRefreshToken());
//				//如果refreshToken过有效期或用户取消授权后则获取不到新的token,速卖通将返回错误信息
//				ObjectMapper mapper = new ObjectMapper();
//			 	JsonNode jsonode =  mapper.readTree(smtResponse);
//			 	//如果返回正确数据,重新设置smt访问令牌,token有效时间 ,否则清空错误或无效的refreshToken
//			 	if (null!=jsonode.get("error_code") || null!=jsonode.get("error")){
//			 		account.setSmtRefreshToken("");
//			 		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("account.smt.updateToken.fail", null, null));
//			 		logger.error("Method[smtAccountUpdateToken] get access token failure by refreshToken:".concat(jsonode.get("error_description").textValue()));
//			 	}else{
//			 		account.setSmtAccessToken(jsonode.get("access_token").textValue());	
//			 		account.setSmtExpireTime(new Date(System.currentTimeMillis()+Long.valueOf(jsonode.get("expires_in").textValue())*1000));
//			 		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("account.smt.updateToken.success", null, null));
//			 	}
//			 	this.accountService.modify(account);
//				return new ModelAndView("redirect:/account/smt/list");
//		}
//		return null;
//	}
//
//	@RequestMapping(value = "smt/remove/{id}", method = RequestMethod.GET)
//	public ModelAndView smtAccountRemove(@PathVariable("id") Long id,RedirectAttributes attr) {
//		if (id!=null){
//			this.accountService.remove(id);
//			logger.info("dele am account id:" + id);
//			attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
//		}
//		logger.info("dele smt account id:" + id);
//		return new ModelAndView("redirect:/account/smt/list");
//	}
//	
//	
//	
//	/**************** 易贝账号管理 *******************************************/
//	
//	
//	/**
//	 * 获取ebay  sessionId,此值需要在Ebay授权url中传递过去
//	 * @return
//	 * @throws Exception
//	 */
//	private String getEbaySessionId() throws Exception{
//		String host = App.getConfig(Constants.EBAY.APP_SESSIONID_URL);		//get sessionId api地址
//		String devId = App.getConfig(Constants.EBAY.APP_DEVID);		//ebay dev id	
//		String appKey = App.getConfig(Constants.EBAY.APP_ID);		// ebay app id 	
//		String appSecretKey = App.getConfig(Constants.EBAY.APP_SECRET);		//ebay app key
//		String ruNames = App.getConfig(Constants.EBAY.APP_RUNAMES);		//ebay ruNames
//		String xmldata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><GetSessionIDRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\"><RuName>" + ruNames +"</RuName></GetSessionIDRequest>";
//		Map<String,String> headerMap = new HashMap<String,String>();
//		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER__APPNAME, appKey);
//		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_DEVNAME, devId);
//		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_APPCERT, appSecretKey);
//		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_CLEVEL, "727");
//		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_SITEID, "201");
//		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_CALLNAME, "GetSessionID");
//		headerMap.put("Content-Type", "text/xml");
//		headerMap.put("Content-Length", String.valueOf(xmldata.getBytes().length));
//		String retServer = EbayAuthService.getSessionId(host,xmldata,headerMap);
//		Map<String,String> map = XmlUtil.readStringXmlOut(retServer);
//		if (!map.containsKey("SessionID")){
//			return null;
//		}
//		return map.get("SessionID");
//	}
//	
//	
//	/**
//	 * 获取ebay授权地址
//	 * @id 为平台账户Id传至ebay,ebay回调的时候将传回此值
//	 * @return
//	 * @throws Exception
//	 */
//	private String getEbayAuthPage(Long id,HttpServletRequest req) throws Exception{
//		String host = App.getConfig(Constants.EBAY.APP_AUTH_URL);		//获得授权链接地址
//		Map<String,String> params = new HashMap<String,String>();
//		String sessionId = getEbaySessionId();
//		req.getSession().setAttribute("SessID",sessionId);
//		params.put("RuName", App.getConfig(Constants.EBAY.APP_RUNAMES));
//		params.put("SessID",  java.net.URLEncoder.encode(sessionId, "UTF-8"));
//		if (null!=id){
//			params.put("ruparams",String.valueOf(id));
//		}
//		return EbayAuthService.getClientAuthUrl(host, params);
//	}
//	
//	@RequestMapping(value = "ebay/list", method = RequestMethod.GET)
//	public ModelAndView ebayAccountList() {
//		ModelAndView mv = new ModelAndView();
//	//	Map<String, Object> para = new HashMap<String, Object>();
//	//	para.put("accountType", AccountEnum.SMT.getValue());
//	//	List<Account> datas = accountService.getAccountList(para);
//	//	mv.addObject("accountList", datas);
//		mv.setViewName("account/ebay/list");
//		return mv;
//	}
//	
//	
//	@RequestMapping("ebay/pageJson")
//	@ResponseBody
//	public Map<String, Object> ebayPageJson(@RequestBody DataTableRequest<?> dtrt,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
//		logger.debug("=======> {}", dtrt); 
//		String keywords = dtrt.getSearch() == null ? null : dtrt.getSearch().getValue();
//		PageRequest pageRequest = WebHelper.assemblePageRequest(dtrt);
////		ParamMap<Object> paraMap = new ParamMap<Object>();
////		paraMap.put("pageRequest",pageRequest);
////		paraMap.put("accountType",AccountEnum.EBAY.getValue());
////		paraMap.put("keywords", keywords);
////		String orderStr = orderString(dtrt);
////		paraMap.put("orderColumns",orderStr);
//		AccountParam para = new AccountParam();
//		para.setAccountType(AccountEnum.EBAY.getValue());
//		para.setSellerId(curUser.getSellerId());
//		para.setKeywords(keywords);
//		para.setOrderColumns(orderString(dtrt));
//		Page<Account> page = this.accountService.findPage(pageRequest,para);
//		return WebHelper.assemblePageData(page, dtrt);
//	}
//	
//
//	@RequestMapping(value = "ebay/addPage")
//	public String ebayAccountAddPage(Long id,ModelAndView model) {
//		if (null!=id){
//			model.addObject("id", id);
//		}
//		return "account/ebay/add";
//	}
//	
//	@RequestMapping(value = "ebay/editPage/{id}", method = RequestMethod.GET)
//	public ModelAndView ebayAccountEditPage(@PathVariable("id") Long id) {
//		ModelAndView mv = new ModelAndView();
//		Account account = accountService.get(id);
//		mv.addObject("account", account);
//		mv.setViewName("account/ebay/edit");
//		return mv;
//	}
//	
//	@RequestMapping(value = "ebay/login", method = RequestMethod.POST)
//	public ModelAndView ebayAccountLogin(@RequestParam String accountName,@RequestParam Long id, ModelAndView model,HttpServletRequest req) throws Exception{
//		String authUrl = getEbayAuthPage(id,req);
//		model.addObject("authUrl", authUrl);
//		model.addObject("accountName", accountName);
//		model.setViewName("account/ebay/add");
//		return model;
//	}
//	
//	/**
//	 * eub设置页面
//	 * @param id
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "ebay/eubauth/{id}", method = RequestMethod.GET)
//	public ModelAndView eubAuthPage(@PathVariable("id") Long id,ModelAndView model){
//		EubConfig config  = this.accountService.getEubConfigByActId(id,1);			
//		if (null==config){
//			config = new EubConfig();
//			config.setAccountId(id);
//			config.setEubType((byte)1);
//		}
//		model.addObject("eubconfig",config);
//		model.setViewName("account/ebay/eub");
//		return model;
//	}
//	
//	/**
//	 * eub线下设置页面
//	 * @param id
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "ebay/offeubauth/{id}", method = RequestMethod.GET)
//	public ModelAndView offlineEubAuthPage(@PathVariable("id") Long id,ModelAndView model){
//		EubConfig config  = this.accountService.getEubConfigByActId(id,0);
//		if (null==config){
//			config = new EubConfig();
//			config.setAccountId(id);
//			config.setEubType((byte)0);
//		}
//		model.addObject("eubconfig",config);
//		model.setViewName("account/ebay/offeub");
//		return model;
//	}
//	
//	/**
//	 * 保存eub设置
//	 * @param eubConfig
//	 * @param attr
//	 * @return
//	 */
//	@RequestMapping(value = "ebay/eubconfig", method = RequestMethod.POST)
//	public ModelAndView eubAuthConfig(EubConfig eubConfig,RedirectAttributes attr){
//		//删除原有的eub设置，重新做insert操作
//		if (null!=eubConfig.getId()){
//			this.accountService.removeEubConfig(eubConfig.getId());
//		}
//		this.accountService.addEubConfig(eubConfig);
//		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
//		return new ModelAndView("redirect:/account/ebay/list");
//	}
//	
//	/**
//	 * 
//	 * 
//	 * 则跳转至ebay用户授权页面
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "ebay/updateToken/{id}", method = RequestMethod.POST)
//	public String ebayAccountUpdateToken(@PathVariable("id") Long id,ModelAndView model){
//		return ebayAccountAddPage(id,model);
//	}
//
//	@RequestMapping(value = "ebay/edit", method = RequestMethod.POST)
//	public ModelAndView ebayAccountEdit(Account account,RedirectAttributes attr) {
//		this.accountService.modify(account);
//		attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
//		return new ModelAndView("redirect:/account/ebay/list");
//	}
//	
//	@RequestMapping(value = "ebay/remove/{id}", method = RequestMethod.GET)
//	public ModelAndView ebayAccountRemove(@PathVariable("id") Long id,RedirectAttributes attr) {
//		if (id!=null){
//			this.accountService.remove(id);
//			logger.info("dele ebay account id:" + id);
//			attr.addFlashAttribute("successMessage", this.messageSource.getMessage("g.tips.success", null, null));
//		}
//		logger.info("dele ebay account id:" + id);
//		return new ModelAndView("redirect:/account/ebay/list");
//	}
	
}

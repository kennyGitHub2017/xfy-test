package com.xuanfeiyang.erp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xuanfeiyang.erp.App;

@Controller
@RequestMapping("/callback")
@SessionAttributes(App.SESSION_USER_KEY)
public class CallbackController {
//	private static final Logger logger = LoggerFactory
//			.getLogger(CallbackController.class);
//	@Resource
//	private AccountService accountService;
//
//	/**
//	 * 速卖通回调
//	 * 
//	 * @param code
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "smt")
//	public ModelAndView smtGetToken(@RequestParam String code,
//			@RequestParam(required = false) String id,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser) {
//		Account account = null;
//		ObjectMapper mapper = new ObjectMapper();
//		ModelAndView model = new ModelAndView();
//		try {
//			logger.info(" Method[smtCallback] code is:" + code);
//			String host = App.getConfig(Constants.SMT.AUTH_TOKEN);
//			Map<String, String> params = new HashMap<String, String>();
//			String appKey = App.getConfig(Constants.SMT.APP_KEY);
//			String appSecret = App.getConfig(Constants.SMT.APP_SECRET);
//			params.put("code", code);
//			params.put("client_id", appKey);
//			params.put("client_secret", appSecret);
//			params.put("redirect_uri", App.getConfig(
//					Constants.SMT.REDIRECT_URL));
//			String smtResponse = SmtAuthService.getToken(host, params, true);
//			JsonNode jsonode = mapper.readTree(smtResponse);
//			if (null != jsonode.get("error_code")
//					|| null != jsonode.get("error")) {
//				throw new Exception(
//						"Method[smtCallback] get access token failure by code:"
//								.concat(jsonode.get("error_description")
//										.textValue()));
//			}
//			String refreshToken = jsonode.get("refresh_token").textValue(); // smt刷新令牌
//			String accessToken = jsonode.get("access_token").textValue(); // smt访问令牌
//			String smtAccount = jsonode.get("resource_owner").textValue(); // resource_owner为登录id
//			String expires_in = jsonode.get("expires_in").textValue(); // token有效时间,时间单位:second
//			// 更新smt账户相关api参数
//			if (StringUtil.isNotBlank(id)) {
//				account = this.accountService.get(Long.valueOf(id));
//				account.setSmtAccessToken(accessToken);
//				account.setSmtRefreshToken(refreshToken);
//				account.setSmtExpireTime(new Date(System.currentTimeMillis()
//						+ Long.valueOf(expires_in) * 1000));
//				this.accountService.modify(account);
//			} else {
//				account = new Account();
//				account.setAccountName(smtAccount);
//				account.setAccountType(AccountEnum.SMT.getValue());
//				account.setStatus(AccountStatusEnum.NORMAL.getValue());
//				account.setSmtAccessToken(accessToken);
//				account.setSmtRefreshToken(refreshToken);
//				account.setSmtAppkey(appKey);
//				account.setSellerId(curUser.getSellerId());
//				account.setSmtSecret(appSecret);
//				account.setSmtExpireTime(new Date(System.currentTimeMillis()
//						+ Long.valueOf(expires_in) * 1000));
//				Long newid = this.accountService.add(account);
//				logger.info("Method[smtCallback] save new smt account id:"
//						+ newid);
//				model.addObject("accontName", account.getAccountName());
//			}
//		} catch (Exception e) {
//			logger.error(" Method[smtCallback] exception occured:" + e);
//		}
//		model.setViewName("account/smt/authInfo");
//		return model;
//	}
//	
//	
//	/**
//	 * ebayServer 回调方法
//	 * 回调授权成功通过返回code获取accesstoken
//	 * 根据id判断来新增smt账户还是更新smt账户
//	 * @param code smt返回临时token
//	 * @param id  smt自传参数原样返回
//	 */
//	@RequestMapping(value = "ebay")
//	public  ModelAndView  ebayAuth(@RequestParam String isAuthSuccessful,@RequestParam(required=false) String ruparams,
//			@RequestParam String username,HttpServletRequest req,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
//		Account account = null;
//		ModelAndView model = new ModelAndView();
//		try{
//			//ebay账户成功授权
//			if (StringUtil.isNotBlank(isAuthSuccessful) && "true".equals(isAuthSuccessful)){
//				Map<String,String> headerMap = new HashMap<String,String>();
//				String host = App.getConfig(Constants.EBAY.APP_TOKEN);		//gettoken api地址
//				String devId = App.getConfig(Constants.EBAY.APP_DEVID);		//ebay dev id	
//				String appKey = App.getConfig(Constants.EBAY.APP_ID);		// ebay app id
//				String appSecretKey = App.getConfig(Constants.EBAY.APP_SECRET);		//ebay app key
//				headerMap = new HashMap<String,String>();
//				headerMap.put(Constants.EBAY.GETSESSION_APIHEADER__APPNAME, appKey);
//				headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_DEVNAME, devId);
//				headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_APPCERT, appSecretKey);
//				headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_CLEVEL, Constants.EBAY.API_VERSION);
//				headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_SITEID, "201");
//				headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_CALLNAME, "FetchToken");
//				String sessionId = (String)req.getSession().getAttribute("SessID");
//				req.getSession().removeAttribute("SessID");
//				StringBuffer xmlBuf = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><FetchTokenRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\"><Version>613</Version><SessionID>"+sessionId+"</SessionID></FetchTokenRequest>");
//				headerMap.put("Content-Type", "text/xml");
//				headerMap.put("Content-Length", String.valueOf(xmlBuf.toString().getBytes().length));
//				String ebayTokenResponse = EbayAuthService.getToken(host, xmlBuf.toString(), headerMap);
//			 	Map<String,String> nodeMap =  XmlUtil.readStringXmlOut(ebayTokenResponse);
//			 	if (null!=nodeMap.get("Errors")){
//			 		throw new Exception("Method[ebayCallback] get access token failure by code:".concat(nodeMap.get("Errors")));
//			 	}
//			 	String ebayToken = nodeMap.get("eBayAuthToken");	//ebayToken
//			 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//			 	String expires_in = nodeMap.get("HardExpirationTime");	//token expiratetime
//			 	//更新ebay账户相关api参数
//			 	if (StringUtil.isNotBlank(ruparams)){
//			 		account = this.accountService.get(Long.valueOf(ruparams));
//			 		account.setEbayToken(ebayToken);
//			 		account.setEbayExpireTime(sdf.parse(expires_in));
//					this.accountService.modify(account);
//			 	}else{
//			 		account = new Account();
//			 		account.setAccountName(username);
//			 		account.setAccountType(AccountEnum.EBAY.getValue());
//			 		account.setStatus(AccountStatusEnum.NORMAL.getValue());
//			 		account.setSellerId(curUser.getSellerId());
//			 		account.setEbayToken(ebayToken);
//			 		account.setEbayExpireTime(sdf.parse(expires_in));
//					Long newid = this.accountService.add(account);
//					logger.info("Method[ebayCallback] save new ebay account id:" + newid);
//			 	}
//			 	model.addObject("authed",true);
//			}
//			//ebay账户未授权或授权失败
//			else{
//				model.addObject("authed",false);	
//			}
//			model.addObject("accountName",username);
//		}catch(Exception e){
//			logger.error(" Method[ebayCallback] exception occured:" + e);
//		}
//		model.setViewName("account/ebay/authInfo");
//		return model;
//	}
//
//	public void setAccountService(AccountService accountService) {
//		this.accountService = accountService;
//	}
}

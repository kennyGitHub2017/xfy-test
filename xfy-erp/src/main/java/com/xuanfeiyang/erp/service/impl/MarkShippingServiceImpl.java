package com.xuanfeiyang.erp.service.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.Resource;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.FeedSubmissionInfo;
import com.amazonaws.mws.model.IdList;
import com.amazonaws.mws.model.SubmitFeedRequest;
import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonaws.mws.model.SubmitFeedResult;
import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.eBayAccount;
import com.ebay.sdk.call.CompleteSaleCall;
import com.ebay.soap.eBLBaseComponents.ErrorType;
import com.ebay.soap.eBLBaseComponents.ShipmentTrackingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShipmentType;
import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.OrderDao;
import com.xuanfeiyang.erp.dao.OrderItemDao;
import com.xuanfeiyang.erp.dao.OrderPackageDao;
import com.xuanfeiyang.erp.dao.PlatformAccountDao;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderMarkShipping;
import com.xuanfeiyang.erp.domain.OrderMarkShippingResult;
import com.xuanfeiyang.erp.service.AccountService;
import com.xuanfeiyang.erp.service.MarkShippingService;
import com.xuanfeiyang.erp.util.CommonUtil;
import com.xuanfeiyang.erp.util.HttpRequest;
import com.xuanfeiyang.erp.util.SmtAuthService;

@Service
public class MarkShippingServiceImpl implements MarkShippingService {

	private static Logger logger = LoggerFactory.getLogger(MarkShippingServiceImpl.class);

	@Resource
	private AccountService accountService;

	@Resource
	private OrderDao orderDao;
	
	@Resource
	private OrderPackageDao orderPackageDao;
	
	@Resource
	private OrderItemDao orderItemDao;
	
	@Resource
	private PlatformAccountDao platformAccountDao;

	@Override
	public List<OrderMarkShippingResult> mark(List<Integer> orderIds) {

		List<OrderMarkShipping> orders = this.getOrders(orderIds);
		return this.markOrders(orders);
	}
	
	private List<OrderMarkShipping> getOrders(List<Integer> orderIds) {
		return this.orderDao.findOrderMarkShipping(orderIds);
	}
	
	@Override
	public List<OrderMarkShippingResult> markPackages(List<String> packageIds) {
		List<Integer> orderIds = this.orderPackageDao.findOrderIdsByIds(packageIds);
		return this.mark(orderIds);
	}

	/**
	 * 标发参数中的所有订单; smt,ebay订单逐单标发，amazon订单按账号分组后，按组标发。
	 * 
	 * @param orders 待标发订单列表
	 */
	@Override
	public List<OrderMarkShippingResult> markOrders(List<OrderMarkShipping> orders) {
		
		if (orders == null || orders.isEmpty()) {
			return null;
		}
		
		List<OrderMarkShippingResult> results = new ArrayList<>(orders.size());
		
		// 需要把 Amazon单独处理，因为 amazon支持批量发送
		// amazon 订单按账号分组，每个账号一批同步
		Map<Integer, List<OrderMarkShipping>> amazonOrders = new HashMap<>();
		List<OrderMarkShipping> ebayOrders = new ArrayList<>();
		List<OrderMarkShipping> smtOrders = new ArrayList<>();
		List<OrderMarkShipping> wishOrders = new ArrayList<>();
		List<OrderMarkShipping> lazadaOrders = new ArrayList<>();
		
		int amCounter = 0;
		for (OrderMarkShipping order : orders) {
			// 订单必须有平台信息 、物流公司、跟踪号、账号信息 才能标发
			try {
				this.validateOrder(order);
			} catch (Exception e) {
				logger.error("标发订单 {} 出错：{}", order.getId(), e.getMessage());
				
				OrderMarkShippingResult r = new OrderMarkShippingResult();
				r.setOrderId(order.getId());
				r.setSuccess(false);
				r.setMessage(e.getMessage());
				results.add(r);
				
				continue;
			}
			
			// 处理合并订单
			List<OrderMarkShipping> originOrders = this.getOriginOrdersFromCombineOrder(order);
			
			String platform = order.getOrderPlatform();
			if (CollectionUtils.isEmpty(originOrders)) {
				if ("amazon".equals(platform)) {
					Integer accountId = order.getAccountId();
					if (! amazonOrders.containsKey(accountId)) {
						amazonOrders.put(accountId, new ArrayList<OrderMarkShipping>());
					}
					amazonOrders.get(accountId).add(order);
					amCounter++;
				} else if ("ebay".equals(platform)) {
					ebayOrders.add(order);
				} else if ("smt".equals(platform)) {
					smtOrders.add(order);
				} else if ("wish".equals(platform)) {
					wishOrders.add(order);
				}  else if ("lazada".equals(platform)) {
					lazadaOrders.add(order);
				}
			} else {
				if ("amazon".equals(platform)) {
					for (OrderMarkShipping originOrder : originOrders) {
						Integer accountId = originOrder.getAccountId();
						if (! amazonOrders.containsKey(accountId)) {
							amazonOrders.put(accountId, new ArrayList<OrderMarkShipping>());
						}
						amazonOrders.get(accountId).add(originOrder);
						amCounter++;
					}
				} else if ("ebay".equals(platform)) {
					ebayOrders.addAll(originOrders);
				} else if ("smt".equals(platform)) {
					smtOrders.addAll(originOrders);
				} else if ("wish".equals(platform)) {
					wishOrders.addAll(originOrders);
				}else if ("lazada".equals(platform)) {
					lazadaOrders.addAll(originOrders);
				}
			}
		}
		
		logger.info("本次需标发订单数，Amazon:{}, eBay: {}, smt: {}, wish: {},lazada:{}", 
				amCounter, ebayOrders.size(), smtOrders.size(), wishOrders.size(),lazadaOrders.size());
		
		results.addAll(this.markEbayOrders(ebayOrders));
		results.addAll(this.markSmtOrders(smtOrders));
		results.addAll(this.markAmazonOrders(amazonOrders));
		results.addAll(this.markWishOrders(wishOrders));
		results.addAll(this.markLazadaOrders(lazadaOrders));
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("标发结果：");
			for (OrderMarkShippingResult result : results) {
				logger.debug(result.toString());
			}
		}
		
		return results;
	}
	
	private List<OrderMarkShipping> getOriginOrdersFromCombineOrder(OrderMarkShipping order) {
		List<OrderMarkShipping> originOrders = null;
		String orderSn = order.getOrderSn();
		if (orderSn.contains(",")) {
			String[] originOrderSns = orderSn.split(",");
			originOrders = this.orderDao.findOrderMarkShippingByOrderSn(Arrays.asList(originOrderSns));
			if (originOrders != null && originOrders.size() > 0) {
				for (OrderMarkShipping tmp : originOrders) {
					tmp.setTrackNumber(order.getTrackNumber());
					tmp.setShippingName(order.getShippingName());
					tmp.setEbayShippingName(order.getEbayShippingName());
					tmp.setSmtShippingName(order.getSmtShippingName());
					tmp.setWishShippingName(order.getWishShippingName());
				}
			}
		}
		
		return originOrders;
	}

	private void validateOrder(OrderMarkShipping order) {
		Preconditions.checkArgument(
				StringUtils.isNotBlank(order.getOrderPlatform()), "缺少平台信息");
		Preconditions.checkArgument(
				"ebay".equals(order.getOrderPlatform())
						|| "amazon".equals(order.getOrderPlatform())
						|| "smt".equals(order.getOrderPlatform())
						|| "wish".equals(order.getOrderPlatform())
						|| "lazada".equals(order.getOrderPlatform()),
				"平台信息错误，可选值:ebay,amazon,smt，实际：" + order.getOrderPlatform());
		Preconditions.checkArgument(
				StringUtils.isNotBlank(order.getShippingName()), "缺少发货方式");
		Preconditions.checkArgument(
				StringUtils.isNotBlank(order.getTrackNumber()), "缺少跟踪号");
		Preconditions.checkNotNull(order.getAccountId(), "缺少平台账户信息");
	}

	// 标发 eBay 订单
	private List<OrderMarkShippingResult> markEbayOrders(List<OrderMarkShipping> ebayOrders) {
		
		List<OrderMarkShippingResult> results = new ArrayList<>(ebayOrders.size());
		
		// 逐单处理 ebay 订单
		for (OrderMarkShipping order : ebayOrders) {
			logger.info("开始 eBay 标发...");
			OrderMarkShippingResult result = this.submitToEbay(order);
			logger.info("eBay 标发结果: {}", result.isSuccess());

			// 标发成功后，更新标发状态
			if (result.isSuccess()) {
				this.updateOrderIsSend(order.getId());
			}
			
			results.add(result);
		}
		
		return results;
	}
	
	// 标发 smt 订单
	private List<OrderMarkShippingResult> markSmtOrders(List<OrderMarkShipping> smtOrders) {
		List<OrderMarkShippingResult> results = new ArrayList<>(smtOrders.size());
		
		// 逐单处理 smt 订单
		for (OrderMarkShipping order : smtOrders) {

			logger.info("开始 smt 标发: {}", order.getOrderSn());
			OrderMarkShippingResult result = null;
			try {
				result = this.submitToSmt(order);
			} catch (RuntimeException e) {
				result = new OrderMarkShippingResult();
				result.setSuccess(false);
				result.setOrderId(order.getId());
				result.setMessage(e.getMessage());
			}
			logger.info("smt 标发结果: {}", result.isSuccess());

			// 标发成功后，更新标发状态
			if (result.isSuccess()) {
				this.updateOrderIsSend(order.getId());
			}
			
			results.add(result);
		}
		
		return results;
	}
	
	// 分账户处理 Amazon 订单
	private List<OrderMarkShippingResult> markAmazonOrders(Map<Integer, List<OrderMarkShipping>> amazonOrders) {
		List<OrderMarkShippingResult> results = new ArrayList<>(amazonOrders.size());
		
		for (Map.Entry<Integer, List<OrderMarkShipping>> entry : amazonOrders.entrySet()) {
			results.addAll(this.markAmazonOrders(entry.getKey(), entry.getValue()));
		}
		
		return results;
	}
	
	// 处理 Amazon 订单
	private List<OrderMarkShippingResult> markAmazonOrders(Integer account, 
			List<OrderMarkShipping> amazonOrders) {
		
		List<OrderMarkShippingResult> results = new ArrayList<>(amazonOrders.size());
		
		// 处理 amazon
		logger.info("开始 Amazon 标发: {} ...", account);
		boolean success = this.submitToAmazon(amazonOrders);
		logger.info("Amazon 标发结果： {}", success);
		
		for (OrderMarkShipping order : amazonOrders) {
			if (success) {
				this.updateOrderIsSend(order.getId());
			}
			
			OrderMarkShippingResult r = new OrderMarkShippingResult();
			r.setOrderId(order.getId());
			r.setSuccess(success);
			results.add(r);
		}
		
		return results;
	}
	
	private List<OrderMarkShippingResult> markWishOrders(
			List<OrderMarkShipping> wishOrders) { 
		
		List<OrderMarkShippingResult> results = new ArrayList<>(wishOrders.size());
		
		// 逐单处理 wish 订单
		for (OrderMarkShipping order : wishOrders) {

			logger.info("开始 wish 标发: {}", order.getOrderSn());
			OrderMarkShippingResult result = this.submitToWish(order);
			logger.info("wish 标发结果: {}", result.isSuccess());

			// 标发成功后，更新标发状态
			if (result.isSuccess()) {
				this.updateOrderIsSend(order.getId());
			}
			
			results.add(result);
		}
		
		return results;
	}
	
	
	private List<OrderMarkShippingResult> markLazadaOrders(List<OrderMarkShipping> lazadaOrders){
		List<OrderMarkShippingResult> results = new ArrayList<>(lazadaOrders.size());
		
		// 逐单处理 lazada 订单
		for (OrderMarkShipping order : lazadaOrders) {

			logger.info("开始 lazada 标发: {}", order.getOrderSn());
			OrderMarkShippingResult result = this.submitToLazada(order);
			logger.info("lazada 标发结果: {}", result.isSuccess());

			// 标发成功后，更新标发状态
			if (result.isSuccess()) {
				this.updateOrderIsSend(order.getId());
			}
			
			results.add(result);
		}
		
		return results;
	}

	private void updateOrderIsSend(Integer orderId) {
		Order tmp = new Order();
		tmp.setId(orderId);
		tmp.setIsSend((short) 1);
		tmp.setShippedTime(new Date());
		this.orderDao.update(tmp);
	}
	
	
	private OrderMarkShippingResult submitToWish(OrderMarkShipping order) {
		OrderMarkShippingResult result = new OrderMarkShippingResult();
		result.setOrderId(order.getId());
		
		String wishApiUrl = "https://merchant.wish.com/api/v2/order/fulfill-one";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(wishApiUrl);

		List<NameValuePair> params = new ArrayList<NameValuePair>(3);
		params.add(new BasicNameValuePair("id", order.getOrderSn()));
		
		String shippingName = StringUtils.trimToNull(order.getWishShippingName());
		if (shippingName == null) {
			shippingName = StringUtils.trimToNull(order.getShippingName());
		}
		params.add(new BasicNameValuePair("tracking_provider", shippingName));
		params.add(new BasicNameValuePair("tracking_number", order.getTrackNumber()));
		params.add(new BasicNameValuePair("access_token", order.getWishKey()));
		
		HttpEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(params, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			
		}
		post.setEntity( entity );
		
		HttpResponse httpResponse = null;
		String response = null;
		try {
			httpResponse = httpClient.execute(post);
			HttpEntity re = httpResponse.getEntity();
			response = re != null ? EntityUtils.toString(re) : null;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		logger.info("wish response: {}, 状态码: {}, 订单ID: {}", response, statusCode, order.getId());
		
		if (statusCode == 200) {
			result.setSuccess(true);
		} else if (response != null) {
			result.setSuccess(false);
			@SuppressWarnings("serial")
			Map<String, Object> resMap = new Gson().fromJson(response, new TypeToken<Map<String, Object>>(){}.getType());
			String message = (resMap.get("code") != null ? ((Double)resMap.get("code")).intValue() : "") 
					+ " - " + ObjectUtils.toString(resMap.get("message"));
			result.setMessage(message);
		}
		
		return result;
	}
	

	
	private OrderMarkShippingResult submitToLazada(OrderMarkShipping order) {
		OrderMarkShippingResult result = new OrderMarkShippingResult();
		result.setOrderId(order.getId());
		
		String lazadaApiUrl = "https://sellercenter-api.lazada.com.my/?";
		String timeStamp = getCurrentTimestamp();
		
		List<OrderItem> items = this.orderItemDao.queryByOrder(order.getId()); 
		List<String> itemIds = new ArrayList<String>();
		for(OrderItem item:items){
			itemIds.add(item.getItemId());
		}
		
		Map<String,String> param = new HashMap<String,String>();
		param.put("Version", "1.0");
		param.put("Action","SetStatusToReadyToShip");
		param.put("Format","JSON");
		param.put("OrderItemIds",itemIds.toString());
		param.put("DeliveryType", "dropship");
		param.put("ShippingProvider", getLazadaShipmentProviders(order));
		param.put("TrackingNumber", order.getTrackNumber());
		param.put("Timestamp",timeStamp);
		param.put("UserID",order.getLazadaApiuserid());
	
		String response = null;
		try {
			HttpRequest req = new HttpRequest();
			req.setConnectTimeout(20000);
			req.setSocketTimeout(20000);
			String paraStr = CommonUtil.getParameterEncoderUrl(param);
			String signatrue = this.lazadaSignature(param, order.getLazadaApikey());
			paraStr += "&Signature="+signatrue;
			lazadaApiUrl = lazadaApiUrl + paraStr;
			
			logger.debug(" submitToLazada req str:" + lazadaApiUrl);
			response = req.doPostWithString(lazadaApiUrl, null, null);
		} catch (Exception e) {
			logger.error("标发出错", e);
		}
		result.setSuccess(true);
		if (response != null) {
			@SuppressWarnings("serial")
			Map<String, Object> resMap = new Gson().fromJson(response, new TypeToken<Map<String, Object>>(){}.getType());
			if (resMap.get("ErrorResponse") != null){
				result.setSuccess(false);
				@SuppressWarnings("unchecked")
				Map<String,Object> errorResponse = 	(Map<String,Object>)resMap.get("ErrorResponse");
				@SuppressWarnings("unchecked")
				Map<String,Object> headerResponse = (Map<String,Object>)errorResponse.get("Head");
				String message = (headerResponse.get("ErrorCode") != null ? ((Double)headerResponse.get("ErrorCode")).intValue() : "") 
						+ " - " + ObjectUtils.toString(resMap.get("ErrorMessage"));
				result.setMessage(message);
			}
		}
		return result;
	}
	
	/**
	 * 获取lazada运输方式
	 * @param order
	 * @return
	 */
	private String getLazadaShipmentProviders(OrderMarkShipping order){
		String lazadaApiUrl = "https://sellercenter-api.lazada.com.my/?";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String timeStamp = getCurrentTimestamp();
		
		Map<String,String> param = new HashMap<String,String>();
		param.put("Action","GetShipmentProviders");
		param.put("Timestamp",timeStamp);
		param.put("UserID",order.getLazadaApiuserid());
		param.put("Version", "1.0");
		param.put("Format","JSON");
		
		String response = null;
		String shippName ="";
		try {
			HttpRequest req = new HttpRequest();
			req.setConnectTimeout(20000);
			req.setSocketTimeout(20000);
			String paraStr = CommonUtil.getParameterEncoderUrl(param);
			String signatrue = this.lazadaSignature(param, order.getLazadaApikey());
			paraStr += "&Signature="+signatrue;
			lazadaApiUrl = lazadaApiUrl + paraStr;
			logger.debug(" getLazadaShipmentProviders req str:" + lazadaApiUrl);
			response = req.doPostWithString(lazadaApiUrl, null, null);
			if (response != null) {
				@SuppressWarnings("serial")
				Map<String, Object> resMap = new Gson().fromJson(response, new TypeToken<Map<String, Object>>(){}.getType());
				if (resMap.get("SuccessResponse") != null){
					@SuppressWarnings("unchecked")
					Map<String, Object> successResponse = (Map<String, Object>)resMap.get("SuccessResponse");
					@SuppressWarnings("unchecked")
					Map<String, Object> body  = (Map<String, Object>)successResponse.get("Body");
					@SuppressWarnings("unchecked")
					Map<String, Object> ShipmentProviders = (Map<String, Object>)body.get("ShipmentProviders");
					@SuppressWarnings("unchecked")
					List<Map<String,Object>> ShipmentProviderList = (List<Map<String,Object>>)ShipmentProviders.get("ShipmentProvider");
					for(Map<String,Object> ShipmentProvider:ShipmentProviderList){
						shippName = (String)ShipmentProvider.get("Name");
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		return shippName;
	}
	
	private OrderMarkShippingResult submitToEbay(OrderMarkShipping order) {

		OrderMarkShippingResult result = new OrderMarkShippingResult();
		result.setOrderId(order.getId());
		
		String orderId = order.getOrderSn().replace("copy_", "");
		// 如果是合并订单, 只标发其中一个
		if (orderId.indexOf(",") != -1) {
			String[] tmp = orderId.split(",");
			orderId = tmp[0];
		}
		
		String[] tmp = orderId.split("-");
		String itemId = tmp[0];
		String transactionId = tmp.length == 2 ? tmp[1] : "0";

		// String version = "705";
		String apiServerUrl = App.getConfig("ebay.api.server.url");
		String devId = order.getEbayDevid();
		String appId = order.getEbayAppid();
		String certId = order.getEbayCertid();
		
		String ebayToken = order.getEbayToken();

		ApiContext apiContext = new ApiContext();
		// apiContext.setWSDLVersion(version);
		apiContext.setApiServerUrl(apiServerUrl);

		ApiAccount apiAccount = new ApiAccount();
		apiAccount.setApplication(appId);
		apiAccount.setDeveloper(devId);
		apiAccount.setCertificate(certId);

		ApiCredential cred = apiContext.getApiCredential();
		cred.seteBayToken(ebayToken);
		cred.setApiAccount(apiAccount);

		eBayAccount ebayAccount = null;
		cred.seteBayAccount(ebayAccount);

		CompleteSaleCall call = new CompleteSaleCall(apiContext);
		call.setItemID(itemId);
		call.setTransactionID(transactionId);
		call.setOrderID(orderId);
		call.setPaid(true);
		call.setShipped(true);

		ShipmentTrackingDetailsType stdt = new ShipmentTrackingDetailsType();
		// 如果是平邮的发货方式,则不需要发送跟踪号.
		if (! order.getShippingName().contains("平邮")) {
			stdt.setShippingCarrierUsed(order.getEbayShippingName()); // 物流商
			stdt.setShipmentTrackingNumber(order.getTrackNumber()); // 跟踪号
		}

		ShipmentType shipment = new ShipmentType();
		shipment.setShipmentTrackingDetails(new ShipmentTrackingDetailsType[] { stdt });

		call.setShipment(shipment);

		try {
			call.completeSale();
			result.setSuccess(true);
		} catch (ApiException e) {
			logger.info("errors: {}", Arrays.toString(e.getErrors()));
			ErrorType[] errors = e.getErrors();
			String message = "";
			for (ErrorType error : errors) {
				logger.info("errors: {} - {} - {}", error.getErrorCode(), error.getShortMessage(),
						error.getLongMessage());
				message += error.getLongMessage() + ", ";
			}
			
			result.setSuccess(false);
			result.setMessage(message);
		} catch (SdkException e) {
			logger.error("ebay标发出错: {}", e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("ebay标发出错.", e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}

		return result;
	}

	private OrderMarkShippingResult submitToSmt(OrderMarkShipping order) {
		
		checkArgument(StringUtils.isNotBlank(order.getSmtAccessToken()), "缺少 access_token");
		checkArgument(StringUtils.isNotBlank(order.getSmtAppKey()), "缺少 app_key");
		checkArgument(StringUtils.isNotBlank(order.getSmtAppSecret()), "缺少 app_secret");
		checkArgument(StringUtils.isNotBlank(order.getSmtRefreshToken()), "缺少 refresh_token");
		
		// 首先使用 access_token 标发一次，若没有授权，则根据refreshToken请求 access_token 再标发一次
		
		OrderMarkShippingResult r = new OrderMarkShippingResult();
		r.setOrderId(order.getId());
		
		String newAccessToken = null;
		
		Map<String, Object> res = this.submitSmtRequest(order);
		if (res != null) {
			if ("401".equals(res.get("error_code"))) {
				logger.info("smt 标发需 refresh token： {}", order.getAccountId());
				newAccessToken = this.refreshToken(order.getSmtRefreshToken(), 
						order.getSmtAppKey(), order.getSmtAppSecret());
				if (newAccessToken == null) {
					logger.info("smt 标发需重新授权： {}", order.getAccountId());
					r.setSuccess(false);
					r.setMessage("smt 标发需重新授权");
					return r;
				}
				
				order.setSmtAccessToken(newAccessToken);
				res = this.submitSmtRequest(order);
			}
			
			r.setSuccess(res.containsKey("success"));
			if (! r.isSuccess()) {
				String message = (String) res.get("exception");
				if (message == null) {
					message = (String) res.get("error_message");
				}
				if (message == null) {
					message = (String) res.get("message");
				}
				r.setMessage(message);
			}
			
		} else {
			r.setSuccess(false);
			r.setMessage("未知错误");
		}
		
		if (StringUtils.isNotBlank(newAccessToken)) {
			this.platformAccountDao.updateSmtAccessToken(order.getAccountId(), newAccessToken);
		}
		
		return r;
	}
	
	/**
	 * 提交标发请求
	 * @param order
	 * @return
	 */
	@SuppressWarnings("serial")
	private Map<String, Object> submitSmtRequest(OrderMarkShipping order) {
		String url = String.format(App.getConfig("smt.open.url.pattern"), "api.sellerShipment", order.getSmtAppKey());

		String shippingName = order.getSmtShippingName();
		if (StringUtils.isBlank(shippingName)) {
			shippingName = order.getShippingName();
		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("serviceName", shippingName);
		params.put("logisticsNo", order.getTrackNumber());
		params.put("description", "");
		params.put("sendType", "all");
		params.put("outRef", order.getOrderSn().replace("copy_", ""));
		params.put("trackingWebsite", shippingName.startsWith("Other") ? "http://www.17track.net/" : "");
		params.put("access_token", order.getSmtAccessToken());

		String signature = this.smtSignature(url, params, order.getSmtAppSecret());
		params.put("_aop_signature", signature);
		
		url = CommonUtil.getWholeUrl(url, params);
		logger.info("request => {}", url);
		
		HttpRequest req = new HttpRequest();
		req.setConnectTimeout(20000);
		req.setSocketTimeout(20000);
		String response = null;
		try {
			response = req.doPostWithString(url, null, null);
		} catch (Exception e) {
			logger.error("标发出错", e);
		}

		logger.info("response => {}", response);

		return new Gson().fromJson(response, new TypeToken<Map<String, Object>>() {}.getType());
		
//		// 标发成功
//		if (map.containsKey("success")) {
//			return "success";
//		} 
//		// 未授权 或 token 过期
//		else if (map.containsKey("error_code") && "401".equals(map.get("error_code"))) {
//			return "401";
//		}
//		
//		return null;
	}

	private String smtSignature(String url, Map<String, String> params,
			String smtAppSecret) {
		String urlPath = url.replace("http://gw.api.alibaba.com/openapi/", "");		
		return CommonUtil.signatureWithParamsAndUrlPath(urlPath, params, smtAppSecret);
	}
	
	private String lazadaSignature(Map<String, String> params,
			String appSecret) throws UnsupportedEncodingException {
		return CommonUtil.signatureWithParams(params, appSecret);
	}

	// 根据 refresh Token 换取 access
	@SuppressWarnings("serial")
	private String refreshToken(String refreshToken, String appKey, String secretKey) {

		Map<String, String> params = new HashMap<>();
		params.put("client_id", appKey);
		params.put("client_secret", secretKey);
		params.put("refresh_token", refreshToken);

		String url = String.format(App.getConfig("smt.oauth2.token.url.pattern"), appKey);

		String response = null;
		try {
			response = SmtAuthService.refreshToken(url, params);
		} catch (Exception e) {
			logger.error("smt refresh token 出错", e);
		}

		if (response == null) {
			return null;
		}

		logger.info("smt 标发 refresh token 返回: {}", response);

		Map<String, Object> map = new Gson().fromJson(response, new TypeToken<Map<String, Object>>() {}.getType());
		return (String) map.get("access_token");
	}

	// 亚马逊标发
	private boolean submitToAmazon(List<OrderMarkShipping> orders) {
		
		if (orders == null || orders.isEmpty()) {
			return false;
		}
		
		OrderMarkShipping order = orders.get(0);
		AmazonAuthConfig conf = this.getAmazonAuthConfig(order);

		if (conf == null) {
			return false;
		}

		final String accessKeyId = conf.getAccessKey();
		final String secretAccessKey = conf.getSecretKey();

		final String appName = conf.getAppName();
		final String appVersion = conf.getAppVersion();

		MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
		config.setServiceURL(conf.getServiceURL());

		MarketplaceWebService service = new MarketplaceWebServiceClient(
				accessKeyId, secretAccessKey, appName, appVersion, config);

		final String merchantId = conf.getSellerId();
		// final String sellerDevAuthToken =
		// "<Merchant Developer MWS Auth Token>";

		final IdList marketplaces = new IdList(Arrays.asList(conf.getMarketplaceId()));

		SubmitFeedRequest request = new SubmitFeedRequest();
		request.setMerchant(merchantId);
		// request.setMWSAuthToken(sellerDevAuthToken);
		request.setMarketplaceIdList(marketplaces);

		request.setFeedType("_POST_ORDER_FULFILLMENT_DATA_");

		String content = this.assembleFeedContentForAmazon(conf, orders);
		
		byte[] bytes = content.getBytes();
		InputStream is = new ByteArrayInputStream(bytes);
		request.setFeedContent(is);
		request.setContentMD5(Base64.encodeBase64String(DigestUtils.md5(bytes)));

		return invokeSubmitFeed(service, request);
	}

	private AmazonAuthConfig getAmazonAuthConfig(OrderMarkShipping order) {
		
		AmazonAuthConfig conf = new AmazonAuthConfig();
		conf.setAccessKey(order.getAmAccessKey());
		conf.setSecretKey(order.getAmSecretAccessKey());
		conf.setSellerId(order.getAmMerchantId());
		conf.setMarketplaceId(order.getAmMarketplaceId());
		conf.setServiceURL(order.getAmServiceUrl());
		return conf;
	}

	private String assembleFeedContentForAmazon(AmazonAuthConfig conf, List<OrderMarkShipping> orders) {

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<AmazonEnvelope xsi:noNamespaceSchemaLocation=\"amzn-envelope.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		sb.append("<Header>");
		sb.append("<DocumentVersion>1.01</DocumentVersion>");
		sb.append("<MerchantIdentifier>").append(conf.getSellerId()).append("</MerchantIdentifier>");
		sb.append("</Header>");
		sb.append("<MessageType>OrderFulfillment</MessageType>");
		
		String timestamp = this.getXmlDate();
		for (OrderMarkShipping order : orders) {
			sb.append(this.assembleFeedContentMessageForAmazon(order, timestamp));
		}

		sb.append("</AmazonEnvelope>");

		return sb.toString();
	}
	
	private StringBuilder assembleFeedContentMessageForAmazon(OrderMarkShipping order, String timestamp) {
		StringBuilder sb = new StringBuilder();
	
		// 发货方式优先取ebay发货方式
		String shippingName = order.getEbayShippingName();
		if (shippingName == null) {
			shippingName = order.getShippingName();
		}
		
		sb.append("<Message>");
		sb.append("<MessageID>").append(order.getId()).append("</MessageID>");
		sb.append("<OperationType>Update</OperationType>");
		sb.append("<OrderFulfillment>");
		sb.append("<AmazonOrderID>").append(order.getOrderSn().replace("copy_", "")).append("</AmazonOrderID>");
		sb.append("<FulfillmentDate>").append(timestamp).append("</FulfillmentDate>");
		sb.append("<FulfillmentData>");
		sb.append("<CarrierName>").append(order.getShippingName()).append("</CarrierName>");
		sb.append("<ShippingMethod>").append(shippingName).append("</ShippingMethod>");
		sb.append("<ShipperTrackingNumber>").append(order.getTrackNumber()).append("</ShipperTrackingNumber>");
		sb.append("</FulfillmentData>");
		
		/*order.setItems(this.orderItemDao.queryByOrder(order.getId()));
		
		if (order.getItems() != null) {
			for (OrderItem item : order.getItems()) {
				sb.append("<Item>");
				sb.append("<AmazonOrderItemCode>").append(item.getItemId()).append("</AmazonOrderItemCode>");
				sb.append("<Quantity>").append(item.getItemQuantity()).append("</Quantity>");
				sb.append("</Item>");
			}
		}*/
		
		sb.append("</OrderFulfillment>");
		sb.append("</Message>");
		
		
		return sb;
	}

	private String getXmlDate() {
		return org.apache.commons.lang3.time.DateFormatUtils.formatUTC(new Date(), "yyyy-MM-dd'T'HH:mm:ss");
	}
	
	private  String getCurrentTimestamp(){
	    final TimeZone tz = TimeZone.getTimeZone("UTC");
	    final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
	    df.setTimeZone(tz);
	    final String nowAsISO = df.format(new Date());
	    return nowAsISO;
	  }

	private boolean invokeSubmitFeed(MarketplaceWebService service, SubmitFeedRequest request) {
		try {
			SubmitFeedResponse response = service.submitFeed(request);
			if (response.isSetSubmitFeedResult()) {
				SubmitFeedResult submitFeedResult = response.getSubmitFeedResult();
				if (submitFeedResult.isSetFeedSubmissionInfo()) {
					FeedSubmissionInfo feedSubmissionInfo = submitFeedResult.getFeedSubmissionInfo();
					if (feedSubmissionInfo.isSetFeedSubmissionId()) {
						return true;
					}
				}
			}
		} catch (MarketplaceWebServiceException ex) {
			logger.debug("Caught Exception: " + ex.getMessage());
			logger.debug("Response Status Code: " + ex.getStatusCode());
			logger.debug("Error Code: " + ex.getErrorCode());
			logger.debug("Error Type: " + ex.getErrorType());
			logger.debug("Request ID: " + ex.getRequestId());
			System.out.print("XML: " + ex.getXML());
			logger.debug("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
		}

		return false;
	}

	
	public static void main(String[] args) {
		MarkShippingServiceImpl mss = new MarkShippingServiceImpl();

		OrderMarkShipping order = new OrderMarkShipping();
		order.setSmtAppKey("1080467");
		order.setSmtAppSecret("cmGY9v08lL");
		order.setSmtAccessToken("4474110b-f5b7-4b93-b7ba-d85667b18374-");
		order.setSmtRefreshToken("43293787-4db5-4283-b02f-86ed687f2649");

		order.setOrderPlatform("smt");
		order.setAccountId(123);
		order.setOrderSn("65370499594651");
		order.setShippingName("CPAM");
		order.setTrackNumber("YC434484944YW");

		List<OrderMarkShipping> orders = new ArrayList<>();
		orders.add(order);

		mss.markOrders(orders);

/*		OrderMarkShipping order = new OrderMarkShipping();
		order.setOrderPlatform("ebay");
		order.setAccountId("1");
		order.setOrderSn("251669053158-1545864007015");
		order.setTrackNumber("LK255908952CN");
		order.setShippingName("EUB");
		order.setEbayToken("AgAAAA**AQAAAA**aAAAAA**RB+RUw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AFkIapCpGCpAmdj6x9nY+seQ**8yMCAA**AAMAAA**+viY59QOfq7gWJN9XVBcK3hf0cs+7oiI7ElER8dI3u7U0znhHhDz9wWjs5ZXLsdKU5DZXwci/mf4bFFiO5u1nAFwUCAHJbPLzvZ8CAQ1SRRDCse05C3koAGDnfA6bsW31DPL7H/q4KrW06LVfGf+EQOJe/gXSLdjJHeX2WfH/IUVu0EXYY8dyti/vIhd2E9yticSCHrj8lPSWtF0BU1iG18UcBIkRcchejsIUUI2FWZCQz/1Ziuu3p41Cm2XmK8xrXyUOZD5vt23j9YaJYegC97iC2ZWA03FMbQGs32azjDHrPPhRsFh4d/ykbJ61dJoUOZZcv3j9GX/y24wM8zpm677/wb/zMVlil3lG5C2PpEDZhvZtEBoR5YyM1xh+mzoWxSv5FbG16E1KcN83TGSsefUfqxjf9JeOBs+IjubzrduMAUkMZyGOyynLqZQlYx487EYMoNcTj+d+Q8NjW0MCh3jzXklUl2EdESN21rbBx0+6GZtUkfe8dUuhTee98TuwQSc0yjJtIOcBNE+n/nTAGAvHU3BQEHqAhMZLmShKVs8Cy17AyIgam/VCgyPiEW2XsFBr/0KgvmD4j8zUl5Lh4EpsK+7c8o9n17e4eRehS4Xr8dzKzmhmiMPJi9zAxm2Gkz6+JpluuFrp7rZFcnEB0EA34MF2IhNBtE59l9hNnkW4bZQlo2mnlNw01TB0tWW6NeaBPNqfOL8pw3+Rx3hlpsNUcccItYg0DF1ft0CLB5Lu+wLJdZbt4s+zDdDhbHm");

		List<OrderMarkShipping> orders = new ArrayList<>();
		orders.add(order);
		
		mss.markOrders(orders);*/
		
/*		List<OrderMarkShipping> orders = new ArrayList<>();
		
		OrderMarkShipping order = new OrderMarkShipping();
		order.setOrderPlatform("amazon");
		order.setAccountId("11");
		order.setAmServiceUrl("https://mws.amazonservices.fr");
		order.setAmAccessKey("AKIAIJAEIQKSESCRRVQA");
		order.setAmSecretAccessKey("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");
		order.setAmMerchantId("A90JGPYY6WZH8");
		order.setAmMarketplaceId("A13V1IB3VIYZZH");
		
		order.setId(1);
		order.setOrderSn("403-5917855-1941952");
		order.setShippingName("CHINA POST STAND");
		order.setTrackNumber("AZ1868135");

//		OrderItem item = new OrderItem();
//		item.setItemId("41298448164995");
//		item.setItemQuantity(1);
//		List<OrderItem> items = new ArrayList<>();
//		items.add(item);
//		order.setItems(items);
		orders.add(order);
		
		order = new OrderMarkShipping();
		order.setOrderPlatform("amazon");
		order.setAccountId("22");
		order.setAmServiceUrl("https://mws.amazonservices.com");
		order.setAmAccessKey("AKIAJF3XJWQLDFHUUBVQ");
		order.setAmSecretAccessKey("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");
		order.setAmMerchantId("A10DLVR13YEV98");
		order.setAmMarketplaceId("ATVPDKIKX0DER");
		
		order.setId(2);
		order.setOrderSn("109-5748964-5553802");
		order.setShippingName("CHINA POST STAND");
		order.setTrackNumber("AZ1868277");

		orders.add(order);
		
		mss.markOrders(orders);*/
	}
}

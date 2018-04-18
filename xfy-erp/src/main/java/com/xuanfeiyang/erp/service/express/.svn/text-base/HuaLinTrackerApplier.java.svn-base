package com.xuanfeiyang.erp.service.express;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.service.TrackerApplyResult;

/**
 * [华霖物流] 跟踪号申请
 * 
 * @author Adam
 *
 */
public class HuaLinTrackerApplier extends AbstractTrackerApplier {

	private static Logger logger = LoggerFactory.getLogger(HuaLinTrackerApplier.class);
	
	private static String apiBaseUrl = "http://sys.hlds56.com:8090/parcelsApiService/";
	private static String getTokenUrl = apiBaseUrl + "loginUser/100044/741258";
	private static String addParclesUrlPattern = apiBaseUrl + "addParcels/%s";
	
	private final static Map<String, String> shipCodeMap;
	static {
		Map<String, String> temp = new HashMap<>();
		temp.put("郑州挂号", "ZZR");
		temp.put("郑州平邮", "PYA");
		shipCodeMap = Collections.unmodifiableMap(temp);
	}
	
	/**
	 * 渠道类型
	 * {"shipCode":"ZZR","shipName":"郑州挂号","traceable":1,"returnAddress":"","barcodeAllocation":1}
	 * {"shipCode":"PYA","shipName":"郑州平邮","traceable":1,"returnAddress":"","barcodeAllocation":1}
	 * 
	 * 全部渠道参看：
	 * http://sys.hlds56.com:8090/parcelsApiService/queryShipType/{apiToken}
	 * 
	 */
	
	private static String apiToken = null;

	@Override
	public TrackerApplyResult doApply(Order order) throws Exception {
		String requestJson = this.assembleRequestJson(order);
		logger.info("[华霖物流] request json ==> {}", requestJson);
		
		String resXml = this.requestWebservice(requestJson);
		logger.info("[华霖物流] response json ==> {}", resXml);
		
		TrackerApplyResult result = this.parseResponseJson(resXml);

		if ("登录失败,请重新获取密钥".equals(result.getMessage())) {
			logger.info("[华霖物流] apiToken 已失效，重新申请....");
			apiToken = this.getApiToken();
			return this.doApply(order);
		}
		
		result.setOrderId(order.getId());
		return result;
	}

	private String assembleRequestJson(Order order) {
		String shipCode = shipCodeMap.get(order.getShippingName());
		checkArgument(shipCode != null, "不支持的发货方式：%s, 目前支持：%s", order.getShippingName(), shipCodeMap.keySet().toString());
		
		Map<String, Object> applyOrder = new LinkedHashMap<>();
		applyOrder.put("shipCode", shipCode);  // 运输方式(代码)
		applyOrder.put("orderNo", ObjectUtils.toString(order.getId()));  // orderNo
		
		OrderBuyerInfo buyer = order.getBuyinfo();
		applyOrder.put("apname", buyer.getShippingName());  // 收件人姓名
		applyOrder.put("desCode", buyer.getShippingCountry());  // 国家代码(代码)
		applyOrder.put("street1", buyer.getShippingStreet1());  // 街道1
		applyOrder.put("street2", buyer.getShippingStreet2());  // 街道2
		applyOrder.put("city", buyer.getShippingCity());  // 城市
		applyOrder.put("province", buyer.getShippingState());  // 州/省
		applyOrder.put("zipCode", buyer.getShippingPostcode());  // 邮编
		applyOrder.put("apTel", buyer.getShippingPhone());  // 收件人电话
		
		List<Map<String, String>> detailList = new ArrayList<>();
		applyOrder.put("detaillist", detailList);
		
		for (OrderItem temp : order.getItems()) {
			OrderItemWithShippingInfo orderItem = (OrderItemWithShippingInfo) temp;
			Map<String, String> item = new LinkedHashMap<>();
			item.put("apdescription", orderItem.getDeclarationNameEn());  // 物品
			item.put("customsArticleName", orderItem.getDeclarationNameEn());  // 申报品名
			item.put("apquantity", ObjectUtils.toString(orderItem.getItemQuantity()));  // 件数
			item.put("apweight", ObjectUtils.toString(orderItem.getWeight() != null ? orderItem.getWeight().setScale(3, RoundingMode.CEILING) : ""));  // 重量
			item.put("apvalue", ObjectUtils.toString(orderItem.getDeclarationCost() != null ? orderItem.getDeclarationCost().setScale(2, RoundingMode.CEILING) : "")); // 价值
			
			detailList.add(item);
		}
		
		List<Map<String, Object>> orders = new ArrayList<>();
		orders.add(applyOrder);
		
		return new Gson().toJson(orders);
	}
	
	private String requestWebservice(String json) throws Exception {
		if (apiToken == null) {
			logger.info("[华霖物流] apiToken为空，申请token....");
			apiToken = this.getApiToken();
		}
		
		String url = String.format(addParclesUrlPattern, apiToken);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		
		NameValuePair data = new BasicNameValuePair("data", json);
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(data);
		HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
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
		
		
		return response;
	}

	private String getApiToken() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(getTokenUrl);
		
		HttpResponse httpResponse = null;
		String response = null;
		try {
			httpResponse = httpClient.execute(get);
			
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity re = httpResponse.getEntity();
				response = re != null ? EntityUtils.toString(re) : null;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		
		@SuppressWarnings("serial")
		Map<String, Object> resMap = new Gson().fromJson(response, 
				new TypeToken<Map<String, Object>>() {}.getType());
		
		Boolean success = (Boolean) resMap.get("success");
		if (success != null && success == true) {
			@SuppressWarnings("rawtypes")
			Map map = (Map) resMap.get("map");
			
			if (map != null) {
				return (String) map.get("apiToken");
			}
		}
		
		return null;
	}

	private TrackerApplyResult parseResponseJson(String responseJson) {
		TrackerApplyResult result = new TrackerApplyResult();

		@SuppressWarnings("serial")
		List<Map<String, Object>> resList = new Gson().fromJson(responseJson, 
				new TypeToken<List<Map<String, Object>>>() {}.getType());
		
		Map<String, Object> resMap = resList.get(0);
		
		Boolean success = (Boolean) resMap.get("success");
		if (success != null && success == true) {
			String trackerNumber = (String) resMap.get("trackingNo");
			if (trackerNumber != null) {
				result.setSuccess(true);
				result.setTrackNumber(trackerNumber);
			} else {
				result.setSuccess(false);
				result.setMessage((String) resMap.get("errorMsg"));
			}
		} else {
			String errorMsg = (String) resMap.get("errorMsg");
			result.setSuccess(false);
			result.setMessage(errorMsg);
		}
		
		return result;
	}
	
	/*
	public static void main(String[] args) {
		
		OrderItemWithShippingInfo item = new OrderItemWithShippingInfo();
		item.setSku("S033693000005");
		item.setDeclarationNameCn("挂钟");
		item.setDeclarationNameEn("Wall Clocks");
		item.setItemQuantity(1);
		item.setDeclarationCost(new BigDecimal(10));
		item.setWeight(new BigDecimal(0.010));

		List<OrderItem> items = new ArrayList<>();
		items.add(item);

		OrderBuyerInfo buyer = new OrderBuyerInfo();
		buyer.setShippingName("RAMONALDAMIZ");
		buyer.setShippingStreet1("31 Harefoot close");
		buyer.setShippingStreet2("");
		buyer.setShippingPostcode("364060");
		buyer.setShippingCity("Northampton");
		buyer.setShippingState("");
		buyer.setShippingCountry("USaaaa");
		buyer.setShippingPhone("8-962-6554111");
		buyer.setBuyerEmail("t@r.com");

		Order order = new Order();
		order.setBuyinfo(buyer);
		order.setId(NumberUtils.toInt(DateFormatUtils.format(new Date(), "MMddHHmmss")));
		order.setShippingName("郑州平邮");

		order.setItems(items);
		
		TrackerApplyResult result = new HuaLinTrackerApplier().apply(order);
		System.out.println(result);
	}
	*/
}

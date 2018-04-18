package com.xuanfeiyang.erp.service.express;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.service.TrackerApplyResult;

public class BpostTrackerApplier extends AbstractTrackerApplier {
	
	private static Logger logger = LoggerFactory.getLogger(BpostTrackerApplier.class);

	final String API_URL = App.getConfig("express.bpost.url");
	final String USERNAME = App.getConfig("express.bpost.username");
	final String PASSWORD = App.getConfig("express.bpost.password");
	
	public TrackerApplyResult doApply(Order order) {
		
		TrackerApplyResult result = new TrackerApplyResult();
		result.setOrderId(order.getId());
		
		String data = this.assembleRequestData(order);
		logger.info("bPost request ==> {}", data);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(API_URL);
		
		HttpEntity entity = new StringEntity(data, "utf-8");
		post.setEntity( entity );
		
		// set request header
		post.setHeader("Content-type", "text/json;charset=utf-8");
		post.setHeader("Accept", "text/json");
		post.setHeader("Cache-Control", "no-cache");
		post.setHeader("Authorization", "basic " + getAuthInfo());
		
		HttpResponse httpResponse = null;
		String response = null;
		try {
			httpResponse = httpClient.execute(post);
			
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity re = httpResponse.getEntity();
				response = re != null ? EntityUtils.toString(re) : null;
			} else {
				result.setMessage(httpResponse.getStatusLine().toString());
			}
			
		} catch (IOException e) {
			logger.error("bpost 申请跟踪号出错", e);;
			result.setMessage(e.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		
		logger.info("bPost response ==> {}", response);
		
		if (response != null) {
			@SuppressWarnings("serial")
			Map<String, Object> resMap = new Gson().fromJson(response, 
					new TypeToken<Map<String, Object>>() {}.getType());
			
			String trackNumber = (String) resMap.get("ProductBarcode"); 
			if (trackNumber != null) {
				result.setSuccess(true);
				result.setTrackNumber(trackNumber);
			} else {
				result.setSuccess(false);
				result.setTrackNumber(response);
			}
		}
		
		return result;
	}

	private String getAuthInfo() {
		String tmp = USERNAME + ":" + PASSWORD;
		return Base64.encodeBase64String(tmp.getBytes());
	}

	private String assembleRequestData(Order order) {
		Map<String, Object> data = new LinkedHashMap<>();
		
		OrderBuyerInfo buyer = order.getBuyinfo();
		data.put("ContractId", 1);
		data.put("OrderNumber", String.valueOf(order.getId()));
		data.put("RecipientName", buyer.getShippingName());
		data.put("RecipientStreet", buyer.getShippingStreet1() + " " + buyer.getShippingStreet2());
		data.put("RecipientHouseNumber", "");
		data.put("RecipientBusnumber", ""); // eg. sample string 8
		data.put("RecipientZipCode", buyer.getShippingPostcode());
		data.put("RecipientCity", buyer.getShippingCity());
		data.put("RecipientState", buyer.getShippingState());
		data.put("RecipientCountry", buyer.getShippingCountry() != null ? buyer.getShippingCountry() : buyer.getShippingCountryName());
		data.put("PhoneNumber", buyer.getShippingPhone());
		data.put("Email", buyer.getBuyerEmail());
		data.put("SenderName", "");
		data.put("SenderAddress", "");
		data.put("SenderSequence", "");
		data.put("IsSure", "true");
		
		List<Map<String, Object>> customs = new ArrayList<>();
		data.put("Customs", customs);

		if (order.getItems() != null && order.getItems().size() > 0) {
			for (OrderItem tmp : order.getItems()) {
				OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) tmp;
				Map<String, Object> custom = this.assembleCustom(item);
	
				customs.add(custom);
			}
		}
		
		return new Gson().toJson(data);
	}
	
	private Map<String, Object> assembleCustom(OrderItemWithShippingInfo item) {
		Map<String, Object> custom = new LinkedHashMap<>();
		custom.put("Sku", item.getSku());
		custom.put("ChineseContentDescription", item.getDeclarationNameCn()); // 中文申报名称
		custom.put("ItemContent", item.getDeclarationNameEn());
		custom.put("ItemCount", item.getItemQuantity());
		custom.put("Value", item.getDeclarationCost().doubleValue());	// 申报价值 
		custom.put("Currency", "USD");
		
		Double weight = Math.ceil(item.getWeight() != null ? item.getWeight().doubleValue() * item.getItemQuantity() * 1000 : 1);
		
		custom.put("Weight", weight.intValue());	// 重量
		custom.put("SkuInInvoice", item.getSku());
		
		return custom;
	}

	/*
	public static void main(String[] args) {
		
		OrderItemWithShippingInfo item = new OrderItemWithShippingInfo();
		item.setSku("S033693000005");
		item.setDeclarationNameCn("挂钟");
		item.setDeclarationNameEn("Wall Clocks");
		item.setItemQuantity(1);
		item.setDeclarationCost(new BigDecimal(10));
		item.setWeight(new BigDecimal(0.001));
		
		List<OrderItem> items = new ArrayList<>();
		items.add(item);
		
		OrderBuyerInfo buyer = new OrderBuyerInfo();
		buyer.setShippingName("Michelle Hammons");
		buyer.setShippingStreet1("31 Harefoot close");
		buyer.setShippingStreet2("");
		buyer.setShippingPostcode("XXXX");
		buyer.setShippingCity("Northampton");
		buyer.setShippingState("");
		buyer.setShippingCountryName("UNITED KINGDOM");
		buyer.setShippingPhone("123123123123");
		buyer.setBuyerEmail("t@r.com");
		
		Order order = new Order();
		order.setBuyinfo(buyer);
		order.setId(6926504);
		order.setShippingName("bPost");
		
		order.setItems(items);
		
		TrackerApplyResult result = new BpostTrackerApplier().apply(order);
		System.out.println(result);
	}
	*/
}

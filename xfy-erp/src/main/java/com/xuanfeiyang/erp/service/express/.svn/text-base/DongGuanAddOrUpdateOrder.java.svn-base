package com.xuanfeiyang.erp.service.express;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.service.TrackerApplyResult;

public class DongGuanAddOrUpdateOrder extends AbstractTrackerApplier{
	private static Logger logger = LoggerFactory.getLogger(DongGuanAddOrUpdateOrder.class);
	
	private String expressType;
	
	public DongGuanAddOrUpdateOrder(String expressType){
		this.expressType = expressType;
	}
	
	private static Map<String, String> shippings = new HashMap<>();
	static {
		shippings.put("国内小包", "001");
		shippings.put("国际小包挂号", "002");
		shippings.put("国际小包平邮", "am");
		shippings.put("中邮小包挂号", "002");
		shippings.put("中邮小包平邮", "am");
		shippings.put("两岸小包", "tw");
		shippings.put("水陆路", "004");
		shippings.put("平邮小包", "am+");
		shippings.put("线下eub", "offline_eub");
		shippings.put("中国邮政", "CHINA POST");
	}

	@Override
	public TrackerApplyResult doApply(Order order) throws Exception {
		String param = this.requestData(order); //请求参数
		TrackerApplyResult result = this.sendPost(param);//post请求
		result.setOrderId(order.getId());
		return result;
	}


	/***
	 * 提交订单数据
	 * 
	 */
	private String requestData(Order order){
		List<OrderItem> orderItem = order.getItems();
	
		String shipWayCode = shippings.get(order.getShippingName());
		Preconditions.checkArgument(shipWayCode != null, "发货方式错误");
		
		StringBuffer req = new StringBuffer();
		req.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		
		req.append("<Orders>");
		req.append("<Order>");
		
		req.append("<SellerAccountName>");
		req.append(order.getAccountName());
		req.append("</SellerAccountName>");
		
		OrderBuyerInfo buyerInfo = order.getBuyinfo();
		
		req.append("<OrderId>").append(order.getId()).append("</OrderId>");
		req.append("<SalesOrderId>").append(order.getOrderSn()).append("</SalesOrderId>");
		req.append("<BuyerId>").append(buyerInfo.getBuyerUserId()).append("</BuyerId>");
		req.append("<ReceiverName>").append(buyerInfo.getShippingName()).append("</ReceiverName>");
		req.append("<AddressLine1>").append(buyerInfo.getShippingStreet1()).append("</AddressLine1>");
		req.append("<AddressLine2>").append(buyerInfo.getShippingStreet2()).append("</AddressLine2>");
		
		String country = buyerInfo.getShippingCountry() != null ? buyerInfo.getShippingCountry() : buyerInfo.getShippingCountryName();
		// 因为不支持UK，将UK转为支持的GB
		country = "UK".equals(country) ? "GB" : country; 
		req.append("<Country>").append(country).append("</Country>");
		
	
		req.append("<State>").append(buyerInfo.getShippingState()).append("</State>");
		
		req.append("<City>").append(buyerInfo.getShippingCity()).append("</City>");
		
		req.append("<PostCode>").append(buyerInfo.getShippingPostcode()).append("</PostCode>");
	
		String phone = StringUtils.defaultIfBlank(buyerInfo.getShippingPhone(), 
				buyerInfo.getShippingMobile()) ;
		req.append("<PhoneNumber>").append(phone).append("</PhoneNumber>");
	
		req.append("<Email>").append(buyerInfo.getBuyerEmail()).append("</Email>");
		req.append("<ShipWayCode>").append(shipWayCode).append("</ShipWayCode>");
		
		req.append("<TrackingNo>").append("</TrackingNo>");
		
		req.append("<OrderItems>");
		for (int i = 0; i < orderItem.size(); i++) {
			OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) orderItem.get(i);
			req.append("<OrderItem>");
			req.append("<Quantity>").append(item.getItemQuantity()).append("</Quantity>");
			req.append("<Sku>").append(item.getSku()).append("</Sku>");
			req.append("<Title>").append("</Title>");
			req.append("</OrderItem>");
		}
		req.append("</OrderItems>");
		
		
		
		req.append("<OrderCustoms>");
		req.append("<Currency>").append(order.getCurrency()).append("</Currency>");
		req.append("<CustomsType>").append("商品").append("</CustomsType>");
		
		req.append("<OrderCustom>");
		for (int j = 0; j < orderItem.size(); j++) {
			OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) orderItem.get(j);
			req.append("<Quantity>").append(item.getItemQuantity()).append("</Quantity>");
			req.append("<DescriptionEn>").append("DS").append("</DescriptionEn>");
			req.append("<DescriptionCn>").append("DSA").append("</DescriptionCn>");
			req.append("<Weight>").append(order.getCalcWeight() == null ? 0:order.getCalcWeight()).append("</Weight>");
			req.append("<Value>").append("10.5").append("</Value>");
		}
		
		req.append("</OrderCustom>");
		req.append("</OrderCustoms>");
	
		req.append("</Order>");
		req.append("</Orders>");
		
	
		return req.toString();
	}
	
	
	/***
	 * 
	 * post 请求 result 返回值
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * 
	 */
	public TrackerApplyResult sendPost(String param) {
		logger.info("eub request param: {}", param);
		String url = App.getConfig("express.eub.dg.url.addorupdate");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		HttpEntity entity = new StringEntity(param, "utf-8");
		post.setEntity( entity );
		
		// set request header
		post.setHeader("Content-Type", "application/xml; charset=utf-8");
		post.setHeader("version", App.getConfig("express.eub.dg.version"));
		if(this.expressType.equals("1")){
			post.setHeader("APIToken",App.getConfig("express.eub.dg.apitoken"));
		}
		if(this.expressType.equals("2")){
			post.setHeader("APIToken",App.getConfig("express.eub.dg.apitoken2"));
		}
		HttpResponse httpResponse = null;
		String response = null;
		String errorMsg = null;
		try {
			httpResponse = httpClient.execute(post);
			
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity re = httpResponse.getEntity();
				response = re != null ? EntityUtils.toString(re) : null;
			}
			
			logger.info("eub response: {}", response);
			return this.parseResponse(response);
			
		} catch (ClientProtocolException e) {
			logger.error("连接异常",e.getMessage());
			errorMsg = e.getMessage();
		} catch (IOException e) {
			logger.error("IO异常",e.getMessage());
			errorMsg = e.getMessage();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				
			}
		}
		
		TrackerApplyResult result = new TrackerApplyResult();
		result.setSuccess(false);
		result.setMessage(errorMsg);
		return result;
	}	
	
	
	
	private TrackerApplyResult parseResponse(String response){
		TrackerApplyResult result = new TrackerApplyResult();
		Document document = null;
		
		try {
			document = DocumentHelper.parseText(response);
		} catch (DocumentException e) {
			logger.error("解析文档出错, 错误: {}, 文档: {}", e.getMessage(), response);
		}
		
		Element root = document.getRootElement();
		Element ordersElement = root.element("Orders");
		Element orderElement = ordersElement.element("Order");
		String status = orderElement.elementText("Status");
	
		if(status.equals("fail")){
			String error = orderElement.elementText("Error");
			result.setMessage(error);
			result.setSuccess(false);
		}else if(status.equals("success")){
			result.setSuccess(true);
		}
		
		return result;
	}
	
	

	/*public static void main(String[] args) {

	OrderItemWithShippingInfo item = new OrderItemWithShippingInfo();
		
		item.setSku("1100019001");
		item.setDeclarationNameCn("大众帕萨特B5排挡杆防尘套");
		item.setDeclarationNameEn("Wall Clocks");
		item.setItemQuantity(1);
		item.setDeclarationCost(new BigDecimal(10));
		item.setWeight(new BigDecimal(0.010));
		item.setGoodsUnit("ge");

		List<OrderItem> items = new ArrayList<>();
		items.add(item);

		OrderBuyerInfo buyer = new OrderBuyerInfo();
		buyer.setShippingName("Michelle Hammons");
		buyer.setShippingStreet1("31 Harefoot close");
		buyer.setShippingStreet2("");
		buyer.setShippingPostcode("19720");
		buyer.setShippingCity("Nev Castle");
		buyer.setShippingState("DE");
		buyer.setShippingCountryName("UNITED STATES");
		//buyer.setShippingPhone("12121212");
		buyer.setBuyerEmail("t@r.com");
		buyer.setBuyerUserId("XXX");

		Order order = new Order();
		order.setBuyinfo(buyer);
		order.setId(NumberUtils.toInt(DateFormatUtils.format(new Date(), "MMddHHmmss")));
		order.setShippingName("国际小包挂号");
		order.setAccountName("linlin");
		order.setOrderSn("427141022");
		order.setPackageWeight(new BigDecimal(8.0));
		order.setCurrency("RMB");
		order.setItems(items);

		TrackerApplyResult result = new DongGuanAddOrUpdateOrder().apply(order);
		System.out.println(result);
	}*/
	
	

}

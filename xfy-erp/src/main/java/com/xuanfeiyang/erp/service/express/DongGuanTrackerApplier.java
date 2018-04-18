package com.xuanfeiyang.erp.service.express;

import java.io.IOException;

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

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.service.TrackerApplyResult;

public class DongGuanTrackerApplier extends AbstractTrackerApplier{
	private static Logger logger = LoggerFactory.getLogger(DongGuanTrackerApplier.class);
	
	private String expressType;
	
	public DongGuanTrackerApplier(String expressType){
		this.expressType = expressType;
	}
	
	
	@Override
	public TrackerApplyResult doApply(Order order) throws Exception {
		//调用上传订单信息方法
		AbstractTrackerApplier addOrUpdate = new DongGuanAddOrUpdateOrder(expressType);
		TrackerApplyResult result = addOrUpdate.doApply(order);
		if (! result.isSuccess()) {
			return result;
		}
		//申请跟踪号
		String param = this.requestData(order); //请求参数
		result = this.sendPost(param);//post请求
		result.setOrderId(order.getId());
		return result;
	}
	
	
	private String requestData(Order order) {

		StringBuffer req = new StringBuffer();
		req.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		req.append("<Orders>");
		req.append("<Order>");
		req.append("<OrderId>");
		req.append(order.getId());
		req.append("</OrderId>");
		req.append("<EubPrintProductFormat>");
		req.append("{sku}({itemtitle})");
		req.append("</EubPrintProductFormat>");
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
		String url = App.getConfig("express.eub.dg.url.applytrackno");
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
	
	
	private TrackerApplyResult parseResponse(String response) {
		TrackerApplyResult result = new TrackerApplyResult();
		Document document = null;
		try {
			document = DocumentHelper.parseText(response);
		} catch (DocumentException e) {
			logger.error("解析文档出错, 错误: {}, 文档: {}", e.getMessage(), response);
		}

		Element root = document.getRootElement();

		Element orderElement = root.element("Order");

		String status = orderElement.elementText("Status");
		if (status.equals("fail")) {
			String error = orderElement.elementText("ErrorMsg");
			result.setMessage(error);
			result.setSuccess(false);
		} else if (status.equals("success")) {
			String trackNo = orderElement.elementText("TrackingNo");
			result.setSuccess(true);
			result.setTrackNumber(trackNo);
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
	buyer.setShippingPhone("12121212121");
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
	
		TrackerApplyResult result = new DongGuanTrackerApplier().apply(order);
		System.out.println(result);
	}
	*/

}

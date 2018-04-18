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

/***
 * 删除订单
 * @author Administrator
 *
 */
public class DongGuanOrderDelete extends AbstractTrackerApplier{
	private static Logger logger = LoggerFactory.getLogger(DongGuanOrderDelete.class);
	@Override
	public TrackerApplyResult doApply(Order order) throws Exception {
		String param = this.requestData(order); //请求参数
		TrackerApplyResult result = this.sendPost(param);//post请求
		result.setOrderId(order.getId());
		return result;
	}
	
	
	
	private String requestData(Order order){
		
		StringBuffer req = new StringBuffer();
		req.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		req.append("<Orders>");
		req.append("<OrderId>");
		req.append(order.getId());
		req.append("</OrderId>");
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
		
		TrackerApplyResult result = new TrackerApplyResult();
		
		logger.info("eub request param: {}", param);
		String url = App.getConfig("express.eub.dg.url.delete");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		HttpEntity entity = new StringEntity(param, "utf-8");
		post.setEntity( entity );
		
		// set request header
		post.setHeader("Content-Type", "application/xml; charset=utf-8");
		post.setHeader("version", App.getConfig("express.eub.dg.version"));
		post.setHeader("APIToken",App.getConfig("express.eub.dg.apitoken"));
		HttpResponse httpResponse = null;
		String response = null;
		try {
			httpResponse = httpClient.execute(post);
			
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity re = httpResponse.getEntity();
				response = re != null ? EntityUtils.toString(re) : null;
			}
			
			Document document = null;
			
			try {
				document = DocumentHelper.parseText(response);
			} catch (DocumentException e) {
				logger.error("解析文档出错, 错误: {}, 文档: {}", e.getMessage(), response);
			} 
			Element root = document.getRootElement();
			
			Element orderElement = root.element("Order");
			
			String status = orderElement.elementText("status");
		
				if(status.equals("fail")){
					result.setMessage("删除失败");
					result.setSuccess(false);
				}else if(status.equals("success")){
					result.setSuccess(true);
					result.setMessage("删除成功");
				}

			
		} catch (ClientProtocolException e) {
			logger.error("连接异常",e.getMessage());
			
		} catch (IOException e) {
			
			logger.error("IO异常",e.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				
			}
		}
		
		logger.info("返回 response: {}", response);
		return result;
	}
	
/*	public static void main(String[] args) {
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
		order.setId(428144324);
		//order.setId(NumberUtils.toInt(DateFormatUtils.format(new Date(), "MMddHHmmss")));
		order.setShippingName("国际小包挂号");
		order.setAccountName("linlin");
		
		//order.setOrderSn("428144324");
		//order.setPackageWeight(new BigDecimal(8.0));
		order.setCurrency("RMB");
		order.setItems(items);
		TrackerApplyResult result = new DongGuanOrderDelete().apply(order);
		System.out.println(result);
	}
	*/

}

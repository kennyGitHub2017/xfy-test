package com.xuanfeiyang.erp.service.express;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
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
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.service.TrackerApplyResult;

public class EubTrackerApplier extends AbstractTrackerApplier {
	private static Logger logger = LoggerFactory.getLogger(EubTrackerApplier.class);

	private String expressType;
	
	public EubTrackerApplier(String expressType){
		this.expressType = expressType;
	}
	
	
	
	@Override
	public TrackerApplyResult doApply(Order order) throws Exception {
		String param = this.requestData(order); //请求参数
		TrackerApplyResult result = this.sendPost(param);//post请求
		result.setOrderId(order.getId());
		return result;
	}

	
	/**
	 * 拼装请求数据
	 * @param order
	 */
	private String requestData(Order order){

		List<OrderItem> orderItem = order.getItems();
		
		StringBuffer req = new StringBuffer();
		req.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		req.append("<orders xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		req.append("<order>");
		
		req.append("<orderid>");
		req.append(order.getId());
		req.append("</orderid>");
		
		req.append("<operationtype>0</operationtype>");
		req.append("<producttype>0</producttype>");
		
		req.append("<customercode>");
		req.append("155552255");
		req.append("</customercode>");
		
		req.append("<vipcode></vipcode>");
		req.append("<clcttype>1</clcttype>");
		
		
		req.append("<pod>false</pod>");
		
		
		req.append("<untread>Returned</untread>");
		
		req.append("<volweight>");
		req.append("100");//????
		req.append("</volweight>");
		
		req.append("<startdate>2011-03-01T22:22:22</startdate>");
		req.append("<enddate>2011-03-11T22:22:22</enddate>");
		
		
		req.append("<printcode>00</printcode>");
		
		//邮件人信息
	req.append("<sender>");
		req.append("<name>");
		req.append("轩飞扬贸易公司");
		req.append("</name>");
		
		req.append("<postcode>");
		req.append("518000");
		req.append("</postcode>");
		
		req.append("<phone>");
		req.append("13168081231");
		req.append("</phone>");
		
		req.append("<mobile>");
		req.append("0755-29662769");
		req.append("</mobile>");
		
		req.append("<country>");
		req.append("CN");
		req.append("</country>");
		
		req.append("<province>");
		req.append("440000");
		req.append("</province>");
		
		req.append("<city>");
		req.append("440300");
		req.append("</city>");
		
		req.append("<county>");
		req.append("440306");
		req.append("</county>");
		
		req.append("<company>");
		req.append("深圳轩飞扬贸易有限公司");
		req.append("</company>");
		
		req.append("<street>");
		req.append("新安5路");
		req.append("</street>");
		
		req.append("<email>");
		req.append("2589780816@qq.com");
		req.append("</email>");
	req.append("</sender>");

	OrderBuyerInfo buyInfo = order.getBuyinfo();
	
	//收件信息
	req.append("<receiver>");
		req.append("<name>");
		req.append(buyInfo.getShippingName());
		req.append("</name>");
		
		req.append("<postcode>");
		req.append(buyInfo.getShippingPostcode());
		req.append("</postcode>");
		
		req.append("<phone>");
		req.append(buyInfo.getShippingPhone());
		req.append("</phone>");
		
		req.append("<mobile>");
		req.append(buyInfo.getShippingMobile());
		req.append("</mobile>");
		
		req.append("<country>");
		req.append(buyInfo.getShippingCountry() != null ? buyInfo.getShippingCountry() : buyInfo.getShippingCountryName());
		req.append("</country>");
	
		req.append("<province>");
		req.append(buyInfo.getShippingState());
		req.append("</province>");
		
		req.append("<city>");
		req.append(buyInfo.getShippingCity());
		req.append("</city>");
		
		req.append("<county>");
		req.append("</county>");
		
		req.append("<street>");
		req.append(buyInfo.getShippingStreet1());
		req.append("</street>");
	req.append("</receiver>");
	
	//揽收信息
	req.append("<collect>");
	
		req.append("<name>");
		req.append("TEST");
		req.append("</name>");
		
		req.append("<postcode>");
		req.append("518000");
		req.append("</postcode>");
		
		req.append("<phone>");
		req.append("13168081231");
		req.append("</phone>");
		
		req.append("<mobile>");
		req.append("0755-29662769");
		req.append("</mobile>");
		
		req.append("<country>");
		req.append("CN");
		req.append("</country>");
		
		req.append("<province>");
		req.append("440000");
		req.append("</province>");
		
		req.append("<city>");
		req.append("440300");
		req.append("</city>");
		
		req.append("<county>");
		req.append("440306");
		req.append("</county>");
		
		req.append("<company/>");
		
		req.append("<street>");
		req.append("新安5路");
		req.append("</street>");
		
		req.append("<email>");
		req.append("2589780816@qq.com");
		req.append("</email>");
		
	req.append("</collect>");
		
	//商品信息
	req.append("<items>");
		
		for (int i = 0; i < orderItem.size(); i++) {
			
			OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) orderItem.get(i);
			
			req.append("<item>");
			
				req.append("<cnname>");
				req.append(item.getDeclarationNameCn());
				req.append("</cnname>");
				
				req.append("<enname>");
				req.append(item.getDeclarationNameEn());
				req.append("</enname>");
				
				req.append("<count>");
				req.append(item.getItemQuantity());
				req.append("</count>");
				
				req.append("<unit>");
				req.append(item.getGoodsUnit());
				req.append("</unit>");
				
				req.append("<weight>");
				req.append(this.returnWeight(item.getSku(), item.getItemQuantity(), item.getWeight()));
				req.append("</weight>");
				
				req.append("<delcarevalue>");
				req.append(this.returnDeclarationValue(item.getSku(), item.getItemQuantity(), item.getDeclarationCost()));
				req.append("</delcarevalue>");
				
				req.append("<origin>");
				req.append("CN");
				req.append("</origin>");
				
				req.append("<description>");
				req.append("</description>");
				
			req.append("</item>");
		}
		req.append("</items>");
		
		req.append("<remark/>");
		req.append("</order>");
		req.append("</orders>");
		
		
		return req.toString();
	}

	/***
	 * 
	 * 计算  重量
	 */
	private BigDecimal returnWeight(String sku, Integer buyCount, BigDecimal itemWeight){
		BigDecimal toatlWeight = itemWeight.multiply(new BigDecimal(buyCount));
		return toatlWeight.setScale(3, BigDecimal.ROUND_HALF_UP);
	}
	
	/***
	 * 
	 * 计算 报关价格
	 * 
	 */
	private BigDecimal returnDeclarationValue(String sku, Integer buyCount, BigDecimal itemCost){
		
		BigDecimal amount = itemCost.multiply(new BigDecimal(buyCount));
		return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/***
	 * 
	 * post 请求 
	 * result 返回值
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * 
	 */
	public TrackerApplyResult sendPost(String param) {
		
		TrackerApplyResult result = new TrackerApplyResult();
		
		logger.info("eub request param: {}", param);
		
		String url = App.getConfig("express.eub.url");
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		
		HttpEntity entity = new StringEntity(param, "utf-8");
		post.setEntity( entity );
		
		// set request header
		post.setHeader("Content-Type", "application/xml; charset=utf-8");
		post.setHeader("version", "international_eub_us_1.1");
		
		
		if(this.expressType.equals("1")){
			post.setHeader("authenticate", App.getConfig("express.eub.authenticate"));
		}
		
		if(this.expressType.equals("2")){
			post.setHeader("authenticate", App.getConfig("express.xiang.eub.authenticate"));
		}
		
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
			String mailnum = root.elementText("mailnum");
			if(mailnum != null){
				result.setSuccess(true);
				result.setTrackNumber(mailnum);
			}else{
				result.setSuccess(false);
				String description = root.elementText("description");
				result.setMessage(description);
			}
			
			
		} catch (ClientProtocolException e) {
			
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			result.setTrackNumber(null);
			
			logger.error("连接异常",e.getMessage());
		} catch (IOException e) {
			
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			result.setTrackNumber(null);
			
			logger.error("IO异常",e.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				
			}
		}
		
		logger.info("eub response: {}", response);
		return result;
	}
	/*
	public static void main(String[] args) {

		OrderItemWithShippingInfo item = new OrderItemWithShippingInfo();
		
		item.setSku("1410005101");
		item.setDeclarationNameCn("挂钟");
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
		buyer.setShippingPostcode("3043");
		buyer.setShippingCity("Northampton");
		buyer.setShippingState("XX");
		buyer.setShippingCountryName("AU");
		buyer.setShippingPhone("123123123123");
		buyer.setBuyerEmail("t@r.com");

		Order order = new Order();
		order.setBuyinfo(buyer);
		order.setId(NumberUtils.toInt(DateFormatUtils.format(new Date(), "MMddHHmmss")));
		order.setShippingName("中邮北京平邮小包");

		order.setItems(items);

		TrackerApplyResult result = new EubTrackerApplier("2").apply(order);
		System.out.println(result);
	}
	
	*/
}

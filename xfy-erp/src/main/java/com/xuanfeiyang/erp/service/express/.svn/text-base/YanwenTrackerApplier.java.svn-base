package com.xuanfeiyang.erp.service.express;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.service.TrackerApplyResult;
import com.xuanfeiyang.erp.service.express.yanwen.CreateExpressResponseType;
import com.xuanfeiyang.erp.service.express.yanwen.ExpressType;
import com.xuanfeiyang.erp.service.express.yanwen.GoodsName;
import com.xuanfeiyang.erp.service.express.yanwen.Receiver;
import com.xuanfeiyang.erp.service.express.yanwen.XmlParser;

/**
 * 燕文跟踪号申请
 * 
 * @author Adam
 *
 */
public class YanwenTrackerApplier extends AbstractTrackerApplier {
	private static Logger logger = LoggerFactory.getLogger(YanwenTrackerApplier.class);

	@Override
	public TrackerApplyResult doApply(Order order) throws Exception {

		TrackerApplyResult result = new TrackerApplyResult();
		result.setOrderId(order.getId());

		CreateExpressResponseType cer = this.sendYanwenRequest(order);

		if (cer != null) {
			if (cer.isCallSuccess()) {
				result.setSuccess(true);
				result.setTrackNumber(cer.getCreatedExpress().getEpcode());
			} else {
				result.setSuccess(false);
				result.setMessage(cer.getResponse().getReason() + " - " + cer.getResponse().getReasonMessage());
			}
		} else {
			result.setSuccess(false);
			result.setMessage("返回为空");
		}

		return result;
	}

	/**
	 * 提交燕文请求
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	private CreateExpressResponseType sendYanwenRequest(Order order) throws Exception {
		String apiusid = App.getConfig("express.yanwen.apiusid");
		String apitoken = App.getConfig("express.yanwen.apitoken");
		String apiurl = String.format(App.getConfig("express.yanwen.apiurl"), apiusid);

		String params = buildYanwenRequestParam(order);
		logger.info("yanwen request params ==> {}", params);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(apiurl);
		
		HttpEntity entity = new StringEntity(params, "utf-8");
		post.setEntity( entity );
		
		// set request header
		post.setHeader("Content-Type", "text/xml; charset=utf-8");
		post.setHeader("Authorization", "basic " + apitoken);
		
		HttpResponse httpResponse = null;
		String response = null;
		try {
			httpResponse = httpClient.execute(post);
			
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity re = httpResponse.getEntity();
				response = re != null ? EntityUtils.toString(re) : null;
			}
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		

		logger.info("yanwen response xml => {}", response);
		CreateExpressResponseType cert = XmlParser.parse(response, CreateExpressResponseType.class);

		return cert;
	}

	/**
	 * 构建燕文请求参数
	 * 
	 * @return
	 * @throws Exception
	 */
	private String buildYanwenRequestParam(Order order) throws Exception {
		String apiusid = App.getConfig("express.yanwen.apiusid"); // 客户号

		// 订单收件人信息
		OrderBuyerInfo buyerInfo = order.getBuyinfo();

		// ExpressType expressType = ExpressType.mock();
		ExpressType expressType = new ExpressType();
		expressType.setEpcode(null); // 运单号，部分发货方式必填
		expressType.setUserid(apiusid);// 客户号
		expressType.setChannel(order.getShippingName());// 发货方式->
		expressType.setUserOrderNumber(String.valueOf(order.getId()));// 客户订单号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		expressType.setSendDate(sdf.format(new Date()));// 发货日期

		Receiver receiver = new Receiver();// 收件人
		receiver.setUserid(apiusid); // 客户号
		receiver.setName(buyerInfo.getShippingName());// 收货人-姓名
		receiver.setPhone(buyerInfo.getShippingPhone());// 收货人-座机
		receiver.setMobile(buyerInfo.getShippingMobile());// 手机
		receiver.setEmail(buyerInfo.getBuyerEmail());// 买家-邮编
		receiver.setCompany(null);// 收货人-公司
		receiver.setCountry(buyerInfo.getShippingCountryName()); // 收货人-国家
		receiver.setPostcode(buyerInfo.getShippingPostcode());// 收货人-邮编
		receiver.setState(buyerInfo.getShippingState());// 收货人-州
		receiver.setCity(buyerInfo.getShippingCity());// 收货人-城市
		receiver.setAddress1(buyerInfo.getShippingStreet1());// 收货人-地址1
		receiver.setAddress2(buyerInfo.getShippingStreet1());// 收货人-地址2
		if (StringUtils.isBlank(receiver.getAddress1())) {
			receiver.setAddress1(receiver.getAddress2());
			receiver.setAddress2(null);
		}
		expressType.setReceiver(receiver);

		List<OrderItem> items = order.getItems();

		int totalQuantity = 0;
		double totalWeight = 0;
		double totalCost = 0;
		String enName = null, cnName = null;
		StringBuilder nameQuantityStr = new StringBuilder();

		for (OrderItem tmp : items) {
			OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) tmp;

			int quantity = item.getItemQuantity() != null ? item.getItemQuantity() : 1;
			totalQuantity += quantity;
			
			double weight = item.getWeight() != null ? item.getWeight().doubleValue() * quantity * 1000 : 0;
			totalWeight += weight;

			double cost = item.getDeclarationCost() != null ? item.getDeclarationCost().doubleValue() : 0;
			totalCost += cost;

			nameQuantityStr.append(item.getSku()).append("*").append(quantity).append(" ");

			cnName = item.getDeclarationNameCn();
			enName = item.getDeclarationNameEn();
		}

		String allGoodsStr = nameQuantityStr.toString();
		expressType.setQuantity("" + totalQuantity);// 货品数量
		expressType.setPackageNo(null);// 包裹号
		expressType.setInsure(null);// 是否需要保险
		expressType.setMemo(allGoodsStr);// 备注-会出现在拣货单上

		GoodsName goodsName = new GoodsName();

		goodsName.setUserid(apiusid);// 客户号
		goodsName.setNameCh(cnName); // 商品中文品名
		goodsName.setNameEn(enName); // 商品英文品名
		goodsName.setMoreGoodsName(allGoodsStr);
		goodsName.setWeight(String.valueOf(new Double(totalWeight).intValue()));// 包裹重量
		goodsName.setDeclaredValue(String.valueOf(totalCost)); // 申报价值
		goodsName.setDeclaredCurrency("USD");// 申报币种

		// goodsName.setHsCode(null);
		expressType.setGoodsName(goodsName);

		return XmlParser.format(expressType, ExpressType.class);
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
		order.setId(NumberUtils.toInt(DateFormatUtils.format(new Date(), "MMddHHmmss")));
		order.setShippingName("中邮北京挂号小包");

		order.setItems(items);

		TrackerApplyResult result = new YanwenTrackerApplier().apply(order);
		System.out.println(result);
	}
	*/
}

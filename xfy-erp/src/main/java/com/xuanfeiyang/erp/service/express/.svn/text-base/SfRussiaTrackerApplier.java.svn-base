package com.xuanfeiyang.erp.service.express;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.sf.integration.expressservice.service.IService_CommonServicePort_Client;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.domain.ShippingAddressConfig;
import com.xuanfeiyang.erp.service.TrackerApplyResult;
import com.xuanfeiyang.erp.service.express.sf.DeclareinvoiceDTO;
import com.xuanfeiyang.erp.service.express.sf.OrderDTO;
import com.xuanfeiyang.erp.service.express.sf.SenderModel;
import com.xuanfeiyang.erp.service.express.sf.XMLUtil;

/**
 * 顺丰俄罗斯
 * 
 * @author Adam
 *
 */
public class SfRussiaTrackerApplier extends AbstractTrackerApplier {
	
	private static Logger logger = LoggerFactory.getLogger(SfRussiaTrackerApplier.class);
	
	final static String SERVICE_NAME = "OrderService";

	// 接入编码
	final static String clientCode = App.getConfig("express.sf.russia.clientcode");
	// 校验码
	final static String checkWord = App.getConfig("express.sf.russia.checkword");
	
	// 收件城市代码  key-国家, value-城市代码
	final static Map<String, String> deliveryCodeMap;
	static {
		Map<String, String> tmp = new HashMap<>();
		tmp.put("RU", "MOW");
		tmp.put("LT", "VNO");
		tmp.put("LV", "RIX");
		tmp.put("EE", "TLL");
		tmp.put("SE", "ARN");
		tmp.put("NO", "OSL");
		tmp.put("FI", "HEL");
		tmp.put("BY", "MSQ");
		tmp.put("UA", "KBP");
		tmp.put("PL", "WAW");
		
		deliveryCodeMap = Collections.unmodifiableMap(tmp);
	}
	
	// 快件产品类别: 9 顺 E 宝平邮, 10 顺 E 宝挂号
	private final static Map<String, String> expressTypeMap;
	static {
		Map<String, String> temp = new HashMap<>();
		temp.put("俄罗斯小包平邮", "9");
		temp.put("俄罗斯小包挂号", "10");
		expressTypeMap = Collections.unmodifiableMap(temp);
	}
	
	public TrackerApplyResult doApply(Order order) throws Exception {
		
		String xml = this.assembleOrderXml(order);
		logger.info("sf request xml ==> {}", xml);
		
		String resXml = this.requestWebservice(xml);
		logger.info("sf response xml ==> {}", resXml);
		
		TrackerApplyResult result = this.parseResponseXml(resXml);
		result.setOrderId(order.getId());
		
		return result;
	}
	
	// 解析响应的 xml 内容
	private TrackerApplyResult parseResponseXml(String resXml) {
		TrackerApplyResult result = new TrackerApplyResult();
		
		Document document = null;
		
		try {
			document = DocumentHelper.parseText(resXml);
		} catch (DocumentException e) {
			logger.error("解析文档出错, 错误: {}, 文档: {}", e.getMessage(), resXml);
		} 
		
		Element root = document.getRootElement();
		String head = root.elementText("Head");
		if ("ERR".equals(head)) {
			result.setSuccess(false);
			
			Element err = root.element("ERROR");
			String errCode = err.attributeValue("code");
			String errMsg = err.getText();
			
			result.setMessage(String.format("%s - %s", errCode, errMsg));
			
		} else if ("OK".equals(head)) {
			Element or = root.element("Body").element("OrderResponse");
			String orderId = or.attributeValue("orderid");
			String mailNo = or.attributeValue("mailno");
			String agentMailNo = or.attributeValue("agent_mailno");
			result.setOrderId(NumberUtils.toInt(orderId));
			if (StringUtils.isNotBlank(agentMailNo)) {
				result.setTrackNumber(agentMailNo);
				result.setTrackNumberRef(mailNo);
			} else {
				result.setTrackNumber(mailNo);
			}
			result.setSuccess(true);
		}
		
		return result;
	}
	
	// 请求Webservice， 这里使用 cxf客户端， xfire报错
	private String requestWebservice(String xml) throws Exception {
		return IService_CommonServicePort_Client.execute(xml);
	}

	private String assembleOrderXml(Order order) {
		String expressType = expressTypeMap.get(order.getShippingName());
		checkArgument(expressType != null, "不支持的发货方式：%s, 目前支持：%s", order.getShippingName(), expressTypeMap.keySet().toString());
		
		SenderModel senderModel = new SenderModel();
		// <Head>接入编码,校验码</Head>
		senderModel.setHeadText(clientCode + "," + checkWord);
		senderModel.setServiceName(SERVICE_NAME);
		
		double totalWeight = 0;
		double totalCost = 0;
		List<DeclareinvoiceDTO> lstDatas = new ArrayList<DeclareinvoiceDTO>();
		for (OrderItem tmp : order.getItems()) {
			OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) tmp;
			
			int quantity = item.getItemQuantity() != null ? item.getItemQuantity() : 1;
			
			double weight = item.getWeight()  != null ? item.getWeight().doubleValue() * quantity : 0;
			totalWeight += weight;
			
			double cost = item.getDeclarationCost() != null ? item.getDeclarationCost().doubleValue() : 0;
			totalCost += cost;
			
			DeclareinvoiceDTO declareinvoiceDTO = new DeclareinvoiceDTO();
			declareinvoiceDTO.setName(item.getDeclarationNameEn());
			//declareinvoiceDTO.setHscode(item.getCustomsCode());
			//declareinvoiceDTO.setEname(item.getDeclarationNameEn());
			declareinvoiceDTO.setUnit("PCE");
			declareinvoiceDTO.setCount(String.valueOf(item.getItemQuantity()));
			declareinvoiceDTO.setAmount(String.valueOf(item.getDeclarationCost()));
			declareinvoiceDTO.setWeight(String.valueOf(weight));
			declareinvoiceDTO.setCurrency("USD");
			declareinvoiceDTO.setSource_area("China");
			lstDatas.add(declareinvoiceDTO);
		}
		
		@SuppressWarnings("rawtypes")
		Map<Object, List> datas = new HashMap<Object, List>();
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getId().toString());
		orderDTO.setExpressType(expressType);
		orderDTO.setCustid(App.getConfig("express.sf.russia.custid"));
		
		ShippingAddressConfig sac = order.getShippingAddressConfig();
		Preconditions.checkArgument(sac != null && 
				StringUtils.isNotBlank(sac.getName()), "未配置地址信息或必要字段为空");
		
		orderDTO.setJ_country(sac.getCountry());
		orderDTO.setJ_province(sac.getProvince());
		orderDTO.setJ_city(sac.getCity());
		orderDTO.setJ_county(sac.getDistrict());
		orderDTO.setJ_company(sac.getCompany());
		orderDTO.setJ_contact(sac.getName());
		orderDTO.setJ_tel(sac.getTelphone());
		orderDTO.setJ_mobile(sac.getMobile());
		orderDTO.setJ_address(sac.getStreet());
		orderDTO.setJ_post_code(sac.getPostcode());
		
		OrderBuyerInfo buyer = order.getBuyinfo();
		String countryCode = buyer.getShippingCountry() != null ? buyer.getShippingCountry() : buyer.getShippingCountryName();
		String deliveryCode = deliveryCodeMap.get(countryCode);
		Preconditions.checkArgument(StringUtils.isNotBlank(deliveryCode), "不支持的国家: %s", countryCode);
		
		orderDTO.setD_country(countryCode);
		orderDTO.setD_province(buyer.getShippingState());
		orderDTO.setD_city(buyer.getShippingCity());
		orderDTO.setD_county("");
		orderDTO.setD_company(buyer.getShippingName());
		orderDTO.setD_contact(buyer.getShippingName());
		orderDTO.setD_tel(buyer.getShippingPhone());
		orderDTO.setD_mobile(buyer.getShippingMobile());
		String address = StringUtils.trimToEmpty(buyer.getShippingStreet1());
		String address2 = StringUtils.trimToEmpty(buyer.getShippingStreet2());
		address += (address2.isEmpty() ? "" : (" " + address2));
		
		orderDTO.setD_address(address);
		orderDTO.setD_post_code(buyer.getShippingPostcode());
		orderDTO.setD_deliverycode(deliveryCode);
		
		orderDTO.setParcelQuantity("1");
		
		orderDTO.setDeclared_value(String.valueOf(totalCost));
		orderDTO.setDeclared_value_currency("USD");
		orderDTO.setCargoTotalWeight(String.valueOf(totalWeight));
		// orderDTO.setReturnsign("Y");
		//orderDTO.setD_email(buyer.getBuyerEmail());
		//orderDTO.setOperateFlag("0");
		
		
		
		datas.put(orderDTO, lstDatas);
		
		senderModel.setDatas(datas);
		
		return XMLUtil.modelToXml(senderModel);
	}
	/*
	public static void main(String[] args) throws Exception {
		
		OrderItemWithShippingInfo item = new OrderItemWithShippingInfo();
		item.setSku("S033693000005");
		item.setDeclarationNameCn("挂钟");
		item.setDeclarationNameEn("Wall Clocks");
		item.setItemTitle("Wall Clocks");
		item.setItemQuantity(1);
		item.setDeclarationCost(new BigDecimal(10));
		item.setWeight(new BigDecimal(0.058));
		item.setCustomsCode("123123123");
		
		List<OrderItem> items = new ArrayList<>();
		items.add(item);
		
		OrderBuyerInfo buyer = new OrderBuyerInfo();
		buyer.setShippingName("us Lee");
		buyer.setShippingStreet1("31 Harefoot close");
		buyer.setShippingStreet2("");
		buyer.setShippingPostcode("XXXX");
		buyer.setShippingCity("Northampton");
		buyer.setShippingState("");
		buyer.setShippingCountryName("LV");
		buyer.setShippingPhone("65322512");
		buyer.setBuyerEmail("sandxos@yahoo.com");
		
		Order order = new Order();
		order.setBuyinfo(buyer);
		order.setId(NumberUtils.toInt(DateFormatUtils.format(new Date(), "MMddHHmmss")));
		order.setShippingName("俄罗斯小包平邮");
		
		order.setItems(items);
		
		ShippingAddressConfig sac = new ShippingAddressConfig();
		sac.setName("zhang san");
		sac.setCompany("xuan feiyang");
		sac.setCountry("China");
		sac.setProvince("guangdongsheng");
		sac.setCity("shenzhenshi");
		sac.setDistrict("baoanqu");
		sac.setStreet("sasdfasf sdfasf a asdf a");
		sac.setPostcode("518000");
		sac.setMobile("12312312312");
		sac.setTelphone("123123123");
		sac.setEmail("zh@a.com");
		
		order.setShippingAddressConfig(sac);
		
		TrackerApplyResult result = new SfRussiaTrackerApplier().apply(order);
		System.out.println(result);
		
		
		
//		TrackerApplyResult r = new SfTrackerApplier().apply(null);
//		System.out.println(r);

	}
	*/
}

package com.xuanfeiyang.erp.service.express;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.xuanfeiyang.erp.service.express.winit.ApiClient;
import com.xuanfeiyang.erp.service.express.winit.RequestMsg;
import com.xuanfeiyang.erp.service.express.winit.WinitWarehouse;

/**
 * 万邑通物流跟踪号申请
 * @author bryant
 *
 */
public class WinitTrackerApplier extends AbstractTrackerApplier {
	
	private static Logger logger = LoggerFactory.getLogger(WinitTrackerApplier.class);
	
	@Override
	public TrackerApplyResult doApply(Order order) throws Exception {
		
		String response = this.sendWinitRequest(order);
		
		TrackerApplyResult trackerResult = this.parseResponse(response);
		trackerResult.setOrderId(order.getId());
		
		return trackerResult;
	}
	
	//解析响应的信息
	@SuppressWarnings({ "serial", "unchecked" })
	private TrackerApplyResult parseResponse(String res) {
		
		TrackerApplyResult result = new TrackerApplyResult();
		if(res != null) {
			Map<String, Object> resMap = new Gson().fromJson(res, new TypeToken<Map<String, Object>>() {}.getType());
			if(resMap.get("code") != null){
				String code = resMap.get("code").toString();
				if(resMap.containsKey("code") && "0".equals(code)) {
					Map<String, String> m = (Map<String, String>)resMap.get("data");
					result.setSuccess(true);
					result.setTrackNumber(m.get("trackingNo"));
					result.setTrackNumberRef(m.get("orderNo"));
				} else {
					result.setSuccess(false);
					result.setMessage(resMap.get("msg").toString());
				}
			}
		} else {
			result.setSuccess(false);
			result.setMessage("返回为空");
		}
		return result;
	}
	
	//提交请求
	private String sendWinitRequest(Order order) {
		
		//订单收件人信息
		OrderBuyerInfo buyerInfo = order.getBuyinfo();
		
		String token = App.getConfig("express.winit.token");
		RequestMsg  requestMsg = new RequestMsg();
		requestMsg.setAction("isp.order.createOrder");
		requestMsg.setApp_key(App.getConfig("express.winit.username"));
		Map<String, Object> data = new TreeMap<String, Object>();
		data.put("buyerAddress1", buyerInfo.getShippingStreet1() == null ? "" : buyerInfo.getShippingStreet1());
		data.put("buyerAddress2", buyerInfo.getShippingStreet2() == null ? "" : buyerInfo.getShippingStreet2());
		data.put("buyerCity", buyerInfo.getShippingCity());
		String contactNo = buyerInfo.getShippingPhone() == null ? buyerInfo.getShippingMobile() : buyerInfo.getShippingPhone(); 
		data.put("buyerContactNo", contactNo);
		String country = buyerInfo.getShippingCountry() == null ? buyerInfo.getShippingCountryName() : buyerInfo.getShippingCountry();
		data.put("buyerCountry", country);
		data.put("buyerEmail", buyerInfo.getBuyerEmail() == null ? "" : buyerInfo.getBuyerEmail());
		data.put("buyerName", buyerInfo.getShippingName());
		data.put("buyerState", buyerInfo.getShippingState());
		data.put("buyerZipCode", buyerInfo.getShippingPostcode());
		data.put("dispatchType", "P");
		data.put("packageList", buildPackageList(order));
		data.put("refNo", order.getId());
		data.put("shipperAddrCode", "XFY1044");
		
		//查询物流产品编码和仓库编码
		WinitWarehouse winitWarehouse = new WinitWarehouse();
		Map<String, String> code = winitWarehouse.findWinitWarehouse(order);
		data.put("warehouseCode", code.get("warehouseCode"));
		data.put("winitProductCode", code.get("winitProductCode"));
		logger.info("万邑通请求数据: {}", data);
		requestMsg.setData(data);
		requestMsg.setFormat("json");
		requestMsg.setLanguage("zh_CN");
		requestMsg.setPlatform("SELLERERP");
		requestMsg.setSign_method("md5");
		requestMsg.setTimestamp(getDate());
		requestMsg.setVersion("1.0");
		ApiClient api = new ApiClient();
		String warehouseRes = api.post(requestMsg, App.getConfig("express.winit.url"), token);
		logger.info("查询产品编码和仓库编码返回数据：{}", warehouseRes);
		return warehouseRes;
		
	}
	
	//生成包裹列表
	@SuppressWarnings("unused")
	private List<Map<String, Object>> buildPackageList(Order order){
		
		List<OrderItem> items = order.getItems();
		int totalQuantity = 0;
		double totalWeight = 0;

		for (OrderItem tmp : items) {
			OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) tmp;

			int quantity = item.getItemQuantity() != null ? item.getItemQuantity() : 1;
			totalQuantity += quantity;
			
			double weight = item.getWeight() != null ? item.getWeight().doubleValue() * quantity : 0;
			totalWeight += weight;
		}
		if(totalWeight == 0){
			totalWeight = 0.1;
		}
		
		List<Map<String, Object>> packageList = new ArrayList<Map<String, Object>>();
		Map<String, Object> packageMap = new TreeMap<String, Object>();
		packageMap.put("height", "5");
		packageMap.put("length", "30");
		packageMap.put("merchandiseList", buildMerchandiseList(order));
		packageMap.put("weight", String.valueOf(new Double(totalWeight)));
		packageMap.put("width", "10");
		packageList.add(packageMap);
		return packageList;
	}
		
	//生成商品列表
	private List<Map<String, Object>> buildMerchandiseList(Order order){
		
		List<OrderItem> items = order.getItems();

		double totalCost = 0;
		String enName = null, cnName = null;

		for (OrderItem tmp : items) {
			OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) tmp;
			double cost = item.getDeclarationCost() != null ? item.getDeclarationCost().doubleValue() : 0;
			totalCost += cost;
			cnName = item.getDeclarationNameCn();
			enName = item.getDeclarationNameEn();
		}
		List<Map<String, Object>> merList = new ArrayList<Map<String, Object>>();
		Map<String, Object> merMap = new TreeMap<String, Object>();
		merMap.put("declaredNameCn", cnName);
		merMap.put("declaredNameEn", enName);
		merMap.put("declaredValue", totalCost);
		merList.add(merMap);
		return merList;
	}
	
	private static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		return date;
	}
	
}

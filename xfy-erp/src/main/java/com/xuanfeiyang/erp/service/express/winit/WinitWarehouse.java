package com.xuanfeiyang.erp.service.express.winit;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Order;

/**
 * 查询验货仓
 * @author Administrator
 *
 */
public class WinitWarehouse {
	
	/**
	 *  "国际递送-中国至全球-新邮小包（非挂号）" : "WP-SGP002",  （无跟踪号，有订单号） 
	 *  "国际递送-中英线-标准服务（挂号信函）" : "WH-UK001"
	 *  "国际递送-中英线-标准服务（挂号小包）" : "WH-UK002"
	 *  "国际递送-中英线-标准服务（非挂号小包）" : "WH-UK003"
	 *  "国际递送-中美线-UPS速快（包裹，2~7自然日）" : "WE-UPS001"
	 *  "国际递送-中美线-UPS快捷（大货，5~11自然日）" : "WE-UPS004"
	 *  "万邑邮选-马来西亚渠道（平邮）" : "WP-MYP001"
	 *  "万邑邮选-马来西亚渠道（挂号）" : "WP-MYP002"
	 *  "万邑邮选-爱沙尼亚渠道（平邮）" :  "WP-EEP002"
	 *  "万邑邮选-爱沙尼亚渠道（挂号）" :  "WP-EEP001"
	 *  "万邑邮选-新加坡渠道（挂号）" : "WP-SGP003"
	 *  "万邑邮选-新加坡渠道（平邮）" : "WP-SGP004"
	 *  "万邑邮选-荷兰渠道（挂号）" "WP-NLP011"
	 *  "万邑邮选-荷兰渠道（平邮）" "WP-NLP012"
	 *  "万邑邮选-普通渠道（挂号）-北京" "WP-CNP007"
	 *  "万邑邮选-普通渠道（平邮）-北京" "WP-CNP004"
	 *  "万邑邮选-俄罗斯SPSR渠道（挂号）"  "WP-SRP001"
	 *  "万邑邮选-芬兰渠道（挂号）" "WP-FIP001"
	 *  "万邑邮选-德国渠道（挂号）" "WP-DEP001"
	 *  "万邑邮选-德国渠道（平邮）" "WP-DEP002"
	 *  
	 *  
	 */
	private static Logger logger = LoggerFactory.getLogger(WinitWarehouse.class);
	
	private final static Map<String, String> expressTypeMap;
	static {
		Map<String, String> temp = new HashMap<>();
		temp.put("万邑通中国至全球-新邮小包（非挂号）", "WP-SGP002");
		temp.put("万邑通中英线（挂号信函）", "WH-UK001");
		temp.put("万邑通中英线（挂号小包）", "WH-UK002");
		temp.put("万邑通中英线（非挂号小包）", "WH-UK003");
		temp.put("万邑通中美线-UPS速快（包裹）", "WE-UPS001");
		temp.put("万邑通中美线-UPS快捷（大货）", "WE-UPS004");
		temp.put("万邑通马来西亚平邮", "WP-MYP001");
		temp.put("万邑通马来西亚挂号", "WP-MYP002");
		temp.put("万邑通爱沙尼亚平邮", "WP-EEP002");
		temp.put("万邑通爱沙尼亚挂号", "WP-EEP001");
		temp.put("万邑通新加坡挂号", "WP-SGP003");
		temp.put("万邑通新加坡平邮", "WP-SGP004");
		temp.put("万邑通荷兰挂号（含电）", "WP-NLP001");
		temp.put("万邑通荷兰挂号（不含电）", "WP-NLP011");
		temp.put("万邑通荷兰平邮（含电）", "WP-NLP002");
		temp.put("万邑通荷兰平邮（不含电）", "WP-NLP012");
		temp.put("万邑通北京挂号", "WP-CNP007");
		temp.put("万邑通北京平邮", "WP-CNP004");
		temp.put("万邑通俄罗斯挂号", "WP-SRP001");
		temp.put("万邑通芬兰挂号", "WP-FIP001");
		temp.put("万邑通德国挂号", "WP-DEP001");
		temp.put("万邑通德国平邮", "WP-DEP002");
		
		
		expressTypeMap = Collections.unmodifiableMap(temp);
	}
	@SuppressWarnings({ "serial", "unchecked" })
	public Map<String, String> findWinitWarehouse(Order order){
		
		//返回数据
		Map<String, String> response = new TreeMap<String, String>();
	
		//产品编码
		String productCode = expressTypeMap.get(order.getShippingName());
		response.put("winitProductCode", productCode);
		logger.info("产品编码winitProductCode：{}",productCode);
		//查询验货仓信息
		String token = App.getConfig("express.winit.token");
		RequestMsg  requestMsg = new RequestMsg();
		requestMsg.setAction("baseData.inspWarehouseList");
		requestMsg.setApp_key(App.getConfig("express.winit.username"));
		Map<String, Object> data = new TreeMap<String, Object>();
		data.put("productCode", productCode);
		requestMsg.setData(data);
		requestMsg.setFormat("json");
		requestMsg.setLanguage("zh_CN");
		requestMsg.setPlatform("SELLERERP");
		requestMsg.setSign_method("md5");
		requestMsg.setTimestamp(getDate());
		requestMsg.setVersion("1.0");
		ApiClient api = new ApiClient();
		String warehouseRes = api.post(requestMsg, App.getConfig("express.winit.url"), token);
		logger.info("查询出的验货仓信息：{}",warehouseRes);
		
		//从返回数据中查找到仓库编码  warehouseCode
		String warehouseName = null;
		String warehouseCode = null;
		Map<String, Object> warehouseMap = new Gson().fromJson(warehouseRes, new TypeToken<Map<String, Object>>() {}.getType());
		if(warehouseMap.get("data") != null && !warehouseMap.get("data").equals("")) {
			List<Object> dataList = (List<Object>)warehouseMap.get("data");
			if(dataList != null) {
				for (Object obj : dataList) {
					Map<String, String> warehouse = (Map<String, String>) obj;
					warehouseName = warehouse.get("warehouseName");
					if(warehouseName.contains("深圳") || warehouseName.contains("ShenZhen")) {
						warehouseCode = warehouse.get("warehouseCode");
						response.put("warehouseCode", warehouseCode);
						logger.info("仓库编码：{}",warehouseCode);
					}
				}
			}
		}
		return response;
	}
	
	private static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		return date;
	}
	
}

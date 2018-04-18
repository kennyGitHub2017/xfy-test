package com.xuanfeiyang.erp.web.app;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xuanfeiyang.erp.service.GoodsService;
import com.xuanfeiyang.erp.service.OrderService;
import com.xuanfeiyang.erp.service.UserService;

/**
 * app订单数据接口
 * @author bernard
 *
 */
@Controller
@RequestMapping("/app/order")
public class AppOrderController{
	@Resource
	private GoodsService goodsService;
	@Resource
	private OrderService orderService;
	@Resource
	private UserService userService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AppOrderController.class);
	
	/**
	 * 订单审核
	 * @param id
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="audit")
	public Map<String,Object> auditOrder(@RequestParam(value="id",required=true)Integer id,@RequestParam(value="userName",required=true)String userName){
		Map<String,Object> retMap = new HashMap<>();
		try{
			orderService.audit(id,userName);
			retMap.put("result", true);
		}catch(Exception e){
			logger.error("订单["+id+"]审核失败:" + e.getMessage());
			retMap.put("result", false);
			retMap.put("error", "订单["+id+"]审核失败:" + e.getMessage());
		}
		 return retMap;
	}
	
	
	/**
	 * 订单暂停/启用
	 * @param id
	 * @param opt
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="pause-enable")
	public Map<String,Object>  orderPauseEnable(@RequestParam(value="id",required=true)Integer id,@RequestParam(value="opt",required=true)Integer opt,
			@RequestParam(value="userName",required=true)String userName){
		Map<String,Object> retMap = new HashMap<>();
		try{
			orderService.orderSuspendAndEnable(Arrays.asList(new Integer[]{id}), opt,userName);
			retMap.put("result", true);
		}catch(Exception e){
			retMap.put("result", false);
			retMap.put("error", e.getMessage());
			logger.error("订单["+id+"]" +(opt==1?"暂停错误:":"启用错误:") +e.getMessage());
		}
		return retMap;
	}
}

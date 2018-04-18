package com.xuanfeiyang.erp.web.app;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuanfeiyang.erp.service.PayOrderService;


/***
 * 
 * 手机 微信支付 下单接口
 * 
 * 
 * @author Administrator
 *
 */
@RequestMapping("/app/payOrder")
@Controller
public class PayOrderController {
		@Resource
		private PayOrderService payOrderService;
		
		
		/***
		 * 下单接口
		 * @param amount
		 * @param ip
		 * @param body
		 * @param sellerId
		 * @return
		 */
		
		@RequestMapping(value="weixinPay", method=RequestMethod.POST)
		@ResponseBody
		public Map<String,String> weixinPay(String amount,String ip,String body,Integer sellerId){
			Map<String, String> map = new HashMap<String, String>();
			map = this.payOrderService.weixinPaying(amount, body, ip, sellerId);
			return map;
		}
		
		
		
		/****
		 * 
		 * 支付结果返回 结果
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("weixinPayNotify")
		@ResponseBody
		public Map<String,String> weixinPayNotify(HttpServletRequest request) throws Exception{
			Map<String, String> map = new HashMap<String, String>();
			InputStream inStream = request.getInputStream();
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
					outSteam.write(buffer, 0, len);
			}
			
			outSteam.close();
			inStream.close();
			
			String info  = new String(outSteam.toByteArray(),"utf-8");
			String result = this.payOrderService.getNotifyInfo(info);
			map.put("result", result);
			
			return map;
		}
	

}

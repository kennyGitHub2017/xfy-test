package com.xuanfeiyang.erp.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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
import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.PayOrderDao;
import com.xuanfeiyang.erp.dao.SellerDepositDao;
import com.xuanfeiyang.erp.dao.SellerDepositLogDao;
import com.xuanfeiyang.erp.domain.PayOrder;
import com.xuanfeiyang.erp.domain.SellerDeposit;
import com.xuanfeiyang.erp.domain.SellerDepositLog;
import com.xuanfeiyang.erp.param.PayOrderParams;
import com.xuanfeiyang.erp.service.PayOrderService;
import com.xuanfeiyang.erp.service.TableKeyService;
import com.xuanfeiyang.erp.util.EncryptUtils;

/***
 * 微信扫码支付
 * @author Administrator
 *
 */
@Service
public class PayOrderServiceImpl implements PayOrderService{
	private static Logger logger = LoggerFactory.getLogger(PayOrderServiceImpl.class);
	
	@Resource
	private PayOrderDao payOrderDao;
	
	@Resource
	private SellerDepositDao sellerDepositDao;
	
	@Resource
	private SellerDepositLogDao sellerDepositLogDao;
	
	@Resource
	private TableKeyService tableKeyService;
	
	
	/***
	 * 微信支付下单
	 * 
	 */
	@Override
	public String weixinPay(String amount, String body, String ip,Integer sellerId) {
		Integer tradeTypeNative = 1;
		Integer totalFee = Integer.parseInt(amount) * 100;
		String result = this.requestParamXml(totalFee.toString(), body, ip,sellerId, tradeTypeNative);//拼装参数
		logger.info("requestWeixin {}",result);
		String code = this.sendPost(result);//提交参数
		return code;
	}
	

	/***
	 * 微信支付下单
	 * 手机调用接口
	 * [APP]
	 */
	@Override
	public Map<String, String> weixinPaying(String amount, String body, String ip,
			Integer sellerId) {
		Integer tradeTypeApp = 2;
		Integer totalFee = Integer.parseInt(amount) * 100;
		String result = this.requestParamXml(totalFee.toString(), body, ip,sellerId, tradeTypeApp);//拼装参数
		return this.returnInfoMap(result);
	}
	
	
	/***
	 * 
	 * POST 提交参数,返回支付二维码链接
	 * @param param
	 */
	private String sendPost(String param) {

		String resultCodeUrl = ""; // 返回数据
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		HttpEntity entity = new StringEntity(param, "UTF-8");
		post.setEntity(entity);
		post.setHeader("Content-Type", "application/xml; charset=UTF-8");
		HttpResponse httpResponse = null;
		String response = null;

		try {
			httpResponse = httpClient.execute(post);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				response = EntityUtils.toString(httpResponse.getEntity(),
						"UTF-8");
				logger.info("responseWeixin{}", response);
			}

			Document document = null;
			try {
				document = DocumentHelper.parseText(response);
			} catch (DocumentException e) {
			}

			Element root = document.getRootElement();

			String returnCode = root.element("return_code").getText()
					.toString();
			if (returnCode.equals("FAIL")) {
				resultCodeUrl = "error";
			} else if (returnCode.equals("SUCCESS")) {

				String codeUrl = root.element("code_url").getText().toString();
				resultCodeUrl = codeUrl;
			}

		} catch (ClientProtocolException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultCodeUrl;
	}
	
	
	/**
	 * 请求参数拼装成XML格式
	 * @param amount
	 * @param body
	 * @param ip
	 * @return
	 */
	private String requestParamXml(String amount, String body, String ip, Integer sellerId,Integer tradeType) {
		Map<String, String> map = this.getData(amount, body, ip, sellerId,tradeType);

		String param = "<xml>";

		for (String key : map.keySet()) {
			param += "<" + key + ">" + map.get(key) + "</" + key + ">";
		}

		param += "</xml>";
	
		return param;
	
	}
	
	
	/***
	 * 将提交参数拼装成MAP
	 * @param amount
	 * @param body
	 * @param ip
	 */
	private Map<String, String> requestParamMap(String amount, String body, String ip,Integer tradeType){
		String thisTradeType = "NATIVE";
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", "");
		map.put("mch_id", "");
		map.put("nonce_str", this.getNonceStr());
		map.put("body", body);
		map.put("out_trade_no", this.tradeNo());
		map.put("total_fee", amount);
		map.put("spbill_create_ip", ip);
		map.put("notify_url", "http://www.xxx.com:8080/weixinPayNotify");
		map.put("trade_type", thisTradeType);
		return map;
	}
	
	
	/****
	 * 获得签名value
	 * @param amount
	 * @param body
	 * @param ip
	 * @return
	 */
	private Map<String, String> getData(String amount, String body, String ip,Integer sellerId,Integer tradeType) {
		Map<String, String> map = this.requestParamMap(amount, body, ip,tradeType);
		
		List<String> keyList = new ArrayList<>();
		keyList.addAll(map.keySet());
		Collections.sort(keyList);
		
		String stringA = "";
		
		for (String key : keyList) {
			stringA += key + "=" + map.get(key)+"&";
		}
		String signStr = stringA.substring(0, stringA.length()-1);

		String stringSignTemp = signStr + "&key=" + "123UKwZ";

		map.put("sign", EncryptUtils.encryptByMD5(stringSignTemp).toUpperCase());
		return map;
	}

	/**
	 * 随机产生商户订单号
	 * [商户订单号字段]
	 * @return
	 * 2015082511520710001
	 */
	private String tradeNo() {
		String flowno = RandomStringUtils.randomNumeric(4);
		StringBuilder sb = new StringBuilder();
		sb.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		sb.append(flowno);
		return sb.toString();
	}
	
	
	/**
	 * 16位随机字符串
	 * [随机字符串 字段]
	 * @return
	 * NPpDLfJfNAYmCgQj
	 */
	private String getNonceStr() {
		return RandomStringUtils.randomAlphabetic(16);
	}
	
	
	/***
	 * 
	 * 保存支付订单信息
	 * @param map
	 * 
	 */
	private void addPayOrder(Map<String, String> map,Integer sellerId) {
		BigDecimal amount = new BigDecimal(map.get("total_fee"));
		BigDecimal divideNo = new BigDecimal(100);
		BigDecimal fee = amount.divide(divideNo);
		
		PayOrder po = new PayOrder();
		po.setAppId(map.get("appid"));
		po.setBody(map.get("body"));
		po.setMchId(map.get("mch_id"));
		po.setNonceStr(map.get("nonce_str"));
		po.setOutTradeNo(map.get("out_trade_no"));
		po.setSpbillCreateIp(map.get("spbill_create_ip"));
		po.setTradeType(map.get("trade_type"));
		po.setCreatedTime(new Date());
		po.setTotalFee(fee);
		po.setSign(map.get("sign"));
		po.setSellerId(sellerId);
		po.setStatus(0);
		po.setType(0);//微信支付类型
		this.payOrderDao.add(po);
	}
	
	
	
	/**
	 * 解析充值后返回结果，并进行逻辑处理
	 */
	@Override
	public String getNotifyInfo(String res) {
		StringBuffer bf = new StringBuffer();
		bf.append("<xml>");
		Document document = null;
		try {
			document = DocumentHelper.parseText(res);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Element root = document.getRootElement();
		String returnCode = root.element("return_code").getText().toString();

		if (returnCode.equals("FAIL")) {

			String returnMsg = root.element("return_msg").getText().toString();
			bf.append("<return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA["+returnMsg+"]]></return_msg>");

		} else if (returnCode.equals("SUCCESS")) {

			String outTradeNo = root.element("out_trade_no").getText()
					.toString();// 返回商户订单号
			String time = root.element("time_end").getText().toString();// 充值完成时间
			
			PayOrder pOrder = this.payOrderDao.getByTradeNo(outTradeNo);
			
			if(pOrder != null && pOrder.getStatus() == 0){ //只有充值为成功 才充值
				this.payOrderDao.updateByOutTradeNo(1, time, outTradeNo);// 更新充值订单状态
				this.addDeposit(outTradeNo);// 资金流水增加记录
				bf.append("<return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg>");
			}
		}
		bf.append("</xml>");
		return bf.toString();
	}
	
	
	
	/***
	 * 增加卖家保证金相关信息
	 * @param outTradeNo
	 */
	private void addDeposit(String outTradeNo){
		PayOrder po = this.payOrderDao.getByTradeNo(outTradeNo);
		SellerDeposit sd1 = this.sellerDepositDao.load(po.getSellerId());//查询卖家资金流水信息
		BigDecimal resultTotalFee = this.deductionFee(po.getTotalFee());//充值金额计算

		BigDecimal  beforeAmount = new BigDecimal(0);
		if(sd1 == null) {
			
			SellerDeposit sd =  new SellerDeposit();
			sd.setSellerId(po.getSellerId());
			sd.setDeposit(resultTotalFee);
			sd.setCreatedTime(new Date());
			sd.setLastUpdatedTime(new Date());
			this.sellerDepositDao.insert(sd);
			
		} else {
			
			SellerDeposit sd =  new SellerDeposit();
			sd.setSellerId(po.getSellerId());
			sd.setDeposit(resultTotalFee.add(sd1.getDeposit()));
			sd.setLastUpdatedTime(new Date());
			this.sellerDepositDao.update(sd);
			beforeAmount = sd1.getDeposit();
		}
		
		SellerDepositLog log = new SellerDepositLog();
		log.setSellerId(po.getSellerId());
		log.setType(1);
		log.setBalanceBefore(beforeAmount);
		log.setBalanceAfter(resultTotalFee.add(beforeAmount));
		log.setAmount(resultTotalFee);
		log.setNote("微信充值");
		log.setCreatedTime(new Date());
		this.sellerDepositLogDao.insert(log);
	}

	
	/***
	 * 解析下单返回结果，拼装成map
	 * @param response
	 * @return
	 * [APP]
	 */
	private Map<String, String> readResultXml(String response){
		
		Map<String, String> map = new HashMap<String, String>();
		Document document = null;
		try {
			document = DocumentHelper.parseText(response);
		} catch (DocumentException e) {
		}
		
		Element root = document.getRootElement();
		String returnCode = root.element("return_code").getText().toString();
		
		map.put("return_code", returnCode);
		
		if(returnCode.equals("FAIL")) {
			
			String returnMsg = root.element("return_msg").getText().toString();
			map.put("return_msg", returnMsg);
			
		}else if(returnCode.equals("SUCCESS")){
			
			String returnMsg = root.element("return_msg").getText().toString();
			String appid = root.element("appid").getText().toString();
			String nonceStr = root.element("nonce_str").getText().toString();
			String sign = root.element("sign").getText().toString();
			String resultCode = root.element("result_code").getText().toString();
			String prepayId = root.element("prepay_id").getText().toString();
			String tradeType = root.element("trade_type").getText().toString();
			//String codeUrl = root.element("code_url").getText().toString();
		
			
			map.put("return_msg", returnMsg);
			map.put("appid", appid);
			map.put("nonce_str", nonceStr);
			map.put("sign", sign);
			map.put("result_code", resultCode);
			map.put("prepay_id", prepayId);
			map.put("trade_type", tradeType);
			//map.put("code_url", codeUrl);
			
		}
			return map;
	}


	
	/***
	 * 
	 * POST 提交参数,返回支付结果
	 * @param param 拼装下单参数
	 * [APP]
	 */
	private Map<String, String> returnInfoMap(String param) {
		Map<String, String> map = new HashMap<String, String>();
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		HttpEntity entity = new StringEntity(param, "UTF-8");
		post.setEntity(entity);
		post.setHeader("Content-Type", "application/xml; charset=UTF-8");
		HttpResponse httpResponse = null;
		String response = null;

		try {
			httpResponse = httpClient.execute(post);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				response = EntityUtils.toString(httpResponse.getEntity(),
						"UTF-8");
				logger.info("response {}", response);
			}

			map = this.readResultXml(response);

		} catch (ClientProtocolException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	private BigDecimal deductionFee(BigDecimal totalFee){
		BigDecimal compareFee = new BigDecimal(1000);
		if (totalFee.compareTo(compareFee) != -1) {
			BigDecimal rate = new BigDecimal(0.994);
			totalFee = totalFee.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return totalFee;
	}


	@Override
	public Page<PayOrder> findPage(PageRequest pageRequest,
			PayOrderParams params) {
		return this.payOrderDao.findPage(pageRequest, params);
	}


	@Override
	public List<PayOrder> export(PayOrderParams params) {
		return this.payOrderDao.export(params);
	}
}

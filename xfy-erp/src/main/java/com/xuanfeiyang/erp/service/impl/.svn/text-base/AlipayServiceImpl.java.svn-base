package com.xuanfeiyang.erp.service.impl;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.PayOrderDao;
import com.xuanfeiyang.erp.dao.SellerDepositDao;
import com.xuanfeiyang.erp.dao.SellerDepositLogDao;
import com.xuanfeiyang.erp.domain.PayOrder;
import com.xuanfeiyang.erp.domain.SellerDeposit;
import com.xuanfeiyang.erp.domain.SellerDepositLog;
import com.xuanfeiyang.erp.service.AlipayService;

/**
 * 支付宝扫码充值
 * @author Administrator
 *
 */
@Service
public class AlipayServiceImpl implements AlipayService {
	
	private static Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);
	/**
     * 支付宝消息验证地址
     */
	private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
	
	@Resource
	private PayOrderDao payOrderDao;
	
	@Resource
	private SellerDepositDao sellerDepositDao;
	
	@Resource
	private SellerDepositLogDao sellerDepositLogDao;
	
	/**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
	public Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
		//除去数组中的空值和签名参数
		Map<String, String> sPara = paraFilter(sParaTemp);
		//生成签名结果
		String mysign = buildRequestMysign(sPara);
		//签名结果与签名方式加入请求提交数组中
		sPara.put("sign", mysign);
		sPara.put("sign_type", "MD5");
		return sPara;
	}
	
	/**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	private String buildRequestMysign(Map<String, String> sPara) {
		String prestr = createLinkString(sPara);  //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String mysign = "";
		String key = App.getConfig("alipay.key");
		String input_charset = "utf-8";
		mysign = sign(prestr, key, input_charset);
		return mysign;
	}
	
	/**
	 * 签名字符串
	 * @param text 需要签名的字符串
	 * @param key 密钥
	 * @param input_charset
	 * @return
	 */
	private String sign(String text, String key, String input_charset) {
		text = text + key;
		return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	}
	
	/**
	 * 得到内容字符
	 * @param content
	 * @param charset
	 * @throws UnsupportedEncodingException
	 * @return
	 */
	private byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误，指定的编码集不对，您目前指定的编码集是：" + charset);
		}
	}
	
	
	/** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
	private String createLinkString(Map<String, String> params) {
		
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		
		String prestr = "";
		
		for (int i = 0; i< keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			
			if(i == keys.size() - 1) {
				prestr = prestr + key + "=" + value;     //拼接时，不包括最后一个&字符
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}
	
	
	/**
	 * 除去数组中的空值和签名参数
	 * @param sArray
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	private Map<String, String> paraFilter(Map<String, String> sArray) {
		
		Map<String, String> result = new HashMap<String, String>();
		if (sArray == null || sArray.size() <= 0) {
			return result;
		}
		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}
	
	
	
	/**
     * 验证消息是否是支付宝发出的合法消息
     * @param params 通知返回来的参数数组
     * @return 验证结果
	 * @throws UnsupportedEncodingException 
     */
	public boolean verify(Map<String, String> params) throws UnsupportedEncodingException {
		
		//判断responsetTxt是否为true，isSign是否为true
        //responseTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
        //isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String responseTxt = "true";
		if (params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id);
			logger.info("验证结果：responseTxt:{}",responseTxt);
			
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		
		boolean isSign = getSignVerify(params, sign);
		logger.info("验证结果：isSign:{}",isSign);
		//写日志记录（若要调试，请取消下面两行注释）
		/*String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign + "\n 返回来的参数：" + createLinkString(params);
		logResult(sWord);*/
		if (isSign && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
	
	/** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
	@SuppressWarnings("unused")
	private void logResult(String sWord) {
		FileWriter writer = null;
	    String log_path = "D:\\支付宝充值日志记录\\";
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
	private boolean getSignVerify(Map<String, String> Params, String sign) {
		//过滤空值，sign与sign_type参数
		Map<String,String> sParaNew = paraFilter(Params);
		logger.info("通知返回来的参数数组 sParaNew:{}",sParaNew);
		//获取待签名字符串
		String preSignStr = createLinkString(sParaNew);
		//获得签名验证结果
		boolean isSign = false;
		String key = App.getConfig("alipay.key");
		isSign = verify(preSignStr, sign, key, "utf-8");
		
		return isSign;
	}
	
	/**
	 * 验证签名字符串是否一致
	 * @param text
	 * @param sign
	 * @param key
	 * @param input_charset
	 * @return
	 */
	public boolean verify(String text, String sign, String key, String input_charset) {
		text = text + key;
		String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
		logger.info("验证签名字符串：sign:{}",sign);
		logger.info("生成的签名字符串：mysign:{}",mysign);
		if(mysign.equals(sign)) {
			return true;
		}
		else {
			return false;
		}
	}
	
   /**
    * 获取远程服务器ATN结果,验证返回URL
    * @param notify_id 通知校验ID
    * @return 服务器ATN结果
    * 验证结果集：
    * invalid命令参数不对. 出现这个错误，请检测返回处理中partner和key是否为空 
    * true 返回正确信息
    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    */
	private String verifyResponse(String notify_id) {
		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
		String partner = App.getConfig("alipay.partner");
		String verify_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;
		return checkUrl(verify_url);
	}
		
	/**
	 * 验证URL是否正确
	 * @param verify_url
	 * @return
	 */
	private String checkUrl(String urlvalue) {
		String inputLine = "";
		
		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}
		return inputLine;
	}
	
	/**
	 * 保存支付订单信息
	 * @param map
	 * @param sellerId
	 */
	public void addPayOrder (Map<String, String> map, String sellerId) {
		PayOrder po = new PayOrder();
		po.setPartner(map.get("partner"));
		po.setSellerEmail(map.get("seller_email"));
		if(sellerId != null && !sellerId.equals("") && !sellerId.equals("1")) {
			po.setSellerId(Integer.parseInt(sellerId));
		}
		po.setBody(map.get("subject"));
		Double totalFee = Double.parseDouble(map.get("total_fee"));
		BigDecimal fee = new BigDecimal(totalFee);
		po.setTotalFee(fee);
		po.setOutTradeNo(map.get("out_trade_no"));
		po.setTradeType("NATIVE");
		po.setCreatedTime(new Date());
		po.setSign(map.get("sign"));
		po.setStatus(0);
		po.setType(1);
		this.payOrderDao.add(po);
	}
	
	
	/**
	 * 生成随机商户订单号
	 * @return
	 */
	public String tradeNo() {
		String flowNo = RandomStringUtils.randomNumeric(4);
		StringBuilder sb = new StringBuilder();
		sb.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		sb.append(flowNo);
		return sb.toString();
	}
	
	/***
	 * 增加卖家保证金相关信息
	 * @param outTradeNo
	 */
	public void addDeposit(String outTradeNo){
		PayOrder po = this.payOrderDao.getByTradeNo(outTradeNo);
		SellerDeposit sd1 = this.sellerDepositDao.load(po.getSellerId());
		BigDecimal  beforeAmount = new BigDecimal(0);
		if(sd1 == null) {
			SellerDeposit seller = new SellerDeposit();
			seller.setSellerId(po.getSellerId());
			seller.setDeposit(po.getTotalFee());
			seller.setCreatedTime(po.getCreatedTime());
			seller.setLastUpdatedTime(new Date());
			this.sellerDepositDao.insert(seller);
		} else {
			SellerDeposit sd =  new SellerDeposit();
			sd.setSellerId(po.getSellerId());
			sd.setDeposit(po.getTotalFee().add(sd1.getDeposit()));
			sd.setCreatedTime(po.getCreatedTime());
			sd.setLastUpdatedTime(new Date());
			this.sellerDepositDao.update(sd);
			beforeAmount = sd1.getDeposit();
		}
		
		SellerDepositLog log = new SellerDepositLog();
		log.setSellerId(po.getSellerId());
		log.setType(1);
		if(sd1 == null) {
			log.setBalanceBefore(beforeAmount);
		} else {
			log.setBalanceBefore(sd1.getDeposit());
		}
		log.setBalanceAfter(po.getTotalFee().add(beforeAmount));
		log.setAmount(po.getTotalFee());
		log.setNote("支付宝充值");
		log.setCreatedTime(new Date());
		this.sellerDepositLogDao.insert(log);
		
	}

	
}

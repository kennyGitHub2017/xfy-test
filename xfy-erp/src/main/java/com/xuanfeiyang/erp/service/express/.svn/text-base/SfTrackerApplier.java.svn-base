package com.xuanfeiyang.erp.service.express;

import static com.google.common.base.Preconditions.checkArgument;
import it.sauronsoftware.base64.Base64;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.sf.ieca.business.sfexpress.appservice.SfexpressService_SfexpressServiceImplPort_Client;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.domain.ShippingAddressConfig;
import com.xuanfeiyang.erp.service.TrackerApplyResult;
import com.xuanfeiyang.erp.service.express.sf.DeclareinvoiceDTO;
import com.xuanfeiyang.erp.service.express.sf.OrderDTO;
import com.xuanfeiyang.erp.service.express.sf.PasswordEncryption;
import com.xuanfeiyang.erp.service.express.sf.SenderModel;
import com.xuanfeiyang.erp.service.express.sf.XMLUtil;

/**
 * 顺丰欧洲
 * 
 * @author Adam
 *
 */
public class SfTrackerApplier extends AbstractTrackerApplier {
	
	private static Logger logger = LoggerFactory.getLogger(SfTrackerApplier.class);
	
	final static String SERVICE_NAME = "OrderService";

	// 接入编码
	final static String clientCode = App.getConfig("express.sf.europe.clientcode");
	// 校验码
	final static String checkWord = App.getConfig("express.sf.europe.checkword");
	
	/* 快件产品类别: 
		A1： 欧洲小包挂号
		A2：欧洲小包平邮
		A3: 欧洲专递
		A5: 澳洲小包挂号
		A6: 澳洲小包平邮
		A9: 美国小包
		B1: 顺丰全球经济小包挂号
		B2：顺丰全球经济小包平邮
	*/
	private final static Map<String, String> expressTypeMap;
	static {
		Map<String, String> temp = new HashMap<>();
		temp.put("欧洲小包挂号", "A1");
		temp.put("欧洲小包平邮", "A2");
		expressTypeMap = Collections.unmodifiableMap(temp);
	}
	
	private final static Map<String, String> errorMsgMap;
	static {
		Map<String, String> temp = new HashMap<>();
		temp.put("6101", "请求数据缺少必选项");
		temp.put("6102", "寄件方公司名称为空");
		temp.put("6103", "寄方联系人为空");
		temp.put("6106", "寄件方详细地址为空");
		temp.put("6107", "到件方公司名称为空");
		temp.put("6108", "到件方联系人为空");
		temp.put("6111", "到件方地址为空");
		temp.put("6112", "到件方国家不能为空");
		temp.put("6114", "必须提供客户订单号");
		temp.put("6115", "到件方所属城市名称不能为空");
		temp.put("6116", "到件方所在县/区不能为空");
		temp.put("6117", "到件方详细地址不能为空");
		temp.put("6118", "订单号不能为空");
		temp.put("6119", "到件方联系电话不能为空");
		temp.put("6120", "快递类型不能为空");
		temp.put("6121", "寄件方联系电话不能为空");
		temp.put("6122", "筛单类别不合法");
		temp.put("6123", "运单号不能为空");
		temp.put("6124", "付款方式不能为空");
		temp.put("6125", "需生成电子运单, 货物名称等不能为空");
		temp.put("6126", "月结卡号不合法");
		temp.put("6127", "增值服务名不能为空");
		temp.put("6128", "增值服务名不合法");
		temp.put("6129", "付款方式不正确");
		temp.put("6130", "体积参数不合法");
		temp.put("6131", "订单操作标识不合法");
		temp.put("6132", "路由查询方式不合法");
		temp.put("6133", "路由查询类别不合法");
		temp.put("6134", "未传入筛单数据");
		temp.put("6135", "未传入订单信息");
		temp.put("6136", "未传入订单确认信息");
		temp.put("6137", "未传入请求路由信息");
		temp.put("6138", "代收货款金额传入错误");
		temp.put("6139", "代收货款金额小于 0 错误");
		temp.put("6140", "代收月结卡号不能为空");
		temp.put("6141", "无效月结卡号, 未配置代收货款上限");
		temp.put("6142", "超过代收货款费用限制");
		temp.put("6143", "是否自取件只能为 1 或 2");
		temp.put("6144", "是否转寄件只能为 1 或 2");
		temp.put("6145", "是否上门收款只能为 1 或 2");
		temp.put("6146", "回单类型错误");
		temp.put("6150", "订单不存在");
		temp.put("8000", "报文 参数不合法");
		temp.put("8001", "IP 未授权");
		temp.put("8002", "服务（功能）未授权");
		temp.put("8003", "查询单号超过最大限制");
		temp.put("8004", "路由查询条数超限制");
		temp.put("8005", "查询次数超限制");
		temp.put("8006", "已下单，无法接收订单确认请求");
		temp.put("8007", "此订单已经确认，无法接收订单确认请求");
		temp.put("8008", "此订单人工筛单还未确认，无法接收订单确认请求");
		temp.put("8009", "此订单不可收派, 无法接收订单确认请求。");
		temp.put("8010", "此订单未筛单, 无法接收订单确认请求。");
		temp.put("8011", "不存在该接入编码与运单号绑定关系");
		temp.put("8012", "不存在该接入编码与订单号绑定关系");
		temp.put("8013", "未传入查询单号");
		temp.put("8014", "校验码错误");
		temp.put("8015", "未传入运单号信息");
		temp.put("8016", "重复下单");
		temp.put("8017", "订单号与运单号不匹配");
		temp.put("8018", "未获取到订单信息");
		temp.put("8019", "订单已确认");
		temp.put("8020", "不存在该订单跟运单绑定关系");
		temp.put("8021", "接入编码为空");
		temp.put("8022", "校验码为空");
		temp.put("8023", "服务名为空");
		temp.put("8024", "未下单");
		temp.put("8025", "未传入服务或不提供该服务");
		temp.put("8026", "不存在的客户");
		temp.put("8027", "不存在的业务模板");
		temp.put("8028", "客户未配置此业务");
		temp.put("8029", "客户未配置默认模板");
		temp.put("8030", "未找到这个时间的合法模板");
		temp.put("8031", "数据错误，未找到模板");
		temp.put("8032", "数据错误，未找到业务配置");
		temp.put("8033", "数据错误，未找到业务属性");
		temp.put("8034", "重复注册人工筛单结果推送");
		temp.put("8035", "生成电子运单，必须存在运单号");
		temp.put("8036", "注册路由推送必须存在运单号");
		temp.put("8037", "已消单");
		temp.put("8038", "业务类型错误");
		temp.put("8039", "寄方地址错误");
		temp.put("8040", "到方地址错误");
		temp.put("8041", "寄件时间格式错误");
		temp.put("8042", "客户账号异常，请联系客服人员！");
		temp.put("8043", "该账号已被锁定，请联系客服人员！");
		temp.put("8044", "此订单已经处理中，无法接收订单修改请求");
		temp.put("4001", "系统发生数据错误或运行时异常");
		temp.put("4002", "报文解析错误");
		temp.put("9000", "身份验证失败");
		temp.put("9001", "客户订单号超过长度限制");
		temp.put("9002", "客户订单号存在重复");
		temp.put("9003", "客户订单号格式错误，只能包含数字和字母");
		temp.put("9004", "运输方式不能为空");
		temp.put("9005", "运输方式错误");
		temp.put("9006", "目的国家不能为空");
		temp.put("9007", "目的国家错误，请填写国家二字码");
		temp.put("9008", "收件人公司名超过长度限制");
		temp.put("9009", "收件人姓名不能为空");
		temp.put("9010", "收件人姓名超过长度限制");
		temp.put("9011", "收件人州或省超过长度限制");
		temp.put("9012", "收件人城市超过长度限制");
		temp.put("9013", "联系地址不能为空");
		temp.put("9014", "联系地址超过长度限制");
		temp.put("9015", "收件人手机号码超过长度限制");
		temp.put("9016", "收件人邮编超过长度限制");
		temp.put("9017", "收件人邮编只能是英文和数字");
		temp.put("9018", "重量数字格式不准确");
		temp.put("9019", "重量必须大于 0");
		temp.put("9020", "重量超过长度限制");
		temp.put("9021", "是否退件填写错误，只能填写 Y 或 N");
		temp.put("9022", "海关申报信息不能为空");
		temp.put("9023", "英文申报品名不能为空");
		temp.put("9024", "英文申报品名超过长度限制");
		temp.put("9025", "英文申报品名只能为英文、数字、空格、（）、() 、，、 ,%");
		temp.put("9026", "申报价值必须大于 0");
		temp.put("9027", "申报价值必须为正数");
		temp.put("9028", "申报价值超过长度限制");
		temp.put("9029", "申报品数量必须为正整数");
		temp.put("9030", "申报品数量超过长度限制");
		temp.put("9031", "中文申报品名超过长度限制");
		temp.put("9032", "中文申报品名必须为中文");
		temp.put("9033", "海关货物编号超过长度限制");
		temp.put("9034", "海关货物编号只能为数字");
		temp.put("9035", "收件人手机号码格式不正确");
		temp.put("9036", "服务商单号或顺丰单号已用完，请联系客服人员");
		temp.put("9037", "寄件人姓名超过长度限制");
		temp.put("9038", "寄件人公司名超过长度限制");
		temp.put("9039", "寄件人省超过长度限制");
		temp.put("9040", "寄件人城市超过长度限制");
		temp.put("9041", "寄件人地址超过长度限制");
		temp.put("9042", "寄件人手机号码超过长度限制");
		temp.put("9043", "寄件人手机号码格式不准确");
		temp.put("9044", "寄件人邮编超过长度限制");
		temp.put("9045", "寄件人邮编只能是英文和数字");
		temp.put("9046", "不支持批量操作");
		temp.put("9047", "批量交易记录数超过限制");
		temp.put("9048", "此订单已确认，不能再操作");
		temp.put("9049", "此订单已收货，不能再操作");
		temp.put("9050", "此订单已出货，不能再操作");
		temp.put("9051", "此订单已取消，不能再操作");
		temp.put("9052", "收件人电话超过长度限制");
		temp.put("9053", "收件人电话格式不正确");
		temp.put("9054", "寄件人电话超过长度限制");
		temp.put("9055", "寄件人电话格式不正确");
		temp.put("9056", "货物件数必须为正整数");
		temp.put("9057", "货物件数超过长度限制");
		temp.put("9058", "寄件人国家错误，请填写国家二字码，默认为 CN");
		temp.put("9059", "货物单位超过长度限制，默认为 PCE");
		temp.put("9060", "货物单位重量格式不正确");
		temp.put("9061", "货物单位重量超过长度限制");
		temp.put("9062", "该运输方式暂时不支持此国家的派送，请选择其他派送方式");
		temp.put("9063", "当前运输方式暂时不支持该国家此邮编的派送，请选择其他派送方式！");
		temp.put("9064", "该运输方式必须输入邮编");
		temp.put("9065", "寄件人国家国家不能为空");
		temp.put("9066", "寄件人公司名不能为空");
		temp.put("9067", "寄件人公司名不能包含中文");
		temp.put("9068", "寄件人姓名不能为空");
		temp.put("9069", "寄件人姓名不能包含中文");
		temp.put("9070", "寄件人城市不能为空");
		temp.put("9071", "寄件人城市不能包含中文");
		temp.put("9072", "寄件人地址不能为空");
		temp.put("9073", "寄件人地址不能包含中文");
		temp.put("9074", "寄件人邮编不能为空");
		temp.put("9075", "寄件人邮编不能包含中文");
		temp.put("9076", "收件人公司名不能为空");
		temp.put("9077", "收件人公司名不能包含中文");
		temp.put("9078", "收件人城市不能为空");
		temp.put("9079", "收件人城市不能包含中文");
		temp.put("9080", "查询类别不正确，合法值为： 1（运单号）， 2（订单号）");
		temp.put("9081", "查询号不能不能为空。");
		temp.put("9082", "查询方法错误，合法值为： 1（标准查询）");
		temp.put("9083", "查询号不能超过 10 个。注：多个单号，以逗号分隔。");
		temp.put("9084", "收件人电话不能为空");
		temp.put("9085", "收件人姓名不能包含中文");
		temp.put("9086", "英文申报品名必须为英文");
		temp.put("9087", "收件人手机不能包含中文");
		temp.put("9088", "收件人电话不能包含中文");
		temp.put("9089", "寄件人电话不能包含中文");
		temp.put("9090", "寄件人手机不能包含中文");
		temp.put("9091", "海关货物编号不能为空");
		temp.put("9092", "联系地址不能包含中文");
		temp.put("9093", "当总申报价值超过 75 欧元时【收件人邮箱】不能为空");
		temp.put("9094", "收件人邮箱超过长度限制");
		temp.put("9095", "收件人邮箱格式不正确");
		temp.put("9096", "寄件人省不能包含中文");
		temp.put("9097", "收件人州或省超不能包含中文");
		temp.put("9098", "收件人邮编不能包含中文");
		temp.put("9099", "英文申报品名根据服务商要求，申报品名包含disc、 speaker、 power bank、 battery、magne 禁止运输，请选择其他运输方式！");
		temp.put("9100", "寄件人省不能为空");
		temp.put("9101", "收件人州或省不能为空");
		temp.put("9102", "收件人邮编只能为数字");
		temp.put("9103", "收件人邮编只能为 4 个字节");
		temp.put("9104", "【收件人邮编】 , 【收件人城市】 , 【州╲省】不匹配");
		temp.put("9105", "申报价值大于 200 美元时，【海关货物编号】不能为空！");
		temp.put("9106", "收件人州或省不正确");
		temp.put("9107", "寄件人邮编只能包含数字");
		temp.put("9108", "收件人邮编格式不正确");
		temp.put("9109", "【州╲省】美国境外岛屿、区域不支持派送！");
		temp.put("9110", "【州╲省】 APO/FPO 军事区域不支持派送！");
		temp.put("9111", "客户 EPR 不存在！");
		temp.put("9112", "【配货备注】长度超过限制！");
		temp.put("9113", "【配货名称】不能包含中文！");
		temp.put("9114", "【配货名称】长度超过限制！");
		temp.put("9115", "【包裹长（ CM）】数字格式不正确！");
		temp.put("9116", "【包裹长（ CM）】不能超过 4 位！");
		temp.put("9117", "【包裹长（ CM）】必须大于 0！");
		temp.put("9118", "【包裹宽（ CM）】数字格式不正确！");
		temp.put("9119", "【包裹宽（ CM）】不能超过 4 位！");
		temp.put("9120", "【包裹宽（ CM）】必须大于 0！");
		temp.put("9121", "【包裹高（ CM）】数字格式不正确！");
		temp.put("9122", "【包裹高（ CM）】不能超过 4 位！");
		temp.put("9123", "【包裹高（ CM）】必须大于 0！");
		temp.put("9124", "【收件人身份证号/护照号】只能为数字和字母！");
		temp.put("9125", "【收件人身份证号/护照号】长度不能超过 18 个字符！");
		temp.put("9126", "【 VAT 税号】只能为数字和字母！");
		temp.put("9127", "【 VAT 税号】长度不能超过 20 个字符！");
		temp.put("9128", "【是否电池】填写错误，只能填写 Y 或 N！");
		temp.put("9129", "寄件人公司名不能包含,或\"");
		temp.put("9130", "寄件人姓名不能包含,或\"");
		temp.put("9131", "寄件人省不能包含,或\"");
		temp.put("9132", "寄件人城市不能包含,或\"");
		temp.put("9133", "寄件人地址不能包含,或\"");
		temp.put("9134", "寄件人电话不能包含,或\"");
		temp.put("9135", "寄件人手机号码不能包含,或\"");
		temp.put("9136", "收件人公司名不能包含,或\"");
		temp.put("9137", "收件人姓名不能包含,或\"");
		temp.put("9138", "收件人城市不能包含,或\"");
		temp.put("9139", "联系地址不能包含,或\"");
		temp.put("9140", "收件人电话不能包含,或\"");
		temp.put("9141", "收件人手机不能包含,或\"");
		temp.put("9142", "英文申报品名不能包含,或\"");
		errorMsgMap = Collections.unmodifiableMap(temp);
	}
	
	public TrackerApplyResult doApply(Order order) throws Exception {

		String xml = this.assembleOrderXml(order);
		logger.info("sf request xml ==> {}", xml);
		
		String resXml = this.requestWebservice(xml, checkWord);
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
			
			Element errorElement = root.element("ERROR");
			String errorCode = errorElement.attributeValue("code");
			String errorMsg = errorMsgMap.get(errorCode);
			errorMsg = errorMsg != null ? errorMsg : errorElement.getText();
			
			result.setMessage(String.format("%s - %s", errorCode, errorMsg));
			
		} else if ("OK".equals(head)) {
			Element or = root.element("Body").element("OrderResponse");
			String orderId = or.attributeValue("orderid");
			String agentMailNo = or.attributeValue("agent_mailno");
			String mailNo = or.attributeValue("mailno");
			result.setOrderId(NumberUtils.toInt(orderId));
			result.setTrackNumber(agentMailNo);
			result.setTrackNumberRef(mailNo);
			result.setSuccess(true);
		}
		
		return result;
	}

	// 请求Webservice， 这里使用 xfire 客户端， cxf报错
	private String requestWebservice(String xml, String checkword) throws Exception {
		String verifyCode = PasswordEncryption.encrypt(xml + checkword);
		verifyCode = Base64.encode(verifyCode, "UTF-8");
		logger.info("sf request verifyCode ==> {}", verifyCode);
		
		return SfexpressService_SfexpressServiceImplPort_Client.execute(xml, verifyCode);
	}

	private String assembleOrderXml(Order order) {
		String expressType = expressTypeMap.get(order.getShippingName());
		checkArgument(expressType != null, "不支持的发货方式：%s, 目前支持：%s", order.getShippingName(), expressTypeMap.keySet().toString());
		
		SenderModel senderModel = new SenderModel();
		senderModel.setHeadText(clientCode);
		senderModel.setServiceName(SERVICE_NAME);
		

		BigDecimal totalWeight = new BigDecimal(0);
		List<DeclareinvoiceDTO> lstDatas = new ArrayList<DeclareinvoiceDTO>();
		for (OrderItem tmp : order.getItems()) {
			OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) tmp;
			
			int quantity = item.getItemQuantity() != null ? item.getItemQuantity() : 1;
			
			BigDecimal weight = item.getWeight() != null ? item.getWeight().multiply(new BigDecimal(quantity)) : new BigDecimal(0);
			weight = weight.setScale(3, RoundingMode.HALF_UP);
			totalWeight = totalWeight.add(weight);
			
			DeclareinvoiceDTO declareinvoiceDTO = new DeclareinvoiceDTO();
			declareinvoiceDTO.setName(item.getDeclarationNameCn());
			//declareinvoiceDTO.setHscode(item.getCustomsCode());
			declareinvoiceDTO.setEname(item.getDeclarationNameEn());
			declareinvoiceDTO.setUnit("PCE");
			declareinvoiceDTO.setCount(String.valueOf(item.getItemQuantity()));
			declareinvoiceDTO.setAmount(String.valueOf(item.getDeclarationCost()));
			declareinvoiceDTO.setWeight(weight.toString());
			lstDatas.add(declareinvoiceDTO);
		}
		totalWeight = totalWeight.setScale(3, RoundingMode.HALF_UP);
		OrderBuyerInfo buyer = order.getBuyinfo();
		
		@SuppressWarnings("rawtypes")
		Map<Object, List> datas = new HashMap<Object, List>();
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getId().toString());
		orderDTO.setExpressType(expressType);
		
//		orderDTO.setJ_country("CN");
//		orderDTO.setJ_province("广东省");
//		orderDTO.setJ_city("深圳市");
//		orderDTO.setJ_address("广东省深圳市宝安区52区新安五路宝裕大厦8楼");
//		orderDTO.setJ_company("深圳市轩飞扬贸易有限公司");
//		orderDTO.setJ_contact("陈飞");
//		orderDTO.setJ_tel("18925220117");
//		orderDTO.setJ_mobile("18925220117");
//		orderDTO.setJ_post_code("518100");
		
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
		
		orderDTO.setD_company(buyer.getShippingName());
		orderDTO.setD_contact(buyer.getShippingName());
		orderDTO.setD_tel(buyer.getShippingPhone());
		orderDTO.setD_mobile(buyer.getShippingMobile());
		orderDTO.setD_address(buyer.getShippingStreet1() + " " + buyer.getShippingStreet2());
		orderDTO.setParcelQuantity("1");
		orderDTO.setD_province(buyer.getShippingState());
		orderDTO.setD_city(buyer.getShippingCity());
		orderDTO.setD_country(buyer.getShippingCountry() != null ? buyer.getShippingCountry() : buyer.getShippingCountryName());
		orderDTO.setD_post_code(buyer.getShippingPostcode());
		orderDTO.setCargoTotalWeight(totalWeight.toString());
		orderDTO.setReturnsign("Y");
		orderDTO.setD_email(buyer.getBuyerEmail());
		orderDTO.setOperateFlag("1");
		
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
		item.setWeight(new BigDecimal(0.001));
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
		buyer.setShippingCountryName("US");
		buyer.setShippingPhone("123123123123");
		buyer.setBuyerEmail("t@r.com");
		
		Order order = new Order();
		order.setBuyinfo(buyer);
		order.setId(NumberUtils.toInt(DateFormatUtils.format(new Date(), "MMddHHmmss")));
		order.setShippingName("欧洲小包挂号");
		
		order.setItems(items);

		ShippingAddressConfig sac = new ShippingAddressConfig();
		sac.setName("zhang san");
		sac.setCompany("xuan feiyang");
		sac.setCountry("CN");
		sac.setProvince("guangdongsheng");
		sac.setCity("shenzhenshi");
		sac.setDistrict("baoanqu");
		sac.setStreet("sasdfasf sdfasf a asdf a");
		sac.setPostcode("518000");
		sac.setMobile("12312312312");
		sac.setTelphone("123123123");
		sac.setEmail("zh@a.com");

		order.setShippingAddressConfig(sac);
		
		TrackerApplyResult result = new SfTrackerApplier().apply(order);
		System.out.println(result);

	}
	*/
}

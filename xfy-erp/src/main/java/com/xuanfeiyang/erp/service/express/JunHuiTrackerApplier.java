package com.xuanfeiyang.erp.service.express;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.service.TrackerApplyResult;

/**
 * 均晖物流 跟踪号申请
 * 
 * @author Adam
 *
 */
public class JunHuiTrackerApplier extends AbstractTrackerApplier {

	private static Logger logger = LoggerFactory.getLogger(JunHuiTrackerApplier.class);
	
	/**
	 * 渠道类型
	 * {"Name":"北京邮政小包","Code":"CNBJ,40758"},
	 * {"Name":"佛山挂号小包","Code":"CNFS,53759"},
	 * {"Name":"深圳挂号小包","Code":"CNSZ,47438"},
	 * {"Name":"中山挂号小包","Code":"CNZS,49718"},
	 * {"Name":"德国邮政平邮","Code":"DEPY,36260"},
	 * {"Name":"德国邮政小包挂号","Code":"DERG,50098"},
	 * {"Name":"德邮新加坡平邮","Code":"DESP,52379"},
	 * {"Name":"德邮新加坡挂号","Code":"DESR,52378"},
	 * {"Name":"E邮宝","Code":"EUBS,52039"},
	 * {"Name":"佛山平邮小包","Code":"FSPY,53761"},
	 * {"Name":"香港平邮小包","Code":"HKPY,36238"},
	 * {"Name":"香港挂号小包","Code":"HKRG,45338"},
	 * {"Name":"马来西亚平邮小包","Code":"MYPY,43139"},
	 * {"Name":"马来西亚小包挂号","Code":"MYRG,43138"},
	 * {"Name":"荷兰平邮","Code":"NLPY,46298"},
	 * {"Name":"荷兰小包","Code":"NLRG,39658"},
	 * {"Name":"俄罗斯小包","Code":"RUXX,52778"},
	 * {"Name":"瑞典平邮电池货","Code":"SEPD,54020"},
	 * {"Name":"瑞典邮政平邮","Code":"SEPY,30358"},
	 * {"Name":"瑞典挂号电池货","Code":"SERD,54019"},
	 * {"Name":"瑞典小包挂号","Code":"SERG,33298"}
	 * 
	 */
	private String expressType;
	
	public JunHuiTrackerApplier(String expressType) {
		this.expressType = expressType;
	}

	@Override
	public TrackerApplyResult doApply(Order order) throws Exception {
		String xml = this.assembleRequestXml(order);
		logger.info("均晖物流 request xml ==> {}", xml);
		
		String resXml = this.requestWebservice(xml);
		logger.info("均晖物流 response xml ==> {}", resXml);
		
		TrackerApplyResult result = this.parseResponseXml(resXml);
		result.setOrderId(order.getId());
		
		return result;
	}

	private static String requestXmlFormat = 
			  "<name>%s</name>"
			+ "<pwd>%s</pwd>"
			+ "<hawb_no></hawb_no>"
			+ "<caddress1>%s</caddress1>"
			+ "<counCode>%s</counCode>"
			+ "<cityName>%s</cityName>"
			+ "<cstate>%s</cstate>"
			+ "<cname>%s</cname>"
			+ "<ctel>%s</ctel>"
			+ "<ccode>%s</ccode>"
			+ "<wt>%s</wt>"
			+ "<name1>%s</<name1>"
			+ "<nacmCn>%s</nameCn>"
			+ "<svalue>%s</svalue>"
			+ "<remark></remark>"
			+ "<webpage>%s</webpage>"
			+ "<servCode>%s</servCode>"
			+ "<airNo>%s</airNo>"
			+ "<dataSources></dataSources>";

	private String assembleRequestXml(Order order) {
		OrderBuyerInfo obi = order.getBuyinfo();
		OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) order.getItems().get(0);
		return String.format(requestXmlFormat, 
				App.getConfig("express.junhui.userename"),
				App.getConfig("express.junhui.password"),
				obi.getShippingStreet1() + obi.getShippingStreet2(),
				obi.getShippingCountry(),
				obi.getShippingCity(),
				obi.getShippingState(),
				obi.getShippingName(),
				obi.getShippingMobile() != null ? obi.getShippingMobile() : obi.getShippingPhone(),
				obi.getShippingPostcode(),
				order.getCalcWeight() != null ? order.getCalcWeight() : item.getWeight(),
				item.getDeclarationNameEn(),
				item.getDeclarationNameCn(),
				item.getDeclarationCost(),
				"D",   // A:Gift，B：sample C:Printed Matter	D:Other
				this.expressType,
				"Y");
	}
	

	
	private String requestWebservice(String xml) throws Exception {
		String endpoint = App.getConfig("express.junhui.url");
		
		Service service = new Service();
		Call call = (Call) service.createCall();

		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		call.setOperationName("addEms");
		return (String) call.invoke(new Object[] { xml });
	}

	private TrackerApplyResult parseResponseXml(String resXml) {
		TrackerApplyResult result = new TrackerApplyResult();
		
		Document document = null;
		
		try {
			document = DocumentHelper.parseText(resXml);
		} catch (DocumentException e) {
			logger.error("解析文档出错, 错误: {}, 文档: {}", e.getMessage(), resXml);
		} 
		
		Element root = document.getRootElement();
		String flag = root.elementText("flag");
		if ("1".equals(flag)) {
			result.setSuccess(true);
			result.setTrackNumber(root.elementText("data"));
		} else {
			result.setSuccess(false);
			result.setMessage(root.elementText("errorDesc"));
		}
		
		return result;
	}
}

package com.xuanfeiyang.erp.service.express;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hlt.ws.service.hop.service.CreateOrderRequest;
import com.hlt.ws.service.hop.service.CreateOrderResponse;
import com.hlt.ws.service.hop.service.DeclareItem;
import com.hlt.ws.service.hop.service.GetTransportWayListResponse;
import com.hlt.ws.service.hop.service.HopHopError;
import com.hlt.ws.service.hop.service.OrderOnlineService_OrderOnlineServicePort_Client;
import com.hlt.ws.service.hop.service.TransportWay;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.service.TrackerApplyResult;

public class HuLianYiTrackerApplier extends AbstractTrackerApplier {

	private static Logger logger = LoggerFactory
			.getLogger(HuLianYiTrackerApplier.class);
	
	protected final static String USER_TOKEN = App.getConfig("express.hulianyi.user.token");
	
	protected final static Map<String, String> transportWays;
	static {
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("互联易转运提货费", "HLYZY");
		temp.put("香港e-Express", "e-Express");
		temp.put("美国独轮车空加派双清全包", "DLDHLDLCKJP");
		temp.put("美国仓本土大货服务USW-DH", "USW-DH");
		temp.put("美国本土邮政经济型包裹服务USPS/BUD", "MGBTYZJJXBGFW");
		temp.put("美国本土邮政快捷包裹服务USPS/EXP", "MGBTYZKJBGFW");
		temp.put("美国本土联邦大货服务FEDEX/LTL", "FEDEXMW");
		temp.put("美国本土联邦陆运服务FEDEX/GR", "FEDEXGR");
		temp.put("美国本土联邦智邮服务FEDEX/SMP", "FEDEXSMP");
		temp.put("美国本土UPS陆运服务UPS/GR", "UPSGR");
		temp.put("美国本土邮政优先级邮件USPS/PMS", "MGBTYZYXJYJ");
		temp.put("美国本土邮政一级邮件包裹服务USPS/FPS", "MGBTYZYJYJBGFW");
		temp.put("美国本土邮政一级邮件大信封服务USPS/FLL", "MGBTYZYJYJDXFFW");
		temp.put("美国本土邮政一级邮件信封服务USPS/FCL", "MGBTYZYJYJXFFW");
		temp.put("美国本土邮政优先级国际邮件USPS/PMI", "USPSFPI1");
		temp.put("美国本土邮政一级邮件国际包裹服务USPS/FPI", "USPSFPI");
		temp.put("大陆Fedex\\UPS独轮车", "CNFEDEX-IPA");
		temp.put("互联易速递", "HLYSD");
		temp.put("美国仓快递头程SPDHL", "SPDHL");
		temp.put("海外退件", "JKJ");
		temp.put("香港DHL代理价", "HKDHL");
		temp.put("香港DHL-A价", "HKY-DHL-A");
		temp.put("香港DHL-H", "HK-DHL-H");
		temp.put("DHL小包挂号", "RDXBGHH");
		temp.put("互联宝", "HLB");
		temp.put("大陆DHL小货价", "CN-DHL");
		temp.put("DHL小包平邮", "RDXBGHI");
		temp.put("香港UPS欧美-F价", "HK-UPS-F");
		temp.put("香港UPS代理价", "HKUPS");
		temp.put("FBA转运头程/美国仓头程(空运普货)", "FBAKYMG");
		temp.put("香港联邦IE代理价", "HK-FEDEX-IE");
		temp.put("FBA转运头程/美国仓头程（空运带电池或磁性）", "FBAKYMGDD");
		temp.put("香港联邦IP代理价", "HK-FEDEX-IP");
		temp.put("香港联邦-F价", "HK-FEDEX-F");
		temp.put("FBA转运海运头程/美国仓海运头程", "FBATCHY");
		temp.put("FBA快递含税DHL", "FBAKDHS");
		temp.put("香港联邦IE超大、超重件", "HK-FEDEX-IE-FEF");
		temp.put("FBA快递含税UPS", "FBAKDHSUPS");
		temp.put("FBA头程直送海运(美西)", "FBATCMX");
		temp.put("FBA头程直送海运(美中)", "FBATCMZ");
		temp.put("FBA头程直送海运(美东)", "FBATCMD");
		temp.put("香港ARAMEX", "HKARAMEX");
		temp.put("大陆DHL小货价（带电）", "DHLDLXHJ");
		temp.put("大陆DHL欧洲重货价", "DHLDLOZZHJ");
		temp.put("大陆DHL南美洲重货价", "DHLDLNMZZHJ");
		temp.put("深圳EMS", "SZEMS");
		temp.put("广东EMS", "GDEMS");
		temp.put("E特快", "GJETK");
		temp.put("线下E邮宝(美国)", "EYBA");
		temp.put("广东线下EUB(美国)", "GDEYBA");
		temp.put("深圳线下EUB(美国)", "SZEYBA");
		temp.put("e邮宝", "EYB");
		temp.put("E速宝-美国", "E-US");
		temp.put("e邮宝(广州)", "EYBGZ");
		temp.put("线下E邮宝(澳洲)", "AU-EYB-B");
		temp.put("深圳线下EUB(澳洲)", "SZ-EYB-B");
		temp.put("澳洲E邮宝", "AU-EYB");
		temp.put("线下E邮宝(英国)", "EYB-GB-B");
		temp.put("深圳线下EUB(英国)", "SZEYB-GB-B");
		temp.put("广东线下EUB(澳洲)", "GZ-EYB-B");
		temp.put("英国E邮宝", "HKPOSTPB");
		temp.put("香港E邮宝", "HK-EYB");
		temp.put("线下e邮宝(加拿大)", "CA-EYB-B");
		temp.put("深圳线下EUB(加拿大)", "SZCA-EYB-B");
		temp.put("加拿大E邮宝", "CA-EYB");
		temp.put("线下e邮宝(法国)", "EYB-FR-B");
		temp.put("深圳线下EUB(法国)", "SZEYB-FR-B");
		temp.put("广东线下EUB(加拿大)", "GDCA-EYB-B");
		temp.put("法国E邮宝", "CN-DHL-MY");
		temp.put("线上e邮宝(俄罗斯)", "EYB-RU");
		temp.put("线下e邮宝(俄罗斯)", "EYB-RU-B");
		temp.put("深圳线下EUB(俄罗斯)", "SZEYB-RU-B");
		temp.put("广东线下EUB(法国)", "GDEYB-FR-B");
		temp.put("广东线下EUB(俄罗斯)", "GDEYB-RU-B");
		temp.put("广东线下EUB(英国)", "GDEYB-GB-B");
		temp.put("新加坡EMS", "SGEMS");
		temp.put("香港EMS", "HKEMS");
		temp.put("DX香港大包裹", "DX-HK-DB");
		temp.put("香港邮政大包裹", "HKDB");
		temp.put("香港邮政大包裹平邮", "HK-PPSM");
		temp.put("香港本地包裹服务", "HKPOSTPM");
		temp.put("中国邮政航空大包(广州)", "CNPOSTAIR-GZ");
		temp.put("中国邮政航空大包", "CNPOSTAIR");
		temp.put("中国邮政水陆路大包(广州)", "CNPOSTSUR-GZ");
		temp.put("中国邮政水陆路大包", "CNPOSTSUR");
		temp.put("中国邮政空运水陆大包(广州)", "CNPOSTSAL-GZ");
		temp.put("中国邮政空运水陆大包", "CNPOSTSAL");
		temp.put("SZ邮政小包挂号", "SZCNPOSTGH");
		temp.put("SZ邮政小包平邮", "SZCNPOSTPY");
		temp.put("中国邮政航空小包挂号", "CNPOSTGH-SZ");
		temp.put("中国邮政航空小包挂号(GZ)", "CNPOSTGH-GZ");
		temp.put("省外中邮小包挂号", "RSGHPS");
		temp.put("省外中邮小包平邮", "HK-UPS-LDTH");
		temp.put("利通挂号(到付)", "LTGH2");
		temp.put("美国专线", "US-ZX");
		temp.put("澳洲专线(挂号)", "HKPOSTPGM");
		temp.put("澳洲专线(平邮)", "AUEMS");
		temp.put("互联易专线", "HLYZX");
		temp.put("利通挂号(揽收)", "LTTGH2");
		temp.put("俄罗斯专线", "CNPOSTRU");
		temp.put("SHKE邮宝", "SHKE-EYB");
		temp.put("香港小包挂号", "HKPOSTTH");
		temp.put("马来西亚小包平邮", "MYA");
		temp.put("马来西亚小包挂号", "MY");
		temp.put("瑞典小包平邮(电池)", "RDXBGHC");
		temp.put("瑞典小包挂号(电池)", "RDXBGHB");
		temp.put("荷兰小包平邮", "RDXBGHF");
		temp.put("荷兰小包挂号", "HLXB");
		temp.put("俄罗斯小包挂号", "CNPOSTRUXB");
		temp.put("俄罗斯小包(新疆)", "CNPOSTRUXB-XJ");
		temp.put("RU专线小包", "RUZXXB");
		temp.put("DLEMS", "DLEMS");
		temp.put("香港进口E特快", "HK-E");
		temp.put("香港快件进口(经济快递)", "HK-EI-EI");
		temp.put("香港快件进口(深圳)", "HK-EI-SZ");
		temp.put("香港快件进口(省内)", "HK-EI-GUGOYW");
		temp.put("香港快件进口(标快)", "HK-EI-SE");
		temp.put("香港TNT-F价", "HK-TNT-F");
		temp.put("深圳TNT", "TNT");
		temp.put("顺风", "SF");
		temp.put("中港快件", "ZGKD");
		temp.put("出口过港", "CKGG");
		temp.put("中港快件（进口）", "ZGKDJK");
		temp.put("互联易转运（二）", "HLYZYR");
		temp.put("互联易转运（一）", "HLYYZY");
		temp.put("国外仓储快件", "WCCKJ");
		temp.put("空运航线一", "C-AIR1");
		temp.put("国内运输", "GNYS");
		temp.put("海外退件", "HWTJ");
		temp.put("香港顺丰", "HK-SF");
		temp.put("香港空邮小包平邮", "HKPOSTPY");
		temp.put("香港一级挂号", "HKPOSTG1");
		temp.put("瑞士小包平邮", "RSPOSTPY");
		temp.put("瑞士小包挂号", "RSPOSTGH");
		temp.put("新小包平邮", "JPPOSTPY");
		temp.put("新小包挂号", "XJPPOSTGH");
		temp.put("瑞邮宝平邮", "RYB");
		temp.put("瑞邮宝挂号", "RYBGH");
		temp.put("自提", "ZT");
		temp.put("香港投寄易", "HK-TJY");
		temp.put("欧洲专线", "DPD-RPX");
		temp.put("欧洲独轮车专线", "DPD-RPXDLC");
		temp.put("中国邮政平邮小包特价(GZ)", "CNPOSTPY-GZ");

		transportWays = Collections.unmodifiableMap(temp);
	}

	@Override
	public TrackerApplyResult doApply(Order order) throws Exception {
		CreateOrderRequest request = this.assembleRequest(order);
		logger.info("互联易request ==> {}", request);

		CreateOrderResponse response = this.requestWebservice(request);
		logger.info("互联易 response ==> {}", response);

		TrackerApplyResult result = this.parseResponse(response);
		result.setOrderId(order.getId());

		return result;
	}

	private CreateOrderRequest assembleRequest(Order order) {
		
		String transportWayCode = transportWays.get(order.getShippingName());
		checkArgument(transportWayCode != null, "不支持的发货方式：%s", order.getShippingName());

		OrderBuyerInfo buyerinfo = order.getBuyinfo();
		checkArgument(buyerinfo != null, "收货信息为空");
		
		CreateOrderRequest r = new CreateOrderRequest();
		r.setOrderNo(order.getId() + "");
		r.setTransportWayCode(transportWayCode);
		r.setCargoCode("W");
		
		String countryCode = StringUtils.trim(buyerinfo.getShippingCountry());
		checkArgument(StringUtils.length(countryCode) == 2, "只支持国家二位简码");
		r.setDestinationCountryCode(countryCode);
		r.setPieces(1L);
		
		r.setConsigneeName(buyerinfo.getShippingName());
		r.setStreet(buyerinfo.getShippingStreet1() + " " + buyerinfo.getShippingStreet2());
		r.setCity(buyerinfo.getShippingCity());
		r.setProvince(buyerinfo.getShippingState());
		r.setConsigneePostcode(buyerinfo.getShippingPostcode());
		r.setConsigneeTelephone(buyerinfo.getShippingPhone());
		r.setConsigneeMobile(buyerinfo.getShippingMobile());
		
		r.setInsured("N");
		r.setGoodsCategory("G");
		
		List<DeclareItem> declareItems = r.getDeclareItems();
		
		double totalWeight = 0;
		for (OrderItem tmp : order.getItems()) {
			OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) tmp;
			
			int quantity = item.getItemQuantity() != null ? item.getItemQuantity() : 1;
			
			double weight = item.getWeight()  != null ? item.getWeight().doubleValue() * quantity : 0;
			totalWeight += weight;
			
			double cost = item.getDeclarationCost() != null ? item.getDeclarationCost().doubleValue() : 0;
			
			DeclareItem declareItem = new DeclareItem();
			declareItem.setName(item.getDeclarationNameEn());
			declareItem.setCnName(item.getDeclarationNameCn());
			declareItem.setPieces(quantity + 0L);
			declareItem.setNetWeight(weight);
			declareItem.setUnitPrice(cost);
			
			declareItems.add(declareItem);
		}
		
		r.setWeight(totalWeight);
		
		return r;
	}

	private CreateOrderResponse requestWebservice(CreateOrderRequest request) {
		return OrderOnlineService_OrderOnlineServicePort_Client.createAndAuditOrder(USER_TOKEN, request);
	}

	private TrackerApplyResult parseResponse(CreateOrderResponse response) {
		TrackerApplyResult result = new TrackerApplyResult();
		if (response.isSuccess()) {
			result.setSuccess(true);
			result.setTrackNumber(response.getTrackingNo());
		} else {
			result.setSuccess(false);
			HopHopError error = response.getError();
			result.setMessage(error.getErrorCode() + " - " + error.getErrorInfo());
		}
		
		return result;
	}

	public static void main(String[] args) {
		GetTransportWayListResponse res = OrderOnlineService_OrderOnlineServicePort_Client.getTransportWayList(USER_TOKEN);
		System.out.println(res.isSuccess());
		System.out.println(res.getError() != null ? res.getError().getErrorInfo() : "");
		for (TransportWay way : res.getTransportWays()) {
			System.out.println(way.getCode() + "\t\t\t\t\t" + way.getName());
		}
		
	}
	
}



package com.xuanfeiyang.erp.service.express;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ebay.apacshipping.api.AddAPACShippingPackageRequest;
import com.ebay.apacshipping.api.AddAPACShippingPackageResponse2;
import com.ebay.apacshipping.api.ApacShippingServiceSoap_ApacShippingServiceSoap_Client;
import com.ebay.apacshipping.api.Item;
import com.ebay.apacshipping.api.ItemList;
import com.ebay.apacshipping.api.Notification;
import com.ebay.apacshipping.api.NotificationList;
import com.ebay.apacshipping.api.OrderDetail;
import com.ebay.apacshipping.api.PickUpAddress;
import com.ebay.apacshipping.api.ReturnAddress;
import com.ebay.apacshipping.api.SKU;
import com.ebay.apacshipping.api.ShipFromAddress;
import com.ebay.apacshipping.api.ShipToAddress;
import com.google.common.base.Preconditions;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.domain.ShippingAddressConfig;
import com.xuanfeiyang.erp.service.TrackerApplyResult;

/**
 * eBay亚太物流平台申请跟踪号
 * 
 * @author Adam
 *
 */
public class ApacTrackerApplier extends AbstractTrackerApplier {

	@Override
	public TrackerApplyResult doApply(Order order) throws Exception {
		
		checkArgument(! order.getOrderSn().startsWith("copy_"), "复制的订单");

		AddAPACShippingPackageRequest request = this.assembleRequestData(order);
		AddAPACShippingPackageResponse2 response = this.requestWebservice(request);

		TrackerApplyResult result = this.parseResponse(response);
		result.setOrderId(order.getId());

		return result;
	}

	private AddAPACShippingPackageResponse2 requestWebservice(AddAPACShippingPackageRequest request) {
		return ApacShippingServiceSoap_ApacShippingServiceSoap_Client.addAPACShippingPackage(request);
	}

	private TrackerApplyResult parseResponse(AddAPACShippingPackageResponse2 response) {
		TrackerApplyResult result = new TrackerApplyResult();
		
		if ("Success".equals(response.getAck().value())) {
			result.setSuccess(true);
			result.setTrackNumber(response.getTrackCode());
		} else {
			result.setSuccess(false);

			NotificationList nl = response.getNotificationList();
			if (nl != null) {
				List<Notification> ns = nl.getNotification();
				if (nl != null) {
					String message = "";
					for (Notification n : ns) {
						message += String.format("%s(%s), ", n.getCode(), n.getMessage());
					}
					result.setMessage(message);
				}
			}
		}

		return result;
	}

	// 拼装请求数据
	private AddAPACShippingPackageRequest assembleRequestData(Order order) {
		
		ShippingAddressConfig shippingConf = order.getShippingAddressConfig();
		Preconditions.checkNotNull(shippingConf, "发货方式地址配置为空");
		
		PlatformAccount account = order.getPlatformAccount();
		Preconditions.checkNotNull(account, "eBay账户信息为空");
		String ebayToken = StringUtils.trimToNull(account.getEbayToken());
		Preconditions.checkNotNull(ebayToken, "eBay账户授权信息为空");
		
		AddAPACShippingPackageRequest request = new AddAPACShippingPackageRequest();
		request.setAppID(account.getEbayAppid());
		request.setAppCert(account.getEbayCertid());
		request.setAPIDevUserID(account.getEbayDevid());
		request.setAPISellerUserToken(account.getEbayToken());
		request.setAPISellerUserID(account.getAccountName());
		request.setMessageID("" + System.currentTimeMillis());
		request.setVersion("4.0.0");
		request.setCarrier("CNPOST");
		//request.setService("EPACK");

		OrderDetail d = new OrderDetail();
		request.setOrderDetail(d);

		d.setEMSPickUpType(1);
		
		// 揽收地址信息。
		PickUpAddress pua = new PickUpAddress();
		d.setPickUpAddress(pua);

		pua.setContact(shippingConf.getName());
		pua.setCompany(shippingConf.getCompany());
		pua.setStreet(shippingConf.getStreet());
		pua.setDistrict(shippingConf.getDistrict());
		pua.setCity(shippingConf.getCity());
		pua.setProvince(shippingConf.getProvince());
		pua.setPostcode(shippingConf.getPostcode());
		pua.setCountryCode("CN");
		pua.setEmail(shippingConf.getEmail());
		pua.setMobile(shippingConf.getMobile());
		pua.setPhone(shippingConf.getTelphone());

		// 寄件人地址信息，必须以英文填写
		ShipFromAddress sfa = new ShipFromAddress();
		d.setShipFromAddress(sfa);

		sfa.setContact(shippingConf.getEnReturnName()); // 使用英文退货地址，与PHP系统保持一致，下同
		sfa.setCompany(shippingConf.getEnReturnCompany());
		sfa.setStreet(shippingConf.getEnReturnStreet());
		sfa.setDistrict(shippingConf.getEnReturnDistrict());
		sfa.setCity(StringUtils.upperCase(shippingConf.getEnReturnCity()));
		sfa.setProvince(StringUtils.upperCase(shippingConf.getEnReturnProvince()));
		sfa.setPostcode(shippingConf.getEnReturnPostcode());
		sfa.setCountryCode("CN");
		sfa.setEmail(shippingConf.getEnReturnEmail());
		sfa.setMobile(shippingConf.getEnReturnMobile());
		sfa.setPhone(shippingConf.getEnReturnTelphone());

		OrderBuyerInfo buyer = order.getBuyinfo();
		
		// 收件人地址信息
		ShipToAddress sta = new ShipToAddress();
		d.setShipToAddress(sta);

		sta.setContact(buyer.getShippingName());
		//sta.setCompany(buyer.get );
		sta.setStreet(buyer.getShippingStreet1() + " " + buyer.getShippingStreet2());
		// sta.setDistrict("");
		sta.setCity(buyer.getShippingCity());
		sta.setProvince(buyer.getShippingState());
		sta.setCountryCode(buyer.getShippingCountry() != null ? buyer.getShippingCountry() : buyer.getShippingCountryName());
		sta.setPostcode(buyer.getShippingPostcode());
		sta.setPhone(buyer.getShippingPhone());
		sta.setMobile(buyer.getShippingMobile());
		sta.setEmail(buyer.getBuyerEmail());

		// 退货地址信息，并且必须保持与寄件人地址是中英文翻译的关系。
		ReturnAddress ra = new ReturnAddress();
		d.setReturnAddress(ra);

		ra.setContact(shippingConf.getCnReturnName());
		ra.setCompany(shippingConf.getCnReturnCompany());
		ra.setStreet(shippingConf.getCnReturnStreet());
		ra.setDistrict(shippingConf.getCnReturnDistrict());
		ra.setCity(shippingConf.getCnReturnCity());
		ra.setProvince(shippingConf.getCnReturnProvince());
		ra.setPostcode(shippingConf.getCnReturnPostcode());
		ra.setCountryCode("CN");
		
		ItemList itemList = new ItemList();
		d.setItemList(itemList);

		for (OrderItem tmp : order.getItems()) {
			OrderItemWithShippingInfo orderItem = (OrderItemWithShippingInfo) tmp;

			String orderLimitId = orderItem.getOrderLimitId();
			if (orderLimitId == null) {
				continue;
			}

			Item item = new Item();
			itemList.getItem().add(item);
			
			String[] tmpArr = orderLimitId.split("-");
			
			item.setEBayItemID(tmpArr[0]);
			item.setEBayTransactionID(tmpArr.length == 2 ? tmpArr[1] : "0");
			item.setEBayBuyerID(buyer.getBuyerUserId());
			item.setPostedQTY(orderItem.getItemQuantity());
	
			SKU sku = new SKU();
			item.setSKU(sku);
	
			sku.setSKUID(orderItem.getItemSku());
			sku.setDeclaredValue(orderItem.getDeclarationCost());
			sku.setWeight(orderItem.getWeight());
			sku.setCustomsTitle(orderItem.getDeclarationNameCn());
			sku.setCustomsTitleEN(orderItem.getDeclarationNameEn());
			sku.setOriginCountryCode("CN");
			sku.setHTSNumber(orderItem.getCustomsCode());
		}

		return request;
	}
	
	public static void main(String[] args) {
		
	}

}

package com.xuanfeiyang.erp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ebay.apacshipping.api.ApacShippingServiceSoap_ApacShippingServiceSoap_Client;
import com.ebay.apacshipping.api.CancelAPACShippingPackageRequest;
import com.ebay.apacshipping.api.CancelAPACShippingPackageResponse2;
import com.ebay.apacshipping.api.ConfirmAPACShippingPackageRequest;
import com.ebay.apacshipping.api.ConfirmAPACShippingPackageResponse2;
import com.ebay.apacshipping.api.Notification;
import com.ebay.apacshipping.api.NotificationList;
import com.ebay.apacshipping.api.RecreateAPACShippingPackageRequest;
import com.ebay.apacshipping.api.RecreateAPACShippingPackageResponse2;
import com.google.common.base.Preconditions;
import com.xuanfeiyang.erp.dao.OrderDao;
import com.xuanfeiyang.erp.dao.OrderItemDao;
import com.xuanfeiyang.erp.dao.OrderPackageDao;
import com.xuanfeiyang.erp.dao.OrderPackageItemDao;
import com.xuanfeiyang.erp.dao.ShippingAddressConfigDao;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderPackage;
import com.xuanfeiyang.erp.domain.OrderPackageItem;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.domain.ShippingAddressConfig;
import com.xuanfeiyang.erp.domain.SimpleOrderWithAccount;
import com.xuanfeiyang.erp.service.PlatformAccountService;
import com.xuanfeiyang.erp.service.TrackerApplyResult;
import com.xuanfeiyang.erp.service.TrackerNumberService;
import com.xuanfeiyang.erp.service.express.ApacTrackerApplier;
import com.xuanfeiyang.erp.service.express.TrackerApplier;

/**
 * 物流跟踪号服务实现类
 * 
 * @author Administrator
 *
 */
@Service
public class TrackerNumberServiceImpl implements TrackerNumberService {

	@Resource
	private OrderDao orderDao;

	@Resource
	private OrderItemDao orderItemDao;
	
	@Resource 
	private OrderPackageDao orderPackageDao;

	@Resource 
	private OrderPackageItemDao orderPackageItemDao;
	
	@Resource
	private PlatformAccountService platformAccountService;
	
	@Resource
	private ShippingAddressConfigDao shippingAddressConfigDao;
	

	@Override
	public List<TrackerApplyResult> applyMultiOrders(String type, List<Integer> orderIds) {
		type = StringUtils.trimToNull(type);

		Preconditions.checkNotNull(type, "申请跟踪号类型为空");
		Preconditions.checkArgument(orderIds != null && orderIds.size() > 0, "订单ID不能为空");

		TrackerApplier applier = this.getApplierByType(type);

		List<TrackerApplyResult> results = new ArrayList<>(orderIds.size());
		for (Integer orderId : orderIds) {
			if (orderId != null) {
				results.add(this.applyOneOrder(applier, orderId));
			}
		}

		return results;
	}

	@Override
	public TrackerApplyResult applyOneOrder(String type, Integer orderId) {
		TrackerApplier applier = this.getApplierByType(type);
		return this.applyOneOrder(applier, orderId);
	}

	// 通过传入的类型获取申请实现类
	private TrackerApplier getApplierByType(String type) {
		TrackerApplierHolder applierHolder = null;

		try {
			applierHolder = TrackerApplierHolder.valueOf(type);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("错误的申请类型: " + type);
		}

		return applierHolder.getApplier();
	}

	// 申请单号,并完成更新
	private TrackerApplyResult applyOneOrder(TrackerApplier applier, Integer orderId) {
		TrackerApplyResult result = null;

		if (applier == null) {
			result = this.createForManual(orderId, null);
		} else {
			Order order = this.orderDao.getWithShippingInfo(orderId);
			result = this.remoteApply(applier, order);
		}

		// 根据申请结果,更新到数据库中
		this.updateOrderByResult(result);

		return result;
	}

	// 通过第三方API申请
	private TrackerApplyResult remoteApply(TrackerApplier trackerApplier, Order order) {
		TrackerApplyResult result = new TrackerApplyResult();
		result.setOrderId(order.getId());

		// 如果是新亚太物流，需要特殊信息
		if (trackerApplier instanceof ApacTrackerApplier) {
			
			int accountId = order.getAccountId();
			PlatformAccount account = this.platformAccountService.loadById(accountId);
			order.setPlatformAccount(account);
		}
		
		ShippingAddressConfig shippingConfig = this.shippingAddressConfigDao.loadByShippingName(order.getShippingName());
		order.setShippingAddressConfig(shippingConfig);

		result = trackerApplier.apply(order);

		return result;
	}

	// 处理那些需要手工跟踪号订单
	private TrackerApplyResult createForManual(Integer orderId, String packageId) {
		TrackerApplyResult result = new TrackerApplyResult();
		result.setOrderId(orderId);
		result.setPackageId(packageId);
		result.setSuccess(true);

		result.setTrackNumber("AZ" + (StringUtils.isNotBlank(packageId) ? packageId : orderId));

		return result;
	}

	// 如果申请成功，则把跟踪号保存到记录中
	private void updateOrderByResult(TrackerApplyResult result) {
		// 申请成功跟踪号后, 更新到订单，如果有包裹，也更新到包裹
		if (result.isSuccess()) {
			Order order = new Order();
			order.setId(result.getOrderId());
			order.setTrackNumber(result.getTrackNumber());
			order.setTrackNumberRef(result.getTrackNumberRef());
			
			// 更新到订单
			this.orderDao.update(order);
			
			// 如果已经生成包裹，则把跟踪号和发货方式更新到包裹上去
			this.orderPackageDao.updateTrackNumberAndShippingNameByOrderId(result.getOrderId());
		}
	}

	@Override
	public List<TrackerApplyResult> cancelApac(List<Integer> orderids) {
		if (orderids == null || orderids.size() == 0) {
			return null;
		}

		List<TrackerApplyResult>  results = new ArrayList<>();

		for (Integer orderId : orderids) {

			SimpleOrderWithAccount order = this.orderDao.getSimpleOrderAccountById(orderId);
			TrackerApplyResult result = this.cancelApac(order);
			result.setOrderId(orderId);
			
			if (result.isSuccess()) {
				Order tmp = new Order();
				tmp.setId(result.getOrderId());
				tmp.setTrackNumber(" ");
				this.orderDao.update(tmp);
			}
			
			results.add(result);
		}
		
		return results;
	}

	private TrackerApplyResult cancelApac(SimpleOrderWithAccount order) {
		
		TrackerApplyResult result = new TrackerApplyResult();

		try {
			CancelAPACShippingPackageRequest request = this.assembleCancelRequest(order);
			CancelAPACShippingPackageResponse2 response = ApacShippingServiceSoap_ApacShippingServiceSoap_Client
					.cancelAPACShippingPackage(request);
			
			if ("Success".equals(response.getAck().value())) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				result.setMessage(getMessageFromNotificationList(response.getNotificationList()));
			}
			
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}

		return result;
	}
	
	private String getMessageFromNotificationList(NotificationList nl) {
		if (nl != null) {
			List<Notification> ns = nl.getNotification();
			if (ns != null) {
				StringBuilder message = new StringBuilder();
				for (Notification n : ns) {
					message.append(String.format("%s(%s), ", n.getCode(), n.getMessage()));
				}
				
				return message.toString();
			}
		}
		
		return null;
	}

	private CancelAPACShippingPackageRequest assembleCancelRequest(SimpleOrderWithAccount order) {
		CancelAPACShippingPackageRequest request = new CancelAPACShippingPackageRequest();
		request.setAPIDevUserID(order.getEbayDevid());
		request.setAPISellerUserToken(order.getEbayToken());
		request.setAPISellerUserID(order.getAccountName());
		request.setAppID(order.getEbayAppid());
		request.setAppCert(order.getEbayCertid());
		request.setMessageID("" + System.currentTimeMillis());
		request.setVersion("4.0.0");
		request.setCarrier("CNPOST");
		request.setTrackCode(order.getTrackNumber());
		
		return request;
	}

	@Override
	public TrackerApplyResult applyOnePackage(String type, String packageId) {
		
		// 检测type 是否正确 同时 获取跟踪号申请处理类
		TrackerApplier applier = this.getApplierByType(type);
		
		OrderPackage p = this.orderPackageDao.getById(packageId);
		Preconditions.checkNotNull(p, "包裹ID输入错误");
		// 如果包裹已经有了跟踪号，则不需要处理
		if (p.getTrackNumber() != null) {
			TrackerApplyResult result = new TrackerApplyResult();
			result.setOrderId(p.getOrderId());
			result.setPackageId(p.getId());
			result.setSuccess(false);
			result.setMessage("包裹已经申请了跟踪号，不能再申请");
			return result;
		}
		
			
		/*
		 * 如果没有跟踪号，检查对应的订单有没有申请跟踪号
		 * 		若订单已经申请了跟踪号，则，检查该订单是否有多个包裹，
		 * 			如果只有此一个包裹，则将订单的跟踪号和发货方式同步过来
		 * 			如果有多个包裹，且其他包裹没有申请跟踪号，则将订单的跟踪号同步到此包裹
		 * 			如果其他包裹有和订单相同的跟踪号，则重新为此包裹申请跟踪号
		 *		若订单没有申请跟踪号，
		 *			则需要为此包裹申请跟踪号，申请完成后将跟踪号和发货方式同步到订单
		 */
		
		Order order = this.orderDao.getWithShippingInfo(p.getOrderId());
		List<OrderPackage> ops = this.orderPackageDao.findByOrderId(p.getOrderId());
		
		// 是否需要申请
		boolean needApply = false;
		
		if (order == null || StringUtils.isBlank(order.getTrackNumber())) {
			needApply = true;
		} else {
			for (OrderPackage tp : ops) {
				if (StringUtils.isNotBlank(tp.getTrackNumber())) {
					needApply = true;
					break;
				}
			}
		}
		
		// 不需要申请的，将订单的信息同步过来
		if (! needApply) {
			this.updatePackageTrackerNumber(p.getId(), order.getTrackNumber());
			
			TrackerApplyResult r = new TrackerApplyResult();
			r.setSuccess(true);
			r.setOrderId(order.getId());
			r.setPackageId(p.getId());
			r.setMessage("跟踪号来自订单中的跟踪号");
			return r;
		} 
		// 需要申请跟踪号
		// 为了重用 基于订单的申请跟踪号方法，需在一个订单多包裹时，进行处理
		else {
			if (ops.size() > 1) {
				List<OrderPackageItem> opis = this.orderPackageItemDao.findByPackage(p.getId());
				
				List<OrderItem> tmpItems = new ArrayList<>();
				for (OrderItem oi : order.getItems()) {
					// 检查订单行的SKU是否在此包裹
					for (OrderPackageItem opi : opis) {
						if (oi.getSku().equals(opi.getSku())) {
							// 修改数量与包裹相同
							oi.setItemQuantity(opi.getPackageAmount());
							tmpItems.add(oi);
							
							break;
						}
					}
				}
				
				order.setItems(tmpItems);
			}
		}
		

		TrackerApplyResult result = null;
		
		// 如果是平邮的，可以自己生成的
		if (applier == null) {
			result = this.createForManual(p.getOrderId(), p.getId());
		} else {
			result = this.remoteApply(applier, order);
		}
		
		// 成功后更新到数据库中
		if (result.isSuccess()) {
			this.updatePackageTrackerNumber(p.getId(), result.getTrackNumber());
			
			// 只有订单的跟踪号为空的时候，才更新
			if (order != null && StringUtils.isBlank(order.getTrackNumber())) {
				this.updateOrderByResult(result);
			}
		}
		
		result.setPackageId(p.getId());
		
		orderPackageDao.updateState(3, p.getId());//申请物流跟踪号后  更新包裹的 状态-3
		
		return result;
	}

	private void updatePackageTrackerNumber(String packageId, String trackNumber) {
		OrderPackage tmp = new OrderPackage();
		tmp.setId(packageId);
		tmp.setTrackNumber(trackNumber);
		this.orderPackageDao.update(tmp);
	}

	@Override
	public List<TrackerApplyResult> cancelApacForPackage(List<String> packageIds) {
		
		if (packageIds == null || packageIds.isEmpty()) {
			return null;
		}
		
		List<TrackerApplyResult> results = new ArrayList<>();
		for (String packageId : packageIds) {
			OrderPackage op = this.orderPackageDao.getById(packageId);
			Integer orderId = op.getOrderId();
			
			SimpleOrderWithAccount order = this.orderDao.getSimpleOrderAccountById(orderId);
			TrackerApplyResult result = this.cancelApac(order);
			result.setOrderId(orderId);
			result.setPackageId(packageId);
			
			if (result.isSuccess()) {
				OrderPackage t = new OrderPackage();
				t.setId(op.getId());
				t.setTrackNumber(" ");
				this.orderPackageDao.update(t);
			}
			
			results.add(result);
		}
		
		return results;
	}

	@Override
	public List<TrackerApplyResult> applyMultiPackages(String type, List<String> packageIds) {
		List<TrackerApplyResult> results = new ArrayList<>();
		
		for (String packageId : packageIds) {
			results.add(this.applyOnePackage(type, packageId)); 
		}
		
		return results;
	}

	@Override
	public List<TrackerApplyResult> confirmApac(List<Integer> orderids) {
		if (orderids == null || orderids.size() == 0) {
			return null;
		}

		List<TrackerApplyResult>  results = new ArrayList<>();

		for (Integer orderId : orderids) {

			SimpleOrderWithAccount order = this.orderDao.getSimpleOrderAccountById(orderId);
			TrackerApplyResult result = this.confirmApac(order);
			result.setOrderId(orderId);
			
			// 成功后更新为已标发
			if (result.isSuccess()) {
				Order tmp = new Order();
				tmp.setId(result.getOrderId());
				tmp.setIsSend((short) 1);
				this.orderDao.update(tmp);
			}
			
			results.add(result);
		}
		
		return results;
	}
	
	private TrackerApplyResult confirmApac(SimpleOrderWithAccount order) {
		TrackerApplyResult result = new TrackerApplyResult();

		try {
			ConfirmAPACShippingPackageRequest request = this.assembleConfirmRequest(order);
			ConfirmAPACShippingPackageResponse2 response = ApacShippingServiceSoap_ApacShippingServiceSoap_Client
					.confirmAPACShippingPackage(request);
			
			if ("Success".equals(response.getAck().value())) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				result.setMessage(getMessageFromNotificationList(response.getNotificationList()));
			}
			
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}

		return result;
	}

	private ConfirmAPACShippingPackageRequest assembleConfirmRequest(SimpleOrderWithAccount order) {
		ConfirmAPACShippingPackageRequest request = new ConfirmAPACShippingPackageRequest();
		request.setAPIDevUserID(order.getEbayDevid());
		request.setAPISellerUserToken(order.getEbayToken());
		request.setAPISellerUserID(order.getAccountName());
		request.setAppID(order.getEbayAppid());
		request.setAppCert(order.getEbayCertid());
		request.setMessageID("" + System.currentTimeMillis());
		request.setVersion("4.0.0");
		request.setCarrier("CNPOST");
		request.setTrackCode(order.getTrackNumber());
		
		return request;
	}

	@Override
	public List<TrackerApplyResult> confirmApacForPackage(List<String> packageIds) {
		
		if (packageIds == null || packageIds.isEmpty()) {
			return null;
		}
		
		List<TrackerApplyResult> results = new ArrayList<>();
		for (String packageId : packageIds) {
			OrderPackage op = this.orderPackageDao.getById(packageId);
			Integer orderId = op.getOrderId();
			
			SimpleOrderWithAccount order = this.orderDao.getSimpleOrderAccountById(orderId);
			
			TrackerApplyResult result = null;
			
			if (order == null) {
				result = new TrackerApplyResult();
				result.setSuccess(false);
				result.setPackageId(packageId);
				result.setMessage("没找对应的订单");
			} else {
				result = this.confirmApac(order);
				result.setOrderId(orderId);
				result.setPackageId(packageId);
				
				// 成功后更新为已标发
				if (result.isSuccess()) {
					Order tmp = new Order();
					tmp.setId(result.getOrderId());
					tmp.setIsSend((short) 1);
					this.orderDao.update(tmp);
				}
			}
			
			results.add(result);
			
		}
		
		return results;
	}

	@Override
	public List<TrackerApplyResult> recreateApac(List<Integer> orderids) {
		if (orderids == null || orderids.size() == 0) {
			return null;
		}

		List<TrackerApplyResult>  results = new ArrayList<>();

		for (Integer orderId : orderids) {

			SimpleOrderWithAccount order = this.orderDao.getSimpleOrderAccountById(orderId);
			TrackerApplyResult result = this.recreateApac(order);
			result.setOrderId(orderId);
			
			// 成功后更新为已标发
			if (result.isSuccess()) {
				Order tmp = new Order();
				tmp.setId(result.getOrderId());
				tmp.setTrackNumber(result.getTrackNumber());
				tmp.setIsSend((short) 1);
				this.orderDao.update(tmp);
				
				// 如果已经生成包裹，则把跟踪号和发货方式更新到包裹上去
				this.orderPackageDao.updateTrackNumberAndShippingNameByOrderId(result.getOrderId());
			}
			
			results.add(result);
		}
		
		return results;
	}
	
	private TrackerApplyResult recreateApac(SimpleOrderWithAccount order) {
		TrackerApplyResult result = new TrackerApplyResult();

		try {
			RecreateAPACShippingPackageRequest request = this.assembleRecreateRequest(order);
			RecreateAPACShippingPackageResponse2 response = ApacShippingServiceSoap_ApacShippingServiceSoap_Client
					.recreateAPACShippingPackage(request);
			
			if ("Success".equals(response.getAck().value())) {
				result.setSuccess(true);
				result.setTrackNumber(response.getTrackCode());
			} else {
				result.setSuccess(false);
				result.setMessage(getMessageFromNotificationList(response.getNotificationList()));
			}
			
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}

		return result;
	}

	private RecreateAPACShippingPackageRequest assembleRecreateRequest(SimpleOrderWithAccount order) {
		RecreateAPACShippingPackageRequest request = new RecreateAPACShippingPackageRequest();
		
		request.setAPIDevUserID(order.getEbayDevid());
		request.setAPISellerUserToken(order.getEbayToken());
		request.setAPISellerUserID(order.getAccountName());
		request.setAppID(order.getEbayAppid());
		request.setAppCert(order.getEbayCertid());
		request.setMessageID("" + System.currentTimeMillis());
		request.setVersion("4.0.0");
		request.setCarrier("CNPOST");
		request.setTrackCode(order.getTrackNumber());
		
		return request;
	}
	
}

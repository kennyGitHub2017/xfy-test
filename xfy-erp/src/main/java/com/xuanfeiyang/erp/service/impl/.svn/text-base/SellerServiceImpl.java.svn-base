package com.xuanfeiyang.erp.service.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.SellerDao;
import com.xuanfeiyang.erp.dao.UserInfoDao;
import com.xuanfeiyang.erp.dao.UserLoginDao;
import com.xuanfeiyang.erp.dao.UserRoleDao;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.domain.UserRole;
import com.xuanfeiyang.erp.param.SellerParams;
import com.xuanfeiyang.erp.service.SellerService;
import com.xuanfeiyang.erp.service.SmsSendService;

@Service
public class SellerServiceImpl implements SellerService {

	@Resource
	private SellerDao sellerDao;
	
	@Resource
	private UserLoginDao userLoginDao;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private SmsSendService smsSendService;
	
	@Resource
	private UserRoleDao userRoleDao;
	
	@Override
	public Page<Seller> findPage(PageRequest pageRequest, SellerParams params) {
		return this.sellerDao.findPage(pageRequest, params);
	}

	@Override
	public Page<Seller> findPageWithDeposit(PageRequest pageRequest, SellerParams params) {
		return this.sellerDao.findPageWithDeposit(pageRequest, params);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(Integer id) {
		if (id == null) {
			return;
		}
		
		// 先删除卖家所有用户的登录信息，再删除卖家搜有的用户信息，最后删除卖家信息
		this.userLoginDao.deleteBySellerId(id);
		this.userInfoDao.deleteBySellerId(id);
		this.sellerDao.delete(id);
	}

	@Override
	public void save(Seller seller) {
		if (seller == null) {
			return;
		}
		
		seller.setStatus(0);
		seller.setCreatedTime(new Date());
		seller.setLastUpdatedTime(seller.getCreatedTime());
		
		this.sellerDao.save(seller);
	}

	@Override
	public Seller load(Integer id) {
		return id == null ? null : this.sellerDao.load(id);
	}

	@Override
	public void update(Seller seller) {
		if (seller == null) {
			return;
		}
		
		seller.setLastUpdatedTime(new Date());
		
		this.sellerDao.update(seller);
	}

	private void updateStatus(Integer sellerId, Integer targetStatus, String statusNote) {
		
		if (sellerId == null || targetStatus == null) {
			return;
		}
		
		Seller seller = new Seller();
		seller.setId(sellerId);
		seller.setStatus(targetStatus);
		Date now = new Date();
		seller.setStatusTime(now);
		seller.setStatusNote(statusNote);
		seller.setLastUpdatedTime(now);;
		
		this.sellerDao.update(seller);
	}

	@Override
	public void approve(Integer sellerId) {
		this.updateStatus(sellerId, 2, null);
		
		String message = App.getConfig("sms.content.seller.approved");
		this.sendSms(sellerId, message);
	}

	@Override
	public void unapprove(Integer sellerId, String note) {
		
		note = StringUtils.trimToNull(note);
		Preconditions.checkNotNull(note, "审核不过时，必须填写审核不通过原因");
		
		this.updateStatus(sellerId, 3, note);
		
		String message = App.getConfig("sms.content.seller.unapproved");
		this.sendSms(sellerId, message);
	}

	private void sendSms(Integer sellerId, String message) {
		Seller seller = this.sellerDao.load(sellerId);
		String mobile = seller.getMobile();
		if (mobile != null) {
			this.smsSendService.send(mobile, message);
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateSelfFlag(Integer id, String selfFlag) {
		Preconditions.checkNotNull(id, "缺少ID");
		Preconditions.checkNotNull(selfFlag, "缺少 selfFlag");
		Preconditions.checkArgument("0".equals(selfFlag) || "1".equals(selfFlag), "selfFlag 取值不对");
		
		this.sellerDao.updateSelfFlag(id, selfFlag);
		// 如果为自营，取消VIP
		if ("1".equals(selfFlag)) {
			this.sellerDao.updateVipFlag(id, "0");
		}
		
		// 更新状态后，需要将卖家下用户的角色相应修改
		//this
		List<UserInfo> sellerUsers = this.userInfoDao.findBySellerId(id);
		if (sellerUsers.isEmpty()) {
			return;
		}
		
		for (UserInfo user : sellerUsers) {
			this.userRoleDao.deleteByUserIdAndRoleId(user.getUserId(), "1".equals(selfFlag) ? App.ROLE_ID_SELLER : App.ROLE_ID_SELF_SELLER);
			// 如果为自营，取消VIP
			if ("1".equals(selfFlag)) {
				this.userRoleDao.deleteByUserIdAndRoleId(user.getUserId(), App.ROLE_ID_VIP_SELLER);
			}
			
			UserRole ur = new UserRole();
			ur.setUserId(user.getUserId());
			ur.setRoleId("1".equals(selfFlag) ? App.ROLE_ID_SELF_SELLER : App.ROLE_ID_SELLER);
			Date now = new Date();
			ur.setCreatedTime(now);
			ur.setLastUpdatedTime(now);
			this.userRoleDao.insert(ur);
		}
	}

	@Override
	public int countPendingSeller() {
		return this.sellerDao.countByStatus(1);
	}
	
	/**
	 * 自动给卖家发送信息，提醒提交审核资料
	 */
	public void autoSendSmsToSellerforApprove() {
		int maxSmsSendTimes = 3;
		List<Seller> sellers = this.sellerDao.findForSendSms(maxSmsSendTimes);
		
		if (sellers == null || sellers.isEmpty()) {
			return;
		}
		
		for (Seller seller : sellers) {
			String mobile = seller.getMobile();
			if (mobile != null && mobile.length() == 11) {
				this.smsSendService.send(mobile, App.getConfig("sms.content.seller.approve.tips"));
				this.sellerDao.increaseSmsSendTimes(seller.getId());
			}
		}
	}

	@Override
	public void lock(Integer sellerId) {
		this.updateLocked(sellerId, 1);
	}

	@Override
	public void unlock(Integer sellerId) {
		this.updateLocked(sellerId, 0);
	}

	private void updateLocked(Integer sellerId, int locked) {
		Seller s = new Seller();
		s.setId(sellerId);
		s.setLocked(locked);
		s.setLockedTime(new Date());
		
		this.sellerDao.update(s);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateVipFlag(Integer id, String vipFlag) {
		Preconditions.checkNotNull(id, "缺少ID");
		Preconditions.checkNotNull(vipFlag, "缺少 vipFlag");
		Preconditions.checkArgument("0".equals(vipFlag) || "1".equals(vipFlag), "vipFlag 取值不对");
		
		this.sellerDao.updateVipFlag(id, vipFlag);
		// 如果是VIP，取消自营
		if ("1".equals(vipFlag)) {
			this.sellerDao.updateSelfFlag(id, "0");
		}
		
		// 更新状态后，需要将卖家下用户的角色相应修改
		List<UserInfo> sellerUsers = this.userInfoDao.findBySellerId(id);
		if (sellerUsers.isEmpty()) {
			return;
		}
		
		/* 
		 * 如果是VIP，则为该卖家的所有用户设置为VIP角色
		 * 否则，删掉该卖家所有用户的VIP角色
		 */
		if ("1".equals(vipFlag)) {
			for (UserInfo user : sellerUsers) {
				// 如果是VIP，取消自营
				this.userRoleDao.deleteByUserIdAndRoleId(user.getUserId(), App.ROLE_ID_SELF_SELLER);
				
				UserRole ur = new UserRole();
				ur.setUserId(user.getUserId());
				ur.setRoleId(App.ROLE_ID_VIP_SELLER);
				Date now = new Date();
				ur.setCreatedTime(now);
				ur.setLastUpdatedTime(now);
				this.userRoleDao.insert(ur);
			}
		} else {
			for (UserInfo user : sellerUsers) {
				this.userRoleDao.deleteByUserIdAndRoleId(user.getUserId(), App.ROLE_ID_VIP_SELLER);
			}
		}
	}

	@Override
	public void upgradeAgent(Integer id) {
		
		Seller seller = this.sellerDao.load(id);
		checkArgument(seller != null, "找不到对应的卖家用户信息,ID: %s", id);
		checkArgument(seller.getStatus() == 2, "卖家用户还未审核通过或代理商申请审核中");
		
		Date now = new Date();
		/*
		 * 1, 卖家主用户的类型变为代理商，
		 * 2, 卖家主用户的角色 增加代理商角色
		 * 3, 将卖家的状态设置为待审核
		 */
		
		List<UserInfo> users = this.userInfoDao.findBySellerId(id);
		
		for (UserInfo user : users) {
			if (user.getIsMaster() == 1) {
				// 1, 卖家主用户的类型变为代理商
				UserInfo tmp = new UserInfo();
				tmp.setUserId(user.getUserId());
				tmp.setType(3);
				this.userInfoDao.update(tmp);
				
				// 2, 卖家主用户的角色 增加代理商角色
				UserRole userRole = new UserRole();
				userRole.setUserId(user.getUserId());
				userRole.setRoleId(App.ROLE_ID_AGENT);
				userRole.setCreatedTime(now);
				userRole.setLastUpdatedTime(userRole.getCreatedTime());
				this.userRoleDao.insert(userRole);
			}
		}
		
		// 3, 将卖家的状态设置为待审核
		Seller tmp = new Seller();
		tmp.setId(seller.getId());
		tmp.setStatus(1);
		tmp.setStatusTime(now);
		String note = "卖家申请升级代理商需重新审核@" + now;
		tmp.setStatusNote((tmp.getStatusNote() == null ? "" : tmp.getStatusNote() + ", ") + note);
		
		this.sellerDao.update(tmp);
	}

	@Override
	public List<Seller> findByAgent(Integer agentUserId) {
		return this.sellerDao.findByAgent(agentUserId);
	}

	@Override
	public Integer getCountByUserId(Integer agentUserId) {
		return this.sellerDao.getCountByUserId(agentUserId);
	}
	
	
}

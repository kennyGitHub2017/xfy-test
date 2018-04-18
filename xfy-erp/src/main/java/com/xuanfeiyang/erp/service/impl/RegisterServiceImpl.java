package com.xuanfeiyang.erp.service.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.SellerDao;
import com.xuanfeiyang.erp.dao.UserInfoDao;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.domain.UserLogin;
import com.xuanfeiyang.erp.service.DuplicateDataExcepption;
import com.xuanfeiyang.erp.service.RegisterService;
import com.xuanfeiyang.erp.service.SellerService;
import com.xuanfeiyang.erp.service.UserService;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Resource
	private UserService userService;
	
	@Resource
	private SellerDao sellerDao;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private SellerService sellerService;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void registerSeller(UserInfo userInfo, String password) throws DuplicateDataExcepption {
		if (password == null) {
			return;
		}
		
		Date now = new Date();
		
		// 保存卖家信息
		Seller seller = new Seller();
		seller.setContacts(userInfo.getName());
		seller.setMobile(userInfo.getMobile());
		seller.setEmail(userInfo.getEmail());
		seller.setAddress(userInfo.getAddress());
		seller.setIdCardNo(userInfo.getIdCardNo());
		seller.setReferrerMobile(userInfo.getReferrerMobile());
		seller.setInfoAcquisitionChannel(userInfo.getInfoAcquisitionChannel());
		seller.setStatus(0);
		seller.setCreatedTime(now);
		seller.setLastUpdatedTime(now);
		
		if (StringUtils.isNotBlank(userInfo.getAgentMobile())) {
			UserInfo tmp = this.userInfoDao.loadByMobile(StringUtils.trimToEmpty(userInfo.getAgentMobile()));
			if (tmp != null) {
				seller.setAgentUserId(tmp.getUserId());
			}
		}
		
		this.sellerDao.save(seller);
		
		// 关联卖家ID 
		userInfo.setSellerId(seller.getId());
		userInfo.setIsMaster(1); // 卖家注册时产生的第一个用户为主用户
		userInfo.setType(2); // 类型为 卖家
		userInfo.setStatus(1);
		// 保存用户基本信息
		this.userService.saveUserInfo(userInfo, password);
		
		// 角色信息
		List<Integer> roleIds = new ArrayList<Integer>(1);
		roleIds.add(App.ROLE_ID_SELLER);
		this.userService.saveUserRoles(userInfo.getUserId(), roleIds);
	}

	@Override
	public void registerAgent(UserInfo userInfo, String password)
			throws DuplicateDataExcepption {
		
		userInfo.setSellerId(-1);
		userInfo.setType(3); // 类型为 代理商
		userInfo.setStatus(0);
		userInfo.setStatusTime(new Date());
		// 保存用户基本信息
		this.userService.saveUserInfo(userInfo, password);

		// 角色信息
		List<Integer> roleIds = new ArrayList<Integer>(1);
		roleIds.add(App.ROLE_ID_AGENT);
		this.userService.saveUserRoles(userInfo.getUserId(), roleIds);
	}

	@Override
	public void upgradeAgent(UserLogin userLogin) {
		UserInfo user = this.userInfoDao.load(userLogin.getUserId());
		checkArgument(user != null, "找不到对应的用户信息");
		
		Integer sellerId = user.getSellerId();
		checkArgument(sellerId != null && user.getSellerId() > App.DEFAULT_SELLER_ID, "只有小卖家用户才能升级为代理商");
		
		this.sellerService.upgradeAgent(sellerId);
	}

	
}

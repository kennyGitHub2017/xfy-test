package com.xuanfeiyang.erp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.RolePowerDao;
import com.xuanfeiyang.erp.dao.UserInfoDao;
import com.xuanfeiyang.erp.dao.UserLoginDao;
import com.xuanfeiyang.erp.dao.UserPowerDao;
import com.xuanfeiyang.erp.dao.UserRoleDao;
import com.xuanfeiyang.erp.dao.UsersPowerDao;
import com.xuanfeiyang.erp.domain.RolePower;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.domain.UserLogin;
import com.xuanfeiyang.erp.domain.UserPower;
import com.xuanfeiyang.erp.domain.UserRole;
import com.xuanfeiyang.erp.domain.UsersPower;
import com.xuanfeiyang.erp.param.UserParams;
import com.xuanfeiyang.erp.service.DuplicateDataExcepption;
import com.xuanfeiyang.erp.service.RoleService;
import com.xuanfeiyang.erp.service.SellerService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.util.EncryptUtils;


@Service
public class UserServiceImpl implements UserService {
	
//	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserLoginDao userLoginDao;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource 
	private RolePowerDao rolePowerDao;
	
	@Resource
	private UserPowerDao userPowerDao;
	
	@Resource
	private SellerService sellerService;
	
	@Resource
	private UsersPowerDao usersPowerDao;
	
	/**
	 * 密码加密
	 * @param sourcePassword
	 * @return
	 */
	private String encryptPassword(String sourcePassword) {
		return EncryptUtils.encryptByMD5(sourcePassword);
	}
	
	@Override
	public UserLogin loadUserLoginByUsernameAndPassword(String username, String password) {
		if (username == null || password == null) {
			return null;
		}
		
		username = username.trim();
		if ("".equals(username)) {
			return null;
		}
		
		List<UserLogin> users = this.userLoginDao.findByUsernameOrMobileOrEmail(username);
		if (users == null || users.size() == 0) {
			return null;
		}
		
		UserLogin user = users.get(0);
		
		String encryptPassword = this.encryptPassword(password);
		if (encryptPassword.equals(user.getPassword())) {
			return user;
		}
		
		return null;
		
	}
	
	@Override
	public UserInfo loadUserInfo(Integer userId) {
		return userId == null ? null : this.userInfoDao.load(userId);
	}
	
	@Override
	public Page<UserInfo> findPage(PageRequest pageRequest, UserParams params) {
		return this.userInfoDao.findPage(pageRequest, params);
	}

	@Override
	public UserLogin loadUserLogin(Integer userId) {
		return userId == null ? null : this.userLoginDao.load(userId);
	}

	@Override
	public void saveOrUpdateLoginInfo(UserLogin loginInfo)
			throws DuplicateDataExcepption {
		if (loginInfo == null) {
			return;
		}
		
		if (loginInfo.getUserId() != null) {
			this.updateLoginInfo(loginInfo);
		} else {
			this.saveLoginInfo(loginInfo);
		}
	}
	
	private void updateLoginInfo(UserLogin loginInfo)
			throws DuplicateDataExcepption {
		// 检查用户名唯一性
		if (loginInfo.getUsername() != null) {
			UserLogin tmp = this.userLoginDao.loadByUsername(loginInfo
					.getUsername());
			if (tmp != null && !tmp.getUserId().equals(loginInfo.getUserId())) {
				throw new DuplicateDataExcepption(loginInfo);
			}
		}

		// 密码为空时不更新
		// 密码加密存储
		String password = StringUtils.trimToEmpty(loginInfo.getPassword());
		if (!password.isEmpty()) {
			loginInfo.setPassword(this.encryptPassword(password));
		} else {
			loginInfo.setPassword(null);
		}

		loginInfo.setLastUpdatedTime(new Date());

		this.userLoginDao.update(loginInfo);
	}
	
	private void saveLoginInfo(UserLogin loginInfo) throws DuplicateDataExcepption {
		
		// 用户名唯一
		UserLogin tmp = this.userLoginDao.loadByUsername(loginInfo.getUsername());
		if (tmp != null) {
			throw new DuplicateDataExcepption("user.username.exists", loginInfo);
		}
		
		// 密码加密存储
		String password = StringUtils.trimToEmpty(loginInfo.getPassword());
		if (! password.isEmpty()) {
			loginInfo.setPassword(this.encryptPassword(loginInfo.getPassword()));
		}
		loginInfo.setCreatedTime(new Date());
		loginInfo.setLastUpdatedTime(loginInfo.getCreatedTime());
		
		this.userLoginDao.save(loginInfo);
	}
	
	@Override
	public void updatePassword2(Integer userId, String oldPassword,
			String newPassword) {
		if (userId == null || oldPassword == null || newPassword == null) {
			return;
		}
		
		if (oldPassword.equals(newPassword)) {
			return;
		}
		
		UserLogin user = this.userLoginDao.load(userId);
		if (user == null) {
			return;
		}
		
		String oldPasswordEncrypted = this.encryptPassword(oldPassword);
		if (! oldPasswordEncrypted.equals(user.getPassword())) {
			throw new IllegalArgumentException("user.old.password.incorrect");
		}
		
		String newPasswordEncrypted = this.encryptPassword(newPassword);
		UserLogin tmp = new UserLogin();
		tmp.setUserId(userId);
		tmp.setPassword(newPasswordEncrypted);
		tmp.setLastUpdatedTime(new Date());
		
		this.userLoginDao.update(tmp);
	}
	
	private void initUserInfo(UserInfo user) {
		user.setCode(StringUtils.trimToNull(user.getCode()));
		user.setMobile(StringUtils.trimToNull(user.getMobile()));
		user.setEmail(StringUtils.trimToNull(user.getEmail()));
	}
	
	/**
	 * 找回密码功能，重置密码
	 */
	@Override
	public void updateUserPassword(Integer userId, String newPassword) {
		if(userId == null){
			return;
		}
		UserLogin userLogin = this.userLoginDao.load(userId);
		if(userLogin == null){
			return;
		}
		String newPasswordEncrypted = this.encryptPassword(newPassword);
		UserLogin tmp = new UserLogin();
		tmp.setUserId(userId);
		tmp.setPassword(newPasswordEncrypted);
		tmp.setLastUpdatedTime(new Date());
		
		this.userLoginDao.update(tmp);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public synchronized void saveUserInfo(UserInfo user, String password) throws DuplicateDataExcepption {
		if (user == null) {
			return;
		}
		
		this.initUserInfo(user);
		
		// 工号和手机号不能同时为空，因为这两个要作为登陆名
		if (user.getCode() == null && user.getMobile() == null) {
			return;
		}
		
		// 验证工号
		if (user.getCode() != null) {
			UserInfo tmp = this.userInfoDao.loadByCode(user.getCode());
			if (tmp != null) {
				throw new DuplicateDataExcepption("user.code.exists", user.getCode());
			}
		}
		
		// 验证手机
		if (user.getMobile() != null) {
			UserInfo tmp = this.userInfoDao.loadByMobile(user.getCode());
			if (tmp != null) {
				throw new DuplicateDataExcepption("user.mobile.exists", user.getMobile());
			}
		}
		
		// 验证email
		if (user.getEmail() != null) {
			UserInfo tmp = this.userInfoDao.loadByEmail(user.getCode());
			if (tmp != null) {
				throw new DuplicateDataExcepption("user.email.exists", user.getEmail());
			}
		}
		
		// 登录名优先去工号，再取手机号
		String username = user.getCode() != null ? user.getCode() : user.getMobile();
		
		UserLogin loginInfo = new UserLogin();
		loginInfo.setUsername(username);
		// 密码为空时去系统配置的默认密码
		loginInfo.setPassword(password == null ? App.getConfig("user.defalut.password", "123456") : password);
		
		this.saveLoginInfo(loginInfo);

		Date now = new Date();
		user.setUserId(loginInfo.getUserId());
		user.setCreatedTime(now);
		user.setLastUpdatedTime(now);
		
		this.userInfoDao.save(user);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public synchronized void updateUserInfo(UserInfo user) throws DuplicateDataExcepption {
		if (user == null) {
			return;
		}
		
		user.setCode(StringUtils.trimToNull(user.getCode()));
		user.setMobile(StringUtils.trimToNull(user.getMobile()));
		user.setEmail(StringUtils.trimToNull(user.getEmail()));
		
		// 验证工号
		if (user.getCode() != null) {
			UserInfo tmp = this.userInfoDao.loadByCode(user.getCode());
			if (tmp != null && ! tmp.getUserId().equals(user.getUserId())) {
				throw new DuplicateDataExcepption("user.code.exists", user.getCode());
			}
		}
		
		// 验证手机
		if (user.getMobile() != null) {
			UserInfo tmp = this.userInfoDao.loadByMobile(user.getMobile());
			if (tmp != null && ! tmp.getUserId().equals(user.getUserId())) {
				throw new DuplicateDataExcepption("user.mobile.exists", user.getMobile());
			}
		}
		
		// 验证email
		if (user.getEmail() != null) {
			UserInfo tmp = this.userInfoDao.loadByEmail(user.getEmail());
			if (tmp != null && ! tmp.getUserId().equals(user.getUserId())) {
				throw new DuplicateDataExcepption("user.email.exists", user.getEmail());
			}
		}
		
		user.setLastUpdatedTime(new Date());
		this.userInfoDao.update(user);
		
		// 若存在工号修改、手机修改 等情况，需要更新登陆信息
		// 简单起见，直接修改，登录名优先使用工号，再使用手机号
		UserLogin loginInfo = new UserLogin();
		loginInfo.setUserId(user.getUserId());
		loginInfo.setUsername(user.getCode() != null ? user.getCode() : user.getMobile());
		try {
			this.userLoginDao.update(loginInfo);
		} catch (DuplicateKeyException e) {
			throw new DuplicateDataExcepption("user.username.exists", loginInfo.getUsername());
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(Integer userId) {
		this.userInfoDao.delete(userId);
		this.userLoginDao.delete(userId);
	}

	@Override
	public List<UserInfo> findAll() {
		return this.userInfoDao.findAll();
	}

	@Override
	@Transactional
	public void saveUserRoles(Integer userId, List<Integer> roleIds) {
		if (userId == null) {
			return;
		}
		
		// 先删除现有权限，再添加新权限
		this.userRoleDao.deleteByUserId(userId);
		
		if (roleIds == null) {
			return;
		}
		
		for (Integer roleId : roleIds) {
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			Date now = new Date();
			userRole.setCreatedTime(now );
			userRole.setLastUpdatedTime(now);
			this.userRoleDao.insert(userRole );
		}
	}

	/**
	 * 查询某用户权限信息
	 * <p>返回map结构结构为 {powerType => [powerCode1, powerCode2]} 如下：<br>
	 * {<br>
	 *   "module" => ["system", "order"],<br>
	 *   "page" => ["user", "role", ...],<br>
	 *   "function" => ["user_delete", "user_add", "role_delte", ...]<br>
	 * }
	 * 
	 * <p> 参考：{@link RoleService#findRolePowers(Integer)}
	 * 
	 * @param userId
	 */
	@Override
	public Map<String, Set<String>> findUserPowers(Integer userId) {
		if (userId == null) {
			return null;
		}
		
		// 根据用户ID 查询该用户的所有的角色的权限的合集
		List<RolePower> allRolePowers = this.rolePowerDao.findByUserId(userId);
		if (allRolePowers == null || allRolePowers.isEmpty()) {
			return null;
		}
		
		List<UsersPower> usersPowers = this.usersPowerDao.findByUserId(userId);
		
		

		Map<String, Set<String>> powersMap = new HashMap<>();
		// 卖家子帐号默认不继承角色权限；其他用户继承角色权限
		if (usersPowers == null || usersPowers.isEmpty()) {
			UserInfo u = this.userInfoDao.load(userId);
			if (u.getSellerId() > App.DEFAULT_SELLER_ID 
					&& u.getIsMaster().intValue() == 0) {
				return null;
			}
			
			for (RolePower power : allRolePowers) {
				Set<String> typePowers = powersMap.get(power.getPowerType());
				if (typePowers == null) {
					typePowers =  new HashSet<String>();
					powersMap.put(power.getPowerType(), typePowers);
				}
				
				typePowers.add(power.getPowerCode());
			}
			
			return powersMap;
			
		} else {
			List<UsersPower> tmp = new ArrayList<>();
			for (RolePower rolePower : allRolePowers) {
				for (UsersPower userPower : usersPowers) {
					if (rolePower.getPowerType().equals(userPower.getPowerType()) &&
							rolePower.getPowerCode().equals(userPower.getPowerCode())) {
						tmp.add(userPower);
					}
				}
			}
			
			for (UsersPower power : tmp) {
				Set<String> typePowers = powersMap.get(power.getPowerType());
				if (typePowers == null) {
					typePowers =  new HashSet<String>();
					powersMap.put(power.getPowerType(), typePowers);
				}
				
				typePowers.add(power.getPowerCode());
			}
		}
		return powersMap;
	}

	@Override
	public List<RolePower> findAllRolesPowers(Integer userId) {
		return this.rolePowerDao.findByUserId(userId);
	}

	@Override
	public UserInfo loadByCode(String code) {
		return code == null ? null : this.userInfoDao.loadByCode(code);
	}

	@Override
	public UserInfo loadByMobile(String mobile) {
		return mobile == null ? null : this.userInfoDao.loadByMobile(mobile);
	}

	@Override
	public UserInfo loadByIdCardNo(String icCardNo) {
		return icCardNo == null ? null : this.userInfoDao.loadByIdCardNo(icCardNo);
	}

	@Override
	public UserInfo loadByEmail(String email) {
		return email == null ? null : this.userInfoDao.loadByEmail(email);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveUserInfoForSeller(Integer sellerId, String name, String username, String password) throws DuplicateDataExcepption {
		
		Seller seller = this.sellerService.load(sellerId);
		Preconditions.checkArgument(seller != null, "卖家不存在");
		
		// 卖家子账户限制：非自营卖家最多添加2个，自营卖家无限制
		if (sellerId > App.DEFAULT_SELLER_ID) {
			if ("0".equals(seller.getSelfFlag())) {
				if (this.userInfoDao.countBySellerId(sellerId) > 2) {
					throw new DuplicateDataExcepption("最多只能增加二个子账户");
				}
			}
		}
		
		Date now = new Date();

		// 登录信息
		UserLogin loginInfo = new UserLogin();
		loginInfo.setUsername(username);
		loginInfo.setPassword(password);
		loginInfo.setCreatedTime(now);
		loginInfo.setLastUpdatedTime(now);
		
		this.saveLoginInfo(loginInfo);
		
		// 用户信息
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(loginInfo.getUserId());
		userInfo.setName(name);
		userInfo.setSellerId(sellerId);
		userInfo.setIsMaster(0);
		userInfo.setCreatedTime(now );
		userInfo.setLastUpdatedTime(now);
		userInfo.setType(2);
		userInfo.setStatus(1);
		userInfo.setStatusTime(now);
		
		this.userInfoDao.save(userInfo);
		
		// 角色信息
		List<Integer> roleIds = new ArrayList<Integer>(1);
		roleIds.add("1".equals(seller.getSelfFlag()) ? App.ROLE_ID_SELF_SELLER : App.ROLE_ID_SELLER);
		if ("1".equals(seller.getVipFlag())) {
			roleIds.add(App.ROLE_ID_VIP_SELLER);
		}
		this.saveUserRoles(userInfo.getUserId(), roleIds);
	}

	@Override
	public Integer getUserIdByUsername(String username) {
		
		username = StringUtils.trimToNull(username);
		if (username == null) {
			return null;
		}
		
		UserLogin user = this.userLoginDao.loadByUsername(username);
		if (user != null) {
			return user.getUserId();
		}
		
		return null;
	}

	@Override
	@Transactional
	public void savePlatformAccountPower(UserPower param) {
		this.userPowerDao.saveUserPower(param);
	}

	@Override
	public void deletePlatformAccountPower(Integer userId) {
			this.userPowerDao.deleteUserPower(userId);
	}

	@Override
	public List<UserPower> getPlatformAccountPower(Integer userId) {
		return this.userPowerDao.getUserPower(userId);
	}

	@Override
	public List<UserInfo> findByDepartment(Integer departmentId) {
		return	this.userInfoDao.findByDepartment(departmentId);
	}

	@Override
	public List<UserInfo> findSaleUser() {
		return this.userInfoDao.findSaleUser();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void savePowers(Integer userId, List<String> modulePowers,
			List<String> pagePowers, List<String> functionPowers) {
		// 先删除原有权限
		this.usersPowerDao.deleteByUserId(userId);
		
		// 保存新权限
		Date now = new Date();
		
		if (modulePowers != null) {
			for (String code : modulePowers) {
				this.usersPowerDao.insert(buildRolePower(userId, code, "module", now));
			}
		}
		
		if (pagePowers != null) {
			for (String code : pagePowers) {
				this.usersPowerDao.insert(buildRolePower(userId, code, "page", now));
			}
		}
		
		if (functionPowers != null) {
			for (String code : functionPowers) {
				this.usersPowerDao.insert(buildRolePower(userId, code, "function", now));
			}
		}
	}

	private UsersPower buildRolePower(Integer userId, String powerCode,
			String powerType, Date when) {
		UsersPower up = new UsersPower();
		up.setUserId(userId);
		up.setPowerCode(powerCode);
		up.setPowerType(powerType);
		up.setCreatedTime(when);
		return up;
	}

	@Override
	public void lock(Integer userId) {
		this.lockUser(userId, 1);
	}

	@Override
	public void unlock(Integer userId) {
		this.lockUser(userId, 0);
	}

	private void lockUser(Integer userId, int locked) {
		UserInfo u = new UserInfo();
		u.setUserId(userId);
		u.setLocked(locked);
		u.setLockedTime(new Date());
		this.userInfoDao.update(u);
	}

	
	
	
}

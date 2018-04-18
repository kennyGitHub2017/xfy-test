package com.xuanfeiyang.erp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.RolePower;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.domain.UserLogin;
import com.xuanfeiyang.erp.domain.UserPower;
import com.xuanfeiyang.erp.param.UserParams;

public interface UserService {

	public UserLogin loadUserLoginByUsernameAndPassword(String username, String password);
	
	public UserInfo loadUserInfo(Integer userId);
	
	public Page<UserInfo> findPage(PageRequest pageRequest, UserParams params);

	public UserLogin loadUserLogin(Integer userId);
	
	public void saveOrUpdateLoginInfo(UserLogin userLogin) throws DuplicateDataExcepption;
	
	/**
	 * 修改密码
	 * @param id 用户ID
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * 
	 * @throws IllegalArgumentException 
	 */
	public void updatePassword2(Integer id, String oldPassword, String newPassword);
	
	/**
	 * 重置密码
	 * @param id
	 * @param newPassword 新密码
	 */
	public void updateUserPassword(Integer id,String newPassword);

	public void saveUserInfo(UserInfo user, String password) throws DuplicateDataExcepption;
	
	/**
	 * 卖家新增用户
	 * 
	 * @param user
	 * @param password
	 * @throws DuplicateDataExcepption
	 */
	public void saveUserInfoForSeller(Integer sellerId, String name, String username, String password) throws DuplicateDataExcepption;

	public void updateUserInfo(UserInfo user) throws DuplicateDataExcepption;
	
	public void delete(Integer userId);
	
	public List<UserInfo> findAll();
	
	/**
	 * 保存用户角色信息
	 * @param userId
	 * @param roleIds
	 */
	public void saveUserRoles(Integer userId, List<Integer> roleIds);
	
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
	public Map<String, Set<String>> findUserPowers(Integer userId);
	
	/**
	 * 查询该用户对应的所有角色的权限的合集
	 * 
	 * @param userId
	 * @return
	 */
	public List<RolePower> findAllRolesPowers(Integer userId); 
	
	public UserInfo loadByCode(String code);

	public UserInfo loadByMobile(String mobile);
	
	public UserInfo loadByIdCardNo(String icCardNo);

	public UserInfo loadByEmail(String email);
	
	public Integer getUserIdByUsername(String username);
	
	
	
	
	/***
	 * 
	 * 卖家设置 用户平台账号权限
	 * 保存 saveUserPower
	 * 删除 deleteUserPower
	 * 查询 getUserPower
	 * @param param
	 */
	public void savePlatformAccountPower(UserPower param);
	public void deletePlatformAccountPower(Integer userId);
	public List<UserPower> getPlatformAccountPower(Integer userId);
	
	
	/***
	 * 根据部门ID查询
	 * @param departmentId 部门ID
	 * @return
	 */
	List<UserInfo> findByDepartment(Integer departmentId);
	
	/**
	 * 查询销售人员
	 * @return
	 */
	List<UserInfo> findSaleUser();

	/**
	 * 保存用户权限
	 * @param userId
	 * @param modulePowers
	 * @param pagePowers
	 * @param functionPowers
	 */
	public void savePowers(Integer userId, List<String> modulePowers,
			List<String> pagePowers, List<String> functionPowers);

	/**
	 * 锁定
	 * @param userId
	 */
	public void lock(Integer userId);
	
	/**
	 * 解锁
	 * @param userId
	 */
	public void unlock(Integer userId);
}

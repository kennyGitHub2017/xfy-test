package com.xuanfeiyang.erp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.erp.dao.RoleDao;
import com.xuanfeiyang.erp.dao.RolePowerDao;
import com.xuanfeiyang.erp.dao.UserRoleDao;
import com.xuanfeiyang.erp.domain.Role;
import com.xuanfeiyang.erp.domain.RolePower;
import com.xuanfeiyang.erp.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource
	private RolePowerDao rolePowerDao;
	
	@Override
	public void save(Role role) {
		if (role == null || role.getName() == null) {
			return;
		}
		
		Date now = new Date();
		role.setCreatedTime(now);
		role.setLastUpdatedTime(now);
		
		this.roleDao.insert(role);
	}

	@Override
	public void update(Role role) {
		if (role == null || role.getId() == null) {
			return;
		}
		
		role.setLastUpdatedTime(new Date());
		this.roleDao.update(role);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		if (id == null) {
			return;
		}
		
		// TODO 内置角色不能删除
		this.userRoleDao.deleteByRoleId(id);
		
		this.roleDao.delete(id);
	}

	@Override
	public List<Role> find() {
		return this.roleDao.find();
	}

	@Override
	public Role load(Integer id) {
		return id == null ? null : this.roleDao.load(id);
	}

	@Override
	public Map<String, Set<String>> findRolePowers(Integer roleId) {
		if (roleId == null) {
			return null;
		}
		
		List<RolePower> powersList = this.rolePowerDao.findByRoleId(roleId);
		if (powersList == null) {
			return null;
		}
				
		Map<String, Set<String>> powersMap = new HashMap<>();
		for (RolePower power : powersList) {
			Set<String> typePowers = powersMap.get(power.getPowerType());
			if (typePowers == null) {
				typePowers =  new HashSet<String>();
				powersMap.put(power.getPowerType(), typePowers);
			}
			
			typePowers.add(power.getPowerCode());
		}
		
		return powersMap;
	}

	@Override
	@Transactional
	public void saveRolePowers(Integer roleId, List<String> modulePowers,
			List<String> pagePowers, List<String> functionPowers) {
		// 先删除以前的权限，再增加新权限
		this.rolePowerDao.deleteByRoleId(roleId);
		
		Date now = new Date();
		
		if (modulePowers != null) {
			for (String code : modulePowers) {
				this.rolePowerDao.insert(buildRolePower(roleId, code, "module", now));
			}
		}
		
		if (pagePowers != null) {
			for (String code : pagePowers) {
				this.rolePowerDao.insert(buildRolePower(roleId, code, "page", now));
			}
		}
		
		if (functionPowers != null) {
			for (String code : functionPowers) {
				this.rolePowerDao.insert(buildRolePower(roleId, code, "function", now));
			}
		}
	}

	private RolePower buildRolePower(Integer roleId, String powerCode,
			String powerType, Date date) {
		RolePower rp = new RolePower();
		rp.setRoleId(roleId);
		rp.setPowerCode(powerCode);
		rp.setPowerType(powerType);
		rp.setCreatedTime(date);
		return rp;
	}



	
}

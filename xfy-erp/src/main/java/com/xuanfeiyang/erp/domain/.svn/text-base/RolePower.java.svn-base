package com.xuanfeiyang.erp.domain;

import java.util.Date;

/**
 * 标识一个角色的一个权限信息
 * @author Adam
 *
 */
public class RolePower {
	private Integer id;

	private Integer roleId;

	private String powerCode;

	private String powerType;

	private Date createdTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getPowerCode() {
		return powerCode;
	}

	public void setPowerCode(String powerCode) {
		this.powerCode = powerCode == null ? null : powerCode.trim();
	}

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType == null ? null : powerType.trim();
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((powerCode == null) ? 0 : powerCode.hashCode());
		result = prime * result
				+ ((powerType == null) ? 0 : powerType.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePower other = (RolePower) obj;
		if (powerCode == null) {
			if (other.powerCode != null)
				return false;
		} else if (!powerCode.equals(other.powerCode))
			return false;
		if (powerType == null) {
			if (other.powerType != null)
				return false;
		} else if (!powerType.equals(other.powerType))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}
}
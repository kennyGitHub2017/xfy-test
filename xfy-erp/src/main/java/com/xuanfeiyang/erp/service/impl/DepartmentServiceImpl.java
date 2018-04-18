package com.xuanfeiyang.erp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.DepartmentDao;
import com.xuanfeiyang.erp.domain.Department;
import com.xuanfeiyang.erp.service.DepartmentService;

/**
 * 部门管理
 * 
 * @author Adam
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Resource
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> find() {
		return this.departmentDao.find();
	}

	@Override
	public Department load(Integer id) {
		if (id == null) {
			return null;
		}
		
		return this.departmentDao.load(id);
	}

	@Override
	public void save(Department department) {
		if (department == null) {
			return;
		}
		department.setName(StringUtils.trimToEmpty(department.getName()));
		department.setCreatedTime(new Date());
		department.setLastUpdatedTime(department.getCreatedTime());
		
		this.departmentDao.save(department);
	}

	@Override
	public void update(Department department) {

		if (department == null) {
			return;
		}
		
		department.setName(StringUtils.trimToEmpty(department.getName()));
		department.setLastUpdatedTime(new Date());
		
		this.departmentDao.update(department);
		
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			return;
		}
		
		this.departmentDao.delete(id);
	}

	@Override
	public boolean exists(String depName) {
		return this.departmentDao.countByDepartment(depName)>0?true:false;
	}
	
}

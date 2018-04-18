package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.Department;

public interface DepartmentDao {
	
	// 查询
	List<Department> find();

	Department load(Integer id);
	
	public Integer countByDepartment(@Param("depName")String depName);

	void save(Department department);
	
	void update(Department department);

	void delete(Integer id);
	
}

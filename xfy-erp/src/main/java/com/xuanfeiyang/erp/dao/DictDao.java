package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.Dict;

public interface DictDao {
	
	/**
	 * 根据字典类型查找
	 * @param type
	 * @return
	 */
	public List<Dict> findByType(Integer type);
	
	/**
	 * 查询一笔
	 * @param type
	 * @param code
	 * @return
	 */
	public Dict load(@Param("type") Integer type, @Param("code") Integer code);
	
	/**
	 * 删除
	 * @param type
	 * @param code
	 * @return
	 */
	public int delete(@Param("type") Integer type, @Param("code") Integer code);
	
	public void save(Dict dict);
	
	public void update(Dict dict);
	
	List<Dict> findByTable(@Param("tableName")String tableName);
}

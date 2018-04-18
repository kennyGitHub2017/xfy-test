package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.domain.GoodsCategory;

public interface GoodsCategoryService {
	public void add(GoodsCategory category);

	public void modify(GoodsCategory account);

	public void remove (Integer id);

	public GoodsCategory get(Integer id);
	
	public boolean categoryInuse(Integer id);
	
	/**
	 * 通过父类ID查询，只查询直接子类
	 * @param parentId
	 * @return
	 */
	public List<GoodsCategory> findByParentId(Integer parentId);
	
	/**
	 * 通过类目名称查询
	 * @param name
	 * @return
	 */
	public GoodsCategory loadByName(String name);
	
	/**
	 * 通过类目编码查询
	 * @param code
	 * @return
	 */
	public GoodsCategory loadByCode(String code);
	
	/**
	 * 根据某个parentId查询所有子分类，具体子分类中带有改类目的子孙节点信息
	 * @param parentId
	 * @return
	 */
	public List<GoodsCategory> findWithChildren(Integer parentId);
	
	/**
	 * 根据ID加载类目对象，带有父类信息
	 * @param id
	 * @return
	 */
	public GoodsCategory loadWithParents(Integer id);
}

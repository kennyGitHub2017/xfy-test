package com.xuanfeiyang.erp.dao;

import java.util.List;

import com.xuanfeiyang.erp.domain.GoodsCategory;

public interface GoodsCategoryDao {
	public Integer insert(GoodsCategory category);
	public void update(GoodsCategory category);
	public void delete(Integer id);
	public GoodsCategory get(Integer id);
	
	/**
	 * 通过父类ID查询，只查询直接子类
	 * @param parentId
	 * @return
	 */
	public List<GoodsCategory> findByParentId(Integer parentId);
	
	/**
	 * 根据类目名加载分类对象
	 * @param name
	 * @return
	 */
	public GoodsCategory loadByName(String name);
	
	/**
	 * 根据类目编码加载分类对象
	 * @param code
	 * @return
	 */
	public GoodsCategory loadByCode(String code);
	
	/**
	 * 通过parentId查询孩子列表，每个孩子节点包含自己的子孙节点
	 * @param parentId
	 * @return
	 */
	public List<GoodsCategory> findChildrenByParentId(Integer parentId);
	
	/**
	 * 根据Id加载当前类目节点，并带有祖先节点信息
	 * 
	 * @return
	 */
	public GoodsCategory loadWithParents(Integer id);
	
	/**
	 * 根据id查询对象
	 * @author kenny
	 * @param id
	 * @return
	 */
	public GoodsCategory findById(Integer id);
}

package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.GoodsPackingMaterial;

/**
 * 包装材料
 * @author kenny
 *
 */
public interface GoodsPackingMaterialDao {
	
	public List<GoodsPackingMaterial> find();
	
	public int delete(Integer id);
	
	public int save(@Param("param")GoodsPackingMaterial packingMaterial);
	
	public int update(@Param("param")GoodsPackingMaterial packingMaterial);
	
	public GoodsPackingMaterial getById(Integer id);
	
	/**
	 * 检查型号是否存在
	 * @param model 型号
	 * @param id id
	 * @return
	 */
	public GoodsPackingMaterial getByModel(@Param("model")String model,@Param("id")Integer id);
	

}

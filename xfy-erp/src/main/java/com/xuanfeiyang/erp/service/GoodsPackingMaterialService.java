package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.domain.GoodsPackingMaterial;

public interface GoodsPackingMaterialService {
	
	
	public List<GoodsPackingMaterial> find();
	
	public boolean delete(Integer id);
	
	public boolean add(GoodsPackingMaterial packingMaterial);
	
	public boolean modify(GoodsPackingMaterial packingMaterial);
	
	public GoodsPackingMaterial findById(Integer id);
	
	public GoodsPackingMaterial findByModel(String model,Integer id);

}

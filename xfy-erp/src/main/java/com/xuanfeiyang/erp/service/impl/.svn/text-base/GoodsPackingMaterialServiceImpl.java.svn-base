package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.GoodsPackingMaterialDao;
import com.xuanfeiyang.erp.domain.GoodsPackingMaterial;
import com.xuanfeiyang.erp.service.GoodsPackingMaterialService;

@Service
public class GoodsPackingMaterialServiceImpl implements GoodsPackingMaterialService{
	@Resource
	private GoodsPackingMaterialDao goodsPackingMaterialService;

	@Override
	public List<GoodsPackingMaterial> find() {
		
		return goodsPackingMaterialService.find();
	}

	@Override
	public boolean delete(Integer id) {
		int result =  goodsPackingMaterialService.delete(id);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean add(GoodsPackingMaterial packingMaterial) {
		int result = goodsPackingMaterialService.save(packingMaterial);
		if(result > 0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean modify(GoodsPackingMaterial packingMaterial) {
		int result = goodsPackingMaterialService.update(packingMaterial);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public GoodsPackingMaterial findById(Integer id) {
		return goodsPackingMaterialService.getById(id);
		 
	}

	@Override
	public GoodsPackingMaterial findByModel(String model,Integer id) {
		
		return goodsPackingMaterialService.getByModel(model,id);
	}
	
	

}

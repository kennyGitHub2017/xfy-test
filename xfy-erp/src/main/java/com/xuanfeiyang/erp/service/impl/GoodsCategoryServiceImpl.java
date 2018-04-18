package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.GoodsCategoryDao;
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.domain.GoodsCategory;
import com.xuanfeiyang.erp.service.GoodsCategoryService;
@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
	@Autowired
	private GoodsCategoryDao goodsCategoryDao;
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public void add(GoodsCategory category) {
		if (category == null || category.getName() == null || category.getCode() == null) {
			return;
		}
		
		// 编码不能重复
		GoodsCategory tmp = this.loadByCode(category.getCode());
		if (tmp != null) {
			return;
		}
		
		// 名称不能重复
		tmp = this.loadByName(category.getName());
		if (tmp != null) {
			return;
		}
		
		// 默认父ID为顶级
		if (category.getParentId() == null) {
			category.setParentId(0);
		}
		
		goodsCategoryDao.insert(category);
	}

	@Override
	public void modify(GoodsCategory category) {
		if (category == null || category.getId() == null) {
			return;
		}

		// 编码不能重复
		if (category.getCode() != null) {
			GoodsCategory tmp = this.loadByCode(category.getCode());
			if (tmp != null && ! tmp.getId().equals(category.getId())) {
				return;
			}
		}

		// 名称不能重复
		if (category.getName() != null) {
			GoodsCategory tmp = this.loadByName(category.getName());
			if (tmp != null && ! tmp.getId().equals(category.getId())) {
				return;
			}
		}
		
		// 父ID为空或者父ID与本身ID相同,则设置父ID为顶级
		if (category.getParentId() == null || 
				category.getParentId().equals(category.getId())) {
			category.setParentId(0);
		} 
		
		goodsCategoryDao.update(category);
	}

	@Override
	public void remove(Integer id) {
		goodsCategoryDao.delete(id);
	}

	@Override
	public GoodsCategory get(Integer id) {
		return goodsCategoryDao.get(id);
	}

	public void setGoodsCategoryDao(GoodsCategoryDao goodsCategoryDao) {
		this.goodsCategoryDao = goodsCategoryDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	@Override
	public boolean categoryInuse(Integer categoryId) {
		return goodsDao.countGoodsByCategory(categoryId)>0?true:false;
	}

	@Override
	public List<GoodsCategory> findByParentId(Integer parentId) {
		return this.goodsCategoryDao.findByParentId(parentId);
	}

	@Override
	public GoodsCategory loadByName(String name) {
		return name == null ? null : this.goodsCategoryDao.loadByName(name);
	}

	@Override
	public GoodsCategory loadByCode(String code) {
		return code == null ? null : this.goodsCategoryDao.loadByCode(code);
	}

	@Override
	public List<GoodsCategory> findWithChildren(Integer parentId) {
		return parentId == null ? null : this.goodsCategoryDao.findChildrenByParentId(parentId);
	}

	@Override
	public GoodsCategory loadWithParents(Integer id) {
		return id== null ? null : this.goodsCategoryDao.loadWithParents(id);
	}
	
	
}

package com.xuanfeiyang.erp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsCostLog;
import com.xuanfeiyang.erp.domain.GoodsDesc;
import com.xuanfeiyang.erp.param.GoodsParam;

public interface GoodsService {
	
	public boolean insert(Goods goods);
	
	public Page<Goods> findPage(PageRequest pageRequest,GoodsParam goodsParam);
	
	public Goods findById(Integer id);

	public Goods findByIdWithSupplierName(Integer id);
	
	public Goods findBySku(String sku);
	
	public boolean mofify(Goods goods);
	
	public boolean delete(Integer id);
	
	/**
	 * 导入商品描述等信息
	 * @param file 上传的文件信息
	 */
	public void importGoodsDesc(MultipartFile file) throws FileImportException;

	/**
	 * 根据商品 SKU 查询商品的描述信息
	 * 
	 * @param goodsSku
	 * @return
	 */
	public List<GoodsDesc> findDescriptionsBySku(String goodsSku);
	
	/**
	 * 导入商品资料信息 importGoods
	 * @param request
	 * @param goods
	 * @throws Exception
	 */
	public void importGoods(MultipartFile request,Goods goods) throws FileImportException;
	
	/**
	 * 更新商品的开放标识 
	 * @param id 商品ID
	 * @param openFlag 开放标识
	 */
	public void updateOpenFlag(Integer id, String openFlag);
	
	/****
	 * 条件查询
	 * @param goodsParam
	 * @return List
	 */
	List<Goods> findByParam(GoodsParam goodsParam);
	
	public Integer getCountByParam(GoodsParam goodsParam);
	
	/**
	 * 将不为空的属性更新到相应的字段上
	 * 
	 * @param goods
	 */
	public void update(Goods goods);
	
	public List<Goods> findListById(List<Integer> ids);
	
	public Page<Goods> getListingInfo(PageRequest pageRequest,String goodsSku);
	
	public List<Goods> getListingCount(List<String> goodsSku);
	public void modifyGoodsCost(Integer id, String costNew, 
			Integer updateType,String operUser, String sku,String customCostNew,Integer customUpType);
	public List<GoodsCostLog> findLogBySku(String sku);
	
}

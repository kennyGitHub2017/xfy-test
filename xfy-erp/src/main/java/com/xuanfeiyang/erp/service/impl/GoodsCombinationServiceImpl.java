package com.xuanfeiyang.erp.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.GoodsCombinationDao;
import com.xuanfeiyang.erp.dao.GoodsCombinationItemDao;
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.domain.GoodsCombination;
import com.xuanfeiyang.erp.domain.GoodsCombinationItem;
import com.xuanfeiyang.erp.service.GoodsCombinationService;

@Service
public class GoodsCombinationServiceImpl implements GoodsCombinationService {

	@Resource private GoodsCombinationDao goodsCombinationDao;
	@Resource private GoodsCombinationItemDao goodsCombinationItemDao;
	@Resource private GoodsDao goodsDao;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void save(GoodsCombination gc) {
		
		checkNotNull(gc, "参数为空");
		checkArgument(StringUtils.isBlank(gc.getCombSku()), "产品编码为空");
		
		Date now = new Date();
		gc.setCombSku(StringUtils.trim(gc.getCombSku()));
		gc.setCreatedTime(now);
		gc.setLastUpdatedTime(now);
		
		this.goodsCombinationDao.insert(gc);
		
		if (gc.getItems() != null && gc.getItems().size() > 0) {
			for (GoodsCombinationItem d : gc.getItems()) {
				d.setCombId(gc.getId());
				d.setCreatedTime(now);
				d.setLastUpdatedTime(now);
				this.goodsCombinationItemDao.insert(d);
			}
		}
	}

	@Override
	public Page<GoodsCombination> findPageWithItems(PageRequest pageRequest,
			String keywords) {
		
		Page<GoodsCombination> page = this.goodsCombinationDao.findPage(pageRequest, keywords);
		List<GoodsCombination> gcs = page.getContent();
		
		if (gcs != null && gcs.size() > 0) {
			for (GoodsCombination gc : gcs) {
				gc.setItems(this.goodsCombinationItemDao.findByCombId(gc.getId()));
			}
		}
		
		return page;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(Integer id) {
		checkNotNull(id, "id不能为空");
		
		this.goodsCombinationItemDao.deleteByCombId(id);
		this.goodsCombinationDao.delete(id);
	}

	@Override
	public GoodsCombination loadWithItems(Integer id) {
		GoodsCombination gc = this.goodsCombinationDao.load(id);
		if (gc != null) {
			gc.setItems(this.goodsCombinationItemDao.findByCombId(id));
		}
		return gc;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void save(Integer id, String combSku, String name, String note, String itemsStr) {
		GoodsCombination gc = this.accessbleData(id, combSku, name, note, itemsStr);
		
		GoodsCombination tmp = this.goodsCombinationDao.loadBySku(gc.getCombSku());
		
		Date now = new Date();
		gc.setCreatedTime(now);
		gc.setLastUpdatedTime(now);
		
		// 新增
		if (gc.getId() == null) {
			checkArgument(tmp == null, "已存在相同的产品编码：%s", combSku);

			this.goodsCombinationDao.insert(gc);
			for (GoodsCombinationItem item : gc.getItems()) {
				item.setCombId(gc.getId());
				item.setCreatedTime(now);
				item.setLastUpdatedTime(now);
				this.goodsCombinationItemDao.insert(item);
			}
		}
		// 修改
		else {
			checkArgument(tmp == null || tmp.getId().equals(id), 
					"已存在相同的产品编码：%s", combSku);
			
			this.goodsCombinationDao.update(gc);
			this.goodsCombinationItemDao.deleteByCombId(id);
			for (GoodsCombinationItem item : gc.getItems()) {
				item.setCombId(gc.getId());
				item.setCreatedTime(now);
				item.setLastUpdatedTime(now);
				this.goodsCombinationItemDao.insert(item);
			}
		}
	}

	private GoodsCombination accessbleData(Integer id, String combSku,
			String name, String note, String itemsStr) {
		checkArgument(StringUtils.isNotBlank(combSku), "缺少组合产品编码");
		checkArgument(StringUtils.isNotBlank(combSku), "缺少组合产品明细");
		
		combSku = StringUtils.trim(combSku);
		itemsStr = StringUtils.trim(itemsStr);
		
		String[] itemStrArr = itemsStr.split(",");
		checkArgument(itemStrArr.length > 0, "缺少组合产品明细");
		
		List<GoodsCombinationItem> items = new ArrayList<>();
		for (String itemStr : itemStrArr) {
			itemStr = StringUtils.trimToEmpty(itemStr);
			if (itemStr.isEmpty()) {
				continue;
			}
			
			String[] tmpArr = itemStr.split("\\*");
			checkArgument(tmpArr.length == 2 &&
					StringUtils.isNotBlank(tmpArr[0]) &&
					StringUtils.isNumeric(tmpArr[1]), "%s 输入不正确", itemStr);
			
			String oldSku = StringUtils.trim(tmpArr[0]);
			String newSku = this.goodsDao.findGoodsSkuByOldSku(oldSku);
			checkArgument(newSku != null, "SKU: %s 不存在", oldSku);

			int quantity = NumberUtils.toInt(StringUtils.trim(tmpArr[1]));
			
			GoodsCombinationItem item = new GoodsCombinationItem();
			item.setOldSku(oldSku);
			item.setNewSku(newSku);
			item.setQuantity(quantity);
			
			items.add(item);
		}
		
		GoodsCombination gc = new GoodsCombination();
		gc.setId(id);
		gc.setCombSku(combSku);
		gc.setName(name);
		gc.setNote(note);
		gc.setItems(items);
		
		
		return gc;
	}

	
}

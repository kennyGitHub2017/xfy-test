package com.xuanfeiyang.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;

import com.xuanfeiyang.erp.domain.GoodsCombination;
import com.xuanfeiyang.erp.domain.GoodsCombinationItem;
import com.xuanfeiyang.erp.service.GoodsCombinationService;
import com.xuanfeiyang.erp.util.ExcelParser;
import com.xuanfeiyang.test.BaseTestCase;

public class GoodsCombinationServiceImplTest extends BaseTestCase {
	
	@Resource 
	private GoodsCombinationService goodsCompinationService;
	
	@Test
	public void saveFile() {
		List<String[]> lines = ExcelParser.parseToString("F:\\goods_comb3.xls");
		
//		for (String[] line : lines) {
//			System.out.println(Arrays.toString(line));
//		}
		
		for (int i = 1, size = lines.size(); i < size; i++) {
			String[] line = lines.get(i);
			System.out.println("============================= " + Arrays.toString(line));
			
			GoodsCombination gc = new GoodsCombination();
			gc.setCombSku(line[1]);
			
			String[] detArray = line[2].split(",");
			Map<String, Integer> skuMap = new HashMap<>();
			for (String itemStr : detArray) {
				itemStr.replaceAll("(^\\s+)|(\\s+$)", "");
				String[] itemArray = itemStr.split("\\*");
				String sku = null;
				int quantity = 0;
				
				if (itemArray.length == 2) {
					sku = itemArray[0];
					quantity = Integer.parseInt(itemArray[1].trim());
				} else {
					sku = itemArray[0];
					quantity = 1;
				}
				
				if (skuMap.containsKey(sku)) {
					skuMap.put(sku, skuMap.get(sku) + quantity);
				} else {
					skuMap.put(sku, quantity);
				}
				
			}

			List<GoodsCombinationItem> items = new ArrayList<>();
			for (Map.Entry<String, Integer> sku : skuMap.entrySet()) {
				GoodsCombinationItem item = new GoodsCombinationItem();
				item.setCombId(gc.getId());
				item.setOldSku(sku.getKey());
				item.setQuantity(sku.getValue());
				
				items.add(item);
			}
			
			gc.setItems(items);
			
			try {
				this.goodsCompinationService.save(gc);
			} catch (DuplicateKeyException e) {
				logger.error("exists", e);
				continue;
			}
		}
	}
	
}

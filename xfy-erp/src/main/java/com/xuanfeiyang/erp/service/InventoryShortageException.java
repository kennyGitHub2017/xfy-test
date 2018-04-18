package com.xuanfeiyang.erp.service;

import com.xuanfeiyang.erp.domain.GoodsInventory;

/**
 * 库存不足异常
 * 
 * @author Adam
 *
 */
public class InventoryShortageException extends RuntimeException {
	
	private static final long serialVersionUID = -2059212682755129635L;

	/**
	 * 实际库存信息
	 */
	private GoodsInventory goodsInventory;
	
	/**
	 * 扣减库存数
	 */
	private int decreaseCount;
	
//	public InventoryShortageException() {
//		super();
//	}

	/**
	 * 
	 * @param goodsInventory 实际库存信息
	 * @param decreaseCount 扣减库存数
	 */
	public InventoryShortageException(GoodsInventory goodsInventory, int decreaseCount) {
		super();
		this.goodsInventory = goodsInventory;
		this.decreaseCount = decreaseCount;
	}
	
	/**
	 * 
	 * @param goodsSku SKU
	 * @param availableCount 实际库存
	 * @param decreaseCount 扣减库存数
	 */
	public InventoryShortageException(String goodsSku, int availableCount, int decreaseCount) {
		super();
		GoodsInventory tmp = new GoodsInventory();
		tmp.setGoodsSku(goodsSku);
		tmp.setCount(availableCount);
		this.goodsInventory = tmp;
		
		this.decreaseCount = decreaseCount;
	}

	public GoodsInventory getGoodsInventory() {
		return goodsInventory;
	}

	public void setGoodsInventory(GoodsInventory goodsInventory) {
		this.goodsInventory = goodsInventory;
	}

	public int getAmount() {
		return decreaseCount;
	}

	public void setAmount(int amount) {
		this.decreaseCount = amount;
	}

	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("库存不足 (")
		.append("sku: ")
		.append(this.goodsInventory.getGoodsSku())
		.append(", storeId: ")
		.append(this.goodsInventory.getStoreId())
		.append(", storeShelfId: ")
		.append(this.goodsInventory.getStoreShelfId())
		.append("实际库存：").append(this.goodsInventory.getCount())
		.append("需要扣减: ").append(this.decreaseCount)
		.append(")");
		return sb.toString();
	}
	
	
}

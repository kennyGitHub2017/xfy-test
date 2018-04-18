package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.domain.Shipping;
import com.xuanfeiyang.erp.domain.ShippingAddressConfig;

public interface ShippingService {
	
	public List<Shipping> find();
	
	/**
	 * 添加发货方式
	 * @param shipping
	 * @return
	 */
	public boolean add(Shipping shipping);
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Shipping findById(Integer id);
	
	/**
	 * 根据id修改
	 * @param shipping
	 * @return
	 */
	public boolean modifyById(Shipping shipping);
	
	/**
	 * 根据ID加载发货方式对应的地址信息
	 * 
	 * @param shippingId
	 * @return
	 */
	public ShippingAddressConfig loadAddressConfig(Integer shippingId);
	
	/**
	 * 保存 地址信息
	 * @param address
	 */
	public void saveAddressConfig(ShippingAddressConfig address);

}

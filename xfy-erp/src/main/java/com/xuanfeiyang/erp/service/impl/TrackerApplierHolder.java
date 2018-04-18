package com.xuanfeiyang.erp.service.impl;

import com.xuanfeiyang.erp.service.express.ApacTrackerApplier;
import com.xuanfeiyang.erp.service.express.BpostTrackerApplier;
import com.xuanfeiyang.erp.service.express.DongGuanOrderDelete;
import com.xuanfeiyang.erp.service.express.DongGuanTrackerApplier;
import com.xuanfeiyang.erp.service.express.EubTrackerApplier;
import com.xuanfeiyang.erp.service.express.HuLianYiTrackerApplier;
import com.xuanfeiyang.erp.service.express.HuaLinTrackerApplier;
import com.xuanfeiyang.erp.service.express.JunHuiTrackerApplier;
import com.xuanfeiyang.erp.service.express.SfRussiaTrackerApplier;
import com.xuanfeiyang.erp.service.express.SfTrackerApplier;
import com.xuanfeiyang.erp.service.express.TrackerApplier;
import com.xuanfeiyang.erp.service.express.WinitTrackerApplier;
import com.xuanfeiyang.erp.service.express.YanwenTrackerApplier;

/**
 * 定义所有申请跟踪号的实现
 * 
 * @author Adam
 *
 */
public enum TrackerApplierHolder {
	
	// 自己产生
	Manual(null),
	
	// 华霖物流
	HuaLin(new HuaLinTrackerApplier()),
	
	// 燕文
	YanWen(new YanwenTrackerApplier()),
	
	//万邑通物流
	Winit(new WinitTrackerApplier()),
	
	// bPost
	bPost(new BpostTrackerApplier()),

	// 顺丰物流
	Sf(new SfTrackerApplier()),
	
	// 顺丰俄罗斯
	SfRussia(new SfRussiaTrackerApplier()),
	
	// 青岛EUB
	Eub(new EubTrackerApplier("1")),
	//长沙EUB
	Eub2(new EubTrackerApplier("2")),
	
	//东莞EUB跟踪号申请
	DongGuanEub(new DongGuanTrackerApplier("1")),
	
	//东莞EUB跟踪号申请
	DongGuanEub2(new DongGuanTrackerApplier("2")),
	
	//东莞EUB订单删除
	DongGuanEubDelete(new DongGuanOrderDelete()),
	// eBay亚太物流平台
	Apac(new ApacTrackerApplier()),
	// 均晖物流 - 德国邮政平邮
	JunHuiDEPY(new JunHuiTrackerApplier("DEPY")),
	// 均晖物流 - 德国邮政小包挂号
	JunHuiDERG(new JunHuiTrackerApplier("DERG")),
	// 均晖物流 - 荷兰平邮
	JunHuiNLPY(new JunHuiTrackerApplier("NLPY")),
	// 均晖物流 - 荷兰小包
	JunHuiNLRG(new JunHuiTrackerApplier("NLRG")),
	// 互联易
	HuLianYi(new HuLianYiTrackerApplier())
	;
	
	

	private TrackerApplier applier;
	
	TrackerApplierHolder(TrackerApplier applier) {
		this.applier = applier;
	}

	public TrackerApplier getApplier() {
		return applier;
	}

	public void setApplier(TrackerApplier applier) {
		this.applier = applier;
	}
	
	
}

package com.xuanfeiyang.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;

import com.xuanfeiyang.erp.service.GeneratePackageService;
import com.xuanfeiyang.erp.service.OrderPackageService;
import com.xuanfeiyang.erp.service.OrderService;
import com.xuanfeiyang.test.BaseTestCase;

public class OrderServiceImplTest  extends BaseTestCase{
	
	@Resource
	private OrderService orderService;
	@Resource
	private  GeneratePackageService generatePackageService;
	@Resource
	private OrderPackageService orderPackageService;
	
	private  Integer  ids[] = new Integer[]{10759605,10759606}; 
	
	private Thread thread[] = new Thread[10];
	
	/**
	 * 测试订单锁库存并发情况
	*/
	public void  lockInventory(){
		for(int i=0;i<10;i++){
			thread [i] = new Thread(
					new Runnable() {
						@Override
						public void run() {
							orderService.lockOrderInventory(ids);
						}
					}
			);
			
			try {
				thread[i].start();
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	 
	
	@Test
	public void test2()  throws Exception{
		
		ExecutorService threadpool =  Executors.newCachedThreadPool();
			
		threadpool.submit(new Runnable() {
			@Override
			public void run() {
						try {
							orderPackageService.generatePackage(10759605,"abc");
						} catch (Exception e) {
							e.printStackTrace();
						}
				};
		});
		
		threadpool.submit(new Runnable() {
			@Override
			public void run() {
				orderService.generatePackage3();
			}
		});
		
		threadpool.awaitTermination(10, TimeUnit.SECONDS);
	}	
}

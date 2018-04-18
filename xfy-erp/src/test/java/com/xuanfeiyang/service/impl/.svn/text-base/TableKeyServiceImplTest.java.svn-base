package com.xuanfeiyang.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.annotation.Resource;

import org.junit.Test;

import com.xuanfeiyang.erp.service.TableKeyService;
import com.xuanfeiyang.test.BaseTestCase;

public class TableKeyServiceImplTest extends BaseTestCase{
	@Resource
	private  TableKeyService tableKeyService;
	
	static DateFormat dft = new SimpleDateFormat("yyyyMMdd");
	
	static String dateStr,fmt;
	
	static{
		dateStr = dft.format(Calendar.getInstance().getTime());
		fmt =String.format("LS-%s-",dateStr)+"%d";
	}
	
	static Random rdm = new Random();
	private Thread thread[] = new Thread[12];
	
	public void test1(){
		String tableName = "goods_transfer_order";
		System.out.println(tableKeyService.nextSerialNumber(tableName, fmt));	
	}
	
	@Test
	public void testThreadConrrent() {
		
		for(int i=0;i<4;i++){
			thread [i] = new Thread(
					new Runnable() {
						@Override
						public void run() {
							while (true){
								System.out.println(Thread.currentThread().getName()+"\ti_orders:" + tableKeyService.nextSerialNumber("i_orders", fmt));
								try {
									Thread.sleep(rdm.nextInt(500));
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}
			);
			thread [i].start();
		}
		
		
		for(int i=4;i<8;i++){
			thread [i] = new Thread(
					new Runnable() {
						@Override
						public void run() {
							while (true){
								System.out.println(Thread.currentThread().getName()+"\to_orders:" + tableKeyService.nextSerialNumber("o_orders", fmt));
								try {
									Thread.sleep(rdm.nextInt(500));
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}
			);
			thread [i].start();
		}
		
		
		for(int i=8;i<12;i++){
			thread [i] = new Thread(
					new Runnable() {
						@Override
						public void run() {
							while (true){
								System.out.println(Thread.currentThread().getName()+"\tgoods_transfer_order:" + tableKeyService.nextSerialNumber("goods_transfer_order", fmt));
								try {
									Thread.sleep(rdm.nextInt(500));
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}
			);
			thread [i].start();
		}
		
		
		try {
			for(int i=0;i<12;i++){
				thread[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

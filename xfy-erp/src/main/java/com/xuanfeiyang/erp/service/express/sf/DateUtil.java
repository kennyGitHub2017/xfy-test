package com.xuanfeiyang.erp.service.express.sf;

import java.text.SimpleDateFormat;

public final class DateUtil {

	/**
	 * date转换问String
	 * 
	 * @param obj
	 * @param regula
	 * @return
	 */
	public static String dateToString(Object obj, String regula) {
		SimpleDateFormat sdf = new SimpleDateFormat(regula);
		String str = sdf.format(obj);
		return str;
	}

}
package com.xuanfeiyang.erp.service.express.sf;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(StringUtil.class);
	private StringUtil() {

	}

	public static boolean isBlank(String str) {
		if (str == null || str.length() == 0)
			return true;
		int strLen = str.length();
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}

	public static boolean isNotBlank(String str) {
		if (str == null || str.length() == 0)
			return false;
		int strLen = str.length();
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return true;

		return false;
	}

	public static boolean isEmpty(Object obj) {
		boolean result = true;
		try {
			if (obj == null) {
				return true;
			}
			Class<? extends Object> cls = obj.getClass();

			if (cls.isArray()) {
				Object[] objs = (Object[]) obj;
				if (objs.length > 0) {
					result = false;
				}
			}
			if (obj instanceof String && !obj.toString().trim().equals("")) {
				result = false;
			}
			if (obj instanceof Collection) {
				Collection<?> coll = (Collection<?>) obj;
				if (!coll.isEmpty()) {
					result = false;
				}
			}
			if (obj instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) obj;
				if (!map.isEmpty()) {
					result = false;
				}
			}
			if (obj instanceof Number) {
				Number num = (Number) obj;
				if (!num.toString().trim().equals("")) {
					result = false;
				}
			}
			if (obj instanceof Date) {
				Date date = (Date) obj;
				if (null != date) {
					result = false;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	public static String valueOf(Object o) {
		return o != null ? o.toString() : "";
	}

}

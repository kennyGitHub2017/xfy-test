package com.xuanfeiyang.erp.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {
	/**
	 * 读取pojo属性
	 * @param owner
	 * @param fieldname
	 * @param classType
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object getValueMethod(Object owner, String fieldname,
			Class<?> classType) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class<?> ownerClass = owner.getClass();
		Method method = null;
		method = ownerClass.getMethod(toGetter(fieldname, classType));

		Object object = null;
		object = method.invoke(owner);

		return object;
	}
	
	/**
	 * 设置pojo属性
	 * @param owner
	 * @param fieldname
	 * @param classType
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void setValueMethod(Object owner, String fieldname,
			Class<?> classType) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class<?> ownerClass = owner.getClass();
		Method method = null;
		method = ownerClass.getMethod(toSetter(fieldname, classType));

		method.invoke(owner);
	}
	
	public static String toGetter(String fieldname,Class<?> classType) {

		if (fieldname == null || fieldname.length() == 0) {
			return null;
		}
		/* Common situation */
		String type = classType.getName();
		String toUpper = fieldname.substring(0, 1).toUpperCase().concat(fieldname.substring(1));
		if (type.equals("java.lang.Boolean") || type.equals("boolean")){
			return "is".concat(toUpper); 
		}else{
			return  "get".concat(toUpper);
		}
	}
	
	public static String toSetter(String fieldname,Class<?> classType) {

		if (fieldname == null || fieldname.length() == 0) {
			return null;
		}
		/* Common situation */
		String toUpper = fieldname.substring(0, 1).toUpperCase().concat(fieldname.substring(1));
			return  "set".concat(toUpper);
	}
}

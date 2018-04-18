package com.xuanfeiyang.erp.service.express.sf;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class XMLUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(XMLUtil.class);
	/**  */
	private static final String XML_ROOT = "Request";

	/**  */
	private static final String HEAD_ROOT = "Head";

	private static final String BODY_ROOT = "Body";

	private static final String ORDER_ROOT = "Order";

	private static final String CARGO_ROOT = "Cargo";

	/**  */
	private static Map<String, String> elementMap = null;

	static {
		elementMap = new HashMap<String, String>();
		// 订单
		elementMap.put(OrderDTO.class.getName(), ORDER_ROOT);
		elementMap.put(DeclareinvoiceDTO.class.getName(), CARGO_ROOT);

	}

	/**
	 * 利用指定对象生成xml
	 * 
	 * @param obj
	 *            DataModel对象
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String modelToXml(SenderModel obj) {
		Document document = DocumentHelper.createDocument();
		try {
			document.setXMLEncoding("UTF-8");
			Element root = document.addElement(XML_ROOT);
			root.addAttribute("service", obj.getServiceName());
			root.addAttribute("lang", "zh-CN");
			Element header = root.addElement(HEAD_ROOT);
			String headText = obj.getHeadText();
			header.addText(headText);
			Element content = root.addElement(BODY_ROOT);

			Map<Object, List> datas = obj.getDatas();
			if (null != datas && datas.size() > 0) {
				for (Map.Entry<Object, List> entry : datas.entrySet()) {
					Object o = entry.getKey();
					if (null != o) {
						String className = o.getClass().getName();
						String nodeName = elementMap.get(className);
						Element el = content.addElement(nodeName);
						Field[] fieds = o.getClass().getDeclaredFields();
						for (Field f : fieds) {
							Object fieldValue = readFieldValue(o, f.getName());
							if (fieldValue == null) {
								continue;
							}
							String name = "";
							Column column = f.getAnnotation(Column.class);
							if (null != column
									&& StringUtil.isNotBlank(column.Mapper())) {
								name = column.Mapper();
							}
							if (StringUtil.isNotBlank(name)) {
								el.addAttribute(name,
										convertToStr(PropertyUtils.getProperty(
												o, f.getName())));
							}
						}
						List lstDatas = entry.getValue();
						if (null != lstDatas && lstDatas.size() > 0) {
							for (Object ob : lstDatas) {
								String className1 = ob.getClass().getName();
								String nodeName1 = elementMap.get(className1);
								Element e2 = el.addElement(nodeName1);
								Field[] fieds1 = ob.getClass()
										.getDeclaredFields();
								for (Field f : fieds1) {
									Object fieldValue = readFieldValue(ob, f.getName());
									if (fieldValue == null) {
										continue;
									}

									String name = "";
									Column column = f
											.getAnnotation(Column.class);
									if (null != column
											&& StringUtil.isNotBlank(column
													.Mapper())) {
										name = column.Mapper();
									}
									if (StringUtil.isNotBlank(name)) {
										e2.addAttribute(name,
												convertToStr(PropertyUtils
														.getProperty(ob,
																f.getName())));
									}
								}
							}
						}

					}
				}

			}
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage());
		}
		return document.asXML();
	}

	public static String convertToStr(Object obj) {
		String str = "";
		if (!StringUtil.isEmpty(obj)) {
			if (obj.getClass() == Timestamp.class) {
				str = DateUtil.dateToString(obj, "yyyy-MM-dd HH:mm:ss");
			} else if (obj.getClass() == java.sql.Date.class) {
				str = DateUtil.dateToString(obj, "yyyy-MM-dd HH:mm:ss");
			} else if (obj.getClass() == Date.class) {
				str = DateUtil.dateToString(obj, "yyyy-MM-dd HH:mm:ss");
			} else
				str = obj.toString().trim();
		}
		return str;
	}
	
	@SuppressWarnings("rawtypes")
	private static Object readFieldValue(Object target, String fieldName) {
		if (target == null) {
			return null;
		}
		
		if (target instanceof Map) {
			return ((Map) target).get(fieldName);
		}
		
		try {
			return FieldUtils.readDeclaredField(target, fieldName, true);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

}

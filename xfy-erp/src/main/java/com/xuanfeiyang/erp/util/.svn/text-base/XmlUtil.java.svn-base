package com.xuanfeiyang.erp.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XML解析工具类
 * @author Administrator
 *
 */
public class XmlUtil {
	private static Logger log = LoggerFactory.getLogger(XmlUtil.class);

	public static void readStringXml(String xml) {
		Map<String, String> mapReturn = XmlUtil.readStringXmlOut(xml);
		Set<Entry<String, String>> set = mapReturn.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			log.debug(entry.getKey() + "\t" + entry.getValue());
		}
	}

	/**
	 * @description 将xml字符串转换成map
	 * @param xml
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> readStringXmlOut(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			Iterator<Element> iter = rootElt.elements().iterator(); // 获取根节点下的所有子节点
			// 遍历所有节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String name = recordEle.getName();
				log.debug("node name:" + name + "\t NodeValue:"
						+ recordEle.getTextTrim());
				if (!map.containsKey(name)) {
					map.put(name, recordEle.getTextTrim());
				}
			}
		} catch (DocumentException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return map;
	}
}
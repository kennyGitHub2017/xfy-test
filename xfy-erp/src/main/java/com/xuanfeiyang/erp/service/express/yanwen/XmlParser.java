package com.xuanfeiyang.erp.service.express.yanwen;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * XML解析对象
 * 
 * @author Administrator
 *
 */
public class XmlParser {
	/**
	 * 把XML字符串解析成对象
	 * 
	 * @param xml
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public static <T> T parse(String xml, Class<T> c) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(c);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		@SuppressWarnings("unchecked")
		T result = (T) jaxbUnmarshaller.unmarshal(new StringReader(xml));
		return result;
	}

	/**
	 * 把对象格式为XML字符串
	 * 
	 * @param obj
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public static <T> String format(T obj, Class<T> c) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(c);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// output
		// pretty
		// printed
		StringWriter out = new StringWriter();
		jaxbMarshaller.marshal(obj, out);
		return out.toString();
	}

	/*
	public static void main(String[] args) throws Exception {
		System.out.println(format(ExpressType.mock(), ExpressType.class));
	}
	*/
}

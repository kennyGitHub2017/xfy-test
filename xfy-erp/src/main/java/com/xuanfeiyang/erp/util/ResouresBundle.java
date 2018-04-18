package com.xuanfeiyang.erp.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 资源文件操作类
 * @author bernard
 *
 */
public final class ResouresBundle {
	private  static Map<String,Properties> props = new ConcurrentHashMap<String,Properties>();
	private  static Map<String,String> namePathMap = new ConcurrentHashMap<String,String>();
	private ResouresBundle(){}
	
	/**
	 * 绑定资料文件
	 * @param fileName  资源文件名
	 * @param filePath    资源文件所在路径
	 * @throws Exception
	 */
	public static void bundle(String fileName,String filePath) throws Exception{
		if (!StringUtil.isNotBlank(filePath) || !StringUtil.isNotBlank(fileName)){
			throw new Exception("资源文件名不能为空");
		}
		InputStream is = null;
		try{
			if (props.containsKey(fileName)){
				return;
			}
			is = ResouresBundle.class.getResourceAsStream(filePath);
			URL url = ResouresBundle.class.getResource(filePath);
			Properties p = new Properties();
			p.load(is);
			props.put(fileName, p);
			namePathMap.put(fileName,url.getPath());
		}finally{
			is = null;
		}
	}
	
	/**
	 * 解绑资源文件
	 * @param fileName
	 */
	public static void  unbind(String fileName){
		if (props.containsKey(fileName)){
			props.remove(fileName);
			namePathMap.remove(fileName);
		}
	}
	
	/**
	 * 读取绑定资源文件中的value值
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getValue(String fileName,String key) throws Exception{
		if (!props.containsKey(fileName)){
			throw new Exception("指定的fileName未进行绑定,请先绑定");
		}
		return props.get(fileName).getProperty(key);
	}
	
	/**
	 * 以key/value写入绑定资源文件
	 * @param fileName 已绑定的资源文件名
	 * @param key 如果key存在则覆盖原有value,key不存在则新增key/value对
	 * @param value
	 */
	public static void setValue(String fileName,String key,String value) throws Exception{
		if (!props.containsKey(fileName)){
			return;
		}
		Properties p = props.get(fileName);
		p.put(key, value);
		String filePath = namePathMap.get(fileName);
		OutputStream os = new FileOutputStream(filePath);
		p.store(os,null);
	}
	
	/**
	 * 获取所有已绑定的资源文件名
	 * @return
	 */
	public static Iterator<String> getBindedFilesName(){
		return namePathMap.keySet().iterator();
	}
	
	/**
	 * 获取所有忆已绑定的资源文件名
	 * @return
	 */
	public static Iterator<String> getBindedFilesPath(){
		return namePathMap.values().iterator();
	}
}

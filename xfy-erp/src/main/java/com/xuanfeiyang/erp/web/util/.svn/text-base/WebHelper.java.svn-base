package com.xuanfeiyang.erp.web.util;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;

public class WebHelper {
	
	/**
	 * 根据 datatables组件 ajax分页请求组装 PageRequest 对象
	 * 
	 * @param start 开始行号
	 * @param pageSize 页大小
	 * @return
	 */
	public static PageRequest assemblePageRequest(Integer start, Integer pageSize) {
		start = (start == null ? 0 : start);
		pageSize = (pageSize == null ? 10 : pageSize);

		return new PageRequest(start / pageSize, pageSize);
	}
	
	/**
	 * 根据 datatables组件 ajax分页请求组装 PageRequest 对象
	 * 
	 * @param dtr DataTable 组件的请求参数对象
	 * @return
	 */
	public static PageRequest assemblePageRequest(DataTableRequest<?> dtr) {
		int start = dtr.getStart();
		int pageSize = dtr.getLength();
		if (pageSize == 0) {
			pageSize = 10;
		}

		return new PageRequest(start / pageSize, pageSize);
	}
	
	/**
	 * 根据 datatables组件 ajax分页请求组装 PageRequest 对象
	 * 
	 * @param dtr DataTable 组件的请求参数对象
	 * @return
	 */
	public static PageRequest assemblePageRequest(DataTableRequest<?> dtr, boolean countable) {
		int start = dtr.getStart();
		int pageSize = dtr.getLength();
		if (pageSize == 0) {
			pageSize = 10;
		}

		return new PageRequest(start / pageSize, pageSize, countable);
	}
	
	
	/**
	 * 为前端 datatables 组件 ajax请求组装分页数据
	 * 
	 * @param page
	 * @param draw 此为 DataTable 组件请求带来的标识
	 * @return
	 */
	public static Map<String, Object> assemblePageData(Page<? extends Object> page, Integer draw) {
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("draw", draw != null ? draw + 1 : 1);
		data.put("recordsTotal", page.getTotalElements());
		data.put("recordsFiltered", page.getTotalElements());
		data.put("data", page.getContent());
		
		return data;
	}
	
	/**
	 * 为前端 datatables 组件 ajax请求组装分页数据
	 * 
	 * @param page
	 * @param dtr DataTable 组件的请求参数对象
	 * @return
	 */
	public static Map<String, Object> assemblePageData(
			Page<? extends Object> page, DataTableRequest<?> dtr) {
		
		int draw = dtr.getDraw();
		
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("draw", draw + 1);
		data.put("recordsTotal", page.getTotalElements());
		data.put("recordsFiltered", page.getTotalElements());
		data.put("data", page.getContent());
		data.put("error", null);
		
		return data;
	}
	
	/**
	 * 为前端 datatables 组件 ajax请求组装分页数据
	 * 
	 * @param <T> 实体类类型
	 * 
	 * @param dtr DataTable 组件的请求参数对象
	 * @param page 分页数据对象
	 * @return 
	 * @return
	 */
	public static <T> DataTableResponse<T> assembleDataTableResponse(
			DataTableRequest<?> dtr, Page<T> page) {
		
		int draw = dtr.getDraw();
		
		DataTableResponse<T> res = new DataTableResponse<T>();
		res.setDraw(draw + 1);
		res.setRecordsTotal(page.getTotalElements());
		res.setRecordsFiltered(page.getTotalElements());
		res.setData(page.getContent());
		
		return res;
	}
	
	/***
	 * 获取客户端IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getRemoteAddr();
		}
		return ip;
	}
}

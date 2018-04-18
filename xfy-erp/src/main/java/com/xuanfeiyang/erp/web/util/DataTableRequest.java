package com.xuanfeiyang.erp.web.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 本类是对 jquery.DataTables 组件的请求参数的封装
 * 详细参考：http://datatables.net/manual/server-side
 * 
 * 
 * @author Adam
 *
 */
public class DataTableRequest<T> {
	
	private Integer draw;
	
	private Integer start;
	
	private Integer length;
	
	private DataTableSearch search;
	
	private List<DataTableOrder> order;
	
	private List<DataTableColumn> columns;
	
	private T params;

	public T getParams() {
		return params;
	}

	public void setParams(T params) {
		this.params = params;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public DataTableSearch getSearch() {
		return search;
	}

	public void setSearch(DataTableSearch search) {
		this.search = search;
	}

	public List<DataTableOrder> getOrder() {
		return order;
	}

	public void setOrder(List<DataTableOrder> order) {
		this.order = order;
	}

	public List<DataTableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DataTableColumn> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataTableRequest [draw=");
		builder.append(draw);
		builder.append(", start=");
		builder.append(start);
		builder.append(", length=");
		builder.append(length);
		builder.append(", search=");
		builder.append(search);
		builder.append(", order=");
		builder.append(order);
		builder.append(", columns=");
		builder.append(columns);
		builder.append(", params=");
		builder.append(params);
		builder.append("]");
		return builder.toString();
	}

	public String getOrderStr() {
		int orderByIdx = 0,idx = 0;
		String orderType =null;
		StringBuilder sbf = new StringBuilder();
		for(DataTableOrder dtor:order){
			orderByIdx = dtor.getColumn();
			orderType = dtor.getDir();
			break;
		}
		if (orderType==null){
			return sbf.toString();
		}
		
		for(DataTableColumn column:columns){
			if (idx++==orderByIdx && column.isOrderable() && StringUtils.isNotBlank(column.getName())){
				sbf.append(column.getName().concat(" ").concat(orderType));
			}
		}
		return sbf.toString();
	}
	
}
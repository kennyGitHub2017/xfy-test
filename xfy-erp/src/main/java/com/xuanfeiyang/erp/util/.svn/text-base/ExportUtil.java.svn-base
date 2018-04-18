package com.xuanfeiyang.erp.util;

import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.buzheng.excel.Column;
import org.buzheng.excel.ExcelBuilder;
import org.buzheng.excel.FileType;

public class ExportUtil {
	/**
	 * 
	 * @param response
	 * @param rowList  要导出的行数据
	 * @param columnList 要导出的列数据
	 */
	public static <T>  void export(final String fileName,HttpServletResponse response,List<T> rowList,
			List<Column> columnList) throws Exception{
		ServletOutputStream os = null;
		ExcelBuilder<T> eb = new ExcelBuilder<T>();
		eb.setFileType(FileType.XLSX);
		eb.setData(rowList);
		eb.setColumns(columnList);
		try{
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
			+new String(fileName.getBytes("GBK"),"ISO-8859-1"));
			os = response.getOutputStream();
			eb.toOutputStream(os);
		}finally{
			if (null!=os){
				os.flush();
				os.close();
			}
			rowList = null;
			columnList = null;
		}
	}
}

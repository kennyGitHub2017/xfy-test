package com.xuanfeiyang.erp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelParser {
	
	private static Logger logger = LoggerFactory.getLogger(ExcelParser.class);
	
	/**
	 * 解析excel文件内容，每个单元格都作为String类型。
	 * 
	 * @param file 文件路径
	 * 
	 * @return 所有行的内容：List<String[]> 为所有行，整个String[] 为每行的内容，String[] 中的每个元素为每个单元格的内容
	 */
	public static List<String[]> parseToString(String file) {

		String suffix = FilenameUtils.getExtension(file);

		InputStream is = null;
		try {
			is = new FileInputStream(new File(file));
		} catch (FileNotFoundException e1) {
			logger.info("file not found: {}", file);
			return null;
		}

		return parseToString(is, suffix);
	}
	
	/**
	 * 解析excel输入流，每个单元格都作为String类型。
	 * 
	 * @param is 输入流
	 * @param fileExtension 文件后缀名，用以区分excel类型
	 * @return
	 */
	public static List<String[]> parseToString(InputStream is, String fileExtension) {
		if (fileExtension == null) {
			throw new IllegalArgumentException("仅支持 xls 和 xlsx 文件。");
		}
		
		fileExtension = fileExtension.toLowerCase();
		
		if (! ("xls".equals(fileExtension) || "xlsx".equals(fileExtension))) {
			throw new IllegalArgumentException("仅支持 xls 和 xlsx 文件。");
		}
		
		Workbook workbook = null;
		
		try {
			if ("xls".equals(fileExtension)) {
				workbook = new HSSFWorkbook(is);
			} else if ("xlsx".equals(fileExtension)) {
				workbook = new XSSFWorkbook(is);
			} 
		} catch (IOException e) {
			logger.error("解析文件出错！", e);
			return null;
		}
		
		try {
			
			Sheet sheet = workbook.getSheetAt(0);
			int rowSize = sheet.getLastRowNum();
			
			// 确定最大列数
			int cellSize = 0;
			for (Row row : sheet) {
				int rowCellSize = row.getLastCellNum();
				if (rowCellSize > cellSize) {
					cellSize = rowCellSize;
				}
			}

			List<String[]> rowValues = new ArrayList<>(rowSize);
			for (Row row : sheet) {
				String[] cellValues = new String[cellSize];
				rowValues.add(cellValues);
				
				for (int i = 0; i < cellSize; i++) {
					Cell cell = row.getCell(i);
					if (cell == null) {
						continue;
					}
					
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValues[i] = (cell.getStringCellValue() == null ? "" : cell.getStringCellValue());
				}
			}
			
			return rowValues;
		} catch (Exception e) {
			logger.error("解析文件出错！", e);
			return null;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				return null;
			}
		}
	}
	/*
	public static void main(String[] args) {
		
		List<String[]> data = ExcelParser.parseToString("d:\\1111.xls");
		for (int i = 0; i < data.size(); i++) {
			System.out.print(Arrays.toString(data.get(i)));
			System.out.println();
		}
	}
	*/
}

package com.xuanfeiyang.erp.service;

/**
 * 描述文件导入时出现的错误
 *
 */
public class FileImportException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3479228086113926L;

	// 行号
	private int lineNumber;
	
	// 列数
	private int columnNumber;
	
	// 描述信息
	private String message;
	
	
	
	public FileImportException(int lineNumber, int columnNumber, String message) {
		super(message);
		this.lineNumber = lineNumber;
		this.columnNumber = columnNumber;
		this.message = message;
	}

	public FileImportException(String message) {
		super(message);
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

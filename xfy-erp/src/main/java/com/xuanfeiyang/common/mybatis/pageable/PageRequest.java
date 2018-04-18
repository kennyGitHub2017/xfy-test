package com.xuanfeiyang.common.mybatis.pageable;

/**
 * 分页请求对象
 * 
 * @author zany@buzheng.org
 *
 */
public class PageRequest {
	
	private final int pageNumber;
	private final int pageSize;
	
	/**
	 * 是否查询总记录数
	 */
	private final boolean countable;

	/**
	 * 
	 * @param pageNumber the page no you want to request, start from 0
	 * @param pageSize page size
	 */
	public PageRequest(int pageNumber, int pageSize) {

		this(pageNumber, pageSize, true);
	}
	
	/**
	 * 
	 * @param pageNumber the page no you want to request, start from 0
	 * @param pageSize page size
	 * @param countable whether count total numbers
	 */
	public PageRequest(int pageNumber, int pageSize, boolean countable) {

		if (pageNumber < 0) {
			throw new IllegalArgumentException("Page index must not be less than zero!");
		}

		if (pageSize < 0) {
			throw new IllegalArgumentException("Page size must not be less than zero!");
		}

		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		
		this.countable = countable;
	}
	
	public boolean getCountable() {
		return countable;
	}
	
	public int getPageSize() {

		return pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getOffset() {
		return pageNumber * pageSize;
	}

	public boolean hasPrevious() {
		return pageNumber > 0;
	}

	public PageRequest next() {
		return new PageRequest(pageNumber + 1, pageSize);
	}

	public PageRequest previousOrFirst() {
		return hasPrevious() ? new PageRequest(pageNumber - 1, pageSize) : this;
	}

	public PageRequest first() {
		return new PageRequest(0, pageSize);
	}

	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Page request [number: %d, size %d, sort: %s]", pageNumber, pageSize);
	}
}

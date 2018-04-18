package com.xuanfeiyang.erp.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.NoticeDao;
import com.xuanfeiyang.erp.domain.Notice;

@Service
public class NoticeService {

	@Resource
	private NoticeDao noticeDao;
	
	public void save(Notice notice) {
		if (notice == null) {
			return;
		}
		
		Date now = new Date();
		notice.setCreatedTime(now);
		notice.setLastUpdatedTime(now);
		this.noticeDao.insert(notice);
	}
	
	public void delete(Integer id) {
		this.noticeDao.delete(id);
	}
	
	public void update(Notice notice) {
		if (notice == null) {
			return;
		}
		
		notice.setLastUpdatedTime(new Date());
		this.noticeDao.update(notice);
	}
	
	public Notice load(Integer id) {
		return this.noticeDao.load(id);
	}
	
	public Page<Notice> findPage(PageRequest pageRequest) {
		return this.noticeDao.findPage(pageRequest);
	}
	
	public Notice getLatestOne() {
		return this.noticeDao.getLatestOne();
	}
}

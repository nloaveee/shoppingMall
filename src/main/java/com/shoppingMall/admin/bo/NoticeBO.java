package com.shoppingMall.admin.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingMall.admin.domain.Notice;
import com.shoppingMall.admin.mapper.NoticeMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeBO {

	@Autowired
	private NoticeMapper noticeMapper;
	
	// 공지사항 작성 
	public void addNotice(String title,String content) {
		noticeMapper.insertNotice(title, content);
	}
	
	// 공지사항 list
	public List<Notice> getNoticeList() {
		return noticeMapper.selectNoticeList();
	}
	
	// 공지사항 수정 
	public void updateNoticeByNoticeId(int id, String title, String content) {
		
		// 기존 글 가져오기 (업데이트 대상이 있는지 확인)
		Notice notice = noticeMapper.selectNoticeByNoticeId(id);
		if (notice == null) {
			log.warn("[공지사항 수정] notice is null. noticeId:{}", id);
			return;
		}
		
		// db update
		noticeMapper.updateNoticeByNoticeId(id, title, content);
	}
	
	public Notice getNoticeByNoticeId(int id) {
		return noticeMapper.selectNoticeByNoticeId(id);
	}
	
	// 공지사항 삭제 
	public void deleteNoticeByNoticeId(int id) {
		noticeMapper.deleteNoticeByNoticeId(id);
	}
}

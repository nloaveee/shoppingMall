package com.shoppingMall.admin.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shoppingMall.admin.domain.Notice;


@Mapper
public interface NoticeMapper {
	
	// 공지사항 작성 
	public void insertNotice(
			@Param("title") String title,
			@Param("content") String content);
	
	// 공지사항 list
	public List<Notice> selectNoticeList();
	
	public Notice selectNoticeByNoticeId(int id);
	
	// 공지사항 수정 
	public void updateNoticeByNoticeId(
			@Param("id") int id, 
			@Param("title") String title, 
			@Param("content") String content);
	
	// 공지사항 삭제 
	public void deleteNoticeByNoticeId(int id);
	
}

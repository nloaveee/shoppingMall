package com.shoppingMall.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingMall.admin.bo.NoticeBO;
import com.shoppingMall.admin.domain.Notice;
import com.shoppingMall.cs.bo.InquiryBO;
import com.shoppingMall.cs.entity.Inquiry;
import com.shoppingMall.cs.entity.InquiryAnswer;
import com.shoppingMall.user.bo.UserBO;
import com.shoppingMall.user.entity.User;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/shop/main")
@Controller
public class CsController {
	
	@Autowired
	private InquiryBO inquiryBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private NoticeBO noticeBO;
	
		// Q&A 목록
		@GetMapping("/inquiry-list-view")
		public String inquiryListView(
				Model model) {
			
			List<Inquiry> inquiryList = inquiryBO.getInquiryList();
			
			model.addAttribute("inquiryList",inquiryList);
			
			return "cs/list";
		}
		
		// Q&A 작성
		@GetMapping("/inquiry-create-view")
		public String inquiryCreateView(Model model, HttpSession session) {
			
			if (session != null) {
				String userId = (String)session.getAttribute("userId");
				User user = userBO.getUserByUserId(userId);
				model.addAttribute("user",user);
			} else {
				model.addAttribute("user",null);
			}
			return "cs/create";
		}
		
		
		// Q&A 상세 페이지
		@GetMapping("/inquiry-detail-view")
		public String inquirydetailView(
				@RequestParam("inquiryId") int id,
				Model model
				) {
			
			Inquiry inquiry = inquiryBO.getInquiryById(id);
			InquiryAnswer answer = inquiryBO.getInquiryAnswer(id);			
			
			model.addAttribute("inquiry",inquiry);
			model.addAttribute("answer",answer);
			
			return "cs/detail";
		}
		
		// 공지사항목록 페이지 
		@GetMapping("/notice-list-view")
		public String noticeListView(Model model) {
			
			// db 조회
			List<Notice> noticeList = noticeBO.getNoticeList();
			
			model.addAttribute("noticeList" , noticeList);
			return "notice/list";
		}

		// 공지사항 상세 페이지
		@GetMapping("/notice-detail-view")
		public String noticeDetailView(
				@RequestParam("noticeId") int id
				,Model model) {
			
			// db 조회 
			Notice notice = noticeBO.getNoticeByNoticeId(id);
			
			model.addAttribute("notice" ,notice);
			
			return "notice/detail";
		}

	
}

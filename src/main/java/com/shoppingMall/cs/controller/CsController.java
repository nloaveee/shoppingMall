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
				@RequestParam(value="prevId", required = false) Integer prevIdParam,
				@RequestParam(value="nextId", required = false) Integer nextIdParam,
				Model model) {
			
				List<Inquiry> inquiryList = inquiryBO.getInquiryList(prevIdParam, nextIdParam);
			
				int prevId = 0;
				int nextId = 0;
				if (inquiryList.isEmpty() == false) { // item 비어있지 않을 때 페이징 정보 세팅 
					prevId = inquiryList.get(0).getId(); // 첫번째칸 id
					nextId = inquiryList.get(inquiryList.size() - 1).getId(); // 마지막칸 id 	
					
					// 이전 방향의 끝인가? => 끝이면 0으로 세팅 
					// prevId == 유저가 쓴 item 테이블의 제일 큰 숫자와 같으면 이전의 끝페이지 
					if (inquiryBO.isPrevLastPage( prevId)) { // true가 온 경우
						prevId = 0;
					}
					
					// 다음 방향의 끝인가? => 끝이면 0으로 세팅
					// nextId == 테이블의 제일 작은 숫자와 같으면 다음의 끝페이지 
					if (inquiryBO.isNextLastPage(nextId)) { // true가 온 경우 
						nextId = 0;
					}
					
				}
				
				model.addAttribute("inquiryList",inquiryList);
				model.addAttribute("prevId",prevId);
				model.addAttribute("nextId",nextId);
			
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

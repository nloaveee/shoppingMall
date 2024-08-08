package com.shoppingMall.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingMall.user.bo.UserBO;
import com.shoppingMall.user.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop/user")
public class MypageController {
	
	@Autowired
	private UserBO userBO;
	
	// 마이페이지 - 메인
	@GetMapping("/mypage")
	public String mypage(Model model, HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		
		// db 조회 
		User user = userBO.getUserByUserId(userId);
		
		model.addAttribute("user",user);
		
		return "mypage/main";
	}
	
	// 마이페이지-프로필 페이지
		@GetMapping("/mypage/profile")
		public String profile(Model model, HttpSession session) {
			
			String userId = (String)session.getAttribute("userId");
			
			// db 조회 
			User user = userBO.getUserByUserId(userId);
			
			model.addAttribute("user",user);
			
			return "mypage/profile";
		}
	
	
	

}

package com.shoppingMall.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop/user")
public class UserController {
	
	// 로그인
	@GetMapping("/sign-in-view")
	public String signInView() {
		return "user/signIn";
	}
	
	// 회원가입 
	@GetMapping("/sign-up-view")
	public String signUpView() {
		return "user/signUp";
	}
	
	// 로그아웃 API
		@GetMapping("/sign-out")
		public String signOut(
				HttpSession session) {
			session.removeAttribute("userId");
			session.removeAttribute("loginId");
			session.removeAttribute("name");
			session.removeAttribute("email");
			session.removeAttribute("phone");
			
			return "redirect:/shop/main";		
		}
		
	// 아이디 찾기 
	@GetMapping("/find-id-view")
	public String findIdView() {
		return "user/findId";
	}
	
	// 비밀번호 찾기 
	@GetMapping("/find-pwd-view")
	public String finPwdView() {
		return "user/findPwd";
	}
	
	
	
	


}

package com.shoppingMall.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}

package com.shoppingMall.mypage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingMall.common.PasswordEncryptor;
import com.shoppingMall.mypage.bo.WishBO;
import com.shoppingMall.mypage.entity.Wish;
import com.shoppingMall.user.bo.UserBO;
import com.shoppingMall.user.entity.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/shop/user/mypage")
public class MypageRestController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private WishBO wishBO;

	@PostMapping("/profile-update")
	public Map<String, Object> profileUpdate(
			@RequestParam("userPwd") String userPwd,
			@RequestParam("phone") String phone,
			@RequestParam("fullAddress") String address,
			HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		
		String encryptedPassword = PasswordEncryptor.encryptPassword(userPwd);
		
		User user = userBO.updateUser(userId, encryptedPassword, phone,address);
		
		Map<String, Object> result = new HashMap<>();
			if (user != null) {
				result.put("code", 200);
			} else {			
				result.put("error_message", "일치하는 정보가 없습니다.");
			}
		
		return result;
	}
	
	// 찜 
	@PostMapping("/wish/add")
	public Map<String, Object> wishAdd(
			@RequestParam("size") String size,
			@RequestParam("color") String color,
			@RequestParam("itemId") int itemId,
			HttpSession session){
		
		String userId = (String)session.getAttribute("userId");
		
		// db insert
		boolean wishAdd = wishBO.addWish(userId, size, color, itemId);
		
				
		Map<String, Object> result = new HashMap<>();
		if (wishAdd == true) {
			result.put("code", 200);
		} else {			
			result.put("error_message", "찜 하는데 실패했습니다.");
		}
	
	return result;
	}
}

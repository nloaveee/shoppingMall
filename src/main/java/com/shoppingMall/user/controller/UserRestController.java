package com.shoppingMall.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingMall.common.PasswordEncryptor;
import com.shoppingMall.user.bo.UserBO;
import com.shoppingMall.user.entity.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/shop/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	/**
	 * 아이디 중복확인
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId(@RequestParam("userId") String userId) {
		
		// db 조회 
		User user = userBO.getUserByUserId(userId);
		
		// 응답값 
		Map<String, Object> result = new HashMap<>();		
		result.put("code", 200);
		if (user != null) {
			result.put("is_duplicated_id",true);
		} else {
			result.put("is_duplicated_id",false);
		}

		return result;
	}
	
	/**
	 * 회원가입 
	 * @param userId
	 * @param password
	 * @param name
	 * @param phone
	 * @param email
	 * @param address
	 * @return
	 */
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("fullAddress") String address
			){
		
		
		// 비밀번호 암호화
		String encryptedPassword = PasswordEncryptor.encryptPassword(password);
		
		// db insert
		User user =  userBO.addUser(userId, encryptedPassword, name, phone, email, address);
		
		// 응답값 
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "회원가입에 실패했습니다.");
		}	
		
		return result;
	}
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("userId") String userId,
			@RequestParam("userPwd") String password,
			HttpSession session) {
		
		String encryptedPassword = PasswordEncryptor.encryptPassword(password);
		
		// db 조회 
		User user = userBO.getUserByLoginIdAndUserPwd(userId,encryptedPassword);
		
		// 응답값 
		Map<String, Object> result = new HashMap<>();
	    if (user != null) {
	    	session.setAttribute("userId", user.getId());
	    	session.setAttribute("userId", user.getUserId());
	    	session.setAttribute("name", user.getName());
	    	session.setAttribute("email", user.getEmail());
	    	
	    	result.put("code", 200); 
		} else {
			result.put("error_message", "로그인이 실패했습니다.");
		}
	    
		return result;
	}
	

}

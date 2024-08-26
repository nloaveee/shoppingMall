package com.shoppingMall.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingMall.common.PasswordEncryptor;
import com.shoppingMall.mypage.bo.CartBO;
import com.shoppingMall.mypage.bo.WishBO;
import com.shoppingMall.mypage.entity.CartItem;
import com.shoppingMall.mypage.entity.Wish;
import com.shoppingMall.mypage.entity.WishList;
import com.shoppingMall.mypage.entity.WishRequest;
import com.shoppingMall.order.entity.OrderItem;
import com.shoppingMall.order.entity.OrderRequest;
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
	
	@Autowired
	private CartBO cartBO;

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
			@RequestBody WishRequest request,
			HttpSession session){
		
		String userId = (String)session.getAttribute("userId");
		
		List<WishList> wishList = request.getWishList();
		
		List<WishList> wishAdd = wishBO.addWish(wishList,userId);
				
		Map<String, Object> result = new HashMap<>();
		if (wishAdd != null) {
			result.put("code", 200);
		} else {			
			result.put("error_message", "찜 하는데 실패했습니다.");
		}
	
	return result;
	}
	
	// 카트
	@PostMapping("/cart/add")
	public Map<String, Object> cartAdd(	
			@RequestBody List<CartItem> cartList,
	        HttpSession session) {

	    Map<String, Object> result = new HashMap<>();
	    String userId = (String) session.getAttribute("userId");

	    if (userId == null) {
	        result.put("code", 401);
	        return result;
	    }
	    
	    result = cartBO.addCart(userId, cartList);	    

	    return result;
	}
}

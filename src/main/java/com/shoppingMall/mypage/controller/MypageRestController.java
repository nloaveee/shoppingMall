package com.shoppingMall.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingMall.common.PasswordEncryptor;
import com.shoppingMall.mypage.bo.CartBO;
import com.shoppingMall.mypage.bo.WishBO;
import com.shoppingMall.mypage.entity.CartItem;
import com.shoppingMall.mypage.entity.WishItem;
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
			@RequestBody List<WishItem> wishList,
	        HttpSession session) {

	    Map<String, Object> result = new HashMap<>();
	    String userId = (String) session.getAttribute("userId");
	    
	    result = wishBO.addWish(userId, wishList);	    

	    return result;
	}
	
	// 찜 상품 삭제 
	@DeleteMapping("/wish/delete")
	public Map<String, Object> wishItemDelete(
			@RequestParam("wishId") int wishId
			) {
			    
		wishBO.deleteWishItem(wishId);	    
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);

	    return result;		
	}
	
	// 장바구니
	@PostMapping("/cart/add")
	public Map<String, Object> cartAdd(	
			@RequestBody List<CartItem> cartList,
	        HttpSession session) {

	    Map<String, Object> result = new HashMap<>();
	    String userId = (String) session.getAttribute("userId");

	    // 비로그인
	    if (userId == null) {
	        result.put("code", 401);
	        return result;
	    }
	    
	    result = cartBO.addCart(userId, cartList);	    

	    return result;
	}
	
	// 장바구니 상품 삭제 
	@DeleteMapping("/cart/delete")
	public Map<String, Object> cartItemDelete(
			@RequestParam("cartId") int cartId
			) {
			    
		cartBO.deleteCartItem(cartId);	    
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);

	    return result;		
	}
	
	// 장바구니 상품 찜하기 
	@PostMapping("/cart/wish-add")
	public Map<String, Object> cartWishAdd(
			@RequestParam("itemId") int itemId,
			@RequestParam("optionId") int optionId,
			HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		result = wishBO.addCartItemWish(userId,itemId,optionId);
			
		return result;
		
	}
	
	// 장바구니 상품 수량 변경 
	@PostMapping("/cart/quantity-update")
	public Map<String, Object> quantityUpdate(
			@RequestParam("cartId") int cartId,
			@RequestParam("quantity") int quantity) {
		
		Map<String, Object> result = new HashMap<>();
		
		result = cartBO.updateCartItemQuantity(cartId,quantity);
		
		return result;
	}
}

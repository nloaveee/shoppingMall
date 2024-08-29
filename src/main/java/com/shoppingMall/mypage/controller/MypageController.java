package com.shoppingMall.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingMall.mypage.bo.CartBO;
import com.shoppingMall.mypage.bo.WishBO;
import com.shoppingMall.mypage.entity.Cart;
import com.shoppingMall.mypage.entity.CartItem;
import com.shoppingMall.mypage.entity.CartView;
import com.shoppingMall.mypage.entity.Wish;
import com.shoppingMall.mypage.entity.WishView;
import com.shoppingMall.order.entity.OrderItem;
import com.shoppingMall.user.bo.UserBO;
import com.shoppingMall.user.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop/user")
public class MypageController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private WishBO wishBO;
	
	@Autowired
	private CartBO cartBO;
	
	// 마이페이지 - 메인
	@GetMapping("/mypage")
	public String mypage(Model model, HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		
		// db 조회 
		User user = userBO.getUserByUserId(userId);
		
		model.addAttribute("user",user);
		
		return "mypage/main";
	}
	
	// 마이페이지-프로필 페이지 수정 
	@GetMapping("/mypage/profile")
	public String profile(Model model, HttpSession session) {
		
		String userId = (String)session.getAttribute("userId");
		
		// db 조회 
		User user = userBO.getUserByUserId(userId);
		
	    // address를 나누는 코드
		// $ 기호를 기준으로 나누기
		String[] addressParts = user.getAddress().split("\\&");
		String postCode = addressParts.length > 0 ? addressParts[0].trim() : "";
		String address = addressParts.length > 1 ? addressParts[1].trim() : "";
		String detailAddress = addressParts.length > 2 ? addressParts[2].trim() : "";
		
	    // 나눈 값들을 model에 담기
	    model.addAttribute("postCode", postCode);
	    model.addAttribute("address", address);
	    model.addAttribute("detailAddress", detailAddress);
	    model.addAttribute("user", user);
		
		return "mypage/profile";
	}

	// 마이페이지 - 찜
	@GetMapping("/mypage/wish-view")
	public String wish(
			HttpSession session,
			Model model) {
		
		String userId = (String)session.getAttribute("userId");
		
	    List<WishView> wishViewList = wishBO.generateWishViewList(userId);
	    model.addAttribute("wishViewList", wishViewList);
		
		return "mypage/wish";
	}
	
	
	// 마이페이지 - 카트
	@GetMapping("/mypage/cart-view")
	public String cartView(
			@RequestParam(value="prevId", required = false) Integer prevIdParam,
			@RequestParam(value="nextId", required = false) Integer nextIdParam,
			HttpSession session,
			Model model) {
		
		String userId =(String) session.getAttribute("userId");
		
		List<Cart> cartItem = cartBO.getCartItemByUserId(userId);
		int prevId = 0;
		int nextId = 0;
		if (cartItem.isEmpty() == false) { // 글 목록이 비어있지 않을 때 페이징 정보 세팅 
			prevId = cartItem.get(0).getId();// 첫번째칸 id
			nextId = cartItem.get(cartItem.size() - 1).getId(); // 마지막칸 id 	
			
			// 이전 방향의 끝인가? => 끝이면 0으로 세팅 
			// prevId == 유저가 쓴 post 테이블의 제일 큰 숫자와 같으면 이전의 끝페이지 
			if (cartBO.isPrevLastPageByUserId(userId, prevId)) { // true가 온 경우
				prevId = 0;
			}
			
			// 다음 방향의 끝인가? => 끝이면 0으로 세팅
			// nextId == 테이블의 제일 작은 숫자와 같으면 다음의 끝페이지 
			if (cartBO.isNextLastPageByUserId(userId,nextId)) { // true가 온 경우 
				nextId = 0;
			}
		}		
		
		List<CartView> cartViewList = cartBO.generateCartViewList(userId);
		model.addAttribute("cartViewList",cartViewList);
		model.addAttribute("prevId",prevId);
		model.addAttribute("nextId",nextId);
		
		return "mypage/cart";
	}
	
	

}

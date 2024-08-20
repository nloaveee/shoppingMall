package com.shoppingMall.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop/order")
public class OrderController {
	

	// 주문 메인 
	@GetMapping("/order-form-view")
	public String orderFormView() {
				
		return "order/orderForm";
	}
	
	// 주문시 로그인 여부 확인 
	@GetMapping("/checkLogin")
	public ResponseEntity<Map<String, Object>> checkLogin(HttpSession session) {
	    Map<String, Object> response = new HashMap<>();
	    String loggedInUser = (String) session.getAttribute("userId");
	    
	    if (loggedInUser != null) {
	        response.put("loggedIn", true);
	    } else {
	        response.put("loggedIn", false);
	    }
	    return ResponseEntity.ok(response);
	}
}

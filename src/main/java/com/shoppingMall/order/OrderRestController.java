package com.shoppingMall.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.order.bo.OrderBO;
import com.shoppingMall.user.entity.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/shop/order")
public class OrderRestController {
	
	@Autowired
	private OrderBO orderBO;

	// 주문하기
	@PostMapping("/order")
	public Map<String, Object> order(
			@RequestParam("items") List<Item> orderList, 
			@RequestParam("totalPrice") int totalPrice,
			HttpSession session) {
		
		String userId = (String)session.getAttribute("userId");
		
		// db insert
		orderBO.addOrder(userId, orderList, totalPrice );
		
		
		

	    Map<String, Object> result = new HashMap<>();
	    result.put("code", 200);
	    return result;
	}


	
}

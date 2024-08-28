package com.shoppingMall.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.mypage.entity.CartItem;
import com.shoppingMall.order.bo.OrderBO;
import com.shoppingMall.order.entity.Orders;
import com.shoppingMall.order.entity.OrderItem;
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
			@RequestBody List<OrderItem> orderList,
	        HttpSession session) {
	    
	    Map<String, Object> result = new HashMap<>();
	    String userId = (String) session.getAttribute("userId");

	    // 비로그인
	    if (userId == null) {
	        result.put("code", 401);
	        return result;
	    }
	    
	    result = orderBO.addOrder(userId, orderList);	    

	    return result;
	}
	
	// cart 개별 상품 주문 
//	@PostMapping("/cart-order")
//	public Map<String, Object> cartOrder(
//			@RequestParam("itemId") int itemId,
//			@RequestParam("optionId") int optionId,
//			@RequestParam("quantity") int quantity,
//			HttpSession session
//			) {
//		
//		Map<String, Object> result = new HashMap<>();
//		String userId = (String) session.getAttribute("userId");
//		
//		orderBO.addCartOrder(userId,itemId,optionId);
//		
//		
//		
//	}
	


	
}

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
import com.shoppingMall.order.bo.OrderBO;
import com.shoppingMall.order.entity.Orders;
import com.shoppingMall.order.entity.OrderItem;
import com.shoppingMall.order.entity.OrderRequest;
import com.shoppingMall.user.entity.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/shop/order")
public class OrderRestController {
	
	@Autowired
	private OrderBO orderBO;
		

	// 주문하기
//	@PostMapping("/order")
//	public Map<String, Object> order(
//			@RequestBody OrderRequest request,
//	        HttpSession session) {
//	    
//	    String userId = (String) session.getAttribute("userId");
//	    
//	    List<OrderItem> orderItem = request.getOrderItem();
//	    
//	    orderBO.addOrder(userId,orderItem);	    
//	    
//	    Map<String, Object> result = new HashMap<>();
//	    result.put("code", 200);
//
//	    return result;
//	}


	
}

package com.shoppingMall.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingMall.user.bo.UserBO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop/order")
public class OrderController {
	

	// 주문 메인 
	@RequestMapping("/order-form-view")
	public String orderFormView() {
				
		return "order/orderForm";
	}
}

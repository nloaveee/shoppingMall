package com.shoppingMall.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop/order")
public class OrderController {

	// 주문 메인 
	@RequestMapping("/main-view")
	public String orderMainView() {
		return "order/main";
	}
}

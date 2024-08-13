package com.shoppingMall.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop/order")
public class OrderController {

	// 주문 메인 
	@RequestMapping("/order-form-view")
	public String orderFormView() {
		return "order/orderForm";
	}
}

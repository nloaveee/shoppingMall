package com.shoppingMall.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.order.bo.OrderBO;
import com.shoppingMall.order.entity.OrderItem;
import com.shoppingMall.order.entity.OrderView;
import com.shoppingMall.user.bo.UserBO;
import com.shoppingMall.user.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop/order")
public class OrderController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private OrderBO orderBO;
		
	@Autowired
	private ItemBO itemBO;
	
	// 주문 메인 
	@GetMapping("/order-form-view")
	public String orderFormView(Model model, HttpSession session) {
				
		String userId = (String) session.getAttribute("userId");
		
		User user = userBO.getUserByUserId(userId);	
		
	    List<OrderItem> orderList = (List<OrderItem>) session.getAttribute("orderList");
	    model.addAttribute("orderList", orderList);
		
		
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
		model.addAttribute("user",user);
		
		return "order/orderForm";
	}
	
	// 주문 성공 페이지 
	@GetMapping("/order-success-view")
	public String orderSuccess() {
		return "order/success";
	}
	

}

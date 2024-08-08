package com.shoppingMall.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class MainController {

	// 메인 화면
	@GetMapping("/main")
	public String shopMain() {
		return "main/shopMain";
	}
}

package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.dto.ItemDto;

@Controller
public class ThymeleafController {

	@GetMapping("/thymeleaf/ex1")
	public String ex1(Model model) {

		ItemDto itemDto = ItemDto.builder().itemNm("최신 스프링").itemDetail("스프링 부트 3.1.4").itemSellStatus("SELL")
				.price(20000).build();

		model.addAttribute("itemDto", itemDto);
		return "thymeleaf/ex1";
	}

	@GetMapping("/")
	public String ex4() {
		return "/index";
	}

}

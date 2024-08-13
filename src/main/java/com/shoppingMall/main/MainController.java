package com.shoppingMall.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;

@Controller
@RequestMapping("/shop")
public class MainController {
	
	@Autowired
	private ItemBO itemBO;

	// 메인 화면
	@GetMapping("/main")
	public String shopMain(Model model) {
		
		List<Item> itemList = itemBO.getItemList();
		
		model.addAttribute("itemList",itemList);
		
		return "main/shopMain";
	}
	
}

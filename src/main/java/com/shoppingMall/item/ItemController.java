package com.shoppingMall.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;


@Controller
@RequestMapping("/shop/item")
public class ItemController {
	
	@Autowired
	private ItemBO itemBO;
	
	// 상품 상세 페이지
	@GetMapping("/item-detail-view")
	public String itemDetailView(
			@RequestParam("itemid") int itemId,
			Model model) {
		
		// 상품 상세 정보 가져오기 
		
		Item item = itemBO.getItemByid(itemId);
		//ItemOption itemOption = itemBO.get
		
		model.addAttribute("item",item);
		
		
		return "item/detail";
	}
	

}

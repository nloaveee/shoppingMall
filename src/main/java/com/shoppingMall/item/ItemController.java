package com.shoppingMall.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/shop/item")
public class ItemController {
	
	@Autowired
	private ItemBO itemBO;
	
	// 상품 상세 페이지
	@GetMapping("/item-detail-view")
	public String itemDetailView(
			@RequestParam("itemid") int itemId,
			Model model,HttpSession session) {
		
		// 상품 상세 정보 가져오기 		
		Item item = itemBO.getItemById(itemId);
		List<ItemOption> optionList = itemBO.getItemOptionListByItemId(itemId);
		String userId = (String)session.getAttribute("userId");
		
		model.addAttribute("item",item);
		model.addAttribute("optionList",optionList);
		model.addAttribute("userId",userId);
		
		
		return "item/detail";
	}
	

}

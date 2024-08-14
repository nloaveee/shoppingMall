package com.shoppingMall.mypage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.mypage.repository.WishRepository;

@Service
public class WishBO {

	@Autowired
	private WishRepository wishRepository;
	
	@Autowired
	private ItemBO itemBO;
	
//	public void addWish(String name, String option) {
//		Item item = itemBO.getItemByName(name);
//		ItemOption itemOption = itemBO.get
//	}
}

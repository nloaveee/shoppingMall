package com.shoppingMall.mypage.bo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingMall.admin.bo.ItemBO;

import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.mypage.entity.Wish;
import com.shoppingMall.mypage.entity.WishItem;
import com.shoppingMall.mypage.repository.WishRepository;
@Service
public class WishBO {

	@Autowired
	private WishRepository wishRepository;
	
	@Autowired
	private ItemBO itemBO;
	
	public boolean addWish(String userId, String size, String color, int itemId) {
		
		List<ItemOption> optionList = itemBO.getItemOptionByItemIdColorSize(itemId, color, size);
		
		for(ItemOption option:optionList) {
			int optionId = option.getId();
			wishRepository.save(Wish.builder()
					.userId(userId)
					.optionId(optionId)
					.build());
		}
		
		return true;				
	}
	
	public List<Wish> getWishListByUserId(String userId) {
		return wishRepository.findByUserId(userId);
	}
	
	
}

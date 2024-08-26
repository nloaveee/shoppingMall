package com.shoppingMall.mypage.bo;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.mypage.entity.Wish;
import com.shoppingMall.mypage.entity.WishList;
import com.shoppingMall.mypage.repository.WishRepository;

@Service
public class WishBO {

	@Autowired
	private WishRepository wishRepository;
	
	@Autowired
	private ItemBO itemBO;
	
	
	
	public List<WishList> addWish(List<WishList> wishList,String userId) {
	
		List<WishList> userWishList = new ArrayList<>();
		
		for (WishList wish: wishList) {
			
			Item item = itemBO.getItemByName(wish.getName());
			
			String[] optionPart = wish.getOption().split("\\/");
			String color = optionPart.length>0 ? optionPart[0].trim() : "";
			String size = optionPart.length>1 ? optionPart[1].trim() : "";
			ItemOption option = itemBO.getItemOptionByItemIdColorSize(item.getId(),color , size);
			
			wishRepository.save(Wish.builder()
					.userId(userId)
					.itemId(option.getItemId())
					.optionId(option.getId())
					.build());
			
			userWishList.add(wish);
		}
		return userWishList;
	}
	
}

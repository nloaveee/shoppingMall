package com.shoppingMall.mypage.bo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.mypage.entity.Cart;
import com.shoppingMall.mypage.entity.CartItem;
import com.shoppingMall.mypage.entity.Wish;
import com.shoppingMall.mypage.entity.WishItem;
import com.shoppingMall.mypage.repository.WishRepository;

@Service
public class WishBO {

	@Autowired
	private WishRepository wishRepository;
	
	@Autowired
	private ItemBO itemBO;
	
	
public Map<String, Object> addWish(String userId, List<WishItem> wishList) {
		
		Map<String, Object> result = new HashMap<>(); 
	        for (WishItem item : wishList) {
	        	
	        	String[] optionPart = item.getOption().split("\\/");
				String color = optionPart.length>0 ? optionPart[0].trim() : "";
				String size = optionPart.length>1 ? optionPart[1].trim() : "";
				ItemOption option = itemBO.getItemOptionByItemIdColorSize(item.getItemId(),color , size);
	        	
	            // wish 이미 같은 아이템이 있는지 확인
	            Wish existingWish = wishRepository.findByUserIdAndItemIdAndOptionId(userId, item.getItemId(),option.getId());
	            
	            if (existingWish != null) {
	                result.put("code", 501); // 이미 있는 경우
	                return result;
	            }

	            // 새로운 Cart 객체 생성
	            Wish wish = Wish.builder()
	                    .userId(userId)
	                    .itemId(item.getItemId())
	                    .optionId(option.getId())
	                    .build();

	            // DB에 저장
	            wishRepository.save(wish);
	        }
	        
	        result.put("code", 200); // 성공
			return result;
		}
	
}

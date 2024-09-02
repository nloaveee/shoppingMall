package com.shoppingMall.mypage.bo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.mypage.entity.Wish;
import com.shoppingMall.mypage.entity.WishItem;
import com.shoppingMall.mypage.entity.WishView;
import com.shoppingMall.mypage.repository.WishRepository;
import com.shoppingMall.user.bo.UserBO;
import com.shoppingMall.user.entity.User;



@Service
public class WishBO {

	@Autowired
	private WishRepository wishRepository;
	
	@Autowired
	private ItemBO itemBO;
	
	@Autowired
	private UserBO userBO;
	
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
		
	
	public Map<String, Object> addCartItemWish(String userId, int itemId, int optionId) {
		
		Map<String, Object> result = new HashMap<>();
		
		Wish existingWish = wishRepository.findByUserIdAndItemIdAndOptionId(userId, itemId, optionId);
				
        if (existingWish != null) {
            result.put("code", 500); // 이미 있는 경우
            return result;
        }
        
        wishRepository.save(Wish.builder()
        		.userId(userId)
        		.itemId(itemId)
        		.optionId(optionId)        		       		
        		.build());
        
        result.put("code", 200);
        return result;
	}
	
	public List<WishView> generateWishViewList(String userId) {
		List<WishView> wishViewList = new ArrayList<>(); 
		
		List<Wish> wishList = getWishItemByUserId(userId);
		
		for (Wish wish : wishList) {
			WishView wishView = new WishView();
			
			User user = userBO.getUserByUserId(userId);
			wishView.setUser(user);
			
			wishView.setWish(wish);
			
			List<WishItem> wishItemList = new ArrayList<>();
			
			WishItem wishItemObj = new WishItem();
			wishItemObj.setItemId(wish.getItemId());
			
			Item item = itemBO.getItemById(wish.getItemId());
			wishItemObj.setItem(item);
			
			ItemOption option = itemBO.getitemOptionByOptionId(wish.getOptionId()); // Option 정보 가져오기
			wishItemObj.setItemOption(option);
			
			wishItemList.add(wishItemObj);
			
			wishView.setWishItemList(wishItemList);
			
			wishViewList.add(wishView);
		}
		
		return wishViewList;
	}
	
	public List<Wish> getWishItemByUserId(String userId) {
		return wishRepository.findByUserId(userId);
	}
	
	public void deleteWishItem(int wishId) {
		wishRepository.deleteById(wishId);
	}
	
	@Transactional
	public void deleteWishItemByUserId(String userId) {
		wishRepository.deleteByUserId(userId);
	}
	
	@Transactional
	public Map<String, Object> deleteWishItemByWishId(List<Integer> wishIds) {
		
		Map<String, Object> result = new HashMap<>();
		
		for (Integer wishId : wishIds) {			
			wishRepository.deleteById(wishId);			
		}
		result.put("code", 200);
		return result;
	}
	
}

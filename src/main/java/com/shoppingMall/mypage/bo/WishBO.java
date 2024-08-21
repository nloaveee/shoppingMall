package com.shoppingMall.mypage.bo;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.mypage.entity.Wish;
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
	
//	public List<WishView> generateWishViewList(String userId) {
//		List<WishView> wishViewList = new ArrayList<>();
//		
//		List<Wish> wishList = getWishListByUserId(userId);
//		
//		for(Wish wish : wishList) {
//			WishView wishview = new WishView();
//			
//			wishview.setWish(wish);
//			
//			// 회원
//			User user = userBO.getUserByUserId(wishview.getUser().getUserId());
//			wishview.setUser(user);
//			
//			// 아이템
//			List<WishView> wishItemList = generateWishViewItemListByUserId(wish.getUserId());
//			wishview.setItem(wishItemList);
//			
//			// 옵션 		
//			List<WishView> wishOptionList = itemBO.generateOptionViewListByoptionId(wish.getOptionId());
//			wishview.setItemOption(wishOptionList);
//			
//		}
//	}
	
	public List<WishView> generateWishViewItemListByUserId(String userId) {
		List<WishView> wishViewItemList = new ArrayList<>();
		
		// wish 아이템 가져오기 
		List<Wish> wishList = wishRepository.getByUserId(userId);
		
		// 반복문 순회 
		for (Wish wish: wishList) {
			WishView wishView = new WishView();
			
			User user = userBO.getUserByUserId(wish.getUserId());
			wishView.setUser(user);
			
			wishViewItemList.add(wishView);
		}
		return wishViewItemList;
	}
	 

	
}

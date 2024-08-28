package com.shoppingMall.mypage.entity;

import java.util.List;

import com.shoppingMall.user.entity.User;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
// user 1개와 매핑
public class WishView {
	
	private Wish wish;

	private User user;
	
	// 아이템 N개
	private List<WishItem> wishItemList;
	
}

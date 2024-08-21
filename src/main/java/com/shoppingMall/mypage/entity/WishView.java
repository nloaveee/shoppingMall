package com.shoppingMall.mypage.entity;

import java.util.List;


import com.shoppingMall.user.entity.User;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WishView {
	
	private User user;
	
	private Wish wish;
	
	private List<WishView> item;
	
	private List<WishView> itemOption;
}

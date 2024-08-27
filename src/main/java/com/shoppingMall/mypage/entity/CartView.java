package com.shoppingMall.mypage.entity;

import java.util.List;

import com.shoppingMall.user.entity.User;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.ToString;

// cart 1개와 매핑
@Data
@ToString

public class CartView {
	
	// cart 1개
	private Cart cart;
	
	// user 정보
	private User user;
	
	// 아이템 N개
	private List<CartItem> cartItemList;
	
}

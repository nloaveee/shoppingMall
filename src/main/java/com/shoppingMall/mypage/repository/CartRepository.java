package com.shoppingMall.mypage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.mypage.entity.Cart;
import com.shoppingMall.mypage.entity.CartItem;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	public Cart findByUserIdAndItemIdAndOptionId(String uerId, int itemId, int optionId);
	
}

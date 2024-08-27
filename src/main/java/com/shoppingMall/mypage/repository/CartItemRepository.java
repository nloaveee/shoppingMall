package com.shoppingMall.mypage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.mypage.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	public List<CartItem> findCartItemByCartId(int cartId);
	
}

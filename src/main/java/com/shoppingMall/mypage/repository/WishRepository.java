package com.shoppingMall.mypage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.mypage.entity.Cart;
import com.shoppingMall.mypage.entity.Wish;

public interface WishRepository extends JpaRepository<Wish, Integer>{

	public List<Wish> findByUserId(String userId);
	
	public List<Wish> getByUserId(String userId);
	
	public Wish findByUserIdAndItemIdAndOptionId(String uerId, int itemId, int optionId);
	
	public void deleteByUserId(String userId);
}

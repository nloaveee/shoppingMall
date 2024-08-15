package com.shoppingMall.mypage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.mypage.entity.Wish;

public interface WishRepository extends JpaRepository<Wish, Integer>{

	public List<Wish> findByUserId(String userId);
}

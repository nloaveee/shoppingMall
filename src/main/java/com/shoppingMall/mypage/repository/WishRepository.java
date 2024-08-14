package com.shoppingMall.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.mypage.entity.Wish;

public interface WishRepository extends JpaRepository<Wish, Integer>{

}

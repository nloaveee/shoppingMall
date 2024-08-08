package com.shoppingMall.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.user.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByUserId(String userId);
}

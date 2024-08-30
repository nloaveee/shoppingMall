package com.shoppingMall.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.shoppingMall.order.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{
	
	public List<Orders> findByUserId (String userId);

}

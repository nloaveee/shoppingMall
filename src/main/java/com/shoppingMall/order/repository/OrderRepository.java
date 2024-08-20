package com.shoppingMall.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}

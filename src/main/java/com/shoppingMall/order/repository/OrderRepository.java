package com.shoppingMall.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.order.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

}

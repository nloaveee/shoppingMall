package com.shoppingMall.mypage.entity;

import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class CartItem {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int cartId;
	 private int itemId;
	 private String option;
	 private int quantity;

}

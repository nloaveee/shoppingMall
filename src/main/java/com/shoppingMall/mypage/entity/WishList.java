package com.shoppingMall.mypage.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WishList {

	private String name;
	private String option;
	private int count;
	private int sale;
}

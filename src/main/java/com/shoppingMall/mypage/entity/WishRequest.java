package com.shoppingMall.mypage.entity;

import java.util.List;

import lombok.Data;

@Data
public class WishRequest {

	private List<WishList> wishList;
}

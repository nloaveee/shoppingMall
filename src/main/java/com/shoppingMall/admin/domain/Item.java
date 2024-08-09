package com.shoppingMall.admin.domain;

import java.time.LocalDateTime;

import groovy.transform.ToString;
import lombok.Data;

@Data
@ToString
public class Item {
	private int id;
	private String name;
	private String content;
	private int	price;
	private int	sale;
	private String category;
	private String imagePath;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}

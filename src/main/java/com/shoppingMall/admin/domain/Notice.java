package com.shoppingMall.admin.domain;


import java.time.LocalDateTime;

import groovy.transform.ToString;
import lombok.Data;

@ToString
@Data
public class Notice {
	private int id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}

package com.shoppingMall.mypage.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name="wish")
@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wish {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="itemId")
	private int itemId;
	
	@Column(name="optionId")
	private int optionId;
	
	@Column(name="createdAt")
	@CurrentTimestamp
 	private LocalDateTime createdAt;

}

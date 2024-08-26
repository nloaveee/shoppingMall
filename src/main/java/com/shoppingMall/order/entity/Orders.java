package com.shoppingMall.order.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="orders")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@DynamicUpdate
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="itemId")
	private int itemId;

	@Column(name="optionId")
	private int optionId;
	
	private int price;
	
	@Column(name="itemCount")
	private int itemCount;
	
	@Column(name="createdAt")
	@CurrentTimestamp
	private LocalDateTime createdAt;
}

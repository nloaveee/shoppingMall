package com.shoppingMall.user.entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name="user")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="userPwd")
	private String userPwd;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private boolean agreement;
	
	@Column(name="tempPwd")
	private boolean tempPwd;
	
	private boolean closing;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(name="closedAt")
	@CurrentTimestamp
	private LocalDateTime closedAt;

}

package com.shoppingMall.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.cs.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer>{
	
	public Inquiry findById(int id);

}

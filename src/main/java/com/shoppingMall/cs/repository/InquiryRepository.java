package com.shoppingMall.cs.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.cs.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer>{
	
	 public Inquiry findById(int id);
	    
    public List<Inquiry> findAll();
    
}

package com.shoppingMall.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingMall.cs.entity.InquiryAnswer;

public interface AnswerRepository extends JpaRepository<InquiryAnswer, Integer>{
	public InquiryAnswer findByInquiryId(int inquiryId);
}

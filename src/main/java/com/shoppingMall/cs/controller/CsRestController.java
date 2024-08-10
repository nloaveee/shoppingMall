package com.shoppingMall.cs.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingMall.cs.bo.InquiryBO;
import com.shoppingMall.cs.entity.Inquiry;

import groovyjarjarantlr4.v4.parse.BlockSetTransformer.setAlt_return;

@RequestMapping("/shop/main/inquiry")
@RestController
public class CsRestController {
	
	@Autowired
	private InquiryBO inquiryBO;
	
		/**
		 * 상품문의 작성
		 * @param title
		 * @param name
		 * @param content
		 * @param file
		 * @return
		 */
		@PostMapping("/create")
		public Map<String, Object> inquiryCreate(
				@RequestParam("title") String title,
				@RequestParam("name") String name,
				@RequestParam("content") String content,
				@RequestParam(value = "file", required = false) MultipartFile file
				) {
			
			// db insert
			Inquiry inquiry = inquiryBO.addInquiry(title,name,content,file);
			
			
			Map<String, Object> result =new HashMap<>();
			if (inquiry != null) {
				result.put("code", 200);
			} else {
				result.put("error_message", "상품문의 작성에 실패했습니다.");
			}
			return result;
		}
		
		/**
		 * 상품문의 수정 
		 * @param inquiryId
		 * @param content
		 * @param file
		 * @return
		 */
		@PostMapping("/update")
		public Map<String, Object> inquiryUpdate(
				@RequestParam("name") String name,
				@RequestParam("inquiryId") int inquiryId,
				@RequestParam("content") String content,
				@RequestParam(value = "file", required = false) MultipartFile file){
			
			// db update
			Inquiry inquiry = inquiryBO.updateInquiryByinquiryId(name, inquiryId, content, file);
			
			// 응답값
			Map<String, Object> result = new HashMap<>();
			if (inquiry != null) {
				result.put("code", 200); 
			} else {
				result.put("error_message", "공지사항 수정에 실패했습니다");
			}
			return result;
		}
		
		@DeleteMapping("/delete") 
		public Map<String, Object> inquiryDelete(
				@RequestParam("inquiryId") int inquiryId) {
		
			// DB delete
			inquiryBO.deleteInquiryByinquiryId(inquiryId);
			
			// 응답값
			Map<String, Object> result = new HashMap<>();
			result.put("code", 200);
			result.put("error_message", "공지사항 수정에 실패했습니다");
			return result;
			
			
		}
}

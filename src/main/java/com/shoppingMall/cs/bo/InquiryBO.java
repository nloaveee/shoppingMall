package com.shoppingMall.cs.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingMall.common.FileManagerService;
import com.shoppingMall.cs.entity.Inquiry;
import com.shoppingMall.cs.repository.InquiryRepository;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InquiryBO {
	@Autowired
	private InquiryRepository inquiryRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public Inquiry addInquiry(String title, String name, String content, MultipartFile file) {
		
		String imagePath = null;
		
		if (file != null) {
			// 업로드 할 이미지가 있을 때에만 업로드
			imagePath = fileManagerService.uploadFileInquiry(name,file);
		}
		
		return inquiryRepository.save(Inquiry.builder()
				.title(title)
				.name(name)
				.content(content)
				.image(imagePath)
				.build());
	}
	
	public List<Inquiry> getInquiryList() {
		return inquiryRepository.findAll();
	}
	
	public Inquiry getInquiryById(int id) {
		return inquiryRepository.findById(id);
	}
	
	public Inquiry updateInquiryByinquiryId(String name, int inquiryId, String content, MultipartFile file) {
		
		Inquiry inquiry = inquiryRepository.findById(inquiryId);
		
		String imagePath = null;
		
		if (file != null) {
			// 업로드 할 이미지가 있을 때에만 업로드
			imagePath = fileManagerService.uploadFileInquiry(name,file);
			
			// 업로드 성공 시 (null 아님) 기존 이미지가 있으면 제거 
			if (imagePath != null && inquiry.getImage() != null) {
				// 폴더와 기존이미지 제거(서버에서)
				fileManagerService.deleteFile(inquiry.getImage());
			}
		}
		
		if (inquiry != null) {
			inquiry.setContent(content);
			inquiry.setImage(imagePath);			
		    return inquiryRepository.save(inquiry);			
		} else {
			return null;
		}
				
	}
	
	public void deleteInquiryByinquiryId(int inquiryId) {
		
		Inquiry inquiry = inquiryRepository.findById(inquiryId);
		
		if (inquiry == null) {
			log.info("[글 삭제] post is null. inquiryId: {}\", inquiryId");			
			return;
		}
				
		
	   inquiryRepository.deleteById(inquiryId);
		
		//이미지가 존재하면 삭제, 삭제된 행도 1일 때
		if (inquiry.getImage() != null) {
			fileManagerService.deleteFile(inquiry.getImage());
		} 		
	}
}


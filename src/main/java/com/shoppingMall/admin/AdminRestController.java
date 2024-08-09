package com.shoppingMall.admin;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.bo.NoticeBO;



@RestController
@RequestMapping("/shop/admin")
public class AdminRestController {
	
	@Autowired
	private NoticeBO noticeBO;
	
	@Autowired
	private ItemBO itemBo;

	/**
	 * 공지사항 작성
	 * @param title
	 * @param content
	 * @return
	 */
	@PostMapping("/notice-create")
	public Map<String, Object> noticeCreate(
			@RequestParam("title") String title,
			@RequestParam("content") String content) {
		
		// db insert
		noticeBO.addNotice(title,content);
		
		// 응답값 
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("error_message", "공지사항 작성에 실패했습니다");
		return result;
	}
	
	/**
	 * 공지사항 수정  
	 * @param id
	 * @param title
	 * @param content
	 * @return
	 */
	@PostMapping("/notice-update")
	public Map<String, Object> noticeUpdate(
			@RequestParam("id") int id, 
			@RequestParam("title") String title,
			@RequestParam("content") String content){
		
		// db update
		noticeBO.updateNoticeByNoticeId(id, title, content);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("error_message", "공지사항 수정에 실패했습니다");
		return result;
	}
	
	
	/**
	 * 공지사항 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("/notice-delete")
	public Map<String, Object> noticeDelete(
			@RequestParam("noticeId") int id) {
		
		// DB delete
		noticeBO.deleteNoticeByNoticeId(id);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("error_message", "공지사항 수정에 실패했습니다");
		return result;
		
	}
	
	
	// 상품 등록
	@PostMapping("/item/add")
	public Map<String, Object> itemAdd(
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("sale") int sale,
			@RequestParam("category") String category,
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file){
		
		// DB insert 		
		boolean item = itemBo.addItem(name, content, price, sale, category, file);
		 
		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (item == true) {
			result.put("code", 200); 
		} else {
			result.put("error_message", "이미 등록된 상품입니다. 다른 상품을 등록해주세요.");
		}
		return result;
	}
	
	
	// 상품 검색 
//	@PostMapping("/item/search")
//	public Map<String, Object> itemSearch(
//			@RequestParam("name") String name) {
//		
//		// db 조회
//		List<Item> searchItemList = itemBo.getItemListByName(name);
//		
//		// 응답값
//		Map<String, Object> result = new HashMap<>();
//		if (searchItemList != null) {
//			result.put("code", 200);
//		} else {
//			result.put("error_message", "해당하는 상품이 없습니다.");
//		}
//		
//		return result;
//	}
 
}

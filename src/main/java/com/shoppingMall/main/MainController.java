package com.shoppingMall.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;

import jakarta.servlet.http.HttpSession;

import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/shop")
public class MainController {
	
	@Autowired
	private ItemBO itemBO;

	// 메인 화면
	@GetMapping("/main")
	public String shopMain(
			@RequestParam(value="prevId", required = false) Integer prevIdParam,
			@RequestParam(value="nextId", required = false) Integer nextIdParam,
			Model model,HttpSession session) {
		
		String userId = (String)session.getAttribute("userId");
		
		List<Item> itemList = itemBO.getItemList();		
		model.addAttribute("itemList",itemList);
		
		// DB 조회 => item 목록
		List<Item> pagingItemList = itemBO.getItemList(prevIdParam, nextIdParam);
		int prevId = 0;
		int nextId = 0;
		if (pagingItemList.isEmpty() == false) { // 글 목록이 비어있지 않을 때 페이징 정보 세팅 
			prevId = pagingItemList.get(0).getId(); // 첫번째칸 id
			nextId = pagingItemList.get(pagingItemList.size() - 1).getId(); // 마지막칸 id 	
			
			// 이전 방향의 끝인가? => 끝이면 0으로 세팅 
			// prevId == 유저가 쓴 post 테이블의 제일 큰 숫자와 같으면 이전의 끝페이지 
			if (itemBO.isPrevLastPage(prevId)) { // true가 온 경우
				prevId = 0;
			}
			
			// 다음 방향의 끝인가? => 끝이면 0으로 세팅
			// nextId == 테이블의 제일 작은 숫자와 같으면 다음의 끝페이지 
			if (itemBO.isNextLastPage(nextId)) { // true가 온 경우 
				nextId = 0;
			}
		}
	
		model.addAttribute("prevId",prevId);
		model.addAttribute("nextId",nextId);
		
		return "main/shopMain";
	}
	
}

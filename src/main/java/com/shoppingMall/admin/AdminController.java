package com.shoppingMall.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.bo.NoticeBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.admin.domain.Notice;
import com.shoppingMall.cs.bo.InquiryBO;
import com.shoppingMall.cs.entity.Inquiry;
import com.shoppingMall.cs.entity.InquiryAnswer;
import com.shoppingMall.order.bo.OrderBO;
import com.shoppingMall.order.entity.Orders;
import com.shoppingMall.user.bo.UserBO;
import com.shoppingMall.user.entity.User;


@Controller
@RequestMapping("/shop/admin")
public class AdminController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private NoticeBO noticeBO;
	
	@Autowired
	private ItemBO itemBO;
	
	@Autowired
	private InquiryBO inquiryBO;
	
	@Autowired
	private OrderBO orderBO;
	
	
	// 메인 화면
	@RequestMapping("/main")
	public String main() {
		return "admin/adminMain";
	}
	
	// 회원 관리 페이지 
	@GetMapping("/user-list-view")
	public String userListView(Model model) {
		
		// db 조회 
		List<User> userList = userBO.getUsers();
		
		// model 담기 
		model.addAttribute("userList",userList);
		
		return "admin/user/list";
	}
	
	// 공지사항 목록 페이지 
	@GetMapping("/notice-list-view")
	public String noticeListView(Model model) {
		
		// db 조회
		List<Notice> noticeList = noticeBO.getNoticeList();
		
		model.addAttribute("noticeList" , noticeList);
		return "admin/notice/list";
	}
	
	// 공지사항 글작성 페이지 
	@GetMapping("/notice-create-view") 
	public String noticeCreateView() {
		return "admin/notice/create";
	}
	
	// 공지사항 수정 페이지 
	@GetMapping("/notice-detail-view")
	public String noticeDetailView(
			@RequestParam("noticeId") int id, 
			Model model) {
		
		Notice notice = noticeBO.getNoticeByNoticeId(id);
		
		model.addAttribute("notice",notice);
		
		return "admin/notice/detail";
	}
	
	// 상품 등록 페이지 
	@GetMapping("/item-add-view")
	public String itemAddView() {
		return "admin/item/add";
	}
	
	// 상품 목록 페이지 
	@GetMapping("/item-list-view")
	public String itemListView(Model model) {
		
		List<Item> itemList = itemBO.getItemList();
		
		model.addAttribute("itemList", itemList);
		
		return "admin/item/list";
	}

	// 상품 상세 페이지 
	@GetMapping("/item-detail-view")
	public String itemDetailView(
			@RequestParam("name") String name,
			Model model) {
		
		Item itemInfo = itemBO.getItemByName(name);
		int itemId = itemInfo.getId();
		
		List<ItemOption> optionList = itemBO.getItemOptionListByItemId(itemId);	
		
		model.addAttribute("itemInfo",itemInfo);
		model.addAttribute("optionList",optionList);
		
		return "admin/item/detail";
	}
	
		
	// 상품 검색 페이지 
	@GetMapping("/item-search-view") 
	public String itemSearchView(Model model,
			@RequestParam("name") String name) {
		
		List<Item> searchItemList = itemBO.getItemListByName(name);
		
		model.addAttribute("searchItemList", searchItemList);
		
		return "admin/item/search";
	}
	
	// 문의 사항 
	@GetMapping("/inquiry-list-view")
	public String inquiryListView(Model model) {
		
		List<Inquiry> inquiryList = inquiryBO.getInquiryList();
		
		model.addAttribute("inquiryList", inquiryList);
		
		return "admin/inquiry/list";
	}
	
	// 문의 사항 상세 페이지
	@GetMapping("/inquiry-detail-view")
	public String inquiryDetailView(
			@RequestParam("inquiryId") int id,
			Model model) {

		Inquiry inquiry = inquiryBO.getInquiryById(id);
		InquiryAnswer answer = inquiryBO.getInquiryAnswer(id);
		
		model.addAttribute("inquiry", inquiry);
		model.addAttribute("answer",answer);
		
		return "admin/inquiry/detail";
	}
	
	// 주문
	@GetMapping("/order-list-view")
	public String orderListView(Model model) {
		List<Orders> orderList = orderBO.getOrderList();
		
		model.addAttribute("orderList",orderList);
		
		return "admin/order/list";
	}
}

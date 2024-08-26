package com.shoppingMall.mypage.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingMall.admin.bo.ItemBO;

import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.mypage.entity.Cart;
import com.shoppingMall.mypage.entity.CartItem;
import com.shoppingMall.mypage.repository.CartRepository;


@Service
public class CartBO {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ItemBO itemBO;
	
	public Map<String, Object> addCart(String userId, List<CartItem> cartList) {
		
		Map<String, Object> result = new HashMap<>(); 
	        for (CartItem item : cartList) {
	        	
	        	String[] optionPart = item.getOption().split("\\/");
				String color = optionPart.length>0 ? optionPart[0].trim() : "";
				String size = optionPart.length>1 ? optionPart[1].trim() : "";
				ItemOption option = itemBO.getItemOptionByItemIdColorSize(item.getItemId(),color , size);
	        	
	            // 장바구니에 이미 같은 아이템이 있는지 확인
	            Cart existingCart = cartRepository.findByUserIdAndItemIdAndOptionId(userId, item.getItemId(),option.getId());
	            
	            if (existingCart != null) {
	                result.put("code", 501); // 이미 장바구니에 있는 경우
	                return result;
	            }

	            // 새로운 Cart 객체 생성
	            Cart cart = Cart.builder()
	                    .userId(userId)
	                    .itemId(item.getItemId())
	                    .optionId(option.getId())
	                    .quantity(item.getQuantity())
	                    .build();

	            // DB에 저장
	            cartRepository.save(cart);
	        }
	        
	        result.put("code", 200); // 성공
			return result;
		}
	
}

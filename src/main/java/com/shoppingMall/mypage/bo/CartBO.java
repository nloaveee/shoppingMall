package com.shoppingMall.mypage.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.mypage.entity.Cart;
import com.shoppingMall.mypage.entity.CartItem;
import com.shoppingMall.mypage.entity.CartView;
import com.shoppingMall.mypage.repository.CartRepository;
import com.shoppingMall.user.bo.UserBO;
import com.shoppingMall.user.entity.User;


@Service
public class CartBO {
	
	@Autowired
	private CartRepository cartRepository;
	
	
	@Autowired
	private ItemBO itemBO;
	
	@Autowired
	private UserBO userBO;
	
	
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
	
	public List<Cart> getCartList() {
		return cartRepository.findAll();
	}
	
	
	public List<CartView> generateCartViewList(String userId) {
	    List<CartView> cartViewList = new ArrayList<>();
	    
	    // 사용자 ID에 해당하는 모든 Cart 가져오기
	    List<Cart> cartList = getCartItemByUserId(userId);
	    
	    for (Cart cart : cartList) {
	        CartView cartView = new CartView();
	        
	        cartView.setCart(cart);
	        
	        User user = userBO.getUserByUserId(userId);
	        cartView.setUser(user);
	        
	        // CartItem 리스트 생성
	        List<CartItem> cartItemList = new ArrayList<>();
	        
	        // 현재 Cart에 해당하는 CartItem 생성
	        CartItem cartItemObj = new CartItem();
	        cartItemObj.setCartId(cart.getId());
	        
	        // Item 정보 설정
	        Item item = itemBO.getItemByid(cart.getItemId());
	        cartItemObj.setItem(item);
	        
	        // Option 정보 설정
	        ItemOption option = itemBO.getitemOptionByOptionId(cart.getOptionId()); // Option 정보 가져오기
	        cartItemObj.setItemOption(option);
	        
	        cartItemObj.setQuantity(cart.getQuantity());
	        
	        cartItemList.add(cartItemObj);
	        
	        cartView.setCartItemList(cartItemList);
	        
	        cartViewList.add(cartView);
	    }
	    
	    return cartViewList;
	}

	public List<Cart> getCartItemByUserId(String userId) {
	    return cartRepository.findByUserId(userId);
	}
		
	
	public void deleteCartItem(int cartId) {
		cartRepository.deleteById(cartId);
	}
	
	public Map<String, Object> updateCartItemQuantity (int cartId, int quantity) {
		
		Map<String, Object> result = new HashMap<>(); 
		
		Cart cart = cartRepository.findById(cartId);
		
		if (cart != null) {
			cart.setQuantity(quantity);
			cartRepository.save(cart);
			result.put("code", 200);
			return result;
		} else {
			result.put("code", 500);
		}
		return result;		
		
	}
	


}

package com.shoppingMall.order.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingMall.admin.bo.ItemBO;

import com.shoppingMall.admin.domain.ItemOption;

import com.shoppingMall.order.entity.OrderItem;

import com.shoppingMall.order.entity.Orders;
import com.shoppingMall.order.repository.OrderRepository;

@Service
public class OrderBO {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ItemBO itemBO;
	
	
	public Map<String, Object> addOrder(String userId, List<OrderItem> orderList) {
			
			Map<String, Object> result = new HashMap<>(); 
		        for (OrderItem item : orderList) {
		        	
		        	String[] optionPart = item.getOption().split("\\/");
					String color = optionPart.length>0 ? optionPart[0].trim() : "";
					String size = optionPart.length>1 ? optionPart[1].trim() : "";
					ItemOption option = itemBO.getItemOptionByItemIdColorSize(item.getItemId(),color , size);
					
					
		            // 새로운 Cart 객체 생성
		            Orders order = Orders.builder()
		            		.userId(userId)
		            		.itemId(item.getItemId())
		            		.optionId(option.getId())
		            		.price(item.getSale())
		            		.itemCount(item.getCount())
		                    .build();
		            
		            // DB에 저장
		            orderRepository.save(order);
	
		        }
		        		        
		        result.put("code", 200); // 성공
				return result;
			}
		

//	public Map<String, Object> addCartOrder(String userId, int itemId, int optionId, int quantity) {
//		
//		Map<String, Object> result = new HashMap<>(); 
//				
//		orderRepository.save(Orders.builder()
//				.userId(userId)
//				.itemId(itemId)
//				.optionId(optionId)
//				.price()
//				.itemCount(quantity)
//				.build());
//	}
// 	
}

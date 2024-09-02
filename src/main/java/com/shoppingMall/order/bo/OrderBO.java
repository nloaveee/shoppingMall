package com.shoppingMall.order.bo;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;

import com.shoppingMall.order.entity.OrderItem;
import com.shoppingMall.order.entity.OrderView;
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
					
					// itemOption 가져오기
					ItemOption option = itemBO.getItemOptionByItemIdColorSize(item.getItemId(),color , size);			
					
			        // Item 가져오기
			        Item orderItem = itemBO.getItemById(item.getItemId());
			        item.setItem(orderItem);
					
					// 재고 확인
					int remainingStock = option.getStock() - item.getCount();
					if (remainingStock < 0) {
						result.put("code", 400); // 재고 부족
						result.put("message", "현재 상품 재고가 0으로 주문이 불가합니다.");
						return result;
					}
											         
		            Orders order = Orders.builder()
		            		.userId(userId)
		            		.itemId(item.getItemId())
		            		.optionId(option.getId())
		            		.price(item.getSale())
		            		.itemCount(item.getCount())
		                    .build();
		            
		            // DB에 저장
		            orderRepository.save(order);
		            
		    		// 재고 업데이트
		    		option.setStock(remainingStock);
		    			    	
		    		itemBO.updateItemOptionStock(option, remainingStock);
	
		        }
		        		        
		        result.put("code", 200); // 성공
				return result;
			}
	
	
	public List<Orders> getOrdersByUserId(String userId) {
		return orderRepository.findByUserId(userId);
	}


	public List<Orders> getOrderList() {
		return orderRepository.findAll();
	}
}

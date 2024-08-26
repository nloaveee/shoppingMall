package com.shoppingMall.order.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;

import com.shoppingMall.order.entity.OrderItem;

import com.shoppingMall.order.entity.OrderRequest;
import com.shoppingMall.order.repository.OrderRepository;

@Service
public class OrderBO {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ItemBO itemBO;
	
//	
//	public void addOrder(String userId, List<OrderRequest> orderRequest) {
//		
//		List<OrderItem> orders = new ArrayList<>();
//		
//		for(OrderRequest order: orderRequest) {						
//			Item item = itemBO.getItemByName();
//			
//			String[] optionPart = order.getOption().split("\\/");
//			String color = optionPart.length > 0 ? optionPart[0].trim() : "";
//			String size = optionPart.length > 1 ? optionPart[1].trim() : "";
//			ItemOption option = itemBO.getItemOptionByItemIdColorSize(item.getId(),color , size);
//			
//			OrderList 
//			
//			orderRepository.save(Order.builder()
//					.userId(userId)
//					.itemId(option.getItemId())
//					.optionId(option.getId())
//					.price(order.getSale())
//					.itemCount(order.getCount())
//					.build());			
//	    }
//		
//	}
//	

	
}

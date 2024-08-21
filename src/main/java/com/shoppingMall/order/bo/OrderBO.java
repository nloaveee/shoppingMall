package com.shoppingMall.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.order.entity.Order;
import com.shoppingMall.order.repository.OrderRepository;

@Service
public class OrderBO {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ItemBO itemBO;

//	public Order addOrder (String userId, List<Item> orderList, int totalPrice) {
//		
//		for (Item orderItem: orderList) {
//			
//			Item item = itemBO.getItemByName();
//			ItemOption option = itemBO.getItemOptionByItemId(item.getId());
//			
//			orderRepository.save(Order.builder()
//					.userId(userId)
//					.itemId(item.getId())
//					.optionId(option.getId())
//					.totalPrice(totalPrice)
//					.itemCount(orderItem.getItemCount())
//					.build());
//			
//			
//			orderItem.getName();
//		}
//		
//		orderRepository.save(Order.builder()
//				.userId(userId)
//				.itemId(totalPrice)
//				.build());
//		
//		
//	}
}

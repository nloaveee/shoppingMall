package com.shoppingMall.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingMall.admin.bo.ItemBO;
import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.order.entity.Order;
import com.shoppingMall.order.entity.OrderList;
import com.shoppingMall.order.repository.OrderRepository;

@Service
public class OrderBO {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ItemBO itemBO;
	
	public void addOrder(String userId, List<OrderList> orderList) {
		
		for(OrderList order: orderList) {						
			Item item = itemBO.getItemByName(order.getName());
			
			String[] optionPart = order.getOption().split("\\/");
			String color = optionPart.length>0 ? optionPart[0].trim() : "";
			String size = optionPart.length>1 ? optionPart[1].trim() : "";
			ItemOption option = itemBO.getItemOptionByItemIdColorSize(item.getId(),color , size);
			
			orderRepository.save(Order.builder()
					.userId(userId)
					.itemId(item.getId())
					.optionId(option.getId())
					.price(order.getSale())
					.itemCount(order.getCount())
					.build());
			
	    }
		
	}
	

	
}

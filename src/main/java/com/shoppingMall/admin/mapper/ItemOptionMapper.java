package com.shoppingMall.admin.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingMall.admin.domain.ItemOption;

@Mapper
public interface ItemOptionMapper {

	public boolean insertItemOption(
			@Param("itemId") int itemId,
			@Param("size") String size,
			@Param("color") String color,
			@Param("stock") int stock);
	
	public List<ItemOption> selectOptionList(
			@Param("itemId") int itemId);
	
	public ItemOption selectOptionById(int itemId);
	
	public List<ItemOption> selectOptionByItemIdColorSize(
			@Param("itemId") int itemId,
			@Param("color") String color,
			@Param("size") String size);
	
}

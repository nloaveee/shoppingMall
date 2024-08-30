package com.shoppingMall.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shoppingMall.admin.domain.Item;



@Mapper
public interface ItemMapper {

	public boolean insertItem(
			@Param("name") String name,
			@Param("content") String content,
			@Param("price") int price,
			@Param("sale") int sale,
			@Param("category") String category,			
			@Param("imagePath") String imagePath);
	
	public Item selectItemByName(String name);
	
	public List<Item> selectItemList();
	
	public List<Item> selectItemListByName(String name);
	
	public Item selectItemById(int id);
	
	public List<Item> selectItemListAll(
			@Param("standardId") Integer standardId,
			@Param("direction") String direction,
			@Param("limit") int limit);
	
	// 페이징 마지막 검사
	public int selectIdAsSort(
			@Param("sort") String sort);
	

}

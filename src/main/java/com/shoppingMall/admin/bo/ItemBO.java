package com.shoppingMall.admin.bo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.domain.ItemOption;
import com.shoppingMall.admin.mapper.ItemMapper;
import com.shoppingMall.admin.mapper.ItemOptionMapper;
import com.shoppingMall.common.FileManagerService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemBO {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemOptionMapper itemOptionMapper;
	
	@Autowired
	private FileManagerService fileManagerService;

	// 상품 등록 
	public boolean addItem(String name, String content, int price, int sale, String category, MultipartFile file) {
		String imagePath = null;
		
		imagePath = fileManagerService.uploadFileByItems(name, file);
		
		Item item = itemMapper.selectItemByName(name);
		if (item == null) {
			itemMapper.insertItem(name, content, price, sale, category, imagePath);
			return true;
		} else {
			return false;
		}
	}
	
	public List<Item> getItemList() {
		return itemMapper.selectItemList();
	}
	
	public List<Item> getItemListByName(String name) {
		
		return itemMapper.selectItemListByName(name);
	}	
	
	public Item getItemByName(String name) {
		return itemMapper.selectItemByName(name);
	}
	
	// 상품 옵션 저장 
	public boolean addItemOption(int itemId, String name, String size, String color, int stock) {
		
		List<ItemOption> optionList = itemOptionMapper.selectOptionList(itemId);
		
        for (ItemOption option : optionList) {
        	if (option.getColor().equals(color) && option.getSize().equals(size)) {
        		return false;
        	}
        }
        
        itemOptionMapper.insertItemOption(itemId, size, color, stock);
        
        return true;
	}
	

	public List<ItemOption> getItemOptionListByItemId(int itemId) {
	    
	    List<ItemOption> optionList = itemOptionMapper.selectOptionList(itemId);
	    
	    // COLOR 속성으로 알파벳 순서로 정렬
	    optionList.sort((o1, o2) -> o1.getColor().compareToIgnoreCase(o2.getColor()));
	    
	    return optionList;
	}

	public Item getItemById(int itemId) {
		return itemMapper.selectItemById(itemId);
	}
	
	public ItemOption getItemOptionByItemId(int itemId) {
		return itemOptionMapper.selectOptionById(itemId);
	}
	
	public List<ItemOption> getItemOptionListByItemIdColorSize(int itemId, String color, String size) {
		return itemOptionMapper.selectOptionListByItemIdColorSize(itemId,color,size);
	}
	
	public ItemOption getItemOptionByItemIdColorSize(int itemId, String color, String size) {
		return itemOptionMapper.selectOptionByItemIdColorSize(itemId,color,size);
	}
	
	public ItemOption getitemOptionByOptionId(int optionId) {
		return itemOptionMapper.selectOptionByOptionId(optionId);
	}
	
	
	
	// 페이징
	private static final int ITEM_MAX_SIZE = 12;
	public List<Item> getItemList(Integer prevId,Integer nextId) {
		
		Integer standardId = null; // 기준 
		String direction = null; // 방향 
		
		if (prevId != null) { // 2) 이전			
			standardId = prevId;
			direction = "prev";
			
			List<Item> itemList = itemMapper.selectItemListAll(standardId, direction, ITEM_MAX_SIZE);
			// [5 6 7] -> [7 6 5]
			Collections.reverse(itemList); 
					
			return itemList;
					
		} else if ( nextId != null) { // 1) 다음
			standardId = nextId;
			direction = "next";
		}
		
		// 3) 페이징 x, 1) 다음
		return itemMapper.selectItemListAll(standardId, direction, ITEM_MAX_SIZE);
	}
	
	// 이전 페이지의 마지막인가?
	public boolean isPrevLastPage(int prevId) {
		// 제일 큰 숫자를 가져오기 위해서 desc 정렬을 한다.
		int maxPostId = itemMapper.selectIdAsSort("DESC");
		return maxPostId == prevId; // 같으면 마지막
	}
	
	// 다음 페이지의 마지막인가?
	public boolean isNextLastPage(int nextId) {
		// 제일 작은 숫자를 가져오기 위해서 asc 정렬을 한다.
		int minPostId = itemMapper.selectIdAsSort("ASC"); 
		return minPostId == nextId;
	}
	
	
}

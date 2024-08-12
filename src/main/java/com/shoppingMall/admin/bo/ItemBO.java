package com.shoppingMall.admin.bo;

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
	
	// 상품 옵션 리스트 select
	public List<ItemOption> getItemOptionListByItemId(int itemId) {
		
		List<ItemOption> optionList = itemOptionMapper.selectOptionList(itemId);
		
		// COLOR 속성으로 알파벳 순서로 정렬
		optionList.sort((o1, o2) -> o1.getColor().compareToIgnoreCase(o2.getColor()));
		
		return optionList;
	}
	
	public Item getItemByid(int itemId) {
		return itemMapper.selectItemById(itemId);
	}
	
	public ItemOption getItemOptionByItemId(int itemId) {
		return itemOptionMapper.selectOptionById(itemId);
	}
}

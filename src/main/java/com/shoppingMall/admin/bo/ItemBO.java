package com.shoppingMall.admin.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingMall.admin.domain.Item;
import com.shoppingMall.admin.mapper.ItemMapper;
import com.shoppingMall.common.FileManagerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemBO {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private FileManagerService fileManagerService;

	// 상품 등록 
	public boolean addItem(String name, String content, int price, int sale, String category, MultipartFile file) {
		String imagePath = null;
		
		imagePath = fileManagerService.uploadFile(file, category);
		
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
}

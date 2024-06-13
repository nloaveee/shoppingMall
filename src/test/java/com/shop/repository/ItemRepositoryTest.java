package com.shop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;

	public void createItemList() {
		for (int i = 1; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100 + i);
			item.setRegTime(LocalDateTime.now());
			item.setUpDateTime(LocalDateTime.now());
			itemRepository.save(item);
		}
	}

	@Test
	@DisplayName("상품 생성 테스트")
	public void createItemTest() {
		Item item = Item.builder().itemNm("테스트 상품").price(10000).stockNumber(100).itemDetail("테스트 상품 상세 설명")
				.itemSellStatus(ItemSellStatus.SELL).regTime(LocalDateTime.now()).upDateTime(LocalDateTime.now())
				.build();
		System.out.println("================item: " + item);
		Item savedItem = itemRepository.save(item);
		System.out.println("================savedItem: " + savedItem);
	}

	@Test
	@DisplayName("상품명 조회 테스트")
	void findByItemNm() {
		createItemList();

		List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
		for (Item item : itemList) {
			System.out.println(item);
		}
	}
}

package com.shop.repository;

import java.time.LocalDateTime;

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

	@Test
	@DisplayName("상품 생성 테스트")
	public void createItemTest() {
		Item item = Item.builder().itemName("테스트 상품").price(10000).stockNumber(100).itemDetail("테스트 상품 상세 설명")
				.itemSellStatus(ItemSellStatus.SELL).regTime(LocalDateTime.now()).upDateTime(LocalDateTime.now())
				.build();
		System.out.println("================item: " + item);
		Item savedItem = itemRepository.save(item);
		System.out.println("================savedItem: " + savedItem);

	}
}

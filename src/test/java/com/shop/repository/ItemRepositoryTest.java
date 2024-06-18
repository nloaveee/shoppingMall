package com.shop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.QItem;

import jakarta.persistence.EntityManager;

@SpringBootTest
class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private EntityManager em;

	public void createItemList() {
		for (int i = 1; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpDateTime(LocalDateTime.now());
			Item savedItem = itemRepository.save(item);
		}
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

	@Test
	@DisplayName("OR 테스트")
	public void findByItemNmOrItemDetail() {
		createItemList();

		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품2", "테스트 상품 상세 설명8");
		for (Item item : itemList) {
			System.out.println(item);
		}
	}

	@Test
	@DisplayName("OrderBy 테스트")
	public void findByPriceLessThanOrderByPriceDescTest() {
		createItemList();

		List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
		for (Item item : itemList) {
			System.out.println(item);
		}
	}

	@Test
	@DisplayName("JPQL 테스트")
	public void findByDetail() {
		createItemList();

		List<Item> itemList = itemRepository.findByDetail("1");
		for (Item item : itemList) {
			System.out.println(item);
		}
	}

	@Test
	@DisplayName("Native 테스트")
	public void findByDetailNative() {
		createItemList();

		List<Item> itemList = itemRepository.findByDetailNative("1");
		for (Item item : itemList) {
			System.out.println(item);
		}
	}

	@Test
	@DisplayName("querydsl 테스트")
	public void querydslTest() {
		createItemList();

		JPAQueryFactory query = new JPAQueryFactory(em);
		QItem qItem = QItem.item;

		List<Item> itemList = query.selectFrom(QItem.item).where(QItem.item.itemSellStatus.eq(ItemSellStatus.SELL))
				.where(QItem.item.itemDetail.like("%" + "1" + "%")).orderBy(QItem.item.price.desc()).fetch();

		for (Item item : itemList) {
			System.out.println(item);
		}

	}

	public void createItemList2() {
		for (int i = 1; i <= 5; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpDateTime(LocalDateTime.now());
			itemRepository.save(item);
		}

		for (int i = 6; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpDateTime(LocalDateTime.now());
			itemRepository.save(item);
		}
	}

	@Test
	@DisplayName("querydsl 테스트2")
	public void querydslTest2() {
		createItemList2();

		BooleanBuilder builder = new BooleanBuilder();
		String itemDetail = "테스트";
		int price = 10004;
		String itemSellStatus = "SELL";

		QItem item = QItem.item;

		builder.and(item.itemDetail.like("%" + itemDetail + "%"));
		builder.and(item.price.gt(price));

		if (StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)) {
			builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
		}

		Pageable pageable = PageRequest.of(0, 5);

		Page<Item> page = itemRepository.findAll(builder, pageable);
		List<Item> content = page.getContent();

		for (Item resultItem : content) {
			System.out.println(resultItem);
		}

	}
}

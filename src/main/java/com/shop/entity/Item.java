package com.shop.entity;

import java.time.LocalDateTime;

import com.shop.constant.ItemSellStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id; // 상품 아이디

	@Column(nullable = false, length = 50)
	private String itemName; // 상품 이름

	private int price; // 가격

	private int stockNumber; // 재고 수량

	@Lob // 큰 자료형을 관리하게 해줌
	@Column(nullable = false)
	private String itemDetail; // 상품 상세 설명

	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus; // 상품 판매 상태

	private LocalDateTime regTime; // 등록 시간

	private LocalDateTime upDateTime; // 수정 시간

}

package com.shop.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {

	private Long id; // 상품 아이디

	private String itemNm; // 상품 이름

	private int price; // 가격

	private int stockNumber; // 재고 수량

	private String itemDetail; // 상품 상세 설명

	private String itemSellStatus; // 상품 판매 상태

	private LocalDateTime regTime; // 등록 시간

	private LocalDateTime upDateTime; // 수정 시간

}

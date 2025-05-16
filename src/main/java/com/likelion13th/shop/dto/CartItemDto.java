package com.likelion13th.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 장바구니에 담을 상품의 아이디와 수량
public class CartItemDto {
    private Long itemId;
    private int count;
}
package com.likelion13th.shop.dto;

import com.likelion13th.shop.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.HashMap;

@Getter
@Setter
public class OrderItemDto {
    private Long itemId;
    private String itemName;
    private Integer itemPrice;
    private Integer count;
    private int totalPrice;

    private static ModelMapper modelMapper = new ModelMapper();

    // OrderItem 객체를 OrderItemDto로 변환하는 of 메서드
    public static OrderItemDto of(OrderItem orderItem) {
        OrderItemDto orderItemDto = modelMapper.map(orderItem, OrderItemDto.class);

        // 수동 매핑, itemId와 itemName은 Item 객체 안에 있으므로 따로 세팅
        orderItemDto.setItemId(orderItem.getItem().getId());
        orderItemDto.setItemName(orderItem.getItem().getItemName());

        // 총 가격 계산
        orderItemDto.setTotalPrice(orderItem.getTotalPrice());

        return orderItemDto;
    }
}
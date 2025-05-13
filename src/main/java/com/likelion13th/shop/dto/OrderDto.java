package com.likelion13th.shop.dto;

import com.likelion13th.shop.constant.OrderStatus;
import com.likelion13th.shop.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private String orderDate;
    private OrderStatus orderStatus;
    private Long itemId;
    private int totalPrice;

    private static ModelMapper modelMapper = new ModelMapper();

    // Order 객체를 OrderDto로 변환
    public static OrderDto of(Order order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);

        if (!order.getOrderItemList().isEmpty()) {
            orderDto.setItemId(order.getOrderItemList().get(0).getItem().getId());
        }

        return orderDto;
    }
}

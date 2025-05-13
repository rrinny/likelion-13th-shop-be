package com.likelion13th.shop.entity;

import com.likelion13th.shop.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter

public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;
    private LocalDateTime createdBy;
    private LocalDateTime modifiedBy;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    // orderItemList에 주문 상품 정보 추가
    public void addOrderItem(OrderItem orderItem) {
        // orderItem 객체를 order 객체의 orderItemList에 추가
        orderItemList.add(orderItem);

        orderItem.setOrder(this);
    }

    // 주문 생성 메소드, 회원과 아이템 리스트로 주문 생성
    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();

        // 상품을 주문한 회원의 정보를 세팅
        order.setMember(member);

        // 여러 개의 주문 상품을 담을 수 있도록 orderItem 객체를 추가
        for (OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }

        // 주문 상태를 ORDER로 세팅
        order.setOrderStatus(OrderStatus.ORDER);

        // 현재 시간을 주문 시간으로 세팅
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    // 총 주문 금액을 구하는 메소드
    public int getTotalPrice() {
        int totalPrice = 0;

        for (OrderItem orderItem : orderItemList) {
            // orderItem에서 메소드 호출
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

    // 주문 취소 시 (1) 주문 상태를 "CANCEL" 상태로 변경 후 주문 수량을 상품의 재고에 더해 주는 로직
    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;

        for (OrderItem orderItem : orderItemList) {
            // orderItem에서 메소드 호출
            orderItem.cancel();
        }
    }
}

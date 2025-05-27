package com.likelion13th.shop.entity;

import com.likelion13th.shop.constant.ItemSellStatus;
import com.likelion13th.shop.constant.OrderStatus;
import com.likelion13th.shop.repository.ItemRepository;
import com.likelion13th.shop.repository.OrderItemRepository;
import com.likelion13th.shop.repository.OrderRepository;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

import static com.likelion13th.shop.entity.QItem.item;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional

public class OrderTest {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @PersistenceContext
    private EntityManager em;

    // 상품 생성 메소드
    public Item createItem() {
        Item item = new Item();

        // 상품명 - "테스트 상품"
        item.setItemName("테스트 상품");

        // 상품 가격 - 10000
        item.setPrice(10000);

        // 상품 상세 설명 - "테스트 상품 상세 설명"
        item.setItemDetail("테스트 상품 상세 설명");

        // 상품 상태 - SELL
        item.setItemSellStatus(ItemSellStatus.SELL);

        // 상품 수량 - 100
        item.setStock(100);

        // 등록 시간 - 현재
        item.setCreatedBy(LocalDateTime.now());

        // 수정 시간 - 현재
        item.setModifiedBy(LocalDateTime.now());

        return item;
    }

//    @Test
//    @DisplayName("영속성 전이 테스트")
//    public void cascadeTest() {
//        Order order = new Order();
//
//        for (int i = 0; i < 3; i++) {
//            Item item = this.createItem();
//            itemRepository.save(item);
//
//            OrderItem orderItem = new OrderItem();
//
//            // 생성한 아이템 세팅
//            orderItem.setItem(item);
//
//            // 주문 수량 - 10
//            orderItem.setCount(10);
//
//            // 주문 가격 - 1000
//            orderItem.setPrice(1000);
//
//            orderItem.setCreatedBy(LocalDateTime.now());
//            orderItem.setModifiedBy(LocalDateTime.now());
//
//            // 주문 상품에 추가 - add 사용
//            orderItem.setOrder(order);
//            order.getOrderItemList().add(orderItem);
//        }
//
//        orderRepository.saveAndFlush(order);
//        em.clear();
//
//        // id로 방금 생성한 Order 엔티티 조회
//        Order savedOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);
//
//        // 주문 상품 리스트 사이즈와 비교
//        assertEquals(3, savedOrder.getOrderItemList().size());
//    }

    public Order createOrder() {
        Order order = new Order();

        for(int i = 0; i < 3; i++) {
            // item 생성
            Item item = createItem();
            em.persist(item);

            // orderItem 생성
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setOrderPrice(10000);
            orderItem.setCount(1);
            orderItem.setOrder(order);
            orderItem.setCreatedBy(LocalDateTime.now());
            orderItem.setModifiedBy(LocalDateTime.now());

            // order에 주문 상품 추가
            order.getOrderItemList().add(orderItem);
        }

        // 회원 생성
        Member member = new Member();
        em.persist(member);

        // 주문 생성
        order.setMember(member);
        em.persist(order);

        return order;
    }

//    @Test
//    @DisplayName("고아객체 제거 테스트")
//    public void orphanRemovalTest() {
//        Order order = this.createOrder();
//        order.getOrderItemList().remove(0);
//        em.flush();
//    }

    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest() {
        Order order = this.createOrder();
        Long orderItemId = order.getOrderItemList().get(0).getId();
        em.flush();
        em.clear();

        //id로 주문 상품 조회
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(EntityNotFoundException::new);

        System.out.println("Order class: " + orderItem.getOrder().getClass());
        System.out.println("====================================================");
        orderItem.getOrder().getOrderDate();
        System.out.println("====================================================");
    }
}

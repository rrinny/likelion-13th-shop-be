package com.likelion13th.shop.repository;

import com.likelion13th.shop.entity.Item;
import com.likelion13th.shop.entity.QItem;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.likelion13th.shop.constant.ItemSellStatus;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")

class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    // 영속성 컨텍스트를 사용하기 위해 EntityManager 빈 주입
    @PersistenceContext
    EntityManager em;

//    @Test
//    @DisplayName("상품 저장 테스트")
//    public void createItemTest() {
//        Item item = new Item();
//
//        item.setItemName("테스트 상품");
//        item.setPrice(10000);
//        item.setItemDetail("테스트 상품 상세 설명");
//        item.setItemSellStatus(ItemSellStatus.SELL);
//        item.setStock(100);
//        item.setCreatedBy(LocalDateTime.now());
//        item.setModifiedBy(LocalDateTime.now());
//
//        Item savedItem = itemRepository.save(item);
//        System.out.println(savedItem.toString());

    public void createItemList() {
        for (int i = 0; i < 10; i++) { // item 10개 생성
            Item item = new Item();
            item.setItemName("테스트 상품 " + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명 " + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStock(100);
            item.setCreatedBy(LocalDateTime.now());
            item.setModifiedBy(LocalDateTime.now());

            // item 저장
            itemRepository.save(item);
        }
    }

//    @Test
//    @DisplayName("상품명 조회 테스트")
//    public void findByItemNameTest() {
//        this.createItemList();
//        List<Item> itemList = itemRepository.findByItemName("테스트 상품 1");
//        for (Item item : itemList) {  // itemList 출력 코드
//            System.out.println(itemList.toString());
//        }
//    }

//    @Test
//    @DisplayName("가격 내림차순 조회 테스트")
//    public void findByPriceLessThanOrderByPriceDescTest() {
//        this.createItemList();
//
//        // 10005원 이하의 상품 조회 후 가격 내림차순 정렬 결과 출력
//        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10006);
//
//        for (Item item : itemList) {
//            System.out.println(item.toString());
//        }
//    }

//    @Test
//    @DisplayName("@Query를 이용한 상품 조회 테스트")
//    public void findByItemDetailTest() {
//        // "테스트 상품 상세 설명" 조회 후 가격 내림차순 정렬
//        this.createItemList();
//
//        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
//
//        for (Item item : itemList) {
//            System.out.println(item.toString());
//        }
//    }

    @Test
    @DisplayName("Querydsl 조회 테스트")
    public void queryDslTest() {
        this.createItemList();

        // JPAQueryFactory를 통해 쿼리를 동적으로 생성, 파라미터로는 EntityManager 객체
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;

        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%"))
                .orderBy(qItem.price.desc());

        // fetch() 메소드 실행 시점에 쿼리문 실행
        List<Item> itemList = query.fetch();

    //    for (Item item : itemList) {
    //        System.out.println(item.toString());
    //    }

        // 상품이 1개만 조회되도록 코드 수정
        System.out.println(itemList.get(0));
    }
}


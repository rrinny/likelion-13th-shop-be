package com.likelion13th.shop.repository;

import com.likelion13th.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 상품명으로 상품 조회
    List<Item> findByItemName(String itemName);

    // 특정 가격보다 저렴한 상품 내림차순 정렬
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    // 상품 상세 설명 조회 + 가격 내림차순 정렬
    @Query("select i from Item i where i.itemDetail like " + "%:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    // 상품명으로 상품 조회
    List<Item> findByItemNameContainingIgnoreCase(String itemName);
}
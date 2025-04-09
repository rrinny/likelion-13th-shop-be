package com.likelion13th.shop.entity;

import com.likelion13th.shop.constant.ItemSellStatus;
import com.likelion13th.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter

public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;
    private int stock;

    private String itemName;
    private String itemDetail;

    private LocalDateTime createdBy;
    private LocalDateTime modifiedBy;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
}

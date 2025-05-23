package com.likelion13th.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_item")
@Getter
@Setter

public class CartItem {
    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    private LocalDateTime createdBy;
    private LocalDateTime modifiedBy;
}

package com.likelion13th.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter

public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    private LocalDateTime createdBy;
    private LocalDateTime modifiedBy;
}
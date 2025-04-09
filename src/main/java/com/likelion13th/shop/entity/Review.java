package com.likelion13th.shop.entity;

import com.likelion13th.shop.dto.MemberFormDto;
import com.likelion13th.shop.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String content;
    private String rating;

    private LocalDateTime createdAt;
}

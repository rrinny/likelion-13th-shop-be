package com.likelion13th.shop.repository;

import com.likelion13th.shop.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

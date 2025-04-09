package com.likelion13th.shop.repository;

import com.likelion13th.shop.entity.Item;
import com.likelion13th.shop.entity.Member;
import com.likelion13th.shop.entity.Review;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")

class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;

    @Test
    @DisplayName("리뷰 테스트")

    public void createMemberTest() {
        // 멤버 객체 생성
        Member member = new Member();
        member.setName("아기사자");

        // 리뷰 객체 생성
        Review review = new Review();

        // 리뷰 객체에 회원, 내용, 별점 세팅
        review.setName(member.getName());
        review.setContent("♡ 마음에 쏙 들어요! ♡");
        review.setRating("★★★★★");
        review.setCreatedAt(LocalDateTime.now());

        // reviewRepository의 save 메서드 사용해서 해당 객체 저장
        review = reviewRepository.save(review);

        // System.out.println() 함수로 객체 출력
        // (Review 엔티티에 @ToString 어노테이션 추가 후 review.toString()을 출력)
        System.out.println(review.toString());
    }
}
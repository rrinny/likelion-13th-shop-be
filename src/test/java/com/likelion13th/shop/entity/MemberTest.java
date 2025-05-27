package com.likelion13th.shop.entity;

import com.likelion13th.shop.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username = "아기사자", roles = "USER")
    public void auditingTest() {
        // member 생성 후 저장
        Member member = new Member();
        Member savedMember = memberRepository.save(member);

        em.flush();
        em.clear();

        // 생성한 member id로 조회하기 (+ 에러 처리)
        memberRepository.findById(savedMember.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        // 데이터 출력
        System.out.println("register time: " + savedMember.getRegTime());
        System.out.println("update time: " + savedMember.getUpdateTime());
        System.out.println("creater: " + savedMember.getCreatedBy());
        System.out.println("modifier: " + savedMember.getModifiedBy());
    }
}
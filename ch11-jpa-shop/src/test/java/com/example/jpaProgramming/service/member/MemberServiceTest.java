package com.example.jpaProgramming.service.member;

import com.example.jpaProgramming.domain.user.Address;
import com.example.jpaProgramming.domain.user.Member;
import com.example.jpaProgramming.domain.user.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원 저장 성공")
    @Test
    void join() {
        Member member = Member.builder()
                .name("KAI")
                .address(new Address("Seoul", "Gyeong-in", "12314"))
                .build();
        memberService.join(member);

        List<Member> members = memberRepository.findAllByName("KAI");
        assertThat(members.get(0).getName()).isEqualTo("KAI");
    }

    @DisplayName("이름이 같은 회원을 저장할 경우 예외발생")
    @Test
    void joinDuplicate() {
        Member member1 = Member.builder()
                .name("KAI")
                .address(new Address("Seoul", "Gyeong-in", "12314"))
                .build();
        memberRepository.saveAndFlush(member1);

        Member member2 = Member.builder()
                .name("KAI")
                .address(new Address("Seoul", "Gyeong-in", "12314"))
                .build();

        assertThrows(IllegalStateException.class,() -> memberService.join(member2));
    }
}
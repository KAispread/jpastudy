package com.example.jpaProgramming.service.member;

import com.example.jpaProgramming.domain.user.Member;
import com.example.jpaProgramming.domain.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(Member member) {
        validateDuplicateMember(member);
        Member save = memberRepository.save(member);
        return save.getId();
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
         return memberRepository.findById(memberId)
                 .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    private void validateDuplicateMember(Member member) {
        boolean exist = memberRepository.existsAllByName(member.getName());
        if (exist) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}

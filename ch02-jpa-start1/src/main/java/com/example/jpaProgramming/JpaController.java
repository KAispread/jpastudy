package com.example.jpaProgramming;

import com.example.jpaProgramming.domain.Member;
import com.example.jpaProgramming.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class JpaController {
    @PersistenceUnit
    private final EntityManagerFactory emf;

    private final MemberRepository memberRepository;

    @ResponseBody
    @GetMapping("/order")
    public String addMember() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        return "OK";
    }

    private static void logic(EntityManager em) {
        String id = "id3";
        Member member = new Member();
        member.setId(id);
        member.setAge(20);
        member.setUsername("기우");

        // 등록
        em.persist(member);

        member.setAge(24);

        Member findMember = em.find(Member.class, id);
        System.out.println("findMember = " + findMember.getUsername() + "/ age = " + findMember.getAge());

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size() = " + members.size());
    }
}

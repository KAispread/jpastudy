package com.example.jpaProgramming;

import com.example.jpaProgramming.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class JpaController {
    @PersistenceUnit
    private final EntityManagerFactory emf;

    @GetMapping("/jpa")
    public String jpql() {
        EntityManager em = emf.createEntityManager();
        String jpql = "select m from Member as m where m.username = 'kim'";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
        return "OK";
    }

    private void criteria() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class);
        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
        List<Member> resultList = em.createQuery(cq).getResultList();
    }

    private void nativeQuery() {
        EntityManager em = emf.createEntityManager();
        String sql = "SELECT ID, AGE, TEAM_ID, NAME FROM MEMBER WHERE NAME = 'kim'";
        List resultList = em.createNativeQuery(sql, Member.class).getResultList();
    }
}

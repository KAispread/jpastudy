package jpaProgramming;

import jpaProgramming.domain.Member;
import jpaProgramming.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
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
//        String jpql = "select m from Member m join m.team t where " + "t.name=:teamName";
        String jpql = null;
        List<Member> resultList = em.createQuery(jpql, Member.class).setParameter("teamName", "팀1")
                .getResultList();

        for (Member member : resultList) {
            System.out.println("[query] member.username=" + member.getName());
        }
    }
}

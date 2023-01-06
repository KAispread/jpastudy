package com.example.jpaProgramming;

import com.example.jpaProgramming.domain.Member;
import com.example.jpaProgramming.domain.QMember;
import com.example.jpaProgramming.domain.QOrder;
import com.example.jpaProgramming.domain.QOrderItem;
import com.example.jpaProgramming.domain.item.Item;
import com.example.jpaProgramming.domain.item.QItem;
import com.example.jpaProgramming.example.UserDTO;
import com.mysema.query.jpa.JPASubQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.example.jpaProgramming.domain.QMember.member;

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

        // 조회대상이 명확할 땐 TypedQuery
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
        List<Member> resultList1 = query.getResultList();

        // 조회대상이 명확하지 않을 땐 Query
        List<Object[]> resultList2 = em.createQuery("SELECT m.username, m.address from Member m")
                .getResultList();
        for (Object[] result : resultList2) {
            // 결과가 둘 이상이면 Object 배열 반환
            System.out.println("username : " + result[0]);
            System.out.println("address : " + result[1]);
        }

        // 파라미터 바인딩
        String usernameParam = "User1";
        List username = em.createQuery("select m from Member m where m.username = :username")
                .setParameter("username", usernameParam)
                .getResultList();

        List resultList3 = em.createQuery("select m from Member m where m.username = ?1")
                .setParameter(1, usernameParam)
                .getResultList();

        // 스칼라 타입 프로젝션 & 중복 제거
        List<String> resultList4 = em.createQuery("select distinct m.username from Member m", String.class)
                .getResultList();

        // 통계 쿼리
        TypedQuery<Double> query2 = em.createQuery("select avg(o.amount) from Order o", Double.class);

        // 여러 필드를 프로젝션할 경우 객체로 결과 반환 -> NEW 명령어
        TypedQuery<UserDTO> query1 = em.createQuery("select new com.example.jpaProgramming.example.UserDTO(m.username, m.address) from Member m", UserDTO.class);

        // 페이징 API
        TypedQuery<Member> query3 = em.createQuery("select m from Member m order by m.username desc", Member.class);
        query3.setFirstResult(10);
        query3.setFirstResult(20);
        query3.getResultList();

        // 집합 함수
        TypedQuery<Long> query4 = em.createQuery("select count(distinct m) from Member m", Long.class);

        // 내부 JOIN
        String username1 = "kai";
        List<Member> members = em.createQuery("select m from Order o inner join o.member m where m.username = :name", Member.class)
                .setParameter("name", username1)
                .getResultList();

        // 외부 JOIN
        List<Member> resultList5 = em.createQuery("select m from Member m left join m.orders o", Member.class)
                .getResultList();

        // ON 절 -> JOIN 대상 필터링
        List resultList6 = em.createQuery("select m,o from Member m left join m.orders o on o.amount > 10")
                .getResultList();

        // FETCH JOIN -> 연관된 엔티티를 함께 조회
        // Collection FETCH JOIN 시 데이터 뻥튀기 발생 -> distinct 를 사용하면 애플리케이션에서 한 번 더 중복을 제거해줌
        List resultList7 = em.createQuery("select distinct m from Member m join fetch m.orders")
                .getResultList();

        // 묵시적 조인 -> join 절 사용 없이도 엔티티의 연관관계를 탐색하면 묵시적 내부 조인 발생 (Inner Join ORDER)
        // 연관된 컬렉션 값 경로를 탐색할 때 size 라는 기능을 제공 -> count 함수와 동일
        List resultList8 = em.createQuery("select m from Member m where m.orders.size > 10")
                .getResultList();
        em.createQuery("select m from Member m inner join m.orders o where o.size > 10")
                .getResultList();

        // like -> 문자 표현식과 패턴 값을 비교
        // % -> 아무 값들이 입력되어도 됨
        // _ -> 한 글자에 대해 아무 값이 입력되어도 됨
        em.createQuery("select m from Member m where m.username like '_기우'")
                .getResultList();

        // null, empty 비교
        em.createQuery("select m from Member m where m.orders IS EMPTY and m.username IS NOT NULL");

        // 스칼라 식
        // CONCAT, SUBSTRING, LOWER, TRIM, LENGTH 등
        em.createQuery("select CONCAT(m.username, m.createdDate) from Member m");

        // CASE 식
        em.createQuery("select case when o.amount <= 1000 then '일반 요금' when o.amount > 5000 then '프리미엄' else '일반요금'" +
                "end from Order o");
        em.createQuery("select " +
                "case o.amount " +
                    "when 1000 then '일반 요금' " +
                    "when 5000 then '프리미엄' " +
                "else '일반요금'" +
                "end from Order o");

        // TREAT -> 자식 타입으로 다루기
        em.createQuery("select i from Item i where treat(i as Book).author = 'kim'");

        // NamedQuery 사용 (정적 쿼리)
        em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("username", "회원1")
                .getResultList();

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

    private void queryDSL() {
        EntityManager em = emf.createEntityManager();

        JPAQuery<Member> query = new JPAQuery<>(em);
        List<Member> members = query.from(member)
                .where(member.username.eq("회원1"))
                .orderBy(member.username.desc())
                .fetch();

        // 검색 조건 쿼리
        JPAQuery<Item> query1 = new JPAQuery<>(em);
        QItem item = QItem.item;
        List<Item> list1 = query1.from(item)
                .where(item.name.eq("좋은상품")
                        .and(item.price.gt(20000))
                        .and(item.price.between(40000, 70000))
                        .and(item.name.contains("상품1"))
                        .and(item.name.startsWith("고급")))
                .fetch();

        // 페이징 & 정렬
        List<Item> list2 = query1.from(item)
                .where(item.price.gt(20000))
                .orderBy(item.price.desc(), item.stockQuantity.asc())
                .offset(10).limit(20)
                .fetch();

        // 그룹
        query1.from(item)
                .groupBy(item.price)
                .having(item.price.gt(10000))
                .fetch();

        // 조인
        QOrder order = QOrder.order;
        QOrderItem orderItem = QOrderItem.orderItem;
        query1.from(order)
                .join(order.member, member)
                .leftJoin(order.orderItems, orderItem)
                .on(orderItem.count.gt(2))
                .fetch();

        // 페치조인
        query1.from(order)
                .join(order.member, member).fetchJoin()
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .on(orderItem.count.gt(2))
                .fetch();

        // 서브 쿼리
        QItem itemSub = new QItem("itemSub");
//        query1.from(item)
//                .where(item.price.eq(
//                        new JPASubQuery().from(item).unique(itemSub.price.max())
//                )).fetch();
    }
}

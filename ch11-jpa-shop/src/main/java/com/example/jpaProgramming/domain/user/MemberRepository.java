package com.example.jpaProgramming.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.name = :name")
    List<Member> findAllByName(@Param("name") String name);

    boolean existsAllByName(String name);
}

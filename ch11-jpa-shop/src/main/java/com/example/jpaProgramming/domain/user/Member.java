package com.example.jpaProgramming.domain.user;

import com.example.jpaProgramming.domain.order.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(unique = true)
    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public void setOrders(Order order) {
        if (!orders.contains(order)) {
            orders.add(order);
        }
    }
}

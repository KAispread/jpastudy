package com.example.jpaProgramming.domain;

import javax.persistence.*;

@Entity
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private String city;
    private String zipcode;
    private String street;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}

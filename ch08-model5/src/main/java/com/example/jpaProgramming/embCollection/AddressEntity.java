package com.example.jpaProgramming.embCollection;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AddressEntity {
    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Address address;
}

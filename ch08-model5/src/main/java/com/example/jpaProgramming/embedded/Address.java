package com.example.jpaProgramming.embedded;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Embeddable
public class Address {
    @Column(name = "city")
    private String city;
    private String street;
    private String state;
    @Embedded
    Zipcode zipcode;
}

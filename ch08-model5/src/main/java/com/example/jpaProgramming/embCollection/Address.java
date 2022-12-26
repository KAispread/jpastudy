package com.example.jpaProgramming.embCollection;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Address {
    @Column
    private String city;
    private String street;
    private String zipcode;
}

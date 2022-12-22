package com.example.jpaProgramming.embedded;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    private Long id;
    private String name;

    @Embedded
    PhoneNumber phoneNumber;

    @Embedded
    Address homeAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "COMNAY_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "COMNAY_STREET")),
            @AttributeOverride(name = "state", column = @Column(name = "COMNAY_STATE")),
    })
    Address companyAddress;
}

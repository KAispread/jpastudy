package com.example.jpaProgramming.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @Column
    private String id;

    @Column(name = "NAME")
    private String username;

    private Integer age;
}

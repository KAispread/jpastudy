package com.example.jpaProgramming.proxy;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String username;

    @ManyToOne
    private Team team;
}

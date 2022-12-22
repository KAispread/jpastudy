package com.example.jpaProgramming.proxy;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Team {
    @Id @GeneratedValue
    private Long id;

    private String name;
}

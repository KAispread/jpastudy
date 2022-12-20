package com.example.jpaProgramming.foriegn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hello {
    @Id @GeneratedValue
    private String id;
}

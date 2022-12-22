package com.example.jpaProgramming.cascade;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Child {
    @Id
    private Long id;

    @ManyToOne
    private Parent parent;
}

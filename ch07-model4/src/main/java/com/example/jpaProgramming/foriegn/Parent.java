package com.example.jpaProgramming.foriegn;

import javax.persistence.*;

@Entity
@IdClass(ParentId.class)
public class Parent {
    @Id @Column(name = "PARENT_ID")
    private String id;

    private String name;
}

package com.example.jpaProgramming.foriegn;

import javax.persistence.*;

@Entity
public class Child {
    @EmbeddedId
    private ChildId id;

    @MapsId("parentId")
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;
}

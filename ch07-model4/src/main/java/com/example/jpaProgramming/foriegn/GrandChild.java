package com.example.jpaProgramming.foriegn;

import javax.persistence.*;

@Entity
public class GrandChild {
    @EmbeddedId
    private GrandChildId id;

    @MapsId("childId")
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "PARENT_ID"),
        @JoinColumn(name = "CHILD_ID")
    })
    private Child child;
}

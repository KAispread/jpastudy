package com.example.jpaProgramming.embCollection;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(
            name = "FAVORITE_FOODS",
            joinColumns = @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();
}

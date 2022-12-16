package com.example.jpaProgramming.manytomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    @OneToMany(mappedBy = "product")
    private List<Orders> orders = new ArrayList<>();
}

package com.example.jpaProgramming.domain;

import com.example.jpaProgramming.domain.item.Item;

import javax.persistence.*;

@Entity
public class CategoryItem {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}

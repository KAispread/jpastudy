package com.example.jpaProgramming.domain.item.sub;

import com.example.jpaProgramming.domain.item.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("B")
@Getter
@Entity
public class Book extends Item {
    private String author;
    private String isbn;
}

package com.example.jpaProgramming.domain.item.sub;

import com.example.jpaProgramming.domain.item.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("B")
@Getter
@Entity
public class Book extends Item {
    private String author;
    private String isbn;

    @Builder
    public Book(String author, String isbn, String name, int price, int stockQuantity) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}

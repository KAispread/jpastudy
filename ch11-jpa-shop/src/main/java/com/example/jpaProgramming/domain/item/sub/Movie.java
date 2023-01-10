package com.example.jpaProgramming.domain.item.sub;

import com.example.jpaProgramming.domain.item.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("M")
@Getter
@Entity
public class Movie extends Item {
    private String director;
    private String actor;
}

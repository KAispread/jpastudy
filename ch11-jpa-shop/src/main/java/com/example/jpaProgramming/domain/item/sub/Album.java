package com.example.jpaProgramming.domain.item.sub;

import com.example.jpaProgramming.domain.item.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("A")
@Getter
@Entity
public class Album extends Item {
    private String artist;
    private String etc;
}

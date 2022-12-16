package com.example.jpaProgramming.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("A")
@PrimaryKeyJoinColumn(name = "ALBUM_ID")
public class Album extends Item{
    private String artist;
}

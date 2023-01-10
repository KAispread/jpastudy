package com.example.jpaProgramming.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseEntity {
    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastModified;
}

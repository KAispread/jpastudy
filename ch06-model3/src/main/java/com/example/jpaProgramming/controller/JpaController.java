package com.example.jpaProgramming.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Slf4j
@RequiredArgsConstructor
@RestController
public class JpaController {
    @PersistenceUnit
    private final EntityManagerFactory emf;


    @GetMapping("/order")
    public String order() {

        return "Ok";
    }
}

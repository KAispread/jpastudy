package com.example.jpaProgramming.example;

import com.example.jpaProgramming.domain.Address;
import lombok.Getter;

@Getter
public class UserDTO {
    private final String username;
    private final Address address;

    public UserDTO(String username, Address address) {
        this.username = username;
        this.address = address;
    }
}

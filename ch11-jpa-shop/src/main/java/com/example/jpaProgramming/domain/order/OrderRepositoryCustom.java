package com.example.jpaProgramming.domain.order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findAll(OrderSearch orderSearch);
}

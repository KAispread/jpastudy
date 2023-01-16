package com.example.jpaProgramming.exception.notenough;

public class NotEnoughStockException extends NotEnoughException {
    public NotEnoughStockException() {
        super("need more stock");
    }
}

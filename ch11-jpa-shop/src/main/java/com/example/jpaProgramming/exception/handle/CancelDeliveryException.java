package com.example.jpaProgramming.exception.handle;

import com.example.jpaProgramming.exception.CustomException;

public class CancelDeliveryException extends CustomException {
    public CancelDeliveryException() {
        super("이미 배송완료된 상품은 취소가 불가능합니다.");
    }
}

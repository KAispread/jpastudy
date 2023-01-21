package com.example.jpaProgramming.service.order;

import com.example.jpaProgramming.domain.item.Item;
import com.example.jpaProgramming.domain.order.*;
import com.example.jpaProgramming.domain.user.Member;
import com.example.jpaProgramming.domain.user.MemberRepository;
import com.example.jpaProgramming.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        Item item = itemService.findOne(itemId);

        // 배송 정보 생성
        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .build();

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    // 주문 취소
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}

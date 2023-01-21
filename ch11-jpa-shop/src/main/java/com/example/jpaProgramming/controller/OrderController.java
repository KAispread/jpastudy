package com.example.jpaProgramming.controller;

import com.example.jpaProgramming.domain.item.Item;
import com.example.jpaProgramming.domain.user.Member;
import com.example.jpaProgramming.service.item.ItemService;
import com.example.jpaProgramming.service.member.MemberService;
import com.example.jpaProgramming.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller("/order")
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping
    public String order(@RequestParam Long memberId, @RequestParam Long itemId, @RequestParam int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/order/list";
    }

    @GetMapping("/list")
    public String orderList(Model model) {
        model.addAttribute("orders", orderService.findOrders());
        return "order/orderList";
    }
}

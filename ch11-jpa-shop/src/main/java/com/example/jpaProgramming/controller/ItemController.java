package com.example.jpaProgramming.controller;

import com.example.jpaProgramming.domain.item.Item;
import com.example.jpaProgramming.domain.item.sub.Book;
import com.example.jpaProgramming.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller("/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/new")
    public String createForm() {
        return "items/createItemForm";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute Book item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        model.addAttribute("item", item);
        return "items/updateItemForm";
    }

    // 상품 수정
    @GetMapping("{itemId}/edit")
    public String updateItem(@ModelAttribute Book item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }
}

package com.example.hibernateexample.web.rest;


import com.example.hibernateexample.entity.Item;
import com.example.hibernateexample.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items/post")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        var res = itemService.save(item);
        return ResponseEntity.ok(res);
    }
}

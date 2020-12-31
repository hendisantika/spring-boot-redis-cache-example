package com.hendisantika.springbootrediscacheexample.controller;

import com.hendisantika.springbootrediscacheexample.service.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-cache-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/12/20
 * Time: 11.41
 */
@RestController
@RequestMapping(path = "/api/items/")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
}

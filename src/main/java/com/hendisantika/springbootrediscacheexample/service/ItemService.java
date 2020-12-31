package com.hendisantika.springbootrediscacheexample.service;

import com.hendisantika.springbootrediscacheexample.entity.Item;
import com.hendisantika.springbootrediscacheexample.repository.ItemRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-cache-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/12/20
 * Time: 11.35
 */
@Service
@Log4j2
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> items() {
        return itemRepository.findAll();
    }

    @Cacheable(value = "items", key = "#id")
    public Item getItem(Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(RuntimeException::new);
        log.info("Loading data from DB {}", item);
        return item;
    }
}

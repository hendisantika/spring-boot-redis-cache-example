package com.hendisantika.springbootrediscacheexample.repository;

import com.hendisantika.springbootrediscacheexample.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-cache-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/12/20
 * Time: 11.34
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
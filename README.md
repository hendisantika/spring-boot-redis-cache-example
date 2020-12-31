# spring-boot-redis-cache-example

## Using Redis cache in Spring Boot

Redis is a high performance open source cache store that keeps cached content in-memory for quick retrieval of data.
Redis can be used as a cache store, database even a message broker. We will concentrate on the caching aspects in this
article. Redis provides basic set of data-types such as Strings, Hashes, Lists, Sets and Streams. We can even store
binary content such as the byte data of Serialized Java objects.

Run the following command for starting the containers.

```shell
docker-compose.yml up -d
```

Once redis and redis-commander is up, You can open http://localhost:8081/ in your browser to see the redis-commander
window, and you should see the following screen or similar.

Setup caching annotations. To enable caching, we need to do three things.

1. Add @EnableCaching to one of your configuration classes. (Preferably the main class which is annotated with
   @SpringBootApplication)
2. Add @Cacheable annotation to the methods for which you need to enable caching.
3. Optionally, Add an @CacheEvict annotation when you need to clear the cached object.
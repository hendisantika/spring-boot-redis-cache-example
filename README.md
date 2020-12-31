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

## Redis in Action

Once we start the application, we can see that the first request to http://localhost:8080/items/2 returns response from
the database, and a cache entry is made in redis server. We know this because,

```shell
curl -X PUT \
http://localhost:8080/items/2 \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
"productName": "Pants Large",
"price": 14.99
}'
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 13 Dec 2020 18:11:16 GMT

{"id":2,"productName":"Pants Large","price":14.99} 
```

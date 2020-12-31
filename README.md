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

Add New Item:

```shell
curl --location --request POST 'http://localhost:8080/api/items' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productName": "Pulpen Pilot Motor",
    "price": 19.99
}' | jq .
```

Get All Items

```shell
curl --location --request GET 'http://localhost:8080/api/items' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productName": "Pulpen Pilot",
    "price": 19.99
}' | jq .
```

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

## Timeout for cached values

One more difference between redis and inmemory cache is that, Redis can evict cached entries based on time-to-live(TTL).
In order to enable this feature you need to add the following parameter to your application.properties.

```shell
spring.cache.redis.time-to-live=5m
```

The above configuration will automatically evict any cached entry after 5 minutes.

## What we have learned?

Redis can help provide robust cache store with minimal change to your project. Redis acts as a central cache for all of
your instances thus there is no cache poisoning (inconsistent cache values between different servers). A new tool called
redis-commander for exploring the content of redis cache store and how to set it up.

## Things to consider

1. Cacheable objects must be Serializable. The reason is due to how redis stores java objects. The safest way to store
   objects outside JVM is to write them into serialized bytes. To do that, those classes must implement Serializable.
2. Try not to cache large objects. Even though redis server is separate from application server, large objects in the
   cache will cause performance issues.
3. Make sure all applications using the same cache are at the same version. Cached objects created by application with
   the version A may not be compatible to the applications with the version B. These type of situations will yield
   unpredictable results which are not good for business.
4. Application restarts don’t affect cache stored in redis.

Unlike in-memory caching, redis data store is outside of application JVM. This means that the cached data is available
even after the restart of an application.

Finally, Redis is one of the many officially supported cache solution by Spring Boot. Try the rest and chose the one
that fits best for you.
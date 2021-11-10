package com.array.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @author XIII
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.cache-expiration-in-hours:1}")
    private int cacheExpirationInHours;

    public void put(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, Duration.ofHours(cacheExpirationInHours));
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

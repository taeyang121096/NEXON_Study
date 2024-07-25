package com.example.concurrency.core.redis.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;


    public Boolean tryLock(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForValue()
                .setIfAbsent(key, "lock", timeout, timeUnit);
    }

    public Boolean unlock(String key) {
        return redisTemplate.delete(key);
    }
}

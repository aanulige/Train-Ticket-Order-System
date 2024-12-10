package com.example.train_ticket_system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    public RedisTemplate<String, Object> redisTemplate;

    // 设置键值
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 获取键值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 设置带过期时间的键值
    public void setWithExpire(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // 删除键
    public void delete(String key) {
        redisTemplate.delete(key);
    }


}

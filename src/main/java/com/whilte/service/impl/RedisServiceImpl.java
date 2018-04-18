package com.whilte.service.impl;

import com.whilte.domail.User;
import com.whilte.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate< String , User > redisTemplate;

    @Override
    public void set(User user) {
        redisTemplate.opsForValue().set(user.getTelephoneNumber(),user,30*60, TimeUnit.SECONDS);
    }

    @Override
    public User get(User user) {
        return redisTemplate.opsForValue().get(user.getTelephoneNumber());
    }

    @Override
    public void delete(User user) {
        redisTemplate.delete(user.getTelephoneNumber());
    }
}

package com.whilte;

import com.whilte.domail.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String , User> redisTemplate;

    @org.junit.Test
    public void test(){
        // 保存对象
        User user = new User("超人", 20);
        redisTemplate.opsForValue().set("user",user,60*30, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("user").getAge());
    }

}

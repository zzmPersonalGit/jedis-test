package com.example.jedistest;

import com.example.jedistest.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class JedisTestApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Test
    void testString() {

        redisTemplate.opsForValue().set("name","王慧");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    void testUser(){
        redisTemplate.opsForValue().set("user:100",new User("王慧","123456",22));

        User user = (User)redisTemplate.opsForValue().get("user:100");
        System.out.println(user);
    }

}

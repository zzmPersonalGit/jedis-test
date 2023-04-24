package com.example.jedistest;

import com.example.jedistest.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class RedisStringTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    void testString() {

        stringRedisTemplate.opsForValue().set("name","王慧");
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testUser() throws JsonProcessingException {
        //创建对象
        User user1 = new User("周忠明", "1234567", 22);
        //手动序列化
        String s = mapper.writeValueAsString(user1);
        //写入数据
        stringRedisTemplate.opsForValue().set("user:200", s);

        //获取数据
        String jsonuser = stringRedisTemplate.opsForValue().get("user:200");
        User user = mapper.readValue(jsonuser, User.class);
        System.out.println("user = "+user);
    }

    @Test
    void testHash(){
        stringRedisTemplate.opsForHash().put("user:400","name","王辉");
        stringRedisTemplate.opsForHash().put("user:400","age","21");
        stringRedisTemplate.opsForHash().put("user:400","password","123455");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println(entries);
    }

}

package com.example.redismq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class serivcetest {
    @Autowired
    private RedisTemplate redisTemplate;

    public void a() {
        redisTemplate.convertAndSend("testchannel", "testchannel");
        redisTemplate.convertAndSend("chat", "chat");
    }
}

package com.example.redismq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Controller
public class test {
    @Autowired
    private serivcetest serivcetest;

    @RequestMapping("/a")
    @ResponseBody
    public String a(){
        serivcetest.a();
        return "a";
    }
}

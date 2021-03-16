package com.allen.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuguocai on 2021/3/16 10:05  针对 List 数据结构操作
 */
@RestController
@RequestMapping("/redis/")
public class RedisListController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/rpush")
    public String rpush() {
        List<String> list = new ArrayList<>();
        list.add("xu");
        list.add("guo");
        list.add("cai");
        list.add("gai");
        list.add("jian");
        list.add("fei");


        return null;
    }

}

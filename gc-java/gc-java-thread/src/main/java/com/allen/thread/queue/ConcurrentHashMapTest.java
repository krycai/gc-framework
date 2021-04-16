package com.allen.thread.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: MultiThread
 * @description: ConcurrentHashMapTest
 * @author: allen小哥
 * @Date: 2019-12-14 09:13
 **/
@Slf4j
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("key","早上好");
        map.put("key2","中午好");
        map.put("key3","下午好");

      log.info("写数据完毕");
        Object key = map.get("key");
        log.info("key:{}",key);
    }

}

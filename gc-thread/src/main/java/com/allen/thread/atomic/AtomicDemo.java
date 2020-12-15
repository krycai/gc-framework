package com.allen.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: MultiThread
 * @description: AtomicDemo
 * @author: allen小哥
 * @Date: 2019-12-15 14:28
 **/
@Slf4j
public class AtomicDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static AtomicBoolean atomicBoolean = new AtomicBoolean();
    private static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) {
        log.info("开始执行:{}",atomicInteger.getAndIncrement()); // 返回原来的值
        log.info("得到的值:{}",atomicInteger.get());
    }

}

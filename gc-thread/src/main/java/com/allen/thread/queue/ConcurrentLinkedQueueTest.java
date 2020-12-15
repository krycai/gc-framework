package com.allen.thread.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @program: MultiThread
 * @description: ConcurrentLinkedQueueTest
 * @author: allen小哥
 * @Date: 2019-12-14 07:37
 **/
@Slf4j
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.offer("早上好");
        queue.offer("中午好");
        queue.offer("晚上好");
        queue.offer("明天见");
        log.info("操作offer方法完毕，谢谢");

        Object poll = queue.poll();
        log.info(poll.toString());
    }

}

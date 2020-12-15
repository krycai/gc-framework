package com.allen.thread.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: MultiThread
 * @description: ThreadLocalTest
 * @author: allen小哥
 * @Date: 2019-12-14 09:24
 **/
@Slf4j
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("早上好");

        Object o = threadLocal.get();
        log.info("o===="+o.toString());

        threadLocal.remove();

        Object o1 = threadLocal.get();
        log.info("o1==="+o1);
    }

}

package com.allen.thread.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: MultiThread
 * @description: CopyOnWriteArrayListTest
 * @author: allen小哥
 * @Date: 2019-12-14 08:56
 **/
@Slf4j
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        list.add("早上好");
        list.add("中午好");
        list.add("下午好");
        list.add("晚上好");
        list.add("明天见");

        log.info("写数据已完毕");

        list.forEach(item ->{ log.info(item.toString());});
        log.info("读数据已完毕");

    }

}

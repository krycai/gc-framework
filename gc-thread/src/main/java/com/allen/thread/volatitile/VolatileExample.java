package com.allen.thread.volatitile;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: MultiThread
 * @description: VolatileExample
 * @author: allen小哥
 * @Date: 2019-11-28 19:53
 **/
@Slf4j
public class VolatileExample {

    private int a =0;
    private volatile boolean flag = false;

    public void write(){
        a = 1;
        flag  = true;
        log.info("执行写操作a：{},flag:{}",a,flag);
    }

    public void  reader(){
        if (flag){
            int i = a;
            log.info("执行读i的值:{}",i);
        }
    }

    public static void main(String[] args) {
        VolatileExample example = new VolatileExample();

        //线程1
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                example.write();
            }
        });
        thread1.start();

        // 线程2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                example.reader();
            }
        });
        thread2.start();

    }

}

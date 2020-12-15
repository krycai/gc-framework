package com.allen.thread.volatitile;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: MultiThread
 * @description: VolatileDemo
 * @author: allen小哥
 * @Date: 2019-11-28 20:11
 **/
@Slf4j
public class VolatileDemo {

    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag){
                    log.info("一直在执行xx");
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        flag = true;
    }

}

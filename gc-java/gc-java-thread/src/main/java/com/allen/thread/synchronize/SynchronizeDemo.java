package com.allen.thread.synchronize;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: MultiThread
 * @description: SynchronizeDemo
 * @author: allen小哥
 * @Date: 2019-11-28 10:29
 **/
@Slf4j
public class SynchronizeDemo implements Runnable {

    private static int count =0;

    public static void main(String[] args) {
        for (int i = 0;i <10 ; i++){
            Thread thread = new Thread(new SynchronizeDemo());
            thread.start();
        }
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("打印count值:{}",count);
    }

//    @Override
//    public void run() {
//        for (int i =0; i <2000;i++){
//            count++;
//        }
//        log.info("正在执行的线程:{},执行run方法得到的值:{}",Thread.currentThread().getName(),count);
//    }

    @Override
    public void run() {
        synchronized (SynchronizeDemo.class){
            for (int i =0; i <2000;i++){
                count++;
            }
            log.info("正在执行的线程:{},执行run方法得到的值:{}",Thread.currentThread().getName(),count);
        }

    }
}

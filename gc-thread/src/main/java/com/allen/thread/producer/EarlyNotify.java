package com.allen.thread.producer;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: MultiThread
 * @description: 通知过早的现象  WaitThread会一直在wait方法出阻塞
 *
 * 使用Object的wait/notify的消息通知机制
 *
 * @author: allen小哥
 * @Date: 2019-12-15 21:11
 **/
@Slf4j
public class EarlyNotify {

    private static String lockObject ="";

    public static void main(String[] args) {
        WaiterThread waiterThread = new WaiterThread(lockObject);
        NotifyThread notifyThread = new NotifyThread(lockObject);
        notifyThread.start();
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        waiterThread.start();
    }

    static class WaiterThread extends Thread{
        private String lock;

        public WaiterThread(String lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock){
                try{
                    log.info("WaiterThread当前线程:{}",Thread.currentThread().getName()+"进入代码块");
                    lock.wait();
                    log.info("WaiterThread当前线程:{}",Thread.currentThread().getName()+"结束");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    static class NotifyThread extends Thread{
       private String lock;

       public NotifyThread(String lock){
           this.lock = lock;
       }

        @Override
        public void run() {
            synchronized (lock){
                log.info("NotifyThread当前线程:{}",Thread.currentThread().getName()+"进入代码块");
                lock.notify();
                log.info("NotifyThread当前线程:{}",Thread.currentThread().getName()+"结束");
            }
        }
    }

}

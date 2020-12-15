package com.allen.thread.producer;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: MultiThread
 * @description: 增加了一个isWait状态变量，NotifyThread调用notify方法后会对状态变量进行更新，在WaitThread中调用wait方法之前会先对状态变量进行判断
 *
 * 使用Object的wait/notify的消息通知机制；
 *
 * @author: allen小哥
 * @Date: 2019-12-15 21:11
 **/
@Slf4j
public class EarlyNotify2 {

    private static String lockObject ="";
    private static boolean isWait = true;

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
                    while (isWait){ // 避免了Notify过 早通知造成遗漏的情况
                        log.info("WaiterThread当前线程:{}",Thread.currentThread().getName()+"进入代码块");
                        lock.wait();
                        log.info("WaiterThread当前线程:{}",Thread.currentThread().getName()+"结束");
                    }

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
                lock.notifyAll();
                isWait = false;
                log.info("NotifyThread当前线程:{}",Thread.currentThread().getName()+"结束");
            }
        }
    }

}

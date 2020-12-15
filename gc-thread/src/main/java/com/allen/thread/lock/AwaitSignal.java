package com.allen.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: MultiThread
 * @description: AwaitSignal
 * @author: allen小哥
 * @Date: 2019-12-05 20:31
 **/
@Slf4j
public class AwaitSignal {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread waiter = new Thread(new Waiter());
        waiter.start();
        Thread signaler = new Thread(new Signaler());
        signaler.start();
    }

    static class Waiter implements Runnable{
        @Override
        public void run() {
            lock.lock();
            try {
                while (!flag){
                    log.info("当前线程名称:{}",Thread.currentThread().getName()+"当前条件不满足等待");
                    try {
                        condition.await();
                        condition.awaitUninterruptibly();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                log.info("当前线程名称:{}",Thread.currentThread().getName()+"接收到通知条件通知");
            }finally {
                lock.unlock();
            }
        }
    }

    static class Signaler implements Runnable{
        @Override
        public void run() {
            lock.lock();
            log.info("当前线程名称:{}",Thread.currentThread().getName());
            try {
                flag = true;
                condition.signalAll();
                log.info("当前线程名称signalAll:{}",Thread.currentThread().getName());
            }finally {
                lock.unlock();
            }
        }
    }
}

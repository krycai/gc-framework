package com.allen.thread.producer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: MultiThread
 * @description: 改进ConditionChange的问题
 * 在使用线程的等待/通知机制时，一般都要在 while 循环中调用 wait()方法，
 * 因此xuy配合使用一个 boolean 变量（或其他能判断真假的条件，如本文中的 list.isEmpty()），
 * 满足 while 循环的条件时，进入 while 循环，执行 wait()方法，不满足 while 循环的条件时，跳出循环，执行后面的代码。
 * @author: allen小哥
 * @Date: 2019-12-16 20:23
 **/
@Slf4j
public class ConditionChange2 {

    private static List<String> lockObject = new ArrayList<>();

    public static void main(String[] args) {
        Consumer consumer = new Consumer(lockObject);
        Consumer consumer2 = new Consumer(lockObject);
        Producer producer = new Producer(lockObject);
        consumer.start();
        consumer2.start();
        producer.start();

    }
    // 消费者
    static class Consumer extends Thread{
        private List<String> lock;

        public Consumer(List lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock){
                try {
                    // 线程执行前先判断数组是否为空，为空的话不执行。这样可以避免ConditionChange中出现的问题。
                    while (lock.isEmpty()){
                        log.info("当前线程:{}",Thread.currentThread().getName()+"list 为空");
                        lock.wait();
                        log.info("当前线程:{}",Thread.currentThread().getName()+"wait 方法执行结束");
                    }
                    String remove = lock.remove(0);
                    log.info("当前线程:{}",Thread.currentThread().getName()+"取出第一个元素为:{}",remove);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    //生产者
    static class Producer extends Thread{
        private List<String> lock;

        public Producer(List lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock){
                log.info("当前线程:{}",Thread.currentThread().getName()+"开始添加元素");
                lock.add(Thread.currentThread().getName());
                lock.notifyAll();
            }
        }
    }
}

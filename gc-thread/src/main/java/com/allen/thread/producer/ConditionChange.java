package com.allen.thread.producer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: MultiThread
 * @description:
 *
 * 在Object提供的消息通知机制应该遵循如下这些条件：
 *
 * 永远在while循环中对条件进行判断而不是if语句中进行wait条件的判断；
 * 使用NotifyAll而不是使用notify。
 *
 * @author: allen小哥
 * @Date: 2019-12-16 20:23
 **/
@Slf4j
public class ConditionChange {

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
                    // 第一个消费线程执行后，删除数组中的数据。第二个消费线程执行后，发现数组为空，数组已经没有元素，报错。
                    if (lock.isEmpty()){
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

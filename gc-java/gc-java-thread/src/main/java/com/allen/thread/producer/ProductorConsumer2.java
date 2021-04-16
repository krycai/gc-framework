package com.allen.thread.producer;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: MultiThread
 * @description: 使用Lock中Condition的await/signalAll实现生产者-消费者
 * @author: allen小哥
 * @Date: 2019-12-16 21:05
 **/
@Slf4j
public class ProductorConsumer2 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition full = lock.newCondition();
    private static Condition empty = lock.newCondition();

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            service.submit(new Productor(linkedList, 8, lock));
        }
        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer(linkedList, lock));
        }
    }

    // 生产者
    static class Productor implements Runnable {
        private List<Integer> list;
        private int maxLenth;
        private Lock lock;

        public Productor(List list, int maxLenth, Lock lock) {
            this.list = list;
            this.maxLenth = maxLenth;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (list.size() == maxLenth) {
                        log.info("生产者-当前线程:{}", Thread.currentThread().getName() + "List 以达到最大容量，进行wait");
                        list.wait();
                        log.info("生产者-当前线程:{}", Thread.currentThread().getName() + "退出wait");
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    log.info("生产者-当前线程:{}", Thread.currentThread().getName() + "生产数据" + i);
                    list.add(i);
                    empty.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    //消费者
    static class Consumer implements Runnable {
        private List<Integer> list;
        private Lock lock;

        public Consumer(List list, Lock lock) {
            this.list = list;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (list.isEmpty()) {
                        log.info("消费者-当前线程:{}", Thread.currentThread().getName() + "List为空，进行wait");
                        list.wait();
                        log.info("消费者-当前线程:{}", Thread.currentThread().getName() + "退出wait");
                    }
                    Integer remove = list.remove(0);
                    log.info("消费者-当前线程:{}", Thread.currentThread().getName() + "消费数据" + remove);
                    full.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}

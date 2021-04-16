package com.allen.thread.producer;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: MultiThread
 * @description: 使用BlockingQueue实现生产者-消费者
 * 即当队列已满时，阻塞向队列中插入数据的线程，直至队列中未满；当队列为空时，阻塞从队列中获取数据的线程，直至队列非空时为止
 * @author: allen小哥
 * @Date: 2019-12-16 21:05
 **/
@Slf4j
public class ProductorConsumer5 {

    private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            service.submit(new Productor(queue));
        }
        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer(queue));
        }
    }

    // 生产者
    static class Productor implements Runnable {
        BlockingQueue queue;

        public Productor(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    log.info("生产者-当前线程:{}", Thread.currentThread().getName() + "List 以达到最大容量，进行wait");
                    Random random = new Random();
                    int i = random.nextInt();
                    log.info("生产者-当前线程:{}", Thread.currentThread().getName() + "生产数据" + i);
                    queue.add(i);//放进阻塞队列中
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //消费者
    static class Consumer implements Runnable {
        BlockingQueue queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    log.info("消费者-当前线程:{}", Thread.currentThread().getName() + "List为空，进行wait");
                    Object take = queue.take();
                    log.info("消费者-当前线程:{}", Thread.currentThread().getName() + "消费数据" + take);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

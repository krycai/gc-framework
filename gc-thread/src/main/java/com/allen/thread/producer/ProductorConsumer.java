package com.allen.thread.producer;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: MultiThread
 * @description: ProductorConsumer
 * @author: allen小哥
 * @Date: 2019-12-16 20:43
 **/
@Slf4j
public class ProductorConsumer {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            service.submit(new Productor(linkedList, 8));
        }
        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer(linkedList));
        }
    }

    // 生产者
    static class Productor implements Runnable {
        private List<Integer> list;
        private int maxLenth;

        public Productor(List list, int maxLenth) {
            this.list = list;
            this.maxLenth = maxLenth;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    try {
                        while (list.size() == maxLenth) { // 数组大小确定之后，生产者线程进入等待状态
                            log.info("生产者-当前线程:{}", Thread.currentThread().getName() + "List 以达到最大容量，进行wait");
                            list.wait();
                            log.info("生产者-当前线程:{}", Thread.currentThread().getName() + "退出wait");
                        }
                        Random random = new Random();
                        int i = random.nextInt();
                        log.info("生产者-当前线程:{}", Thread.currentThread().getName() + "生产数据" + i);
                        list.add(i);
                        list.notifyAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //消费者
    static class Consumer implements Runnable {
        private List<Integer> list;

        public Consumer(List list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    try {
                        while (list.isEmpty()) { // 当数组为空后，消费者线程进入等待状态
                            log.info("消费者-当前线程:{}", Thread.currentThread().getName() + "List为空，进行wait");
                            list.wait();
                            log.info("消费者-当前线程:{}", Thread.currentThread().getName() + "退出wait");
                        }
                        Integer remove = list.remove(0);
                        log.info("消费者-当前线程:{}", Thread.currentThread().getName() + "消费数据" + remove);
                        list.notifyAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

package com.allen.algorithm.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印奇偶数
 * Function: 两个线程交替执行打印 1~100  ，关键点：volatile 的可见性
 *
 * Created by xuguocai on 2021/1/6 14:23
 */
public class TwoThread {

    private int start = 1;

    /**
     * 对 flag 的写入虽然加锁保证了线程安全，但读取的时候由于 不是 volatile 所以可能会读取到旧值
     *
     */
    private volatile boolean flag = false;

    /**
     * 重入锁
     */
    private final static Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        TwoThread twoThread = new TwoThread();
        // 偶数线程
        Thread t1 = new Thread(new OuNum(twoThread));
        t1.setName("t1");

        // 奇数线程
        Thread t2 = new Thread(new JiNum(twoThread));
        t2.setName("t2");

        t1.start();
        t2.start();
    }

    /**
     * 偶数线程
     */
    public static class OuNum implements Runnable {

        private TwoThread number;

        public OuNum(TwoThread number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= 10) { //遍历 1000

                if (number.flag) { // true 则打印
                    try {
                        LOCK.lock(); // 加锁
                        System.out.println(Thread.currentThread().getName() + "+-+" + number.start);
                        number.start++;
                        number.flag = false;// 切换奇数变量，运用内存的可见性特点
                    } finally {
                        LOCK.unlock(); // 释放锁
                    }
                }
            }
        }
    }

    /**
     * 奇数线程
     */
    public static class JiNum implements Runnable {

        private TwoThread number;

        public JiNum(TwoThread number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= 10) { // 遍历 1000

                if (!number.flag) { // 执行条件
                    try {
                        LOCK.lock(); // 加锁
                        System.out.println(Thread.currentThread().getName() + "====" + number.start);
                        number.start++;
                        number.flag = true; // 改变执行线程
                    } finally {
                        LOCK.unlock(); // 释放锁
                    }
                }
            }
        }
    }
}

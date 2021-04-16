package com.allen.thread.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: MultiThread   (信号量)-允许多个线程同时访问
 * @description: SemaphoreTest
 * @author: allen小哥
 * @Date: 2019-12-15 17:14
 * Semaphore可以理解为信号量，用于控制资源能够被并发访问的线程数量，以保证多个线程能够合理的使用特定资源。
 * Semaphore就相当于一个许可证，线程需要先通过acquire方法获取该许可证，该线程才能继续往下执行，否则只能在该方法出阻塞等待。
 * 当执行完业务功能后，需要通过release()方法将许可证归还，以便其他线程能够获得许可证继续执行。
 *
 * Semaphore 经常用于限制获取某种资源的线程数量。有两种模式，公平模式和非公平模式。
 *
 * 公平模式： 调用 acquire 的顺序就是获取许可证的顺序，遵循 FIFO；
 * 非公平模式： 抢占式的。
 **/
@Slf4j
public class SemaphoreTest {
    // 请求的数量
    private static final int threadCount = 550;
    //  创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
    private static ExecutorService threadPool = Executors.newFixedThreadPool(300);

    private static Semaphore semaphore =new Semaphore(3);

    public static void main(String[] args) {
//        test();
        test2();
    }

    public static void test2(){
        // 一次只能允许执行的线程数量。
        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {// Lambda 表达式的运用
                try {
                    semaphore.acquire();// 获取一个许可，所以可运行线程数量为20/1=20
                    testAA(threadnum);
                    semaphore.release();// 释放一个许可
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            });
        }
        //启动有序关闭，在该关闭中执行先前提交的任务，但不接受新任务。如果已关闭，则调用不会产生任何其他影响。
        threadPool.shutdown();
        log.info("finish");
    }

    public static void testAA(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        log.info("threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }

    public static void test(){
        for (int i = 0;i<6;i++){
            threadPool.execute(() ->{
                try {
                    log.info("当前线程:{}",Thread.currentThread().getName()+"同学准备获取笔");
                    semaphore.acquire();
                    log.info("当前线程:{}",Thread.currentThread().getName()+"同学获取到笔");
                    log.info("当前线程:{}",Thread.currentThread().getName()+"填写表格ing");
                    TimeUnit.SECONDS.sleep(3);
                    semaphore.release();
                    log.info("当前线程:{}",Thread.currentThread().getName()+"填写完表格，归还");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

}

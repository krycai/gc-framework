package com.allen.thread.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: MultiThread
 * @description: SemaphoreTest
 * @author: allen小哥
 * @Date: 2019-12-15 17:14
 * Semaphore可以理解为信号量，用于控制资源能够被并发访问的线程数量，以保证多个线程能够合理的使用特定资源。
 * Semaphore就相当于一个许可证，线程需要先通过acquire方法获取该许可证，该线程才能继续往下执行，否则只能在该方法出阻塞等待。
 * 当执行完业务功能后，需要通过release()方法将许可证归还，以便其他线程能够获得许可证继续执行。
 **/
@Slf4j
public class SemaphoreTest {

    private static Semaphore semaphore =new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(6);
        for (int i = 0;i<6;i++){
            service.execute(() ->{
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
        service.shutdown();

    }

}

package com.allen.thread.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: MultiThread
 * @description: ThreadPoolExecutorTest
 *
 * execute方法执行逻辑有这样几种情况：
 *
 * 如果当前运行的线程少于corePoolSize，则会创建新的线程来执行新的任务；
 * 如果运行的线程个数等于或者大于corePoolSize，则会将提交的任务存放到阻塞队列workQueue中；
 * 如果当前workQueue队列已满的话，则会创建新的线程来执行任务；
 * 如果线程个数已经超过了maximumPoolSize，则会使用饱和策略RejectedExecutionHandler来进行处理。
 *
 * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)
 *
 * @author: allen小哥
 * @Date: 2019-12-15 13:45
 **/
@Slf4j
public class ThreadPoolExecutorTest {

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("allen-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(5,20,0L,TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),threadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0;i<Integer.MAX_VALUE;i++){
            pool.execute(new PoolThread("2019-11-25 09:00:"+i % 60));
        }
    }

    static class PoolThread implements Runnable{

        private String date;

        public PoolThread(String date) {
            this.date = date;
        }

        @Override
        public void run() {
            log.info("date:{}",date);
        }
    }
}

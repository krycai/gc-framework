package com.allen.thread.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuguocai on 2021/2/4 14:13
 *
 * 线程首先会先执行 5 个任务，然后这些任务有任务被执行完的话，就会去拿新的任务执行。
 *
 *
 * https://snailclimb.gitee.io/javaguide/#/./docs/java/multi-thread/java%E7%BA%BF%E7%A8%8B%E6%B1%A0%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93
 *
 * 对于   executor.execute(worker); 可以深入研究
 */
public class ThreadPoolExecutorDemo {
    //  核心线程数为 5。
    private static final int CORE_POOL_SIZE = 5;
    // 最大线程数 10
    private static final int MAX_POOL_SIZE = 10;
    //任务队列为 ArrayBlockingQueue，并且容量为 100
    private static final int QUEUE_CAPACITY = 100;
    //等待时间为 1L。
    private static final Long KEEP_ALIVE_TIME = 1L;
    public static void main(String[] args) {

        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()); // 饱和策略为 CallerRunsPolicy。

        for (int i = 0; i < 10; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new MyRunnable("" + i);
            //执行Runnable
            executor.execute(worker);
        }
        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}

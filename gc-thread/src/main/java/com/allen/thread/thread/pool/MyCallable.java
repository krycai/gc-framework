package com.allen.thread.thread.pool;

import java.util.concurrent.Callable;

/**
 * Created by xuguocai on 2021/2/4 14:46
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        //返回执行当前 Callable 的线程名字
        return Thread.currentThread().getName();
    }
}

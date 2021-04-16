package com.allen.thread.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: MultiThread
 * @description: 线程的实现方式
 * @author: allen小哥
 * @Date: 2019-11-26 20:30
 **/
public class ThreadDemo {

    public static void main(String[] args) {
        // 继承 Thread
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("通过继承实现线程");
                super.run();
            }
        };
        thread.start();

        // 实现Runable
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("通过实现接口实现线程");
            }
        });
        threadA.start();

        // 实现Callable
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "通过实现Callable实现线程";
            }
        });
        try {
            String result = future.get();
            System.out.println("执行future成功:"+result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

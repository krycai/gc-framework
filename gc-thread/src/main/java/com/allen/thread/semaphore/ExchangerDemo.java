package com.allen.thread.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: MultiThread
 * @description: 用于线程间协作的工具类 用于两个线程间能够交换
 * 它提供了一个交换的同步点，在这个同步点两个线程能够交换数据。
 * 具体交换数据是通过exchange方法来实现的，如果一个线程先执行exchange方法，
 * 那么它会同步等待另一个线程也执行exchange方法，这个时候两个线程就都达到了同步点，两个线程就可以交换数据。
 * @author: allen小哥
 * @Date: 2019-12-15 17:30
 **/
@Slf4j
public class ExchangerDemo {

    private static Exchanger<String> exchanger = new Exchanger();
    //两个线程就能交换彼此的数据
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        //甲方
        service.execute(() ->{
            try{
                String exchange = exchanger.exchange("我其实暗恋你很久了=======");
                log.info("参与的甲方发表的表白感言:{}",exchange);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        // 乙方
        service.execute(() ->{
            try {
                log.info("乙方世界开始");
                TimeUnit.SECONDS.sleep(3);
                String other = exchanger.exchange("我也很喜欢你");
                log.info("甲方说:{}",other);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }

}

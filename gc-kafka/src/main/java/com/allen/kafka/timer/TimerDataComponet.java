package com.allen.kafka.timer;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * Created by xuguocai on 2021/3/18 11:24  定时器轮询操作  ：注意启动的时候开启定时器
 */
@Component
public class TimerDataComponet {

    /**
     * Spring Redis Template
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("TimerData-Thread-%d").build();

    private ScheduledExecutorService  scheduledExecutorService = new ScheduledThreadPoolExecutor(5, threadFactory,
            new ThreadPoolExecutor.AbortPolicy());


    public void timer(){
        Runnable runnable = new Runnable() {
            //创建 run 方法
            @Override
            public void run() {
                // task to run goes here
                System.out.println("Hello, stranger");
//                String msg = redisTemplate.opsForList().rightPop("allen-test-topic");
//                System.out.println("redis消息:"+msg);
            }
        };

        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        // 10：秒   5：秒
        // 第一次执行的时间为10秒，然后每隔五秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(runnable,10, 5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
//        timer();
    }
}

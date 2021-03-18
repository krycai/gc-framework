package com.allen.kafka;

import com.allen.kafka.timer.TimerDataComponet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xuguocai on 2021/3/16 14:23
 */

@SpringBootApplication
public class KafkaApp {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApp.class, args);
        // 启动轮询
        TimerDataComponet timerDataComponet = new TimerDataComponet();
        timerDataComponet.timer();
    }
}

package com.allen.kafka.controller;

import com.allen.kafka.send.AllenKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuguocai on 2021/3/18 9:52
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private AllenKafkaProducer allenKafkaProducer;


    @GetMapping("/send")
    public String send(){
        for (int i = 0;i<1000;i++){
            String msg = "发送信息，测试失败处理，次数为:"+i;

            allenKafkaProducer.send("allen-test-topic",msg);
        }

        return "ddd";
    }

}

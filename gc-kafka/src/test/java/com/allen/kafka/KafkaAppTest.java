package com.allen.kafka;

import com.allen.kafka.send.AllenKafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xuguocai on 2021/3/16 15:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApp.class)
public class KafkaAppTest {

    @Autowired
    private AllenKafkaProducer allenKafkaProducer;

    @Test
    public void send(){
        for (int i = 0;i < 1000000;i++){
            String msg = "测试Kafka发送数据次数: "+i+" 次";
            allenKafkaProducer.send("allen-test-topic",msg);
        }
    }
}

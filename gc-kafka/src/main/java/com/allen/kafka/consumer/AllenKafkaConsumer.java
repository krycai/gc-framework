package com.allen.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 验证 kafka 是否 正常消费，部署前需要注释 @KafkaListener
 * @author xuguocai 2020/8/17 14:55
 */
@Component
public class AllenKafkaConsumer {

    @KafkaListener(topics = "allen-test-topic")
    public void pullMessage(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("----------------- record =" + record);
            System.out.println("------------------ message =" + message);
        }
    }

}

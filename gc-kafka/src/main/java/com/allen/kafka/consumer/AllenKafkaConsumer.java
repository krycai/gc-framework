package com.allen.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
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

    /**
     * enable-auto-commit = false
     * @param record
     * @param ack
     */
//    @KafkaListener(containerFactory = "ackContainerFactory",topics = "allen-test-topic")
    public void pullMessage(ConsumerRecord record, Acknowledgment ack) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("----------------- record =" + record);
            System.out.println("------------------ message =" + message);
            // 处理消息 todo 业务处理
           // process(records);
            // 手动提交
            if (ack !=null){
                ack.acknowledge();
            }
            //commitAysnc();

        }
    }
}

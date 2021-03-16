package com.allen.kafka.send;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * kafka 生产者
 */
@Component
public class AllenKafkaProducer {
    private static Logger log = LogManager.getLogger("ASYNFILE");

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 消息推送
     * @param topic  主题
     * @param message  消息体
     */
    public boolean send(String topic,String message) {
        boolean flag = true;
        try {
            kafkaTemplate.send(topic, message);
            log.info("推送主题：{}，消息体：\n{}",topic,message);
        }catch (Exception e){
            log.error("推送消息失败，消息的主题:{}",topic);
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

}

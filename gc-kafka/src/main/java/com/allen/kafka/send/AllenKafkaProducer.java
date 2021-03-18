package com.allen.kafka.send;

import com.allen.kafka.storage.StorageFailData;
import com.allen.kafka.timer.TimerDataComponet;
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

    @Autowired
    private StorageFailData storageFailData;

    @Autowired
    private TimerDataComponet timerDataComponet;

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

            // redis 缓存失败数据  ，此处要增加 重试机制，超过的话丢弃
            storageFailData.sendDataToRedis(topic,message);
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }



}

package com.allen.kafka.storage;

import com.allen.kafka.send.AllenKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuguocai on 2021/3/18 9:08 存 Kafka 发送失败的数据
 */
@Component
public class StorageFailData {

    // 利用 数组 存储
    private static Map<String,List<String>> map = new HashMap<>();

    /**
     * Spring Redis Template 利用redis处理
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    // 利用 MySQL数据库 处理

    @Autowired
    private AllenKafkaProducer allenKafkaProducer;

    /**
     * 数组存储 发送失败的 数据
     * @param msg
     */
    public void sendDataToList(String topic,String msg){
        List<String> strings = map.get(topic);
        if (CollectionUtils.isEmpty(strings)){
            strings = new ArrayList<>();
        }

        strings.add(msg);
        map.put(topic,strings);
    }

    /**
     * 利用 redis 缓存数据
     * @param topic
     * @param msg
     */
    public void sendDataToRedis(String topic,String msg){
        // 数组，数据从左边进右边出
        redisTemplate.opsForList().leftPush(topic,msg);
    }

    public Map<String,List<String>> consumerList(){
        return map;
    }



}

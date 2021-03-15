package com.allen.redis.example;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.MappingSqlQueryWithParameters;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuguocai on 2021/3/15 9:07   缓存穿透
 */
public class CachePass {

    /**
     * 操作 redis  缓存
     */
    private StringRedisTemplate redisTemplate;

    /**
     * 操作数据库
     */
    private JdbcTemplate jdbcTemplate;

    public CachePass(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 思路：
     *   1、查 缓存 的结果
     *   2、若没有缓存，查 数据库
     *     2.1 数据库有值，则更新 缓存
     *     2.2 数据库无值，则设置 key 过期时间
     *   3、有缓存，则返回
     *
     * @param key
     * @return
     */
    public Object getObjectInclNullById(String key) {
        // 从缓存中获取数据
        Object cacheValue = redisTemplate.getExpire(key);
        // 缓存为空
        if (cacheValue == null) {
            // 从数据库中获取
            Object storageValue = null;//jdbcTemplate.query(null,null);
            // 缓存空对象
//            cache.set(key, storageValue);
            // 如果存储数据为空，需要设置一个过期时间(300秒)
            if (storageValue == null) {
                // 必须设置过期时间，否则有被攻击的风险
                //cache.expire(key, 60 * 5);
            }
            return storageValue;
        }
        return cacheValue;
    }


}

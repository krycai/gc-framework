package com.allen.redis.core.example;

import cn.hutool.core.util.StrUtil;
import com.allen.redis.core.repository.RedisRepository;
import com.allen.redis.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xuguocai on 2021/6/4 15:52  缓存击穿 处理
 */
public class BreakDown {

    private static final String key_mutex = "test";

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 缓存击穿 的处理
     * @param cacheKey
     * @return
     * @throws InterruptedException
     */
    public String breakDown(String cacheKey) throws InterruptedException {
        // 获取 缓存值
        String value = redisRepository.get(cacheKey);
        //代表缓存值过期
        if (StrUtil.isBlank(value)) {
            //设置60s的超时，防止del操作失败的时候，下次缓存过期一直不能load db
            if (redisRepository.setNxex(key_mutex,60)) {  //代表设置成功
                value = sysUserMapper.selectAll().toString();
                redisRepository.setExpire(cacheKey,value,60);
                redisRepository.del(key_mutex);

            } else {  //这个时候代表同时候的其他线程已经load db并回设到缓存了，这时候重试获取缓存值即可
                Thread.sleep(50);
                //重试
                breakDown(cacheKey);
            }
        }
        return value;
    }
}

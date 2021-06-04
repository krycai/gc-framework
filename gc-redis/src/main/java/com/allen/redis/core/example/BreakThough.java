package com.allen.redis.core.example;

import cn.hutool.core.util.StrUtil;
import com.allen.redis.core.repository.RedisRepository;
import com.allen.redis.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xuguocai on 2021/6/4 15:23  缓存穿透
 */
public class BreakThough {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 缓存穿透 处理逻辑
     * @return
     */
    public String breakThrough(){
        // 定义缓存时间
        int cacheTime = 30;
        // 缓存 kay
        String cacheKey = "product_list";
        // 从 redis 取值
        String cacheValue = redisRepository.get(cacheKey);
        if (StrUtil.isNotBlank(cacheValue)) {
            // 有就返回
            return cacheValue;
        }
        // 此处获取redis 的值（若是其他人操作，已经赋值（集群操作），这里直接取值）
        cacheValue = redisRepository.get(cacheKey);
        if (StrUtil.isNotBlank(cacheValue)) {
            return cacheValue;
        } else {
            //数据库查询不到，为空
            cacheValue = sysUserMapper.selectAll().toString();
            if (cacheValue == null) {
                //如果发现为空，设置个默认值，也缓存起来
                cacheValue = "";
            }
            redisRepository.setExpire(cacheKey,cacheValue,cacheTime);
            return cacheValue;
        }
    }

}

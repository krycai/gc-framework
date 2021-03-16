package com.allen.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created by xuguocai on 2021/3/16 10:34  数据结构：string  list set hash sort set bitmap
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApp.class)
public class RedisAppTest {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *  rpush,lpop,lpush,rpop,lrange、llen
     */
    @Test
    public void rpush(){
        List<String> list = new ArrayList<>();
        list.add("xu");
        list.add("guo");
        list.add("cai");
        list.add("gai");
        list.add("jian");
        list.add("fei");
        System.out.println("原来的数据:"+list);
        // 左边插入数组
        redisTemplate.opsForList().leftPushAll("mylist",list);
        // 右边插入数组
        redisTemplate.opsForList().rightPushAll("mylist",list);

        System.out.println("==============获取数据元素================");
        // 左边取出元素
        Object leftPop = redisTemplate.opsForList().leftPop("mylist");
        System.out.println("leftPop:"+leftPop);
        // 右边插入元素
        redisTemplate.opsForList().rightPush("mylist","one");
        // 右边取出元素（元素取出后就不在数组）
        Object mylist = redisTemplate.opsForList().rightPop("mylist");
        // 右边取出元素
        Object mylist2 = redisTemplate.opsForList().rightPop("mylist");

        System.out.println("mylist:"+mylist+" mylist2:"+mylist2);

        System.out.println("=============分页===========");
        // 取出数组下标 0 到 4 的数组
        List mylist1 = redisTemplate.opsForList().range("mylist", 0, 4);
        System.out.println(mylist1);
        // 取出数组 长度
        Long size = redisTemplate.opsForList().size("mylist");

        System.out.println(size);
    }

    /**
     *  sadd,spop,smembers,sismember,scard,sinterstore,sunion
     */
    @Test
    public void pushSet(){
        Set<String> list = new HashSet<>();
        list.add("xu");
        list.add("guo");
        list.add("cai");
        list.add("gai");
        list.add("jian");
        list.add("fei");
        System.out.println("原来的数据:"+list);
        // 插入 set 数据
        redisTemplate.opsForSet().add("myset",list);

        for (String item:list){
            redisTemplate.opsForSet().add("myset",item);
        }

//        System.out.println("获取数据元素================"+redisTemplate.opsForSet().pop("myset"));
        // 添加元素
         redisTemplate.opsForSet().add("myset","one");
        // 集合中是否有元素
        Boolean member = redisTemplate.opsForSet().isMember("myset", "one555");
        System.out.println("member:"+member);
        // 集合的元素
        Set myset = redisTemplate.opsForSet().members("myset");
        System.out.println("myset:"+myset);

        // 随机获取元素
        Object myset1 = redisTemplate.opsForSet().randomMember("myset");
        System.out.println(myset1);
        // 移除 元素
        Long remove = redisTemplate.opsForSet().remove("myset", "fei");
        System.out.println(remove);
    }

    /**
     * set,hmset,hexists,hget,hgetall,hkeys,hvals
     */
    @Test
    public void pushHash(){
        Set<String> list = new HashSet<>();
        list.add("xu");
        list.add("guo");
        list.add("cai");
        list.add("gai");
        list.add("jian");
        list.add("fei");
        System.out.println("原来的数据:"+list);
        for (String item:list){
            redisTemplate.opsForHash().put("myhash",item,item);
        }

        Map myhash = redisTemplate.opsForHash().entries("myhash");
        System.out.println(myhash);

        Object o = redisTemplate.opsForHash().get("myhash", "xu");
        System.out.println(o);

        Set myhash1 = redisTemplate.opsForHash().keys("myhash");
        System.out.println("keys:"+myhash1);

        Boolean aBoolean = redisTemplate.opsForHash().hasKey("myhash", "xu");
        System.out.println(aBoolean);

        Long myhash2 = redisTemplate.opsForHash().size("myhash");
        System.out.println(myhash2);

    }

    /**
     * zadd,zcard,zscore,zrange,zrevrange,zrem
     */
    @Test
    public void pushSortSet(){
        List<String> list = new ArrayList<>();
        list.add("xu");
        list.add("guo");
        list.add("cai");
        list.add("gai");
        list.add("jian");
        list.add("fei");

        for (int i=0;i<list.size();i++){
            // 添加元素
            redisTemplate.opsForZSet().add("mysortset",list.get(i),i+1);
        }
        // 取出元素的 权重
        Double score = redisTemplate.opsForZSet().score("mysortset", "xu");
        System.out.println(score);

        // 获取 数组
        Set mysortset = redisTemplate.opsForZSet().range("mysortset", 0, 3);
        System.out.println(mysortset);

        //
        Long rank = redisTemplate.opsForZSet().rank("mysortset", "guo");
        System.out.println(rank);

        // 获取元素个数
        Long mysortset1 = redisTemplate.opsForZSet().zCard("mysortset");
        System.out.println(mysortset1);
    }

    /**
     * setbit 、getbit 、bitcount、bitop
     */
    @Test
    public void pushBitmap(){
        Boolean mybitmap = redisTemplate.opsForValue().setBit("mybitmap", 80556397, true);
        System.out.println(mybitmap);
        Boolean mybitmap1 = redisTemplate.opsForValue().getBit("mybitmap", 80556397);
        System.out.println(mybitmap1);

    }
}

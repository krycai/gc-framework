package com.allen.thread.thread;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by xuguocai on 2021/2/5 9:08
 */
@Slf4j
public class ThreadLocalTestDemo {
    private List<String> messages = Lists.newArrayList();

    public static final ThreadLocal<ThreadLocalTestDemo> holder = ThreadLocal.withInitial(ThreadLocalTestDemo::new);

    public static void add(String message) {
        holder.get().messages.add(message);
        log.info("添加数组后的数据信息:{},数组大小:{}",message,holder.get().messages.size());
    }

    public static List<String> clear() {
        List<String> messages = holder.get().messages;
        holder.remove();

        log.info("执行clear的size:{}，数组：{} " , holder.get().messages.size(),messages);
        return messages;
    }

    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
//        ThreadLocalTestDemo.add("一枝花算不算浪漫");
//        log.info("数组信息:{}",holder.get().messages);
//        ThreadLocalTestDemo.clear();

        int hashCode = 0;
        for (int i =0;i<10;i++){
            hashCode = i+HASH_INCREMENT+HASH_INCREMENT;
            int bucket = hashCode & 15;
            System.out.println(i+"===="+bucket);
        }
    }
}

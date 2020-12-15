package com.allen.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: MultiThread
 * @description: LockDemo2
 * @author: allen小哥
 * @Date: 2019-12-02 20:26
 **/
@Slf4j
public class LockDemo2 {

    private static ReentrantLock lock = new ReentrantLock();

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ReentrantReadWriteLockCacheSystem();
    }

    // 读写锁
    public static void ReentrantReadWriteLockCacheSystem() {

        //这里为了实现简单，将缓存大小设置为4。
        Map<String, String> cacheMap = new HashMap<>(4);
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        for (int i = 0; i < 20; i++) { //同时开启20个线程访问缓存

            final String key = String.valueOf(i % 4);
            log.info(Thread.currentThread().getName()+"key:{}",key);
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        //①读取缓存时获取读锁
                        readWriteLock.readLock().lock();
                        //获取读锁后通过key获取缓存中的值
                        String valueStr = cacheMap.get(key);
                        log.info("value:{}",valueStr);
                        //缓存值不存在
                        if (valueStr == null) {
                            //③释放读锁后再尝试获取写锁
                            // 读不到数据，就关闭读锁，开启写锁
                            readWriteLock.readLock().unlock();  //关闭读锁
                            try {
                                //④获取写锁来写入不存在的key值，
                                readWriteLock.writeLock().lock();
                                valueStr = cacheMap.get(key);
                                log.info("key:{}"+key+",关闭读锁后value:{}",valueStr);
                                if (valueStr == null) {
                                    valueStr = key + " --- value";
                                    log.info("为锁赋值：{}",valueStr);
                                    cacheMap.put(key, valueStr); //写入值
                                    System.out.println(Thread.currentThread().getName() + " --------- put " + valueStr);
                                }
                                // ⑥锁降级，避免被其他写线程抢占后再次更新值，保证这一次操作的原子性
                                readWriteLock.readLock().lock();
                                log.info("读锁操作：{}"+key);
                                System.out.println(Thread.currentThread().getName() + " --------- get new " + valueStr);
                            } finally {
                                log.info("写锁操作:{}",key);
                                readWriteLock.writeLock().unlock(); //⑤释放写锁
                            }

                        } else {
                            System.out.println(Thread.currentThread().getName() + " ------ get cache value");
                        }
                    } finally {
                        log.info("释放读锁:{}",key);
                        readWriteLock.readLock().unlock();  //②释放读锁
                    }
                }
            }, String.valueOf(i));
            thread.start();
        }
    }

}

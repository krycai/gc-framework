package com.allen.lock.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author xuguocai 2020/9/3 11:12
 */
@Service
@Slf4j
public class ZooKeeperService {

    private static final String lockPath = "/lock/order";
    @Autowired
    private CuratorFramework zkClient;

    public void makeOrder(String product) {
        log.info("try do job for " + product);
        String path = lockPath + "/" + product;

        try {
            // InterProcessMutex 构建一个分布式锁
            InterProcessMutex lock = new InterProcessMutex(zkClient, path);

            try {
                if (lock.acquire(5, TimeUnit.HOURS)) { // 获得锁
                    // 模拟业务处理耗时5秒
                    Thread.sleep(5*1000);
                    log.info("do job " + product + " done");
                }
            } finally {
                // 释放该锁
                lock.release();
            }

        } catch (Exception e) {
            log.error("失败:{}",e.getMessage());
            // zk异常
            e.printStackTrace();
        }
    }
}

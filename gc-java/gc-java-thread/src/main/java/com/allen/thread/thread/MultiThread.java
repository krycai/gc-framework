package com.allen.thread.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: MultiThread
 * @description: MultiThread
 * Java 程序天生就是多线程程序，我们可以通过 JMX 来看一下一个普通的 Java 程序有哪些线程
 * @author: allen小哥
 * @Date: 2020-01-09 21:25
 **/
@Slf4j
public class MultiThread {

    public static void main(String[] args) {
//        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
//        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
//        for (ThreadInfo item : threadInfos){
//            log.info("线程ID：{}",item.getThreadName());
//        }

        // 类加载器
        log.info("==="+MultiThread.class.getClassLoader());
        log.info("parent===="+MultiThread.class.getClassLoader().getParent());
        log.info("--22=="+MultiThread.class.getClassLoader().getParent().getParent());
    }

}

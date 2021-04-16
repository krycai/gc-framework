package com.allen.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 基本类型原子类介绍
 * @program: MultiThread
 * @description: AtomicDemo
 * @author: allen小哥
 * @Date: 2019-12-15 14:28
 *
 * CAS的原理是拿期望的值和原本的一个值作比较，如果相同则更新成新的值。UnSafe 类的 objectFieldOffset() 方法是一个本地方法，
 * 这个方法是用来拿到“原来的值”的内存地址。另外 value 是一个volatile变量，在内存中可见，因此 JVM 可以保证任何时刻任何线程总能拿到该变量的最新值。
 *
 **/
@Slf4j
public class AtomicDemo {
    //整型原子类
    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    //布尔型原子类
    private static AtomicBoolean atomicBoolean = new AtomicBoolean();
    //长整型原子类
    private static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) {
        // 获取当前的值
        log.info("获取当前的值:{}",atomicInteger.get());
        //获取当前的值，并设置新的值
        log.info("获取当前的值，并设置新的值:{}",atomicInteger.getAndSet(5));
        //获取当前的值，并自增
        log.info("获取当前的值，并自增:{}",atomicInteger.getAndIncrement());
        //获取当前的值，并自减
        log.info("获取当前的值，并自减:{}",atomicInteger.getAndDecrement());
        //获取当前的值，并加上预期的值
        log.info("获取当前的值，并加上预期的值:{}",atomicInteger.getAndAdd(5));
        //如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update）
        log.info("如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update）:{}",atomicInteger.compareAndSet(10,15));
        //最终设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
        atomicInteger.lazySet(20);
        log.info(" lazySet 设置之后:{}",atomicInteger.get());

    }
    //=======================优势=====================================
    // 多线程环境不使用原子类保证线程安全（基本数据类型）
    class Test {
        private volatile int count = 0;
        //若要线程安全执行执行count++，需要加锁
        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
    //多线程环境使用原子类保证线程安全（基本数据类型）
    class Test2 {
        private AtomicInteger count = new AtomicInteger();

        public void increment() {
            count.incrementAndGet();
        }
        //使用AtomicInteger之后，不需要加锁，也可以实现线程安全。
        public int getCount() {
            return count.get();
        }
    }

}

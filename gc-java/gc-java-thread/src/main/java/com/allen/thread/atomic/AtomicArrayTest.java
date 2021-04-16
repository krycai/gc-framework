package com.allen.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @program: MultiThread  数组类型原子类
 * @description: AtomicArrayTest
 * @author: allen小哥
 * @Date: 2019-12-15 14:32
 *
 **/
@Slf4j
public class AtomicArrayTest {

    private static int[] values = new int[]{1,2,3,4,5};
    private static long[] longs = new long[]{1,2,3};
    private static String[] strings = new String[]{"a","b"};
    //整形数组原子类
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(values);
    //长整形数组原子类
    private static AtomicLongArray atomicLongArray = new AtomicLongArray(longs);
    //引用类型数组原子类
    private static AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(strings);

    public static void main(String[] args) {
        //获取 index=i 位置元素的值
        log.info("获取 index=i 位置元素的值:{}",atomicIntegerArray.get(0));
        //返回 index=i 位置的当前的值，并将其设置为新值：newValue
        log.info("返回 index=i 位置的当前的值，并将其设置为新值：newValue:{}",atomicIntegerArray.getAndSet(1,5));
        //获取 index=i 位置元素的值，并让该位置的元素自增
        log.info("获取 index=i 位置元素的值，并让该位置的元素自增:{}",atomicIntegerArray.getAndIncrement(2));
        // 获取 index=i 位置元素的值，并让该位置的元素自减
        log.info("获取 index=i 位置元素的值，并让该位置的元素自减:{}",atomicIntegerArray.getAndDecrement(2));
        // 获取 index=i 位置元素的值，并加上预期的值
        log.info("获取 index=i 位置元素的值，并加上预期的值:{}",atomicIntegerArray.getAndAdd(0,5));
        //如果输入的数值等于预期值，则以原子方式将 index=i 位置的元素值设置为输入值（update）
        log.info("如果输入的数值等于预期值，则以原子方式将 index=i 位置的元素值设置为输入值（update）:{},变更后的值:{}",atomicIntegerArray.compareAndSet(3,4,8),atomicIntegerArray.get(3));
        // 最终 将index=i 位置的元素设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
        atomicIntegerArray.lazySet(4,10);
        log.info("最终 将index=i 位置的元素设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。:{}",atomicIntegerArray.get(4));

    }

}

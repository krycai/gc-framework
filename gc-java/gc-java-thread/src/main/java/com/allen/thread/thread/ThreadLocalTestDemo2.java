package com.allen.thread.thread;

import java.lang.reflect.Field;

/**
 * Created by xuguocai on 2021/2/5 9:36
 */
public class ThreadLocalTestDemo2 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        Thread t = new Thread(()->test("abc",false));
        t.start();
        // join()  mian线程等待t 线程执行完了再执行
        t.join();
        System.out.println("--gc后--");
        Thread t2 = new Thread(() -> test("def", true));
        t2.start();
        t2.join(); //mian线程等待t2 线程执行完了再执行
    }

    // 反射关注弱引用，线程 ==》线程属性 ==》线程属性组成 ==》线程属性数组 == ？遍历数组的对象 == 》查看对象的引用类型
    private static void test(String s,boolean isGC)  {
        try {
            // new 对象 ，强引用 ，gc后还是会存在
            new ThreadLocal<>().set(s);
            if (isGC) {
                // 垃圾回收
                System.gc();
            }
            // 获取当前线程
            Thread t = Thread.currentThread();
            Class<? extends Thread> clz = t.getClass();
            // 线程的threadLocals属性
            Field field = clz.getDeclaredField("threadLocals");
            field.setAccessible(true);
            // 获取 ThreadLocalMap 对象
            Object ThreadLocalMap = field.get(t);
            Class<?> tlmClass = ThreadLocalMap.getClass();
            // 获取 ThreadLocalMap的数组
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(ThreadLocalMap);
            for (Object o : arr) {
                if (o != null) {
                    // 获取 ThreadLocalMap 数组中的值
                    Class<?> entryClass = o.getClass();
                    Field valueField = entryClass.getDeclaredField("value");
                    // 引用类型
                    Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    valueField.setAccessible(true);
                    referenceField.setAccessible(true);
                    System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

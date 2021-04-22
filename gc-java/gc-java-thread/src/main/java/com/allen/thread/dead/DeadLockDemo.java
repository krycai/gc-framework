package com.allen.thread.dead;

/**
 * @program: MultiThread
 * @description: DeadLockDemo
 * @author: allen小哥
 * @Date: 2019-11-26 15:40
 *
 * 测试过程:
 *  先找到线程号：jps -l
 *  根据线程号查找线程执行过程:jstack -l 83624
 *
 **/
public class DeadLockDemo {

    private static String resourceA = "A";
    private static String resourceB ="B";

    public static void deadLock(){
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                synchronized (resourceA){
                    System.out.println("获取 resourceA 锁:"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                        synchronized (resourceB){
                            System.out.println("获取 resourceB 锁："+Thread.currentThread().getName());
                        }
                    }catch (Exception e){
                        System.out.println("抛出异常,{}"+e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            public void run() {
                synchronized (resourceB){
                    System.out.println("获取 resourceA 锁:"+Thread.currentThread().getName());
                    synchronized (resourceA){
                        System.out.println("获取 resourceB 锁:"+Thread.currentThread().getName());
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
    }

    public static void main(String[] args) {
        deadLock();
    }

}

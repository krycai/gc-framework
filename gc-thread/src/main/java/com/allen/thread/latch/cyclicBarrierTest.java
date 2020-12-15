package com.allen.thread.latch;

import java.util.concurrent.CyclicBarrier;

/**
 * @program: MultiThread
 * @description: cyclicBarrierTest
 *特点:
 * 加计数方式
 *计数达到指定值时释放所有等待线程
 * 计数达到指定值时，计数置为0重新开始
 *调用await()方法计数加1，若加1后的值不等于构造方法的值，则线程阻塞
 * 可重复利用
 *
 * @author: allen小哥
 * @Date: 2019-11-14 22:33
 **/
public class cyclicBarrierTest {

    public static void main(String[] args) {
        // CyclicBarrier() 参数parties指让多少个线程或者任务等待至barrier状态；参数barrierAction为当这些线程都达到barrier状态时会执行的内容。
        CyclicBarrier cyclicBarrier =new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("线程组结束");
            }
        });
        for (int i=0;i<15;i++){
            new Thread(new ReadNum2(i,cyclicBarrier)).start();
        }
        System.out.println("cyclicBarrier = [线程执行完]");
    }

    //CyclicBarrier会在所有线程任务结束之后，才会进行后续任务
    static class ReadNum2 implements Runnable{

        private int id;
        private CyclicBarrier barrier;

        public ReadNum2(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        public void run() {
            synchronized (this){
                System.out.println("打印ID:"+id+" 线程名称为:"+Thread.currentThread().getName());
                try {
                    System.out.println("barrier的数值:"+barrier.getNumberWaiting());
                    barrier.await();
                    System.out.println("线程组任务"+id+" 结束，其他任务继续执行,此时barrier的数值:"+barrier.getNumberWaiting());
                }catch (Exception e){
                    System.out.println("发生异常，异常信息为:"+e.getMessage());
                }

            }
        }
    }

}

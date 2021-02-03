package com.allen.thread.latch;

import java.util.concurrent.CountDownLatch;

/**
 * @program: MultiThread
 * @description: countDownlatchTest
 *
 * 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 * 特点：
 * 减计数方式
 * 计算为0时释放所有等待的线程
 * 计数为0时，无法重置
 * 调用countDown()方法计数减一，调用await()方法只进行阻塞，对计数没任何影响
 * 不可重复利用
 *
 * @author: allen小哥
 * @Date: 2019-11-14 22:02
 **/
public class CountDownlatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch =new CountDownLatch(10); //需要10个线程完成任务
        for (int i=0;i<100;i++){
            // 生成线程
            new Thread(new ReadNum(i,countDownLatch)).start();
        }
        countDownLatch.await(); //要求主线程等待所有10个检查任务全部完成
        System.out.println("args = [ 线程结束]");
    }

    static class ReadNum implements Runnable{
        private int id;
        private CountDownLatch latch;

        public ReadNum(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        public void run() {
            synchronized (this){
                System.out.println("打印ID:"+id+" 线程名称为:"+Thread.currentThread().getName());
                latch.countDown(); //通知CountDownLatch一个线程已经完成了任务
                System.out.println("latch的数值:"+latch.getCount()+"  线程组任务:"+id+"  结束，其他任务继续");
            }
        }
    }

}

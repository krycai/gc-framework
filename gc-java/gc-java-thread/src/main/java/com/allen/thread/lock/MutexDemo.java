package com.allen.thread.lock;

/**
 * @program: MultiThread
 * @description: MutexDemo
 * @author: allen小哥
 * @Date: 2019-12-01 09:52
 **/
public class MutexDemo {

    private static Mutex mutex = new Mutex();

    public static void main(String[] args) {
        for (int i=0; i < 10; i++){
            Thread thread =new Thread(() ->{
                mutex.lock();
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    mutex.unlock();
                }
            });
            thread.start();
        }
    }

}

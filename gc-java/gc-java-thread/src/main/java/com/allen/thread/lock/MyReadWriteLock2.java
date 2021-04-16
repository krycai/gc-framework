package com.allen.thread.lock;

/**
 * @program: MultiThread
 * @description: MyReadWriteLock2
 *
 * https://juejin.im/post/5b9df6015188255c8f06923a
 *
 * @author: allen小哥
 * @Date: 2019-12-26 21:39
 **/
public class MyReadWriteLock2 {

    private int state = 0; //1. 定义一个读写锁共享变量state
    private int writeRequest = 0; //记录写请求数量

    //2. state高16位为读锁数量
    private int GetReadCount() {
        return state >>> 16;
    }

    //2. 低16位为写锁数量
    private int GetWriteCount() {
        return state & ((1 << 16) - 1);
    }

    //3. 获取读锁时先判断是否有写锁，有则等待，没有则将读锁数量加1
    public synchronized void lockRead() throws InterruptedException{
        //写锁数量大于0或者写请求数量大于0的情况下都优先执行写
        while (GetWriteCount() > 0 || writeRequest > 0) {
            wait();
        }

        System.out.println("lockRead ---" + Thread.currentThread().getName());
        state = state + (1 << 16);
    }

    //4. 释放读锁数量减1，通知所有等待线程
    public synchronized void unlockRead() {
        state = state - ((1 << 16));
        notifyAll();
    }

    //5. 获取写锁时需要判断读锁和写锁是否都存在，有则等待，没有则将写锁数量加1
    public synchronized void lockWriters() throws InterruptedException{

        writeRequest++; //写请求+1
        while (GetReadCount() > 0 || GetWriteCount() > 0) {
            wait();
        }
        writeRequest--; //获取写锁后写请求-1
        System.out.println("lockWriters ---" + Thread.currentThread().getName());
        state++;
    }

    //6. 释放写锁数量减1，通知所有等待线程
    public synchronized void unlockWriters(){

        state--;
        notifyAll();
    }
}


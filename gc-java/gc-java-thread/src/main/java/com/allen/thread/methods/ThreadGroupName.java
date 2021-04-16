package com.allen.thread.methods;

/**
 * @author xuguocai 2020/1/22 9:37
 */
public class ThreadGroupName implements Runnable {

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("group");
        Thread A = new Thread(group, new ThreadGroupName(), "A");
        Thread B = new Thread(group, new ThreadGroupName(), "B");
        A.start();
        B.start();
        System.out.println("线程组数:"+group.activeCount());
        group.list();
    }

    @Override
    public void run() {
        System.out.println("当前线程组："+Thread.currentThread().getThreadGroup().getName());
        System.out.println("当前线程名称："+Thread.currentThread().getName());
        while (true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

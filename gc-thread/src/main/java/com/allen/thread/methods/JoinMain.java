package com.allen.thread.methods;

/**
 * @author xuguocai 2020/1/22 9:00
 */
public class JoinMain {

    public static void main(String[] args)throws InterruptedException {
        AThread aThread = new AThread();
        aThread.start();
        aThread.join();
        System.out.println("i==="+i);
    }

    public volatile static int i =0;

    public static class AThread extends Thread{
        @Override
        public void run() {
            for (i = 0;i<1000;i++);
        }
    }

}

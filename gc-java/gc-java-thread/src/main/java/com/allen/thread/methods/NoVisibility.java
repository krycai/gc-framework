package com.allen.thread.methods;

/**
 * @author xuguocai 2020/1/22 9:28
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ready==="+ready);

        new Reader().start();
        Thread.sleep(1000);
        System.out.println("当前线程==："+Thread.currentThread().getName());
        number = 20;
        ready=true;
        Thread.sleep(10000);
        System.out.println("当前线程："+Thread.currentThread().getName());
    }

    private static class Reader extends Thread{
        public void run(){
            while (!ready){
                System.out.println("number==="+number);
            }
        }
    }


}

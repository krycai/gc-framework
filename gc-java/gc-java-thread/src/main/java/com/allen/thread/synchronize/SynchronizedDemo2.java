package com.allen.thread.synchronize;

/**
 * Created by xuguocai on 2021/2/7 9:02
 */
public class SynchronizedDemo2 {

    public void method() {
        synchronized (this) {
            System.out.println("synchronized 5555");
        }
    }

}

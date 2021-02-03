package com.allen.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * Created by xuguocai on 2021/2/3 8:47
 *
 * AtomicMarkableReference是将一个boolean值作是否有更改的标记，本质就是它的版本号只有两个，true和false，
 *
 * 修改的时候在这两个版本号之间来回切换，这样做并不能解决ABA的问题，只是会降低ABA问题发生的几率而已
 */
@Slf4j
public class SolveABAByAtomicMarkableReference {
    private static AtomicMarkableReference atomicMarkableReference = new AtomicMarkableReference(100, false);

    public static void main(String[] args) {
        Thread refT1 = new Thread(() -> {
            try {
                // 睡眠 1秒
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /**
             * compareAndSet 方法 将引用对象根据期望的值判断，true则设置成新值和新标记值 false则不设置
             * 如果当前引用 == 预期引用，并且当前标记等于预期标记，那么以原子方式将引用和标记的值设置为给定的更新值。
             *
             * expectedReference - 该引用的预期值
             * newReference - 该引用的新值
             * expectedMark - 该标记的预期值
             * newMark - 该标记的新值
             */
            atomicMarkableReference.compareAndSet(100, 101, atomicMarkableReference.isMarked(), !atomicMarkableReference.isMarked());
            atomicMarkableReference.compareAndSet(101, 100, atomicMarkableReference.isMarked(), !atomicMarkableReference.isMarked());
        });

        Thread refT2 = new Thread(() -> {
            //返回该标记的当前值。
            boolean marked = atomicMarkableReference.isMarked();
            log.info("线程refT2获取的值:{}",marked);
            try {
                // 线程睡眠 2秒
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean c3 = atomicMarkableReference.compareAndSet(100, 101, marked, !marked);
            log.info("线程refT2返回的值:{}",c3);// 返回true,实际应该返回false
        });

        refT1.start();
        refT2.start();
    }
}

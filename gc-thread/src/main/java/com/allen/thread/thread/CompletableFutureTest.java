package com.allen.thread.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by xuguocai on 2021/2/7 10:17
 */
public class CompletableFutureTest {

    @Test
    public void test(){
        String[] list ={"a","b","c","d"};
        List<CompletableFuture> objects = new ArrayList<>();
        for (String item:list){
            objects.add(CompletableFuture.supplyAsync(() ->item).thenApply(e->e.toLowerCase()));
        }

        CompletableFuture.allOf(objects.toArray(new CompletableFuture[objects.size()])).
                whenComplete((r,e)->{
                    if (null == e){
                        System.out.println("=-=-"+r);
                    }else {
                        System.out.println("=-=-==="+r);
                        throw new RuntimeException(e);
                    }
                });

    }

    @Test
    public void test2(){
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();

        System.arraycopy(a, 1, a, 2, 4);
        a[2]=99;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    @Test
    public void test3(){
        List<String> list =new LinkedList<>();
    }
}

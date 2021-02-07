package com.allen.thread.thread;

import org.junit.Test;

import java.util.ArrayList;
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

}

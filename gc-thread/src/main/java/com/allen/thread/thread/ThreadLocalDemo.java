package com.allen.thread.thread;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: MultiThread
 * @description: ThreadLocalDemo
 * @author: allen小哥
 * @Date: 2019-12-14 09:44
 **/
@Slf4j
public class ThreadLocalDemo {

    private static ThreadLocal<SimpleDateFormat> format = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i =0;i< 100;i++){
            service.submit(new DateUtil("2019-11-25 09:00:"+i % 60));
        }
    }

    static class DateUtil implements Runnable{

        private String date;

        public DateUtil(String date) {
            this.date = date;
        }

        @Override
        public void run() {
            if (format.get() ==null){
                format.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }else {
             try {
                 Date parse = format.get().parse(this.date);
                 log.info("执行的时间为：{}",parse);
             }catch (Exception e){
                 e.printStackTrace();
             }
            }
        }
    }

}

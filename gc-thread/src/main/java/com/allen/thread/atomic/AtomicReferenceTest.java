package com.allen.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: MultiThread
 * @description: AtomicReferenceTest
 * @author: allen小哥
 * @Date: 2019-12-15 14:40
 **/
@Slf4j
public class AtomicReferenceTest {

    private static AtomicReference<User> reference = new AtomicReference<User>();

    public static void main(String[] args) {
        User a = new User("a", 1);
        reference.set(a);
        User b = new User("b", 5);
        User andSet = reference.getAndSet(b); // 返回原来的值
        log.info("执行的结果:{}",andSet);
        log.info("值:{}",reference.get());
    }

    static class User{
        private String userName;
        private int age;

        public User(String userName, int age) {
            this.userName = userName;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}

package com.allen.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: MultiThread  引用类型原子类   基本类型原子类只能更新一个变量，如果需要原子更新多个变量，需要使用 引用类型原子类。
 * @description: AtomicReferenceTest
 * @author: allen小哥
 * @Date: 2019-12-15 14:40
 *
 * AtomicReference：引用类型原子类
 * AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，可用于解决原子的更新数据和数据的版本号，可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。
 * AtomicMarkableReference ：原子更新带有标记的引用类型。该类将 boolean 标记与引用关联起来，也可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。
 **/
@Slf4j
public class AtomicReferenceTest {

    private static AtomicReference<User> reference = new AtomicReference<User>();

    public static void main(String[] args) {
        User a = new User("a", 1);
        reference.set(a);
        User b = new User("b", 5);
//        User andSet = reference.getAndSet(b); // 返回原来的值
//        log.info("执行的结果:{}",andSet);
        log.info("值:{}",reference.get());
        System.out.println("===================");


        reference.compareAndSet(a, b);

        log.info("username：{}",reference.get().getUserName());
        log.info("age:{}",reference.get().getAge());
    }

    static class User{
        private String userName;
        private int age;

        public User(String userName, int age) {
            this.userName = userName;
            this.age = age;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
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

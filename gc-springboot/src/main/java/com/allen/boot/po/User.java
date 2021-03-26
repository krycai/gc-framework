package com.allen.boot.po;

/**
 * Created by xuguocai on 2021/3/26 16:07
 */
public class User {

    private Person person;

    private String name;

    private String age;

    private String hello;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}

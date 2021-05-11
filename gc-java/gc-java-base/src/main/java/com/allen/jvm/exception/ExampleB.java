package com.allen.jvm.exception;

public class ExampleB extends ExampleA {

    private String name;

    public ExampleB(String name){
        this.name = name;
        System.out.println("ExampleB:"+name);
    }
    public ExampleB(){
        System.out.println("ExampleB-=-=");
    }

}

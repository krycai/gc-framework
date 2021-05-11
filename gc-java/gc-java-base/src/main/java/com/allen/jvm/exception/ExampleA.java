package com.allen.jvm.exception;

public class ExampleA extends Exception {
    private String name;

    public ExampleA(String name){
        this.name = name;
        System.out.println("ExampleA:"+name);
    }

    public ExampleA(){}
}

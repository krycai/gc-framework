package com.allen.java.exception;

public class ExampleTest {

    public static void main(String[] args) throws Exception{
        try {
            try {
                throw new ExampleB();
            }
            catch ( ExampleA a ) {
                System.out.println("Caught Annoyance");
                throw a;
            } }
        catch ( ExampleB s ) {
            System.out.println("Caught Sneeze");
            return ; }
        finally {
            System.out.println("Hello World!");
        }
    }

}

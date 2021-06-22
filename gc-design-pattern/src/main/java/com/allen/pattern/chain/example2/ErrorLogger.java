package com.allen.pattern.chain.example2;

/**
 * @author xuguocai on 2021/6/22 10:15  创建扩展了该记录器类的实体类
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}

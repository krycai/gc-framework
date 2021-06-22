package com.allen.pattern.chain.example2;

/**
 * @author xuguocai on 2021/6/22 10:16 创建扩展了该记录器类的实体类
 */
public class FileLogger extends AbstractLogger{

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}

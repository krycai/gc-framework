package com.allen.boot.inject.set;

import com.allen.boot.inject.SpringDao;
import com.allen.boot.po.User;

/**
 * Created by xuguocai on 2021/3/26 16:06
 */
public class SpringDaoImpl implements SpringDao {
    @Override
    public void ok(){
        System.out.println("调用com.allen.boot.inject.SpringAction 的方法ok()");
    }

    @Override
    public void save(User user) {
        System.out.println("==========保存方法================="+user);
    }

    @Override
    public void saveFactory() {
        System.out.println("========静态工厂===============");
    }

    @Override
    public void insertFactory() {
        System.out.println("=-=-= 工厂方法 ---====---");
    }
}

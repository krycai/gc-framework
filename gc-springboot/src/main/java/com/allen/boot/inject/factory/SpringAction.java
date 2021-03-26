package com.allen.boot.inject.factory;

import com.allen.boot.inject.SpringDao;

/**
 * Created by xuguocai on 2021/3/26 16:45
 *
 * 获取对象实例的方法不是静态的，所以你需要首先new工厂类，再调用普通的实例方法：
 */
public class SpringAction {

    //注入对象
    private SpringDao springDao;

    public void factoryOk(){
        springDao.insertFactory();
    }

    public void setFactoryDao(SpringDao factoryDao) {
        this.springDao = factoryDao;
    }

}

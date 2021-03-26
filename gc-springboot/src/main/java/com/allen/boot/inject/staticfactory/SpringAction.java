package com.allen.boot.inject.staticfactory;

import com.allen.boot.inject.SpringDao;

/**
 * Created by xuguocai on 2021/3/26 16:34
 *
 * 通过调用静态工厂的方法来获取自己需要的对象，为了让spring管理所有对象，我们不能直接通过”工程类.静态方法()”来获取对象，
 * 而是依然通过spring注入的形式获取：
 */
public class SpringAction {
    //注入对象
    private SpringDao staticFactoryDao;

    public void staticFactoryOk(){
        staticFactoryDao.saveFactory();
    }

    //注入对象的set方法
    public void setStaticFactoryDao(SpringDao staticFactoryDao) {
        this.staticFactoryDao = staticFactoryDao;
    }

}

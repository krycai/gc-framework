package com.allen.boot.inject.staticfactory;

import com.allen.boot.inject.SpringDao;
import com.allen.boot.inject.set.SpringDaoImpl;

/**
 * Created by xuguocai on 2021/3/26 16:30
 */
public class StaticDaoFactory {
    //静态工厂
    public static final SpringDao getStaticFactoryDaoImpl(){
        return new SpringDaoImpl();
    }
}

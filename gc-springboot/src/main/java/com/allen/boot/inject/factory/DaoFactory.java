package com.allen.boot.inject.factory;

import com.allen.boot.inject.SpringDao;
import com.allen.boot.inject.set.SpringDaoImpl;

/**
 * Created by xuguocai on 2021/3/26 16:39
 *
 * 获取对象实例的方法不是静态的，所以你需要首先new工厂类，再调用普通的实例方法：
 */
public class DaoFactory {

    //实例工厂
    public SpringDao getFactoryDaoImpl(){
        return new SpringDaoImpl();
    }

}

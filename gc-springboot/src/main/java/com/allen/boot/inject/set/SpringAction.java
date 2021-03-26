package com.allen.boot.inject.set;

import com.allen.boot.inject.SpringDao;

/**
 * Created by xuguocai on 2021/3/26 15:58
 */
public class SpringAction {

    //注入对象springDao
    private SpringDao springDao;

    // ================ set注入 ============================

    //一定要写被注入对象的set方法
    public void setSpringDao(SpringDao springDao) {
        this.springDao = springDao;
    }

    public void ok(){
        springDao.ok();
    }

}

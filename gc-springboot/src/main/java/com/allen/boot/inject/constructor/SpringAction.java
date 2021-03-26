package com.allen.boot.inject.constructor;

import com.allen.boot.inject.SpringDao;
import com.allen.boot.po.User;

/**
 * Created by xuguocai on 2021/3/26 15:58  指带有参数的构造函数注入
 */
public class SpringAction {

    //注入对象springDao
    private SpringDao springDao;

    private User user;

    // ==================== 构造函数注入 =====================

    public SpringAction(SpringDao springDao,User user){
        this.springDao = springDao;
        this.user = user;
        System.out.println("构造方法调用springDao和user");
    }

    public void save(){
        user.setName("卡卡");
        springDao.save(user);
    }

}

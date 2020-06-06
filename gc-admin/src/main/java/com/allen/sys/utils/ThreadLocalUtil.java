package com.allen.sys.utils;

import com.allen.sys.model.po.SysUser;

/**
 * @author: allen小哥 2020-06-06 15:47
 **/
public class ThreadLocalUtil {

    private static ThreadLocal<SysUser> LOCAL = new ThreadLocal<SysUser>();

    public ThreadLocalUtil() {}

    public static void set(SysUser user){
        LOCAL.set(user);
    }

    public static SysUser get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }

}

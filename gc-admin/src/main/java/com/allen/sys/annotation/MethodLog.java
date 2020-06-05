package com.allen.sys.annotation;

import java.lang.annotation.*;

/**
 * @author xuguocai 2020/5/29 9:22
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {

    //对标记接口的补充说明
    String content() default "";

    String operType() default "0";
    //操作等级
    int operLevel() default 0;
}

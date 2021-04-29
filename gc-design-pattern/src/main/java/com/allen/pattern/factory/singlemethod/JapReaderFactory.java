package com.allen.pattern.factory.singlemethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName JapReaderFactory
 * @Description TODO   具体工厂。制造产品的实际工厂。它负责创建一个或者多个具体产品，只有JapReaderFactory类知道如何创建这些产品。
 * @Author Xu
 * @Date 2019/3/19 17:08
 **/
@Slf4j
public class JapReaderFactory implements ReaderFactory {
    @Override
    public Reader getReader() {
        log.info("调用jpg加载工厂");
        return new JpaReader();
    }
}

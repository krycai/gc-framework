package com.allen.pattern.factory.singlemethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName JapReaderFactory
 * @Description TODO
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

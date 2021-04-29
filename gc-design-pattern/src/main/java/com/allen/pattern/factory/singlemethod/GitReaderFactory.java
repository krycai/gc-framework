package com.allen.pattern.factory.singlemethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName GitReaderFactory
 * @Description TODO  具体工厂。制造产品的实际工厂。它负责创建一个或者多个具体产品，只有GitReaderFactory类知道如何创建这些产品。
 * @Author Xu
 * @Date 2019/3/19 17:09
 **/
@Slf4j
public class GitReaderFactory implements ReaderFactory {

    @Override
    public Reader getReader() {
        log.info("调用git工厂类");
        return new GitReader();
    }

}

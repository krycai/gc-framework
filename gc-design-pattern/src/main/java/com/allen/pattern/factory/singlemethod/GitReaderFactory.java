package com.allen.pattern.factory.singlemethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName GitReaderFactory
 * @Description TODO
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

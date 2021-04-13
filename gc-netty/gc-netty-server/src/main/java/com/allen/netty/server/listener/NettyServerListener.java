package com.allen.netty.server.listener;

import com.allen.netty.server.config.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by xuguocai on 2021/4/12 16:15  netty 服务端
 */
@WebListener
public class NettyServerListener implements ServletContextListener {

    private Logger log = LoggerFactory.getLogger(NettyServerListener.class);

    /** 注入NettyServer */
    @Autowired
    private NettyServer nettyServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ServletContex初始化...");

        Thread thread = new Thread(new NettyServerThread());
        // 启动netty服务
        thread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    /**
     * Netty 服务启动线程
     */
    private class NettyServerThread implements Runnable {

        @Override
        public void run() {
            nettyServer.run();
        }
    }


}

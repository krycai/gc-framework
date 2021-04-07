package com.allen.apollo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuguocai on 2021/4/7 9:08
 */
@RestController
public class ApolloCOntroller {

    private static Logger logger = LoggerFactory.getLogger( ApolloCOntroller.class );

    @Value( "${server.port}" )
    String port;

    @GetMapping("hi")
    public String hi(String name) {

        logger.debug( "debug log..." );
        logger.info( "info log..." );
        logger.warn( "warn log..." );

        return "hi " + name + " ,i am from port:" + port;
    }
}

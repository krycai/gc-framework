package com.allen.boot.controller;

import com.allen.boot.properties.AllenProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义属性配置 自定义配置获取
 *
 * Created by xuguocai on 2020/11/30 15:17
 */
@RequestMapping("/allen")
@RestController
public class BootController {
    private static final Logger log = LoggerFactory.getLogger(BootController.class);

    private AllenProperties allenProperties;

    public BootController(AllenProperties allenProperties){
        this.allenProperties = allenProperties;
    }

    @GetMapping("/1")
    public AllenProperties myProperties1() {
        log.info("=================================================================================================");
        log.info(allenProperties.toString());
        log.info("=================================================================================================");
        return allenProperties;
    }
}

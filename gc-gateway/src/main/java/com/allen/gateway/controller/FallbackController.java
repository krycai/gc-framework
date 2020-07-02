package com.allen.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断后转发处理类
 * @author xuguocai 2020/7/2 13:32
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public String fallback() {
        return "fallback";
    }

}

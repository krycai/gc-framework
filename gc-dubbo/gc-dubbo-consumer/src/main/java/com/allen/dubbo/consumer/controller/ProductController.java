package com.allen.dubbo.consumer.controller;

import com.allen.dubbo.consumer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuguocai on 2021/3/31 11:57
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("getCost")
    public String getCost(int a){
        return "该产品总共消费 ："+productService.getCost(a);
    }
}

package com.allen.es.controller;

import com.allen.es.po.DocBean;
import com.allen.es.service.IElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xuguocai on 2021/4/7 10:47
 */

@RestController
@RequestMapping("/elastic")
public class ElasticController {

    @Autowired
    private IElasticService elasticService;

    @GetMapping("/init")
    public void init() {
//        elasticService.createIndex();
        List<DocBean> list = new ArrayList<>();
        for (long i = 4;i<20000;i++){
            list.add(new DocBean(i, "XX0193hhh", "XX8064hhh", "xxxxxxgghhhh"+i, 1));
        }

        elasticService.saveAll(list);

    }

    @GetMapping("/all")
    public Iterator<DocBean> all() {
        return elasticService.findAll();
    }

}
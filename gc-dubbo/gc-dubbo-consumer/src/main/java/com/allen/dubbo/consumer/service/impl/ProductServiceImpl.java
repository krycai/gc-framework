package com.allen.dubbo.consumer.service.impl;

import com.allen.dubbo.api.CostService;
import com.allen.dubbo.consumer.service.ProductService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * Created by xuguocai on 2021/3/31 11:54
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * 使用dubbo的注解 以前：org.apache.dubbo.config.annotation.Reference;。进行远程调用service
     */
    @Reference
    private CostService costService;

    @Override
    public Integer getCost(int a) {
        return costService.add(a);
    }
}

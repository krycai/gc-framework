package com.allen.dubbo.provider.service.impl;

import com.allen.dubbo.api.CostService;
import org.apache.dubbo.config.annotation.Service;

/**
 * Created by xuguocai on 2021/3/31 13:43
 */
@Service
public class CostServiceImpl implements CostService {
    /**
     * 假设之前总花费了100
     */
    private final Integer totalCost = 1000;

    /**
     * 之前总和 加上 最近一笔
     * @param cost
     * @return
     */
    @Override
    public Integer add(int cost) {
        return totalCost + cost;
    }

}

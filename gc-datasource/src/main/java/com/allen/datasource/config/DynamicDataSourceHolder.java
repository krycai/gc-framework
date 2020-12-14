package com.allen.datasource.config;

import com.allen.datasource.common.DynamicDataSourceGlobal;

/**
 * Created by xuguocai on 2020/12/14 14:28
 */
public final class DynamicDataSourceHolder {

    /**
     * 动态数据源存储
     */
    private static final ThreadLocal<DynamicDataSourceGlobal> DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL = new ThreadLocal<>();

    private DynamicDataSourceHolder() {
        //
    }

    /**
     * 存放数据源
     *
     * @param dataSource 数据源
     */
    public static void putDataSource(DynamicDataSourceGlobal dataSource) {
        DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL.set(dataSource);
    }

    /**
     * 获取数据源
     *
     * @return the data source
     */
    public static DynamicDataSourceGlobal getDataSource() {
        return DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource() {
        DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL.remove();
    }

}

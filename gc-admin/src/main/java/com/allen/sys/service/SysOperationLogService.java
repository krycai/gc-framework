package com.allen.sys.service;

import com.allen.sys.model.dto.LogParamDto;
import com.github.pagehelper.PageInfo;

/**
 * 系统日志表
 * 
 * @author xuguocai
 * @email xuguocai@bluemoon.com.cn
 * @date 2020-05-29 10:21:39
 */
public interface SysOperationLogService {

    /**
     * 保存操作
     * @param operationLog
     */
    void insert(SysOperationLog operationLog);

    /**
     * 分页操作
     * @param paramDto
     * @return
     */
    PageInfo<SysOperationLog> findPage(LogParamDto paramDto);

}

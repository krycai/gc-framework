package com.allen.sys.controller;

import com.allen.sys.annotation.MethodLog;
import com.allen.sys.common.ResponseBean;
import com.allen.sys.common.ResponseBeanUtil;
import com.allen.sys.model.dto.LogParamDto;
import com.allen.sys.model.po.SysOperationLog;
import com.allen.sys.service.SysOperationLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuguocai 2020/5/29 13:28
 */
@RestController
@CrossOrigin
@RequestMapping("/operationLog")
public class SysOperationLogController {

    @Autowired
    private SysOperationLogService sysOperationLogService;

    @MethodLog(content = "获取日志列表接口")
    @PostMapping(value = "/list")
    public ResponseBean<PageInfo<SysOperationLog>> list(@RequestBody LogParamDto paramDto) {
        PageInfo<SysOperationLog> page = sysOperationLogService.findPage(paramDto);
        return ResponseBeanUtil.ok(page);
    }

}

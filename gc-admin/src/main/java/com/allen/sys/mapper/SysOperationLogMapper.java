package com.allen.sys.mapper;

import com.allen.sys.model.dto.LogParamDto;
import com.allen.sys.model.po.SysOperationLog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysOperationLogMapper extends Mapper<SysOperationLog> {

    /**
     * 分页
     * @param paramDto
     * @return
     */
    List<SysOperationLog> findPage(@Param("param") LogParamDto paramDto);
}
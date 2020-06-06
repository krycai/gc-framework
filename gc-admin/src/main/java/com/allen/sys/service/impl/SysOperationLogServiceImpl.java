package com.allen.sys.service.impl;

import com.allen.sys.mapper.SysOperationLogMapper;
import com.allen.sys.model.dto.LogParamDto;
import com.allen.sys.model.po.SysOperationLog;
import com.allen.sys.service.SysOperationLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOperationLogServiceImpl implements SysOperationLogService {

	@Autowired
	private SysOperationLogMapper sysOperationLogMapper;

	@Override
	public PageInfo<SysOperationLog> findPage(LogParamDto paramDto) {
		PageHelper.startPage(paramDto.getPageNum(), paramDto.getPageSize());
		List<SysOperationLog> list = sysOperationLogMapper.findPage(paramDto);
		return new PageInfo<>(list);
	}

	@Override
	public void insert(SysOperationLog operationLog) {
		sysOperationLogMapper.insert(operationLog);
	}
}

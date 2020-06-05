package com.allen.sys.service.impl;

import cn.com.bluemoon.mybatis.api.Paging;
import cn.com.bluemoon.qy.pojo.dto.GeneratorDto;
import com.allen.sys.mapper.SysGeneratorMapper;
import com.allen.sys.model.dto.FormCodeDto;
import com.allen.sys.service.SysGeneratorService;
import com.allen.sys.utils.GeneratorUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author xuguocai 2020/6/1 10:43
 */
@Service
public class SysGeneratorServiceImpl implements SysGeneratorService {

	@Autowired
	private SysGeneratorMapper sysGeneratorMapper;

	@Override
	public Map<String, String> queryTable(String tableName) {
		return sysGeneratorMapper.queryTable(tableName);
	}

	@Override
	public List<Map<String, String>> queryColumns(String tableName) {
		return sysGeneratorMapper.queryColumns(tableName);
	}

	@Override
	public PageInfo<SysTable> findTablePage(GeneratorDto generatorDto) {
		// 执行分页查询
		Paging page = generatorDto.getPage();
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<SysTable> list = sysGeneratorMapper.findTablePage(generatorDto.getTableName());
		return new PageInfo<>(list);
	}


	@Override
	public byte[] generatorCode(FormCodeDto formCodeDto) {
		String tables = formCodeDto.getTables();
		if (StringUtils.isEmpty(tables)){
			throw new RuntimeException("数据表不能为空");
		}
		String[] tableNames = tables.split(",");
		String allPackage = formCodeDto.getPackageName();
		String author = formCodeDto.getAuthor();
		String email = formCodeDto.getEmail();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		
		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			//生成代码
			//GeneratorUtil.generatorCode(table, columns, zip,allPackage,author,email,formCodeDto.getType());
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}

package com.allen.sys.controller;


import com.allen.sys.annotation.MethodLog;
import com.allen.sys.common.ResponseBean;
import com.allen.sys.common.ResponseBeanUtil;
import com.allen.sys.model.dto.FormCodeDto;
import com.allen.sys.model.dto.GeneratorParam;
import com.allen.sys.model.po.SysTable;
import com.allen.sys.service.SysGeneratorService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @Description  代码生成器
 */

@Validated
@RestController
@CrossOrigin()
@RequestMapping("/admin/sys/generator")
public class SysGeneratorController  {

	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@MethodLog(content = "获取代码生成器列表接口")
	@PostMapping(value="/list")
	public ResponseBean list(@RequestBody GeneratorParam param) {
		PageInfo<SysTable> tablePage = sysGeneratorService.findTablePage(param);
		return ResponseBeanUtil.ok(tablePage);
	}
	
	/**
	 * 生成代码
	 */
	@MethodLog(content = "代码生成接口")
	@PostMapping(value="/code")
	public void code(HttpServletRequest request, HttpServletResponse response, @RequestBody FormCodeDto formCodeDto) throws IOException {
		System.out.println("传参:"+formCodeDto);

		byte[] data = sysGeneratorService.generatorCode(formCodeDto);

		setResponse(response, data);
		IOUtils.write(data, response.getOutputStream());
		System.out.println("---------------结束-----------------");
	}

	private void setResponse(HttpServletResponse response, byte[] data) {
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Disposition", "attachment;filename=\"source-code.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
	}

}

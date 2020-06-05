package com.allen.sys.controller;

import com.allen.sys.annotation.MethodLog;
import com.allen.sys.common.ResponseBean;
import com.allen.sys.common.ResponseBeanUtil;
import com.allen.sys.model.dto.SysDictParam;
import com.allen.sys.model.po.SysDictEntry;
import com.allen.sys.model.po.SysDictType;
import com.allen.sys.service.SysDictService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 业务字典相关控制器类
 */
@Validated
@RestController
@CrossOrigin
@RequestMapping("/admin/sys/dict")
public class SysDictController {
	
	@Autowired
	private SysDictService dictService;

	/**
	 * This get dictType page info.
	 * @param param
	 * @return
	 */
	@MethodLog(content = "获取字典类型列表接口")
	@PostMapping(value="/type/list")
	public ResponseBean dictTypeList(@RequestBody SysDictParam param ){
		PageInfo<SysDictType> dictTypePage = dictService.findDictTypePage(param);
		return ResponseBeanUtil.ok(dictTypePage);
	}
	
	/**
	 * save dict type info
	 * @param dictType
	 * @return
	 */
	@MethodLog(content = "保存字典类型信息接口")
	@PostMapping(value = "/type/save")
	public ResponseBean saveDictType( @RequestBody SysDictType dictType ){
		//如果是在新增业务字典的情况下,校验dicttypeId是否存在
		if( dictType.getId() == null ){
			if( dictService.checkDictTypeId(dictType.getTypeCode()) ){
				return ResponseBeanUtil.fail(-1, "类型代码已经存在");
			}
		}
		dictService.saveDictType(dictType);
		return ResponseBeanUtil.ok();
	}
	
	/**
	 * delete dict type info.
	 * @param id
	 */
	@MethodLog(content = "删除字典类型信息接口")
	@GetMapping(value = "/type/delete")
	public ResponseBean deleteDictType( Integer id ){
		List<SysDictEntry> entryList = dictService.getDictEntryByTypeId(id);
		dictService.deleteDictTypeById(id);
		
		//同时删除entry里面的字典子项信息
		for( SysDictEntry entry:entryList ){
			dictService.deleteDictEntryById(entry.getId());
		}
		return ResponseBeanUtil.ok("删除成功");
	}

	/**
	 * get dict type info.
	 * @param id
	 */
	@MethodLog(content = "获取字典类型信息接口")
	@GetMapping(value = "/type/getTypeInfo")
	public ResponseBean getTypeInfo(Integer id ){
		if (null == id){
			return ResponseBeanUtil.fail(-1,"类型的ID不能为空");
		}
		SysDictType typeInfo = dictService.getTypeInfo(id);
		return ResponseBeanUtil.ok(typeInfo);
	}
	
	/**
	 * get dictEntry page info.
	 * @param param
	 * @return
	 */
	@MethodLog(content = "获取字典项列表接口")
	@PostMapping(value="/entry/list")
	public ResponseBean dictEntryList( @RequestBody SysDictParam param ){
		PageInfo<SysDictEntry> dictEntryPage = dictService.findDictEntryPage(param);
		return ResponseBeanUtil.ok(dictEntryPage);
	}
	
	/**
	 * save dict entry info.
	 * @param dictEntry
	 * @return
	 */
	@MethodLog(content = "保存字典项接口")
	@PostMapping(value = "/entry/save")
	public ResponseBean saveDictEntry(  @RequestBody SysDictEntry dictEntry ){
		//如果是在新增字典类型情况下，校验当前的dicttypeId是否已经存在
		if( dictEntry.getId() == null ){
			if( dictService.checkDictId(dictEntry.getDictCode(), dictEntry.getDictCode()) ){
				return ResponseBeanUtil.fail(1001, "字典项代码已经存在");
			}
		}
		dictService.saveDictEntry(dictEntry);
		return ResponseBeanUtil.ok();
	}
	
	/**
	 * delete dict entry info.
	 * @param id
	 */
	@MethodLog(content = "删除字典项接口")
	@GetMapping(value = "/entry/delete")
	public ResponseBean deleteDictEntry(Integer id){
		dictService.deleteDictEntryById(id);
		return ResponseBeanUtil.ok();
	}

	/**
	 * delete dict entry info.
	 * @param id
	 */
	@MethodLog(content = "获取字典项信息接口")
	@GetMapping(value = "/entry/getEntryInfo")
	public ResponseBean getEntryInfo(Integer id){
		SysDictEntry dictEntryInfo = dictService.getDictEntryInfo(id);
		return ResponseBeanUtil.ok(dictEntryInfo);
	}

}

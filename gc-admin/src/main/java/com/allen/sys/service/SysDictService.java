package com.allen.sys.service;

import com.allen.sys.model.dto.SysDictParam;
import com.allen.sys.model.po.SysDictEntry;
import com.allen.sys.model.po.SysDictType;
import com.allen.sys.model.vo.DictInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author xuguocai 2020/6/1 10:43
 */
public interface SysDictService {
	
	/**
	 * 查询业务字典列表
	 * @param param 查询参数
	 * @return	分页数据 page info 
	 */
	PageInfo<SysDictType> findDictTypePage(SysDictParam param);
	
	/**
	 * 保存业务字典信息
	 * @param dictType 业务字典
	 * @return	dictType
	 */
	SysDictType saveDictType(SysDictType dictType);
	
	/**
	 * 更新业务字典信息
	 * @param dictType
	 */
	void updateDictType(SysDictType dictType);
	
	/**
	 * 删除业务字典信息
	 * @param id
	 */
	void deleteDictTypeById(Integer id);

	/**
	 * 获取业务字典信息
	 * @param id
	 */
	SysDictType getTypeInfo(Integer id);

	/**
	 * 查询业务字典子项列表
	 * @param param
	 * @return
	 */
	PageInfo<SysDictEntry> findDictEntryPage(SysDictParam param);

	/**
	 * 保存字典子项信息
	 * @param dictEntry
	 * @return
	 */
	SysDictEntry saveDictEntry(SysDictEntry dictEntry);

	/**
	 * 更新字典子项信息
	 * @param dictEntry
	 */
	void updateDictEntry(SysDictEntry dictEntry);

	/**
	 * 根据主键删除字典子项信息
	 * @param id
	 */
	void deleteDictEntryById(Integer id);

	SysDictEntry getDictEntryInfo(Integer id);

	/**
	 * 检查dicttypeId是否存在，存在返回true，不存在返回false
	 * @param dicttypeId
	 * @return
	 */
	boolean checkDictTypeId(String dicttypeId);

	/**
	 * 检查dictId是否存在，存在返回true,不存在返回false
	 * @param dicttypeId
	 * @param dictId
	 * @return
	 */
	boolean checkDictId(String dicttypeId, String dictId);

	/**
	 * 根据字典项ID获取entry的列表
	 * @param id
	 * @return
	 */
	List<SysDictEntry> getDictEntryByTypeId(Integer id);

}

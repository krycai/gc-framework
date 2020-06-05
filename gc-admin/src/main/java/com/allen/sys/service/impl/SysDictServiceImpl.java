package com.allen.sys.service.impl;

import com.allen.sys.mapper.SysDictEntryMapper;
import com.allen.sys.mapper.SysDictTypeMapper;
import com.allen.sys.model.dto.SysDictParam;
import com.allen.sys.model.po.SysDictEntry;
import com.allen.sys.model.po.SysDictType;
import com.allen.sys.service.SysDictService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;


/**
 * @author xuguocai 2020/6/1 10:43
 */
@Service
@Transactional(readOnly = true)
public class SysDictServiceImpl implements SysDictService {
	
	@Autowired
	private SysDictEntryMapper dictEntryMapper;
	@Autowired
	private SysDictTypeMapper dictTypeMapper;

	@Override
	public PageInfo<SysDictType> findDictTypePage(SysDictParam param) {
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<SysDictType> list = dictTypeMapper.findList(param.getTypeCode(),param.getTypeName());
		return new PageInfo<>(list);
	}

	@Override
	@Transactional(readOnly = false)
	public SysDictType saveDictType(SysDictType dictType) {
		if( dictType.getId() == null ){
			dictType.setCreateTime(new Date());
			dictType.setUpdateTime(new Date());
			dictTypeMapper.insert(dictType);
		}else{
			dictType.setUpdateTime(new Date());
			dictTypeMapper.updateByPrimaryKeySelective(dictType);
		}
		return dictType;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateDictType(SysDictType dictType) {
		dictType.setUpdateTime(new Date());
		dictTypeMapper.updateByPrimaryKeySelective(dictType);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteDictTypeById(Integer id) {
		SysDictType type = new SysDictType();
		type.setId(id);
		type.setDelFlag(true);
		dictTypeMapper.updateByPrimaryKeySelective(type);
	}

	@Override
	public SysDictType getTypeInfo(Integer id) {
		return dictTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<SysDictEntry> findDictEntryPage(SysDictParam param) {
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<SysDictEntry> list = dictEntryMapper.findList(param.getTypeCode(),null);
		return new PageInfo<> (list);
	}

	@Override
	@Transactional(readOnly = false)
	public SysDictEntry saveDictEntry(SysDictEntry dictEntry) {
		if( dictEntry.getId() == null ){
			dictEntry.setCreateTime(new Date());
			dictEntry.setUpdateTime(new Date());
			dictEntryMapper.insert(dictEntry);
		}else{
			dictEntry.setUpdateTime(new Date());
			dictEntryMapper.updateByPrimaryKeySelective(dictEntry);
		}
		return dictEntry;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateDictEntry(SysDictEntry dictEntry) {
		dictEntry.setUpdateTime(new Date());
		dictEntryMapper.updateByPrimaryKeySelective(dictEntry);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteDictEntryById(Integer id) {
		SysDictEntry dictEntry = new SysDictEntry();
		dictEntry.setId(id);
		dictEntry.setDelFlag(true);
		dictEntryMapper.updateByPrimaryKeySelective(dictEntry);
	}

	@Override
	public SysDictEntry getDictEntryInfo(Integer id) {
		return dictEntryMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean checkDictTypeId(String typeCode) {
		List<SysDictType> list = dictTypeMapper.findList(typeCode,null);
		if(CollectionUtils.isEmpty(list)){
			return false;
		}
		return true;
	}

	@Override
	public boolean checkDictId(String typeCode, String dictCode) {
		SysDictEntry dictEntry = new SysDictEntry();
		dictEntry.setTypeCode(typeCode);
		dictEntry.setDictCode(dictCode);
		int count = dictEntryMapper.selectCount(dictEntry);
		if(count < 1){
			return false;
		}
		return true;
	}

	@Override
	public List<SysDictEntry> getDictEntryByTypeId(Integer id) {
		SysDictType type = dictTypeMapper.selectByPrimaryKey(id);
		SysDictEntry entry = new SysDictEntry();
		entry.setTypeCode(type.getTypeCode());
		return dictEntryMapper.select(entry);
	}

}

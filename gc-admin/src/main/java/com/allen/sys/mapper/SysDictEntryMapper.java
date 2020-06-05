package com.allen.sys.mapper;

import com.allen.sys.model.po.SysDictEntry;
import com.allen.sys.model.po.SysDictType;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysDictEntryMapper extends Mapper<SysDictEntry> {

    List<SysDictEntry> findList(@Param("typeCode")String typeCode,@Param("dictCode")String dictCode);

}